package gui;

import java.net.URL;

import java.util.Collection;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;

import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.model.RichTextChange;
import org.fxmisc.richtext.model.StyledText;

public class EditorController implements Initializable {
    private EditorModel model;

    @FXML
    private CodeArea thfArea;
    @FXML
    private CodeArea wysArea;

    public EditorController(EditorModel model){
        this.model = model;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        thfArea.richChanges().subscribe(this::onTHFTextChange);
        wysArea.richChanges().subscribe(this::onWYSTextChange);
    }


    @FXML
    private void onFileNew(ActionEvent e){
        System.out.println("newfile");
    }

    @FXML
    private void onFileSave(ActionEvent e){
        System.out.println("savefile");
    }

    @FXML
    private void onFileExit(ActionEvent e){
        System.exit(0);
    }

    @FXML
    private void onTHFTextChange(RichTextChange<Collection<String>,StyledText<Collection<String>>,Collection<String>> change)
    {
        System.out.println("thf change");
    }

    @FXML
    private void onWYSTextChange(RichTextChange<Collection<String>,StyledText<Collection<String>>,Collection<String>> change)
    {
        System.out.println("wysiwyg change");
    }
}
