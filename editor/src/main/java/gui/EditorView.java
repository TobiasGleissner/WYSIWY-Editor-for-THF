package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class EditorView extends Application {

    @Override
    public void start(Stage stage) throws Exception
    {
        Font.loadFont(getClass().getResourceAsStream("/gui/fonts/FontAwesome.otf"), 12);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/editor.fxml"));
        loader.setControllerFactory(t->new EditorController(new EditorModel(), stage));

        Scene scene = new Scene(loader.load());

        stage.setScene(scene);
        stage.show();
    }

}
