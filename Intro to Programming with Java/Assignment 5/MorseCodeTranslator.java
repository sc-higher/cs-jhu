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


import java.util.Scanner;

public class MorseCodeTranslator
{

    // create translation arrays
    static char[] english = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
            'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
            'y', 'z', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0',
            ',', '.', '?', '!', ' '};

    static String[] morse = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..",
            ".---", "-.-", ".-..", "--", "-.", "---", ".---.", "--.-", ".-.",
            "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--..", ".----",
            "..---", "...--", "....-", ".....", "-....", "--...", "---..", "----.",
            "-----", "--..--", ".-.-.-", "..--..", "", "|"};

    static String alphabet = " abcdefghijklmnopqrstuvwxyz";


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

        // get user input for string to be translated
        System.out.println();
        input.nextLine();
        System.out.print("Please enter a sentence you would like translated: ");
        String userString = input.nextLine().toLowerCase();

        // test stuff
        System.out.println();
        System.out.println(userString);
        System.out.println();

        englishToMorse(userString);


    }




    public static void englishToMorse(String userString)
    {
        // initialize translated string
        String translated = new String();

        // create char array from user input string
        char[] splitToChar = userString.toCharArray();

        //
        for (char thisLetter : splitToChar)
        {
            translated = translated + charToMorse(thisLetter) + " ";
        }

        System.out.println(translated);
    }


    public static String charToMorse(char letter)
    {
        String empty = new String();
        String strLetter = "" + letter;

        if (alphabet.contains(strLetter))
        {
            for (int i = 0; i <= english.length; i++)
            {
                if (letter == english[i])
                {
                    return morse[i];
                }
            }
        }
        else
        {
            return empty;
        }

        return empty;

    }

}
