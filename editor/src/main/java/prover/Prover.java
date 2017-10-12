package prover;

import java.util.List;

import java.io.IOException;

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
        SYSTEMONTPTP_CUSTOM_PROVER {
            public String getString() {
                return "Custom Remote";
            }
            public List<String> getAvailableProvers(List<TPTPDefinitions.TPTPSubDialect> subdialects) throws IOException {
                return SystemOnTPTPProver.getInstance().getAvailableCustomProvers(subdialects);
            }
        },
        SYSTEMONTPTP_DEFAULT_PROVER {
            public String getString() {
                return "Default Remote";
            }
            public List<String> getAvailableProvers(List<TPTPDefinitions.TPTPSubDialect> subdialects) throws IOException {
                return SystemOnTPTPProver.getInstance().getAvailableDefaultProvers(subdialects);
            }
        };

        public abstract String getString();
        public abstract List<String> getAvailableProvers(List<TPTPDefinitions.TPTPSubDialect> subdialects) throws IOException;
    }

    public static String getNiceProverTypeName(ProverType pt){
        switch (pt){
            case LOCAL_PROVER: return "local";
            case SYSTEMONTPTP_CUSTOM_PROVER: return "custom remote";
            case SYSTEMONTPTP_DEFAULT_PROVER: return "default remote";
            default: return "unknown";
        }
    }

    public static String getTrueFormula(TPTPDefinitions.TPTPDialect dialect){
        switch (dialect){
            case FOF: return "fof(1,conjecture,$true).";
            case CNF: return "cnf(1,conjecture,$true)."; // not working
            case TCF: return null;
            case TFF: return "tff(1,conjecture,$true).";
            case TFX: return null;
            case THF: return "thf(1,conjecture,$true).";
        }
        return null;
    }
}
