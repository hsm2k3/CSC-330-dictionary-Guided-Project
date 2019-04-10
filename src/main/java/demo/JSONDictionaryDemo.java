package demo;

import dictionary.Dictionary;
import storage.JSONStorageService;
import storage.StorageService;
import java.util.HashMap;
import java.util.Map;

import java.util.Scanner;

public class JSONDictionaryDemo {

    // A static final field is like a const value in C++
    protected static final int ADD_WORD = 1;
    protected static final int FIND_DEFINITION = 2;
    protected static final int ENTRY_COUNT = 3;
    protected static final int LOAD_DICTIONARY = 4;
    protected static final int SAVE_DICTIONARY = 5;
    protected static final int EXIT = 6;

    protected static final String FILE_NAME = "dictionary.json";

    public static void main(String[] args)
    {
        StorageService storageService = new JSONStorageService();
        Dictionary dictionary = new Dictionary(storageService, FILE_NAME);
        Scanner scanner = new Scanner(System.in);

        int selection = 0;
        do{
        selection = beginMenuLoop(selection, scanner, dictionary);

        } while(selection != EXIT);

        System.out.println("\nThank you for using our dictionary");
    }

    public static int beginMenuLoop(int selection, Scanner scanner, Dictionary dictionary){


            selection = displayMainMenuPrompt(scanner, dictionary);

            switch (selection)
            {
                case ADD_WORD:
                    displayAddWordPrompt(scanner, dictionary);
                    break;
                case FIND_DEFINITION:
                    displayFindDefinitionPrompt(scanner, dictionary);
                    break;
                case ENTRY_COUNT:
                    displayEntryCountPrompt(scanner, dictionary);
                    break;
                case LOAD_DICTIONARY:
                    displayLoadDictionaryPrompt(dictionary);
                    break;
                case SAVE_DICTIONARY:
                    displaySaveDictionaryPrompt(dictionary);
                    break;
                default:
                    selection = EXIT;

            }

        return selection;

    }

    public static int displayMainMenuPrompt(Scanner scanner, Dictionary dictionary)
    {

        System.out.println("**********************************");
        System.out.println("MENU: ");
        System.out.println("\t1. Add a new word/definition");
        System.out.println("\t2. Get a definition");
        System.out.println("\t3. Get the entry count");
        System.out.println("\t4. Load the dictionary file");
        System.out.println("\t5. Save the dictionary file");
        System.out.println("\t6. Quit");
        System.out.println("**********************************");
        System.out.print("\nEnter your selection: ");

        int selection;

        try {
            selection = scanner.nextInt();
        }
        catch(Exception e){
            selection = EXIT;
        }

        scanner.nextLine(); // Clear the input buffer of the extra new line
        System.out.println();

        return selection;
    }

    public static void displayAddWordPrompt(Scanner scanner, Dictionary dictionary)
    {
        String word;
        String definition;

        boolean validEntry = true;
        do {
            System.out.println("**********************************");
            System.out.println("ADD WORD");
            System.out.println("**********************************");
            System.out.println();

            System.out.print("\tPlease enter the word: ");
            word = scanner.nextLine();

            System.out.print("\tPlease enter the definition: ");
            definition = scanner.nextLine();

            if(!dictionary.addEntry(word,definition)) { // addEntry() returns false if the word or definition are syntactically bad
                System.out.println("\tYou have entered an invalid word or definition. Please try again\n");
                validEntry = false;
            }
            else
                validEntry = true;

            System.out.println();
        }
        while(!validEntry);
    }

    public static void displayFindDefinitionPrompt(Scanner scanner, Dictionary dictionary)
    {
        System.out.println("**********************************");
        System.out.println("FIND DEFINITION");
        System.out.println("**********************************");
        System.out.println();

        System.out.print("\tPlease enter the word you wish to be defined: ");
        String word = scanner.nextLine();
        String definition = dictionary.findDefinition(word.toLowerCase());

        System.out.println();

        if(definition.trim().isEmpty()){
            System.out.println("\t\tNO DEFINITION FOUND FOR " + word.toUpperCase());
        }
        else {
            System.out.println("\t\t" + word.toUpperCase());
            System.out.println("\t\t\t" + definition);
        }

        System.out.println();

    }

    public static void displayEntryCountPrompt(Scanner scanner, Dictionary dictionary)
    {
        System.out.println("**********************************");
        System.out.println("ENTRY COUNT");
        System.out.println("**********************************");
        System.out.println();
        System.out.println("\tThere are " + dictionary.getEntryCount() + " entries in our dictionary");
        System.out.println();
    }

    public static void displayLoadDictionaryPrompt(Dictionary dictionary)
    {
        System.out.println("**********************************");
        System.out.println("LOAD DICTIONARY");
        System.out.println("**********************************");
        System.out.println();

        System.out.print("\tLoading...");
        if(dictionary.loadFile(FILE_NAME))
            System.out.println("SUCCESS\n");
        else
            System.out.println("FAILED\n");

        System.out.println("SUCCESS\n");
    }

    public static void displaySaveDictionaryPrompt(Dictionary dictionary)
    {
        System.out.println("**********************************");
        System.out.println("SAVE DICTIONARY");
        System.out.println("**********************************");
        System.out.println();

        System.out.print("\tSaving...");
        if(dictionary.saveFile(FILE_NAME))
            System.out.println("SUCCESS\n");
        else
            System.out.println("FAILED\n");
    }

} // end of class demo.JSONDictionaryDemo
