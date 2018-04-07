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

        System.out.println("\nValue of quarter: " + usd_symbol +
                (double) USD.QUARTER.getValue()/100);

        System.out.println("\nFive EUR: " + eur_symbol +
                (double) EUR.FIVEN.getValue()/100);

        System.out.println("\nOne hundred JPY: " + jpy_symbol +
                JPY.HUNDRED.getValue());

        // ReadCSV product contents to separate Product objects in arraylist
        ArrayList<Product> productList = new ArrayList<Product>();
        toProductObject(productFromCSV, productList);

        System.out.println("\nProduct 1 Name: " + productList.get(0).getName());
        System.out.println("Product 1 Quantity: " + productList.get(0).getQuantity());
        System.out.println("Product 1 Price: " + productList.get(0).getPrice());

        System.out.println("\nProduct 2 Name: " + productList.get(1).getName());
        System.out.println("Product 2 Quantity: " + productList.get(1).getQuantity());
        System.out.println("Product 2 Price: " + productList.get(1).getPrice());

        System.out.println("productList length: " + productList.size());

        // TEST displayInventory method
        displayInventory(productList);
        productList.get(0).setQuantity(5);
        displayInventory(productList);

        // TEST UserInput object
        UserInput testmenu = new UserInput(2);
        int num = testmenu.getUserSelection();
        System.out.println("selection is: " + num);

    }


    /**
     *
     * @param productList
     */
    private static void displayInventory(ArrayList<Product> productList)
    {
        String name = "Name";
        String quantity = "Quantity";
        String price = "Price";

        System.out.println();
        System.out.println("              Current Inventory              ");
        System.out.println("---------------------------------------------");

        System.out.printf("%1$15s", name);
        System.out.printf("%1$15s", quantity);
        System.out.printf("%1$15s", price);

        for (int i = 0; i < productList.size(); i++)
        {
            System.out.printf("\n%1$15s", productList.get(i).getName());
            System.out.printf("%1$15s", productList.get(i).getQuantity());
            System.out.printf("%1$15s", productList.get(i).getPrice());
        }

        System.out.println("\n\n");
    }


    /**
     *
     * @param productFromCSV
     * @param productList
     */
    private static void toProductObject(ReadCSV productFromCSV,
                                        ArrayList<Product> productList)
    {
        for (int i = 1; i < productFromCSV.numRows(); i++)
        {
            String name = productFromCSV.getValue(i,0);
            int quantity = Integer.parseInt(productFromCSV.getValue(i,1));
            int price = Integer.parseInt(productFromCSV.getValue(i,2));

            productList.add( new Product(name,quantity,price) );
        }
    }



}