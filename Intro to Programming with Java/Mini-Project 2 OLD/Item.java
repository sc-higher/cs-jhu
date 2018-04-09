public class Item
{
    // initialize variables
    String name;
    int value;
    int quantity;

    // Product constructor
    public Item(String name, int value, int quantity)
    {
        this.name = name;
        this.value = value;
        this.quantity = quantity;
    }

    // get and set methods
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getValue()
    {
        return value;
    }

    public void setValue(int value)
    {
        this.value = value;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

}