/**
 * An employee has an employee number, a name, an address, and a hire date.
 * All fields are required to be non-blank.
 * Issue appropriate error messages when incorrect data is entered.
 */

public class Employee
{

    // declare employee variables
    private int employeeNumber;
    private Name employeeName;
    private Address employeeAddress;
    private Date employeeDate;


    /** CONSTRUCTORS */

    // no-argument constructor
    public Employee()
    {
        // employee number
        employeeNumber = 0;
        // employee name
        employeeName = new Name();
        // employee address
        employeeAddress = new Address();
        // employee date
        employeeDate = new Date();

    }

    // constructor for all arguments
    public Employee(int number, Name name, Address address, Date date)
    {
        // employee number
        employeeNumber = number;
        // employee name
        employeeName = name;
        // employee address
        employeeAddress = address;
        // employee date
        employeeDate = date;

    }


    /** METHODS */

    // employee number set/get methods
    public void setNumber(int number)
    {
        employeeNumber = number;
    }

    public int getNumber()
    {
        return employeeNumber;
    }

    // employee name set/get methods
    public void setName(Name name)
    {
        employeeName = name;
    }

    public Name getName()
    {
        return employeeName;
    }

    // employee address set/get methods
    public void setAddress(Address address)
    {
        employeeAddress = address;
    }

    public Address getAddress()
    {
        return employeeAddress;
    }

    // employee date set/get methods
    public void setDate(Date date)
    {
        employeeDate = date;
    }

    public Date getDate()
    {
        return employeeDate;
    }


}