package common.find;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * user: koku0316
 * date: 10/06/2016.
 */
public class NCInterfaceFinder implements InterfaceFinder {
    private List<File> files = new ArrayList<>();
    @Override
    public List<File> getInterfacesFrom(String path) {
        return files;
    }
}
