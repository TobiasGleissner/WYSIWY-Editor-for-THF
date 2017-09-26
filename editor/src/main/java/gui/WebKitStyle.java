package gui;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class WebKitStyle {

    private Document doc;
    private Document outputDoc;
    private double fontSize;

    private static final double fontSizeIncrementStep = Config.fontSizeIncrementStep;
    private static final double fontSizePresentationMode = Config.fontSizePresentationMode;
    private static String defaultCss;
    static{
        InputStream cssInputStream = WebKitStyle.class.getResourceAsStream("/gui/editorField.css");
        defaultCss = new BufferedReader(new InputStreamReader(cssInputStream)).lines().collect(Collectors.joining("\n"));
        cssInputStream = WebKitStyle.class.getResourceAsStream("/gui/editorHighlighting.css");
        defaultCss += new BufferedReader(new InputStreamReader(cssInputStream)).lines().collect(Collectors.joining("\n"));
    }

    public WebKitStyle(){
        this.fontSize = Config.getFontSize();
    }

    public void setDoc(Document doc){
        this.doc = doc;
        updateCss();
    }

    public void setOutputDoc(Document doc){
        this.outputDoc = doc;
        updateCssOutputDoc();
    }

    private void updateCssOutputDoc() {
        Element style = outputDoc.getElementById("style");
        StringBuilder sb = new StringBuilder();

        sb.append("*{\n");
        sb.append("font-size: ");
        sb.append(fontSize);
        sb.append("cm;\n");
        sb.append("}\n");
        //sb.append(defaultCss);

        String st = sb.toString();
        //System.out.println(st);
        style.setTextContent(st);
    }

    private void updateCss(){
        Element style = doc.getElementById("style");
        StringBuilder sb = new StringBuilder();

        sb.append("*{\n");
        sb.append("font-size: ");
        sb.append(fontSize);
        sb.append("cm;\n");
        sb.append("}\n");
        sb.append(defaultCss);

        String st = sb.toString();
        //System.out.println(st);
        style.setTextContent(st);
    }

    public void setFontSize(double fontSize){
        if (fontSize <= 0){
            System.err.println("Invalid fontSize: " + fontSize);
            return;
        }
        this.fontSize = fontSize;
        this.updateCss();
    }

    public void increaseFontSize(){
        this.setFontSize(this.fontSize + fontSizeIncrementStep);
    }

    public void decreaseFontSize(){
        this.setFontSize(this.fontSize - fontSizeIncrementStep);
    }

    public void setFontSizeToPresentationMode(){
        this.setFontSize(fontSizePresentationMode);
    }





}
