/**
 * This program is part of my response to Project 3 for the class 605.201.81
 * Intro to Programming Using Java at the JHU EPP CS program.
 *
 * This class is used to obtain user input (as integer) from the terminal.
 *
 * @author: Sean Connor
 */

import java.util.Scanner;

public class UserInput
{
    // initialize variables
    Scanner input = new Scanner(System.in);
    private Integer userselection;
    private int start;
    private int end;

    // constructor
    /**
     * This constructor requires two int inputs, representing the start and
     * end of the desired int selection range.
     *
     * @param start
     * @param end
     */
    public UserInput(int start, int end)
    {
        this.start = start;
        this.end = end;
        userselection = Integer.valueOf(-1);
    }


    /**
     * This method prompts the user to enter an integer value between the
     * start and end values (inclusive) specified in the constructor. Any
     * non-int value, or int value outside of the specified range, will not
     * be accepted. Method will continue until acceptable input is entered.
     *
     * @return int value representing the user selection
     */
    public int getUserSelection()
    {
        System.out.println();
        String userstring = "";
        userselection = Integer.valueOf(-1);

        // make sure input is valid
        while ((userselection < start) || (userselection > end))
        {
            System.out.print("Input: ");
            userstring = input.nextLine();

            // check if nothing was entered
            if ( userstring.isEmpty() )
            {
                System.out.println("\nPlease enter only integer values" +
                        " between " + start + " and " + end + ".\n");
            }

            else
            {
                // if something was entered, make sure is in range
                try
                {
                    userselection = Integer.valueOf(userstring);
                    if ((userselection < start) || (userselection > end))
                    {
                        System.out.println("\nPlease enter only integer values"
                                + " between " + start + " and " + end + ".\n");
                    }
                }
                catch (NumberFormatException e)
                {
                    System.out.println("\nPlease enter only integer values" +
                            " between " + start + " and " + end + ".\n");
                }
            }
        }

        return userselection;
    }
}