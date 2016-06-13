package common;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * user: koku0316
 * date: 10/06/2016.
 */
public class JSDocsBuilderTest {
    private JSDocsBuilder builder;
    @Before
    public void before(){
        builder = new JSDocsBuilder();
    }
    @Test
    public void createJSDocsFrom() throws Exception {
        builder.createJSDocsFrom("C:\\Users\\Константин\\Desktop\\test\\src","test.js");
    }
}