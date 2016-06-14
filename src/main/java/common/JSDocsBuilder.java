package common;

import common.find.InterfaceFinder;
import common.find.NCInterfaceFinder;
import common.parsers.InterfaceParser;
import common.parsers.NCInterfaceParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.List;

/**
 * user: koku0316
 * date: 10/06/2016.
 */
public class JSDocsBuilder {
    private InterfaceFinder finder = new NCInterfaceFinder();
    private InterfaceParser parser = new NCInterfaceParser();

    private String resultString = "(function () { " + "\n" + "window.uiplugins = {" + "\n";

    public void createJSDocsFrom(String path, String fileName) throws FileNotFoundException, UnsupportedEncodingException {
        Collection<File> files =finder.getInterfacesFrom(path);

        files.forEach(file -> {
            resultString += parser.getJSDocsOf(file);
        });
        resultString += "};" + "\n" + parser.getInheritanceString() + "});";

        PrintWriter writer = new PrintWriter(fileName,"UTF-8");
        writer.print(resultString);
        writer.close();
    }
}
