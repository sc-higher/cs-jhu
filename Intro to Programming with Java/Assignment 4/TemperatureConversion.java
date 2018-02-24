import java.util.Scanner;

public class TemperatureConversion
{
    public static void main(String[] args)
    {
        int userChoice = 0;                           // User selection: 1, 2, 3

        Scanner input = new Scanner(System.in);     // Create a Scanner to obtain user input

        while( userChoice != 3 )
        {
            System.out.print( "Enter 1 to convert F->C, 2 to convert C->F, 3 to quit: " );
            userChoice = input.nextInt();              // Read user input
            ConversionResult(userChoice);
        }
    }


    // method to convert fahrenheit to celsius
    public static float FahToCel(float temperatureFahrenheit)
    {
        return 5F/9F * ( temperatureFahrenheit - 32F );
    }


    // method to convert celsius to fahrenheit
    public static float CelToFah(float temperatureCelsius)
    {
        return 9F/5F * temperatureCelsius + 32F;
    }


    // method to take userChoice and perform the specified conversion (by calling the previous methods)
    // and then outputing the result to terminal
    public static void ConversionResult(int userChoice)
    {
        float temperatureFahrenheit = 0;              // Fahrenheit temperature
        float temperatureCelsius = 0;                 // Celsius temperature
        Scanner input = new Scanner(System.in);     // Create a Scanner to obtain user input

        switch( userChoice )
        {
            case 1:     // Convert Fahrenheit to Celsius
                System.out.print("Enter a Fahrenheit temperature: ");
                temperatureFahrenheit = input.nextFloat();
                temperatureCelsius = FahToCel(temperatureFahrenheit);
                System.out.println(temperatureFahrenheit + " degrees Fahrenheit is " +
                        temperatureCelsius + " degrees Celsius");
                break;

            case 2:     // Convert Celsius to Fahrenheit
                System.out.print( "Enter a Celsius temperature: " );
                temperatureCelsius = input.nextFloat();
                temperatureFahrenheit = CelToFah(temperatureCelsius);
                System.out.println( temperatureCelsius + " degrees Celsius is " +
                        temperatureFahrenheit + " degrees Fahrenheit" );
                break;

            case 3:     // End Program
                System.out.println();
                break;

            default:    // Invalid Data Entered
                System.out.println( "Invalid Data: You must enter 1, 2, or 3" );

        }
    }
}