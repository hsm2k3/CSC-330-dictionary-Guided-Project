package dictionary;

import storage.StorageService;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class FailingStorageServiceMock implements StorageService {
    @Override
    public Map<String, String> loadFile(File file) throws IOException {
        throw new IOException("This mock object will always throw an IOException");
    }

    @Override
    public boolean saveFile(File file,  Map<String, String> mapToStorageService) {
        return false; // Return false to indicate that this mock object will always fail
    }
}
