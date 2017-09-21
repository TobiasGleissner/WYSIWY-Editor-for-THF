package gui;

import java.net.URL;

import java.io.File;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.event.ActionEvent;

import javafx.scene.control.TreeView;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import org.eclipse.fx.ui.controls.filesystem.DirItem;
import org.eclipse.fx.ui.controls.filesystem.DirectoryTreeView;
import org.eclipse.fx.ui.controls.filesystem.ResourceItem;
import org.eclipse.fx.ui.controls.filesystem.RootDirItem;
import org.eclipse.fx.ui.controls.filesystem.DirectoryView;
import parser.ParseContext;
import javafx.stage.FileChooser;

import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;
import org.fxmisc.richtext.model.RichTextChange;
import org.fxmisc.richtext.model.PlainTextChange;
import org.fxmisc.richtext.model.StyledText;
import org.fxmisc.richtext.model.StyledDocument;
import org.fxmisc.richtext.model.StyleSpans;
import org.fxmisc.richtext.model.StyleSpan;
import org.fxmisc.richtext.model.Paragraph;

public class EditorController implements Initializable {
    private EditorModel model;
    private Stage mainStage;

    @FXML
    private CodeArea thfArea;
    @FXML
    private CodeArea wysArea;

    @FXML
    private TreeView fileBrowser;

    // DEBUG
    @FXML
    public void debugALG0157(){
        String file = ClassLoader.getSystemResource("test/ALG015^7.p").getPath();
        debugLoad(file);
    }
    @FXML
    public void debugCOM1601(){
        String file = ClassLoader.getSystemResource("test/COM160^1.p").getPath();
        debugLoad(file);
    }
    @FXML
    public void debugLCL6331(){
        String file = ClassLoader.getSystemResource("test/LCL633^1.p").getPath();
        debugLoad(file);
    }
    @FXML
    public void debugLCL6341(){
        String file = ClassLoader.getSystemResource("test/LCL634^1.p").getPath();
        debugLoad(file);
    }
    @FXML
    public void debugSYN0001(){
        String file = ClassLoader.getSystemResource("test/SYN000^1.p").getPath();
        debugLoad(file);
    }
    @FXML
    public void debugSYN0002(){
        String file = ClassLoader.getSystemResource("test/SYN000^2.p").getPath();
        debugLoad(file);
    }
    private void debugLoad(String filename){
        filename = filename.replace("%","^").replace("5e","");
        File f = new File(filename);
        model.openFile(f);
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
    }

    @FXML
    private void onFileNew(ActionEvent e) {
        System.out.println("newfile");
    }


    @FXML
    private void onDirectoryOpen(ActionEvent e) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Open directory");
        File dir = directoryChooser.showDialog(mainStage);
        if(dir == null)
            return;
        RootDirItem rootDirItem = ResourceItem.createObservedPath(dir.toPath());
        //fileBrowser.setRootDirectories(FXCollections.observableArrayList(rootDirItem));
        model.openDirectory(dir);
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
        if(Config.getFont().equals("monospace"))
            Config.setFont("xos4 Terminus");
        else
            Config.setFont("monospace");

        model.updateStyle();
    }

    @FXML
    private void onReparse(ActionEvent e)
    {
        model.reparse();
    }

    @FXML
    private void onPrintTree(ActionEvent e)
    {
        model.printTPTPTrees();
    }

    @FXML
    private void onTHFTextChange(PlainTextChange change)
    {
        if(change.getInserted().equals(change.getRemoved()))
            return;

        //System.out.println("inserted = " + change.getInserted().getText());
        //System.out.println("removed  = " + change.getRemoved().getText());

        model.updateTHFTree(change.getPosition(), change.getInsertionEnd(), change.getRemovalEnd());
    }

    @FXML
    private void onWYSTextChange(RichTextChange<Collection<String>,StyledText<Collection<String>>,Collection<String>> change)
    {
        System.out.println("wysiwyg change");
    }
}
