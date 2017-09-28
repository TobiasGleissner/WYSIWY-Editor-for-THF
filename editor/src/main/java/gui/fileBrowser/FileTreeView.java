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
    
    /**
     *  Not yet used and tested!
     *  This method updates the complete subtree with item as root and replaces item by the new tree.
     * @param item
     * @param root
     * @param f: The directory which shall be updated
     */
    public static void updateTree(TreeItem<FileWrapper> item, TreeItem<FileWrapper> root, File f) {
        if (item == root) {
            return;
        }
        
        FileTreeView temp = new FileTreeView();
        temp.openDirectory(f);
        TreeItem<FileWrapper> tempRoot = temp.getRoot();
        temp.setRoot(root);
        TreeItem<FileWrapper> parent = item.getParent();
        int i = 0;
        for (TreeItem<FileWrapper> child : parent.getChildren()) {
            if (child == item) {
                parent.getChildren().set(i, tempRoot);
                break;
            }
            i++;
        }
        
    }
    
}
