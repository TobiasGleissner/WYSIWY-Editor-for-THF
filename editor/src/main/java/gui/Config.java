package gui; /* TODO: Change the package hierarchy, put this one above gui. */

import java.util.Arrays;
import java.util.List;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Config {
    public static Preferences prefs = Preferences.userNodeForPackage(Config.class);

    // misc
    public static final String name = "editor";
    public static final String USER_AGENT = "thf_editor";
    public static int maxRecentlyOpenedFiles = 20;

    // editor
    public static final double fontSizeIncrementStep = 0.1;
    public static final double fontSizeDefault = 0.4;
    public static final double fontSizePresentationMode = 1.0;

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

    // user actions
    public static ObservableList<String> getRecentlyOpenedFiles(){
        String files = prefs.get("recentlyOpenedFiles","");
        ObservableList<String> list = FXCollections.observableArrayList(Arrays.asList(files.split(",")));
        return list;
    }
    public static void setRecentlyOpenedFiles(ObservableList<String> files){
        prefs.put("recentlyOpenedFiles", String.join(",",files));
        flush();
    }

    // editor
    public static double getFontSize() {
        return prefs.getDouble("fontSize", fontSizeDefault);
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

    // prover local
    public static List<String> getLocalProvers(){
        String provers = prefs.get("localProverList","");
        List<String>  list = Arrays.asList(provers.split(","));
        return list.stream()
                .filter(n->n.length() >= 6).filter(n-> n.substring(0,6).equals("prover"))
                .map(n -> n.substring(6)).collect(Collectors.toList());
    }
    public static void setLocalProvers(List<String> provers){
        prefs.put("localProverList", String.join(",",provers.stream().map(n->"prover"+n).collect(Collectors.toList())));
        flush();
    }

    public static String getLocalProverCommand(String prover) {
        return prefs.get("prover" + prover,null);
    }
    public static void setLocalProverCommand(String prover, String command) {
        prefs.put("prover" + prover, command);
        flush();
    }

}
