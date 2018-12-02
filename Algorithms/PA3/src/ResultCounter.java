/**
 * This program is part of my response to PA 3 for the class 605.621
 * Foundations of Algorithms at the JHU EPP CS program.
 *
 * The primary class for determining whether two strings are interleavings
 * of a third string.
 *
 * @author Sean Connor
 * @date 2 December 2018
 */

public class ResultCounter {

    private boolean[][] result;
    private int counter;

    /**
     *
     * @param result
     * @param counter
     */
    public ResultCounter(boolean[][] result, int counter) {
        this.result = result;
        this.counter = counter;
    }

    public boolean[][] getResult() {
        return result;
    }

    public int getCounter() {
        return counter;
    }
}
