package gui;

import java.io.File;

import java.lang.Throwable;

import javafx.fxml.FXML;

import org.fxmisc.richtext.CodeArea;

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
}
