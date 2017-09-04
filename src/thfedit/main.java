package thfedit;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.collections.ObservableList;
import javafx.collections.ListChangeListener;
import javafx.collections.ListChangeListener.Change;
import java.lang.CharSequence;

import thfedit.listener;

public class main extends Application
{
    @Override
    public void start(Stage primaryStage)
    {
        TextArea area = new TextArea();

        ObservableList<CharSequence> area_contents = area.getParagraphs();
        area_contents.addListener(new listener());

         /*
        area_contents.addListener(
            new ListChangeListener<CharSequence>() {
                @Override
                public void onChanged(Change<CharSequence> c)
                {
                    while(c.next())
                    {
                        System.out.println("CHANGE!");
                    }
                }
            }
        );
        */

        StackPane root = new StackPane();
        root.getChildren().add(area);

        Scene scene = new Scene(root, 300,250);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
