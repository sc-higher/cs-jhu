/**
 * This program is part of my response to Assignment 12 for the class 605.201.81
 * Intro to Programming Using Java at the JHU EPP CS program.
 *
 * This program creates a GUI that allows the user to calculate the cost of a
 * pizza, based on size and topping selection. Price is updated in real time.
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
import javafx.scene.image.Image;

public class PriceCalculator extends Application
{
    // initialize attributes
    int numberAvailableToppings = 4;
    Price pizza = new Price(numberAvailableToppings);
    Label totalPrice;
    String sPizzaCost = "$0.00";

    // this SimpleStringProperty is necessary because it allows PizzaCost to
    // be Observable - this is used later to BIND pizzaCost to the totalPrice
    // Label, allowing it to be automatically updated with any change (such
    // as a button press)
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
        myStage.getIcons().add(
                new Image(
                        PriceCalculator.class.getResourceAsStream(
                                "icon.jpg" )));

        // set root node
        FlowPane rootNode = new FlowPane(20, 20);

        // create a scene
        Scene myScene = new Scene(rootNode, 460, 350);
        myScene.getStylesheets().add("PriceCalculator.css");

        // set scene on stage
        myStage.setScene(myScene);

        // create label to report price
        totalPrice = new Label(sPizzaCost);
        // this BIND allows the totalPrice Label to be updated automatically
        // with any change in pizzaCost, which is updated with any change via
        // pizzaCost.setValue( pizza.printTotalCost() ) in the event handlers
        totalPrice.textProperty().bind(pizzaCost);
        Pane pizzaCost = new BorderedTitledPane("Total Cost",
                totalCost());

        // create size selection flow pane
        Pane pizzaSize = new BorderedTitledPane("Pizza Size",
                sizeSelection());

        // create topping selection flow pane
        Pane pizzaToppings = new BorderedTitledPane("Pizza Toppings",
                toppingSelection());

        // add to scene
        rootNode.getChildren().addAll(pizzaSize,pizzaToppings,pizzaCost);

        // show stage and scene
        myStage.show();

    }


    public FlowPane totalCost() {
        // create size selection flowpane
        FlowPane totalCostPane = new FlowPane(10, 10);
        totalCostPane.setAlignment(Pos.CENTER);

        // add buttons as children to size selection pane
        totalCostPane.getChildren().addAll(totalPrice);

        // return size flow pane
        return totalCostPane;
    }


    public FlowPane sizeSelection()
    {
        // create size selection flowpane
        FlowPane sizeSelectionPane = new FlowPane(10,10);
        sizeSelectionPane.setAlignment(Pos.CENTER);

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
//        sizeSelectionPane.setStyle("-fx-border-color: red;");

        // return size flow pane
        return sizeSelectionPane;
    }


    public FlowPane toppingSelection()
    {
        // create size selection flowpane
        FlowPane toppingSelectionPane = new FlowPane(10,10);
        toppingSelectionPane.setAlignment(Pos.CENTER);

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

    class BorderedTitledPane extends StackPane
    {
        BorderedTitledPane(String titleString, Node content)
        {
            Label title = new Label(" " + titleString + " ");
            title.getStyleClass().add("bordered-titled-title");
            StackPane.setAlignment(title, Pos.TOP_CENTER);

            StackPane contentPane = new StackPane();
            content.getStyleClass().add("bordered-titled-content");
            contentPane.getChildren().add(content);

            getStyleClass().add("bordered-titled-border");
            getChildren().addAll(title, contentPane);
        }
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
                if ( !cbMushroom.isSelected() & !cbPepperoni.isSelected() )
                {
                    cbPlain.setSelected(true);
                }
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
                if ( !cbSausage.isSelected() & !cbPepperoni.isSelected() )
                {
                    cbPlain.setSelected(true);
                }
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
                if ( !cbSausage.isSelected() & !cbMushroom.isSelected() )
                {
                    cbPlain.setSelected(true);
                }
            }

            pizzaCost.setValue( pizza.printTotalCost() );
        }
    }


}
