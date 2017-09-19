package prover;

import exceptions.ProverNotAvailableException;
import exceptions.ProverResultNotInterpretableException;

public interface Prover {
    ProveResult prove(String problem, String source, String prover, int timelimit) throws ProverNotAvailableException, ProverResultNotInterpretableException;
}
