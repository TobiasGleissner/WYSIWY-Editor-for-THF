package gui.fileBrowser;

import java.io.File;
import java.util.List;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.scene.control.TreeItem;

import jiconfont.icons.FontAwesome;
import jiconfont.javafx.IconNode;

/**
 * @author Alexander Bolte - Bolte Consulting (2010 - 2014).
 *
 *         This class shall be a simple implementation of a TreeItem for
 *         displaying a file system tree.
 *
 *         The idea for this class is taken from the Oracle API docs found at
 *         http
 *         ://docs.oracle.com/javafx/2/api/javafx/scene/control/TreeItem.html.
 *
 *         Basically the file sytsem will only be inspected once. If it changes
 *         during runtime the whole tree would have to be rebuild. Event
 *         handling is not provided in this implementation.
 */
public class FileTreeItem extends TreeItem<FileWrapper> implements Comparable<FileTreeItem> {

    static FontAwesome iconFile = FontAwesome.FILE_O;
    static FontAwesome iconTPTP = FontAwesome.FILE_CODE_O;
    static FontAwesome iconDirectory = FontAwesome.FOLDER_O;
    File file;

    /**
     * Calling the constructor of super class in oder to create a new
     * TreeItem<File>.
     *
     * @param f
     *            an object of type File from which a tree should be build or
     *            which children should be gotten.
     */
    public FileTreeItem(FileWrapper f) {
        super(f);
    }

    /*
     * (non-Javadoc)
     *
     * @see javafx.scene.control.TreeItem#getChildren()
     */
    @Override
    public ObservableList<TreeItem<FileWrapper>> getChildren() {
        if (isFirstTimeChildren) {
            isFirstTimeChildren = false;

            /*
             * First getChildren() call, so we actually go off and determine the
             * children of the File contained in this TreeItem.
             */
            super.getChildren().setAll(buildChildren(this));
        }
        return super.getChildren();
    }

    /*
     * (non-Javadoc)
     *
     * @see javafx.scene.control.TreeItem#isLeaf()
     */
    @Override
    public boolean isLeaf() {
        if (isFirstTimeLeaf) {
            isFirstTimeLeaf = false;
            //File f = getValue();
            isLeaf = getValue().f.isFile();
        }

        return isLeaf;
    }

    /**
     * Returning a collection of type ObservableList containing TreeItems, which
     * represent all children available in handed TreeItem.
     *
     * @param TreeItem
     *            the root node from which children a collection of TreeItem
     *            should be created.
     * @return an ObservableList<TreeItem<File>> containing TreeItems, which
     *         represent all children available in handed TreeItem. If the
     *         handed TreeItem is a leaf, an empty list is returned.
     */
    private ObservableList<TreeItem<FileWrapper>> buildChildren(TreeItem<FileWrapper> TreeItem) {
        File f = getValue().f;
        if (f != null && f.isDirectory()) {
            File[] files = f.listFiles();
            if (files != null) {
                ObservableList<TreeItem<FileWrapper>> children = FXCollections.observableArrayList();
                for (File childFile : files) {
                    TreeItem<FileWrapper> a = new FileTreeItem(new FileWrapper(childFile));
                    a.setGraphic(getIconNodeByFile(childFile));
                    children.add(a);
                }
                return children;
            }
        }
        return FXCollections.emptyObservableList();
    }

    public IconNode getIconNodeByFile(File f){
        IconNode iconNode;
        if (f.isDirectory()) {
            iconNode = new IconNode(iconDirectory);
        } else if (f.isFile()) {
            if (f.getName().endsWith(".p")){
                iconNode = new IconNode(iconTPTP);
            }
            else
                iconNode = new IconNode(iconFile);
        } else {
            iconNode = new IconNode();
        }
        iconNode.getStyleClass().add("filebrowser-icon");
        return iconNode;
    }
    
    /**
     * Sort children of a FileTreeItem lexicographically (separately for folders and files).
     * @param alsoChildrenOfChildren: Sort also the children of the children.
     */
    public void sortChildren(Boolean alsoChildrenOfChildren) {
        List<TreeItem<FileWrapper>> list = this.getChildren();
        
        if (list.size() == 0)
            return;
        Collections.sort(list,
            (lhs, rhs) ->
            {
                if(!(lhs instanceof FileTreeItem) &&
                   !(rhs instanceof FileTreeItem))
                    return lhs.getValue().toString().compareTo(rhs.getValue().toString());

                if(!(lhs instanceof FileTreeItem))
                    return -1;
                if(!(rhs instanceof FileTreeItem))
                    return 1;

                FileTreeItem lhs_ = (FileTreeItem)lhs;
                FileTreeItem rhs_ = (FileTreeItem)rhs;

                return lhs_.compareTo(rhs_);
            }
        );
        
        if (alsoChildrenOfChildren) {
            for (TreeItem<FileWrapper> child : list) {
                ((FileTreeItem) child).sortChildren(true);
            }
        }
    }
    
    @Override
    public int compareTo(FileTreeItem other) {
        if (this.getValue().f.isDirectory() && other.getValue().f.isDirectory() || !this.getValue().f.isDirectory() && !other.getValue().f.isDirectory()) {
            return this.getValue().f.getName().compareTo(other.getValue().f.getName());
        }
        if (this.getValue().f.isDirectory()) {
            return -1;
        } else {
            return 1;
        }
            
    }

    private boolean isFirstTimeChildren = true;
    private boolean isFirstTimeLeaf = true;
    private boolean isLeaf;


}
