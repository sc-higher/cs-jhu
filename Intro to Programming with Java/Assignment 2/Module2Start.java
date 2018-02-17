/**
 * This program is my response to Assignment 2 for the
 * class 605.201.81 Intro to Programming Using Java at
 * the JHU EPP CS program.
 *
 * This is a table-sum program.
 *
 * @author: Sean Connor
*/

import java.util.Scanner;

public class Module2Start
{
    public static void main( String [] args )
    {
        // Define and initialize variables for values to be input
        int userNum1 = 0;      // First value to be input
        int userNum2 = 0;      // Second value to be input
        int userNum3 = 0;      // Third value to be input
        int userNum4 = 0;      // Fourth value to be input
        int userNum5 = 0;      // Fifth value to be input
        int userNum6 = 0;      // Sixth value to be input

        // Use a Scanner to input integer values
        Scanner input = new Scanner(System.in);
        System.out.println( "\n\n" );
        System.out.print( "Enter 6 integers separated by a blank space:" );
        userNum1 = input.nextInt();     // Input first value
        userNum2 = input.nextInt();     // Input second value
        userNum3 = input.nextInt();     // Input third value
        userNum4 = input.nextInt();     // Input fourth value
        userNum5 = input.nextInt();     // Input fifth value
        userNum6 = input.nextInt();     // Input sixth value

        // Sum each column and row
        int colNum1 = userNum1 + userNum3 + userNum5;
        int colNum2 = userNum2 + userNum4 + userNum6;
        int rowNum1 = userNum1 + userNum2;
        int rowNum2 = userNum3 + userNum4;
        int rowNum3 = userNum5 + userNum6;
        int colNum3 = rowNum1 + rowNum2 + rowNum3;

        // Output using System.out.println()
        System.out.println( "\n\n" );
        System.out.println( "\t" + "Value" + "\t" + "Value" + "\t" + "Total" );
        System.out.println( "\t" + userNum1 + "\t" + userNum2 + "\t" + rowNum1);
        System.out.println( "\t" + userNum3 + "\t" + userNum4 + "\t" + rowNum2);
        System.out.println( "\t" + userNum5 + "\t" + userNum6 + "\t" + rowNum3);
        System.out.println( "\t" + "----" + "\t" + "----" + "\t" + "----");
        System.out.println( "Total" + "\t" + colNum1 + "\t"+ colNum2 + "\t" +
        colNum3);
        System.out.println( "\n\n" );
    }
}
