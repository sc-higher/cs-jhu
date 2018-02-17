/**
 * This program is my response to Assignment 3 for the
 * class 605.201.81 Intro to Programming Using Java at
 * the JHU EPP CS program.
 *
 * This is a program that creates a triangle, with style
 * and size selected by the user.
 *
 * @author: Sean Connor
*/

import java.util.Scanner;

public class Triangle
{
    public static void main(String [] args)
    {
        // initialize variables
        int size = 0;
        int shape = 0;

        // get user input
        Scanner input = new Scanner(System.in);
        System.out.println();
        System.out.print("Please enter a number to specify triangle size: ");
        size = input.nextInt();
        System.out.println();
        System.out.print("Please enter '1' or '2' to specify inverted or" +
        " normal: ");
        shape = input.nextInt();
        System.out.println();


        /* nested for-loops to create the triangle */

        // shape == 2 (normal triangle)
        if (shape == 2)
        {
            for (int x = 1; x <= size; x++)
            {
                for (int y = 1; y <= x; y++)
                {
                    System.out.print("+");
                }
                System.out.println("");
            }
        }

        // shape == 1 (inverted triangle)
        else if (shape == 1)
        {
            for (int x = size; x >= 1; x--)
            {
                for (int y = x; y >= 1; y--)
                {
                    System.out.print("+");
                }
                System.out.println("");
            }
        }

        // shape does not equal 1 or 2
        else
        {
            System.out.println("You did not enter a valid number.");
        }

        System.out.println();

    }
}
