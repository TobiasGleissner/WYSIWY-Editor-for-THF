package gui.preferences;

import gui.Config;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class PreferencesController implements Initializable {
    private PreferencesModel model;
    private Stage stage;

    @FXML
    public TextField proverCommandTextField;
    @FXML
    public ComboBox localProversComboBox;

    public PreferencesController(PreferencesModel model, Stage stage){
        this.model = model;
        this.stage = stage;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Provers
        localProversComboBox.getItems().addAll(Config.getLocalProvers());
                //.setItems(FXCollections.observableArrayList(Config.getLocalProvers()));
    }

    @FXML
    public void onApplyProvers(ActionEvent actionEvent){
        System.out.println("onapply");
        System.out.println(localProversComboBox.getTypeSelector());

    }
    @FXML
    public void onTestProver(ActionEvent actionEvent){
        System.out.println("ontest");
        Config.getLocalProvers().forEach(n-> System.out.println(n));
    }

    public void onNewProver(ActionEvent actionEvent) {
        String proverNamae = "asd";
        String proverCommand = proverCommandTextField.getText();
    }

    @FXML
    public void onDeleteProver(ActionEvent actionEvent) {
        System.out.println(localProversComboBox.getTypeSelector());
    }
}
