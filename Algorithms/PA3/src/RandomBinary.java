import java.util.Random;

public class RandomBinary {

    public static void main(String[] args){

        Random rand;
        String number;

        // Create a FileOutput object to handle output tasks
        FileOutput out = new FileOutput("random-binary-numbers.txt");

        // Fill the FileOutput StringBuilder object
        out.append("Random Binary Numbers (length=3)");
        for (int i = 0; i < 10; i++){
            rand = new Random();
            number = "";

            for (int j = 0; j<3; j++){
                number = number + rand.nextInt(2);
            }

            out.append(number);
        }

        out.append("");
        out.append("Random Binary Numbers (length=10)");
        for (int i = 0; i < 10; i++){
            rand = new Random();
            number = "";

            for (int j = 0; j<10; j++){
                number = number + rand.nextInt(2);
            }

            out.append(number);
        }

        out.append("");
        out.append("Random Binary Numbers (length=20)");
        for (int i = 0; i < 10; i++){
            rand = new Random();
            number = "";

            for (int j = 0; j<20; j++){
                number = number + rand.nextInt(2);
            }

            out.append(number);
        }

        out.append("");
        out.append("Random Binary Numbers (length=100)");
        for (int i = 0; i < 10; i++){
            rand = new Random();
            number = "";

            for (int j = 0; j<100; j++){
                number = number + rand.nextInt(2);
            }

            out.append(number);
        }

        out.append("");
        out.append("Random Binary Numbers (length=1000)");
        for (int i = 0; i < 10; i++){
            rand = new Random();
            number = "";

            for (int j = 0; j<1000; j++){
                number = number + rand.nextInt(2);
            }

            out.append(number);
        }

        // Write the output file
        out.write();

    }



}
