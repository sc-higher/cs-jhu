/**
 * This program is my response to Assignment 3 for the
 * class 605.201.81 Intro to Programming Using Java at
 * the JHU EPP CS program.
 *
 * This program is a guessing game. The program asks
 * the user for a maximum value and for how many turns
 * they would like to play. A random value between 1 and
 * the maximum value is generated.

 * The user then makes guesses until they get the correct
 * answer or they run out of turns. If the guess is
 * incorrect, the program will let the user know whether
 * the guess was high or low.
 *
 * At the end of the game, win or lose, the program will
 * ask the user if they want to play again.
 *
 * @author: Sean Connor
*/

import java.util.Scanner;

public class GuessingGame
{
    public static void main(String [] args)
    {
        int continueGame = 1;

        // play the game
        while (continueGame == 1)
        {
            // initialize variables
            int topValue = 0;
            int guesses = 0;
            int randomNumber = 0;
            int guess = 0;
            int attempt = 0;

            // get user input
            Scanner input = new Scanner(System.in);
            System.out.println();
            System.out.println("Let's play a guessing game! Guess the correct" +
            " number between 1 and N.");
            System.out.print("Please enter a number N: ");
            topValue = input.nextInt();
            System.out.print("Please enter the number of guesses you would " +
            "like: ");
            guesses = input.nextInt();
            System.out.println();
            randomNumber = (int) (topValue * Math.random() + 1);

            // user makes guesses
            int i = 0;
            while (i < guesses)
            {
                // get user input for guess
                System.out.print("Make a guess: ");
                guess = input.nextInt();
                System.out.println();

                // correct attempt
                if (guess == randomNumber)
                {
                    System.out.println("Correct! You win!\n");
                    break;
                }

                // low attempt
                else if (guess < randomNumber)
                {
                    if (i == guesses-1)
                    {
                        System.out.println("Wrong! You lose.\n");
                        i += 1;
                    }
                    else
                    {
                        System.out.println("Too low! Try again.\n");
                        i += 1;
                    }
                }

                // high attempt
                else
                {
                    if (i == guesses-1)
                    {
                        System.out.println("Wrong! You lose.\n");
                        i += 1;
                    }
                    else
                    {
                        System.out.println("Too high! Try again.\n");
                        i += 1;
                    }
                }

            }

            // ask if user wants to play again
            System.out.print("Enter '1' to play again or '2' to quit: ");
            continueGame = input.nextInt();
            System.out.println();

        }
    }
}
