/**
 * This program is part of my response to Assignment 11 for the class 605.201.81
 * Intro to Programming Using Java at the JHU EPP CS program.
 *
 * This program will allow the user to import a csv-style contact list, add or
 * remove contacts to the list, and export the list as a csv-style file.
 *
 * @author: Sean Connor
 */

import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.event.*;
import javafx.geometry.*;
import javafx.beans.property.*;

public class PriceCalculator extends Application
{
    // initialize attributes
    int numberAvailableToppings = 4;
    Price pizza = new Price(numberAvailableToppings);
    Label totalPrice;
    String sPizzaCost = "$0.00";
    SimpleStringProperty pizzaCost = new SimpleStringProperty(sPizzaCost);

    // initialize size attributes
    RadioButton rbSmall;
    RadioButton rbMedium;
    RadioButton rbLarge;

    // initialize topping attributes
    CheckBox cbPlain;
    CheckBox cbSausage;
    CheckBox cbMushroom;
    CheckBox cbPepperoni;

    // main method
    public static void main(String[] args)
    {
        launch(args);
    }


    // override the start() method
    public void start(Stage myStage)
    {
        // set title
        myStage.setTitle("Pizza Price Calculator");

        // set root node
        FlowPane rootNode = new FlowPane(20, 20);

        // create a scene
        Scene myScene = new Scene(rootNode, 300, 300);

        // set scene on stage
        myStage.setScene(myScene);

        // create label to report price
        totalPrice = new Label(sPizzaCost);
        totalPrice.textProperty().bind(pizzaCost);

        // create size selection flow pane
        FlowPane sizePane = sizeSelection();

        // create topping selection flow pane
        FlowPane toppingPane = toppingSelection();

        // add to scene
        rootNode.getChildren().addAll(sizePane,toppingPane,totalPrice);

        // show stage and scene
        myStage.show();

    }

    public FlowPane sizeSelection()
    {
        // create size selection flowpane
        FlowPane sizeSelectionPane = new FlowPane(10,10);

        // create size radio buttons
        rbSmall = new RadioButton("Small");
        rbMedium = new RadioButton("Medium");
        rbLarge = new RadioButton("Large");

        // create size toggle group
        ToggleGroup tgSize = new ToggleGroup();

        // add size radio buttons to size toggle group
        rbSmall.setToggleGroup(tgSize);
        rbMedium.setToggleGroup(tgSize);
        rbLarge.setToggleGroup(tgSize);

        // handle action events for size radio buttons
        rbSmall.setOnAction( new smallHandler() );
        rbMedium.setOnAction( new mediumHandler() );
        rbLarge.setOnAction( new largeHandler() );

        // make small size default
        rbSmall.fire();

        // add buttons as children to size selection pane
        sizeSelectionPane.getChildren().addAll(rbSmall, rbMedium, rbLarge);

        // return size flow pane
        return sizeSelectionPane;
    }


    public FlowPane toppingSelection()
    {
        // create size selection flowpane
        FlowPane toppingSelectionPane = new FlowPane(10,10);

        // create topping check box buttons
        cbPlain = new CheckBox("Plain");
        cbSausage = new CheckBox("Sausage");
        cbMushroom = new CheckBox("Mushroom");
        cbPepperoni = new CheckBox("Pepperoni");

        // handle action events for size radio buttons
        cbPlain.setOnAction( new plainHandler() );
        cbSausage.setOnAction( new sausageHandler() );
        cbMushroom.setOnAction( new mushroomHandler() );
        cbPepperoni.setOnAction( new pepperoniHandler() );

        // make small size default
        cbPlain.fire();

        // add buttons as children to size selection pane
        toppingSelectionPane.getChildren().addAll(cbPlain, cbSausage,
                cbMushroom, cbPepperoni);

        // return size flow pane
        return toppingSelectionPane;
    }









    // size selection handlers

    class smallHandler implements EventHandler<ActionEvent>
    {
        public void handle( ActionEvent e )
        {
            pizza.setSize(0);
            pizzaCost.setValue( pizza.printTotalCost() );
        }
    }

    class mediumHandler implements EventHandler<ActionEvent>
    {
        public void handle( ActionEvent e )
        {
            pizza.setSize(1);
            pizzaCost.setValue( pizza.printTotalCost() );
        }
    }

    class largeHandler implements EventHandler<ActionEvent>
    {
        public void handle( ActionEvent e )
        {
            pizza.setSize(2);
            pizzaCost.setValue( pizza.printTotalCost() );
        }
    }

    // topping selection handlers

    class plainHandler implements EventHandler<ActionEvent>
    {
        public void handle( ActionEvent e )
        {
            if ( cbPlain.isSelected() )
            {
                pizza.setTopping(0,true);
                for (int i = 1; i < numberAvailableToppings; i++)
                {
                    pizza.setTopping(i,false);
                }
                cbSausage.setSelected(false);
                cbMushroom.setSelected(false);
                cbPepperoni.setSelected(false);
            }
            else
            {
                pizza.setTopping(0,false);
                pizza.setTopping(3,true);
                cbPepperoni.setSelected(true);
            }

            pizzaCost.setValue( pizza.printTotalCost() );
        }
    }

    class sausageHandler implements EventHandler<ActionEvent>
    {
        public void handle( ActionEvent e )
        {
            if ( cbSausage.isSelected() )
            {
                pizza.setTopping(0,false);
                pizza.setTopping(1,true);

                cbPlain.setSelected(false);
            }
            else
            {
                pizza.setTopping(1,false);
            }

            pizzaCost.setValue( pizza.printTotalCost() );
        }
    }

    class mushroomHandler implements EventHandler<ActionEvent>
    {
        public void handle( ActionEvent e )
        {
            if ( cbMushroom.isSelected() )
            {
                pizza.setTopping(0,false);
                pizza.setTopping(2,true);

                cbPlain.setSelected(false);
            }
            else
            {
                pizza.setTopping(2,false);
            }

            pizzaCost.setValue( pizza.printTotalCost() );
        }
    }

    class pepperoniHandler implements EventHandler<ActionEvent>
    {
        public void handle( ActionEvent e )
        {
            if ( cbPepperoni.isSelected() )
            {
                pizza.setTopping(0,false);
                pizza.setTopping(3,true);

                cbPlain.setSelected(false);
            }
            else
            {
                pizza.setTopping(3,false);
            }

            pizzaCost.setValue( pizza.printTotalCost() );
        }
    }


}
