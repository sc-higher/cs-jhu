/**
 * This program is my response to Assignment 6 for
 * the class 605.201.81 Intro to Programming Using
 * Java at the JHU EPP CS program.
 *
 * DESCRIBE PROGRAM HERE
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
        int numberEmployees = 0;
        System.out.print("\nPlease enter the number of employees to enter: ");
        numberEmployees = input.nextInt();
        input.nextLine();  // Consume newline left-over

        // initialize Employee object array
        Employee[] employeeArray = new Employee[numberEmployees];

        // NOTES
        for (int i = 0; i < numberEmployees; i++)
        {
            employeeArray[i] = new Employee();

            // get user input for employee name
            inputNumber(i, employeeArray);
            inputName(i, input, employeeArray);
            inputAddress(i, input, employeeArray);
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
            System.out.println("Employee Name: " +
                    employeeArray[i].getName().getLastName() +
                    ", " + employeeArray[i].getName().getFirstName());

            // print employee address
            System.out.println("Employee Address: " +
                    employeeArray[i].getAddress().getStreet() +
                    ", " + employeeArray[i].getAddress().getCity() +
                    ", " + employeeArray[i].getAddress().getState() +
                    " " + employeeArray[i].getAddress().getZip());


            // print employee hire date
            System.out.println("Employee Hire Date: " +
                    employeeArray[i].getDate().getMonth() +
                    "/" + employeeArray[i].getDate().getDay() +
                    "/" + employeeArray[i].getDate().getYear());
        }

        System.out.println();

    }

    public static void inputNumber(int i, Employee[] empArray)
    {
        System.out.println();
        System.out.print("Employee Number: " + i);
        empArray[i].setNumber(i);
        System.out.println();
    }

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

    public static void inputAddress(int i, Scanner input, Employee[] empArray)
    {
        System.out.println("Please enter employee address.");

        // get street
        System.out.print("Street: ");
        String street = input.nextLine();

        // use while-loop to be sure input is not blank
        while ( street.isEmpty() )
        {
            System.out.print("Please enter a valid Street: ");
            street = input.nextLine();
        }

        // get city
        System.out.print("City: ");
        String city = input.nextLine();

        // use while-loop to be sure input is not blank
        while ( city.isEmpty() )
        {
            System.out.print("Please enter a valid City: ");
            city = input.nextLine();
        }

        // get state
        System.out.print("State: ");
        String state = input.nextLine();

        // use while-loop to be sure input is not blank
        while ( state.isEmpty() )
        {
            System.out.print("Please enter a valid State: ");
            state = input.nextLine();
        }

        // get zip
        System.out.print("ZIP: ");
        String zip = input.nextLine();

        // use while-loop to be sure input is not blank
        while ( zip.length() != 5 )
        {
            System.out.print("Please enter a valid ZIP: ");
            zip = input.nextLine();
        }

        // set address in Employee array
        empArray[i].getAddress().setStreet(street);
        empArray[i].getAddress().setCity(city);
        empArray[i].getAddress().setState(state);
        empArray[i].getAddress().setZip(zip);
    }

    public static void inputDate(int i, Scanner input, Employee[] empArray)
    {
        System.out.println("Please enter employee hire date.");
        System.out.print("Month: ");
        int month = input.nextInt();
        System.out.print("Day: ");
        int day = input.nextInt();
        System.out.print("Year: ");
        int year = input.nextInt();

        input.nextLine(); // 'reset' scanner after nextInt() usage

        empArray[i].getDate().setMonth(month);
        empArray[i].getDate().setDay(day);
        empArray[i].getDate().setYear(year);
    }






}