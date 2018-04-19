/**
 * This program is my response to Project 2 for the class 605.201.81 Intro
 * to Programming Using Java at the JHU EPP CS program.
 * This class is used to create a way to store different things (products,
 * currency denominations, etc) as Java objects. Each Item object has a number
 * of characteristics - these include ID, Name, Value, and Quantity.
 * @author: Sean Connor
 * Date:    15 April 2018
 */

public class Item
{
    // initialize variables
    String id;
    String name;
    int value;
    int quantity;

    // constructor
    /**
     * This constructor requires four arguments to specificy the ID, Name,
     * Value, and Quantity of the Item object instance.
     *
     * @param id
     * @param name
     * @param value
     * @param quantity
     */
    public Item(String id, String name, int value, int quantity)
    {
        this.id = id;
        this.name = name;
        this.value = value;
        this.quantity = quantity;
    }


    /**
     * This method returns the Item's ID.
     *
     * @return
     */
    public String getID()
    {
        return id;
    }


    /**
     * This method sets the Item's quantity to the specified String value.
     *
     * @param id
     */
    public void setID(String id)
    {
        this.id = id;
    }


    /**
     * This method returns the Item's name.
     *
     * @return
     */
    public String getName()
    {
        return name;
    }


    /**
     * This method sets the Item's name to the specified String value.
     *
     * @param name
     */
    public void setName(String name)
    {
        this.name = name;
    }


    /**
     * This method returns the Item's value.
     *
     * @return
     */
    public int getValue()
    {
        return value;
    }


    /**
     * This method sets the Item's value to the specified integer value.
     *
     * @param value
     */
    public void setValue(int value)
    {
        this.value = value;
    }


    /**
     * This method returns the Item's quantity.
     *
     * @return
     */
    public int getQuantity()
    {
        return quantity;
    }


    /**
     * This method sets the Item's quantity to the specified integer value.
     *
     * @param quantity
     */
    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }


    /**
     * This method decrements the Item's quantity by one.
     */
    public void incrementQuantity()
    {
        this.quantity++;
    }


    /**
     * This method increments the Item's quantity by one.
     */
    public void decrementQuantity()
    {
        this.quantity--;
    }

}