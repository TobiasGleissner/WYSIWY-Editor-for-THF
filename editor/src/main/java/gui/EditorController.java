package gui;

import java.net.URL;

import java.io.File;

import java.util.ResourceBundle;

import gui.fileBrowser.FileTreeView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.event.ActionEvent;

import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import org.eclipse.fx.ui.controls.styledtext.StyledTextArea;
import org.eclipse.fx.ui.controls.styledtext.StyledTextContent;
import org.eclipse.fx.ui.controls.styledtext.TextChangedEvent;
import org.eclipse.fx.ui.controls.styledtext.TextChangingEvent;

public class EditorController implements Initializable {
    private EditorModel model;
    private Stage mainStage;

    @FXML
    private FileTreeView fileBrowser;

    @FXML
    private StyledTextArea thfArea;
    //@FXML
    //private CodeArea wysArea;


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

    private StyledTextContent.TextChangeListener thfListener;
    private StyledTextContent document;

    public EditorController(EditorModel model, Stage mainStage) {
        this.model = model;
        this.mainStage = mainStage;

        this.thfListener = new StyledTextContent.TextChangeListener()
            {
                @Override
                public void textChanged(TextChangedEvent event)
                {
                    model.updateTHFTree(
                        event.offset,
                        event.offset + event.replaceCharCount + event.newCharCount,
                        event.offset + event.replaceCharCount,
                        event.source
                    );

                }

                @Override
                public void textSet(TextChangedEvent event)
                {
                    model.reparse();
                }

                @Override
                public void textChanging(TextChangingEvent event)
                {} /* STUB */
            };

        this.num_updates = 0;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.document = thfArea.getContent();
        this.document.addTextChangeListener(this.thfListener);

        thfArea.contentProperty().addListener
        (
            new ChangeListener<StyledTextContent>()
            {
                @Override
                public void changed(ObservableValue<? extends StyledTextContent> observable, StyledTextContent oldValue, StyledTextContent newValue)
                {
                    document.removeTextChangeListener(thfListener);
                    document = newValue;
                    document.addTextChangeListener(thfListener);
                }
            }
        );

        // TODO ALL
        //thfArea.setParagraphGraphicFactory(LineNumberFactory.get(thfArea));
        //wysArea.setParagraphGraphicFactory(LineNumberFactory.get(wysArea));

        //thfArea.setWrapText(true);
        //wysArea.setWrapText(true);

        //thfArea.plainTextChanges().subscribe(this::onTHFTextChange);
        //wysArea.richChanges().subscribe(this::onWYSTextChange);

        this.model.thfArea = thfArea;
        // TODO this.model.wysArea = wysArea;

        // model.updateStyle();

        thfArea.setLineRulerVisible(true);
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
        //RootDirItem rootDirItem = ResourceItem.createObservedPath(dir.toPath());
        //fileBrowser.setRootDirectories(FXCollections.observableArrayList(rootDirItem));
        fileBrowser.openDirectory(dir);
        //model.openDirectory(dir);
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

    /* TODO
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
    }*/
}
