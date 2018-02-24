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


    }

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

        return board;
    }


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

}