package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class EditorView extends Application {
    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/editor.fxml"));
        loader.setControllerFactory(t->new EditorController(new EditorModel()));
        stage.setScene(new Scene(loader.load()));
        stage.show();
    }

}
