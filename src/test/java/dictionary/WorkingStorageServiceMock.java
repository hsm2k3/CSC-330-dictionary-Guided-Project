package dictionary;

import storage.StorageService;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WorkingStorageServiceMock implements StorageService {

    @Override
    public Map<String, String> loadFile(File file) throws IOException {

        // Note that this mock object will never attempt to read from an actual file.
        // It is meant to *always* simulate a successful load operation.

        Map<String,String> myMap = new HashMap<>();
        myMap.put("Example", "One of a number of things that is used to show the character of the whole");
        return myMap;
    }


    @Override
    public boolean saveFile(File file, Map<String, String> mapToStorageService) {
        // Note that this mock object will never attempt to save to an actual file.
        // It is meant to *always* simulate a successful save operation.
        return true;
    }
}
