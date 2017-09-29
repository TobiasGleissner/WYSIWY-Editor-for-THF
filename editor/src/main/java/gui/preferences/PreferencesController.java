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
import prover.SystemOnTPTPProver;
import prover.TPTPDefinitions;
import prover.LocalProver;
import util.RandomString;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class PreferencesController implements Initializable {
    private PreferencesModel model;
    private Stage stage;
    private LocalProver lp;
    private SystemOnTPTPProver rp;
    private static Logging log = Logging.getInstance();

    @FXML public Label nameTakenWarning;
    @FXML public TextField proverNameTextField;
    @FXML public TextField proverCommandTextField;
    @FXML public TreeView<String> proverTree;
    @FXML public ListView<String> subDialectListView;

    private TreeItem<String> rootLocalProvers;
    private TreeItem<String> rootRemoteProvers;
    private TreeItem<String> dummyRoot;
    private String currentProver;
    private TreeItem<String> currentItem;


    public PreferencesController(PreferencesModel model, Stage stage){
        this.model = model;
        this.stage = stage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Provers
        this.lp = LocalProver.getInstance();
        try {
            this.rp = SystemOnTPTPProver.getInstance();
        } catch (IOException e) {
            log.warning("Could not reach SystemOnTPTP");
        }

        // TPTP SubDialects
        subDialectListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        List<String> subDialects = Arrays.stream(TPTPDefinitions.TPTPSubDialect.values())
                .map(Enum::name)
                .collect(Collectors.toList());
        subDialectListView.setItems(FXCollections.observableArrayList(subDialects));

        // Tree
        proverTree.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        dummyRoot = new TreeItem<>("Local Provers");
        proverTree.setRoot(dummyRoot);
        proverTree.setShowRoot(false);
        rootLocalProvers = new TreeItem<>("Local Provers");
        rootRemoteProvers = new TreeItem<>("Remote Provers");
        dummyRoot.getChildren().add(rootLocalProvers);
        dummyRoot.getChildren().add(rootRemoteProvers);
        updateLocalProversTree();

        // select current prover
        showFirstProver();

        // misc
        nameTakenWarning.setVisible(false);

        // double click on tree item
        proverTree.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent mouseEvent)
            {
                if(mouseEvent.getClickCount() == 1) {
                    Node node = mouseEvent.getPickResult().getIntersectedNode();
                    // Accept clicks only on node cells, and not on empty spaces of the TreeView
                    if (node instanceof Text || (node instanceof TreeCell && ((TreeCell) node).getText() != null)) {
                        TreeItem<String> tempCurrentItem = proverTree.getSelectionModel().getSelectedItem();
                        if (tempCurrentItem.equals(rootLocalProvers) | tempCurrentItem.equals(rootRemoteProvers) || tempCurrentItem.equals(dummyRoot)) {
                            subDialectListView.getSelectionModel().clearSelection();
                            proverTree.getSelectionModel().clearSelection();
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
            else {
                if (currentItem.getParent().equals(rootLocalProvers)){
                    if (lp.getAllProverNames().contains(newValue))
                        nameTakenWarning.setVisible(true);
                }
                else if (currentItem.getParent().equals(rootRemoteProvers)){
                    if (rp.getAllCustomProverNames().contains(newValue))
                        nameTakenWarning.setVisible(true);
                } else {
                    nameTakenWarning.setVisible(false);
                }
            }
        });
    }

    private void updateLocalProversTree(){
        rootLocalProvers.getChildren().removeAll(rootLocalProvers.getChildren());
        List<String> proverList = lp.getAllProverNames();
        for (String p : proverList){
            rootLocalProvers.getChildren().add(new TreeItem<>(p));
        }
        rootLocalProvers.setExpanded(true);
        rootRemoteProvers.getChildren().removeAll(rootRemoteProvers.getChildren());
        List<String> proverList2 = rp.getAllCustomProverNames();
        for (String p : proverList2){
            rootRemoteProvers.getChildren().add(new TreeItem<>(p));
        }
        rootRemoteProvers.setExpanded(true);
    }

    private void showProver(String prover){
        proverNameTextField.setText(prover);
        subDialectListView.getSelectionModel().clearSelection();
        String proverCmd = null;
        if (currentItem.getParent().equals(rootLocalProvers)) {
            proverCmd = lp.getProverCommand(prover);
            lp.getProverSubDialects(currentProver).forEach(sd -> subDialectListView.getSelectionModel().select(sd.name()));
        }
        else if (currentItem.getParent().equals(rootRemoteProvers)) {
            proverCmd = rp.getCustomProverCommand(prover);
            rp.getCustomProverSubDialects(currentProver).forEach(sd -> subDialectListView.getSelectionModel().select(sd.name()));
        }
        proverCommandTextField.setText(proverCmd);
    }

    private void showFirstProver(){
        if (rootLocalProvers.getChildren().size() == 0){ // tree is empty
            currentProver = null;
            currentItem = null;
            proverCommandTextField.setText("");
            proverNameTextField.setText("");
            subDialectListView.getSelectionModel().clearSelection();
            return;
        }
        currentItem = rootLocalProvers.getChildren().get(0);
        currentProver = currentItem.getValue();
        proverTree.getSelectionModel().select(proverTree.getRow(rootLocalProvers.getChildren().get(0))); // select first element in tree
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
        String dialectString = null;
        if (currentItem.getParent().equals(rootLocalProvers)) {
            if (lp.getAllProverNames().contains(proverName) && !currentProver.equals(proverName)) {
                log.warning("Could not apply: A local prover with name='" + proverName + "' already exists.");
                return;
            }
            try {
                lp.updateProver(currentProver, proverName, proverCommand, getSelectedTPTPSubDialects());
                dialectString = String.join(",", lp.getProverSubDialects(proverName).stream()
                        .map(Enum::name)
                        .collect(Collectors.toList()));
            } catch (ProverNotAvailableException e) {
                // does not happen
            }
        } else if (currentItem.getParent().equals(rootRemoteProvers)){
            if (rp.getAllCustomProverNames().contains(proverName) && !currentProver.equals(proverName)) {
                log.warning("Could not apply: A remote prover with name='" + proverName + "' already exists.");
                return;
            }
            try {
                rp.updateProver(currentProver, proverName, proverCommand, getSelectedTPTPSubDialects());
                dialectString = String.join(",", rp.getCustomProverSubDialects(proverName).stream()
                        .map(Enum::name)
                        .collect(Collectors.toList()));
            } catch (ProverNotAvailableException e) {
                e.printStackTrace();
                // does not happen
            }
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

        log.info("Updated prover with name='" + proverName + "' and command = '" + proverCommand
                + "' and TPTP dialects='" + dialectString + "'.");
    }

    @FXML
    public void onTestProver(ActionEvent actionEvent){
        if (currentProver != null){
            String proverCommand = proverCommandTextField.getText();
            if (currentItem.getParent().equals(rootLocalProvers)) {
                System.out.println("LOCAL!");
                try {
                    ProveResult pr = lp.testLocalProver(proverCommand);
                    System.out.println(pr.stdout);
                    log.info("Prover command working. Command='" + proverCommand + "'.");
                } catch (ProverNotAvailableException | IOException | ProverResultNotInterpretableException e) {
                    log.warning("Prover command not working. Command='" + proverCommand + "'.");
                }
            } else if (currentItem.getParent().equals(rootRemoteProvers)) {
                System.out.println("REMOTE!");
                try {
                    // TODO this must be the actual name instead of currentProver
                    ProveResult pr = rp.testRemoteProver(currentProver,proverCommand);
                    System.out.println(pr.stdout);
                    log.info("Prover command working. Command='" + proverCommand + "'.");
                } catch (ProverNotAvailableException | IOException | ProverResultNotInterpretableException e) {
                    log.warning("Prover command not working. Command='" + proverCommand + "'.");
                }
            }

        } else {
            log.warning("Could not test prover: No prover selected.");
        }
    }

    @FXML
    public void onNewLocalProver(ActionEvent actionEvent) {
        String proverName = "unnamed_" + RandomString.getRandomString();
        while (lp.getAllProverNames().contains(proverName)) proverName = "unnamed_" + RandomString.getRandomString();
        currentProver = proverName;
        try {
            lp.addProver(proverName,"",new ArrayList<>(),true);
        } catch (NameAlreadyInUseException e) {
            // does not happen due to while loop
        }
        currentItem = new TreeItem<>(proverName);
        rootLocalProvers.getChildren().add(currentItem);
        proverTree.getSelectionModel().select(currentItem);
        showProver(currentProver);
        log.info("Created new prover with name='" + proverName + "'.");
    }

    @FXML
    public void onNewRemoteProver(ActionEvent actionEvent) {
        String proverName = "unnamed_" + RandomString.getRandomString();
        while (rp.getAllCustomProverNames().contains(proverName)) proverName = "unnamed_" + RandomString.getRandomString();
        currentProver = proverName;
        try {
            rp.addProver(proverName,"",new ArrayList<>(),true);
        } catch (NameAlreadyInUseException e) {
            // does not happen due to while loop
        }
        currentItem = new TreeItem<>(proverName);
        rootRemoteProvers.getChildren().add(currentItem);
        proverTree.getSelectionModel().select(currentItem);
        showProver(currentProver);
        log.info("Created new prover with name='" + proverName + "'.");
    }

    @FXML
    public void onDeleteProver(ActionEvent actionEvent) {
        if (currentProver == null) {
            log.warning("Could not delete Prover: No prover selected.");
            return;
        }
        String oldName = proverNameTextField.getText();
        String oldCommand = proverCommandTextField.getText();
        String oldDialectString = null;
        if (currentItem.getParent().equals(rootLocalProvers)) {
            oldDialectString = String.join(",", lp.getProverSubDialects(oldName).stream()
                    .map(Enum::name)
                    .collect(Collectors.toList()));
            try {
                lp.removeProver(currentProver);
            } catch (ProverNotAvailableException e) {
                e.printStackTrace();
                // does not happen
            }
            rootLocalProvers.getChildren().remove(currentItem);
        } else if (currentItem.getParent().equals(rootRemoteProvers)){
            oldDialectString = String.join(",", rp.getCustomProverSubDialects(oldName).stream()
                    .map(Enum::name)
                    .collect(Collectors.toList()));
            try {
                rp.removeProver(currentProver);
            } catch (ProverNotAvailableException e) {
                // does not happen
                e.printStackTrace();
            }
            rootRemoteProvers.getChildren().remove(currentItem);
        }
        proverNameTextField.setText("");
        proverCommandTextField.setText("");
        proverTree.getSelectionModel().clearSelection();
        subDialectListView.getSelectionModel().clearSelection();
        currentProver = null;
        currentItem = null;
        log.info("Removed prover with name='" + oldName + "' and command='" + oldCommand
                + "' and TPTP dialects='" + oldDialectString + "'.");
    }
}
