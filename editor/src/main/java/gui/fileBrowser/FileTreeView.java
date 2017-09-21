package gui.fileBrowser;

import javafx.scene.control.TreeView;
import javafx.scene.image.ImageView;

import java.io.File;

public class FileTreeView extends TreeView<FileWrapper> {
    public FileTreeView(){
        super();
    }

    public FileTreeView(File f){
        super(new FileTreeItem(new FileWrapper(f)));
    }

    public void openDirectory(File f){
        //this.getChildren().clear();
        this.setRoot(new FileTreeItem(new FileWrapper(f),new ImageView(FileTreeItem.imageDirectory)));
    }

}
