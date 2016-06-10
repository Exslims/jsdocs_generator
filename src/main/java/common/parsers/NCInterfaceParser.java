package common.parsers;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * user: koku0316
 * date: 10/06/2016.
 */
public class NCInterfaceParser extends InterfaceParser {
    private String resultBlock;
    @Override
    public String getJSDocsOf(File file) {
        resultBlock = "";
        try {
            List<String> strings = FileUtils.readLines(file);
            strings.forEach(string ->{
                if(string.contains("interface") && string.contains("{")){
                    String head = StringUtils.substringBetween(string, "interface ", " {");
                    resultBlock += head + ": {" + "\n";
                }
                if(string.contains("(") && string.contains(")")){
                    String substringBefore = StringUtils.substringBefore(string, "(");
                    String methodName = StringUtils.substringAfterLast(substringBefore, " ");
                    resultBlock += methodName + ": function(";

                    String arguments = StringUtils.substringBetween(string, "(", ")");
                    if(!arguments.isEmpty()){
                        String validArg = StringUtils.removePattern(arguments, "\\<(.*?)\\>");
                        String[] splitArgs = validArg.split(",");
                        for (int i = 0; i < splitArgs.length; i++) {
                            String argName = StringUtils.substringAfterLast(splitArgs[i], " ");
                            resultBlock += argName + ((i == splitArgs.length -1) ? ") {}," + "\n" : ",");
                        }
                    }else resultBlock += ") {}," + "\n";
                }
            });
            resultBlock += "}," + "\n";

        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultBlock;
    }
}
