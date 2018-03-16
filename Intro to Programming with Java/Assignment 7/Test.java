/**
 * This program is my response to Assignment 7 for the class 605.201.81 Intro
 * to Programming Using Java at the JHU EPP CS program.
 *
 * This program is a rudimentary employee database program. Employee information
 * is stored in the SalariedEmployee or HourlyEmployee subclasses. The list of
 * employee and their respective information is stored in an Employee[] array.
 * Makes use of additional Name, Address,and Date classes for get/set methods
 * for each class of data.
 *
 * @author: Sean Connor
 */

public class Test
{
    public static void main(String[] args)
    {
        // initialize Employee[] array
        Employee[] employeeArray = new Employee[3];

        // Employee 1 (salaried)
        Name nameEmployee1 = new Name("tom", "hanks");

        Address addressEmployee1 = new Address("317 AUGUST ST",
                "EASTON", "MD", "21601");

        Date dateEmployee1 = new Date(6, 18, 1985);

        employeeArray[0] = new SalariedEmployee(0, nameEmployee1,
                addressEmployee1, dateEmployee1, 75000);


        // Employee 2 (hourly <= 40)
        Name nameEmployee2 = new Name("jeff", "goldblum");

        Address addressEmployee2 = new Address("1595 lexington ave",
                "new york", "ny", "10029");

        Date dateEmployee2 = new Date(01, 01, 2000);

        employeeArray[1] = new HourlyEmployee(1, nameEmployee2,
                addressEmployee2, dateEmployee2, 10, 37.5);


        // Employee 3 (hourly > 40)
        Name nameEmployee3 = new Name("Marilyn", "Monroe");

        Address addressEmployee3 = new Address("3748 Sepulveda Blvd",
                "Torrance", "CA", "90505");

        Date dateEmployee3 = new Date(12, 25, 1954);

        employeeArray[2] = new HourlyEmployee(2, nameEmployee3,
                addressEmployee3, dateEmployee3, 10, 52.5);


        // print all employee information to console
        for (int i = 0; i < 3; i++)
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

            if ( employeeArray[i] instanceof SalariedEmployee)
            {
                System.out.println("Employee Salary: " +
                        employeeArray[i].getSalary());
            }

            else
            {
                System.out.println("Employee Hourly Rate: " +
                        employeeArray[i].getPayRate());
                System.out.println("Employee Hours Worked: " +
                        employeeArray[i].getHoursWorked());
                System.out.println("Employee Total Earnings: " +
                        employeeArray[i].getTotalEarnings());
            }
        }

        System.out.println();

    }
}