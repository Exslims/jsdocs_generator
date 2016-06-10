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
        builder.createJSDocsFrom("C:\\svn_urls\\NCGEN_SVN\\UI_Plugins\\source\\com\\netcracker\\platform\\ui\\plugins\\gwt\\api","test.js");
    }
}