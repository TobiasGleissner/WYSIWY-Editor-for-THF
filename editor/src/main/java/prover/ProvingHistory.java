package prover;

import exceptions.ProverNotAvailableException;
import exceptions.ProverResultNotInterpretableException;
import gui.EditorDocumentModel;
import gui.Logging;
import util.tree.Node;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
        /*
        StringBuilder sb = new StringBuilder();
        Path docDirectory = doc.getPath().getParent();
        Collection<Node> includes = doc.getIncludes();
        for (Node i : includes){
            // include.file_name->Single_quoted
            String includeFile = i.getChild(1).getChild(0).getLabel();
            includeFile = includeFile.substring(1,includeFile.length()-1);
            Path includePath = Paths.get(docDirectory.toString(),includeFile);
            try {
                String includeContent = new String(Files.readAllBytes(includePath),"UTF-8");
                sb.append("% included file ");
                sb.append(includePath);
                sb.append("\n");
                sb.append(includeContent);
            } catch (IOException e) {
                log.error("Could not include file. Try proving anyway. File='" + includePath + "'.");
            }
        }
        if (includes.size() > 0) {
            sb.append("\n% problem file\n");
        }
        sb.append(doc.getText());
        System.out.println(sb.toString());
        return sb.toString();
        */
        return doc.getText();
    }

    public void prove(EditorDocumentModel editorDocument, String proverName, Prover.ProverType proverType, int timeLimit){
        Date now = new Date();
        ProveResult proveResult = null;
        String problemWithResolvedIncludes = preprocessIncludes(editorDocument);
        switch (proverType){
            case LOCAL_PROVER:{
                try {
                    proveResult = localProver.prove(problemWithResolvedIncludes,proverName,timeLimit);
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
                    proveResult = systemOnTPTPProver.prove(problemWithResolvedIncludes,proverName,timeLimit);
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
                String proverCommand = systemOnTPTPProver.getCustomProverCommand(proverName);
                try {
                    proveResult = systemOnTPTPProver.prove(problemWithResolvedIncludes,systemOnTPTPProverName,proverCommand,timeLimit);
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
