/**
 * This program is part of my response to Project 1 for the class 605.202
 * Data Structures at the JHU EPP CS program.
 *
 * This class creates a stack overflow exception to be used in the Stack class.
 *
 * @author Sean Connor
 * @date 30 June 2018
 */

public class StackOverflowException extends RuntimeException {

    public StackOverflowException(String message) {
        super(message);
    }

}