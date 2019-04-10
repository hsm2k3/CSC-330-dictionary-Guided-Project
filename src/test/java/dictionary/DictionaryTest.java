package dictionary;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import storage.StorageService;
import java.util.HashMap;
import java.util.Map;


import static org.junit.jupiter.api.Assertions.*;

class DictionaryTest {

    @DisplayName("This is an example of a passing test")
    @Test
    void passingTest() {
        assertTrue(true); // This is always true
    }

    @DisplayName("This is an example of a failing test")
    @Test
    @Disabled
    void failingTest() {
        fail("This test is hardcoded to fail");
    }

    @DisplayName("testing exceptions")
    @Test
    void testNullPointerExceptionHandling(){
        StorageService mockStorageService = new WorkingStorageServiceMock();
        String fileName = "bogusFileName.json";
        Dictionary dictionary = new Dictionary(mockStorageService,fileName);

        assertTrue(dictionary.findDefinition("asdlar;j") != null );
    }

    @DisplayName("Verify that Dictionary.loadFile() returns true if the underlying StorageService has successfully loaded a file")
    @Test
    void loadFileShouldReturnTrue() {

        // Take a quick look at the implementation for WorkingStorageServiceMock (using CTRL+B)
        StorageService mockStorageService = new WorkingStorageServiceMock();
        String fileName = "bogusFileName.json";
        Dictionary dictionary = new Dictionary(mockStorageService, fileName);

        assertTrue(dictionary.loadFile(fileName));
    }

    @DisplayName("Verify that Dictionary.loadFile() returns false if the underlying StorageService cannot load a file")
    @Test
    void loadFileShouldReturnFalse() {

        // Take a quick look at the implementation for FailingStorageServiceMock (using CTRL+B)
        StorageService mockStorageService = new FailingStorageServiceMock();
        String fileName = "bogusFileName.json";
        Dictionary dictionary = new Dictionary(mockStorageService,fileName);


        assertFalse(dictionary.loadFile(fileName));
    }

    @DisplayName("Verify that Dictionary.saveFile() returns true if the underlying StorageService has successfully loaded a file")
    @Test
    void saveFileShouldReturnTrue() {

        // Take a quick look at the implementation for WorkingStorageServiceMock (using CTRL+B)
        StorageService mockStorageService = new WorkingStorageServiceMock();
        String fileName = "bogusFileName.json";
        Dictionary dictionary = new Dictionary(mockStorageService,fileName);
        Map<String, String> mockMapToStorageService = new HashMap<>();


        assertTrue(dictionary.saveFile(fileName));
    }

    @DisplayName("Verify that Dictionary.saveFile() returns false if the underlying StorageService cannot save a file")
    @Test
    void saveFileShouldReturnFalse() {

        // Take a quick look at the implementation for FailingStorageServiceMock (using CTRL+B)
        StorageService mockStorageService = new FailingStorageServiceMock();
        String fileName = "bogusFileName.json";
        Dictionary dictionary = new Dictionary(mockStorageService,fileName);


        assertFalse(dictionary.loadFile(fileName));
    }

    @DisplayName("Verify that we can successfully add a word and retrieve its definition")
    @Test
    void successfullyAddWord()
    {
        StorageService mockStorageService = new WorkingStorageServiceMock();
        String fileName = "bogusFileName.json";
        Dictionary dictionary = new Dictionary(mockStorageService, fileName);

        String knownDefinition = "A delicious vegetable";
        dictionary.addEntry("potato", knownDefinition);

        assertEquals(knownDefinition, dictionary.findDefinition("potato"));
    }

    @DisplayName("Verify that we can successfully add several words such that the entry counts are correct")
    @Test
    void successfullyAddMultipleWordsAndVerifyCounts()
    {
        StorageService mockStorageService = new WorkingStorageServiceMock();
        String fileName = "bogusFileName.json";
        Dictionary dictionary = new Dictionary(mockStorageService, fileName);

        dictionary.addEntry("apple", "A delicious fruit");
        dictionary.addEntry("potato", "A delicious vegetable");
        dictionary.addEntry("tomato", "Another delicious fruit");

        assertEquals(3, dictionary.getEntryCount());
    }

    @DisplayName("Verify that we can successfully utilize the StorageService to load dictionary entries")
    @Test
    void successfullyLoadWordsFromFile()
    {
        StorageService mockStorageService = new WorkingStorageServiceMock();
        String fileName = "bogusFileName.json";
        Dictionary dictionary = new Dictionary(mockStorageService,fileName);

        // Before loading a file, we expect a brand new dictionary instance to have no entries
        assertEquals(0, dictionary.getEntryCount());

        dictionary.loadFile("bogusFileName.json"); // Our mock storage service doesn't care about the file name

        // After we use the mock storage service to simulate loading a file, we can then verify that our dictionary instance
        // has the expected number of entries.  How do we know how many entries to expect?
        // The WorkingStorageService has a single hardcoded entry.
        // When using mocks, we cannot change these hardcoded values since we would accidentally cause related tests to fail.
        assertEquals(1, dictionary.getEntryCount());
    }

    @DisplayName("We should be able to find the definition of a word regardless of the case")
    @Test
    void successfullyFindDefinitionWithMixedCasedWords()
    {
        StorageService mockStorageService = new WorkingStorageServiceMock();
        String fileName = "bogusFileName.json";
        Dictionary dictionary = new Dictionary(mockStorageService,fileName);

        String appleDefinition = "A delicious fruit";
        String potatoDefinition = "A delicious vegetable";
        String tomatoDefinition = "Another delicious fruit";

        dictionary.addEntry("apPLE", appleDefinition);
        dictionary.addEntry("POTato", potatoDefinition);
        dictionary.addEntry("TomatO", tomatoDefinition);

        assertEquals(appleDefinition, dictionary.findDefinition("Apple"));
        assertEquals(potatoDefinition, dictionary.findDefinition("pOtaTo"));
        assertEquals(tomatoDefinition, dictionary.findDefinition("TOMATO"));
    }

    @DisplayName("Verify that we are rejecting invalid entries and that we are avoid")
    @Test
    void successfullyRejectInvalidEntries()
    {
        StorageService mockStorageService = new WorkingStorageServiceMock();
        String fileName = "bogusFileName.json";
        Dictionary dictionary = new Dictionary(mockStorageService,fileName);


        assertFalse(dictionary.addEntry("Apple", null)); // Definition cannot be null. Note that addEntry() returns false for invalid entries
        assertEquals(0, dictionary.getEntryCount()); // Verify the count has not gone up immediate after the prior attempt

        assertFalse(dictionary.addEntry(null, "Some definition")); // Word cannot be null
        assertEquals(0, dictionary.getEntryCount());

        assertFalse(dictionary.addEntry(null, null)); // Verify we are returning false
        assertEquals(0, dictionary.getEntryCount());

        assertFalse(dictionary.addEntry("spaces not allowed", "spaced allowed")); // The definition can have spaces but not the word
        assertEquals(0, dictionary.getEntryCount());

        assertFalse(dictionary.addEntry("apple", "  ")); // Empty definitions should not be allowed
        assertEquals(0, dictionary.getEntryCount());

        assertFalse(dictionary.addEntry("apple", "")); // Empty definitions should not be allowed
        assertEquals(0, dictionary.getEntryCount());
    }



}
