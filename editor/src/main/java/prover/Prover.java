package prover;

import java.util.List;

import java.io.IOException;

import prover.TPTPDefinitions;
import prover.SystemOnTPTPProver;
import prover.LocalProver;

public abstract class Prover {

    public enum ProverType {
        LOCAL_PROVER {
            public String getString() {
                return "Local";
            }
            public List<String> getAvailableProvers(List<TPTPDefinitions.TPTPSubDialect> subdialects) throws IOException {
                return LocalProver.getInstance().getAvailableProvers(subdialects);
            }
        },
        SYSTEMONTPTP_DEFAULT_PROVER {
            public String getString() {
                return "Remote";
            }
            public List<String> getAvailableProvers(List<TPTPDefinitions.TPTPSubDialect> subdialects) throws IOException {
                return SystemOnTPTPProver.getInstance().getAvailableDefaultProvers(subdialects);
            }
        },
        SYSTEMONTPTP_CUSTOM_PROVER {
            public String getString() {
                return "Custom";
            }
            public List<String> getAvailableProvers(List<TPTPDefinitions.TPTPSubDialect> subdialects) throws IOException {
                return SystemOnTPTPProver.getInstance().getAvailableCustomProvers(subdialects);
            }
        };

        public abstract String getString();
        public abstract List<String> getAvailableProvers(List<TPTPDefinitions.TPTPSubDialect> subdialects) throws IOException;
    }
}
