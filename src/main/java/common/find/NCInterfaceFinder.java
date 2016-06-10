package common.find;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;

import java.io.*;
;
import java.nio.file.Files;
import java.util.Collection;
import java.util.Scanner;

/**
 * user: koku0316
 * date: 10/06/2016.
 */
public class NCInterfaceFinder implements InterfaceFinder {
    @Override
    public Collection<File> getInterfacesFrom(String path) {
        File dir = new File(path);
        return FileUtils.listFiles(dir, new IOFileFilter() {
            @Override
            public boolean accept(File file) {
                try {
                    if(file.getName().endsWith(".java")) {
                        String content = FileUtils.readFileToString(file);

                        if (content.contains("interface"))
                            return true;
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
                return false;
            }

            @Override
            public boolean accept(File file, String name) {
                return name.endsWith(".java");
            }
        }, TrueFileFilter.INSTANCE);
    }
}
