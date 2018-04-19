/**
 * This program is my response to Project 2 for the class 605.201.81 Intro
 * to Programming Using Java at the JHU EPP CS program.
 * This class contains a main() method and is the primary class for the
 * Vending Machine Simulator program.
 * @author: Sean Connor
 * Date:    15 April 2018
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
        UserInput currencySelectionMenu = new UserInput(1,4);
        options.printCurrencyOptions();
        int currencyTypeSelection = currencySelectionMenu.getUserSelection();
        String filenameCurrency = vms.setCurrencyFile(currencyTypeSelection);

        // Get user info for VM inventory
        UserInput inventorySelectionMenu = new UserInput(1,3);
        options.printInventoryOptions();
        int inventoryTypeSelection = inventorySelectionMenu.getUserSelection();
        String filenameInventory = vms.setInventoryFile(inventoryTypeSelection);

        // Load VM currency from CSV based on user selection
        ReadCSV currencyFromCSV = new ReadCSV(filenameCurrency);
        currencyFromCSV.toArray();
        Item[] currency = currencyFromCSV.getItemArray();

        // Load VM inventory from CSV based on user selection
        ReadCSV inventoryFromCSV = new ReadCSV(filenameInventory);
        inventoryFromCSV.toArray();
        Item[] inventory = inventoryFromCSV.getItemArray();

        // Create VM money counter
        Item[] moneyCounter = vms.createMoneyCounter(currency);

        // Display vending machine money and inventory, and wallet money
        System.out.println("\nGreat, let's get started!");

        vms.displayAllInfo(currency,
                           inventory,
                           moneyCounter,
                           currencyTypeSelection);

        // Run the VM simulator
        int mainSelection = Integer.valueOf(-1);

        vms.runVM(mainSelection,
                  currency,
                  inventory,
                  moneyCounter,
                  currencyTypeSelection);
    }


    /*-------------*/
    /*   METHODS   */
    /*-------------*/


    /**
     * This method accepts an integer representing the currency selection
     * (USD, EUR, JPY, etc) and sets filename equal to the file containing
     * the currency information.
     *
     * @param currencySelection   int indicating currency selection
     * @return   String representing currency filename
     */
    private String setCurrencyFile(int currencySelection)
    {
        String filename = null;
        boolean input_test = true;

        switch (currencySelection)
        {
            case 1:
                filename = "USD.csv";
                break;
            case 2:
                filename = "EUR.csv";
                break;
            case 3:
                filename = "JPY.csv";
                break;
            case 4:
                while (input_test)
                {
                    System.out.print("\nPlease enter filename: ");
                    filename = input.nextLine();
                    File f = new File(filename);
                    if(f.exists() && !f.isDirectory())
                    {
                        input_test = false;
                    }
                    else
                    {
                        System.out.println("\nFile not found. Please " +
                        "try again.\n");
                    }
                }
                break;
            default:
                filename = "USD.csv";
        }

        return filename;
    }


    /**
     * This method accepts an integer representing the inventory selection
     * (drinks, snacks, other) and sets filename equal to the file containing
     * the inventory information.
     *
     * @param inventorySelection   int indicating inventory selection
     * @return   String representing inventory filename
     */
    private String setInventoryFile(int inventorySelection)
    {
        String filename = null;
        boolean input_test = true;

        switch (inventorySelection)
        {
            case 1:
                filename = "drinks.csv";
                break;
            case 2:
                filename = "snacks.csv";
                break;
            case 3:
                while (input_test)
                {
                    System.out.print("\nPlease enter filename: ");
                    filename = input.nextLine();
                    File f = new File(filename);
                    if(f.exists() && !f.isDirectory())
                    {
                        input_test = false;
                    }
                    else
                    {
                        System.out.println("\nFile not found. Please " +
                        "try again.\n");
                    }
                }
                break;
            default:
                filename = "drinks.csv";
        }

        return filename;
    }


    /**
     * This method accepts an Item[] representing the inventory and prints out
     * information for each item in the array.
     *
     * @param itemArray   Item[] containing all inventory Items
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
     * This method accepts an Item[] representing the currency to be used and
     * the amount held by the vending machine. Also accepts an int value
     * representing the currency type selection. Prints information for each
     * currency Item in the array.
     *
     * @param itemArray   Item[] containing all currency Items
     * @param currencySelection   int indicating currency selection
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
            case 1:
                symbol = usd_symbol;
                break;
            case 2:
                symbol = eur_symbol;
                break;
            case 3:
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
     * This method accepts an Item[] representing the money inserted into the
     * vending machine during a particular purchase process. Also accepts an
     * int value representing the currency type selection. Prints information
     * for each currency Item in the array.
     *
     * @param itemArray   Item[] containing all currency Items
     * @param currencySelection   int indicating currency selection
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
            case 1:
                symbol = usd_symbol;
                break;
            case 2:
                symbol = eur_symbol;
                break;
            case 3:
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
     * This method displays all information to include inventory, vending
     * machine currency held, and the per use money counter.
     *
     * @param currency   Item[] containing all currency Items
     * @param inventory   Item[] containing all inventory Items
     * @param moneyCounter   Item[] containing all money counter Items
     * @param currencySelection   int indicating currency selection
     */
    private void displayAllInfo(Item[] currency,
                                Item[] inventory,
                                Item[] moneyCounter,
                                int currencySelection)
    {
        System.out.println("\nHere is the most current information.");
        displayVendingCurrency(currency,currencySelection);
        displayMoneyCounter(moneyCounter,currencySelection);
        displayInventory(inventory);
    }


    /**
     * This method creates an Item[] representing the per use money counter.
     * All denominations are set to zero. Returns the Item[].
     *
     * @param currency   Item[] containing all currency Items
     * @return   Item[] representing the per use money counter
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
     * This method is the primary method used to run the vending machine
     * simulator.
     *
     * @param mainSelection   int representing main menu user selection
     * @param currency   Item[] containing all currency Items
     * @param inventory   Item[] containing all inventory Items
     * @param moneyCounter   Item[] containing all money counter Items
     * @param currencySelection   int indicating currency selection
     */
    private void runVM(int mainSelection,
                       Item[] currency,
                       Item[] inventory,
                       Item[] moneyCounter,
                       int currencySelection)
    {
        UserInput mainMenu = new UserInput(1,5);

        UserInput moneyMenu = new UserInput(0,currency.length-1);
        int moneySelection;
        int totalMoney;

        UserInput continueMenu = new UserInput(1,2);
        int continueSelection;

        UserInput inventoryMenu = new UserInput(0,inventory.length-1);
        int inventorySelection;

        options.printMainOptions();

        while (mainSelection != 5)
        {
            mainSelection = mainMenu.getUserSelection();

            switch (mainSelection)
            {
                case 1:
                    options.printMainOptions();
                    mainSelection = -1;
                    break;

                case 2:
                    displayInventory(inventory);
                    mainSelection = -1;
                    break;

                case 3:
                    displayVendingCurrency(currency,currencySelection);
                    mainSelection = -1;
                    break;

                case 4:
                    // Select an item to purchase.
                    System.out.println("\nSelect an item from the " +
                                    "inventory list.");
                    inventorySelection = inventoryMenu.getUserSelection();

                    // Attempt purchase. Check inventory quantity.
                    if ( inventory[inventorySelection].getQuantity() > 0 )
                    {
                        // Insert money.
                        boolean add_more_money = true;
                        while ( add_more_money )
                        {
                            System.out.println("\nSelect a denomination " +
                                            "from the currency list.");
                            displayMoneyCounter(moneyCounter,currencySelection);
                            moneySelection = moneyMenu.getUserSelection();
                            insertMoney(moneySelection,currency,moneyCounter);
                            options.printContinueOptions();
                            // Check if user wants to insert more money
                            continueSelection = continueMenu.getUserSelection();
                            if ( continueSelection == 2 )
                            {
                                add_more_money = false;
                            }
                        }
                        totalMoney = totalMoney(moneyCounter);

                        // Purchase the item
                        purchaseItem(totalMoney,
                                     inventorySelection,
                                     inventory,
                                     currency,
                                     moneyCounter);
                    }

                    else
                    {
                        System.out.println("\nInsufficient inventory. " +
                                "Please choose another item.");
                    }

                    mainSelection = -1;
                    break;

                case 5:
                    System.out.println("\nGoodbye!\n");
                    mainSelection = 5;
                    break;

                default:
                    mainSelection = -1;
            }


        }
    }


    /**
     * This method represents inserting money into the vending machine.
     *
     * @param moneySelection   int indicating currency selection
     * @param currency   Item[] containing all currency Items
     * @param moneyCounter   Item[] containing all money counter Items
     */
    private void insertMoney(int moneySelection,
                             Item[] currency,
                             Item[] moneyCounter)
    {
        currency[moneySelection].incrementQuantity();
        moneyCounter[moneySelection].incrementQuantity();
    }


    /**
     * This method represents canceling a transaction and returning all money
     * to the user from the vending machine.
     *
     * @param currency   Item[] containing all currency Items
     * @param moneyCounter   Item[] containing all money counter Items
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


    /**
     * This method is used to calculate the total value of money inserted by
     * the user.
     *
     * @param moneyCounter   Item[] containing all money counter Items
     * @return   int value representing total value of money inserted
     */
    private int totalMoney(Item[] moneyCounter)
    {
        int sum = 0;

        for (int i = 0; i < moneyCounter.length; i++)
        {
            sum = sum + ( moneyCounter[i].getQuantity() *
                    moneyCounter[i].getValue() );
        }

        return sum;
    }


    /**
     * This method represents purchasing an item. Checks if sufficient money
     * has been inserted to purchase the selected item. Checks if sufficient
     * change is available to return to the user. Returns change to user
     * after purchase.
     *
     * @param totalMoney   int representing total value of money inserted
     * @param itemSelection   int indicating currency selection
     * @param inventory   Item[] containing all inventory Items
     * @param currency   Item[] containing all currency Items
     * @param moneyCounter   Item[] containing all money counter Items
     */
    private void purchaseItem(int totalMoney,
                              int itemSelection,
                              Item[] inventory,
                              Item[] currency,
                              Item[] moneyCounter)
    {
        int[] change;

        if ( totalMoney >= inventory[itemSelection].getValue() )
        {
            change = calculateChange(totalMoney,
                                     itemSelection,
                                     inventory,
                                     currency,
                                     moneyCounter);

            if ( !(change[currency.length] == 1) )
            {
                inventory[itemSelection].decrementQuantity();
                System.out.println("\nPurchase successful!");
                returnChange(change,moneyCounter,currency);
            }

            else
            {
                System.out.println("\nPurchase failed. Insufficient change.");
                System.out.println("All money returned.");
            }

        }

        else
        {
            System.out.println("\nPurchase failed. Insufficient funds.");
            removeAllMoney(currency,moneyCounter);
        }

    }


    /**
     * This method calculates the change to give to the user after a purchase.
     * Ensures that adequate quantity of money exists in the vending machine to
     * succesfuly provide change. Calculates change by denomination, not total
     * value. Highest denominations are prioritized (change returned
     * with as few bills/coins as possible).
     *
     * @param totalMoney   int representing total value of money inserted
     * @param itemSelection   int indicating currency selection
     * @param inventory   Item[] containing all inventory Items
     * @param currency   Item[] containing all currency Items
     * @param moneyCounter   Item[] containing all money counter Items
     * @return int[] representing amount of each denomination to return
     */
    private int[] calculateChange(int totalMoney,
                                  int itemSelection,
                                  Item[] inventory,
                                  Item[] currency,
                                  Item[] moneyCounter)
    {
        int delta = totalMoney - inventory[itemSelection].getValue();

        // Indices 0 to n-1 store int values representing change to give
        // Index n stores int value indicating success/fail of change calc
        int[] change = new int[currency.length + 1];

        int mod;
        int remainder;

        for (int i = (currency.length - 1); i >= 0 ; i--)
        {
            mod = delta % currency[i].getValue();
            remainder = (int) Math.floor( delta / currency[i].getValue() );

            //
            if ( mod == 0 && remainder <= currency[i].getQuantity() )
            {
                change[i] = remainder;
                break;
            }

            //
            else if ( remainder <= currency[i].getQuantity() )
            {
                change[i] = remainder;
                delta = delta - ( change[i] * currency[i].getValue() );
            }

            //
            else if ( i == 0 )
            {
                removeAllMoney(currency,moneyCounter);

                // This index is used to indicate success/fail of change calc
                // Value of 1 indicates fail
                change[currency.length] = 1;

                break;
            }

            //
            else
            {
                change[i] = 0;
            }
        }

        return change;
    }


    /**
     * This method returns the change to the user, removing it from the
     * vending machine in the process and reseting the money counter Items to
     * zero quantity.
     *
     * @param change   int[] representing amount of each denomination to return
     * @param moneyCounter   Item[] containing all money counter Items
     * @param currency   Item[] containing all currency Items
     */
    private void returnChange(int[] change,
                              Item[] moneyCounter,
                              Item[] currency)
    {
        int x;
        System.out.println("\nReturned the following change:");
        for (int i = 0; i < moneyCounter.length; i++)
        {
            System.out.println(currency[i].getName() + ": " + change[i]);
            x = currency[i].getQuantity();
            currency[i].setQuantity( x - change[i] );
            moneyCounter[i].setQuantity(0);
        }
    }

}