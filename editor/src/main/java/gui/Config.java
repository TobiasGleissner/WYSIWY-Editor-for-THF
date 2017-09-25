package gui; /* TODO: Change the package hierarchy, put this one above gui. */

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;
import java.util.stream.Collectors;

public class Config {
    private static Preferences prefs = Preferences.userNodeForPackage(Config.class);

    // common
    public static void removePreference(String pref){prefs.remove(pref);}
    public static String name = "editor";
    public static String USER_AGENT = "thf_editor";

    // gui
    public static String getFont() {
        return prefs.get("font", "monospace");
    }
    public static double getFontSize() {
        return prefs.getDouble("fontSize", 12);
    }

    public static void setFont(String arg) {
        prefs.put("font", arg);
    }
    public static void setFontSize(double arg) {
        prefs.putDouble("fontSize", arg);
    }

    // user actions
    public static int maxRecentlyOpenedFiles = 20;
    public static ArrayList<String> getRecentlyOpenedFiles(){
        String files = prefs.get("recentlyOpenedFiles","");
        List<String> list = Arrays.asList(files.split(","));
        return new ArrayList<>(list.stream().map(n -> n.substring(6)).collect(Collectors.toList()));
    }
    public static void setRecentlyOpenedFiles(List<String> files){
        prefs.put("localProverList",files.stream().map(p->"prover" + p).reduce(",",String::concat));
    }

    // prover remote
    public static String getUrlSystemOnTPTP() {
        return prefs.get("urlSystemOnTPTP", "http://www.cs.miami.edu/~tptp/cgi-bin/SystemOnTPTP");
    }
    public static String getUrlSystemOnTPTPFormReply() {
        return prefs.get("urlSystemOnTPTPFormRepl", "http://www.cs.miami.edu/~tptp/cgi-bin/SystemOnTPTPFormReply");
    }

    public static void setUrlSystemOnTPTP(String arg) {
        prefs.put("urlSystemOnTPTP", arg);
    }
    public static void setUrlSystemOnTPTPFormReply(String arg) {
        prefs.put("urlSystemOnTPTPFormRepl", arg);
    }

    // prover local
    public static List<String> getLocalProvers(){
        String provers = prefs.get("localProverList","proversatallax");
        List<String>  list = Arrays.asList(provers.split(","));
        return list.stream()
                .filter(n->n.length() >= 6).filter(n-> n.substring(0,6).equals("prover"))
                .map(n -> n.substring(6)).collect(Collectors.toList());
    }
    public static void setLocalProvers(List<String> provers){
        prefs.put("localProverList",provers.stream().map(p->"prover" + p).reduce(",",String::concat));
    }

    public static String getLocalProverCommand(String prover) {
        switch(prover){
            case "satallax": return prefs.get("proversatallax", "satallax -t %t %f");
        }
        return prefs.get("prover" + prover,null);
    }
    public static void setLocalProverCommand(String prover, String command) {
        prefs.put(prover, command);
    }

}
