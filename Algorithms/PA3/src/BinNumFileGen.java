/**
 * This program is part of my response to PA 3 for the class 605.621
 * Foundations of Algorithms at the JHU EPP CS program.
 *
 * Used to generate test files. Each test file consists of three lines
 * representing x, y, and s. These test files are used as input for the
 * main program.
 *
 * @author Sean Connor
 * @date 2 December 2018
 */

import java.util.Random;

public class BinNumFileGen {

    public static void main(String[] args){

        Random rand;
        String number;
        FileOutput out;

        String x;
        String y;
        String s;

        // Generate 10 files (x=y=5, s=20)
        for (int i = 0; i < 10; i++) {
            // Create new output file
            out = new FileOutput("5-20-"+i+".txt");

            // Create new Random object
            rand = new Random();

            // Reset strings x, y, and s
            x = "";
            y = "";
            s = "";

            // Generate random binary sequence for x and y
            for (int j = 0; j < 5; j++) {
                x = x + rand.nextInt(2);
                y = y + rand.nextInt(2);
            }

            // Generate random binary sequence for s
            for (int j = 0; j < 20; j++) {
                s = s + rand.nextInt(2);
            }

            // Append and write to output file
            out.append(x);
            out.append(y);
            out.append(s);
            out.write();
        }

        // Generate 10 files (x=y=10, s=100)
        for (int i = 0; i < 10; i++) {
            // Create new output file
            out = new FileOutput("10-100-"+i+".txt");

            // Create new Random object
            rand = new Random();

            // Reset strings x, y, and s
            x = "";
            y = "";
            s = "";

            // Generate random binary sequence for x and y
            for (int j = 0; j < 10; j++) {
                x = x + rand.nextInt(2);
                y = y + rand.nextInt(2);
            }

            // Generate random binary sequence for s
            for (int j = 0; j < 100; j++) {
                s = s + rand.nextInt(2);
            }

            // Append and write to output file
            out.append(x);
            out.append(y);
            out.append(s);
            out.write();
        }

        // Generate 10 files (x=y=100, s=1000)
        for (int i = 0; i < 10; i++) {
            // Create new output file
            out = new FileOutput("100-1000-"+i+".txt");

            // Create new Random object
            rand = new Random();

            // Reset strings x, y, and s
            x = "";
            y = "";
            s = "";

            // Generate random binary sequence for x and y
            for (int j = 0; j < 100; j++) {
                x = x + rand.nextInt(2);
                y = y + rand.nextInt(2);
            }

            // Generate random binary sequence for s
            for (int j = 0; j < 1000; j++) {
                s = s + rand.nextInt(2);
            }

            // Append and write to output file
            out.append(x);
            out.append(y);
            out.append(s);
            out.write();
        }
    }
}
