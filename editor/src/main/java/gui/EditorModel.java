package gui;

import java.io.File;
import java.io.StringReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.charset.StandardCharsets;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.lang.Throwable;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;

import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.model.StyleSpans;
import org.fxmisc.richtext.model.StyleSpansBuilder;

import org.apache.commons.io.IOUtils;

import parser.AstGen;
import parser.ParseContext;

import exceptions.ParseException;

import util.tree.Node;

public class EditorModel
{
    public CodeArea thfArea;
    public CodeArea wysArea;

    public LinkedList<Node> tptpInputNodes;
    private HashMap<String, String> rule2CssColor;

    private ArrayList<String> recentlyOpenedFiles;

    public EditorModel()
    {
        tptpInputNodes = new LinkedList<Node>();
        
        rule2CssColor = new HashMap<String, String>();
        rule2CssColor.put("functor", "c0");
        rule2CssColor.put("defined_functor", "c1");

        recentlyOpenedFiles = new ArrayList<>(); // first element = oldest file, last element = latest file
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

    /**
     * Loads text into THF area
     * Does not add to recently opened files
     * @param stream
     */
    public String openStream(InputStream stream)
    {
        try
        {
            byte[] content = IOUtils.toByteArray(stream);
            return new String(content, StandardCharsets.UTF_8);
        }
        catch(IOException e)
        {
            addErrorMessage(e);
        }
        
        return null;
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
            thfArea.replaceText(content);
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

    public void updateStyle()
    {
        StringBuilder style = new StringBuilder()
            .append("-fx-font-family: " + Config.getFont() + ";\n")
            .append("-fx-font-size: " + Config.getFontSize() + "pt;\n");

        thfArea.setStyle(style.toString());
        wysArea.setStyle(style.toString());
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
        reparseArea(0, thfArea.getLength()-1, tptpInputNodes.listIterator());
        if (tptpInputNodes.size() > 0) {
            addSyntaxHighlighting(0, tptpInputNodes.size() - 1);
        }
    }

    private void addSyntaxHighlighting(int start, int end) {
        ListIterator<Node> itr = tptpInputNodes.listIterator(start);
        
        while (itr.hasNext() && itr.nextIndex() <= end) {
            Node next = itr.next();
            addHighlightingToTptpInput(next);
        }
    }

    private void addHighlightingToTptpInput(Node node) {
        int baseStartIndex = node.startIndex;
        
        for (Node child : node.getChildren()) {
            addHighlighting(child, baseStartIndex);
        }
    }

    private void addHighlighting(Node node, int baseStartIndex) {
        String style = rule2CssColor.get(node.getRule());
        
        if (style != null) {
            thfArea.setStyle(baseStartIndex + node.startIndex, baseStartIndex + node.stopIndex + 1, Collections.singleton(style));
        }
        
        for (Node child : node.getChildren()) {
            addHighlighting(child, baseStartIndex);
        }
    }

    private void reparseArea(int start, int end, ListIterator<Node> position)
    {
        //System.out.println("reparseArea: (" + start + "," + end + ")");

        String text = thfArea.getText(start, end);

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
                off_start = matcher.start();
            }

            /* This should only be true if \A matched. */
            if(off_start != 0)
                off_start++;

            int off_end;
            if(matcher.find())
            {
                off_end = matcher.start();
            }
            else
            {
                last = true;
                off_end = text.length();
            }

            // System.out.println("off_start = " + off_start + ", off_end = " + off_end);
            String part = text.substring(off_start, off_end);
            // System.out.println("part = '" + part + "'");

            StringReader textReader = new StringReader(text.substring(off_start, off_end));
            CharStream stream;
            try
            {
                stream = CharStreams.fromReader(textReader, "THF Window");
            }
            catch(IOException e)
            {
                addErrorMessage(e);
                continue;
            }

            ParseContext parseContext;
            try
            {
                parseContext = AstGen.parse(stream, "tptp_input_or_empty");
            }
            catch(ParseException e)
            {
                addErrorMessage(e);
                continue;
            }

            if(parseContext.hasParseError())
            {
                addErrorMessage("unable to parse: " + parseContext.getParseError());
                continue;
            }

            Node node = parseContext.getRoot().getFirstChild();

            node.startIndex += off_start + start;
            node.stopIndex += off_start + start;

            position.add(node);
            addHighlightingToTptpInput(node);
        }
    }

    public void updateTHFTree(int start, int insEnd, int delEnd)
    {
        int offset = insEnd - delEnd;

        int parseStart = -1;
        int parseEnd = -1;

        ListIterator<Node> nodeIt = tptpInputNodes.listIterator();

        /* First we skip all nodes that don't reach the changed area yet.
         * TODO: This could be optimized with trees. */
        Node prev = null;
        while(nodeIt.hasNext())
        {
            Node next = nodeIt.next();

            int nextIndex = next.stopIndex+1;
            // System.out.println("startIndex = " + next.startIndex + ", nextIndex = " + nextIndex + ", start = " + start);

            if(nextIndex >= start)
            {
                // System.out.println("delete[0]");
                nodeIt.remove();
                break;
            }

            prev = next;
        }

        if(prev != null)
            parseStart = prev.stopIndex+1;

        /* Now we delete all nodes that reach the changed area. */
        while(nodeIt.hasNext())
        {
            Node next = nodeIt.next();

            if(next.startIndex <= delEnd)
            {
                // System.out.println("delete[1]");
                nodeIt.remove();
            }
            else
            {
                break;
            }
        }

        if(nodeIt.hasNext())
        {
            Node next = nodeIt.next();
            if(parseEnd == -1)
                parseEnd = next.startIndex;

            nodeIt.previous();
        }

        /* If we didn't find any preceeding or following completely parsed
         * chunks we have to bite the bullet and need to restart the parser
         * with the maxial constraints. */
        if(parseStart == -1)
            parseStart = 0;
        if(parseEnd == -1)
            parseEnd = thfArea.getLength();

        /* Reparse the changed area we identified. */
        reparseArea(parseStart, parseEnd, nodeIt);

        /* Now we update all nodes following the changed area. */
        while(nodeIt.hasNext())
        {
            Node next = nodeIt.next();

            next.startIndex += offset;
            next.stopIndex += offset;

            if(parseEnd == -1)
                parseEnd = next.startIndex;
        }
    }

    public void updateRainbows()
    {
        String text = thfArea.getText();

        if(text.length() < 4)
            return;

        StyleSpansBuilder<Collection<String>> spansBuilder = new StyleSpansBuilder<Collection<String>>();
        for(int i = 0; i < text.length()/4; ++i)
        {
            if(text.charAt(i) == '\n')
                spansBuilder.add(Collections.emptyList(), 1);
            else
                spansBuilder.add(Collections.singleton("c" + (i%8+1)), 4);
        }

        StyleSpans<Collection<String>> spans = spansBuilder.create();
        thfArea.setStyleSpans(0, spans);
    }
}
