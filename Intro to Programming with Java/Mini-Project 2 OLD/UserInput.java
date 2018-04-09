import java.util.Scanner;

public class UserInput
{
    // initialize variables
    Scanner input = new Scanner(System.in);
    private Integer userselection;
    private int numberChoices;   // the number of choices available

    // constructor
    public UserInput(int numberChoices)
    {
        userselection = Integer.valueOf(-1);
        this.numberChoices = numberChoices;
    }


    /**
     *
     * @return
     */
    public int getUserSelection()
    {
        System.out.println();
        String userstring = "";

        // make sure input is valid
        while ( (userselection < 0) || (userselection >= numberChoices) )
        {
            System.out.print("Please enter selection: ");
            userstring = input.nextLine();

            // check if nothing was entered
            if ( userstring.isEmpty() )
            {
                System.out.println("\nPlease enter only integer values" +
                        " between 0 and " + (numberChoices-1) +".\n");
            }

            else
            {
                // if something was entered, make sure is in range
                try
                {
                    userselection = Integer.valueOf(userstring);
                    if ((userselection < 0) || (userselection >= numberChoices))
                    {
                        System.out.println("\nPlease enter only integer " +
                                "values between 0 and " + (numberChoices-1) +
                                ".\n");
                    }
                }
                catch (NumberFormatException e)
                {
                    System.out.println("\nPlease enter only integer values" +
                            " between 0 and " + (numberChoices-1) +".\n");
                }
            }
        }

        return userselection;
    }
}