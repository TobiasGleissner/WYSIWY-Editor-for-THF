package gui;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.charset.StandardCharsets;

import java.util.Collection;
import java.util.Collections;

import java.lang.Throwable;

import parser.AstGen;
import parser.ParseContext;

import org.antlr.v4.runtime.CharStreams;

import exceptions.ParseException;

import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.model.StyleSpans;
import org.fxmisc.richtext.model.StyleSpansBuilder;

public class EditorModel
{
    public CodeArea thfArea;
    public CodeArea wysArea;

    public void addErrorMessage(String string)
    {
        /* TODO */
        System.err.println("Error: " + string);
    }

    public void addErrorMessage(Throwable e)
    {
        addErrorMessage(e.getLocalizedMessage());
    }

    public ParseContext parse (CodeArea codeArea, String rule) {
    	ParseContext parseContext = null;
    	
    	try {
    		parseContext = AstGen.parse(CharStreams.fromString(codeArea.getText()), rule);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return parseContext;
    }

    public void openFile(File file)
    {
        try
        {
            Path path = file.toPath();
            byte[] content = Files.readAllBytes(path);
            thfArea.replaceText(new String(content, StandardCharsets.UTF_8));
        }
        catch(java.io.IOException t)
        {
            addErrorMessage(t);
        }
    }

    public void updateStyle()
    {
        StringBuilder style = new StringBuilder()
            .append("-fx-font-family: " + Config.getFont() + ";\n")
            .append("-fx-font-size: " + Config.getFontSize() + "pt;\n");

        thfArea.setStyle(style.toString());
        wysArea.setStyle(style.toString());
    }

    public void updateRainbows()
    {
        String text = thfArea.getText();

        if(text.length() == 0)
            return;

        StyleSpansBuilder<Collection<String>> spansBuilder = new StyleSpansBuilder<Collection<String>>();
        for(int i = 0; i < text.length(); ++i)
        {
            if(text.charAt(i) == '\n')
                spansBuilder.add(Collections.emptyList(), 1);
            else
                spansBuilder.add(Collections.singleton("c" + (i%8+1)), 1);
        }

        StyleSpans<Collection<String>> spans = spansBuilder.create();
        thfArea.setStyleSpans(0, spans);
    }
}
