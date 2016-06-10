import common.JSDocsBuilder;

import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        JSDocsBuilder jSDocsBuilder = new JSDocsBuilder();
        Scanner scanner = new Scanner(new InputStreamReader(System.in));
        String path = scanner.next();
        jSDocsBuilder.createJSDocsFrom(path,"test.js");
    }
}
