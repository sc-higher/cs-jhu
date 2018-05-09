/**
 * This program is part of my response to Project 3 for the class 605.201.81
 * Intro to Programming Using Java at the JHU EPP CS program.
 *
 * This program will provide the user with a GUI that allows them to add, edit,
 * or delete songs from a song database. The database can be imported as a CSV-
 * style file (command-line argument for filename), or a new database can be
 * created. Any changes to the database are saved upon exit to the specified
 * filename.
 *
 * @author: Sean Connor
 */

import java.io.*;
import java.util.*;
import javafx.util.*;
import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.event.*;
import javafx.geometry.*;
import javafx.beans.property.*;
import javafx.scene.image.Image;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SongDatabase extends Application
{
    // initialize attributes
    static String filename;
    static int selection;
    static ObservableList<Song> database;

    // initialize labels
    Label selectionLabel = new Label("Select Song:");
    Label titleLabel = new Label("Title:");
    Label itemCodeLabel = new Label("Item Code:");
    Label descriptionLabel = new Label("Description:");
    Label artistLabel = new Label("Artist:");
    Label albumLabel = new Label("Album:");
    Label priceLabel = new Label("Price:");

    // initialize text fields
    ComboBox<Song> selectionCB = new ComboBox<Song>();
    TextField titleTF = new TextField();
    TextField itemCodeTF = new TextField();
    TextField descriptionTF = new TextField();
    TextField artistTF = new TextField();
    TextField albumTF = new TextField();
    TextField priceTF = new TextField();

    // initialize buttons
    Button addButton = new Button("Add");
    Button editButton = new Button("Edit");
    Button deleteButton = new Button("Delete");
    Button acceptButton = new Button("Accept");
    Button cancelButton = new Button("Cancel");
    Button exitButton = new Button("Exit");
    Button saveButton = new Button("Save As...");

    // initialize save window stuff
    Stage saveWindow = new Stage();
    Label filenameLabel = new Label("Filename:");
    TextField filenameTF = new TextField();
    Button okButton = new Button("OK");
    Button saveCancelButton = new Button("Cancel");

    // initialize fail stuff
    Stage failWindow = new Stage();
    VBox fail;
    Label failLabel;

    // initialize ComboBox<Song> callback for getTitle update
    Callback<ListView<Song>, ListCell<Song>> factory;


    /**
     * Main method. Handles validation of argument/filename and creation of
     * initial database. Incorporates the launch() method for javafx gui.
     *
     * @param args   can accept a filename as a command line argument
     */
    public static void main(String[] args)
    {
        // get command line argument for database filename (if applicable)
        // case 1 - one argument, valid filename --> open database
        // case 2 - one argument, invalid filename --> start new database?
        // case 3 - less/more than one argument --> start new database?
        // case 4 - one argument, valid filename, incorrect format --> not
        //          handled
        if ( args.length == 1 )
        {
            filename = args[0];

            try
            {
                // lazy file exists test
                File exists = new File(filename);
                Scanner exist_test = new Scanner(exists);

                // create the database from given file
                ReadCSV songCSV = new ReadCSV(filename);
                database = FXCollections.observableArrayList(
                        songCSV.toArrayList());
            }
            catch (FileNotFoundException exception)
            {
                // ask user if want to create new database
                System.out.println("\nFile does not exist.");
                System.out.println("Create new song database? " +
                        "(1 = yes, 2 = no)");
                UserInput selectionMenu = new UserInput(1,2);
                selection = selectionMenu.getUserSelection();

                if ( selection != 1 )
                {
                    System.out.println("\nGoodbye!");
                    System.exit(1);
                }

                // if 'yes' create new empty database
                database = FXCollections.observableArrayList();
            }

        }
        else
        {
            // set filename
            filename = "song-database.csv";

            // no command-line argument provided
            // ask user if want to create new database
            System.out.println("\nInvalid number of arguments.");
            System.out.println("Create new song database? (1 = yes, 2 = no)");
            UserInput selectionMenu = new UserInput(1,2);
            selection = selectionMenu.getUserSelection();

            if ( selection != 1 )
            {
                System.out.println("\nGoodbye!");
                System.exit(1);
            }

            // if 'yes' create new empty database
            database = FXCollections.observableArrayList();
        }

        // launch song database gui
        launch(args);
    }


    /**
     * JavaFX start method override
     *
     * @param myStage
     */
    @Override
    public void start(Stage myStage)
    {
        // set title
        myStage.setTitle("Song Database");
        myStage.getIcons().add(
                new Image(
                        SongDatabase.class.getResourceAsStream(
                                "icon.jpg" )));

        // set root node
        VBox rootNode = new VBox(10);
        rootNode.setPadding(new Insets(20, 40, 20, 40));

        // create a scene
        Scene myScene = new Scene(rootNode);
//        myScene.getStylesheets().add("PriceCalculator.css");

        // set scene on stage
        myStage.setScene(myScene);

        // create entry fields
        GridPane entryFields = createEntryFields();

        //
        VBox allButtons = createAllButtons();

        // add to scene
        rootNode.getChildren().addAll(entryFields,allButtons);

        // show stage and scene
        myStage.show();

    }


    /**
     * Creates the labels and text fields for the GUI, assembled as a GridPane
     * node.
     *
     * @return  GridPane node with labels and text fields
     */
    public GridPane createEntryFields()
    {
        selectionCB.setPrefWidth(400);
        titleTF.setPrefWidth(400);

        GridPane gridPane = new GridPane();

        gridPane.add(selectionLabel, 0, 0, 1, 1);
        gridPane.add(titleLabel, 0, 1, 1, 1);
        gridPane.add(itemCodeLabel, 0, 2, 1, 1);
        gridPane.add(descriptionLabel, 0, 3, 1, 1);
        gridPane.add(artistLabel, 0, 4, 1, 1);
        gridPane.add(albumLabel, 0, 5, 1, 1);
        gridPane.add(priceLabel, 0, 6, 1, 1);

        gridPane.add(selectionCB, 1, 0, 1, 1);
        gridPane.add(titleTF, 1, 1, 1, 1);
        gridPane.add(itemCodeTF, 1, 2, 1, 1);
        gridPane.add(descriptionTF, 1, 3, 1, 1);
        gridPane.add(artistTF, 1, 4, 1, 1);
        gridPane.add(albumTF, 1, 5, 1, 1);
        gridPane.add(priceTF, 1, 6, 1, 1);

        gridPane.setHgap(10);
        gridPane.setVgap(10);

        gridPane.setAlignment(Pos.CENTER);

        selectionCB.setItems(database);

        /* OLD METHOD - throws exception on some machines */

        // selectionCB.setConverter(new StringConverter<Song>()
        // {
            // @Override
            // public String toString(Song object) {
                // return object.getTitle();
            // }

            // @Override
            // public Song fromString(String string) {
                // return null;
            // }
        // });


        // NEW METHOD - set callback to update ComboBox after edit with getTitle
        factory = lv -> new ListCell<Song>()
        {
            @Override
            protected void updateItem(Song item, boolean empty)
            {
                super.updateItem(item, empty);
                setText(empty ? "" : item.getTitle());
            }
        };
        selectionCB.setCellFactory(factory);
        selectionCB.setButtonCell(factory.call(null));
        
        // initialize with first entry
        selectionCB.getSelectionModel().selectFirst();
        try
            {
                titleTF.setText(selectionCB.getValue().getTitle());
                titleTF.setDisable(true);
                itemCodeTF.setText(selectionCB.getValue().getItemCode());
                itemCodeTF.setDisable(true);
                descriptionTF.setText(selectionCB.getValue().getDescription());
                descriptionTF.setDisable(true);
                artistTF.setText(selectionCB.getValue().getArtist());
                artistTF.setDisable(true);
                albumTF.setText(selectionCB.getValue().getAlbum());
                albumTF.setDisable(true);
                priceTF.setText(selectionCB.getValue().getPrice());
                priceTF.setDisable(true);
            }
            catch ( NullPointerException exception)
            {
                titleTF.clear();
                titleTF.setDisable(true);
                itemCodeTF.clear();
                itemCodeTF.setDisable(true);
                descriptionTF.clear();
                descriptionTF.setDisable(true);
                artistTF.clear();
                artistTF.setDisable(true);
                albumTF.clear();
                albumTF.setDisable(true);
                priceTF.clear();
                priceTF.setDisable(true);
            }

        selectionCB.setOnAction( new selectionCBHandler() );
        
        return gridPane;
    }


    /**
     * Create an HBox with a row of buttons
     *
     * @return HBox with a row of buttons
     */
    public HBox createButtons()
    {
        HBox hb = new HBox(10);

        hb.getChildren().addAll(addButton,editButton,deleteButton,
                acceptButton,cancelButton);

        return hb;
    }


    /**
     * Combines the two button rows as a VBox
     *
     * @return a VBox with all buttons
     */
    public VBox createAllButtons()
    {
        VBox allButtons = new VBox(10);
        HBox buttons = createButtons();
        buttons.setAlignment(Pos.CENTER);

        HBox saveExitButtons = new HBox(10);
        saveExitButtons.getChildren().addAll(saveButton,exitButton);
        saveExitButtons.setAlignment(Pos.CENTER);

        allButtons.getChildren().addAll(buttons,saveExitButtons);
        allButtons.setAlignment(Pos.CENTER);

        acceptButton.setDisable(true);
        cancelButton.setDisable(true);
        if (database.isEmpty() )
        {
            editButton.setDisable(true);
            deleteButton.setDisable(true);
        }

        addButton.setOnAction( new addButtonHandler() );
        editButton.setOnAction( new editButtonHandler() );
        deleteButton.setOnAction( new deleteButtonHandler() );
        acceptButton.setOnAction( new acceptButtonHandler() );
        cancelButton.setOnAction( new cancelButtonHandler() );
        exitButton.setOnAction( new exitButtonHandler() );
        saveButton.setOnAction( new saveButtonHandler() );
        okButton.setOnAction( new okButtonHandler() );
        saveCancelButton.setOnAction( new saveCancelButtonHandler() );

        return allButtons;
    }


    /**
     * Method to write data from database to filename as a csv-style file
     *
     * @param database An OnservableList serving as song database
     * @param filename The desired output filename
     */
    private void writeData(ObservableList<Song> database, String filename)
    {
        String output_filename = filename;

        try ( BufferedWriter bw = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream(output_filename))) )
        {
            StringBuilder sb = new StringBuilder();

            sb.append("Title");
            sb.append(",");
            sb.append("Item Code");
            sb.append(",");
            sb.append("Description");
            sb.append(",");
            sb.append("Artist");
            sb.append(",");
            sb.append("Album");
            sb.append(",");
            sb.append("Price");
            sb.append("\n");

            for(Song s : database)
            {
                sb.append(s.getTitle());
                sb.append(",");
                sb.append(s.getItemCode());
                sb.append(",");
                sb.append(s.getDescription());
                sb.append(",");
                sb.append(s.getArtist());
                sb.append(",");
                sb.append(s.getAlbum());
                sb.append(",");
                sb.append(s.getPrice());
                sb.append("\n");
            }

            bw.write(sb.toString());
        }

        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    /**
     * Checks if string input is a number.
     *
     * @param str A string to check if numeric
     * @return boolean true if numeric, false otherwise
     */
    private boolean isNumeric(String str)
    {
        try
        {
            double d = Double.parseDouble(str);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }

        return true;
    }


    /**********************/
    /**  EVENT HANDLERS  **/
    /**********************/


    class selectionCBHandler implements EventHandler<ActionEvent>
    {
        public void handle ( ActionEvent e )
        {
            try
            {
                titleTF.setText(selectionCB.getValue().getTitle());
                titleTF.setDisable(true);
                itemCodeTF.setText(selectionCB.getValue().getItemCode());
                itemCodeTF.setDisable(true);
                descriptionTF.setText(selectionCB.getValue().getDescription());
                descriptionTF.setDisable(true);
                artistTF.setText(selectionCB.getValue().getArtist());
                artistTF.setDisable(true);
                albumTF.setText(selectionCB.getValue().getAlbum());
                albumTF.setDisable(true);
                priceTF.setText(selectionCB.getValue().getPrice());
                priceTF.setDisable(true);
            }
            catch ( NullPointerException exception)
            {
                titleTF.clear();
                titleTF.setDisable(true);
                itemCodeTF.clear();
                itemCodeTF.setDisable(true);
                descriptionTF.clear();
                descriptionTF.setDisable(true);
                artistTF.clear();
                artistTF.setDisable(true);
                albumTF.clear();
                albumTF.setDisable(true);
                priceTF.clear();
                priceTF.setDisable(true);
            }
        }
    }


    class addButtonHandler implements EventHandler<ActionEvent>
    {
        public void handle( ActionEvent e )
        {
            // disable/enable certain buttons
            addButton.setDisable(true);
            editButton.setDisable(true);
            deleteButton.setDisable(true);
            exitButton.setDisable(true);
            saveButton.setDisable(true);
            acceptButton.setDisable(false);
            cancelButton.setDisable(false);

            // enable textfields
            titleTF.setDisable(false);
            itemCodeTF.setDisable(false);
            descriptionTF.setDisable(false);
            artistTF.setDisable(false);
            albumTF.setDisable(false);
            priceTF.setDisable(false);

            // clear all textfields
            titleTF.clear();
            itemCodeTF.clear();
            descriptionTF.clear();
            artistTF.clear();
            albumTF.clear();
            priceTF.clear();

            //
            selectionCB.setVisible(false);

        }
    }


    class editButtonHandler implements EventHandler<ActionEvent>
    {
        public void handle( ActionEvent e )
        {
            // diable comboBox
            selectionCB.setDisable(true);

            // disable/enable certain buttons
            addButton.setDisable(true);
            editButton.setDisable(true);
            deleteButton.setDisable(true);
            exitButton.setDisable(true);
            saveButton.setDisable(true);
            acceptButton.setDisable(false);
            cancelButton.setDisable(false);

            // enable textfields
            titleTF.setDisable(false);
            itemCodeTF.setDisable(false);
            descriptionTF.setDisable(false);
            artistTF.setDisable(false);
            albumTF.setDisable(false);
            priceTF.setDisable(false);
        }
    }


    class deleteButtonHandler implements EventHandler<ActionEvent>
    {
        public void handle( ActionEvent e )
        {
            database.remove(selectionCB.getValue());

            if (database.isEmpty() )
            {
                editButton.setDisable(true);
                deleteButton.setDisable(true);
            }

            factory = lv -> new ListCell<Song>()
            {
                @Override
                protected void updateItem(Song item, boolean empty)
                {
                    super.updateItem(item, empty);
                    setText(empty ? "" : item.getTitle());
                }
            };
            selectionCB.setCellFactory(factory);
            selectionCB.setButtonCell(factory.call(null));
        }
    }


    class acceptButtonHandler implements EventHandler<ActionEvent>
    {
        public void handle( ActionEvent e )
        {
            if ( selectionCB.isVisible() ) // this is for 'edit' function
            {
                if ( titleTF.getText().trim().isEmpty() ||
                        itemCodeTF.getText().trim().isEmpty() ||
                        descriptionTF.getText().trim().isEmpty() ||
                        artistTF.getText().trim().isEmpty() ||
                        albumTF.getText().trim().isEmpty() ||
                        priceTF.getText().trim().isEmpty() )
                {
                    fail = new VBox(10);
                    failLabel = new Label("Entry must not be blank!");
                    fail.getChildren().addAll(failLabel);

                    fail.setPadding(new Insets(20, 40, 20, 40));

                    Scene failScene = new Scene(fail);

                    // New window (Stage)
                    failWindow.setTitle("");
                    failWindow.setScene(failScene);
                    failWindow.show();

                    if ( database.isEmpty() )
                    {
                        editButton.setDisable(true);
                        deleteButton.setDisable(true);
                    }
                }
                else if ( !isNumeric(priceTF.getText().trim()) )
                {
                    fail = new VBox(10);
                    failLabel = new Label("Price must be numeric!");
                    fail.getChildren().addAll(failLabel);
                    fail.setPadding(new Insets(20, 40, 20, 40));

                    Scene failScene = new Scene(fail);

                    // New window (Stage)
                    failWindow.setTitle("");
                    failWindow.setScene(failScene);
                    failWindow.show();
                }
                else
                {
                    selectionCB.getValue().setTitle(
                            titleTF.getText());

                    selectionCB.getValue().setItemCode(
                            itemCodeTF.getText());

                    selectionCB.getValue().setDescription(
                            descriptionTF.getText());

                    selectionCB.getValue().setArtist(
                            artistTF.getText());

                    selectionCB.getValue().setAlbum(
                            albumTF.getText());

                    selectionCB.getValue().setPrice(
                            priceTF.getText());

                    selectionCB.getSelectionModel().selectFirst();

                    try
                    {
                        selectionCB.setDisable(false);

                        titleTF.setText(
                                selectionCB.getValue().getTitle());
                        titleTF.setDisable(true);

                        itemCodeTF.setText(
                                selectionCB.getValue().getItemCode());
                        itemCodeTF.setDisable(true);

                        descriptionTF.setText(
                                selectionCB.getValue().getDescription());
                        descriptionTF.setDisable(true);

                        artistTF.setText(
                                selectionCB.getValue().getArtist());
                        artistTF.setDisable(true);

                        albumTF.setText(
                                selectionCB.getValue().getAlbum());
                        albumTF.setDisable(true);

                        priceTF.setText(
                                selectionCB.getValue().getPrice());
                        priceTF.setDisable(true);
                    }
                    catch ( NullPointerException exception)
                    {
                        titleTF.clear();
                        titleTF.setDisable(true);

                        itemCodeTF.clear();
                        itemCodeTF.setDisable(true);

                        descriptionTF.clear();
                        descriptionTF.setDisable(true);

                        artistTF.clear();
                        artistTF.setDisable(true);

                        albumTF.clear();
                        albumTF.setDisable(true);

                        priceTF.clear();
                        priceTF.setDisable(true);
                    }

                    // disable/enable certain buttons
                    addButton.setDisable(false);
                    editButton.setDisable(false);
                    deleteButton.setDisable(false);
                    exitButton.setDisable(false);
                    saveButton.setDisable(false);
                    acceptButton.setDisable(true);
                    cancelButton.setDisable(true);

                    //
                    selectionCB.setVisible(true);

                    //
                    factory = lv -> new ListCell<Song>()
                    {
                        @Override
                        protected void updateItem(Song item, boolean empty)
                        {
                            super.updateItem(item, empty);
                            setText(empty ? "" : item.getTitle());
                        }
                    };
                    selectionCB.setCellFactory(factory);
                    selectionCB.setButtonCell(factory.call(null));

                }
            }
            else // this is for 'add' function
            {
                if ( titleTF.getText().trim().isEmpty() ||
                        itemCodeTF.getText().trim().isEmpty() ||
                        descriptionTF.getText().trim().isEmpty() ||
                        artistTF.getText().trim().isEmpty() ||
                        albumTF.getText().trim().isEmpty() ||
                        priceTF.getText().trim().isEmpty() )
                {
                    fail = new VBox(10);
                    failLabel = new Label("Entry must not be blank!");
                    fail.getChildren().addAll(failLabel);
                    fail.setPadding(new Insets(20, 40, 20, 40));

                    Scene failScene = new Scene(fail);

                    // New window (Stage)
                    failWindow.setTitle("");
                    failWindow.setScene(failScene);
                    failWindow.show();

                    if ( database.isEmpty() )
                    {
                        editButton.setDisable(true);
                        deleteButton.setDisable(true);
                    }
                }
                else if ( !isNumeric(priceTF.getText().trim()) )
                {
                    fail = new VBox(10);
                    failLabel = new Label("Price must be numeric!");
                    fail.getChildren().addAll(failLabel);
                    fail.setPadding(new Insets(20, 40, 20, 40));

                    Scene failScene = new Scene(fail);

                    // New window (Stage)
                    failWindow.setTitle("");
                    failWindow.setScene(failScene);

                    failWindow.show();
                }
                else
                {
                    database.add(new Song(titleTF.getText(),
                        itemCodeTF.getText(), descriptionTF.getText(),
                        artistTF.getText(), albumTF.getText(),
                        priceTF.getText()));

                    selectionCB.getSelectionModel().selectFirst();
                    try
                    {
                        selectionCB.setDisable(false);

                        titleTF.setText(
                                selectionCB.getValue().getTitle());
                        titleTF.setDisable(true);

                        itemCodeTF.setText(
                                selectionCB.getValue().getItemCode());
                        itemCodeTF.setDisable(true);

                        descriptionTF.setText(
                                selectionCB.getValue().getDescription());
                        descriptionTF.setDisable(true);

                        artistTF.setText(
                                selectionCB.getValue().getArtist());
                        artistTF.setDisable(true);

                        albumTF.setText(
                                selectionCB.getValue().getAlbum());
                        albumTF.setDisable(true);

                        priceTF.setText(
                                selectionCB.getValue().getPrice());
                        priceTF.setDisable(true);
                    }
                    catch ( NullPointerException exception)
                    {
                        titleTF.clear();
                        titleTF.setDisable(true);

                        itemCodeTF.clear();
                        itemCodeTF.setDisable(true);

                        descriptionTF.clear();
                        descriptionTF.setDisable(true);

                        artistTF.clear();
                        artistTF.setDisable(true);

                        albumTF.clear();
                        albumTF.setDisable(true);

                        priceTF.clear();
                        priceTF.setDisable(true);
                    }

                    // disable/enable certain buttons
                    addButton.setDisable(false);
                    editButton.setDisable(false);
                    deleteButton.setDisable(false);
                    exitButton.setDisable(false);
                    saveButton.setDisable(false);
                    acceptButton.setDisable(true);
                    cancelButton.setDisable(true);

                    //
                    selectionCB.setVisible(true);

                    //
                    factory = lv -> new ListCell<Song>()
                    {
                        @Override
                        protected void updateItem(Song item, boolean empty)
                        {
                            super.updateItem(item, empty);
                            setText(empty ? "" : item.getTitle());
                        }
                    };
                    selectionCB.setCellFactory(factory);
                    selectionCB.setButtonCell(factory.call(null));
                }
            }
        }
    }


    class cancelButtonHandler implements EventHandler<ActionEvent>
    {
        public void handle( ActionEvent e )
        {
            try
            {
                selectionCB.setDisable(false);
                titleTF.setText(selectionCB.getValue().getTitle());
                titleTF.setDisable(true);
                itemCodeTF.setText(selectionCB.getValue().getItemCode());
                itemCodeTF.setDisable(true);
                descriptionTF.setText(selectionCB.getValue().getDescription());
                descriptionTF.setDisable(true);
                artistTF.setText(selectionCB.getValue().getArtist());
                artistTF.setDisable(true);
                albumTF.setText(selectionCB.getValue().getAlbum());
                albumTF.setDisable(true);
                priceTF.setText(selectionCB.getValue().getPrice());
                priceTF.setDisable(true);
            }
            catch ( NullPointerException exception)
            {
                titleTF.clear();
                titleTF.setDisable(true);
                itemCodeTF.clear();
                itemCodeTF.setDisable(true);
                descriptionTF.clear();
                descriptionTF.setDisable(true);
                artistTF.clear();
                artistTF.setDisable(true);
                albumTF.clear();
                albumTF.setDisable(true);
                priceTF.clear();
                priceTF.setDisable(true);
            }

            // disable/enable certain buttons
            addButton.setDisable(false);
            editButton.setDisable(false);
            deleteButton.setDisable(false);
            exitButton.setDisable(false);
            saveButton.setDisable(false);
            acceptButton.setDisable(true);
            cancelButton.setDisable(true);

            //
            selectionCB.setVisible(true);
        }
    }


    class saveButtonHandler implements EventHandler<ActionEvent>
    {
        public void handle( ActionEvent e )
        {
            GridPane gp = new GridPane();
            gp.add(filenameLabel,0,0,1,1);
            gp.add(filenameTF,1,0,1,1);
            gp.add(okButton,0,1,1,1);
            gp.add(saveCancelButton,1,1,1,1);

            gp.setPadding(new Insets(20, 40, 20, 40));
            gp.setHgap(10);
            gp.setVgap(10);

            Scene saveScene = new Scene(gp);

            // New window (Stage)
            saveWindow.setTitle("Save As...");
            saveWindow.setScene(saveScene);

            saveWindow.show();
        }
    }


    class exitButtonHandler implements EventHandler<ActionEvent>
    {
        public void handle( ActionEvent e )
        {
            writeData(database,filename);

            System.exit(1);
        }
    }


    class okButtonHandler implements EventHandler<ActionEvent>
    {
        public void handle( ActionEvent e )
        {
            filename = filenameTF.getText();
            writeData(database,filename);
            System.exit(1);
        }
    }


    class saveCancelButtonHandler implements EventHandler<ActionEvent>
    {
        public void handle( ActionEvent e )
        {
            saveWindow.close();
        }
    }

}
