package gui.preferences;

import exceptions.NameAlreadyInUseException;
import exceptions.ProverNotAvailableException;
import exceptions.ProverResultNotInterpretableException;
import gui.Config;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import prover.ProveResult;
import prover.TPTPDefinitions;
import prover.local.LocalProver;
import util.RandomString;

import java.io.IOException;
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
        // Tree
        root = new TreeItem<>("Provers");
        localProverTree.setRoot(root);
        updateLocalProversTree();

        // current prover
        showFirstProver();

        // misc
        nameTakenWarning.setVisible(false);

        // double click on tree item
        localProverTree.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent mouseEvent)
            {
                if(mouseEvent.getClickCount() == 2)
                {
                    showSelectedElement();
                }
            }
        });

        // type in prover name text field
        proverNameTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (currentProver != null && currentProver.equals(newValue))
                nameTakenWarning.setVisible(false);
            else if (lp.getAvailableProvers().contains(newValue))
                nameTakenWarning.setVisible(true);
            else
                nameTakenWarning.setVisible(false);
        });
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

    private void showSelectedElement(){
        if (localProverTree.getSelectionModel().getSelectedItem().equals(root)) return;
        currentProver = localProverTree.getSelectionModel().getSelectedItem().getValue();
        showLocalProver(currentProver);
    }

    private void showFirstProver(){
        if (root.getChildren().size() == 0){ // tree is empty
            currentProver = null;
            proverCommandTextField.setText("");
            proverNameTextField.setText("");
            return;
        }
        currentProver = root.getChildren().get(0).getValue(); // set first element in tree as current
        localProverTree.getSelectionModel().select(localProverTree.getRow(root.getChildren().get(0))); // select first element in tree
        showLocalProver(currentProver);
    }

    @FXML
    public void onApplyProvers(ActionEvent actionEvent){
        if (currentProver == null) return;
        String proverName = proverNameTextField.getText();
        String proverCommand = proverCommandTextField.getText();
        try {
            lp.removeProver(currentProver);
        } catch (ProverNotAvailableException e) {
        }
        try {
            lp.addProver(proverName,proverCommand,true);
        } catch (NameAlreadyInUseException e) {}
        updateLocalProversTree();
    }

    @FXML
    public void onTestProver(ActionEvent actionEvent){
        String proverCommand = proverCommandTextField.getText();
        try {
            ProveResult pr = lp.testTHFProver(proverCommand);
            System.out.println("Prover working!");
        } catch (ProverNotAvailableException|IOException|ProverResultNotInterpretableException e) {
            System.err.println("Prover not working!");
            System.err.println(e);
        }
    }

    @FXML
    public void onNewProver(ActionEvent actionEvent) {
        String proverName = "unnamed_" + RandomString.getRandomString();
        while (lp.getAvailableProvers().contains(proverName)) proverName = "unnamed_" + RandomString.getRandomString();
        String proverCommand = "";
        currentProver = proverName;
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
        showFirstProver();
    }
}
