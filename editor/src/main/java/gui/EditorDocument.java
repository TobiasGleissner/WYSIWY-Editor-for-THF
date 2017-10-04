package gui;

import prover.Prover;
import prover.ProvingHistory;
import prover.TPTPDefinitions;

import java.nio.file.Path;
import java.util.List;

public class EditorDocument {
    private static ProvingHistory provingHistory = ProvingHistory.getInstance();
    private static Logging log = Logging.getInstance();

    private Path path; // Path to document. Meaning of value null see constructor.

    private EditorDocument() {
        provingHistory.addDocument(this);
    }

    /**
     * Creates a new Document including WebView which can be used for opening multiple documents simultaneously
     * @param path file path on disk, null indicates there is no underlying file.
     *             This might be the case if the file was deleted/moved or a new document was created
     *             The field path has to be updated manually and TODO is assumed to be valid at all times
     */
    public EditorDocument(Path path){
        this();
        this.path = path;
    }

    /**
     * Returns the plain problem
     * @return
     */
    public String getText(){
        // TODO
        // return plain problem
        return "thf(1,conjecture,$true).";
    }

    /**
     * Displays the proving history of this document in a separate window
     */
    public void showProvingHistory(){
        // TODO
        // implement new window or something with slider, etc.
    }

    public void prove(String prover, Prover.ProverType proverType, int timeLimit){
        provingHistory.prove(this, prover, proverType, timeLimit);
    }

    /**
     * Cleanup document specific stuff on File > close
     */
    public void close(){
        provingHistory.remove(this);
    }

    /**
     * Returns the TPTP sub-dialect of the problem
     * @return
     */
    public TPTPDefinitions.TPTPSubDialect classifyByTPTPSubDialect(){
        // TODO
        // maybe this should happen in listener, trigger some flags etc.
        return TPTPDefinitions.TPTPSubDialect.TH1;
    }

    /**
     * Returns all compatible TPTP sub-dialects of the problem
     * @return
     */
    public List<TPTPDefinitions.TPTPSubDialect> getCompatibleTPTPSubDialects(){
        return TPTPDefinitions.getCompatibleSubDialects(this.classifyByTPTPSubDialect());
    }

    /**
     * Sets the current file path of the document
     * @param path
     */
    public void setPath(Path path){
        this.path = path;
    }

    /**
     * Returns the current file path of the document
     * The path is assumed to be valid at all times
     * @return
     */
    public Path getPath(){
        return path;
    }
}
