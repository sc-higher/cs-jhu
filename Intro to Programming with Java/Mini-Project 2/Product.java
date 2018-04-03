public class Product
{
    // initialize variables
    String name;
    int quantity;
    int price;

    // Product constructor
    public Product(String name, int quantity, int price)
    {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
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

    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    public int getPrice()
    {
        return price;
    }

    public void setPrice(int price)
    {
        this.price = price;
    }


}