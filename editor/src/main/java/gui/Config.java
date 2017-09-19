package gui; /* TODO: Change the package hierarchy, put this one above gui. */

import java.util.prefs.Preferences;

public class Config {
    private static Preferences prefs = Preferences.userNodeForPackage(Config.class);

    // common
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

    // prover
    public static String getUrlSystemOnTPTP() {
        return prefs.get("urlSystemOnTPTP", "http://www.cs.miami.edu/~tptp/cgi-bin/SystemOnTPTP");
    }

    public static String getUrlSystemOnTPTPFormReply() {
        return prefs.get("UrlSystemOnTPTPFormRepl", "http://www.cs.miami.edu/~tptp/cgi-bin/SystemOnTPTPFormReply");
    }

    public static void setUrlSystemOnTPTP() {
        prefs.put("urlSystemOnTPTP", "http://www.cs.miami.edu/~tptp/cgi-bin/SystemOnTPTP");
    }

    public static void setUrlSystemOnTPTPFormReply() {
        prefs.put("UrlSystemOnTPTPFormRepl", "http://www.cs.miami.edu/~tptp/cgi-bin/SystemOnTPTPFormReply");
    }
}
