package gui.fileBrowser;

import java.io.File;

public class FileWrapper {
    File f;
    public FileWrapper(File f) {
        this.f = f;
    }

    @Override
    public String toString(){
        return f.getName();
    }
}
