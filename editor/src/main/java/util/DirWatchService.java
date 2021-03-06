package util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import gui.fileBrowser.FileTreeItem;
import gui.fileBrowser.FileTreeView;
import gui.fileBrowser.FileWrapper;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;

public class DirWatchService extends Thread {
    
    WatchService watcher;
    DirWatcher dirWatcher;
    AtomicBoolean stop = new AtomicBoolean(false);
    private Map<WatchKey, Path> keys;
    FileTreeView fileBrowser;
    Path rootPath;
    
    /**
     * This WatchService registers all subfolders of @param path (including path) such that on each create or delete event,
     * the file browser tree is updated, and all files and folders are shown.
     * 
     * This service handles both, file system actions started within our file browser and those started outside of it.
     * 
     * @param path
     * @param fileBrowser
     * @throws IOException
     */
    public DirWatchService(Path path, FileTreeView fileBrowser) throws IOException {
        dirWatcher = new DirWatcher(path);
        watcher = dirWatcher.getWatcher();
        keys = dirWatcher.getKeys();
        this.fileBrowser = fileBrowser;
        this.rootPath = path;
    }
    
    public void run() {
        
        WatchKey key = null;
        
        while (!stop.get()) {
            
            while (true) {
                try {
                    key = watcher.take();
                    break;
                } catch (InterruptedException e) {
                    // Exit when finished
                    if (stop.get())
                        return;
                }
            }
            
            if (stop.get()) { 
                return;
            }
            
            Path path = keys.get(key);
            if (path == null) {
                continue;
            }
            
            for (WatchEvent<?> event : key.pollEvents()) {
                Kind<?> kind = event.kind();
                
                if (kind == StandardWatchEventKinds.OVERFLOW) {
                    continue;
                }
                
                Path newPath = (Path) event.context();
                Path completePath = path.resolve(newPath);
                
                // Get the relative path in order to navigate in the file browser tree.
                Path relativePath = rootPath.relativize(completePath);
                Iterator<Path> itr = relativePath.iterator();
                LinkedList<Path> list = new LinkedList<Path>();
                while (itr.hasNext()) {
                    list.add(itr.next());
                }
                TreeItem<FileWrapper> item = fileBrowser.getRoot();
                int size = 0;
                
                if (kind == StandardWatchEventKinds.ENTRY_CREATE) {
                    size = 1;
                }
                
                // Find the item of the file browser which has to be changed.
                while (list.size() > size) {
                    ObservableList<TreeItem<FileWrapper>> children = item.getChildren();
                    Path nextPath = list.pop();
                    for (TreeItem<FileWrapper> child : children) {
                        if (child.getValue().toString().equals(nextPath.toString())) {
                            item = child;
                            break;
                        }
                    }
                }
                
                
                if (kind == StandardWatchEventKinds.ENTRY_CREATE) {
                    // Add new entry to the file browser.
                    File file = completePath.toFile();
                    FileTreeItem newItem = new FileTreeItem(new FileWrapper(file));
                    newItem.setGraphic(newItem.getIconNodeByFile(file));
                    item.getChildren().add(newItem);
                    ((FileTreeItem) item).sortChildren(false);
                    if (completePath.toFile().isDirectory()) {
                        try {
                            dirWatcher.registerSubfolders(completePath);
                        } catch (IOException e) {
                        }
                    }
                }
                if (kind == StandardWatchEventKinds.ENTRY_DELETE) {
                    // Remove the corresponding item in the file browser.
                    item.getParent().getChildren().remove(item);
                }
            }
            
            if (!key.reset()) {
                keys.remove(key);
                
                if (keys.size() == 0) {
                    break;
                }
            }
        }
    }
    
    public void setStop() {
        this.stop.set(true);
    }
}
