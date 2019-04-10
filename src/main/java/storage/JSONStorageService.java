package storage;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class JSONStorageService implements StorageService{


    @Override
    public Map<String, String> loadFile(File file) throws IOException {

        String jsonString = FileUtils.readFileToString(file, "UTF-8");
        Map<String, String> myMap = JSONStorageService.convertJsonStringToMap(jsonString);
        return myMap;
    }

    @Override
    public boolean saveFile(File file, Map<String, String> mapToStorageService){
        boolean fileSaved = true;

        try {
            String content = JSONStorageService.convertMapToJsonString(mapToStorageService);
            FileUtils.writeStringToFile(file, content, "UTF-8");
        } catch (IOException e) {
            fileSaved = false;
        }

        return fileSaved;
    }

    public static Map<String,String> convertJsonStringToMap(String jsonString) throws IOException
    {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonString, new TypeReference<Map<String,String>>(){});

    }

    public static String convertMapToJsonString(Map<String,String> mapToConvert) throws IOException
    {
        String jsonString;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            jsonString = objectMapper.writeValueAsString(mapToConvert);
        } catch (JsonProcessingException e) {
            jsonString = ""; // This makes sure we don't return a null string if there is an exception
        }

        return jsonString;
    }
}