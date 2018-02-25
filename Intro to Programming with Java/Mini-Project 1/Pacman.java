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

    public static void main(String [] args)
    {
        // initialize variables
        int height = 0;
        int width = 0;
        int userselection = 0;
        int[] position = new int[2];
        char[][] board = new char[height][width];

        // get user input
        Scanner input = new Scanner(System.in);
        System.out.println();
        System.out.print("Please enter board height: ");
        height = input.nextInt();
        System.out.println();
        System.out.print("Please enter board width: ");
        width = input.nextInt();
        System.out.println();
        int target = (int) (height*width*0.2);

        // print instructions
        pacmanInstructions();

        // create the game board
        board = createBoard(height, width);

        // add 'cookies' to the game board
        addCookies('o', target, board, height, width);

        // print the game board to the screen
        printBoard(board, height, width);
        System.out.println("X: " + position[0] + "   Y: " + position[1]);

        // execute selection
        while (userselection != 4)
        {
            userselection = getUserSelection();

            switch(userselection)
            {
                case 0:
                    pacmanInstructions();
                    break;

                case 1:
                    turnLeft(board, position);
                    printBoard(board, height, width);
                    System.out.println("X: " + position[0] + "   Y: " + position[1]);
                    break;

                case 2:
                    turnRight(board, position);
                    printBoard(board, height, width);
                    System.out.println("X: " + position[0] + "   Y: " + position[1]);
                    break;

                case 3:
                    movePacman(board, position, height, width);
                    break;

                case 4:
                    break;

                default:
                    System.out.println("Please enter a valid number.");

            }
        }
    }

    /**
     * 
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
     *
     * @param height
     * @param width
     * @return
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
     *
     * @param place
     * @param target
     * @param board
     * @param height
     * @param width
     */

    public static void addCookies(char place, int target, char[][] board, int height, int width)
    {
        int count = 0;
        while (count < target)
        {

            Random rand = new Random();
            int x = rand.nextInt(width);
            int y = rand.nextInt(height);

            if (board[x][y] == '.')
            {
                board[x][y] = place;
                count++;
            }
        }
    }


    /**
     *
     * @param board
     * @param height
     * @param width
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
     *
     * @return
     */

    public static int getUserSelection()
    {
        Scanner input = new Scanner(System.in);
        System.out.println();
        System.out.print("Please enter selection: ");
        int height = input.nextInt();
        System.out.println();
        return height;
    }


    /**
     *
     * @param board
     * @param position
     * @return
     */

    public static char[][] turnLeft(char[][] board, int[] position)
    {
        int posx = position[0];
        int posy = position[1];

        switch (board[posy][posx])
        {
            case '>':
                board[posy][posx] = '^';
                break;
            case '^':
                board[posy][posx] = '<';
                break;
            case '<':
                board[posy][posx] = 'V';
                break;
            case 'V':
                board[posy][posx] = '>';
                break;
        }
        return board;
    }


    /**
     *
     * @param board
     * @param position
     * @return
     */

    public static char[][] turnRight(char[][] board, int[] position)
    {
        int posx = position[0];
        int posy = position[1];

        switch (board[posy][posx])
        {
            case '>':
                board[posy][posx] = 'V';
                break;
            case 'V':
                board[posy][posx] = '<';
                break;
            case '<':
                board[posy][posx] = '^';
                break;
            case '^':
                board[posy][posx] = '>';
                break;
        }
        return board;
    }


    /**
     *
     * @param board
     * @param position
     * @param height
     * @param width
     * @return
     */

    public static int[] movePacman(char[][] board, int[] position, int height, int width)
    {
        int posx = position[0];
        int posy = position[1];

        switch (board[posy][posx])
        {
            case '>':
                if (posx > 0)
                {
                    board[posy][posx] = ' ';
                    board[posy][posx-1] = '>';
                    printBoard(board, height, width);
                    posx--;
                    position[0] = posx;
                    System.out.println("X: " + position[0] + "   Y: " + position[1]);
                    return position;
                }
                else
                {
                    System.out.println("Invalid move");
                    return position;
                }

            case 'V':
                if (posy > 0)
                {
                    board[posy][posx] = ' ';
                    board[posy-1][posx] = 'V';
                    printBoard(board, height, width);
                    posy--;
                    position[1] = posy;
                    System.out.println("X: " + position[0] + "   Y: " + position[1]);
                    return position;
                }
                else
                {
                    System.out.println("Invalid move");
                    return position;
                }

            case '<':
                if (posx < width-1)
                {
                    board[posy][posx] = ' ';
                    board[posy][posx+1] = '<';
                    printBoard(board, height, width);
                    posx++;
                    position[0] = posx;
                    System.out.println("X: " + position[0] + "   Y: " + position[1]);
                    return position;
                }
                else
                {
                    System.out.println("Invalid move");
                    return position;
                }

            case '^':
                if (posy < height-1)
                {
                    board[posy][posx] = ' ';
                    board[posy+1][posx] = '^';
                    printBoard(board, height, width);
                    posy++;
                    position[1] = posy;
                    System.out.println("X: " + position[0] + "   Y: " + position[1]);
                    return position;
                }
                else
                {
                    System.out.println("Invalid move");
                    return position;
                }

            default:
                System.out.println("FAIL");
                return position;
        }
    }

}