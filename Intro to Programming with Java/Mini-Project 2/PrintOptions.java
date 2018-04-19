/**
 * This program is my response to Project 2 for the class 605.201.81 Intro
 * to Programming Using Java at the JHU EPP CS program.
 * This class is used to store different menus for use in the other vending
 * machine simulator classes.
 * @author: Sean Connor
 * Date:    15 April 2018
 */

public class PrintOptions
{
    /**
     * This method prints the menu used for setting the currency to be used
     * in the vending machine.
     */
    public void printCurrencyOptions()
    {
        System.out.println("\nWelcome to Vending Machine Simulator!");
        System.out.println("Before we begin, let's set a few options.");
        System.out.println("\nPlease select a currency from the following " +
                "list of supported currencies.");
        System.out.println("(1) - USD");
        System.out.println("(2) - EUR");
        System.out.println("(3) - JPY");
        System.out.println("(4) - Other");
    }


    /**
     * This method prints the menu used for setting the inventory to be used
     * in the vending machine.
     */
    public void printInventoryOptions()
    {
        System.out.println("\nPlease select an inventory from the following:");
        System.out.println("(1) - Drinks");
        System.out.println("(2) - Snacks");
        System.out.println("(3) - Other");
    }


    /**
     * This method prints the menu used for the vending machine main menu.
     */
    public void printMainOptions()
    {
        System.out.println("\nPlease select an option from the following:");
        System.out.println("\n(1) - Display list of commands");
        System.out.println("(2) - Display vending machine inventory");
        System.out.println("(3) - Display money currently held in " +
                        "vending machine");
        System.out.println("(4) - Purchase an item");
        System.out.println("(5) - Quit Vending Machine Simulator");
    }


    /**
     * This method prints the menu used when determining whether the user
     * wants to insert additional money.
     */
    public void printContinueOptions()
    {
        System.out.println("\nContinue adding money?");
        System.out.println("\n(1) - Yes");
        System.out.println("(2) - No");
    }

}
