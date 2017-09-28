package gui.preferences;

import exceptions.NameAlreadyInUseException;
import exceptions.ProverNotAvailableException;
import exceptions.ProverResultNotInterpretableException;
import gui.Logging;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import prover.ProveResult;
import prover.TPTPDefinitions;
import prover.local.LocalProver;
import util.RandomString;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class PreferencesController implements Initializable {
    private PreferencesModel model;
    private Stage stage;
    private LocalProver lp;
    private static Logging log = Logging.getInstance();

    @FXML public Label nameTakenWarning;
    @FXML public TextField proverNameTextField;
    @FXML public TextField proverCommandTextField;
    @FXML public TreeView<String> localProverTree;
    @FXML public ListView<String> subDialectListView;

    private TreeItem<String> root;
    private String currentProver;
    private TreeItem<String> currentItem;


    public PreferencesController(PreferencesModel model, Stage stage){
        this.model = model;
        this.stage = stage;
        this.lp = LocalProver.getInstance();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TPTP SubDialects
        subDialectListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        List<String> subDialects = Arrays.stream(TPTPDefinitions.TPTPSubDialect.values())
                .map(Enum::name)
                .collect(Collectors.toList());
        subDialectListView.setItems(FXCollections.observableArrayList(subDialects));

        // Tree
        localProverTree.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        root = new TreeItem<>("Provers");
        localProverTree.setRoot(root);
        updateLocalProversTree();

        // select current prover
        showFirstProver();

        // misc
        nameTakenWarning.setVisible(false);

        // double click on tree item
        localProverTree.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent mouseEvent)
            {
                if(mouseEvent.getClickCount() == 1) {
                    Node node = mouseEvent.getPickResult().getIntersectedNode();
                    // Accept clicks only on node cells, and not on empty spaces of the TreeView
                    if (node instanceof Text || (node instanceof TreeCell && ((TreeCell) node).getText() != null)) {
                        TreeItem<String> tempCurrentItem = localProverTree.getSelectionModel().getSelectedItem();
                        if (tempCurrentItem.equals(root)) {
                            subDialectListView.getSelectionModel().clearSelection();
                            localProverTree.getSelectionModel().clearSelection();
                            proverNameTextField.setText("");
                            proverCommandTextField.setText("");
                            currentItem = null;
                            currentProver = null;
                            return;
                        }
                        currentItem = tempCurrentItem;
                        currentProver = currentItem.getValue();
                        showProver(currentProver);
                    }
                }
            }
        });

        subDialectListView.addEventFilter(MouseEvent.MOUSE_PRESSED, evt -> {
            Node node = evt.getPickResult().getIntersectedNode();
            // go up from the target node until a list cell is found or it's clear
            // it was not a cell that was clicked
            while (node != null && node != subDialectListView && !(node instanceof ListCell)) {
                node = node.getParent();
            }
            // if is part of a cell or the cell,
            // handle event instead of using standard handling
            if (node instanceof ListCell) {
                evt.consume(); // prevent further handling
                ListCell cell = (ListCell) node;
                ListView lv = cell.getListView();
                lv.requestFocus(); // focus on list view
                if (!cell.isEmpty()) {
                    int index = cell.getIndex();
                    if (cell.isSelected()) {
                        lv.getSelectionModel().clearSelection(index);
                    } else {
                        lv.getSelectionModel().select(index);
                    }
                }
            }
        });

        // type in prover name text field
        proverNameTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (currentProver != null && currentProver.equals(newValue))
                nameTakenWarning.setVisible(false);
            else if (lp.getAllProverNames().contains(newValue))
                nameTakenWarning.setVisible(true);
            else
                nameTakenWarning.setVisible(false);
        });
    }

    private void updateLocalProversTree(){
        root.getChildren().removeAll(root.getChildren());
        List<String> proverList = lp.getAllProverNames();
        //Collections.sort(proverList);
        for (String p : proverList){
            root.getChildren().add(new TreeItem<>(p));
        }
        root.setExpanded(true);
    }

    private void showProver(String prover){
        proverNameTextField.setText(prover);
        String proverCmd = lp.getProverCommand(prover);
        proverCommandTextField.setText(proverCmd);
        subDialectListView.getSelectionModel().clearSelection();
        lp.getProverSubDialects(currentProver).forEach(sd -> subDialectListView.getSelectionModel().select(sd.name()));
    }

    private void showFirstProver(){
        if (root.getChildren().size() == 0){ // tree is empty
            currentProver = null;
            currentItem = null;
            proverCommandTextField.setText("");
            proverNameTextField.setText("");
            subDialectListView.getSelectionModel().clearSelection();
            return;
        }
        currentItem = root.getChildren().get(0);
        currentProver = currentItem.getValue();
        localProverTree.getSelectionModel().select(localProverTree.getRow(root.getChildren().get(0))); // select first element in tree
        showProver(currentProver);
    }

    private List<TPTPDefinitions.TPTPSubDialect> getSelectedTPTPSubDialects(){
        return subDialectListView.getSelectionModel().getSelectedItems().stream()
                .map(TPTPDefinitions.TPTPSubDialect::valueOf)
                .collect(Collectors.toList());
    }

    @FXML
    public void onApplyProvers(ActionEvent actionEvent){
        if (currentProver == null) {
            log.warning("Could not apply: No prover selected.");
            return;
        }
        String proverName = proverNameTextField.getText();
        String proverCommand = proverCommandTextField.getText();
        if (lp.getAllProverNames().contains(proverName) && !currentProver.equals(proverName)){
            log.warning("Could not apply: A prover with name='" + proverName + "' already exists.");
            return;
        }
        try {
            lp.updateProver(currentProver,proverName,proverCommand, getSelectedTPTPSubDialects());
        } catch (ProverNotAvailableException e) {
            e.printStackTrace();
            // does not happen
        }
        /*
        try {
            lp.removeProver(currentProver);
        } catch (ProverNotAvailableException e) {
            // does not happen
        }
        try {
            lp.addProver(proverName,proverCommand, getSelectedTPTPSubDialects(),true);
        } catch (NameAlreadyInUseException e) {
            // does not happen
        }*/
        currentItem.setValue(proverName);
        currentProver = proverName;
        String dialectString = String.join(",", lp.getProverSubDialects(proverName).stream()
                .map(Enum::name)
                .collect(Collectors.toList()));
        log.info("Updated prover with name='" + proverName + "' and command = '" + proverCommand
                + "' and TPTP dialects='" + dialectString + "'.");
    }

    @FXML
    public void onTestProver(ActionEvent actionEvent){
        if (currentProver != null){
            String proverCommand = proverCommandTextField.getText();
            try {
                ProveResult pr = lp.testTHFProver(proverCommand);
                log.info("Prover command not working. Command='" + proverCommand + "'.");
            } catch (ProverNotAvailableException|IOException|ProverResultNotInterpretableException e) {
                log.warning("Prover command not working. Command='" + proverCommand + "'.");
            }
        } else {
            log.warning("Could not test prover: No prover selected.");
        }
    }

    @FXML
    public void onNewProver(ActionEvent actionEvent) {
        String proverName = "unnamed_" + RandomString.getRandomString();
        while (lp.getAllProverNames().contains(proverName)) proverName = "unnamed_" + RandomString.getRandomString();
        currentProver = proverName;
        try {
            lp.addProver(proverName,"",new ArrayList<>(),true);
        } catch (NameAlreadyInUseException e) {
            // does not happen due to while loop
        }
        currentItem = new TreeItem<>(proverName);
        localProverTree.getRoot().getChildren().add(currentItem);
        localProverTree.getSelectionModel().select(currentItem);
        showProver(currentProver);
        log.info("Created new prover with name='" + proverName + "'.");
    }

    @FXML
    public void onDeleteProver(ActionEvent actionEvent) {
        String oldName = proverNameTextField.getText();
        String oldCommand = proverCommandTextField.getText();
        String oldDialectString = String.join(",", lp.getProverSubDialects(oldName).stream()
                .map(Enum::name)
                .collect(Collectors.toList()));
        try {
            lp.removeProver(currentProver);
        } catch (ProverNotAvailableException e) {
            log.warning("Could not remove prover: No prover selected.");
        }
        proverNameTextField.setText("");
        proverCommandTextField.setText("");
        localProverTree.getRoot().getChildren().remove(currentItem);
        localProverTree.getSelectionModel().clearSelection();
        subDialectListView.getSelectionModel().clearSelection();
        currentProver = null;
        currentItem = null;
        log.info("Removed prover with name='" + oldName + "' and command='" + oldCommand
                + "' and TPTP dialects='" + oldDialectString + "'.");
    }
}
