package gui;

import prover.ProvingHistory;

import java.nio.file.Path;

public class EditorDocument {
    private static ProvingHistory provingHistory = ProvingHistory.getInstance();
    private static Logging log = Logging.getInstance();

    private Path path;

    private EditorDocument(){
        provingHistory.addDocument(this);
    }

    public EditorDocument(Path path){
        this();
        this.path = path;
    }

    public Path getPath(){
        return path;
    }

    public String getText(){
        // TODO
        return "thf(1,conjecture,$true).";
    }

    public void showProvingHistory(){

    }

    public void prove(){

    }

    public void close(){

    }

}
