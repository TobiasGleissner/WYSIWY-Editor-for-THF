package gui.fileBrowser;

import javafx.scene.control.TreeView;
import javafx.scene.control.TreeItem;

import java.io.File;

import jiconfont.javafx.IconNode;

public class FileTreeView extends TreeView<FileWrapper> {
    public FileTreeView(){
        super();
    }

    public FileTreeView(File f){
        super(new FileTreeItem(new FileWrapper(f)));
    }

    public void openDirectory(File f){
        //this.getChildren().clear();
        TreeItem<FileWrapper> root = new FileTreeItem(new FileWrapper(f));
        IconNode icon = new IconNode(FileTreeItem.iconDirectory);
        icon.getStyleClass().add("filebrowser-icon");
        root.setGraphic(icon);
        this.setRoot(root);
    }
}
