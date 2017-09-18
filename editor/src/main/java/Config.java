package gui; /* TODO: Change the package hierarchy, put this one above gui. */

import java.util.prefs.Preferences;

public class Config
{
    public static String name = "editor";

    private static Preferences prefs = Preferences.userNodeForPackage(Config.class);

    public static String getFont() { return prefs.get("font", "monospace"); }
    public static double getFontSize() { return prefs.getDouble("fontSize", 12); }

    public static void setFont(String arg) { prefs.put("font", arg); }
    public static void setFontSize(double arg) { prefs.putDouble("fontSize", arg); }
}
