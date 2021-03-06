package common.parsers;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * user: koku0316
 * date: 10/06/2016.
 */
public class NCInterfaceParser extends InterfaceParser {
    private Map<String, String> prototypeMap = new HashMap<>();
    private int interfaceCount = 0;
    private int methodCount = 0;
    private String inheritance = "";
    private String resultBlock;
    @Override
    public String getJSDocsOf(File file) {
        resultBlock = "";
        interfaceCount++;
        try {
            List<String> strings = FileUtils.readLines(file);
            strings.forEach(line ->{
                String validLine = StringUtils.removePattern(line, "\\<(.*?)\\>");
                validLine = StringUtils.substringBefore(validLine,"//");
                if(validLine.contains("interface") && validLine.contains("{")){
                    if(validLine.contains("extends")){
                        checkInheritance(validLine);
                        resultBlock += getObjectNameWithoutInh(validLine);
                    }else
                        resultBlock += getObjectName(validLine);
                }
                if(validLine.contains("(") && validLine.contains(")")){
                    methodCount++;
                    String substringBefore = StringUtils.substringBefore(validLine, "(");
                    String arguments = StringUtils.substringBetween(validLine, "(", ")");
                    resultBlock += getMethodDoc(substringBefore,arguments);
                    substringBefore = StringUtils.substringAfterLast(substringBefore, " ");
                    resultBlock += substringBefore + ": function(";
                    if(!arguments.isEmpty()){
                        String[] splitArgs = arguments.split(",");
                        for (int i = 0; i < splitArgs.length; i++) {
                            String argName = StringUtils.substringAfterLast(splitArgs[i], " ");
                            resultBlock += argName + ((i == splitArgs.length -1) ? ") {}," + "\n" : ",");
                        }
                    }else resultBlock += ") {}," + "\n";
                }
            });
            resultBlock = resultBlock.replaceFirst(".$",""); //remove last function separate
            resultBlock += "}," + "\n";

        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultBlock;
    }
    private String getObjectName(String line){
        return StringUtils.substringBetween(line, "interface ", " {") + ": {" + "\n";
    }
    private String getObjectNameWithoutInh(String line){
        String head = getObjectName(line);
        String name = head.split(" extends ")[0];
        return name += ": {" + "\n";
    }
    private String getMethodDoc(String methodName, String argumentsString){
        String returnObject = StringUtils.substringBeforeLast(methodName," ");
        methodName = StringUtils.substringAfterLast(methodName, " ");

        String[] arguments = argumentsString.split(",");
        String comment = "/**" + "\n";
        String[] splitWords = methodName.split("(?=[A-Z])");
        String correctMethodName = WordUtils.capitalize(StringUtils.join(splitWords, " "));
        comment += "* " + correctMethodName + "\n";
        if(!arguments[0].isEmpty()) {
            for (String argument : arguments) {
                String argName = StringUtils.substringAfterLast(argument, " ");
                comment += "* @param " + argName + "\n";
            }
        }
        if(!returnObject.contains("void")){
            String linkStr = " {@link " + StringUtils.trim(returnObject) + "}";
            returnObject = StringUtils.removeEnd(returnObject,"Api");
            comment += "* @return " + StringUtils.trim(returnObject) + linkStr + "\n";
        }
        comment += "*/" + "\n";
        return comment;
    }
    private void checkInheritance(String line){
        if(line.contains("extends")){
            String objectName = getObjectName(line);
            List<String> classes = Arrays.asList(objectName.split(" extends "));

            String parent = prototypeMap.get(classes.get(0));
            String key = classes.get(0);
            String[] parents = classes.get(1).split(",");
            parent = parents[0];
            prototypeMap.put(key,parent);
        }
    }

    public String getInheritanceString(){
        prototypeMap.forEach((k,v) ->{
            String[] split = v.split(":");
            inheritance += "window.uiplugins." + k + ".prototype = " + "window.uiplugins." + split[0] + ";\n";
        });
        return inheritance;
    }
    public String getStatisticString(){
        return "/**" + "\n" +"Interfaces count = " + interfaceCount + "\n" +
                "Methods count = " + methodCount + "\n"
                + "This file is auto-generated" + "\n" + "*/";
    }
}
