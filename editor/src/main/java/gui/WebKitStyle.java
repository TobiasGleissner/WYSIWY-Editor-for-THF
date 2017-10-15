package gui;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class WebKitStyle {

    private Document doc;
    private double fontSizeEditor;

    private static final double fontSizeIncrementStep = Config.fontSizeIncrementStep;
    private static final double fontSizePresentationMode = Config.fontSizeEditorPresentationMode;
    private static String defaultCss;
    static{
        InputStream cssInputStream = WebKitStyle.class.getResourceAsStream("/gui/editorField.css");
        defaultCss = new BufferedReader(new InputStreamReader(cssInputStream)).lines().collect(Collectors.joining("\n"));
    }

    public WebKitStyle(){
        this.fontSizeEditor = Config.getFontSize();
    }

    public void setDoc(Document doc){
        this.doc = doc;
        updateCss();
    }

    public void updateCss(){
        Element style = doc.getElementById("style");
        StringBuilder sb = new StringBuilder();

        sb.append("*{\n");
        sb.append("font-size: ");
        sb.append(fontSizeEditor);
        sb.append("cm;\n");
        sb.append("}\n");

        for(HighlightingStyle t : HighlightingStyle.values())
        {
            sb.append("." + t.getCssName() + " {\n");

            if(t.getColor(false) != null)
                sb.append("    color: " + t.getColor(false) + ";\n");
            if(t.getColor(true) != null)
                sb.append("    background-color: " + t.getColor(true) + ";\n");

            sb.append("}\n");
        }

        sb.append(defaultCss);

        String st = sb.toString();
        //System.out.println(st);
        style.setTextContent(st);
    }

    public void setFontSizeEditor(double fontSizeEditor){
        if (fontSizeEditor <= 0){
            System.err.println("Invalid fontSizeEditor: " + fontSizeEditor);
            return;
        }
        this.fontSizeEditor = fontSizeEditor;
        this.updateCss();
    }

    public void increaseFontSize(){
        this.setFontSizeEditor(this.fontSizeEditor + fontSizeIncrementStep);
        Config.setFontSize(this.fontSizeEditor);
    }

    public void decreaseFontSize(){
        this.setFontSizeEditor(this.fontSizeEditor - fontSizeIncrementStep);
        Config.setFontSize(this.fontSizeEditor);
    }

    public void setFontSizeToPresentationMode(){
        this.setFontSizeEditor(fontSizePresentationMode);
    }

    public void setDefaultFontSize(){
        this.setFontSizeEditor(Config.fontSizeEditorDefault);
        Config.setFontSize(this.fontSizeEditor);
    }



}
