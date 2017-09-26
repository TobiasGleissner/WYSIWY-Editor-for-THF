package gui;

import exceptions.NameAlreadyInUseException;
import exceptions.ProverNotAvailableException;

import static javafx.application.Application.launch;

public class EditorApp {
    public static void main(String[] args) throws NameAlreadyInUseException, ProverNotAvailableException {
        launch(EditorView.class,args);
    }
}
