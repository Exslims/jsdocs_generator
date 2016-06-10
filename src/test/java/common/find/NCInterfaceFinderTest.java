package common.find;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Collection;

import static org.junit.Assert.*;

/**
 * user: koku0316
 * date: 10/06/2016.
 */
public class NCInterfaceFinderTest {
    private InterfaceFinder finder;
    @Before
    public void before(){
        finder = new NCInterfaceFinder();
    }

    @Test
    public void getInterfacesFrom() throws Exception {
        Collection<File> interfacesFrom = finder.getInterfacesFrom("C:\\svn_urls\\NCGEN_SVN\\UI_Plugins\\source\\com\\netcracker\\platform\\ui\\plugins\\gwt\\api");
        assertTrue(finder.getInterfacesFrom("C:\\svn_urls\\NCGEN_SVN\\UI_Plugins\\source\\com\\netcracker\\platform\\ui\\plugins\\gwt\\api").size() > 0);
    }
}