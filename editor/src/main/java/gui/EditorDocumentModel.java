package gui;

import java.io.File;
import java.io.InputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.nio.file.Path;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.Collections;
import java.util.List;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import javafx.concurrent.Worker;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import javafx.scene.web.WebEngine;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.Token;
import org.apache.commons.io.IOUtils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import netscape.javascript.JSObject;

import prover.Prover;
import prover.ProvingHistory;
import prover.TPTPDefinitions;
import util.tree.Node;
import util.SpanElement;

import parser.ParseContext;
import parser.AstGen;

import exceptions.ParseException;

public class EditorDocumentModel
{
    private static Logging log = Logging.getInstance();
    private static ProvingHistory provingHistory = ProvingHistory.getInstance();

    private Path path; // Path to document. Meaning of value null see constructor.

    public WebEngine engine;
    private Document doc;
    public WebKitStyle style;

    private HashMap<Integer, Node> includes;

    private EditorDocumentViewController view;

    private LinkedList<String> css;

    private Queue<Callable<Void>> delayedActions;

    private int parserNodeIdCur = 0;

    public class JSCallbackListener
    {
        private EditorDocumentModel model;
        public JSCallbackListener(EditorDocumentModel model)
        {
            this.model = model;
        }
        public int start_parsing(int startNode, int endNode)
        {
            try
            {
                return model.reparseArea(startNode, endNode);
            }
            catch(Throwable e)
            {
                e.printStackTrace();
                throw e;
            }
        }
        public void debug(String str)
        {
            System.out.println("DEBUG = " + str);
        }
        public void sleep(Integer ms) {
            try {Thread.sleep(ms.longValue()); }
            catch(InterruptedException e) {}
        }
    }

    private JSCallbackListener jsCallbackListener;

    public EditorDocumentModel(WebEngine engine, EditorDocumentViewController view)
    {
        provingHistory.addDocument(this);
        this.engine = engine;
        this.view = view;
        this.style = new WebKitStyle();

        this.delayedActions = new LinkedList<>();

        this.engine.getLoadWorker().stateProperty().addListener(
            new ChangeListener<Worker.State>()
            {
                @Override
                public void changed(ObservableValue ov, Worker.State oldState, Worker.State newState)
                {
                    if(newState == Worker.State.SUCCEEDED)
                    {
                        jsCallbackListener = new JSCallbackListener(EditorDocumentModel.this);

                        doc = engine.getDocument();
                        style.setDoc(doc);

                        JSObject window = (JSObject) engine.executeScript("window");
                        window.setMember("java", jsCallbackListener);

                        maybeCallDelayedActions();
                    }
                }
            }
        );

        try
        {
            engine.loadContent(
                IOUtils.toString(getClass().getResourceAsStream("/gui/editor.html"), "UTF-8")
            );
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }

        // Extract css classes for syntax highlighting from our css file.
        this.css = new LinkedList<String>();
        Scanner scanner;
        scanner = new Scanner(getClass().getResourceAsStream("/gui/editorHighlighting.css"));
        String match = null;
        Pattern pattern = Pattern.compile("\\n\\s*(\\.[^\\. ]+)\\s*\\{");
        while ((match = scanner.findWithinHorizon(pattern, 0)) != null) {
            Matcher matcher = pattern.matcher(match);
            matcher.find();
            css.add(matcher.group(1).substring(1));
            css.add("functor");
            css.add("defined_functor");
            css.add("system_functor");
        }
        scanner.close();

        this.includes = new HashMap<>();
    }

    private void maybeCallDelayedActions()
    {
        if(doc != null)
        {
            while(!delayedActions.isEmpty())
            {
                try
                {
                    delayedActions.poll().call();
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                    log.debug(e.getMessage());
                }
            }
        }
    }

    public boolean isEmpty()
    {
        /* TODO */
        return false;
    }

    public void reparse()
    {
        reparseArea(-1, -1);
    }

    /**
     * Inserts a text node as child of parent and before sibling,
     * inserting the appropriate newline markers.
     *
     * @param text          The text content of the new text node.
     * @param parent        The parent node of the new text node.
     * @param sibling       The next sibling of the new text node. If this argument is null the node is inserted as last child of parent.
     * @param cursorStart   The position at which the cursor_start node was found or -1 if it isn't contained in the parsed text.
     * @param cursorEnd     The position at which the cursor_end node was found or -1 if it isn't contained in the parsed text.
     * @param isFirst       Whether this if the first entry of the file. If true a newline marker is inserted at the beginning of the first line.
     */
    private void insertNewTextNode(String text, org.w3c.dom.Node parent, org.w3c.dom.Node sibling, int cursorStart, int cursorEnd, boolean isFirst)
    {
        int start = 0;
        int end = 0;
        do
        {
            start = end;
            end = text.indexOf('\n', start);

            if(end == -1)
                end = text.length();
            else
                end++;

            boolean insertStart = false;
            if(start < cursorStart && cursorStart <= end)
            {
                end = cursorStart;
                insertStart = true;
            }

            boolean insertEnd = false;
            if(start < cursorEnd && cursorEnd <= end)
            {
                end = cursorEnd;
                insertEnd = true;
            }

            if(isFirst)
            {
                isFirst = false;

                Element nl = doc.createElement("subsection");
                nl.setAttribute("class", "new_line");
                parent.insertBefore(nl, sibling);
            }

            if(!text.isEmpty())
            {
                String s = text.substring(start, end);
                Text textNode = doc.createTextNode(s);
                parent.insertBefore(textNode, sibling);
            }

            if(insertStart)
            {
                Element cursor_start = doc.createElement("span");
                cursor_start.setAttribute("id", "cursor_start");
                parent.insertBefore(cursor_start, sibling);
            }

            if(insertEnd)
            {
                Element cursor_end = doc.createElement("span");
                cursor_end.setAttribute("id", "cursor_end");
                parent.insertBefore(cursor_end, sibling);
            }

            if(!text.isEmpty() && text.charAt(end-1) == '\n')
            {
                Element nl = doc.createElement("subsection");
                nl.setAttribute("class", "new_line");
                parent.insertBefore(nl, sibling);
            }
        }
        while(end < text.length());
    }

    /**
     * Inserts a text node as child of parent and before sibling,
     * inserting the appropriate newline markers.
     *
     * @param text              The text content of the new text node.
     * @param parent            The parent node of the new text node.
     * @param cursorStartPos    The position at which the cursor_start node was found or -1 if it isn't contained in the parsed text.
     * @param cursorEndPos      The position at which the cursor_end node was found or -1 if it isn't contained in the parsed text.
     * @param isFirst           Whether this if the first entry of the file. If true a newline marker is inserted at the beginning of the first line.
     */
    private void insertNewTextNode(String text, org.w3c.dom.Node parent, int cursorStartPos, int cursorEndPos, boolean isFirst)
    {
        insertNewTextNode(text, parent, null, cursorStartPos, cursorEndPos, isFirst);
    }

    /**
     * Reparse an area as indicated by the surrounding node ids.
     *
     * @param leftNodeId    The leftmost node ID to be reparsed. If it is
     *                      -1 parsing starts from the start.
     * @param rightNodeId   The rightmost node ID to be reparsed. If it
     *                      is -1 parsing ends with the end of the file.
     * @return              The ID of the first inserted node.
     */
    public int reparseArea(int leftNodeId, int rightNodeId)
    {
        org.w3c.dom.Node sibling = null;
        org.w3c.dom.Node editor = doc.getElementById("editor");

        org.w3c.dom.Node leftNode = null;
        if(leftNodeId >= 0)
            leftNode = doc.getElementById("hm_node_" + leftNodeId);
        else
            leftNode = editor.getFirstChild();

        /* If the editor field is completely empty editor.getFirstChild() == null. Therefore we need to add a special case for it before using leftNode. */
        if(leftNode == null)
            return reparseString("", editor, null, -1, -1, true);

        boolean isFirst = leftNode.getPreviousSibling() == null;

        if(rightNodeId >= 0)
            sibling = doc.getElementById("hm_node_" + rightNodeId).getNextSibling();

        int cursorStartOffset = -1;
        int cursorEndOffset = -1;

        StringBuilder content = new StringBuilder();
        while(leftNode != null && (sibling == null || !leftNode.isEqualNode(sibling)))
        {
            /* Get the text and cursor offsets. */
            Stack<org.w3c.dom.Node> nodes = new Stack<>();
            nodes.push(leftNode);

            while(!nodes.isEmpty())
            {
                org.w3c.dom.Node node = nodes.pop();

                if(node instanceof Text)
                {
                    Text text = (Text) node;
                    content.append(node.getTextContent());
                    continue;
                }

                org.w3c.dom.Node last = node.getLastChild();
                while(last != null)
                {
                    nodes.push(last);
                    last = last.getPreviousSibling();
                }

                if(node instanceof Element)
                {
                    Element el = (Element) node;
                    String id = el.getAttribute("id");

                    if(id != null)
                    {
                        String lc = id.toLowerCase();

                        if(lc.equals("cursor_start"))
                            cursorStartOffset = content.length();

                        if(lc.equals("cursor_end"))
                            cursorEndOffset = content.length();

                        if(id.startsWith("hm_node_"))
                        {
                            String idSuff = id.substring("hm_node_".length());
                            Integer idInt = Integer.valueOf(Integer.parseInt(idSuff));

                            this.includes.remove(idInt);
                        }
                    }
                }
            }

            org.w3c.dom.Node old = leftNode;
            leftNode = leftNode.getNextSibling();
            editor.removeChild(old);
        }

        String text = content.toString();
        return reparseString(text, editor, sibling, cursorStartOffset, cursorEndOffset, isFirst);
    }

    /**
     * Reparse a string as TPTP file and insert the resulting nodes into
     * the document as children of parent before sibling.
     *
     * @param text      The text to parse again.
     * @param parent    The parent node the highlighted results is
     *                  inserted as child of.
     * @param sibling   The child node before which the results are
     *                  inserted. If this is null the results are inserted
     *                  at the end.
     * @param isFirst   Whether this if the first entry of the file. If
     *                  true a newline marker is inserted at the beginning
     *                  of the first line.
     * @return          The ID of the first inserted node.
     */
    public int reparseString(String text, org.w3c.dom.Node parent, org.w3c.dom.Node sibling, int cursorStartOffset, int cursorEndOffset, boolean isFirst)
    {
        int ret = -1;

        /* NOTE: We hardcode knowledge of the grammar here. This is ugly
        and may fail at any point. I'm sorry. :/ This is also incorrect
        because it fixes errors occuring during parsing before this. But
        it should be fine for an editor. */
        Pattern pattern = Pattern.compile("(\\A|\\s|\\.)(thf|tff|fof|cnf|include)\\(");
        Matcher matcher = pattern.matcher(text);

        boolean last = false;
        boolean first = true;
        while(!last)
        {
            int off_start;
            if(first)
            {
                off_start = 0;
                first = false;
            }
            else
            {
                off_start = matcher.start() + matcher.group(1).length();
            }

            int off_end;
            if(matcher.find())
            {
                off_end = matcher.start() + matcher.group(1).length();
            }
            else
            {
                last = true;
                off_end = text.length();
            }

            // System.out.println("off_start = " + off_start + ", off_end = " + off_end);
            String part = text.substring(off_start, off_end);
            // System.out.println("part = '" + part + "'");

            boolean hasError = false;

            StringReader textReader = new StringReader(text.substring(off_start, off_end));
            CharStream stream = null;
            try
            {
                stream = CharStreams.fromReader(textReader, "THF Window");
            }
            catch(IOException e)
            {
                log.error(e.getMessage());
                hasError = true;
            }

            ParseContext parseContext = null;
            try
            {
                if(!hasError)
                    parseContext = AstGen.parse(stream, "tptp_input_or_empty");
            }
            catch(ParseException e)
            {
                log.error(e.getMessage());
                hasError = true;
            }

            if(!hasError && parseContext.hasParseError())
            {
                log.error("unable to parse: " + parseContext.getParseError());
                hasError = true;
            }

            Node node = null;
            if(!hasError)
                node = parseContext.getRoot().getFirstChild();

            if(hasError)
            {
                node = new Node("not_parsed");

                Element newNode = doc.createElement("subsection");
                newNode.setAttribute("id", "hm_node_" + parserNodeIdCur);
                newNode.setAttribute("class", "not_parsed");
                if(ret == -1) ret = parserNodeIdCur;
                parserNodeIdCur++;

                insertNewTextNode(part, newNode, cursorStartOffset, cursorEndOffset, isFirst);
                cursorStartOffset -= part.length();
                cursorEndOffset -= part.length();
                if(isFirst) isFirst = false;

                parent.insertBefore(newNode, sibling);

                continue;
            }

            if(node.stopIndex < node.startIndex)
                node.stopIndex = node.startIndex = 0;

            Stack<Node> nodes = new Stack<>();
            nodes.push(node);
            while(!nodes.isEmpty())
            {
                Node n = nodes.pop();

                if(n.getRule().equals("include"))
                    includes.put(Integer.valueOf(parserNodeIdCur), n);

                nodes.addAll(n.getChildren());
            }

            /* Preprocessing for highlighting: extract sections which have to be highlighted. */
            LinkedList<SpanElement> spanElements = new LinkedList<SpanElement>();
            addSpanElements(node, spanElements);
            /* Add ranges of comments */
            for (Token token : parseContext.getHiddenTokens()) {
                spanElements.add(new SpanElement(token.getStartIndex(), token.getStopIndex(), "comment"));
            }
            Collections.sort((List<SpanElement>) spanElements);

            Element newNode = doc.createElement("subsection");
            newNode.setAttribute("id", "hm_node_" + parserNodeIdCur);
            newNode.setAttribute("class", "hm_node");

            if(ret == -1) ret = parserNodeIdCur;

            int lastParsedToken = 0;
            int nextEnd = -1;
            int startIndex = -1;
            SpanElement spanElement = null;
            if (spanElements.size() > 0) {
                spanElement = spanElements.pop();
                nextEnd = spanElement.getEndIndex();
                startIndex = spanElement.getStartIndex();
            }

            StringBuilder builder = new StringBuilder();
            
            //System.out.println(part+" "+part.length());

            while (lastParsedToken < part.length()) {

                if (startIndex == -1) {
                    builder.append(part.substring(lastParsedToken));
                    insertNewTextNode(builder.toString(), newNode, cursorStartOffset, cursorEndOffset, isFirst);
                    cursorStartOffset -= builder.length();
                    cursorEndOffset -= builder.length();
                    if(isFirst) isFirst = false;
                    builder.delete(0, builder.length());
                    break;
                }

                if (startIndex > lastParsedToken ) {
                    builder.append(part.substring(lastParsedToken, startIndex));
                    insertNewTextNode(builder.toString(), newNode, cursorStartOffset, cursorEndOffset, isFirst);
                    cursorStartOffset -= builder.length();
                    cursorEndOffset -= builder.length();
                    lastParsedToken += builder.length();
                    if(isFirst) isFirst = false;
                    builder.delete(0, builder.length());
                }

                if (startIndex == lastParsedToken) {

                    builder.append(part.substring(startIndex, nextEnd+1));
                    lastParsedToken += builder.length();

                    Element newSpan = doc.createElement("subsection");
                    newSpan.setAttribute("class", spanElement.getTag());
                    insertNewTextNode(builder.toString(), newSpan, cursorStartOffset, cursorEndOffset, isFirst);
                    cursorStartOffset -= builder.length();
                    cursorEndOffset -= builder.length();
                    if(isFirst) isFirst = false;
                    newNode.appendChild(newSpan);

                    builder.delete(0, builder.length());

                    if (spanElements.size() > 0) {
                        while (spanElements.size() > 0 && (spanElements.peek().getStartIndex() <= startIndex || spanElements.peek().getStartIndex() < lastParsedToken)) {
                            spanElements.pop();
                        }
                        if (spanElements.size() == 0) {
                            startIndex = -1;
                        } else {
                            spanElement = spanElements.pop();
                            nextEnd = spanElement.getEndIndex();
                            startIndex = spanElement.getStartIndex();
                        }
                    } else {
                        startIndex = -1;
                    }
                }
            }

            insertNewTextNode(builder.toString(), newNode, cursorStartOffset, cursorEndOffset, isFirst);
            cursorStartOffset -= builder.length();
            cursorEndOffset -= builder.length();
            if(isFirst) isFirst = false;
            builder.delete(0, builder.length());
            parent.insertBefore(newNode, sibling);

            parserNodeIdCur++;
        }

        return ret;
    }

    private void addSpanElements(Node node, LinkedList<SpanElement> spanElements) {
        if (node == null)
            return;

        if (css.contains(node.getRule())) {
            String tag = node.getRule();
            Node childOfTopBranching = node.getChildBeforeNextTopBranchingNode();
            if (childOfTopBranching == null || !childOfTopBranching.getRule().equals("thf_unitary_type")) {

            }
            if (tag.equals("functor") && (childOfTopBranching == null || !childOfTopBranching.getRule().equals("thf_unitary_type"))) {
                tag = "constant";
            }
            if (tag.equals("defined_functor") && (childOfTopBranching == null || !childOfTopBranching.getRule().equals("thf_unitary_type")) ) {
                tag = "defined_constant";
            }
            if (tag.equals("system_functor") && (childOfTopBranching == null || !childOfTopBranching.getRule().equals("thf_unitary_type"))) {
                tag = "system_constant";
            }
            if (tag.equals("functor") && childOfTopBranching != null && childOfTopBranching.getRule().equals("thf_unitary_type")) {
                tag = "type";
            }
            if (tag.equals("defined_functor") && childOfTopBranching != null && childOfTopBranching.getRule().equals("thf_unitary_type")) {
                tag = "defined_type";
            }
            if (tag.equals("system_functor") && childOfTopBranching != null && childOfTopBranching.getRule().equals("thf_unitary_type")) {
                tag = "system_type";
            }
            spanElements.add(new SpanElement(node.startIndex, node.stopIndex, tag));
        }

        for (Node n : node.getChildren()) {
            addSpanElements(n, spanElements);
        }
    }

    public void openStream(InputStream stream, Path path) {
        delayedActions.add(
            () ->
            {
                try
                {
                    this.path = path;

                    String content = IOUtils.toString(stream, "UTF-8");

                    org.w3c.dom.Node editor = doc.getElementById("editor");

                    while(editor.hasChildNodes())
                    {
                        editor.removeChild(editor.getFirstChild());
                    }

                    Text textNode = doc.createTextNode(content);
                    editor.appendChild(textNode);

                    reparse();
                    engine.executeScript("update_line_numbers()");

                    if(this.path == null)
                        view.setText("unnamed");
                    else
                        view.setText(this.path.getFileName().toString());
                }
                catch(IOException e)
                {
                    log.error(e.getMessage());
                }

                return null;
            }
        );

        maybeCallDelayedActions();
    }

    /**
     * Loads the content of a file into the THF area
     * Every opening method MUST use this
     * Adds to recently opened files
     * @param file
     */
    public void openFile(File file) {
        try {
            InputStream stream = new FileInputStream(file);
            openStream(stream, file.toPath());
            log.info("Opened " + file.getAbsolutePath());
        } catch(FileNotFoundException e) {
            log.error(e.getMessage());
        }
    }

    /**
     * Creates a new Document including WebView which can be used for opening multiple documents simultaneously
     * @param path file path on disk, null indicates there is no underlying file.
     *             This might be the case if the file was deleted/moved or a new document was created
     *             The field path has to be updated manually and TODO is assumed to be valid at all times
     */

    /**
     * Returns the plain problem
     * @return
     */
    public String getText(){
        return doc.getElementById("editor").getTextContent();
    }

    /**
     * Displays the proving history of this document in a separate window
     */
    public void showProvingHistory(){
        // TODO
        // implement new window or something with slider, etc.
    }

    public void prove(String prover, Prover.ProverType proverType, int timeLimit){
        provingHistory.prove(this, prover, proverType, timeLimit);
    }

    /**
     * Cleanup document specific stuff on File > close
     */
    public void close(){
        provingHistory.remove(this);
    }

    /**
     * Returns the TPTP sub-dialect of the problem
     * @return
     */
    public TPTPDefinitions.TPTPSubDialect classifyByTPTPSubDialect(){
        // TODO
        // maybe this should happen in listener, trigger some flags etc.
        return TPTPDefinitions.TPTPSubDialect.TH1;
    }

    /**
     * Returns all compatible TPTP sub-dialects of the problem
     * @return
     */
    public List<TPTPDefinitions.TPTPSubDialect> getCompatibleTPTPSubDialects(){
        return TPTPDefinitions.getCompatibleSubDialects(this.classifyByTPTPSubDialect());
    }

    /**
     * Sets the current file path of the document
     * @param path
     */
    public void setPath(Path path){
        this.path = path;
    }

    /**
     * Returns the current file path of the document
     * The path is assumed to be valid at all times
     * @return
     */
    public Path getPath(){
        return path;
    }

    // ------- DEBUG FUNCTIONS -------
    private void debugPrintHTMLImmediately() {
        String content = (String) engine.executeScript("document.getElementsByTagName('html')[0].innerHTML");
        System.out.println(content);
    }

    public void debugPrintHTML() {
        delayedActions.add(() -> { debugPrintHTMLImmediately(); return null; });
        maybeCallDelayedActions();
    }

    public void debugPrintIncludes() {
        for(Map.Entry<Integer, Node> entry : includes.entrySet()) {
            System.out.println(entry.getValue().toString());
        }
    }
};
