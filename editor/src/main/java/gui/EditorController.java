package gui;

import java.io.*;
import java.net.URI;
import java.net.URL;

import java.io.File;
import java.io.StringWriter;
import java.io.IOException;

import java.nio.file.Path;
import java.nio.file.Paths;

import java.net.URISyntaxException;

import java.util.*;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.concurrent.Worker;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.event.ActionEvent;

import javafx.scene.input.KeyEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser;

import com.sun.javafx.webkit.WebConsoleListener;

import netscape.javascript.JSObject;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;
import org.fxmisc.richtext.model.RichTextChange;
import org.fxmisc.richtext.model.PlainTextChange;
import org.fxmisc.richtext.model.StyledText;
import org.fxmisc.richtext.model.StyledDocument;
import org.fxmisc.richtext.model.StyleSpans;
import org.fxmisc.richtext.model.StyleSpan;
import org.fxmisc.richtext.model.Paragraph;

import org.apache.commons.io.IOUtils;

import gui.fileBrowser.FileTreeView;

import parser.ParseContext;

public class EditorController implements Initializable {
    private EditorModel model;
    private Stage mainStage;

    @FXML
    private WebView thfArea;
    @FXML
    private WebView wysArea;
    @FXML
    private FileTreeView fileBrowser;

    JSObject jsDoc = null;
    Document doc = null;

    // DEBUG
    @FXML
    public void debugALG0157()
    {
        model.openStream(getClass().getResourceAsStream("/test/ALG015^7.p"));
    }
    @FXML
    public void debugCOM1601()
    {
        model.openStream(getClass().getResourceAsStream("/test/COM160^1.p"));
    }
    @FXML
    public void debugLCL6331()
    {
        model.openStream(getClass().getResourceAsStream("/test/LCL633^1.p"));
    }
    @FXML
    public void debugLCL6341()
    {
        model.openStream(getClass().getResourceAsStream("/test/LCL634^1.p"));
    }
    @FXML
    public void debugSYN0001()
    {
        model.openStream(getClass().getResourceAsStream("/test/SYN000^1.p"));
    }
    @FXML
    public void debugSYN0002()
    {
        model.openStream(getClass().getResourceAsStream("/test/SYN000^2.p"));
    }
    // DEBUG END

    private int num_updates;

    public EditorController(EditorModel model, Stage mainStage) {
        this.model = model;
        this.mainStage = mainStage;

        num_updates = 0;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /*
        thfArea.setParagraphGraphicFactory(LineNumberFactory.get(thfArea));
        wysArea.setParagraphGraphicFactory(LineNumberFactory.get(wysArea));

        thfArea.setWrapText(true);
        wysArea.setWrapText(true);

        thfArea.plainTextChanges().subscribe(this::onTHFTextChange);
        wysArea.richChanges().subscribe(this::onWYSTextChange);
        */

        WebConsoleListener.setDefaultListener(new WebConsoleListener(){
            @Override
            public void messageAdded(WebView webView, String message, int lineNumber, String sourceId) {
                System.out.println("Console: [" + sourceId + ":" + lineNumber + "] " + message);
            }
        });

        //this.model.thfArea = thfArea;
        //this.model.wysArea = wysArea;

        model.engine = this.thfArea.getEngine();
        model.engine.setJavaScriptEnabled(true);
        //URL cssURL = getClass().getResource("/gui/editorField.css");
        //model.engine.setUserStyleSheetLocation(cssURL.toString());
        //InputStream cssInputStream = ClassLoader.getSystemResourceAsStream("gui/editorField.css");
        //String css = new BufferedReader(new InputStreamReader(cssInputStream)).lines().collect(Collectors.joining("\n"));



        model.engine.getLoadWorker().stateProperty().addListener(
                new ChangeListener<Worker.State>()
                {
                    @Override
                    public void changed(ObservableValue ov, Worker.State oldState, Worker.State newState)
                    {
                        if(newState == Worker.State.SUCCEEDED)
                        {
                            doc = model.engine.getDocument();
                            model.doc = model.engine.getDocument();
                            model.style = new WebKitStyle();
                            model.style.setDoc(doc);
                            model.style.updateCss();

                            System.out.println("doc = " + doc);

                            if(doc == null)
                                return;

                            jsDoc = (JSObject) doc;

                            Integer sel = (Integer) jsDoc.eval("getSelection().anchorOffset");
                            System.out.println("selection = " + sel);
                        }
                    }
                }
        );

        engine.setOnAlert(t -> System.out.println(t));
        engine.setOnError(e -> System.out.println(e.getMessage()));

        try
        {
            engine.loadContent(
                IOUtils.toString(getClass().getResourceAsStream("/gui/editor.html"), "UTF-8")
            );
        }
        catch(IOException ex)
        {
            /* TODO */
            ex.printStackTrace();
        }

        // Element el = engine.getDocument().getElementById("content");
        // System.out.println("" + sel);

        model.updateStyle();
    }

    @FXML
    private void onFileNew(ActionEvent e) {
        System.out.println("newfile");
    }


    @FXML
    private void onDirectoryOpen(ActionEvent e) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Open directory");
        File dir = directoryChooser.showDialog(mainStage);
        if(dir == null)
            return;
        //RootDirItem rootDirItem = ResourceItem.createObservedPath(dir.toPath());
        //fileBrowser.setRootDirectories(FXCollections.observableArrayList(rootDirItem));
        fileBrowser.openDirectory(dir);
        //model.openDirectory(dir);
    }

    @FXML
    private void onFileOpen(ActionEvent e) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open thf file");
        File selectedFile = fileChooser.showOpenDialog(mainStage);

        if(selectedFile == null)
            return;

        model.openFile(selectedFile);
    }

    @FXML
    private void onFileSave(ActionEvent e) {
        System.out.println("savefile");
    }

    @FXML
    private void onFileExit(ActionEvent e) {
        System.exit(0);
    }

    @FXML
    private void onTestPref(ActionEvent e)
    {
        if(Config.getFont().equals("monospace"))
            Config.setFont("xos4 Terminus");
        else
            Config.setFont("monospace");

        model.updateStyle();

        Integer sel = (Integer) jsDoc.eval("getSelection().anchorOffset");
        System.out.println("selection = " + sel);

        StringBuilder content = new StringBuilder();
        Stack<Node> nodes = new Stack();
        nodes.push(doc.getFirstChild());

        while(!nodes.empty())
        {
            Node n = nodes.pop();

            if(n instanceof Text)
            {
                Text t = (Text)n;
                content.append(t.getWholeText());
            }

            NodeList list = n.getChildNodes();
            for(int i = list.getLength(); i > 0; --i)
                nodes.push(list.item(i-1));

            if(n instanceof Element)
            {
                Element el = (Element)n;
                System.out.println("tag_name = '" + el.getTagName() + "'");
                System.out.println("class = '" + el.getAttribute("class") + "'");

                if(el.getTagName().equals("DIV") && el.getAttribute("class") == null)
                    content.append("\n");
            }
        }

        System.out.print("content = '");
        System.out.print(content.toString());
        System.out.println("'");

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

    @FXML
    private void onReparse(ActionEvent e)
    {
        model.reparse();
    }

    @FXML
    private void onPrintTree(ActionEvent e)
    {
        model.printTPTPTrees();
    }

    @FXML
    private void onTHFTextChange(PlainTextChange change)
    {
        if(change.getInserted().equals(change.getRemoved()))
            return;

        //System.out.println("inserted = " + change.getInserted().getText());
        //System.out.println("removed  = " + change.getRemoved().getText());

        model.updateTHFTree(change.getPosition(), change.getInsertionEnd(), change.getRemovalEnd());
    }

    @FXML
    private void onWYSTextChange(RichTextChange<Collection<String>,StyledText<Collection<String>>,Collection<String>> change)
    {
        System.out.println("wysiwyg change");
    }
}
