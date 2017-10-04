package gui;

import java.io.File;
import java.io.StringReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.nio.file.Path;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.lang.Throwable;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.scene.control.TabPane;
import javafx.scene.web.WebEngine;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
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

    private int parserNodeIdCur = 0;

    public TabPane thfArea;

    public ObservableList<String> recentlyOpenedFiles;

    public WebEngine outputEngine;

    public EditorModel()
    {
        recentlyOpenedFiles = FXCollections.observableArrayList(); // first element = oldest file, last element = latest file
    }

    protected EditorDocumentViewController getSelectedTab()
    {
        if (thfArea.getSelectionModel().getSelectedItem() == null) return null;
        else return (EditorDocumentViewController) thfArea.getSelectionModel().getSelectedItem().getUserData();
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
    }

    public void clearRecentlyOpenedFilesList(){
        recentlyOpenedFiles.clear();
        Config.setRecentlyOpenedFiles(recentlyOpenedFiles);
    }

    public void printTPTPTrees()
    {
        System.out.println("------------------------");
        System.out.println("------------------------");
    }

    public void onViewIncreaseFontSize() {
        getSelectedTab().model.onViewIncreaseFontSize();
    }

    public void onViewDecreaseFontSize() {
        getSelectedTab().model.onViewDecreaseFontSize();
    }

    public void onViewEnterPresentationMode() {
        getSelectedTab().model.onViewEnterPresentationMode();
        // TODO close side drawer, ...
    }

    public void onViewDefaultFontSize()
    {
        getSelectedTab().model.onViewDefaultFontSize();
    }

    private EditorDocumentViewController getNewTab() {
        EditorDocumentViewController doc;

        doc = new EditorDocumentViewController(null, this.thfArea.getTabs());
        thfArea.getSelectionModel().select(doc.tab);

        return doc;
    }

    public void openStream(InputStream stream, Path path) {
        getNewTab().model.openStream(stream, path);
    }

    public void openFile(File file) {
        getNewTab().model.openFile(file);
        updateRecentlyOpenedFiles(file);
    }

    public void newFile(){
        getNewTab();
    }

    // ------- DEBUG FUNCTIONS -------
    public void debugPrintHTML() {
        getSelectedTab().model.debugPrintHTML();
    }
}
