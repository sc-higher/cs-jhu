/**
 *
 *
 */

public class SalariedEmployee extends Employee
{

    // declare SalariedEmployee variables
    private int annualSalary;

    /** CONSTRUCTORS */

    // no-argument constructor
    public SalariedEmployee()
    {
        super();
        annualSalary = 0;
    }

    // constructor for all variables
    public SalariedEmployee(int number, Name name, Address address, Date date,
                            int salary)
    {
        super(number, name, address, date);
        annualSalary = salary;
    }


    /** METHODS */

    public int getSalary()
    {
        return annualSalary;
    }

    public void setSalary(int salary)
    {
        annualSalary = salary;
    }

}