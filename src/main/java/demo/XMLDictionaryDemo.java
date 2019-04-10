package demo;

import dictionary.Dictionary;
import storage.StorageService;
import storage.XMLStorageService;

import java.util.Scanner;

public class XMLDictionaryDemo extends JSONDictionaryDemo {



    private static final String FILE_NAME = "dictionary.xml";

    public static void main(String[] args) {
        StorageService storageService = new XMLStorageService();
        Dictionary dictionary = new Dictionary(storageService, FILE_NAME);
        Scanner scanner = new Scanner(System.in);

        int selection=0;
        do{
        selection = beginMenuLoop(selection, scanner, dictionary);
        } while(selection != EXIT);
        if(selection == EXIT)
        System.out.println("\nThank you for using our dictionary");
        else
            return;
    }
}
   /* public static int displayMainMenuPrompt(Scanner scanner, Dictionary dictionary)
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
}
*/