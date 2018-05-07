/**
 *
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
import javafx.collections.ObservableMap;

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
    Label modeLabel = new Label("Mode:");

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


    // main method
    public static void main(String[] args)
    {
        // get command line argument for database filename (if applicable)
        if ( args.length == 1 )
        {
            filename = args[0];

            try
            {
                // lazy file exists test
                File exists = new File(filename);
                Scanner exist_test = new Scanner(exists);

                //
                ReadCSV songCSV = new ReadCSV(filename);
                database = FXCollections.observableArrayList(songCSV.toArrayList());
            }
            catch (FileNotFoundException exception)
            {
                System.out.println("\nFile does not exist.");
                System.out.println("Create new song database? (1 = yes, 2 = no)");
                UserInput selectionMenu = new UserInput(1,2);
                selection = selectionMenu.getUserSelection();

                if ( selection != 1 )
                {
                    System.out.println("\nGoodbye!");
                    System.exit(1);
                }

                database = FXCollections.observableArrayList();
            }

        }
        else
        {
            System.out.println("\nInvalid number of arguments.");
            System.out.println("Create new song database? (1 = yes, 2 = no)");
            UserInput selectionMenu = new UserInput(1,2);
            selection = selectionMenu.getUserSelection();

            if ( selection != 1 )
            {
                System.out.println("\nGoodbye!");
                System.exit(1);
            }

            database = FXCollections.observableArrayList();
        }

        // launch song database gui
        launch(args);
    }


    // override the start() method
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

        selectionCB.setConverter(new StringConverter<Song>()
        {
            @Override
            public String toString(Song object) {
                return object.getTitle();
            }

            @Override
            public Song fromString(String string) {
                return null;
            }
        });


        selectionCB.setOnAction( new selectionCBHandler() );


        return gridPane;
    }

    public HBox createButtons()
    {
        HBox hb = new HBox(10);

        hb.getChildren().addAll(addButton,editButton,deleteButton,acceptButton,cancelButton);

        return hb;
    }

    public VBox createAllButtons()
    {
        VBox allButtons = new VBox(10);
        HBox buttons = createButtons();
        buttons.setAlignment(Pos.CENTER);
        allButtons.getChildren().addAll(buttons,exitButton);
        allButtons.setAlignment(Pos.CENTER);

        acceptButton.setDisable(true);
        cancelButton.setDisable(true);

        addButton.setOnAction( new addButtonHandler() );
        editButton.setOnAction( new editButtonHandler() );
        deleteButton.setOnAction( new deleteButtonHandler() );
        acceptButton.setOnAction( new acceptButtonHandler() );
        cancelButton.setOnAction( new cancelButtonHandler() );
        exitButton.setOnAction( new exitButtonHandler() );

        return allButtons;
    }



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
        }
    }

    class acceptButtonHandler implements EventHandler<ActionEvent>
    {
        public void handle( ActionEvent e )
        {
            if ( selectionCB.isVisible() ) // this is for 'edit' function
            {
                selectionCB.getValue().setTitle(titleTF.getText());
                selectionCB.getValue().setItemCode(itemCodeTF.getText());
                selectionCB.getValue().setDescription(descriptionTF.getText());
                selectionCB.getValue().setArtist(artistTF.getText());
                selectionCB.getValue().setAlbum(albumTF.getText());
                selectionCB.getValue().setPrice(priceTF.getText());
            }
            else // this is for 'add' function
            {
                database.add(new Song(titleTF.getText(),itemCodeTF.getText(),
                        descriptionTF.getText(), artistTF.getText(),
                        albumTF.getText(),priceTF.getText()));
            }

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
            acceptButton.setDisable(true);
            cancelButton.setDisable(true);

            //
            selectionCB.setVisible(true);

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
            acceptButton.setDisable(true);
            cancelButton.setDisable(true);

            //
            selectionCB.setVisible(true);
        }
    }

    class exitButtonHandler implements EventHandler<ActionEvent>
    {
        public void handle( ActionEvent e )
        {
            writeData(database,"song-database.csv");

            System.exit(1);
        }
    }





    // OLD STUFF!!!!

//    public FlowPane totalCost() {
//        // create size selection flowpane
//        FlowPane totalCostPane = new FlowPane(10, 10);
//        totalCostPane.setAlignment(Pos.CENTER);
//
//        // add buttons as children to size selection pane
//        totalCostPane.getChildren().addAll(totalPrice);
//
//        // return size flow pane
//        return totalCostPane;
//    }
//
//
//    public FlowPane sizeSelection()
//    {
//        // create size selection flowpane
//        FlowPane sizeSelectionPane = new FlowPane(10,10);
//        sizeSelectionPane.setAlignment(Pos.CENTER);
//
//        // create size radio buttons
//        rbSmall = new RadioButton("Small");
//        rbMedium = new RadioButton("Medium");
//        rbLarge = new RadioButton("Large");
//
//        // create size toggle group
//        ToggleGroup tgSize = new ToggleGroup();
//
//        // add size radio buttons to size toggle group
//        rbSmall.setToggleGroup(tgSize);
//        rbMedium.setToggleGroup(tgSize);
//        rbLarge.setToggleGroup(tgSize);
//
//        // handle action events for size radio buttons
//        rbSmall.setOnAction( new smallHandler() );
//        rbMedium.setOnAction( new mediumHandler() );
//        rbLarge.setOnAction( new largeHandler() );
//
//        // make small size default
//        rbSmall.fire();
//
//        // add buttons as children to size selection pane
//        sizeSelectionPane.getChildren().addAll(rbSmall, rbMedium, rbLarge);
////        sizeSelectionPane.setStyle("-fx-border-color: red;");
//
//        // return size flow pane
//        return sizeSelectionPane;
//    }
//
//
//    public FlowPane toppingSelection()
//    {
//        // create size selection flowpane
//        FlowPane toppingSelectionPane = new FlowPane(10,10);
//        toppingSelectionPane.setAlignment(Pos.CENTER);
//
//        // create topping check box buttons
//        cbPlain = new CheckBox("Plain");
//        cbSausage = new CheckBox("Sausage");
//        cbMushroom = new CheckBox("Mushroom");
//        cbPepperoni = new CheckBox("Pepperoni");
//
//        // handle action events for size radio buttons
//        cbPlain.setOnAction( new plainHandler() );
//        cbSausage.setOnAction( new sausageHandler() );
//        cbMushroom.setOnAction( new mushroomHandler() );
//        cbPepperoni.setOnAction( new pepperoniHandler() );
//
//        // make small size default
//        cbPlain.fire();
//
//        // add buttons as children to size selection pane
//        toppingSelectionPane.getChildren().addAll(cbPlain, cbSausage,
//                cbMushroom, cbPepperoni);
//
//        // return size flow pane
//        return toppingSelectionPane;
//    }
//
//    class BorderedTitledPane extends StackPane
//    {
//        BorderedTitledPane(String titleString, Node content)
//        {
//            Label title = new Label(" " + titleString + " ");
//            title.getStyleClass().add("bordered-titled-title");
//            StackPane.setAlignment(title, Pos.TOP_CENTER);
//
//            StackPane contentPane = new StackPane();
//            content.getStyleClass().add("bordered-titled-content");
//            contentPane.getChildren().add(content);
//
//            getStyleClass().add("bordered-titled-border");
//            getChildren().addAll(title, contentPane);
//        }
//    }
//
//
//
//
//    /* HANDLERS */
//
//    class mushroomHandler implements EventHandler<ActionEvent>
//    {
//        public void handle( ActionEvent e )
//        {
//            if ( cbMushroom.isSelected() )
//            {
//                pizza.setTopping(0,false);
//                pizza.setTopping(2,true);
//
//                cbPlain.setSelected(false);
//            }
//            else
//            {
//                pizza.setTopping(2,false);
//                if ( !cbSausage.isSelected() & !cbPepperoni.isSelected() )
//                {
//                    cbPlain.setSelected(true);
//                }
//            }
//
//            pizzaCost.setValue( pizza.printTotalCost() );
//        }
//    }


}
