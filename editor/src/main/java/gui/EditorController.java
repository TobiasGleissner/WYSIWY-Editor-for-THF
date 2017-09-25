package gui;

import java.net.URL;

import java.io.IOException;
import java.io.InputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

import java.util.Collection;
import java.util.ResourceBundle;
import java.util.List;
import java.util.Optional;
import java.util.Iterator;
import java.util.LinkedList;

import java.text.DecimalFormat;

import gui.preferences.PreferencesController;
import gui.preferences.PreferencesModel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.event.EventHandler;
import javafx.event.Event;
import javafx.event.ActionEvent;

import javafx.scene.Scene;
import javafx.stage.DirectoryChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TreeItem;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tab;

import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;
import org.fxmisc.richtext.model.RichTextChange;
import org.fxmisc.richtext.model.PlainTextChange;
import org.fxmisc.richtext.model.StyledText;

import gui.fileBrowser.FileTreeView;
import gui.fileStructure.StructureTreeView;
import gui.fileBrowser.FileWrapper;
import prover.TPTPDefinitions;
import prover.remote.HttpProver;
import prover.local.LocalProver;

public class EditorController implements Initializable {

    private EditorModel model;
    private Stage mainStage;
    private File dir;

    @FXML
    private Menu menubarRunProver;
    @FXML
    private MenuButton toolbarRunProver;
    @FXML
    private CodeArea thfArea;
    @FXML
    private CodeArea wysArea;
    @FXML
    private SplitPane splitPaneVertical;
    @FXML
    private TabPane tabPaneLeft;
    @FXML
    private Tab tabPaneLeftCollapse;
    @FXML
    private FileTreeView fileBrowser;
    @FXML
    private StructureTreeView structureView;

    // DEBUG
    @FXML
    public void debugALG0157()
    {
        model.openFile(new File("./src/main/resources/test/ALG015^7.p"));
    }
    @FXML
    public void debugCOM1601()
    {
        model.openFile(new File("./src/main/resources/test/COM160^1.p"));
    }
    @FXML
    public void debugLCL6331()
    {
        model.openFile(new File("./src/main/resources/test/LCL633^1.p"));
    }
    @FXML
    public void debugLCL6341()
    {
        model.openFile(new File("./src/main/resources/test/LCL634^1.p"));
    }
    @FXML
    public void debugSYN0001()
    {
        model.openFile(new File("./src/main/resources/test/SYN000^1.p"));
    }
    @FXML
    public void debugSYN0002()
    {
        model.openFile(new File("./src/main/resources/test/SYN000^2.p"));
    }
    // DEBUG END

    private int num_updates;

    public EditorController(EditorModel model, Stage mainStage) {
        this.model = model;
        this.mainStage = mainStage;

        num_updates = 0;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        thfArea.setParagraphGraphicFactory(LineNumberFactory.get(thfArea));
        wysArea.setParagraphGraphicFactory(LineNumberFactory.get(wysArea));

        thfArea.setWrapText(true);
        wysArea.setWrapText(true);

        thfArea.plainTextChanges().subscribe(this::onTHFTextChange);
        wysArea.richChanges().subscribe(this::onWYSTextChange);

        this.model.thfArea = thfArea;
        this.model.wysArea = wysArea;

        model.updateStyle();

        addCurrentlyavailableProversToMenus();
        makeTabPaneCollapsable();
    }

    @FXML
    private void onNAMEExit(ActionEvent e) {
        System.exit(0);
    }

    @FXML
    private void onFileNew(ActionEvent e) {
        System.out.println("newfile");
    }

    @FXML
    private void onDirectoryOpen(ActionEvent e) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Open directory");
        dir = directoryChooser.showDialog(mainStage);
        if(dir == null)
            return;
        //RootDirItem rootDirItem = ResourceItem.createObservedPath(dir.toPath());
        //fileBrowser.setRootDirectories(FXCollections.observableArrayList(rootDirItem));
        fileBrowser.openDirectory(dir);
        //model.openDirectory(dir);

        // Open file on double click
        fileBrowser.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent mouseEvent)
            {
                if(mouseEvent.getClickCount() == 2)
                {
                    Path path = getPathToSelectedItem(fileBrowser.getSelectionModel().getSelectedItem(), true, false);
                    if (path == null) {
                        return;
                    }
                    model.openFile(new File(path.toString()));
                }
            }
        });

        final ContextMenu contextMenu = new ContextMenu();
        MenuItem copyDirName = new MenuItem("Copy directory name");
        MenuItem copyPath = new MenuItem("Copy path to clipboard");
        MenuItem copyRelPath = new MenuItem("Copy relative path to clipboard");
        MenuItem cut = new MenuItem("Cut");
        MenuItem paste = new MenuItem("Paste");
        contextMenu.getItems().addAll(copyDirName, copyPath, copyRelPath, cut, paste);

        final ContextMenu contextMenuFile = new ContextMenu();
        MenuItem copyFileName = new MenuItem("Copy file name");
        MenuItem copyPathFile = new MenuItem("Copy path to clipboard");
        MenuItem copyRelPathFile = new MenuItem("Copy relative path to clipboard");
        MenuItem copyContent = new MenuItem("Copy file content to clipboard");
        MenuItem cutFile = new MenuItem("Cut");
        MenuItem pasteFile = new MenuItem("Paste");
        MenuItem deleteFile = new MenuItem("Delete");
        contextMenuFile.getItems().addAll(copyFileName, copyPathFile, copyRelPathFile, copyContent, cutFile, pasteFile, deleteFile);

        cut.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Cut...");
            }
        });
        copyFileName.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                copyFileOrDirectoryName();
            }
        });
        copyDirName.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                copyFileOrDirectoryName();
            }
        });

        // Copy file content to clipboard
        copyContent.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                File file = new File(getPathToSelectedItem(fileBrowser.getSelectionModel().getSelectedItem(), true, false).toString());
                InputStream stream = null;
                try {
                    stream = new FileInputStream(file);
                } catch (FileNotFoundException e) {
                    model.addErrorMessage(e);
                }
                if (stream != null) {
                    copyStringToClipboard(model.openStream(stream));
                }
            }
        });

        copyPath.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                copyFilePathToClipboard(false);
            }
        });

        copyPathFile.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                copyFilePathToClipboard(false);
            }
        });

        copyRelPathFile.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                copyFilePathToClipboard(true);
            }
        });

        copyRelPath.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                copyFilePathToClipboard(true);
            }
        });

        // Delete file in file browser.
        deleteFile.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TreeItem<FileWrapper> item = fileBrowser.getSelectionModel().getSelectedItem();
                File file = new File(getPathToSelectedItem(item, true, false).toString());

                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Delete file");
                alert.setHeaderText("Delete file?");
                alert.setContentText("Do you really want to delete the file "+item.getValue().toString()+"?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    file.delete();
                    item.getParent().getChildren().remove(item);
                }
            }
        });

        fileBrowser.addEventHandler(ContextMenuEvent.CONTEXT_MENU_REQUESTED, event -> {
            if (fileBrowser.getSelectionModel().getSelectedItem().isLeaf()) {
                contextMenuFile.show(fileBrowser, event.getScreenX(), event.getScreenY());
            }
            else {
                contextMenu.show(fileBrowser, event.getScreenX(), event.getScreenY());
            }
            event.consume();
        });
        fileBrowser.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
            contextMenu.hide();
            contextMenuFile.hide();
        });
    }

    /**
     * This method returns the path to the selected item in the file browser.
     * @param selectedItem: selected item in the file browser.
     * @param onlyLeavesAllowed: if true, only the path of leaves is returned.
     * @param relativePath: if true, the path of the selected item relative to the root of the file browser is returned.
     * @return the path of the selected item.
     */
    private Path getPathToSelectedItem(TreeItem<FileWrapper> selectedItem, Boolean onlyLeavesAllowed, Boolean relativePath) {
        if ( selectedItem == null || onlyLeavesAllowed && !selectedItem.isLeaf()) {
            return null;
        }
        Path root;
        if (relativePath) {
            root = Paths.get("");
        } else {
            root = dir.toPath();
        }
        LinkedList<String> paths = new LinkedList<String>();
        paths.add(selectedItem.getValue().toString());
        while (selectedItem.getParent() != null) {
            selectedItem = selectedItem.getParent();
            if (selectedItem.getParent() != null)   // Necessary because the root directory is already in "root".
                paths.add(selectedItem.getValue().toString());
        }

        while (paths.size() > 0) {
            root = root.resolve(paths.pollLast());
        }
        return root;
    }

    /**
     * Copy path of selected item in file browser to system clipboard.
     * @param relativePath: copy the path relative to the root of the file browser. Otherwise, the absolute path is copied.
     */
    private void copyFilePathToClipboard(Boolean relativePath) {
        Path path = getPathToSelectedItem(fileBrowser.getSelectionModel().getSelectedItem(), false, relativePath);
        copyStringToClipboard(path.toString());
    }

    /**
     * Copy @param string to system clipboard.
     */
    private void copyStringToClipboard(String string) {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        StringSelection selection = new StringSelection(string);
        clipboard.setContents(selection, selection);
    }

    // Copy the name of the selected file or directory in the file browser to the clipboard.
    private void copyFileOrDirectoryName() {
        TreeItem<FileWrapper> item = fileBrowser.getSelectionModel().getSelectedItem();
        if (item != null) {
            copyStringToClipboard(item.getValue().toString());
        }
    }

    @FXML
    private void onFileOpen(ActionEvent e) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open thf file");
        File selectedFile = fileChooser.showOpenDialog(mainStage);

        if(selectedFile == null)
            return;

        model.openFile(selectedFile);
    }

    @FXML
    private void onFileSave(ActionEvent e) {
        System.out.println("savefile");
    }

    @FXML
    private void onTestPref(ActionEvent e) {
        if(Config.getFont().equals("monospace"))
            Config.setFont("xos4 Terminus");
        else
            Config.setFont("monospace");

        model.updateStyle();
    }

    @FXML
    private void onReparse(ActionEvent e) {
        model.reparse();
    }

    @FXML
    private void onPrintTree(ActionEvent e) {
        model.printTPTPTrees();
    }

    @FXML
    private void onTHFTextChange(PlainTextChange change) {
        if(change.getInserted().equals(change.getRemoved()))
            return;

        //System.out.println("inserted = " + change.getInserted().getText());
        //System.out.println("removed  = " + change.getRemoved().getText());

        model.updateTHFTree(change.getPosition(), change.getInsertionEnd(), change.getRemovalEnd());
    }

    @FXML
    private void onWYSTextChange(RichTextChange<Collection<String>,StyledText<Collection<String>>,Collection<String>> change) {
        System.out.println("wysiwyg change");
    }

    @FXML
    public void onViewToolWindowProject(ActionEvent actionEvent) {
    }

    @FXML
    public void onViewIncreaseFontSize(ActionEvent actionEvent) {
    }

    @FXML
    public void onViewDecreaseFontSize(ActionEvent actionEvent) {
    }

    @FXML
    public void onViewEnterPresentationMode(ActionEvent actionEvent) {
    }

    public void onPreferences(ActionEvent actionEvent) {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/preferences.fxml"));
        loader.setControllerFactory(t->new PreferencesController(new PreferencesModel(), stage));

        Scene scene = null;
        try {
            scene = new Scene(loader.load());
            stage.setScene(scene);
            //stage.initModality(Modality.APPLICATION_MODAL);
            //stage.setAlwaysOnTop(true);
            stage.show();
            //stage.toFront();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void makeTabPaneCollapsable() {
        tabPaneLeft.getSelectionModel().select(1);
        tabPaneLeft.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
            @Override
            public void changed(ObservableValue<? extends Tab> observable, Tab oldTab, Tab newTab) {
                if(newTab == tabPaneLeftCollapse) {
                    double width = splitPaneVertical.getWidth();
                    double[] orgDividerPositions = splitPaneVertical.getDividerPositions();
                    double minDividerPosition = tabPaneLeft.getTabMaxHeight()/width;
                    if (minDividerPosition/orgDividerPositions[0]<0.95) {
                        splitPaneVertical.setDividerPosition(0,minDividerPosition+(1/width));
                        splitPaneVertical.setResizableWithParent(tabPaneLeft, Boolean.FALSE);
                    } else {
                        splitPaneVertical.setDividerPosition(0,0.2);
                        splitPaneVertical.setResizableWithParent(tabPaneLeft, Boolean.TRUE);
                    }
                    tabPaneLeft.getSelectionModel().select(oldTab);
                }
            }
        });
    }

    private void addCurrentlyavailableProversToMenus() {
        try {
            List<String> availableProversLocal = LocalProver.getInstance().getAvailableProvers(TPTPDefinitions.TPTPDialect.THF);
            List<String> availableProversRemote = HttpProver.getInstance().getAvailableProvers(TPTPDefinitions.TPTPDialect.THF);

            ToggleGroup menubarProvers = new ToggleGroup();
            // add list of local provers to menubar
            for (Iterator<String> i = availableProversLocal.iterator(); i.hasNext();) {
                RadioMenuItem item = new RadioMenuItem(i.next().replace("---"," "));
                item.setToggleGroup(menubarProvers);
                menubarRunProver.getItems().add(item);
            }
            // add list of remote provers to menubar
            for (Iterator<String> i = availableProversRemote.iterator(); i.hasNext();) {
                RadioMenuItem item = new RadioMenuItem(i.next().replace("---"," "));
                item.setToggleGroup(menubarProvers);
                menubarRunProver.getItems().add(item);
            }

            // listener for the toolbar prover menu
            toolbarRunProver.showingProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    if(newValue) {
                        toolbarRunProver.getItems().clear();
                        ToggleGroup toolbarProvers = new ToggleGroup();
                        // add list of local provers to toolbar
                        for (Iterator<String> i = availableProversLocal.iterator(); i.hasNext();) {
                            RadioMenuItem item = new RadioMenuItem(i.next().replace("---"," "));
                            item.setToggleGroup(toolbarProvers);
                            toolbarRunProver.getItems().add(item);
                        }
                        // add list of remote provers to toolbar
                        for (Iterator<String> i = availableProversRemote.iterator(); i.hasNext();) {
                            RadioMenuItem item = new RadioMenuItem(i.next().replace("---"," "));
                            item.setToggleGroup(toolbarProvers);
                            toolbarRunProver.getItems().add(item);
                        }
                    }
                }
            });
        } catch (IOException e) {
            // TODO: write log entry
        }
    }
}
