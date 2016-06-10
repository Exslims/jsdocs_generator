package common.find;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.Collection;

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
                        String validContent = StringUtils.removePattern(content, "\\<(.*?)\\>");
                        if (validContent.contains("interface") && !validContent.contains("@interface")){
                            String substring = validContent.substring(validContent.indexOf("interface") - 10, validContent.indexOf("interface") + 10); //some bullshit(protect from comment)
                            if(!substring.contains("//") && substring.contains("public")){
                                return true;
                            }
                        }
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
