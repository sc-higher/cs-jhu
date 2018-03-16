/**
 *
 *
 */

public class HourlyEmployee extends Employee
{

    // declare HourlyEmployee variables
    private double payRate;
    private double hoursWorked;
    private double totalEarnings;

    /** CONSTRUCTORS */

    // no-argument constructor
    public HourlyEmployee()
    {
        super();
        payRate = 0;
        hoursWorked = 0;
        totalEarnings = 0;
    }

    // constructor for all variables
    public HourlyEmployee(int number, Name name, Address address, Date date,
                            double rate, double hours)
    {
        super(number, name, address, date);
        payRate = rate;
        hoursWorked = hours;
        totalEarnings = calculateEarnings(payRate, hoursWorked);
    }


    /** METHODS */

    // totalEarnings calculation
    private double calculateEarnings(double payRate, double hoursWorked)
    {
        double earnings;

        if ( hoursWorked <= 40 )
        {
            earnings = hoursWorked * payRate;
        }

        else
        {
            earnings = (payRate * 40) + ((hoursWorked - 40) * (1.5 * payRate));
        }

        return earnings;
    }

    // payRate get/set
    public double getPayRate()
    {
        return payRate;
    }

    public void setPayRate(double payRate)
    {
        this.payRate = payRate;
    }

    // hoursWorked get/set
    public double getHoursWorked()
    {
        return hoursWorked;
    }

    public void setHoursWorked(double hoursWorked)
    {
        this.hoursWorked = hoursWorked;
    }

    // totalEarnings get
    public double getTotalEarnings()
    {
        return totalEarnings;
    }

}