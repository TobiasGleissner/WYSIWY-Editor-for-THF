package gui;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class WebKitStyle {

    private Document doc;
    private double fontSize;

    private static final double fontSizeIncrementStep = Config.fontSizeIncrementStep;
    private static final double fontSizePresentationMode = Config.fontSizePresentationMode;
    private static String defaultCss;
    static{
        InputStream cssInputStream = ClassLoader.getSystemResourceAsStream("gui/editorField.css");
        defaultCss = new BufferedReader(new InputStreamReader(cssInputStream)).lines().collect(Collectors.joining("\n"));
    }

    public WebKitStyle(){
        this.fontSize = Config.getFontSize();
    }

    public void setDoc(Document doc){
        this.doc = doc;
        updateCss();
    }

    private void updateCss(){
        Element style = doc.getElementById("style");
        StringBuilder sb = new StringBuilder();

        sb.append("*{\n");
        sb.append("font-size: ");
        sb.append(fontSize);
        sb.append("em;\n");
        sb.append("}\n");
        sb.append(defaultCss);

        String st = sb.toString();
        System.out.println(st);
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

    public void incrementFontSize(){
        this.setFontSize(this.fontSize + fontSizeIncrementStep);
    }

    public void decrementFontSize(){
        this.setFontSize(this.fontSize - fontSizeIncrementStep);
    }

    public void setFontSizeToPresentationMode(){
        this.setFontSize(fontSizePresentationMode);
    }





}
