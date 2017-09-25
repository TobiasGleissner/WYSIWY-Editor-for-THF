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
    public WebEngine engine;
    public Document doc;
    public WebKitStyle style;

    public LinkedList<Node> tptpInputNodes;

    private int parserNodeIdCur = 0;
    private HashMap<Integer, Node> parserNodes;

    private ArrayList<String> recentlyOpenedFiles;
    
    private LinkedList<String> css;

    public EditorModel()
    {
        tptpInputNodes = new LinkedList<Node>();
        recentlyOpenedFiles = new ArrayList<>(); // first element = oldest file, last element = latest file
        parserNodes = new HashMap();
        
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

    public String openStream(InputStream stream) throws IOException {
        byte[] content = IOUtils.toByteArray(stream);
        return new String(content, StandardCharsets.UTF_8);
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
            String content = openStream(stream);

            org.w3c.dom.Node body = doc.getElementsByTagName("body").item(0);

            while(body.hasChildNodes())
            {
                body.removeChild(body.getFirstChild());
            }

            boolean first = true;
            for(String line : content.split("\n"))
            {
                if(first)
                {
                    first = false;
                }
                else
                {
                    Element br = doc.createElement("br");
                    body.appendChild(br);
                }

                Text textNode = doc.createTextNode(line);
                body.appendChild(textNode);
            }

            // reparse();
            updateRecentlyOpenedFiles(file);
        }
        catch(java.io.IOException t)
        {
            addErrorMessage(t);
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
        reparseArea(-1, false, false);

        /*
        if (tptpInputNodes.size() > 0) {
            addSyntaxHighlighting(0, tptpInputNodes.size() - 1);
        }
        */
    }

    private void reparseArea(int oldNodeId, boolean removeLeft, boolean removeRight)
    {
        parserNodes.remove(new Integer(oldNodeId));

        //System.out.println("reparseArea: (" + start + "," + end + ")");

        /*
         * TODO:
         *  - Make a Reader class for this and feed it to antlr.
         *  - Make this start with a node of the caller's choosing.
         */

        /* We have a node to replace. */
        org.w3c.dom.Node sibling = null; /* null if we don't have a next sibling. */
        org.w3c.dom.Node newRoot;
        org.w3c.dom.Node parseRoot;

        if(oldNodeId >= 0)
        {
            parseRoot = doc.createElement("body");

            String initialId = "hm_node_" + oldNodeId;
            org.w3c.dom.Node oldNode = doc.getElementById(initialId);

            newRoot = oldNode.getParentNode();

            /* TODO: For incremental parsing we have to find the first node
             * that is actually a parsing result, as errored nodes need to be
             * reparsed again. */

            org.w3c.dom.Node leftSibling = oldNode.getPreviousSibling();
            if(removeLeft && leftSibling != null)
            {
                String idStr = ((Element) leftSibling).getAttribute("id").replaceFirst("^hm_node_", "");
                Integer id = new Integer(idStr);
                parserNodes.remove(id);

                parseRoot.appendChild(leftSibling);
                newRoot.removeChild(leftSibling);
            }

            parseRoot.appendChild(oldNode);

            org.w3c.dom.Node rightSibling = oldNode.getNextSibling();
            if(removeRight && rightSibling != null)
            {
                sibling = rightSibling.getNextSibling();

                String idStr = ((Element) rightSibling).getAttribute("id").replaceFirst("^hm_node_", "");
                Integer id = new Integer(idStr);
                parserNodes.remove(id);

                parseRoot.appendChild(rightSibling);
                newRoot.removeChild(rightSibling);
            }
            else
            {
                sibling = rightSibling;
            }

            newRoot.removeChild(oldNode);
        }
        else /* We have to restart from the root. */
        {
            parseRoot = doc.getElementsByTagName("body").item(0);

            org.w3c.dom.Node parent = parseRoot.getParentNode();
            parent.removeChild(parseRoot);

            Element newRoot_ = doc.createElement("body");
            newRoot_.setAttribute("contenteditable", "true");
            newRoot = newRoot_;

            parent.appendChild(newRoot);
        }

        StringBuilder content = new StringBuilder();
        Stack<org.w3c.dom.Node> nodes = new Stack();
        nodes.push(parseRoot);
        while(!nodes.empty())
        {
            org.w3c.dom.Node n = nodes.pop();

            System.out.println("start node");

            if(n instanceof Text)
            {
                System.out.println("is text");
                Text t = (Text)n;
                content.append(t.getTextContent());
            }

            NodeList list = n.getChildNodes();
            for(int i = list.getLength(); i > 0; --i)
            {
                System.out.println("add child");
                nodes.push(list.item(i-1));
            }

            if(n instanceof Element)
            {
                System.out.println("is element");

                Element el = (Element)n;
                System.out.println("tag_name = '" + el.getTagName() + "'");
                System.out.println("class = '" + el.getAttribute("class") + "'");

                if(el.getTagName().toLowerCase().equals("br"))
                    content.append("\n");
            }
        }

        String text = content.toString();
        // System.out.println("text = '" + text + "'");

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
                parserNodes.put(new Integer(parserNodeIdCur), node);

                Element newNode = doc.createElement("section");
                newNode.setAttribute("id", "hm_node_" + parserNodeIdCur);
                newNode.setAttribute("class", "not_parsed");
                parserNodeIdCur++;

                String[] lines = part.split("\n");
                for(int i = 0; i < lines.length; ++i)
                {
                    if(i != 0)
                    {
                        Element br = doc.createElement("br");
                        newNode.appendChild(br);
                    }

                    Text textNode = doc.createTextNode(lines[i]);
                    newNode.appendChild(textNode);
                }
                newRoot.insertBefore(newNode, sibling);

                continue;
            }

            parserNodes.put(new Integer(parserNodeIdCur), node);

            // Start preprocessing for highlighting
            LinkedList<SpanElement> spanElements = new LinkedList<SpanElement>();
            addSpanElements(node, spanElements);

            Element newNode = doc.createElement("section");
            newNode.setAttribute("id", "hm_node_" + parserNodeIdCur);
            newNode.setAttribute("class", "hm_node");

            parserNodeIdCur++;

            /* NOTE: Highlighting modifies this part! */
            String[] lines = part.split("\n");
            int lastParsedToken = 0;
            int nextEnd = -1;
            int startIndex = -1;
            SpanElement spanElement = null;
            if (spanElements.size() > 0) {
                spanElement = spanElements.pop();
                nextEnd = spanElement.getEndIndex();
                startIndex = spanElement.getStartIndex();
            }
            for(int i = 0; i < lines.length; ++i)
            {
                if(i != 0)
                {
                    Element br = doc.createElement("br");
                    newNode.appendChild(br);
                    lastParsedToken++;
                }

                StringBuilder builder = new StringBuilder();
                
                for (int j = 0; j < lines[i].length(); j++) {
                    if (lastParsedToken == startIndex && builder.length() > 0) {
                        newNode.appendChild(doc.createTextNode(builder.toString()));
                        builder.delete(0, builder.length());
                    }
                    
                    builder.append(lines[i].charAt(j));
                    lastParsedToken++;
                    
                    if (lastParsedToken == nextEnd + 1) {
                        Element newSpan = doc.createElement("subsection");
                        newSpan.setAttribute("class", spanElement.getTag());
                        newSpan.appendChild(doc.createTextNode(builder.toString()));
                        newNode.appendChild(newSpan);
                        
                        builder.delete(0, builder.length());
                        
                        if (spanElements.size() > 0) {
                            spanElement = spanElements.pop();
                            nextEnd = spanElement.getEndIndex();
                            startIndex = spanElement.getStartIndex();
                        }
                    }
                }
                
                Text textNode = doc.createTextNode(builder.toString());
                builder.delete(0, builder.length());
                newNode.appendChild(textNode);
            }
            newRoot.insertBefore(newNode, sibling);
        }
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
