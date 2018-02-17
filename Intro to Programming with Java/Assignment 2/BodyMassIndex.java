/**
 * This program is my response to Assignment 2 for the
 * class 605.201.81 Intro to Programming Using Java at
 * the JHU EPP CS program.
 *
 * This is a BMI Calculator program.
 *
 * @author: Sean Connor
*/

import java.util.Scanner;

public class BodyMassIndex
{
    public static void main(String [] args)
    {
        // Define and initialize user height and weight variables
        int userHeight = 0;
        int userWeight = 0;

        // Use a Scanner to input integer values
        Scanner input = new Scanner(System.in);
        System.out.println();
        System.out.print("Enter height (in inches): ");
        userHeight = input.nextInt();
        System.out.print("Enter weight (in pounds): ");
        userWeight = input.nextInt();

        // Convert to meters and kilograms
        double heightConvert = userHeight * 0.0254;
        double weightConvert = userWeight * 0.45359237;

        // Calculate BMI
        double bmi = weightConvert / Math.pow(heightConvert, 2);

        // Print BMI and BMI Standards
        System.out.println("Body Mass Index: " + bmi);
        System.out.println();
        System.out.println("\t" + "Underweight: less than 18.5");
        System.out.println("\t" + "Normal: 18.5 - 24.9");
        System.out.println("\t" + "Overweight: 25 - 29.9");
        System.out.println("\t" + "Obese: 30 or greater");
        System.out.println();
    }
}
