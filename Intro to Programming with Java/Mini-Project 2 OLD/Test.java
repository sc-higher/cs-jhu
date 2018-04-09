/**
 * This program is my response to Mini-Project 2 for the class 605.201.81 Intro
 * to Programming Using Java at the JHU EPP CS program.
 *
 * <TEXT HERE>
 *
 * @author: Sean Connor
 */


import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Test
{

    public static final Scanner input = new Scanner(System.in);

    public static void main(String[] args)
    {
        // TEST ReadCSV

        String filename = "product-list-1.csv";

        ReadCSV productFromCSV = new ReadCSV(filename);

        productFromCSV.toArray();

        System.out.println();
        productFromCSV.print();

        // TEST numRows numCols
        System.out.println("\nNumber of rows: " + productFromCSV.numRows());
        System.out.println("Number of columns: " + productFromCSV.numCols());


        // TEST ENUMS AND SYMBOLS

        char usd_symbol = 36;
        char eur_symbol = 8364;
        char jpy_symbol = 165;

//        System.out.println("\nValue of quarter: " + usd_symbol +
//                (double) USD.QUARTER.getValue()/100);
//
//        System.out.println("\nFive EUR: " + eur_symbol +
//                (double) EUR.FIVEN.getValue()/100);
//
//        System.out.println("\nOne hundred JPY: " + jpy_symbol +
//                JPY.HUNDRED.getValue());

        // ReadCSV product contents to separate Item objects in arraylist
        ArrayList<Item> productList = new ArrayList<Item>();
        toItemObject(productFromCSV, productList);

        System.out.println("\nProduct 1 Name: " + productList.get(0).getName());
        System.out.println("Product 1 Price: " + productList.get(0).getValue());
        System.out.println("Product 1 Quantity: " + productList.get(0).getQuantity());

        System.out.println("\nProduct 2 Name: " + productList.get(1).getName());
        System.out.println("Product 2 Price: " + productList.get(1).getValue());
        System.out.println("Product 2 Quantity: " + productList.get(1).getQuantity());

        System.out.println("productList length: " + productList.size());

        // TEST displayInventory method
        displayInventory(productList);
        productList.get(0).setQuantity(5);
        displayInventory(productList);

        // TEST currency selection
        System.out.println("TESTING CURRENCY FROM CSV");
        filename = "JPY.csv";

        ReadCSV currencyFromCSV = new ReadCSV(filename);

        currencyFromCSV.toArray();

        System.out.println();
        currencyFromCSV.print();

        System.out.println("\nNumber of rows: " + currencyFromCSV.numRows());
        System.out.println("Number of columns: " + currencyFromCSV.numCols());

        ArrayList<Item> vendingCurrencyList = new ArrayList<Item>();
        toItemObject(currencyFromCSV, vendingCurrencyList);
        displayVendingCurrency(vendingCurrencyList);


//        System.out.println("\nOne hundred JPY: " + jpy_symbol +
//                currency.HUNDRED.getValue());


        // TEST UserInput object
        UserInput testmenu = new UserInput(2);
        int num = testmenu.getUserSelection();
        System.out.println("selection is: " + num);

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