package gui;

import java.net.URL;

import java.io.File;

import java.util.Collection;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.event.ActionEvent;

import javafx.stage.Stage;
import parser.ParseContext;
import javafx.stage.FileChooser;


import org.eclipse.fx.ui.controls.styledtext.StyledTextArea;

public class EditorController implements Initializable {
    private EditorModel model;
    private Stage mainStage;

    @FXML
    private StyledTextArea thfArea;
    //@FXML
    //private CodeArea wysArea;

    private int num_updates;

    public EditorController(EditorModel model, Stage mainStage) {
        this.model = model;
        this.mainStage = mainStage;

        num_updates = 0;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
