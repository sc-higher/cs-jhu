/**
 * An address consists of a street, a city, a state (2 characters),
 * and a 5-digit zip code.
 * All fields are required to be non-blank.
 * Issue appropriate error messages when incorrect data is entered.
 */

public class Address
{

    // declare address variables
    private String addressStreet;
    private String addressCity;
    private String addressState;
    private String addressZip;


    /** CONSTRUCTORS */

    // no-argument constructor
    public Address()
    {
        // employee address
        addressStreet = "";
        addressCity = "";
        addressState = "";
        addressZip = "";
    }

    // constructor for all arguments
    public Address(String street, String city, String state, String zip)
    {
        // employee address
        addressStreet = street;
        addressCity = city;
        addressState = state;
        addressZip = zip;
    }

    /** METHODS */

    public void setStreet(String street)
    {
        addressStreet = street;
    }

    public void setCity(String city)
    {
        addressCity = city;
    }

    public void setState(String state)
    {
        addressState = state;
    }

    public void setZip(String zip)
    {
        addressZip = zip;
    }

    public String getStreet()
    {
        return addressStreet;
    }

    public String getCity()
    {
        return addressCity;
    }

    public String getState()
    {
        return addressState;
    }

    public String getZip()
    {
        return addressZip;
    }

}