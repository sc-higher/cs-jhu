/**
 *
 *
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class VMS
{

    public static final Scanner input = new Scanner(System.in);

    public static void main(String[] args)
    {
        // Get user input for VM currency type
        UserInput currencySelectionMenu = new UserInput(3);
        printCurrencyOptions();
        int currencyTypeSelection = currencySelectionMenu.getUserSelection();

        // Get user info for VM inventory
        UserInput inventorySelectionMenu = new UserInput(2);
        printInventoryOptions();
        int inventoryTypeSelection = inventorySelectionMenu.getUserSelection();

        // Load vending machine currency from CSV based on user selection
        String filenameCurrency = setCurrencyFile(currencyTypeSelection);
        ReadCSV currencyFromCSV = new ReadCSV(filenameCurrency);
        currencyFromCSV.toArray();
        ArrayList<Item> vendingCurrencyList = new ArrayList<Item>();
        toItemObject(currencyFromCSV, vendingCurrencyList);

        // Load vending machine inventory from CSV based on user selection
        String filenameInventory = setInventoryFile(inventoryTypeSelection);
        ReadCSV inventoryFromCSV = new ReadCSV(filenameInventory);
        inventoryFromCSV.toArray();
        ArrayList<Item> vendingInventoryList = new ArrayList<Item>();
        toItemObject(inventoryFromCSV, vendingInventoryList);

        // Display vending machine money and inventory, and wallet money
        System.out.println("\nGreat, let's get started!\n");
        displayAllInfo(vendingCurrencyList,vendingInventoryList);



    }





    /*-------------*/
    /*   METHODS   */
    /*-------------*/

    private static void printCurrencyOptions()
    {
        System.out.println("\nWelcome to Vending Machine Simulator!");
        System.out.println("Before we begin, let's set a few options.");
        System.out.println("\nPlease select a currency from the following " +
                "list of supported currencies.");
        System.out.println("(0) - USD");
        System.out.println("(1) - EUR");
        System.out.println("(2) - JPY");
    }

    private static void printInventoryOptions()
    {
        System.out.println("\nPlease select an inventory from the following:");
        System.out.println("(0) - Drinks");
        System.out.println("(1) - Snacks");
    }

    private static String setCurrencyFile(int currencySelection)
    {
        String filename;

        switch (currencySelection)
        {
            case 0:
                filename = "USD.csv";
                break;
            case 1:
                filename = "EUR.csv";
                break;
            case 2:
                filename = "JPY.csv";
                break;
            default:
                filename = "USD.csv";
        }

        return filename;
    }

    private static String setInventoryFile(int inventorySelection)
    {
        String filename;

        switch (inventorySelection)
        {
            case 0:
                filename = "drinks.csv";
                break;
            case 1:
                filename = "snacks.csv";
                break;
            default:
                filename = "drinks.csv";
        }

        return filename;
    }


    /**
     *
     * @param itemList
     */
    private static void displayInventory(ArrayList<Item> itemList)
    {
        String name = "Name";
        String price = "Price";
        String quantity = "Quantity";

        System.out.println();
        System.out.println("---------------------------------------------");
        System.out.println("|             Current Inventory             |");
        System.out.println("---------------------------------------------");

        System.out.printf("%1$15s", name);
        System.out.printf("%1$15s", price);
        System.out.printf("%1$15s", quantity);

        for (int i = 0; i < itemList.size(); i++)
        {
            System.out.printf("\n%1$15s", itemList.get(i).getName());
            System.out.printf("%1$15s", itemList.get(i).getValue());
            System.out.printf("%1$15s", itemList.get(i).getQuantity());
        }

        System.out.println("\n\n");
    }


    /**
     *
     * @param itemList
     */
    private static void displayVendingCurrency(ArrayList<Item> itemList)
    {
        String name = "Name";
        String value = "Value";
        String quantity = "Quantity";

        System.out.println();
        System.out.println("---------------------------------------------");
        System.out.println("|           Vending Machine Money           |");
        System.out.println("---------------------------------------------");

        System.out.printf("%1$15s", name);
        System.out.printf("%1$15s", value);
        System.out.printf("%1$15s", quantity);

        for (int i = 0; i < itemList.size(); i++)
        {
            System.out.printf("\n%1$15s", itemList.get(i).getName());
            System.out.printf("%1$15s", itemList.get(i).getValue());
            System.out.printf("%1$15s", itemList.get(i).getQuantity());
        }

        System.out.println("\n\n");
    }


    /**
     *
     */
    private static void displayAllInfo(ArrayList<Item> vendingCurrencyList,
                                       ArrayList<Item> vendingInventoryList)
    {
        System.out.println("Here is the most current information.");
        displayVendingCurrency(vendingCurrencyList);
        displayInventory(vendingInventoryList);

    }


    /**
     *
     * @param itemFromCSV
     * @param itemList
     */
    private static void toItemObject(ReadCSV itemFromCSV,
                                     ArrayList<Item> itemList)
    {
        for (int i = 1; i < itemFromCSV.numRows(); i++)
        {
            String name = itemFromCSV.getValue(i,0);
            int price = Integer.parseInt(itemFromCSV.getValue(i,1));
            int quantity = Integer.parseInt(itemFromCSV.getValue(i,2));

            itemList.add( new Item(name,price,quantity) );
        }
    }
}