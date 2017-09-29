package prover;

import exceptions.ProverNotAvailableException;
import exceptions.ProverResultNotInterpretableException;

import java.io.IOException;
import java.util.List;

public interface Prover {
    public enum ProverType{LOCAL_PROVER, SYSTEMONTPTP_PROVER}
    ProveResult prove(String problem, String prover, int timeLimit) throws ProverNotAvailableException, ProverResultNotInterpretableException, IOException;
    /**
     * Retrieves a list of available provers of a certain TPTP dialect.
     * @param dialect
     * @return SystemOnTPTP provers for the specified dialect as a list of strings
     */
    public List<String> getAvailableProvers(TPTPDefinitions.TPTPDialect dialect);

    /**
     * Retrieves a list of available of a certain TPTP subDialect.
     * @param subDialect
     * @return SystemOnTPTP provers for the specified sub-dialect as a list of strings
     */
    public List<String> getAvailableProvers(TPTPDefinitions.TPTPSubDialect subDialect);

    /**
     * Retrieves a list of available provers of certain TPTP subDialects.
     * @param subDialectList
     * @return SystemOnTPTP provers for the specified sub-dialects as a list of strings
     */
    public List<String> getAvailableProvers(List<TPTPDefinitions.TPTPSubDialect> subDialectList);
}
