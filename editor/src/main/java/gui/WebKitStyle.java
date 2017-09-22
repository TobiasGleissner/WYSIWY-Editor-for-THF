package gui;

import javafx.scene.web.WebEngine;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class WebKitStyle {

    private  Document doc;
    Element style;
    static String defaultCss;

    static{
        InputStream cssInputStream = ClassLoader.getSystemResourceAsStream("gui/editorField.css");
        defaultCss = new BufferedReader(new InputStreamReader(cssInputStream)).lines().collect(Collectors.joining("\n"));
    }

    public WebKitStyle(){}


    public void setDoc(Document doc){
        this.doc = doc;
    }
    public void updateCss(){
        System.out.println("asdasd");
        style = doc.getElementById("style");
        String st = "*{color:green;}\n";//+defaultCss;
        System.out.println(st);
        style.setTextContent(st);
    }



}
