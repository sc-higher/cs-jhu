/**
 * This program is my response to Assignment 5 for
 * the class 605.201.81 Intro to Programming Using
 * Java at the JHU EPP CS program.
 *
 * This is a morse code translator program. It will
 * translate text to morse code and vice-versa.
 *
 * @author: Sean Connor
 */


import java.util.*;

public class MorseCodeTranslator
{

    // create translation arrays for english --> morse
    static char[] english = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
            'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u',
            'v', 'w', 'x', 'y', 'z', '1', '2', '3', '4', '5', '6', '7',
            '8', '9', '0', ',', '.', '?', '!', ' '};

    static String[] morse = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.",
            "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.",
            "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--",
            "--..", ".----", "..---", "...--", "....-", ".....", "-....",
            "--...", "---..", "----.", "-----", "--..--", ".-.-.-", "..--..",
            "", "|"};

    static String alphabet = "abcdefghijklmnopqrstuvwxyz1234567890 ";


    // call main method
    public static void main(String[] args)
    {
        // create scanner object
        Scanner input = new Scanner(System.in);

        // get user input for translation choice
        System.out.println();
        System.out.print("Please enter (1) for morse --> english " +
                "or (2) for english --> morse: ");
        int userChoice = input.nextInt();

        // get user input and run the translation methods
        switch (userChoice)   // switch used to verify translation choice
        {                     // is either 1 or 2.
            case 1:
            {
                // get user input for string to be translated
                // convert to lowercase (if applicable) for translation
                // array
                System.out.println();
                input.nextLine();
                System.out.print("Please enter a sentence you would" +
                        " like translated: ");
                String userString = input.nextLine().toLowerCase();
                // run the translation method
                performTranslation(userChoice, userString);
                break;
            }
            case 2:
            {
                // get user input for string to be translated
                // convert to lowercase (if applicable) for translation
                // array
                System.out.println();
                input.nextLine();
                System.out.print("Please enter a sentence you would" +
                        " like translated: ");
                String userString = input.nextLine().toLowerCase();
                // run the translation method
                performTranslation(userChoice, userString);
                break;
            }
            default:
                System.out.println("\nYou did not enter a valid number.\n");

        }

        //

    }


    /**
     * Creates a character array from all characters in the input string.
     * Calls the charToMorse method on each character, and concatenates
     * the results to a new string 'translated'. Print the 'translated'
     * string.
     *
     * @param userString  String input from user to be translated
     */

    public static void englishToMorse(String userString)
    {
        // initialize translated string
        String translated = new String();

        // create char array from user input string
        char[] splitToChar = userString.toCharArray();

        // translate each character in the character array
        // to the equivalent morse string and concatenate
        // to the 'translated' string
        for (char thisLetter : splitToChar)
        {
            translated = translated + charToMorse(thisLetter) + " ";
        }

        System.out.println("\n"+translated+"\n");
    }


    /**
     * Converts an english character to morse. Will only convert characters
     * contained in the 'alphabet' string, and thus ignores punctuation.
     *
     * @param letter English character input to be translated to morse
     * @return A string representing the morse translation of the input
     * character.
     */

    public static String charToMorse(char letter)
    {
        String empty = new String();
        String strLetter = "" + letter; // convert letter char to string

        if (alphabet.contains(strLetter)) // .contains() method requires
        {                                 // string input
            for (int i = 0; i <= english.length; i++)
            {
                if (letter == english[i])  // identify the array element
                {
                    return morse[i];  // return the equivalent morse element
                }
            }
        }
        else
        {
            return empty;
        }

        return empty;
    }


    /**
     * Converts a string representing a morse character into the equivalent
     * english character. Input string must be in the 'morse' array.
     *
     * @param morseCharacter  String representing a morse character to be
     *                        translated to english
     * @return A char representing the english translation of the input string.
     */

    public static char morseToChar(String morseCharacter)
    {
        int counter = 0;

        for (int i = 0; i <= morse.length; i++)
        {
            if (morseCharacter.equals(morse[i]))
            {
                counter = i;  // needed to place return outside for-loop
                break;
            }

        }

        return english[counter];
    }


    /**
     * First splits the morse-form input string to morse 'words' using the
     * "|" delimiter and places results into an String array. Then, each
     * element of the array is further split using the " " delimiter to break
     * each morse 'word' into morse characters and places results into a
     * separate String array. Finally, the morseToChar method is called on
     * each element in this array. The english translation is returned and
     * concatenated on to a String 'translated', which represents the english
     * translation of the user input morse string.
     *
     * @param userString  String input from user to be translated
     */

    public static void morseToEnglish(String userString)
    {
        String translated = "";

        String[] words = userString.split("\\|");
        for (String word : words)
        {
            String[] characters = word.split(" ");
            for (String character : characters)
            {
                translated = translated + morseToChar(character);
            }
            translated = translated + " ";
        }

        System.out.println("\n"+translated+"\n");
    }


    /**
     * Exectues the translation methods required to convert morse to english or
     * english to morse. Try-catch statements are implemented to limit
     * possibility of faulty input.
     *
     * @param userChoice  Int representing whether user wants morse -> english
     *                    or english -> morse
     * @param userString  String input from user to be translated
     */

    public static void performTranslation(int userChoice, String userString)
    {
        int whileTest = 0;
        switch (userChoice)
        {
            case 1:   // morse to english
            {
                while (whileTest == 0)
                {
                    try
                    {
                        morseToEnglish(userString);
                        whileTest = 1;
                    }
                    catch (ArrayIndexOutOfBoundsException e)
                    {
                        System.out.println("\nThat was not a valid input.\n");
                        System.out.print("Please try again: ");
                        Scanner input = new Scanner(System.in);
                        userString = input.nextLine().toLowerCase();
                    }
                }
                break;
            }
            case 2:   // english to morse
            {
                while (whileTest == 0)
                {
                    try
                    {
                        englishToMorse(userString);
                        whileTest = 1;
                    }
                    catch (InputMismatchException e)
                    {
                        System.out.println("\nThat was not a valid input.\n");
                        System.out.print("Please try again: ");
                        Scanner input = new Scanner(System.in);
                        userString = input.nextLine().toLowerCase();
                    }
                }
                break;
            }
            default:
            {
                System.out.println("\nYou did not enter a valid number.\n");
            }
        }
    }

}