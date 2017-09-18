package gui;

import gui.Config;

import java.net.URL;

import java.io.File;

import java.util.Collection;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;

import javafx.stage.Stage;
import parser.ParseContext;
import javafx.stage.FileChooser;

import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.model.RichTextChange;
import org.fxmisc.richtext.model.StyledText;

public class EditorController implements Initializable {
    private EditorModel model;
    private Stage mainStage;

    @FXML
    private CodeArea thfArea;
    @FXML
    private CodeArea wysArea;

    public EditorController(EditorModel model, Stage mainStage) {
        this.model = model;
        this.mainStage = mainStage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        thfArea.richChanges().subscribe(this::onTHFTextChange);
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
    private void onTHFTextChange(RichTextChange<Collection<String>,StyledText<Collection<String>>,Collection<String>> change)
    {
    	ParseContext parseContext = model.parse(thfArea, "tptp_input");
    	System.out.println(parseContext.toString());
        System.out.println("thf change");

        if(change.getInserted().equals(change.getRemoved()))
            return;

        model.updateRainbows();
    }

    @FXML
    private void onWYSTextChange(RichTextChange<Collection<String>,StyledText<Collection<String>>,Collection<String>> change)
    {
        System.out.println("wysiwyg change");
    }
}
