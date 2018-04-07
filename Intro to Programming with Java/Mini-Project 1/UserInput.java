import java.util.Scanner

public class UserInput
{
    // initialize variables
    Integer userselection;
    int range;

    // constructor
    public UserInput(int range)
    {
        userselection = Integer.valueOf(-1);
        this.range = range
    }


    public int getUserSelection(Integer userselection)
    {
        System.out.println();
        String userstring = "";

        // make sure input is valid
        while ( (userselection < 0) || (userselection > 4) )
        {
            System.out.print("Please enter selection: ");
            userstring = input.nextLine();

            // check if nothing was entered
            if ( userstring.isEmpty() )
            {
                System.out.println("\nOoops! Please enter only integer values" +
                        " between 0 and 4.\n");
                pacmanInstructions();
                printBoard(board, height, width);
                System.out.println("X: " + position[0] + "   Y: " +
                        position[1] + "\n");
            }

            else
            {
                // if something was entered, make sure is int between 2 and 30
                try
                {
                    userselection = Integer.valueOf(userstring);
                    if ( (userselection < 0) || (userselection > 4) )
                    {
                        System.out.println("\nOoops! Please enter only integer"
                                + " values between 0 and 4.\n");
                        pacmanInstructions();
                        printBoard(board, height, width);
                        System.out.println("X: " + position[0] + "   Y: " +
                                position[1] + "\n");
                    }

                }
                catch (NumberFormatException e)
                {
                    System.out.println("\nOoops! Please enter only integer" +
                            " values between 0 and 4.\n");
                    pacmanInstructions();
                    printBoard(board, height, width);
                    System.out.println("X: " + position[0] + "   Y: " +
                            position[1] + "\n");
                }
            }
        }

        return userselection;
    }
}