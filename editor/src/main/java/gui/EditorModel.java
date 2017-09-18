package gui;

import java.io.File;

import java.lang.Throwable;

import javafx.fxml.FXML;
import parser.AstGen;
import parser.ParseContext;

import org.antlr.v4.runtime.CharStreams;



import org.fxmisc.richtext.CodeArea;

import exceptions.ParseException;

public class EditorModel {
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
}
