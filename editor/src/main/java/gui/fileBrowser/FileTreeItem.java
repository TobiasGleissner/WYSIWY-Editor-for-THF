package gui.fileBrowser;

import java.io.File;
import java.io.InputStream;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
public class FileTreeItem extends TreeItem<FileWrapper> {

    static Image imageFile;
    static Image imageTPTP;
    static Image imageDirectory;
    static{
        InputStream image = FileTreeItem.class.getResourceAsStream("/gui/images/fileBrowser/defaultFile.png");
        imageFile = new Image(image, 10, 10, false, false);
        image = FileTreeItem.class.getResourceAsStream("/gui/images/fileBrowser/TPTP.png");
        imageTPTP = new Image(image, 10, 10, false, false);
        image = FileTreeItem.class.getResourceAsStream("/gui/images/fileBrowser/folder.png");
        imageDirectory = new Image(image, 10, 10, false, false);
    }
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
        //this.file = f;
    }

    public FileTreeItem(FileWrapper f, ImageView i) {
        super(f,i);
        //this.file = f;
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
                    TreeItem a = new FileTreeItem(new FileWrapper(childFile),getImageViewByFile(childFile));
                    children.add(a);
                }
                return children;
            }
        }
        return FXCollections.emptyObservableList();
    }

    private ImageView getImageViewByFile(File f){
        ImageView imageView;
        if (f.isDirectory()) {
            imageView = new ImageView(imageDirectory);
        } else if (f.isFile()) {
            if (f.getName().endsWith(".p")){
                imageView = new ImageView(imageTPTP);
            }
            else
                imageView = new ImageView(imageFile);
        } else {
            imageView = new ImageView();
        }
        return imageView;
    }
    private boolean isFirstTimeChildren = true;
    private boolean isFirstTimeLeaf = true;
    private boolean isLeaf;
}
