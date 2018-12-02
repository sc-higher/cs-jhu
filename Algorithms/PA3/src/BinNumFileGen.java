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
