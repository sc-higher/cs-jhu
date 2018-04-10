/**
 *
 *
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class VendingMachineSimulator
{
    public static final Scanner input = new Scanner(System.in);
    public static final PrintOptions options = new PrintOptions();

    public static void main(String[] args)
    {
        // Create a vending machine simulator object
        VendingMachineSimulator vms = new VendingMachineSimulator();

        // Get user input for VM currency type
        UserInput currencySelectionMenu = new UserInput(3);
        options.printCurrencyOptions();
        int currencyTypeSelection = currencySelectionMenu.getUserSelection();

        // Get user info for VM inventory
        UserInput inventorySelectionMenu = new UserInput(2);
        options.printInventoryOptions();
        int inventoryTypeSelection = inventorySelectionMenu.getUserSelection();

        // Load VM currency from CSV based on user selection
        String filenameCurrency = vms.setCurrencyFile(currencyTypeSelection);
        ReadCSV currencyFromCSV = new ReadCSV(filenameCurrency);
        currencyFromCSV.toArray();
        Item[] currency = currencyFromCSV.getItemArray();

        // Load VM inventory from CSV based on user selection
        String filenameInventory = vms.setInventoryFile(inventoryTypeSelection);
        ReadCSV inventoryFromCSV = new ReadCSV(filenameInventory);
        inventoryFromCSV.toArray();
        Item[] inventory = inventoryFromCSV.getItemArray();

        // Create VM money counter
        Item[] moneyCounter = vms.createMoneyCounter(currency);

        // Display vending machine money and inventory, and wallet money
        System.out.println("\nGreat, let's get started!\n");
        vms.displayAllInfo(currency,inventory,moneyCounter,currencyTypeSelection);

        // Run the VM simulator
        int mainSelection = Integer.valueOf(-1);
        vms.runVM(mainSelection,currency,inventory,moneyCounter,currencyTypeSelection);




    }





    /*-------------*/
    /*   METHODS   */
    /*-------------*/

    private String setCurrencyFile(int currencySelection)
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

    private String setInventoryFile(int inventorySelection)
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
    private void displayInventory(Item[] itemArray)
    {
        String id = "ID";
        String name = "Name";
        String price = "Price";
        String quantity = "Quantity";

        System.out.println();
        System.out.println("-------------------------------------------------");
        System.out.println("|               Current Inventory               |");
        System.out.println("-------------------------------------------------");

        System.out.printf("%1$5s", id);
        System.out.printf("%1$15s", name);
        System.out.printf("%1$10s", price);
        System.out.printf("%1$10s", quantity);

        for (int i = 0; i < itemArray.length; i++)
        {
            System.out.printf("\n%1$5s", itemArray[i].getID());
            System.out.printf("%1$15s", itemArray[i].getName());
            System.out.printf("%1$10s", itemArray[i].getValue());
            System.out.printf("%1$10s", itemArray[i].getQuantity());
        }

        System.out.println("\n\n");
    }


    /**
     *
     * @param itemList
     */
    private void displayVendingCurrency(Item[] itemArray,
                                               int currencySelection)
    {
        char usd_symbol = 36;
        char eur_symbol = 8364;
        char jpy_symbol = 165;
        char symbol;

        switch (currencySelection)
        {
            case 0:
                symbol = usd_symbol;
                break;
            case 1:
                symbol = eur_symbol;
                break;
            case 2:
                symbol = jpy_symbol;
                break;
            default:
                symbol = usd_symbol;
        }

        String id = "ID";
        String name = "Name";
        String value = "Value";
        String quantity = "Quantity";

        System.out.println();
        System.out.println("-------------------------------------------------");
        System.out.println("|           Vending Machine Money (" + symbol +
                ")           |");
        System.out.println("-------------------------------------------------");

        System.out.printf("%1$5s", id);
        System.out.printf("%1$15s", name);
        System.out.printf("%1$10s", value);
        System.out.printf("%1$10s", quantity);

        for (int i = 0; i < itemArray.length; i++)
        {
            System.out.printf("\n%1$5s", itemArray[i].getID());
            System.out.printf("%1$15s", itemArray[i].getName());
            System.out.printf("%1$10s", itemArray[i].getValue());
            System.out.printf("%1$10s", itemArray[i].getQuantity());
        }

        System.out.println("\n\n");
    }


    /**
     *
     * @param itemArray
     * @param currencySelection
     */
    private void displayMoneyCounter(Item[] itemArray,
                                            int currencySelection)
    {
        char usd_symbol = 36;
        char eur_symbol = 8364;
        char jpy_symbol = 165;
        char symbol;

        switch (currencySelection)
        {
            case 0:
                symbol = usd_symbol;
                break;
            case 1:
                symbol = eur_symbol;
                break;
            case 2:
                symbol = jpy_symbol;
                break;
            default:
                symbol = usd_symbol;
        }

        String id = "ID";
        String name = "Name";
        String value = "Value";
        String quantity = "Quantity";

        System.out.println();
        System.out.println("-------------------------------------------------");
        System.out.println("|               Money Counter (" + symbol +
                ")               |");
        System.out.println("-------------------------------------------------");

        System.out.printf("%1$5s", id);
        System.out.printf("%1$15s", name);
        System.out.printf("%1$10s", value);
        System.out.printf("%1$10s", quantity);

        for (int i = 0; i < itemArray.length; i++)
        {
            System.out.printf("\n%1$5s", itemArray[i].getID());
            System.out.printf("%1$15s", itemArray[i].getName());
            System.out.printf("%1$10s", itemArray[i].getValue());
            System.out.printf("%1$10s", itemArray[i].getQuantity());
        }

        System.out.println("\n\n");
    }


    /**
     *
     */
    private void displayAllInfo(Item[] currency, Item[] inventory,
                                       Item[] moneyCounter,
                                       int currencySelection)
    {
        System.out.println("Here is the most current information.");
        displayVendingCurrency(currency,currencySelection);
        displayMoneyCounter(moneyCounter,currencySelection);
        displayInventory(inventory);


    }


    /**
     *
     */
    private Item[] createMoneyCounter(Item[] currency)
    {
        int length = currency.length;
        Item[] moneyCounter = new Item[length];

        for (int i = 0; i < length; i++)
        {
            moneyCounter[i] = new Item(
                    currency[i].getID(),
                    currency[i].getName(),
                    currency[i].getValue(),
                    currency[i].getQuantity());

            moneyCounter[i].setQuantity(0);
        }

        return moneyCounter;
    }


    /**
     *
     */
    private void runVM(int mainSelection,
                       Item[] currency,
                       Item[] inventory,
                       Item[] moneyCounter,
                       int currencySelection)
    {
        UserInput mainMenu = new UserInput(4);

        UserInput moneyMenu = new UserInput(currency.length);
        int moneySelection;

        UserInput inventoryMenu = new UserInput(inventory.length);
        int inventorySelection;

        while (mainSelection != 0)
        {
            options.printMainOptions();
            mainSelection = mainMenu.getUserSelection();

            switch (mainSelection)
            {
                case 0:
                    System.out.println("\nGoodbye!");
                    mainSelection = 0;
                    break;
                case 1:
                    displayAllInfo(currency,inventory,moneyCounter,currencySelection);
                    moneySelection = moneyMenu.getUserSelection();
                    insertMoney(moneySelection,currency,moneyCounter);
                    mainSelection = -1;
                    break;
                case 2:
                    removeAllMoney(currency,moneyCounter);
                    displayAllInfo(currency,inventory,moneyCounter,currencySelection);
                    mainSelection = -1;
                    break;
                case 3:
                    mainSelection = -1;
                    break;
                default:
                    mainSelection = -1;
            }


        }
    }


    /**
     *
     * @param moneySelection
     * @param currency
     * @param moneyCounter
     */
    private void insertMoney(int moneySelection,
                             Item[] currency,
                             Item[] moneyCounter)
    {
        currency[moneySelection].incrementQuantity();
        moneyCounter[moneySelection].incrementQuantity();
    }


    /**
     *
     * @param currency
     * @param moneyCounter
     */
    private void removeAllMoney(Item[] currency,
                                Item[] moneyCounter)
    {
        for (int i = 0; i < currency.length; i++)
        {
            int x = currency[i].getQuantity();
            x = x - moneyCounter[i].getQuantity();
            currency[i].setQuantity(x);
            moneyCounter[i].setQuantity(0);
        }
    }




}