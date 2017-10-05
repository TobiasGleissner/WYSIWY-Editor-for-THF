package gui;

import java.nio.file.Path;

import java.util.List;

import javafx.scene.control.Tab;
import javafx.scene.web.WebView;

public class EditorDocumentViewController
{
    private List<Tab> tabs;
    public Tab tab;
    public WebView editor;

    public EditorDocumentModel model;

    public EditorDocumentViewController(Path path, List<Tab> tabs, EditorController editorController)
    {
        this.editor = new WebView();
        this.editor.setContextMenuEnabled(false);
        this.editor.getEngine().setJavaScriptEnabled(true);

        this.tab = new Tab();
        if(path == null)
            this.tab.setText("unnamed");
        else
            this.tab.setText(path.getFileName().toString());
        this.tab.setContent(this.editor);
        this.tab.setUserData(this);

        this.model = new EditorDocumentModel(editor.getEngine(), EditorDocumentViewController.this, editorController);

        this.tabs = tabs;
        this.tabs.add(this.tab);

        this.tab.setOnCloseRequest(
                e-> {model.close();}
                /*
            e ->
            {
                // Don't close the last tab.
                if(this.tabs.size() <= 1)
                {
                    if(this.model != null)
                    {
                        this.model = new EditorDocumentModel(editor.getEngine(), EditorDocumentViewController.this);
                    }

                    e.consume();
                }
            }
            */
        );
    }

    public void setText(String text)
    {
        this.tab.setText(text);
    }
}
