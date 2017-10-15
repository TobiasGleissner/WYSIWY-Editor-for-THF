package gui;

import javafx.scene.web.WebEngine;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import prover.Prover;
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
    private Node defaultTableNode;
    private Node proverTableNode;
    private Node debugTableNode;
    private Document doc;
    private double fontSizeOutput;
    public WebEngine outputEngine;

    private Logging(){}

    public static Logging getInstance(){
        if (instance == null) instance = new Logging();
        return instance;
    }

    public void init(){
        this.doc = outputEngine.getDocument();
        this.defaultTableNode = doc.getElementById("default_table");
        this.proverTableNode = doc.getElementById("prover_table");
        this.debugTableNode = doc.getElementById("debug_table");
        this.fontSizeOutput = Config.fontSizeOutputDefault;
        setFontSize(Config.fontSizeOutputDefault);
    }

    private Node getLevelNode(LogLevel logLevel) {
        switch(logLevel)
        {
            case PROVER: return this.proverTableNode;
            case DEBUG:  return this.debugTableNode;
            default:     return this.defaultTableNode;
        }
    }

    public void log(String msg,LogLevel logLevel){
        getLevelNode(logLevel).appendChild(createRecord(msg, logLevel));
        scroll();
    }

    public void prover(ProvingEntry p){
        Element tr = createRecordSkeleton(LogLevel.PROVER);
        Element messageContainer = doc.createElement("td");
        tr.appendChild(messageContainer);
        Element status = doc.createElement("span");
        status.setAttribute("class","szsstatus");
        status.setTextContent(p.proveResult.status.name());
        messageContainer.appendChild(status);
        Element message = doc.createElement("span");
        StringBuilder sb = new StringBuilder(200);
        sb.append(" cpu=");
        sb.append(p.proveResult.cpu);
        sb.append(" wc=");
        sb.append(p.proveResult.wc);
        sb.append(" timeLimit=");
        sb.append(p.proveResult.timelimit);
        sb.append(" by ");
        sb.append(Prover.getNiceProverTypeName(p.proveResult.proverType));
        sb.append(" ");
        sb.append(p.proveResult.prover);
        message.setTextContent(sb.toString());
        messageContainer.appendChild(message);
        scroll();
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
        Element tr = createRecordSkeleton(logLevel);
        Element message = doc.createElement("td");
        String[] lines = msg.split("\n");
        for (int i = 0; i < lines.length; i++) {
            message.appendChild(doc.createTextNode(lines[i]));
            if (i < lines.length - 1 ) message.appendChild(doc.createElement("br"));
        }
        tr.appendChild(message);
        return tr;
    }

    private Element createRecordSkeleton(LogLevel logLevel){
        Element tr = doc.createElement("tr");
        getLevelNode(logLevel).appendChild(tr);
        Element loglvl = doc.createElement("td");
        loglvl.setAttribute("class",logLevel.name().toLowerCase());
        loglvl.setTextContent(logLevel.name());
        tr.appendChild(loglvl);
        Element time = doc.createElement("td");
        time.setTextContent(getCurrentTime());
        tr.appendChild(time);
        return tr;
    }

    private String getCurrentTime(){
        //SimpleDateFormat date_format = new SimpleDateFormat("MMM dd,yyyy HH:mm:ss");
        SimpleDateFormat date_format = new SimpleDateFormat("HH:mm:ss");
        return date_format.format(new Date());
    }

    public void setFontSize(double fontSizeOutput){
        this.fontSizeOutput = fontSizeOutput;
        Element style = doc.getElementById("fontSizeStyle");
        StringBuilder sb = new StringBuilder();

        sb.append("*{\n");
        sb.append("font-size: ");
        sb.append(fontSizeOutput);
        sb.append("cm;\n");
        sb.append("}\n");

        String st = sb.toString();
        style.setTextContent(st);
    }

    private void scroll(){
        outputEngine.executeScript("window.scrollTo(0, document.body.scrollHeight);");
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
