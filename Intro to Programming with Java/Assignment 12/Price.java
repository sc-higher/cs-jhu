/**
 * This program is part of my response to Assignment 12 for the class 605.201.81
 * Intro to Programming Using Java at the JHU EPP CS program.
 *
 * This program allows the user to create a Price object that stores size and
 * topping selection data. Topping selection data is stored as a boolean array.
 * Methods are included that allow the PriceCalculator class to set size and
 * topping selections and also to calculate price whenever there is a change.
 * Total cost is automatically formated to USD-style and returned as a String.
 *
 * @author: Sean Connor
 */

import java.util.Locale;
import java.text.NumberFormat;

public class Price
{
    // initialize variables
    private int size;
    private int numberAvailableToppings;
    private double costPerTopping;
    private double sizeCost;
    NumberFormat n = NumberFormat.getCurrencyInstance(Locale.US);

    // constructor
    public Price(int numberAvailableToppings)
    {
        this.numberAvailableToppings = numberAvailableToppings;
    }

    private boolean[] toppings = new boolean[4];


    /**
     *
     * @param size
     */
    public void setSize(int size)
    {
        this.size = size;
    }


    /**
     *
     * @param topping
     * @param status
     */
    public void setTopping(int topping, boolean status)
    {
        toppings[topping] = status;
    }


    /**
     *
     * @return
     */
    public String printTotalCost()
    {
        // set topping/size cost
        if (size == 0)
        {
            costPerTopping = 0.5;
            sizeCost = 5.0;
        }
        else if (size == 1)
        {
            costPerTopping = 1.0;
            sizeCost = 8.0;
        }
        else
        {
            costPerTopping = 1.75;
            sizeCost = 11.5;
        }

        // calculate topping cost
        double toppingCost = 0;
        if ( !toppings[0] ) // checks if plain or not
        {
            for ( boolean item : toppings )
            {
                if ( item )
                {
                    toppingCost = toppingCost + costPerTopping;
                }
            }
        }

        // calculate total cost
        double totalCost = toppingCost + sizeCost;

        // format total cost to USD
        String s = n.format(totalCost);

        // return total cost as formatted String
        return s;
    }

}