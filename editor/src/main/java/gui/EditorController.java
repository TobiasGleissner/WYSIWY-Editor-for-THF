package gui;

import javafx.fxml.FXML;

import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;


public class EditorController {
    @FXML
    private TextArea ta;
    private EditorModel model;

    public EditorController(EditorModel model){
        this.model = model;
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

}
