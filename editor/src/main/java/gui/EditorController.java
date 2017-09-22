package gui;

import java.net.URL;

import java.io.IOException;
import java.io.File;
import java.nio.file.Path;

import java.util.Collection;
import java.util.ResourceBundle;
import java.util.List;
import java.util.Iterator;
import java.util.LinkedList;

import gui.fileStructure.StructureTreeView;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.event.EventHandler;
import javafx.event.ActionEvent;

import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import javafx.scene.control.Menu;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.RadioMenuItemBuilder;
import javafx.scene.control.MenuButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TreeItem;
import javafx.scene.input.MouseEvent;

import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;
import org.fxmisc.richtext.model.RichTextChange;
import org.fxmisc.richtext.model.PlainTextChange;
import org.fxmisc.richtext.model.StyledText;

import gui.fileBrowser.FileTreeView;
import gui.fileBrowser.FileWrapper;
import prover.TPTPDefinitions;
import prover.remote.HttpProver;

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
    private FileTreeView fileBrowser;
    @FXML
    private StructureTreeView structureView;

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

        addCurrentlyAvailableProversToMenus();
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
                    TreeItem<FileWrapper> item = fileBrowser.getSelectionModel().getSelectedItem();
                    if (!item.isLeaf()) {
                        return;
                    }
                    Path root = dir.toPath();
                    LinkedList<String> paths = new LinkedList<String>();
                    paths.add(item.getValue().toString());
                    while (item.getParent() != null) {
                        item = item.getParent();
                        if (item.getParent() != null)   // Necessary because the root directory is already in "root".
                            paths.add(item.getValue().toString());
                    }
                    
                    while (paths.size() > 0) {
                        root = root.resolve(paths.pollLast());
                    }
                    
                    model.openFile(new File(root.toString()));
                }
            }
        });
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

    private void addCurrentlyAvailableProversToMenus() {
        try {
            List<String> availableProvers = HttpProver.getInstance().getAvailableProvers(TPTPDefinitions.TPTPDialect.THF);

            // add list of provers to menubar
            ToggleGroup menubarProvers = new ToggleGroup();
            for (Iterator<String> i = availableProvers.iterator(); i.hasNext();) {
                RadioMenuItem item = RadioMenuItemBuilder.create().toggleGroup(menubarProvers).text(i.next().replace("---"," ")).build();
                menubarRunProver.getItems().add(item);
            }

            // add list of provers to toolbar
            toolbarRunProver.showingProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    if(newValue) {
                        toolbarRunProver.getItems().clear();
                        ToggleGroup toolbarProvers = new ToggleGroup();
                        for (Iterator<String> i = availableProvers.iterator(); i.hasNext();) {
                            RadioMenuItem item = RadioMenuItemBuilder.create().toggleGroup(toolbarProvers).text(i.next().replace("---"," ")).build();
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
}
