/**
 * This program is part of my response to Assignment 11 for the class 605.201.81
 * Intro to Programming Using Java at the JHU EPP CS program.
 *
 * This class models a contact. Contains standard get/set methods for all
 * descriptive variables (i.e. first name, last name, etc)
 *
 * @author: Sean Connor
 */

public class Contact
{
    private String lastName;
    private String firstName;
    private String companyName;
    private String phoneNumber;
    private String emailAddress;


    public Contact(String lastName,
                   String firstName,
                   String companyName,
                   String phoneNumber,
                   String emailAddress)
    {
        this.lastName = lastName;
        this.firstName = firstName;
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }


    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getCompanyName()
    {
        return companyName;
    }

    public void setCompanyName(String companyName)
    {
        this.companyName = companyName;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress()
    {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress)
    {
        this.emailAddress = emailAddress;
    }

}