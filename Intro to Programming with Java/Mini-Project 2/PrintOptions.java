public class PrintOptions
{
    public void printCurrencyOptions()
    {
        System.out.println("\nWelcome to Vending Machine Simulator!");
        System.out.println("Before we begin, let's set a few options.");
        System.out.println("\nPlease select a currency from the following " +
                "list of supported currencies.");
        System.out.println("(0) - USD");
        System.out.println("(1) - EUR");
        System.out.println("(2) - JPY");
    }

    public void printInventoryOptions()
    {
        System.out.println("\nPlease select an inventory from the following:");
        System.out.println("(0) - Drinks");
        System.out.println("(1) - Snacks");
    }

    public void printMainOptions()
    {
        System.out.println("\nPlease select an option from the following:");
        System.out.println("\n(0) - Quit Vending Machine Simulator");
        System.out.println("(1) - Insert money");
        System.out.println("(2) - Return all money");
        System.out.println("(3) - Make a selection");
    }
}

