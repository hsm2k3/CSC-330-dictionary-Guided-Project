package storage;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public interface StorageService {
    Map<String, String> loadFile(File file) throws IOException;
    boolean saveFile(File file, Map<String,String> mapToStorageService);
}
