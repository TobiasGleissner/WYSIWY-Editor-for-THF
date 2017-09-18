package gui;

import java.net.URL;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.charset.StandardCharsets;

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

        try
        {
            Path path = selectedFile.toPath();
            byte[] content = Files.readAllBytes(path);
            System.out.println("" + thfArea);
            thfArea.replaceText(new String(content, StandardCharsets.UTF_8));
        }
        catch(java.io.IOException t)
        {
            model.addErrorMessage(t);
        }
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
    private void onTHFTextChange(RichTextChange<Collection<String>,StyledText<Collection<String>>,Collection<String>> change)
    {
    	ParseContext parseContext = model.parse(thfArea, "tptp_input");
    	//System.out.println(parseContext.toString());
        System.out.println("thf change");
    }

    @FXML
    private void onWYSTextChange(RichTextChange<Collection<String>,StyledText<Collection<String>>,Collection<String>> change)
    {
        System.out.println("wysiwyg change");
    }
}
