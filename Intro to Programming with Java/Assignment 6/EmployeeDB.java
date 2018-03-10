/**
 * This program is my response to Assignment 6 for
 * the class 605.201.81 Intro to Programming Using
 * Java at the JHU EPP CS program.
 *
 * This program is a rudimentary employee database program.
 * Employee information is stored in the Employee class. The
 * list of employee and their respective information is stored
 * in an Employee[] array. Makes use of additional Name, Address,
 * and Date classes for get/set methods for each class of data.
 *
 * @author: Sean Connor
 */

import java.util.*;

public class EmployeeDB
{

    public static void main(String[] args)
    {

        Scanner input = new Scanner(System.in);

        // prompt user for number of employees to enter
        Integer numberEmployees = Integer.valueOf(0);
        String employeestring = "";

        // make sure input is valid
        while ( numberEmployees < 1 )
        {
            System.out.print("\nPlease enter the number of employees to enter: ");
            employeestring = input.nextLine();

            // check if nothing was entered
            if ( employeestring.isEmpty() )
            {
                System.out.println("Invalid input, try again.");
            }

            else
            {
                // if something was entered, make sure is int
                // is greater than 0
                try
                {
                    numberEmployees = Integer.valueOf(employeestring);
                    if ( numberEmployees < 1 )
                    {
                        System.out.println("Invalid input, try again.");
                    }

                }
                catch (NumberFormatException e)
                {
                    System.out.println("Invalid input, try again.");
                }
            }
        }


        // initialize Employee object array
        Employee[] employeeArray = new Employee[numberEmployees];

        // set data for each employee
        for (int i = 0; i < numberEmployees; i++)
        {
            employeeArray[i] = new Employee();

            // set employee number (increasing numeric order)
            inputNumber(i, employeeArray);
            // get user input for employee name
            inputName(i, input, employeeArray);
            // get user input for employee address
            inputAddress(i, input, employeeArray);
            // get user input for employee hire date
            inputDate(i, input, employeeArray);

        }


        // print all employee information to console
        for (int i = 0; i < numberEmployees; i++)
        {
            // print employee number
            System.out.println("");
            System.out.println("-------------------------------------------");
            System.out.println("Employee Number: " +
                    employeeArray[i].getNumber());

            // print employee name
            String ename = employeeArray[i].getName().getLastName() +
                    ", " + employeeArray[i].getName().getFirstName();
            System.out.println("Employee Name: " + ename.toUpperCase());

            // print employee address
            String eaddress = employeeArray[i].getAddress().getStreet() +
                    ", " + employeeArray[i].getAddress().getCity() +
                    ", " + employeeArray[i].getAddress().getState() +
                    " " + employeeArray[i].getAddress().getZip();
            System.out.println("Employee Address: " + eaddress.toUpperCase());

            // print employee hire date
            System.out.println("Employee Hire Date: " +
                    employeeArray[i].getDate().getMonth() +
                    "/" + employeeArray[i].getDate().getDay() +
                    "/" + employeeArray[i].getDate().getYear());
        }

        System.out.println();

    }


    /**
     * This method will set the employee number equal to the Employee
     * array location value i.
     *
     * @param i
     * @param empArray
     */
    public static void inputNumber(int i, Employee[] empArray)
    {
        System.out.println();
        System.out.print("Employee Number: " + i);
        empArray[i].setNumber(i);
        System.out.println();
    }


    /**
     * This method will prompt user for employee name information to
     * include first and last name. Method will then set the Employee[i]
     * information.
     *
     * @param i
     * @param input
     * @param empArray
     */
    public static void inputName(int i, Scanner input, Employee[] empArray)
    {
        // get first name
        System.out.print("\nFirst Name: ");
        String firstname = input.nextLine();

        // use while-loop to be sure input is not blank
        while ( firstname.isEmpty() )
        {
            System.out.print("Please enter a valid name: ");
            firstname = input.nextLine();
        }

        // get last name
        System.out.print("Last Name: ");
        String lastname = input.nextLine();

        // use while-loop to be sure input is not blank
        while ( lastname.isEmpty() )
        {
            System.out.print("Please enter a valid name: ");
            lastname = input.nextLine();
        }

        // set names in Employee array
        empArray[i].getName().setFirstName(firstname);
        empArray[i].getName().setLastName(lastname);
    }


    /**
     * This method will prompt user for employee address information to
     * include street, city, state, and zip. Method will then set the
     * Employee[i] information. Only two-character (letter) state values
     * and five-digit numeric zip codes are accepted.
     *
     * @param i
     * @param input
     * @param empArray
     */
    public static void inputAddress(int i, Scanner input, Employee[] empArray)
    {
        System.out.println("Please enter employee address.");

        // get street
        System.out.print("Street: ");
        String street = input.nextLine();

        // use while-loop to be sure input is valid
        while ( street.isEmpty() )
        {
            System.out.print("Please enter a valid Street: ");
            street = input.nextLine();
        }

        // get city
        System.out.print("City: ");
        String city = input.nextLine();

        // use while-loop to be sure input is valid
        while ( city.isEmpty() )
        {
            System.out.print("Please enter a valid City: ");
            city = input.nextLine();
        }

        // get state
        System.out.print("State: ");
        String state = input.nextLine();
        // replace all non-letters with empty
        String state2 = state.replaceAll("[^\\p{IsAlphabetic}]", "");

        // use while-loop to be sure input is valid
        while ( (state.length() != 2) || (state2.length() != 2))
        {
            System.out.print("Please enter a valid State: ");
            state = input.nextLine();
            state2 = state.replaceAll("[^\\p{IsAlphabetic}]", "");
        }

        // get zip
        System.out.print("ZIP: ");
        String zip = input.nextLine();
        // replace all non-digits with empty
        String zip2 = zip.replaceAll("[\\D]", "");

        // use while-loop to be sure input is valid
        while ( (zip.length() != 5) || (zip2.length() != 5) )
        {
            System.out.print("Please enter a valid ZIP: ");
            zip = input.nextLine();
            zip2 = zip.replaceAll("[\\D]", "");

        }

        // set address in Employee array
        empArray[i].getAddress().setStreet(street);
        empArray[i].getAddress().setCity(city);
        empArray[i].getAddress().setState(state);
        empArray[i].getAddress().setZip(zip);
    }


    /**
     * This method will prompt user for employee hire date information to
     * include day, month, and year, and set the Employee[i] information.
     * Only integer input is accepted.
     *
     * @param i
     * @param input
     * @param empArray
     */
    public static void inputDate(int i, Scanner input, Employee[] empArray)
    {
        System.out.println("Please enter employee hire date.");

        // get month
        Integer month = Integer.valueOf(0);
        String monthstring = "";

        // make sure input is valid
        while ( (month < 1) || (month > 12) )
        {
            System.out.print("Enter month (1-12): ");
            monthstring = input.nextLine();

            // check if nothing was entered
            if ( monthstring.isEmpty() )
            {
                System.out.println("Invalid input, try again.");
            }

            else
            {
                // if something was entered, make sure is int
                // between 1 and 12
                try
                {
                    month = Integer.valueOf(monthstring);
                    if ( (month < 1) || (month > 12) )
                    {
                        System.out.println("Invalid input, try again.");
                    }

                }
                catch (NumberFormatException e)
                {
                    System.out.println("Invalid input, try again.");
                }
            }
        }


        // get day
        Integer day = Integer.valueOf(0);
        String daystring = "";

        // make sure input is valid
        while ( (day < 1) || (day > 31) )
        {
            System.out.print("Enter day (1-31): ");
            daystring = input.nextLine();

            // check if nothing was entered
            if ( daystring.isEmpty() )
            {
                System.out.println("Invalid input, try again.");
            }

            else
            {
                // if something was entered, make sure is int
                // between 1 and 31
                try
                {
                    day = Integer.valueOf(daystring);
                    if ( (day < 1) || (day > 31) )
                    {
                        System.out.println("Invalid input, try again.");
                    }

                }
                catch (NumberFormatException e)
                {
                    System.out.println("Invalid input, try again.");
                }
            }
        }

        // get year
        Integer year = Integer.valueOf(0);
        String yearstring = "";

        // make sure input is valid
        while ( (year < 1900) || (year > 2020) )
        {
            System.out.print("Enter year (1900-2020): ");
            yearstring = input.nextLine();

            // check if nothing was entered
            if ( yearstring.isEmpty() )
            {
                System.out.println("Invalid input, try again.");
            }

            else
            {
                // if something was entered, make sure is int
                // between 1900 and 2020
                try
                {
                    year = Integer.valueOf(yearstring);
                    if ( (year < 1900) || (year > 2020) )
                    {
                        System.out.println("Invalid input, try again.");
                    }

                }
                catch (NumberFormatException e)
                {
                    System.out.println("Invalid input, try again.");
                }
            }
        }

        empArray[i].getDate().setMonth(month);
        empArray[i].getDate().setDay(day);
        empArray[i].getDate().setYear(year);
    }

}