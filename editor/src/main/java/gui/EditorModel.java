package gui;

import java.io.File;
import java.io.StringReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.lang.Throwable;

import javafx.collections.ObservableList;
import javafx.collections.ListChangeListener;
import javafx.collections.FXCollections;

import javafx.scene.web.WebEngine;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

import org.apache.commons.io.IOUtils;

import parser.AstGen;
import parser.ParseContext;

import exceptions.ParseException;
import util.SpanElement;
import util.tree.Node;

public class EditorModel
{
    private static Logging log = Logging.getInstance();
    public WebEngine engine;
    public Document doc;
    public WebKitStyle style;

    public LinkedList<Node> tptpInputNodes;

    private int parserNodeIdCur = 0;

    private ObservableList<String> recentlyOpenedFiles;

    private LinkedList<String> css;
    public WebEngine outputEngine;

    public EditorModel()
    {
        tptpInputNodes = new LinkedList<Node>();
        recentlyOpenedFiles = FXCollections.observableArrayList(); // first element = oldest file, last element = latest file
        recentlyOpenedFiles.addListener(new ListChangeListener() {
            @Override
            public void onChanged(ListChangeListener.Change change) {
                // TODO: add just new recently opened files as menuitems and
                // remove removed recently opened files
                // menubarFileReopenFile.getItems().clear();
                // for (Iterator<String> i = recentlyOpenedFiles.iterator(); i.hasNext();) {
                //     MenuItem item = new MenuItem(i.next());
                //     // item.setActionOn(...);
                //     menubarFileReopenFile.getItems().add(item);
                // }
            }
        });

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
        }
        scanner.close();
    }

    public void addErrorMessage(String string)
    {
        /* TODO: Add to the output on the bottom of the screen. */
        System.err.println("Error: " + string);
    }

    public void addErrorMessage(Throwable e)
    {
        addErrorMessage(e.getLocalizedMessage());
    }

    public void openStream(InputStream stream) {
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
        }
        catch(IOException e)
        {
            addErrorMessage(e);
        }
    }

    /**
     * Loads the content of a file into the THF area
     * Every opening method MUST use this
     * Adds to recently opened files
     * @param file
     */
    public void openFile(File file)
    {
        try
        {
            InputStream stream = new FileInputStream(file);
            openStream(stream);
            updateRecentlyOpenedFiles(file);
            log.info("Opened " + file.getAbsolutePath());
        }
        catch(FileNotFoundException e)
        {
            addErrorMessage(e);
        }
    }


    /**
     * Updates concerning recently opened files
     * Is called after opening a file
     * @param file
     */
    private void updateRecentlyOpenedFiles(File file){
        recentlyOpenedFiles.remove(file.getAbsolutePath());
        recentlyOpenedFiles.add(file.getAbsolutePath());
        if (recentlyOpenedFiles.size() > Config.maxRecentlyOpenedFiles) recentlyOpenedFiles.remove(0);
        Config.setRecentlyOpenedFiles(recentlyOpenedFiles);
        // TODO reflect in Menu File > recently opened Files
    }

    public ObservableList getRecentlyOpenedFiles() {
        return recentlyOpenedFiles;
    }

    public void printTPTPTrees()
    {
        System.out.println("------------------------");
        for(Node node : tptpInputNodes)
        {
            Node dummy = new Node("dummy");
            dummy.addChild(node);

            System.out.println("node = " + dummy.toStringWithPositionOutput());
        }
        System.out.println("------------------------");
    }

    public void reparse()
    {
        tptpInputNodes = new LinkedList<Node>();
        reparseArea(-1, -1);

        /*
        if (tptpInputNodes.size() > 0) {
            addSyntaxHighlighting(0, tptpInputNodes.size() - 1);
        }
        */
    }

    private void insertNewTextNode(String text, org.w3c.dom.Node parent, org.w3c.dom.Node sibling, boolean isFirst)
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
                parent.insertBefore(nl, sibling);
            }

            String s = text.substring(start, end);
            parent.insertBefore(doc.createTextNode(s), sibling);
            if(s.endsWith("\n"))
            {
                Element nl = doc.createElement("subsection");
                nl.setAttribute("class", "new_line");
                parent.insertBefore(nl, sibling);
            }
        }
        while(end < text.length());
    }

    private void insertNewTextNode(String text, org.w3c.dom.Node parent, org.w3c.dom.Node sibling)
    {
        insertNewTextNode(text, parent, sibling, false);
    }

    private void insertNewTextNode(String text, org.w3c.dom.Node parent)
    {
        insertNewTextNode(text, parent, null);
    }

    private void insertNewTextNode(String text, org.w3c.dom.Node parent, boolean isFirst)
    {
        insertNewTextNode(text, parent, null, isFirst);
    }

    public int reparseArea(int leftNodeId, int rightNodeId)
    {
        int ret = -1;

        System.out.println("reparseArea(" + leftNodeId + "," + rightNodeId +")");

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

        System.out.println("sibling = " + sibling);
        if(sibling instanceof Element)
            System.out.println("sibling[id] = " + ((Element) sibling).getAttribute("id"));

        StringBuilder content = new StringBuilder();
        while(leftNode != null && (sibling == null || !leftNode.isEqualNode(sibling)))
        {
            content.append(leftNode.getTextContent());
            org.w3c.dom.Node old = leftNode;
            leftNode = leftNode.getNextSibling();
            editor.removeChild(old);
        }

        String text = content.toString();

        /* NOTE: We hardcode knowledge of the grammar here. This is ugly and may fail at any point. I'm sorry. :/ */
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
                addErrorMessage(e);
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
                addErrorMessage(e);
                hasError = true;
            }

            if(!hasError && parseContext.hasParseError())
            {
                addErrorMessage("unable to parse: " + parseContext.getParseError());
                hasError = true;
            }

            Node node = null;
            if(!hasError)
                node = parseContext.getRoot().getFirstChild();

            if(hasError || node.stopIndex < node.startIndex)
            {
                node = new Node("not_parsed");

                Element newNode = doc.createElement("section");
                newNode.setAttribute("id", "hm_node_" + parserNodeIdCur);
                newNode.setAttribute("class", "not_parsed");
                if(ret == -1) ret = parserNodeIdCur;
                parserNodeIdCur++;

                insertNewTextNode(part, newNode, isFirst);
                if(isFirst) isFirst = false;

                editor.insertBefore(newNode, sibling);

                continue;
            }

            /* Preprocessing for highlighting: extract sections which have to be highlighted. */
            LinkedList<SpanElement> spanElements = new LinkedList<SpanElement>();
            addSpanElements(node, spanElements);
            Collections.sort((List<SpanElement>) spanElements);

            Element newNode = doc.createElement("section");
            newNode.setAttribute("id", "hm_node_" + parserNodeIdCur);
            newNode.setAttribute("class", "hm_node");

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
                    insertNewTextNode(builder.toString(), newNode, isFirst);
                    if(isFirst) isFirst = false;
                    builder.delete(0, builder.length());
                    break;
                }

                if (startIndex > lastParsedToken ) {
                    builder.append(part.substring(lastParsedToken, startIndex));
                    lastParsedToken += builder.length();

                    Text textNode = doc.createTextNode(builder.toString());
                    builder.delete(0, builder.length());
                    newNode.appendChild(textNode);
                }

                if (startIndex == lastParsedToken) {

                    builder.append(part.substring(startIndex, nextEnd+1));
                    lastParsedToken += builder.length();

                    Element newSpan = doc.createElement("subsection");
                    newSpan.setAttribute("class", spanElement.getTag());
                    insertNewTextNode(builder.toString(), newSpan, isFirst);
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

            insertNewTextNode(builder.toString(), newNode, isFirst);
            if(isFirst) isFirst = false;
            builder.delete(0, builder.length());
            editor.insertBefore(newNode, sibling);
        }

        return ret;
    }

    private void addSpanElements(Node node, LinkedList<SpanElement> spanElements) {
        if (node == null)
            return;

        if (css.contains(node.getRule())) {
            spanElements.add(new SpanElement(node.startIndex, node.stopIndex, node.getRule()));
        }

        for (Node n : node.getChildren()) {
            addSpanElements(n, spanElements);
        }

    }

    public void onViewIncreaseFontSize() {
        style.increaseFontSize();
    }

    public void onViewDecreaseFontSize() {
        style.decreaseFontSize();
    }

    public void onViewEnterPresentationMode() {
        style.setFontSize(2.0);
        // TODO close side drawer, ...
    }

}
