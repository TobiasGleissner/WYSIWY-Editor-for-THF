package gui;

import java.io.*;
import java.net.URI;
import java.net.URL;

import java.io.IOException;
import java.io.InputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.StringWriter;
import java.io.IOException;

import java.nio.file.Path;
import java.nio.file.Paths;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

import java.util.Stack;
import java.util.Collection;
import java.util.ResourceBundle;
import java.util.List;
import java.util.Optional;
import java.util.Iterator;
import java.util.LinkedList;

import gui.preferences.PreferencesController;
import gui.preferences.PreferencesModel;
import gui.fileStructure.StructureTreeView;

import javafx.concurrent.Worker;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.event.EventHandler;
import javafx.event.Event;
import javafx.event.ActionEvent;

import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
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

import netscape.javascript.JSObject;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import com.sun.javafx.webkit.WebConsoleListener;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

import org.apache.commons.io.IOUtils;

import jiconfont.icons.FontAwesome;
import jiconfont.javafx.IconNode;

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
    static FontAwesome iconCollapse = FontAwesome.ANGLE_DOUBLE_DOWN;
    static FontAwesome iconUncollapse = FontAwesome.ANGLE_DOUBLE_UP;

    @FXML
    private Menu menubarRunProver;
    @FXML
    private MenuButton toolbarRunProver;
    @FXML
    private WebView thfArea;
    @FXML
    private WebView wysArea;
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

    JSObject jsDoc = null;
    Document doc = null;

    // DEBUG
    @FXML
    public void debugALG0157()
    {
        model.openStream(getClass().getResourceAsStream("/test/ALG015^7.p"));
    }
    @FXML
    public void debugCOM1601()
    {
        model.openStream(getClass().getResourceAsStream("/test/COM160^1.p"));
    }
    @FXML
    public void debugLCL6331()
    {
        model.openStream(getClass().getResourceAsStream("/test/LCL633^1.p"));
    }
    @FXML
    public void debugLCL6341()
    {
        model.openStream(getClass().getResourceAsStream("/test/LCL634^1.p"));
    }
    @FXML
    public void debugSYN0001()
    {
        model.openStream(getClass().getResourceAsStream("/test/SYN000^1.p"));
    }
    @FXML
    public void debugSYN0002()
    {
        model.openStream(getClass().getResourceAsStream("/test/SYN000^2.p"));
    }
    // DEBUG END

    private int num_updates;

    public class JSCallbackListener
    {
        private EditorModel model;

        public JSCallbackListener(EditorModel model)
        {
            this.model = model;
        }

        public int start_parsing(int startNode, int endNode)
        {
            return model.reparseArea(startNode, endNode);
        }

        public void debug(String str)
        {
            System.out.println("DEBUG = " + str);
        }

        public void sleep(Integer ms)
        {
            try
            {
                Thread.sleep(ms.longValue());
            }
            catch(InterruptedException e)
            {
            }
        }
    }

    JSCallbackListener jsCallbackListener;

    public EditorController(EditorModel model, Stage mainStage) {
        this.model = model;
        this.mainStage = mainStage;
        this.jsCallbackListener = new JSCallbackListener(model);

        num_updates = 0;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /*
        thfArea.setParagraphGraphicFactory(LineNumberFactory.get(thfArea));
        wysArea.setParagraphGraphicFactory(LineNumberFactory.get(wysArea));

        thfArea.setWrapText(true);
        wysArea.setWrapText(true);

        thfArea.plainTextChanges().subscribe(this::onTHFTextChange);
        wysArea.richChanges().subscribe(this::onWYSTextChange);
        */

        WebConsoleListener.setDefaultListener(new WebConsoleListener(){
            @Override
            public void messageAdded(WebView webView, String message, int lineNumber, String sourceId) {
                System.out.println("Console: [" + sourceId + ":" + lineNumber + "] " + message);
            }
        });

        //this.model.thfArea = thfArea;
        //this.model.wysArea = wysArea;

        model.engine = this.thfArea.getEngine();
        model.engine.setJavaScriptEnabled(true);
        //URL cssURL = getClass().getResource("/gui/editorField.css");
        //model.engine.setUserStyleSheetLocation(cssURL.toString());
        //InputStream cssInputStream = ClassLoader.getSystemResourceAsStream("gui/editorField.css");
        //String css = new BufferedReader(new InputStreamReader(cssInputStream)).lines().collect(Collectors.joining("\n"));



        model.engine.getLoadWorker().stateProperty().addListener(
                new ChangeListener<Worker.State>()
                {
                    @Override
                    public void changed(ObservableValue ov, Worker.State oldState, Worker.State newState)
                    {
                        if(newState == Worker.State.SUCCEEDED)
                        {
                            doc = model.engine.getDocument();
                            model.doc = model.engine.getDocument();
                            model.style = new WebKitStyle();
                            model.style.setDoc(doc);

                            System.out.println("doc = " + doc);

                            if(doc == null)
                                return;

                            jsDoc = (JSObject) doc;

                            JSObject window = (JSObject) model.engine.executeScript("window");
                            window.setMember("java", jsCallbackListener);

                            Integer sel = (Integer) jsDoc.eval("getSelection().anchorOffset");
                            System.out.println("selection = " + sel);
                        }
                    }
                }
        );

        model.engine.setOnAlert(t -> System.out.println(t));
        model.engine.setOnError(e -> System.out.println(e.getMessage()));

        try
        {
            model.engine.loadContent(
                IOUtils.toString(getClass().getResourceAsStream("/gui/editor.html"), "UTF-8")
            );
        }
        catch(IOException ex)
        {
            /* TODO */
            ex.printStackTrace();
        }

        // Element el = engine.getDocument().getElementById("content");
        // System.out.println("" + sel);


        addCurrentlyAvailableProversToMenus();
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
                    try {
                        copyStringToClipboard(IOUtils.toString(stream, "UTF-8"));
                    } catch (IOException e1) {
                        model.addErrorMessage(e1);
                    }
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
    private void onFileExit(ActionEvent e) {
        System.exit(0);
    }

    @FXML
    private void onTestPref(ActionEvent e)
    {

        Integer sel = (Integer) jsDoc.eval("getSelection().anchorOffset");
        System.out.println("selection = " + sel);

        StringBuilder content = new StringBuilder();
        Stack<Node> nodes = new Stack();
        nodes.push(doc.getFirstChild());

        while(!nodes.empty())
        {
            Node n = nodes.pop();

            if(n instanceof Text)
            {
                Text t = (Text)n;
                content.append(t.getWholeText());
            }

            NodeList list = n.getChildNodes();
            for(int i = list.getLength(); i > 0; --i)
                nodes.push(list.item(i-1));

            if(n instanceof Element)
            {
                Element el = (Element)n;
                System.out.println("tag_name = '" + el.getTagName() + "'");
                System.out.println("class = '" + el.getAttribute("class") + "'");

                if(el.getTagName().equals("DIV") && el.getAttribute("class") == null)
                    content.append("\n");
            }
        }

        System.out.print("content = '");
        System.out.print(content.toString());
        System.out.println("'");

        try
        {
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer();
            DOMSource source = new DOMSource(doc);

            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);

            transformer.transform(source, result);

            System.out.print("content = '");
            System.out.print(writer.toString());
            System.out.println("'");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    @FXML
    private void onReparse(ActionEvent e) {
        model.reparse();
    }

    @FXML
    private void onPrintTree(ActionEvent e) {
        model.printTPTPTrees();
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

        IconNode icon = new IconNode(iconCollapse);
        icon.getStyleClass().add("tabpane-icon");
        tabPaneLeftCollapse.setGraphic(icon);
        tabPaneLeft.getSelectionModel().select(1);

        tabPaneLeft.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
            @Override
            public void changed(ObservableValue<? extends Tab> observable, Tab oldTab, Tab newTab) {
                if(newTab == tabPaneLeftCollapse) {
                    double width = splitPaneVertical.getWidth();
                    double orgDividerPosition = splitPaneVertical.getDividers().get(0).positionProperty().getValue();
                    double minDividerPosition = getMinDividerPosition();
                    if (minDividerPosition/orgDividerPosition<0.95) {
                        splitPaneVertical.setDividerPosition(0,minDividerPosition+(1/width));
                    } else {
                        splitPaneVertical.setDividerPosition(0,0.2);
                    }
                    tabPaneLeft.getSelectionModel().select(oldTab);
                }
            }
        });

        splitPaneVertical.getDividers().get(0).positionProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                IconNode icon;
                if(getMinDividerPosition()/(double)newValue<0.95) {
                    icon = new IconNode(iconCollapse);
                    splitPaneVertical.setResizableWithParent(tabPaneLeft, Boolean.TRUE);
                } else {
                    icon = new IconNode(iconUncollapse);
                    splitPaneVertical.setResizableWithParent(tabPaneLeft, Boolean.FALSE);
                }
                icon.getStyleClass().add("tabpane-icon");
                tabPaneLeftCollapse.setGraphic(icon);
            }
        });

    }

    private double getMinDividerPosition() {
        return tabPaneLeft.getTabMaxHeight()/splitPaneVertical.getWidth();
    }

    private void addCurrentlyAvailableProversToMenus() {
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

    @FXML
    public void onViewToolWindowProject(ActionEvent actionEvent){

    }

    @FXML
    public void onViewIncreaseFontSize(ActionEvent actionEvent) {
        model.onViewIncreaseFontSize();
    }

    @FXML
    public void onViewDecreaseFontSize(ActionEvent actionEvent) {
        model.onViewDecreaseFontSize();
    }

    @FXML
    public void onViewEnterPresentationMode(ActionEvent actionEvent) {
        model.onViewEnterPresentationMode();
    }
}
