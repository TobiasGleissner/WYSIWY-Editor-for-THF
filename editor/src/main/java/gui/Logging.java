package gui;

import javafx.scene.web.WebEngine;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import prover.ProveResult;
import prover.ProvingEntry;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Collectors;

public class Logging {
    public enum LogLevel{DEBUG,PROVER,FINE,INFO,WARNING,ERROR}
    private static Logging instance;
    private Node tableNode;
    private Document doc;
    private double fontSizeOutput;

    private static String defaultCss;
    static {
        InputStream cssInputStream = WebKitStyle.class.getResourceAsStream("/gui/editorField.css");
        defaultCss = new BufferedReader(new InputStreamReader(cssInputStream)).lines().collect(Collectors.joining("\n"));
    }

    private Logging(){}

    public WebEngine outputEngine;

    public static Logging getInstance(){
        if (instance == null) instance = new Logging();
        return instance;
    }

    public void init(){
        this.doc = outputEngine.getDocument();
        this.tableNode = doc.getElementById("table");
        this.fontSizeOutput = Config.fontSizeOutputDefault;
        updateCssOutputDoc();
    }

    public void log(String msg,LogLevel logLevel){
        tableNode.appendChild(createRecord(msg,logLevel));
    }

    public void prover(ProvingEntry p){
        // TODO
        this.prover(p.proveResult);
    }

    public void prover(ProveResult p){
        // TODO
        this.prover(p.toString());
    }

    public void prover(String msg){
        log(msg,LogLevel.PROVER);
    }

    public void debug(String msg){
        log(msg,LogLevel.DEBUG);
    }

    public void fine(String msg){
        log(msg,LogLevel.FINE);
    }

    public void info(String msg){
        log(msg,LogLevel.INFO);
    }

    public void warning(String msg){
        log(msg,LogLevel.WARNING);
    }

    public void error(String msg){
        log(msg,LogLevel.ERROR);
    }

    private Node createRecord(String msg, LogLevel logLevel){
        Element tr = doc.createElement("tr");
        tableNode.appendChild(tr);
        Element loglvl = doc.createElement("td");
        loglvl.setAttribute("class",logLevel.name().toLowerCase());
        loglvl.setTextContent(logLevel.name());
        tr.appendChild(loglvl);
        Element time = doc.createElement("td");
        time.setTextContent(getCurrentTime());
        tr.appendChild(time);
        Element message = doc.createElement("td");
        message.setTextContent(msg);
        tr.appendChild(message);
        return tr;
    }

    private String getCurrentTime(){
            SimpleDateFormat date_format = new SimpleDateFormat("MMM dd,yyyy HH:mm:ss");
            return date_format.format(new Date());
    }

    private void updateCssOutputDoc() {
        Element style = doc.getElementById("style");
        StringBuilder sb = new StringBuilder();

        sb.append("*{\n");
        sb.append("font-size: ");
        sb.append(fontSizeOutput);
        sb.append("cm;\n");
        sb.append("}\n");
        sb.append(style.getTextContent());

        String st = sb.toString();
        style.setTextContent(st);
    }

    // for debugging
    private void printHtml(){
        try
        {
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer();
            DOMSource source = new DOMSource(doc);

            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);

            transformer.transform(source, result);

            System.out.print("content = '");
            System.out.print(writer.toString());
            System.out.println("'");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
