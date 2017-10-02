package gui;

import java.io.File;
import java.io.InputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.nio.file.Path;

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

import javafx.scene.Scene;
import javafx.scene.web.WebEngine;

import javafx.stage.Stage;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;

import org.apache.commons.io.IOUtils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import netscape.javascript.JSObject;

import util.tree.Node;
import util.SpanElement;

import parser.ParseContext;
import parser.AstGen;

import exceptions.ParseException;

public class EditorDocumentModel
{
    private static Logging log = Logging.getInstance();

    private WebEngine engine;
    private Document doc;
    private WebKitStyle style;

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
            /* TODO */
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
     * @param text      The text content of the new text node.
     * @param parent    The parent node of the new text node.
     * @param sibling   The next sibling of the new text node. If this
     *                  argument is null the node is inserted as last
     *                  child of parent.
     * @param startOffset The offset from the start of the node. Used
     *                    for data-start and data-end annotations,
     *                    which are optimizations for position queries
     *                    in javascript.
     * @param isFirst   Whether this if the first entry of the file. If
     *                  true a newline marker is inserted at the beginning
     *                  of the first line.
     */
    private void insertNewTextNode(String text, org.w3c.dom.Node parent, org.w3c.dom.Node sibling, int startOffset, boolean isFirst)
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

            if(isFirst)
            {
                isFirst = false;

                Element nl = doc.createElement("subsection");
                nl.setAttribute("class", "new_line");
                nl.setAttribute("data-start", "" + startOffset + start);
                nl.setAttribute("data-end", "" + startOffset + start);
                parent.insertBefore(nl, sibling);
            }

            String s = text.substring(start, end);
            parent.insertBefore(doc.createTextNode(s), sibling);
            if(s.endsWith("\n"))
            {
                Element nl = doc.createElement("subsection");
                nl.setAttribute("class", "new_line");
                nl.setAttribute("data-start", "" + (startOffset + end));
                nl.setAttribute("data-end", "" + (startOffset + end));
                parent.insertBefore(nl, sibling);
            }
        }
        while(end < text.length());
    }

    /**
     * Inserts a text node as child of parent and before sibling,
     * inserting the appropriate newline markers.
     *
     * @param text      The text content of the new text node.
     * @param parent    The parent node of the new text node.
     * @param sibling   The next sibling of the new text node. If this
     *                  argument is null the node is inserted as last
     *                  child of parent.
     * @param startOffset The offset from the start of the node. Used
     *                    for data-start and data-end annotations,
     *                    which are optimizations for position queries
     *                    in javascript.
     */
    private void insertNewTextNode(String text, org.w3c.dom.Node parent, org.w3c.dom.Node sibling, int startOffset)
    {
        insertNewTextNode(text, parent, sibling, startOffset, false);
    }

    /**
     * Inserts a text node as child of parent and before sibling,
     * inserting the appropriate newline markers.
     *
     * @param text      The text content of the new text node.
     * @param parent    The parent node of the new text node.
     * @param startOffset The offset from the start of the node. Used
     *                    for data-start and data-end annotations,
     *                    which are optimizations for position queries
     *                    in javascript.
     */
    private void insertNewTextNode(String text, org.w3c.dom.Node parent, int startOffset)
    {
        insertNewTextNode(text, parent, null, startOffset);
    }

    /**
     * Inserts a text node as child of parent and before sibling,
     * inserting the appropriate newline markers.
     *
     * @param text      The text content of the new text node.
     * @param parent    The parent node of the new text node.
     * @param startOffset The offset from the start of the node. Used
     *                    for data-start and data-end annotations,
     *                    which are optimizations for position queries
     *                    in javascript.
     * @param isFirst   Whether this if the first entry of the file. If
     *                  true a newline marker is inserted at the beginning
     *                  of the first line.
     */
    private void insertNewTextNode(String text, org.w3c.dom.Node parent, int startOffset, boolean isFirst)
    {
        insertNewTextNode(text, parent, null, startOffset, isFirst);
    }

    /**
     * Reparse an area as indicated by the surrounding node ids.
     *
     * @param leftNodeId    The leftmost node ID to be reparsed. If it is
     *                      -1 parsing starts from the start.
     * @param richtNodeId   The rightmost node ID to be reparsed. If it
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

        boolean isFirst = leftNode.getPreviousSibling() == null;

        if(rightNodeId >= 0)
            sibling = doc.getElementById("hm_node_" + rightNodeId).getNextSibling();

        StringBuilder content = new StringBuilder();
        while(leftNode != null && (sibling == null || !leftNode.isEqualNode(sibling)))
        {
            content.append(leftNode.getTextContent());
            org.w3c.dom.Node old = leftNode;
            leftNode = leftNode.getNextSibling();
            editor.removeChild(old);
        }

        String text = content.toString();
        return reparseString(text, editor, sibling, isFirst);
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
    public int reparseString(String text, org.w3c.dom.Node parent, org.w3c.dom.Node sibling, boolean isFirst)
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

            if(hasError || node.stopIndex < node.startIndex)
            {
                node = new Node("not_parsed");

                Element newNode = doc.createElement("subsection");
                newNode.setAttribute("id", "hm_node_" + parserNodeIdCur);
                newNode.setAttribute("class", "not_parsed");
                newNode.setAttribute("data-start", "0");
                newNode.setAttribute("data-end", "" + part.length());
                if(ret == -1) ret = parserNodeIdCur;
                parserNodeIdCur++;

                insertNewTextNode(part, newNode, 0, isFirst);
                if(isFirst) isFirst = false;

                parent.insertBefore(newNode, sibling);

                continue;
            }

            /* Preprocessing for highlighting: extract sections which have to be highlighted. */
            LinkedList<SpanElement> spanElements = new LinkedList<SpanElement>();
            addSpanElements(node, spanElements);
            Collections.sort((List<SpanElement>) spanElements);

            Element newNode = doc.createElement("subsection");
            newNode.setAttribute("id", "hm_node_" + parserNodeIdCur);
            newNode.setAttribute("class", "hm_node");
            newNode.setAttribute("data-start", "0");
            newNode.setAttribute("data-end", "" + part.length());

            if(ret == -1) ret = parserNodeIdCur;
            parserNodeIdCur++;

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

            while (lastParsedToken < part.length()) {

                if (startIndex == -1) {
                    builder.append(part.substring(lastParsedToken));
                    insertNewTextNode(builder.toString(), newNode, lastParsedToken, isFirst);
                    if(isFirst) isFirst = false;
                    builder.delete(0, builder.length());
                    break;
                }

                if (startIndex > lastParsedToken ) {
                    builder.append(part.substring(lastParsedToken, startIndex));
                    lastParsedToken += builder.length();

                    insertNewTextNode(builder.toString(), newNode, lastParsedToken, isFirst);
                    if(isFirst) isFirst = false;
                    builder.delete(0, builder.length());
                }

                if (startIndex == lastParsedToken) {

                    builder.append(part.substring(startIndex, nextEnd+1));
                    lastParsedToken += builder.length();

                    Element newSpan = doc.createElement("subsection");
                    newSpan.setAttribute("class", spanElement.getTag());
                    newSpan.setAttribute("data-start", "" + startIndex);
                    newSpan.setAttribute("data-end", "" + (startIndex + builder.length()));
                    insertNewTextNode(builder.toString(), newSpan, startIndex, isFirst);
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

            insertNewTextNode(builder.toString(), newNode, lastParsedToken, isFirst);
            if(isFirst) isFirst = false;
            builder.delete(0, builder.length());
            parent.insertBefore(newNode, sibling);
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

    public void openStream(InputStream stream, Path file) {
        delayedActions.add(
            () ->
            {
                try
                {
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

                    if(file == null)
                        view.setText("unnamed");
                    else
                        view.setText(file.getFileName().toString());
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

    public void openStream(InputStream stream) {
        openStream(stream, null);
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

    public void onViewIncreaseFontSize() {
        style.increaseFontSize();
        engine.executeScript("update_line_numbers()");
    }

    public void onViewDecreaseFontSize() {
        style.decreaseFontSize();
        engine.executeScript("update_line_numbers()");
    }

    public void onViewEnterPresentationMode() {
        style.setFontSizeEditor(Config.fontSizePresentationMode);
        engine.executeScript("update_line_numbers()");
        // TODO close side drawer, ...
    }

    public void onViewDefaultFontSize() {
        style.setFontSizeEditor(Config.fontSizeEditorDefault);
        engine.executeScript("update_line_numbers()");
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
};
