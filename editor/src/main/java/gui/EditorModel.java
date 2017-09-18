package gui;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.charset.StandardCharsets;

import java.lang.Throwable;

import parser.AstGen;
import parser.ParseContext;

import org.antlr.v4.runtime.CharStreams;

import exceptions.ParseException;

import org.fxmisc.richtext.CodeArea;

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
}
