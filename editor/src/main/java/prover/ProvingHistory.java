package prover;

import exceptions.ProverNotAvailableException;
import exceptions.ProverResultNotInterpretableException;
import gui.EditorDocumentModel;
import gui.Logging;

import java.io.IOException;
import java.util.*;

public class ProvingHistory {
    private static final Logging log = Logging.getInstance();
    private static ProvingHistory instance;
    LocalProver localProver;
    SystemOnTPTPProver systemOnTPTPProver;

    public Map<EditorDocumentModel,List<ProvingEntry>> documentToEntryListMap;
    public List<ProvingEntry> entryList;

    private ProvingHistory(){}

    public static ProvingHistory getInstance() {
        if (instance == null){
            instance = new ProvingHistory();
            instance.localProver = LocalProver.getInstance();
            try {
                instance.systemOnTPTPProver = SystemOnTPTPProver.getInstance();
            } catch (IOException e) {
                instance.systemOnTPTPProver = null;
                log.warning("Remote provers are not available.");
            }
            instance.documentToEntryListMap = new HashMap<>();
            instance.entryList = new ArrayList<>();
        }
        return instance;
    }

    private String preprocessIncludes(EditorDocumentModel doc){
        // TODO includes
        return doc.getText();
    }

    public void prove(EditorDocumentModel editorDocument, String proverName, Prover.ProverType proverType, int timeLimit){
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
            case SYSTEMONTPTP_DEFAULT_PROVER:{
                if (systemOnTPTPProver == null){
                    try {
                        systemOnTPTPProver = SystemOnTPTPProver.getInstance();
                    } catch (IOException e) {
                        systemOnTPTPProver = null;
                        log.error("Remote provers are not available.");
                        return;
                    }
                }
                try {
                    proveResult = systemOnTPTPProver.prove(problemWithIncludes,proverName,timeLimit);
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
            case SYSTEMONTPTP_CUSTOM_PROVER:{
                if (systemOnTPTPProver == null){
                    try {
                        systemOnTPTPProver = SystemOnTPTPProver.getInstance();
                    } catch (IOException e) {
                        systemOnTPTPProver = null;
                        log.error("Remote provers are not available.");
                        return;
                    }
                }
                String systemOnTPTPProverName = systemOnTPTPProver.getCustomProverSystemOnTPTPName(proverName);
                if (systemOnTPTPProverName == null) System.err.println("BUG:: systemOnTPTPProverName null"); // TODO remove this in production
                String proverCommand = systemOnTPTPProver.getCustomProverCommand(proverName);
                if (proverCommand == null) System.err.println("BUG:: proverCommand null"); // TODO remove this in production
                try {
                    proveResult = systemOnTPTPProver.prove(problemWithIncludes,systemOnTPTPProverName,proverCommand,timeLimit);
                } catch (ProverNotAvailableException e) {
                    log.error("The selected prover does not exist or is malfunctioning. ProverName='"
                            + proverName + "' SystemOnTPTPProverName='" + systemOnTPTPProverName + "' ProverCommand='" + proverCommand + "' ProverType='" + proverType.name() + "'.");
                    return;
                } catch (ProverResultNotInterpretableException e) {
                    log.error("The selected prover does not exist or is malfunctioning. ProverName='"
                            + proverName + "' SystemOnTPTPProverName='" + systemOnTPTPProverName + "' ProverCommand='" + proverCommand + "' ProverType='" + proverType.name() + "'\nErrorMessage='" + e.getMessage()
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

    public void remove(EditorDocumentModel doc){
        documentToEntryListMap.remove(doc);
    }

    public void addDocument(EditorDocumentModel doc){
        documentToEntryListMap.put(doc,new ArrayList<>());
    }
}
