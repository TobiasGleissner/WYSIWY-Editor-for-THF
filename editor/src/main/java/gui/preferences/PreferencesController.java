package gui.preferences;

import exceptions.NameAlreadyInUseException;
import exceptions.ProverNotAvailableException;
import exceptions.ProverResultNotInterpretableException;
import gui.Logging;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;
import org.controlsfx.control.CheckComboBox;
import util.RandomString;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

import prover.ProveResult;
import prover.SystemOnTPTPProver;
import prover.TPTPDefinitions;
import prover.LocalProver;

import gui.EditorController;
import gui.EditorDocumentModel;
import gui.HighlightingStyle;

public class PreferencesController implements Initializable {

    private PreferencesModel model;
    private EditorController editor;
    private Stage stage;
    private LocalProver lp;
    private SystemOnTPTPProver rp;
    private static Logging log = Logging.getInstance();

    @FXML public Label nameTakenWarning;
    @FXML public TextField proverNameTextField;
    @FXML public TextField proverCommandTextField;
    @FXML public ComboBox<String> proverSystemOnTPTPNameComboBox;
    @FXML public Label proverSystemOnTPTPNameLabel;
    @FXML public TreeView<String> proverTree;
    @FXML public CheckComboBox<String> subDialectCheckComboBox;
    @FXML public TabPane tabPane;
    @FXML public Tab tabProvers;

    private TreeItem<String> rootLocalProvers;
    private TreeItem<String> rootRemoteProvers;
    private TreeItem<String> dummyRoot;
    private String currentProver;
    private TreeItem<String> currentItem;
    private String select;

    public PreferencesController(PreferencesModel model, EditorController editor, Stage stage, String select){
        this.model = model;
        this.editor = editor;
        this.stage = stage;
        this.select = select;
        
        this.stage.setResizable(false);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (select != null && select.equals("prover")) tabPane.getSelectionModel().select(tabProvers);

        // Color choosers
        initColorPane();

        // Provers
        this.lp = LocalProver.getInstance();
        try {
            this.rp = SystemOnTPTPProver.getInstance();
        } catch (IOException e) {
            log.warning("Could not reach SystemOnTPTP");
        }

        // TPTP SubDialects
        List<String> subDialects = Arrays.stream(TPTPDefinitions.TPTPSubDialect.values())
                .map(Enum::name)
                .collect(Collectors.toList());
        subDialectCheckComboBox.getItems().addAll(FXCollections.observableArrayList(subDialects));

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
        proverSystemOnTPTPNameComboBox.setVisible(false);
        proverSystemOnTPTPNameLabel.setVisible(false);

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
                            subDialectCheckComboBox.getCheckModel().getCheckedIndices().stream().forEach(i->subDialectCheckComboBox.getCheckModel().clearCheck(i));
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

        subDialectCheckComboBox.addEventFilter(MouseEvent.MOUSE_PRESSED, evt -> {
            Node node = evt.getPickResult().getIntersectedNode();
            // go up from the target node until a list cell is found or it's clear
            // it was not a cell that was clicked
            while (node != null && node != subDialectCheckComboBox && !(node instanceof ListCell)) {
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
            if (currentProver == null) {
                nameTakenWarning.setVisible(false);
            } else if (currentProver.equals(newValue))
                nameTakenWarning.setVisible(false);
            else {
                if (currentItem.getParent().equals(rootLocalProvers)){
                    if (lp.getAvailableProvers().contains(newValue))
                        nameTakenWarning.setVisible(true);
                }
                else if (currentItem.getParent().equals(rootRemoteProvers)){
                    if (rp.getAvailableCustomProvers().contains(newValue))
                        nameTakenWarning.setVisible(true);
                } else {
                    nameTakenWarning.setVisible(false);
                }
            }
        });

        // remote prover combobox
        List<String> l = rp.getAvailableDefaultProvers().stream()
                .sorted(String.CASE_INSENSITIVE_ORDER)
                .collect(Collectors.toList());
        ObservableList<String> ol = FXCollections.observableArrayList(l);
        proverSystemOnTPTPNameComboBox.setItems(ol);
    }

    private void updateLocalProversTree(){
        rootLocalProvers.getChildren().removeAll(rootLocalProvers.getChildren());
        List<String> proverList = lp.getAvailableProvers();
        for (String p : proverList){
            rootLocalProvers.getChildren().add(new TreeItem<>(p));
        }
        rootLocalProvers.setExpanded(true);
        rootRemoteProvers.getChildren().removeAll(rootRemoteProvers.getChildren());
        List<String> proverList2 = rp.getAvailableCustomProvers();
        for (String p : proverList2){
            rootRemoteProvers.getChildren().add(new TreeItem<>(p));
        }
        rootRemoteProvers.setExpanded(true);
    }

    private void showProver(String prover){
        proverNameTextField.setText(prover);
        subDialectCheckComboBox.getCheckModel().getCheckedIndices().stream().forEach(i->subDialectCheckComboBox.getCheckModel().clearCheck(i));
        String proverCmd = null;
        if (currentItem.getParent().equals(rootLocalProvers)) {
            proverCmd = lp.getProverCommand(prover);
            lp.getProverSubDialects(currentProver).forEach(sd -> subDialectCheckComboBox.getCheckModel().check(sd.name()));
            proverSystemOnTPTPNameComboBox.setVisible(false);
            proverSystemOnTPTPNameLabel.setVisible(false);
        }
        else if (currentItem.getParent().equals(rootRemoteProvers)) {
            proverCmd = rp.getCustomProverCommand(prover);
            rp.getCustomProverSubDialects(currentProver).forEach(sd -> subDialectCheckComboBox.getCheckModel().check(sd.name()));
            proverSystemOnTPTPNameComboBox.setVisible(true);
            proverSystemOnTPTPNameComboBox.getSelectionModel().select(rp.getCustomProverSystemOnTPTPName(prover));
            proverSystemOnTPTPNameLabel.setVisible(true);
        }
        proverCommandTextField.setText(proverCmd);
    }

    private void showFirstProver(){
        if (rootLocalProvers.getChildren().size() == 0){ // tree is empty
            currentProver = null;
            currentItem = null;
            proverCommandTextField.setText("");
            proverNameTextField.setText("");
            subDialectCheckComboBox.getCheckModel().getCheckedIndices().stream().forEach(i->subDialectCheckComboBox.getCheckModel().clearCheck(i));
            return;
        }
        currentItem = rootLocalProvers.getChildren().get(0);
        currentProver = currentItem.getValue();
        proverTree.getSelectionModel().select(proverTree.getRow(rootLocalProvers.getChildren().get(0))); // select first element in tree
        showProver(currentProver);
    }

    private List<TPTPDefinitions.TPTPSubDialect> getSelectedTPTPSubDialects(){
        return subDialectCheckComboBox.getCheckModel().getCheckedItems().stream()
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
            if (lp.getAvailableProvers().contains(proverName) && !currentProver.equals(proverName)) {
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
            if (rp.getAvailableCustomProvers().contains(proverName) && !currentProver.equals(proverName)) {
                log.warning("Could not apply: A remote prover with name='" + proverName + "' already exists.");
                return;
            }
            try {
                String systemOnTPTPName = proverSystemOnTPTPNameComboBox.getValue();
                rp.updateProver(currentProver, proverName, proverCommand, systemOnTPTPName, getSelectedTPTPSubDialects());
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

        // Refresh lists of available provers in menubar and toolbar
        if (editor.model.getSelectedTab() == null)
            editor.addAvailableProversToMenus(new ArrayList<TPTPDefinitions.TPTPSubDialect>(){{add(TPTPDefinitions.TPTPSubDialect.TH1);}});
        else
            editor.addAvailableProversToMenus(editor.model.getSelectedTab().model.getCompatibleTPTPSubDialects());

        log.debug("Updated prover lists in menu");
    }

    @FXML public void onTestProver(ActionEvent actionEvent){
        if (currentProver != null){
            Collection<TPTPDefinitions.TPTPDialect> dialects = new HashSet<>(); // collects TPTPDialects for testing
            getSelectedTPTPSubDialects().stream()
                    .map(TPTPDefinitions::getTPTPDialectFromTPTPSubDialect)
                    .forEach(dialects::add);
            if (dialects.isEmpty()){
                log.error("Select at least one TPTPSubDialect.");
                return;
            }
            String proverCommand = proverCommandTextField.getText();
            if (currentItem.getParent().equals(rootLocalProvers)) {
                Collection<Pair<TPTPDefinitions.TPTPDialect,ProveResult>> testResults = lp.testLocalProver(proverCommand, dialects);
                for (Pair<TPTPDefinitions.TPTPDialect,ProveResult> r : testResults){
                    if (r.getValue() == null) {
                        log.warning("This dialect is not supported by the testing function. "
                                + "\n TPTPDialect='" + r.getKey() + "'.");
                    } else if (r.getValue().hasException()){
                        log.warning("Prover configuration not working for "
                                + "\n TPTPDialect='" + r.getKey()
                                + "' \n Command='" + proverCommand
                                + "' \n ErrorMessage='" + r.getValue().e + "'.");
                    } else {
                        log.info("Prover configuration working for "
                                + "\n TPTPDialect='" + r.getKey()
                                + "' \n Command='" + proverCommand + "'.");
                    }
                }
            } else if (currentItem.getParent().equals(rootRemoteProvers)) {
                String systemOnTPTPName = proverSystemOnTPTPNameComboBox.getValue();
                Collection<Pair<TPTPDefinitions.TPTPDialect,ProveResult>> testResults = rp.testRemoteProver(systemOnTPTPName,proverCommand,dialects);
                for (Pair<TPTPDefinitions.TPTPDialect,ProveResult> r : testResults){
                    if (r.getValue() == null) {
                        log.warning("This dialect is not supported by the testing function. "
                                + "\nTPTPDialect='" + r.getKey() + "'.");
                    } else if (r.getValue().hasException()){
                        log.warning("Prover configuration not working for "
                                + "\nTPTPDialect='" + r.getKey()
                                + "' \nSystemOnTPTPName='" + systemOnTPTPName
                                + "' \nCommand='" + proverCommand
                                + "' \nErrorMessage='" + r.getValue().e + "'.");
                    } else {
                        log.info("Prover configuration working for "
                                + "\nTPTPDialect='" + r.getKey()
                                + "' \nSystemOnTPTPName='" + systemOnTPTPName
                                + "' \nCommand='" + proverCommand + "'.");
                    }
                }
            }

        } else {
            log.warning("Could not test prover: No prover selected.");
        }
    }

    @FXML
    public void onNewLocalProver(ActionEvent actionEvent) {
        String proverName = "unnamed_" + RandomString.getRandomString();
        while (lp.getAvailableProvers().contains(proverName)) proverName = "unnamed_" + RandomString.getRandomString();
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
        while (rp.getAvailableCustomProvers().contains(proverName)) proverName = "unnamed_" + RandomString.getRandomString();
        if (rp.getAvailableDefaultProvers().size() == 0) {
            log.error("Could not connect to SystemOnTPTP. Please restart the application for the use of SystemOnTPTP provers.");
            return;
        }
        try {
            proverSystemOnTPTPNameComboBox.setValue("asd");
            String systemOnTPTPName = proverSystemOnTPTPNameComboBox.getValue();
            rp.addProver(proverName,"",systemOnTPTPName,new ArrayList<>(),true);
        } catch (NameAlreadyInUseException e) {
            // does not happen due to while loop
        }
        currentProver = proverName;
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
                proverSystemOnTPTPNameComboBox.setVisible(false);
                proverSystemOnTPTPNameLabel.setVisible(false);
            } catch (ProverNotAvailableException e) {
                // does not happen
                e.printStackTrace();
            }
            rootRemoteProvers.getChildren().remove(currentItem);
        }
        currentProver = null;
        currentItem = null;
        proverNameTextField.setText("");
        proverCommandTextField.setText("");
        proverTree.getSelectionModel().clearSelection();
        subDialectCheckComboBox.getCheckModel().getCheckedIndices().stream().forEach(i->subDialectCheckComboBox.getCheckModel().clearCheck(i));
        log.info("Removed prover with name='" + oldName + "' and command='" + oldCommand
                + "' and TPTP dialects='" + oldDialectString + "'.");
    }

    @FXML public GridPane colorPane;

    private void initColorPane()
    {
        int rowIndex = 1;

        for(HighlightingStyle t : HighlightingStyle.values())
        {
            Label name = new Label(t.toString());
            colorPane.setRowIndex(name, rowIndex);
            colorPane.setColumnIndex(name, 0);
            colorPane.getChildren().add(name);

            String fgColor = t.getColor(false);
            String bgColor = t.getColor(true);

            Color fg = null;
            try {
                fg = fgColor == null ? Color.WHITE : Color.web(fgColor);
            } catch (IllegalArgumentException e) {
                fg = Color.WHITE;
            }

            Color bg = null;
            try {
                bg = bgColor == null ? Color.WHITE : Color.web(bgColor);
            } catch (IllegalArgumentException e) {
                bg = Color.WHITE;
            }

            ColorPicker pickerFG = new ColorPicker(fg);
            colorPane.setRowIndex(pickerFG, rowIndex);
            colorPane.setColumnIndex(pickerFG, 1);
            colorPane.getChildren().add(pickerFG);

            ColorPicker pickerBG = new ColorPicker(bg);
            colorPane.setRowIndex(pickerBG, rowIndex);
            colorPane.setColumnIndex(pickerBG, 2);
            colorPane.getChildren().add(pickerBG);

            Button reset = new Button("Reset");
            colorPane.setRowIndex(reset, rowIndex);
            colorPane.setColumnIndex(reset, 3);
            colorPane.getChildren().add(reset);

            pickerFG.setOnAction(
                e -> {
                    t.setColor(false, colorToWeb(pickerFG.getValue()));
                    this.editor.updateCss();
                }
            );

            pickerBG.setOnAction(
                e -> {
                    t.setColor(true,  colorToWeb(pickerBG.getValue()));
                    this.editor.updateCss();
                }
            );

            reset.setOnAction(
                e -> {
                    String defaultFG = t.getDefaultFG();
                    String defaultBG = t.getDefaultBG();

                    t.setColor(false, defaultFG);
                    t.setColor(true,  defaultBG);

                    Color fg_ = defaultFG == null ? Color.WHITE : Color.web(defaultFG);
                    Color bg_ = defaultBG == null ? Color.WHITE : Color.web(defaultBG);

                    pickerFG.setValue(fg_);
                    pickerBG.setValue(bg_);

                    this.editor.updateCss();
                }
            );

            rowIndex++;
        }
    }

    private static String colorToWeb(Color color) {
        return String.format("#%02X%02X%02X%02X",
           (int)(color.getRed() * 255),
           (int)(color.getGreen() * 255),
           (int)(color.getBlue() * 255),
           (int)(color.getOpacity() * 255)
        );
    }
}
