package gui;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import java.nio.file.Files;
import java.nio.file.Path;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class EditorModel
{
    private static Logging log = Logging.getInstance();

    public TabPane thfArea;

    public ObservableList<String> recentlyOpenedFiles;

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

    public void onViewIncreaseFontSize() {
        for (Tab t : thfArea.getTabs()){
            ((EditorDocumentViewController) t.getUserData()).model.style.increaseFontSize();
            ((EditorDocumentViewController) t.getUserData()).model.engine.executeScript("update_line_numbers()");
        }
    }

    public void onViewDecreaseFontSize() {
        for (Tab t : thfArea.getTabs()){
            ((EditorDocumentViewController) t.getUserData()).model.style.decreaseFontSize();
            ((EditorDocumentViewController) t.getUserData()).model.engine.executeScript("update_line_numbers()");
        }
    }

    public void onViewEnterPresentationMode() {
        for (Tab t : thfArea.getTabs()){
            ((EditorDocumentViewController) t.getUserData()).model.style.setFontSizeEditor(Config.fontSizePresentationMode);
            ((EditorDocumentViewController) t.getUserData()).model.engine.executeScript("update_line_numbers()");
        }
        // TODO close side drawer, ...
    }

    public void onViewDefaultFontSize() {
        for (Tab t : thfArea.getTabs()){
            ((EditorDocumentViewController) t.getUserData()).model.style.setFontSizeEditor(Config.fontSizeEditorDefault);
            ((EditorDocumentViewController) t.getUserData()).model.engine.executeScript("update_line_numbers()");
        }
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
        Tab openedTab = null;
        for (Tab t : thfArea.getTabs()){
            if (((EditorDocumentViewController) t.getUserData()).model.getPath().equals(file.toPath())){
                openedTab = t;
                break;
            }
        }
        if (openedTab != null){
            thfArea.getSelectionModel().select(openedTab);
            log.warning("File already opened. File='" + file + "'.");
            return;
        }
        getNewTab().model.openFile(file);
        updateRecentlyOpenedFiles(file);
        log.info("Opened file='" + file + "'.");
    }

    public void saveFile(EditorDocumentModel m){
        try {
            Files.write(m.getPath(),m.getText().getBytes());
            log.info("Saved editor content to file " + m.getPath() + ".");
        } catch (IOException e1) {
            e1.printStackTrace();
            log.error("Could not save editor content to file " + m.getPath() + ".");
        }
    }

    public void newFile(){
        getNewTab();
    }

    // ------- DEBUG FUNCTIONS -------
    public void debugPrintHTML() {
        getSelectedTab().model.debugPrintHTML();
    }
}
