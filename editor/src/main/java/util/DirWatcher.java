package util;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;

public class DirWatcher {
    
    private WatchService watcher;
    private Map<WatchKey, Path> keys;
    
    public DirWatcher (Path path) throws IOException  {
        keys = new HashMap<WatchKey, Path>();
        this.watcher = FileSystems.getDefault().newWatchService();
        
        registerSubfolders(path);
    }
    
    /**
     * Register the folder with the path @param path.
     * @param path
     * @throws IOException
     */
    private void registerFolder(Path path) throws IOException {
        WatchKey key = path.register(watcher, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE);
        keys.put(key, path);
    }

    /**
     * Register all subfolders of @param path, including @param path.
     * @param path
     * @throws IOException
     */
    public void registerSubfolders(Path path) throws IOException {
        
        Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path path, BasicFileAttributes attrs)
                throws IOException
            {
                registerFolder(path);
                return FileVisitResult.CONTINUE;
            }
        });
    }
    
    public WatchService getWatcher() {
        return watcher;
    }
    public Map<WatchKey, Path> getKeys() {
        return keys;
    }
}
