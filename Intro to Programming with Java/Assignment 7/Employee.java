/**
 * An employee has an employee number, a name, an address, and a hire date.
 * All fields are required to be non-blank.
 * Issue appropriate error messages when incorrect data is entered.
 */

public class Employee
{

    // declare Employee variables
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

    // constructor for all variables
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

    // SalariedEmployee set/get methods (avoid downcasting)
    public int getSalary()
    {
        return 0;
    }

    public void setSalary(int salary)
    {
        System.out.println(salary);
    }

    // HourlyEmployee set/get methods (avoid downcasting)
    public double getPayRate()
    {
        return 0.0;
    }

    public void setPayRate(double payRate)
    {
        System.out.println(payRate);
    }

    // hoursWorked get/set
    public double getHoursWorked()
    {
        return 0.0;
    }

    public void setHoursWorked(double hoursWorked)
    {
        System.out.println(hoursWorked);
    }

    // totalEarnings get
    public double getTotalEarnings()
    {
        return 0.0;
    }


}