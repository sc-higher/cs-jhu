/**
 * This program is part of my response to PA 2 for the class 605.621
 * Foundations of Algorithms at the JHU EPP CS program.
 *
 * @author Sean Connor
 * @date 28 October 2018
 */

public class Partition
{
    /**
     * Partitions a subarray using the Median of Three method.
     *
     * @param array The array containing the subarray to be
     * partitioned.
     * @param p Index of the beginning of the subarray.
     * @param r Index of the end of the subarray.
     * @return An index
     */
    public long[] partition(int[] array, int p, int r, long ops) {
        int x = array[r]; // x is the pivot
        int i = p - 1;

        // Maintain the following invariant:
        //   array[p..i] <= x,
        //   array[i+1..j-1] > x, and
        //   array[r] = x.
        for (int j = p; j < r; j++) {
            if (array[j] <= x) {
                i++;
                exchange(array, i, j);
                ops++;
            }
        }

        // Put the pivot value in its correct place and return that
        // index.
        exchange(array, i+1, r);
        ops++;

        // An int[] to return the partition index q and the operation
        // counter.
        long[] data = new long[2];
        data[0] = i + 1;
        data[1] = ops;
        return data;
    }


    /**
     * Exchanges the objects at two positions within an array.
     *
     * @param i The index of one object.
     * @param j The index of the other object.
     */
    public void exchange(int[] array, int i, int j) {
        int t = array[i];
        array[i] = array[j];
        array[j] = t;
    }

}

