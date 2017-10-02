package gui;

import java.io.IOException;

import java.nio.file.Path;

import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import javafx.concurrent.Worker;

import javafx.scene.control.Tab;
import javafx.scene.web.WebView;

import org.apache.commons.io.IOUtils;

import netscape.javascript.JSObject;

import gui.EditorDocument;
import gui.EditorDocumentModel;

public class EditorDocumentViewController
{
    private WebView editor;
    private Tab tab;

    public EditorDocument doc;
    public EditorDocumentModel model;

    public class JSCallbackListener
    {
        private EditorDocumentModel model;
        public JSCallbackListener(EditorDocumentModel model)
        {
            this.model = model;
        }
        public int start_parsing(int startNode, int endNode)
        {
            try
            {
                return model.reparseArea(startNode, endNode);
            }
            catch(Throwable e)
            {
                e.printStackTrace();
                throw e;
            }
        }
        public void debug(String str)
        {
            System.out.println("DEBUG = " + str);
        }
        public void sleep(Integer ms) {
            try {Thread.sleep(ms.longValue()); }
            catch(InterruptedException e) {}
        }
    }

    private JSCallbackListener jsCallbackListener;

    public EditorDocumentViewController(Path path)
    {
        this.editor = new WebView();

        this.tab = new Tab();
        if(path == null)
            this.tab.setText("unnamed");
        else
            this.tab.setText(path.getFileName().toString());
        this.tab.setContent(this.editor);
        this.tab.setUserData(this);

        this.editor.getEngine().setJavaScriptEnabled(true);

        this.model = null;
        this.jsCallbackListener = null;

        this.editor.getEngine().getLoadWorker().stateProperty().addListener(
            new ChangeListener<Worker.State>()
            {
                @Override
                public void changed(ObservableValue ov, Worker.State oldState, Worker.State newState)
                {
                    if(newState == Worker.State.SUCCEEDED)
                    {
                        model = new EditorDocumentModel(editor.getEngine(), EditorDocumentViewController.this);
                        jsCallbackListener = new JSCallbackListener(model);

                        JSObject window = (JSObject) editor.getEngine().executeScript("window");
                        window.setMember("java", jsCallbackListener);
                    }
                }
            }
        );

        try
        {
            editor.getEngine().loadContent(
                IOUtils.toString(getClass().getResourceAsStream("/gui/editor.html"), "UTF-8")
            );
        }
        catch(IOException ex)
        {
            /* TODO */
            ex.printStackTrace();
        }

        this.doc = new EditorDocument(path);
    }

    public void setText(String text)
    {
        this.tab.setText(text);
    }

    public void addSelf(List<Tab> tabs)
    {
        tabs.add(this.tab);
    }
}
