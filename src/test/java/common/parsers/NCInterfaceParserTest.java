package common.parsers;

import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

/**
 * user: koku0316
 * date: 10/06/2016.
 */
public class NCInterfaceParserTest {
    private InterfaceParser parser;

    @Before
    public void before(){
        parser = new NCInterfaceParser();
    }
    @Test
    public void getJSDocsOf() throws Exception {
        String jsDocsOf = parser.getJSDocsOf(
                new File("C:\\svn_urls\\NCGEN_SVN\\UI_Plugins\\source\\com\\netcracker\\platform\\ui\\plugins\\gwt\\api\\action\\ActionConfigApi.java"));
    }
}