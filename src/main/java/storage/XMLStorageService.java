package storage;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class XMLStorageService extends JSONStorageService{
    @Override
    public Map<String, String> loadFile(File file) throws IOException {
        String jsonString = FileUtils.readFileToString(file, "UTF-8");
        Map<String, String> myMap = XMLStorageService.convertXMLStringToMap(jsonString);
        return myMap;
    }

    private static Map<String, String> convertXMLStringToMap(String jsonString) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonString, new TypeReference<Map<String,String>>(){});

    }

    @Override
    public boolean saveFile(File file, Map<String, String> mapToStorageService)  {
        boolean fileSaved = true;

        try {
            String content = XMLStorageService.convertMapToXMLString(mapToStorageService);
            FileUtils.writeStringToFile(file, content, "UTF-8");
        } catch (IOException e) {
            fileSaved = false;
        }

        return fileSaved;
    }



    public static String convertMapToXMLString(Map<String,String> mapToConvert) throws IOException
    {
        String xmlString;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            xmlString = objectMapper.writeValueAsString(mapToConvert);
        } catch (IOException e) {
            xmlString = "error 42:  XMLStorageService.convertMapToXMLString()."; // This makes sure we don't return a null string if there is an exception
        }

        return xmlString;
    }
}