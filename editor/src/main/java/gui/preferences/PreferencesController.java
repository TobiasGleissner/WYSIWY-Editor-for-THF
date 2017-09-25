package gui.preferences;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class PreferencesController implements Initializable {
    private PreferencesModel model;
    private Stage stage;

    public PreferencesController(PreferencesModel model, Stage stage){
        this.model = model;
        this.stage = stage;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void onApplyProvers(){
        System.out.println("onapply");
    }
    @FXML
    public void onTestProver(){
        System.out.println("ontest");
    }

    public void onNewProver(ActionEvent actionEvent) {
    }
}
