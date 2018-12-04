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

public class Interleaving {

    private String x;
    private String y;
    private String s;
    private int lenS;


    /**
     * Constructor. Accepts a String[] containing x, y, and s.
     *
     * @param data
     */
    public Interleaving(String[] data) {
        x = data[0];
        y = data[1];
        s = data[2];
        lenS = s.length();
    }


    /**
     * Extends the length of a string to that of s. Used to extend x and y
     * so that they are the same length as x. Extend strings by repeating.
     *
     * @param a
     * @return
     */
    private String extend(String a) {
        int lenA = a.length();

        int j = 0;
        for (int i = 0; i < lenS-lenA; i++) {
            a = a + a.charAt(j);
            if ( j == lenA-1 ){
                j = 0;
            }
            else {
                j++;
            }
        }

        return a;
    }


    /**
     * Main dynamic programming algorithm. Creates a boolean array of size
     * s.length() by s.length(). Iterates through each element row by row
     * to calculate a true/false result for the interleaving subproblem.
     * If any value at a[i][j] where i+j=s.length (the diagonal) is true,
     * then s is an interleaving of x and y.
     *
     * @return
     */
    public ResultCounter calculate() {
        x = extend(x);
        y = extend(y);
        boolean[][] a = new boolean[lenS+1][lenS+1];
        int cc = 0;

        for (int i = 0; i <= lenS; i++) {
            for (int j = 0; j <= lenS; j++) {
                cc++;
                if (i + j <= lenS) {
                    cc++;
                    if (i == 0 && j == 0){
                        a[i][j] = true;
                    } else if (i == 0) {
                        a[i][j] = a[i][j-1] && (y.charAt(j-1) == s.charAt(i+j-1));
                        cc++;
                    } else if (j == 0) {
                        a[i][j] = a[i-1][j] && (x.charAt(i-1) == s.charAt(i+j-1));
                        cc++;
                    } else {
                        a[i][j] = (a[i][j-1] && (y.charAt(j-1) == s.charAt(i+j-1))) ||
                                (a[i-1][j] && (x.charAt(i-1) == s.charAt(i+j-1)));
                        cc++;
                    }
                }
            }
        }

        ResultCounter results = new ResultCounter(a, cc);
        return results;
    }


    /**
     * Converts the boolean array result of calcualte() to a string array for
     * easy viewing of the result.
     *
     * @param ba
     * @return
     */
    public String[][] toStringArray(boolean[][] ba) {
        String[][] sa = new String[lenS+3][lenS+2];
        for (int i = 2; i <= lenS+2; i++) {
            for (int j = 1; j <= lenS+1; j++) {
                sa[i][j] = "F";
                if (ba[i-2][j-1] == true) {
                    sa[i][j] = "T";
                }
            }
        }

        for (int i = 3; i <= lenS+2; i++) {
            for (int j = lenS+1; j > lenS-i+3; j--) {
                sa[i][j] = " ";
            }
        }

        for (int i = 2; i <= lenS+2; i++) {
            sa[i][0] = Integer.toString(i-2)+"|";
        }

        for (int i = 1; i <= lenS+1; i++) {
            sa[0][i] = Integer.toString(i-1);
        }

        for (int i = 1; i <= lenS+1; i++) {
            sa[1][i] = "-";
        }

        sa[0][0] = " |";
        sa[1][0] = "--";

        return sa;
    }


    /**
     * Iterates through the diagonal where i+j=s.length. If any value at
     * a[i][j] where i+j=s.length (the diagonal) is true, then s is an
     * interleaving of x and y.
     *
     * @param a
     * @return
     */
    public boolean decision(boolean[][] a) {
        int j = lenS;
        for (int i = 0; i <= lenS; i++) {
            if (a[i][j] == true) {
                return true;
            }
            j--;
        }
        return false;
    }


    public String getX() {
        return x;
    }

    public String getY() {
        return y;
    }

    public String getS() {
        return s;
    }
}