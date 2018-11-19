/**
 * This program is part of my response to PA 2 for the class 605.621
 * Foundations of Algorithms at the JHU EPP CS program.
 *
 * This contains the quicksort algorithm that utilizes separate Partition and
 * Partition3 classes. Note there are three sections to reverse commenting in
 * order to switch the type of recursion (full or partial) and partitioning
 * (regular or median-of-3) used.
 *
 * @author Sean Connor
 * @date 28 October 2018
 */

public class Quicksort
{
    // Sort counter and operation counter for performance testing /
    // run time analysis.
    static long count = 0;
    static long ops = 0;

    // An object for partitioning
    protected Partition3 part;
//    protected Partition part; // reverse commenting to switch partition method

    /**
     * Recursive quicksort procedure to sort the subarray
     * <code>array[p..r]</code>.  Uses whatever partitioner the
     * instance variable <code>part</code> is set to.
     *
     * @param array The array containing the subarray to be sorted.
     * @param p Index of the beginning of the subarray.
     * @param r Index of the end of the subarray.
     */
    protected void quicksort(int[] array, int p, int r)
    {
        // Complete recursive method
        if (p < r) {
            count++;
            long[] data = part.partition(array, p, r, ops);
            int q = Math.toIntExact(data[0]);
            ops = data[1];
            quicksort(array, p, q - 1);
            quicksort(array, q + 1, r);
        }

        //Partial recursive method to avoid StackOverflowError
//        while(p<r)
//        {
//            count++;
//            long[] data = part.partition(array, p, r, ops);
//            int q = Math.toIntExact(data[0]);
//            ops = data[1];
//
//            if (q - p <= r - (q + 1))
//            {
//                quicksort(array, p, q);
//                p = q + 1;
//            }
//            else
//            {
//                quicksort(array, q + 1, r);
//                r = q - 1;
//            }
//        }

    }


    /**
     * Sorts an array of integers.
     *
     * @param array The array of integers to be sorted.
     */
    public void sort(int[] array)
    {
        part = new Partition3();
//        part = new Partition(); // reverse commenting to switch partition method
        quicksort(array, 0, array.length - 1);
    }

}