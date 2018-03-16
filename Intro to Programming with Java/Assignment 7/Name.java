/**
 * All fields are required to be non-blank.
 * Issue appropriate error messages when incorrect data is entered.
 */

public class Name
{

    // declare Name variables
    private String employeeNameFirst;
    private String employeeNameLast;


    /** CONSTRUCTORS */

    // no-argument constructor
    public Name()
    {
        // employee name
        employeeNameFirst = "";
        employeeNameLast = "";
    }

    // constructor for all variables
    public Name(String firstName, String lastName)
    {
        // employee name
        employeeNameFirst = firstName;
        employeeNameLast = lastName;
    }


    /** METHODS */

    public void setFirstName(String firstName)
    {
        employeeNameFirst = firstName;
    }

    public void setLastName(String lastName)
    {
        employeeNameLast = lastName;
    }

    public String getFirstName()
    {
        return employeeNameFirst;
    }

    public String getLastName()
    {
        return employeeNameLast;
    }

}