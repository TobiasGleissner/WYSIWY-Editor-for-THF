package prover;

import exceptions.ProverNotAvailableException;
import exceptions.ProverResultNotInterpretableException;

import java.io.IOException;
import java.util.List;

public interface Prover {
    ProveResult prove(String problem, String source, String prover, int timeLimit) throws ProverNotAvailableException, ProverResultNotInterpretableException, IOException;
    List<String> getAvailableProvers(TPTPDefinitions.TPTPDialect dialect) throws ProverNotAvailableException;
}
