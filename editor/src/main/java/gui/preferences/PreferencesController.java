package gui.preferences;

import exceptions.NameAlreadyInUseException;
import exceptions.ProverNotAvailableException;
import gui.Config;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import prover.local.LocalProver;
import util.RandomString;

import java.net.URL;
import java.util.*;

public class PreferencesController implements Initializable {
    private PreferencesModel model;
    private Stage stage;
    private LocalProver lp;

    @FXML public Label nameTakenWarning;
    @FXML public TextField proverNameTextField;
    @FXML public TextField proverCommandTextField;
    @FXML public TreeView<String> localProverTree;

    private TreeItem<String> root;
    private String currentProver;


    public PreferencesController(PreferencesModel model, Stage stage){
        this.model = model;
        this.stage = stage;
        this.lp = LocalProver.getInstance();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Provers
        root = new TreeItem<>("Provers");
        localProverTree.setRoot(root);
        updateLocalProversTree();
        // TODO what if no provers available
        if (root.getChildren().size() != 0) currentProver = root.getChildren().get(0).getValue();
        else currentProver = null;
        localProverTree.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent mouseEvent)
            {
                if(mouseEvent.getClickCount() == 2)
                {
                    showFirstElement();
                }
            }
        });
        /*
        proverNameTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            // prover in combo list (which should mean it is in the local Preferences too)
            if (localProversComboBox.getItems().contains(newValue)){
                proverAlreadyExistsWarning.setVisible(true);
            } else {
                proverAlreadyExistsWarning.setVisible(false);
            }
        });*/
    }

    private void updateLocalProversTree(){
        root.getChildren().removeAll(root.getChildren());
        List<String> proverList =lp.getAvailableProvers();
        Collections.sort(proverList);
        for (String p : proverList){
            root.getChildren().add(new TreeItem<>(p));
        }
    }

    private void showLocalProver(String prover){
        proverNameTextField.setText(prover);
        String proverCmd = Config.getLocalProverCommand(prover);
        if (proverCmd == null) proverCmd = "";
        proverCommandTextField.setText(proverCmd);
    }

    private void showFirstElement(){
        if (localProverTree.getSelectionModel().getSelectedItem().equals(root)) return;
        currentProver = localProverTree.getSelectionModel().getSelectedItem().getValue();
        showLocalProver(currentProver);
    }

    @FXML
    public void onApplyProvers(ActionEvent actionEvent){
        String proverName = proverNameTextField.getText();
        String proverCommand = proverCommandTextField.getText();
        try {
            LocalProver.getInstance().addProver(proverName,proverCommand,true);
        } catch (NameAlreadyInUseException e) {}
        updateLocalProversTree();
    }

    @FXML
    public void onTestProver(ActionEvent actionEvent){
        System.out.println("ontest");
    }

    @FXML
    public void onNewProver(ActionEvent actionEvent) {
        String proverName = "unnamed_" + RandomString.getRandomString();
        while (lp.getAvailableProvers().contains(proverName)) proverName = "unnamed_" + RandomString.getRandomString();
        String proverCommand = "";
        proverNameTextField.setText(proverName);
        proverCommandTextField.setText(proverCommand);
        try {
            lp.addProver(proverName,proverCommand,true);
        } catch (NameAlreadyInUseException e) {
            // does not happen due to while loop
        }
        updateLocalProversTree();
    }

    @FXML
    public void onDeleteProver(ActionEvent actionEvent) {
        try {
            lp.removeProver(localProverTree.getSelectionModel().getSelectedItem().getValue());
        } catch (ProverNotAvailableException e) {
        }
        updateLocalProversTree();
    }
}
