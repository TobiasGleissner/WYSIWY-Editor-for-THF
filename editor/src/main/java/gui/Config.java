package gui; /* TODO: Change the package hierarchy, put this one above gui. */

import java.util.ArrayList;
import java.util.List;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import prover.TPTPDefinitions;
import prover.ProverConfiguration;

public class Config {
    public static Preferences prefs = Preferences.userNodeForPackage(Config.class);

    // misc
    public static final String name = "editor";
    public static final String USER_AGENT = "thf_editor";
    public static final int maxRecentlyOpenedFiles = 5;
    public static final long maxFileSize = 200000; // bytes

    // editor
    public static final double fontSizeIncrementStep = 0.1;
    public static final double fontSizeEditorDefault = 0.4;
    public static final double fontSizeEditorPresentationMode = 1.0;

    // output
    public static final double fontSizeOutputDefault = 0.3;
    public static final double fontSizeOutputPresentationMode = 0.7;

    // prover remote
    public static final String urlSystemOnTPTP = "http://www.cs.miami.edu/~tptp/cgi-bin/SystemOnTPTP"; // default value, get url from preferences
    public static final String urlSystemOnTPTPFormReply = "http://www.cs.miami.edu/~tptp/cgi-bin/SystemOnTPTPFormReply"; // default value, get url from preferences

    /***************
     * PREFERENCES
     **************/

    // misc
    public static void removePreference(String pref){prefs.remove(pref);}
    private static void flush(){
        try {
            prefs.flush();
        } catch (BackingStoreException e) {
            e.printStackTrace();
        }
    }

    // gui states
    public static boolean getProverFilterEnabled() {
        return prefs.getBoolean("proverFilterEnabled", false);
    }

    public static void setProverFilterEnabled(boolean enabled) {
        prefs.put("proverFilterEnabled", String.valueOf(enabled));
        flush();
    }

    // user actions
    public static ObservableList<String> getRecentlyOpenedFiles(){
        int numFiles = prefs.getInt("recentlyOpenedFilesLen",0);
        List<String> files = new ArrayList<>();
        for (int i = 0; i < numFiles; i++){
            String fileName = prefs.get("recentlyOpenedFile" + i,null);
            if (fileName != null){
                files.add(fileName);
            }
        }
        return FXCollections.observableArrayList(files);
    }
    public static void setRecentlyOpenedFiles(ObservableList<String> files){
        prefs.putInt("recentlyOpenedFilesLen", files.size());
        for (int i = 0; i < files.size(); i++){
            prefs.put("recentlyOpenedFile" + i, files.get(i));
        }
        flush();
    }

    public static String getLastOpenedDirectory() {
        return prefs.get("lastOpenedDirectory", null);
    }
    public static void setLastOpenedDirectory(String path) {
        prefs.put("lastOpenedDirectory", path);
        flush();
    }

    // editor
    public static double getFontSize() {
        return prefs.getDouble("fontSize", fontSizeEditorDefault);
    }
    public static void setFontSize(double arg) {
        prefs.putDouble("fontSize", arg);
        flush();
    }

    // prover remote
    public static String getUrlSystemOnTPTP() {
        return prefs.get("urlSystemOnTPTP", urlSystemOnTPTP);
    }
    public static void setUrlSystemOnTPTP(String arg) {
        prefs.put("urlSystemOnTPTP", arg);
        flush();
    }
    public static String getUrlSystemOnTPTPFormReply() {
        return prefs.get("urlSystemOnTPTPFormRepl", urlSystemOnTPTPFormReply);
    }
    public static void setUrlSystemOnTPTPFormReply(String arg) {
        prefs.put("urlSystemOnTPTPFormRepl", arg);
        flush();
    }
    public static List<ProverConfiguration> getCustomRemoteProvers(){
        int numProvers = prefs.getInt("remoteProversLen",0);
        List<ProverConfiguration> provers = new ArrayList<>();
        for (int i = 0; i < numProvers; i++){
            ProverConfiguration pc = new ProverConfiguration();
            pc.proverName = prefs.get("remoteProverName" + i,null);
            pc.proverCommand = prefs.get("remoteProverCommand" + i,null);
            pc.remoteName = prefs.get("remoteProverSystemOnTPTPName" + i,null);
            String subdialects = prefs.get("remoteProverSubDialects" + i,null);
            List<TPTPDefinitions.TPTPSubDialect> subDialectList = new ArrayList<>();
            for (String subdialectString : subdialects.split(",")){
                try {
                    if (!subdialectString.equals(""))
                        subDialectList.add(TPTPDefinitions.TPTPSubDialect.valueOf(subdialectString));
                } catch (IllegalArgumentException e) {
                    // does not happen
                }
            }
            pc.subDialects = subDialectList;
            provers.add(pc);
        }
        return provers;
    }
    public static void setCustomRemoteProvers(List<ProverConfiguration> provers){
        prefs.putInt("remoteProversLen",provers.size());
        for (int i = 0; i < provers.size(); i++){
            prefs.put("remoteProverName" + i, provers.get(i).proverName);
            prefs.put("remoteProverCommand" + i, provers.get(i).proverCommand);
            prefs.put("remoteProverSystemOnTPTPName" + i, provers.get(i).remoteName);
            String subDialects = String.join(",",
                    provers.get(i).subDialects.stream()
                            .map(Enum::name)
                            .collect(Collectors.toList())
            );
            prefs.put("remoteProverSubDialects" + i, subDialects);
        }
        flush();
    }

    // prover local
    public static List<ProverConfiguration> getLocalProvers(){
        int numProvers = prefs.getInt("localProversLen",0);
        List<ProverConfiguration> provers = new ArrayList<>();
        for (int i = 0; i < numProvers; i++){
            ProverConfiguration pc = new ProverConfiguration();
            pc.proverName = prefs.get("localProverName" + i,null);
            pc.proverCommand = prefs.get("localProverCommand" + i,null);
            String subdialects = prefs.get("localProverSubDialects" + i,null);
            List<TPTPDefinitions.TPTPSubDialect> subDialectList = new ArrayList<>();
            for (String subdialectString : subdialects.split(",")){
                try {
                    if (!subdialectString.equals(""))
                        subDialectList.add(TPTPDefinitions.TPTPSubDialect.valueOf(subdialectString));
                } catch (IllegalArgumentException e) {
                    // does not happen
                }
            }
            pc.subDialects = subDialectList;
            provers.add(pc);
        }
        return provers;
    }
    public static void setLocalProvers(List<ProverConfiguration> provers){
        prefs.putInt("localProversLen",provers.size());
        for (int i = 0; i < provers.size(); i++){
            prefs.put("localProverName" + i, provers.get(i).proverName);
            prefs.put("localProverCommand" + i, provers.get(i).proverCommand);
            String subDialects = String.join(",",
                    provers.get(i).subDialects.stream()
                            .map(Enum::name)
                            .collect(Collectors.toList())
            );
            prefs.put("localProverSubDialects" + i, subDialects);
        }
        flush();
    }

    // Colors
    public static void setColor(String cssName, boolean bg, String cssColor) {
        if(cssColor == null)
            cssColor = "null";

        prefs.put(cssName + (bg ? "_bg" : "_fg"), cssColor);
        flush();
    }

    public static String getColorInternal(String cssName, boolean bg, String def) {
        String ret = prefs.get(cssName + (bg ? "_bg" : "_fg"), def);

        if(ret == "null")
            return null;
        else
            return ret;
    }
}
