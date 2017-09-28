package gui;

import exceptions.NameAlreadyInUseException;
import exceptions.ProverNotAvailableException;

import static javafx.application.Application.launch;

public class EditorApp {
    public static void main(String[] args) throws NameAlreadyInUseException, ProverNotAvailableException {
        //Config.removePreference("asd");
        launch(EditorView.class,args);
    }
}
