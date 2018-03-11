/**
 * This program is my response to Mini-Project 1 for the
 * class 605.201.81 Intro to Programming Using Java at
 * the JHU EPP CS program.
 *
 * This is a Pacman-style game. Use the controls as stated
 * to play the game!
 *
 * @author: Sean Connor
 */

import java.util.*;

public class Pacman
{
    static Scanner input = new Scanner(System.in);

    public static void main(String [] args)
    {
        // initialize variables
        Integer height = Integer.valueOf(0);
        Integer width = Integer.valueOf(0);
        Integer userselection = Integer.valueOf(-1);
        int[] position = new int[4];      // position[0] = x-position
                                          // position[1] = y-position
                                          // position[2] = 'cookie' counter
                                          // position[3] = turn counter

        char[][] board = new char[height][width];

        // get user input
        System.out.println();
        height = getBoardDimension(input, height);
        System.out.println();
        width = getBoardDimension(input, width);
        System.out.println();

        // Set the target number of cookies (8%)
        int target = (int) (height*width*0.08);

        // print instructions
        pacmanInstructions();

        // create the game board
        board = createBoard(height, width);

        // add 'cookies' to the game board
        addCookies('o', target, board, height, width);

        // print the game board to the screen
        printBoard(board, height, width);
        System.out.println("X: " + position[0] + "   Y: " + position[1]);

        // run the game
        runGame(board, position, height, width, userselection);

    }


    /**
     * Retrieves user input for board dimensions. Requires integer input
     * between 2 and 30.
     *
     * @param input      a scanner object to retrieve user input
     * @param dimension  an initialized integer for a board dimension
     * @return           an integer value that represents a board dimension
     */

    public static int getBoardDimension(Scanner input, Integer dimension)
    {
        String dimstring = "";

        // make sure input is valid
        while ( (dimension < 2) || (dimension > 30) )
        {
            System.out.print("Please enter board width: ");
            dimstring = input.nextLine();

            // check if nothing was entered
            if ( dimstring.isEmpty() )
            {
                System.out.println("Ooops! Please enter only integer values" +
                        " between 2 and 30.");
            }

            else
            {
                // if something was entered, make sure is int between 2 and 30
                try
                {
                    dimension = Integer.valueOf(dimstring);
                    if ( (dimension < 2) || (dimension > 30) )
                    {
                        System.out.println("Ooops! Please enter only integer" +
                                " values between 2 and 30.");
                    }

                }
                catch (NumberFormatException e)
                {
                    System.out.println("Ooops! Please enter only integer" +
                            " values between 2 and 30.");
                }
            }
        }

        return dimension;
    }


    /**
     * Prints a number of lines containing the Pacman game instructions to
     * the terminal. Accepts no input and returns nothing.
     */

    public static void pacmanInstructions()
    {
        System.out.println("Welcome to Pac-Man!");
        System.out.println("Enter the number of the desired command.");
        System.out.println("<0> Display Commands");
        System.out.println("<1> Turn Left");
        System.out.println("<2> Turn Right");
        System.out.println("<3> Move");
        System.out.println("<4> Exit");
        System.out.println();
    }


    /**
     * Creates the initial Pacman game board, of the dimensions specified by
     * the user. The game board is a 2D char array of dimensions 'height' by
     * 'width', where each array value is filled with a '.' except for position
     * board[0][0], which contains the Pacman character in the starting position
     * ('>').
     *
     * @param height  an integer value representing the height of the game board
     * @param width   an integer value representing the width of the game board
     * @return  the game board, a 'height' x 'width' 2D array.
     */

    public static char[][] createBoard(int height, int width)
    {
        char[][] board = new char[height][width];

        for (int i = 0; i < height; i++)
        {
            for (int j = 0; j < width; j++)
            {
                board[i][j] = '.';
            }
        }

        board[0][0] = '>';

        return board;
    }


    /**
     * Randomly adds the Pacman 'cookies' to the game board. Replaces a user-
     * specified percentage of '.' characters with the specified 'cookie'
     * character.
     *
     * @param place   the type of character to represent the 'cookies'
     * @param target  the number of 'cookies' to be placed on the board
     * @param board   the game board on which the 'cookies' will be placed
     * @param height  the game board height
     * @param width   the game board width
     */

    public static void addCookies(char place, int target, char[][] board,
                                  int height, int width)
    {
        int count = 0;
        while (count < target)
        {

            Random rand = new Random();
            int x = rand.nextInt(width);
            int y = rand.nextInt(height);

            if (board[y][x] == '.')
            {
                board[y][x] = place;
                count++;
            }
        }
    }


    /**
     * Prints the game board to terminal.
     *
     * @param board   the game board to be printed
     * @param height  the game board height
     * @param width   the game board width
     */

    public static void printBoard(char[][] board, int height, int width)
    {
        for (int x = 0; x < height; x++)
        {
            for (int y = 0; y < width; y++)
            {
                System.out.print(board[x][y]);
            }
            System.out.println();
        }
        System.out.println();
    }


    /**
     * This method will prompt the user for a move selection, which is any
     * integer value between 0 and 4 inclusive. Method ensures that only
     * integer values between 0 and 4 are acceptable - all other inputs will
     * result in a prompt for valid input.
     *
     * @param input           a scanner object to retrieve user input
     * @param userselection   an integer value representing user selection,
     *                        initialized to -1.
     * @return                an integer value representing the user selection
     */

    public static int getUserSelection(Scanner input, Integer userselection,
                                       char[][] board, int[] position,
                                       int height, int width)
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
                        System.out.println("\nOoops! Please enter only integer" +
                                " values between 0 and 4.\n");
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


    /**
     * This method will turn the Pacman character left (counter-clockwise). In
     * addition, the turn counter integer, contained in position[3], will be
     * incremented by one to represent the user taking a turn.
     *
     * @param board     the game board on which the Pacman character exists
     *                  and turns left
     * @param position  a size four 1D array that represents x-position,
     *                  y-position, cookie counter, and turn counter,
     *                  respectively
     */

    public static void turnLeft(char[][] board, int[] position)
    {
        int posx = position[0];
        int posy = position[1];

        switch (board[posy][posx])
        {
            case '>':
                board[posy][posx] = '^';
                position[3]++;
                break;
            case '^':
                board[posy][posx] = '<';
                position[3]++;
                break;
            case '<':
                board[posy][posx] = 'V';
                position[3]++;
                break;
            case 'V':
                board[posy][posx] = '>';
                position[3]++;
                break;
        }
    }


    /**
     * This method will turn the Pacman character right (clockwise). In
     * addition, the turn counter integer, contained in position[3], will be
     * incremented by one to represent the user taking a turn.
     *
     * @param board     the game board on which the Pacman character exists and
     *                  turns right
     * @param position  a size four 1D array that represents x-position,
     *                  y-position, cookie counter, and turn counter,
     *                  respectively
     */

    public static void turnRight(char[][] board, int[] position)
    {
        int posx = position[0];
        int posy = position[1];

        switch (board[posy][posx])
        {
            case '>':
                board[posy][posx] = 'V';
                position[3]++;
                break;
            case 'V':
                board[posy][posx] = '<';
                position[3]++;
                break;
            case '<':
                board[posy][posx] = '^';
                position[3]++;
                break;
            case '^':
                board[posy][posx] = '>';
                position[3]++;
                break;
        }
    }


    /**
     * This method will move the Pacman character one space in the direction
     * that the character is facing. The previous space will be replaced with
     * a ' '. If the Pacman character would move into a 'cookie', the method
     * will increment the 'cookie' counter (position[2]) by one. For every move
     * made, the turn counter (position[3]) will increment by one. The x and y
     * positions (position[0] and position[1], respectively) will be updated
     * accordingly.
     *
     * @param board     the game board on which the move is being made
     * @param position  a size four 1D array that represents x-position,
     *                  y-position, cookie counter, and turn counter,
     *                  respectively
     * @param height    the height of the game board
     * @param width     the width of the game board
     */

    public static void movePacman(char[][] board, int[] position, int height,
                                  int width)
    {
        int posx = position[0];
        int posy = position[1];

        switch (board[posy][posx])
        {
            // one case for each of the four directions pacman can face
            case '>':
                if (posx > 0)  // check if pacman is at edge of board
                {
                    if (board[posy][posx-1] == 'o')  // check if move will land
                    {                                // pacman on cookie
                        position[2]++;  // increment cookie counter
                    }
                    board[posy][posx] = ' ';  // set previous position to ' '
                    board[posy][posx-1] = '>';  // set new position to pacman
                                                // character
                    printBoard(board, height, width);  // display the updated
                                                       // board
                    posx--;  // decrement position int
                    position[0] = posx;  // update position array with new
                                         // position data
                    System.out.println("X: " + position[0] + "   Y: " +
                            position[1]);  // print current position
                    position[3]++;  // increment turn counter
                    break;
                }
                else
                {
                    System.out.println("Invalid move");
                    break;
                }

            case 'V':
                if (posy > 0)
                {
                    if (board[posy-1][posx] == 'o')
                    {
                        position[2]++;
                    }
                    board[posy][posx] = ' ';
                    board[posy-1][posx] = 'V';
                    printBoard(board, height, width);
                    posy--;
                    position[1] = posy;
                    System.out.println("X: " + position[0] + "   Y: " +
                            position[1]);
                    position[3]++;
                    break;
                }
                else
                {
                    System.out.println("Invalid move");
                    break;
                }

            case '<':
                if (posx < width-1)
                {
                    if (board[posy][posx+1] == 'o')
                    {
                        position[2]++;
                    }
                    board[posy][posx] = ' ';
                    board[posy][posx+1] = '<';
                    printBoard(board, height, width);
                    posx++;
                    position[0] = posx;
                    System.out.println("X: " + position[0] + "   Y: " +
                            position[1]);
                    position[3]++;
                    break;
                }
                else
                {
                    System.out.println("Invalid move");
                    break;
                }

            case '^':
                if (posy < height-1)
                {
                    if (board[posy+1][posx] == 'o')
                    {
                        position[2]++;
                    }
                    board[posy][posx] = ' ';
                    board[posy+1][posx] = '^';
                    printBoard(board, height, width);
                    posy++;
                    position[1] = posy;
                    System.out.println("X: " + position[0] + "   Y: " +
                            position[1]);
                    position[3]++;
                    break;
                }
                else
                {
                    System.out.println("Invalid move");
                    break;
                }

            default:
                System.out.println("FAIL");
                break;
        }
    }


    /**
     * This method will print the game stats, including number of turns taken,
     * number of 'cookies' acquired, and the average number of turns taken
     * per 'cookie'.
     *
     * @param position   a size four 1D array that represents x-position,
     *                   y-position, cookie counter, and turn counter,
     *                   respectively
     */

    public static void gameStats(int[] position)
    {
        int numCookies = position[2];
        int numTurns = position[3];

        if (numCookies > 0)
        {
            double avgTurnsPerCookie = (double) numTurns / (double) numCookies;

            System.out.println("----------------- STATS -----------------");
            System.out.println("Total cookies acquired: " + numCookies);
            System.out.println("Total turns taken: " + numTurns);
            System.out.printf("Average number of turns per cookie: %.2f\n",
                    avgTurnsPerCookie);
            System.out.println();
            System.out.println("Thanks for playing!");
            System.out.println();
        }

        else
        {
            System.out.println("----------------- STATS -----------------");
            System.out.println("Total cookies acquired: " + numCookies);
            System.out.println("Total turns taken: " + numTurns);
            System.out.println("Average number of turns per cookie: N/A");
            System.out.println();
            System.out.println("Thanks for playing!");
            System.out.println();
        }
    }


    /**
     * This method will run the Pacman game. It will execute the user selections
     * for turn, move, etc, until the user enters '4' in order to quit the game.
     *
     * @param board          the game board on which the move is being made
     * @param position       a size four 1D array that represents x-position,
     *                       y-position, cookie counter, and turn counter,
     *                       respectively
     * @param height         the height of the game board
     * @param width          the width of the game board
     * @param userselection  integer value representing the user selection for
     *                       game options
     */

    public static void runGame(char[][] board, int[] position, int height,
                               int width, int userselection)
    {
        while (userselection != 4)
        {
            userselection = getUserSelection(input, userselection, board,
                    position, height, width);

            switch(userselection)
            {
                case 0:
                    pacmanInstructions();
                    userselection = -1;
                    break;

                case 1:
                    turnLeft(board, position);
                    printBoard(board, height, width);
                    System.out.println("X: " + position[0] + "   Y: " +
                            position[1]);
                    userselection = -1;
                    break;

                case 2:
                    turnRight(board, position);
                    printBoard(board, height, width);
                    System.out.println("X: " + position[0] + "   Y: " +
                            position[1]);
                    userselection = -1;
                    break;

                case 3:
                    movePacman(board, position, height, width);
                    userselection = -1;
                    break;

                case 4:
                    gameStats(position);
                    break;

                default:
                    System.out.println("Please enter a valid number.");
                    userselection = -1;

            }
        }
    }

}