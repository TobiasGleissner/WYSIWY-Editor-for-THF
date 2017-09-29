package prover;

import exceptions.ProverNotAvailableException;
import exceptions.ProverResultNotInterpretableException;
import gui.EditorDocument;
import gui.Logging;
import prover.local.LocalProver;
import prover.remote.HttpProver;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

public class ProvingHistory {

    public static void main(String[] args) throws IOException {

        List<TPTPDefinitions.TPTPSubDialect> subDialects = new ArrayList<>();
        subDialects.add(TPTPDefinitions.TPTPSubDialect.TH0);
        HttpProver.getInstance().getAvailableProvers(subDialects).forEach(System.out::println);

    }

    private static final Logging log = Logging.getInstance();
    private static ProvingHistory instance;
    LocalProver localProver;
    HttpProver httpProver;

    public Map<EditorDocument,List<ProvingEntry>> documentToEntryListMap;
    public List<ProvingEntry> entryList;

    private ProvingHistory(){}

    public static ProvingHistory getInstance() {
        if (instance == null){
            instance = new ProvingHistory();
            instance.localProver = LocalProver.getInstance();
            try {
                instance.httpProver = HttpProver.getInstance();
            } catch (IOException e) {
                instance.httpProver = null;
                log.warning("Remote provers are not available.");
            }
            instance.documentToEntryListMap = new HashMap<>();
            instance.entryList = new ArrayList<>();
        }
        return instance;
    }

    private String preprocessIncludes(EditorDocument doc){
        // TODO includes
        return doc.getText();
    }

    public void prove(EditorDocument editorDocument, String proverName, Prover.ProverType proverType, int timeLimit){
        Date now = new Date();
        ProveResult proveResult = null;
        String problemWithIncludes = preprocessIncludes(editorDocument);
        switch (proverType){
            case LOCAL_PROVER:{
                try {
                    proveResult = localProver.prove(problemWithIncludes,proverName,timeLimit);
                } catch (IOException e) {
                    log.error("Could not create a temporary file containing the problem. Filename='"
                            + editorDocument.getPath().toString()+ "'.");
                    return;
                } catch (ProverNotAvailableException e) {
                    log.error("The selected prover does not exist or is malfunctioning. ProverName='"
                            + proverName + "' ProverType='" + proverType.name() + "'.");
                    return;
                } catch (ProverResultNotInterpretableException e) {
                    log.error("The selected prover does not exist or is malfunctioning. ProverName='"
                            + proverName + "' ProverType='" + proverType.name() + "'\nErrorMessage='" + e.getMessage()
                            + "'\n ProverOutput='" + e.getProverOutput() + "'.");
                    return;
                }
                break;
            }
            case SYSTEMONTPTP_PROVER:{
                if (httpProver == null){
                    try {
                        httpProver = HttpProver.getInstance();
                    } catch (IOException e) {
                        httpProver = null;
                        log.error("Remote provers are not available.");
                        return;
                    }
                }
                try {
                    proveResult = httpProver.prove(problemWithIncludes,proverName,timeLimit);
                } catch (ProverNotAvailableException e) {
                    log.error("The selected prover does not exist or is malfunctioning. ProverName='"
                            + proverName + "' ProverType='" + proverType.name() + "'.");
                    return;
                } catch (ProverResultNotInterpretableException e) {
                    log.error("The selected prover does not exist or is malfunctioning. ProverName='"
                            + proverName + "' ProverType='" + proverType.name() + "'\nErrorMessage='" + e.getMessage()
                            + "'\n ProverOutput='" + e.getProverOutput() + "'.");
                    return;
                }
                break;
            }
        }
        ProvingEntry provingEntry = new ProvingEntry();
        provingEntry.proveResult = proveResult;
        provingEntry.timestamp = now;
        provingEntry.path = editorDocument.getPath();
        provingEntry.originalProblem = editorDocument.getText();
        documentToEntryListMap.get(editorDocument).add(provingEntry);
        entryList.add(provingEntry);
        log.prover(provingEntry);
    }

    public void remove(EditorDocument doc){
        documentToEntryListMap.remove(doc);
    }

    public void addDocument(EditorDocument doc){
        documentToEntryListMap.put(doc,new ArrayList<>());
    }
}
