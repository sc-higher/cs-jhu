import java.util.*;

public class Test {
    // initialize attributes
    static String filename;
    static int selection;
    static List<Song> database;


    // main method
    public static void main(String[] args) {
        // get command line argument for database filename (if applicable)
        if (args.length == 1) {
            filename = args[0];
            ReadCSV songCSV = new ReadCSV(filename);
            database = songCSV.toArrayList();
        } else {
            System.out.println("\nFile does not exist.");
            System.out.println("Create new song database? (1 = yes, 2 = no)");
            UserInput selectionMenu = new UserInput(1, 2);
            selection = selectionMenu.getUserSelection();

            if (selection != 1) {
                System.out.println("\nGoodbye!");
                System.exit(1);
            }

            database = new ArrayList<Song>();
        }

        for (Song t : database) {
            System.out.printf("\n%1$15s", t.getTitle());
            System.out.printf("%1$15s", t.getItemCode());
            System.out.printf("%1$32s", t.getDescription());
            System.out.printf("%1$15s", t.getArtist());
            System.out.printf("%1$32s", t.getAlbum());
            System.out.printf("%1$32s", t.getPrice());
        }

    }
}