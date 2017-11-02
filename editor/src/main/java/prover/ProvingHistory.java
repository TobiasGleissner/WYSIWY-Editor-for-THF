package prover;

import exceptions.ProverNotAvailableException;
import exceptions.ProverResultNotInterpretableException;

import gui.EditorDocumentModel;
import gui.Logging;

import prover.ProveTask;

import util.tree.Node;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProvingHistory {
    private static final Logging log = Logging.getInstance();
    private static ProvingHistory instance;
    LocalProver localProver;
    SystemOnTPTPProver systemOnTPTPProver;

    public Map<EditorDocumentModel,List<ProvingEntry>> documentToEntryListMap;
    public List<ProvingEntry> entryList;

    public ObservableList<Thread> running = FXCollections.observableList(new ArrayList<>());

    private ProvingHistory() {}

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

        ProvingEntry provingEntry = new ProvingEntry();
        provingEntry.path = editorDocument.getPath();
        provingEntry.originalProblem = editorDocument.getText();

        ProveTask task = new ProveTask(proverType, problemWithResolvedIncludes, proverName, timeLimit);
        Thread taskThread = new Thread(task);

        task.setOnSucceeded(r -> {
            running.remove(taskThread);

            if(task.getValue().right() != null)
            {
                log.error(task.getValue().right());
                log.prover(task.getValue().right());
            }
            else
            {
                provingEntry.proveResult = task.getValue().left();
                provingEntry.timestamp = now;

                documentToEntryListMap.get(editorDocument).add(provingEntry);
                entryList.add(provingEntry);
                log.prover(provingEntry);
            }
        });

        task.setOnFailed(r -> {
            running.remove(taskThread);

            Throwable e = task.getException();
            if(e != null)
            {
                log.error(e.getMessage());
                log.prover(e.getMessage());
            }
        });

        running.add(taskThread);
        taskThread.start();
    }

    public void remove(EditorDocumentModel doc){
        documentToEntryListMap.remove(doc);
    }

    public void addDocument(EditorDocumentModel doc){
        documentToEntryListMap.put(doc,new ArrayList<>());
    }
}
