package prover;

import java.nio.file.Path;
import java.util.Date;

public class ProvingEntry {
    public ProveResult proveResult;
    public Date timestamp;
    public Path path;
    public String originalProblem;

    @Override
    public String toString(){
        return proveResult.toString();
    }
}
