package dictionary;

import org.apache.commons.lang3.StringUtils;
import storage.StorageService;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Dictionary {

    private StorageService storageService; // This is a dependency/collaborator
    private Map<String,String> wordMap = new HashMap<>();
    private File file;
    private boolean isThisWordValid(String word)
    {
        if (word == null || word.trim().isEmpty() || !StringUtils.isAlpha(word))
            return true;
        else
            return false;
    }
    private boolean isThisDefinitionValid(String definition)
    {
        if (definition == null || definition.trim().isEmpty() || !StringUtils.isAlphaSpace(definition))
            return true;
        else
            return false;
    }

    /**
     * Constructor
     * @param storageService a reference to an already initialized StorageService
     */
    public Dictionary(StorageService storageService, String fileType)
    {

        this.storageService = storageService;  // This dependency is not being instantiated via the new keyword, it is being "injected"

        String fullPathToFile = System.getProperty("user.home") + File.separator + fileType;
        this.file = new File(fullPathToFile);
    }

    /**
     * This method will attempt to load the specified dictionary file.
     * Note that we are delegating the responsibility of loading a file to our StorageService dependency
     * @return returns true if the file was successfully loaded and false otherwise
     */
    public boolean loadFile(String fileName) {

        boolean loadSuccessful = true;
        try {
            this.wordMap = this.storageService.loadFile(file);
        } catch (IOException e) {
            loadSuccessful = false;
        }

        return loadSuccessful;
    }

    /**
     * This method will attempt to save the current state of our dictionary to a file.
     * Note that we are delegating the responsibility of saving a file to our StorageService dependency
     * @return returns true if the file was successfully saved and false otherwise
     */
    public boolean saveFile(String fileName){

        boolean saved;

            // This saveFile() method is just returning the boolean value that is returned to us from storageService.saveFile()
            if(this.storageService.saveFile(file, this.wordMap))
                saved = true;
            else
                saved = false;


        return saved;
    }

    public boolean addEntry(String word, String definition)
    {
        if(isThisWordValid(word) || isThisDefinitionValid(definition)) {
            return false;
        }
        this.wordMap.put(word.toLowerCase(), definition); // Note that we are converting the word to lower case

        return true;
    }

    public String findDefinition(String word)
    {
        String definition;

        if(isThisWordValid(word)) {
            definition = "";
        }
        else {
            definition = this.wordMap.get(word.toLowerCase());
        }

        if (definition == null)
            definition = "";

        return definition;
    }

    public int getEntryCount(){
        return this.wordMap.entrySet().size();
    }

}
