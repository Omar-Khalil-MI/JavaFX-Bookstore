package com.asu.bookstore.gui;

import com.asu.bookstore.main.Main;
import com.asu.bookstore.models.OrderModel;
import com.asu.bookstore.models.ReaderModel;
import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class OrdersGUI {
    //Prepare orderes scene
    private static Scene ordersScene(boolean isAdmin) {
        //Retrieve reader or all orders
        ArrayList<OrderModel> list = (isAdmin)? Main.Orders: ReaderModel.viewOrders();
        GridPane layout = new GridPane(80, 50);
        layout.setAlignment(Pos.CENTER);
        //Counter for the grid pane
        int counter = 1;
        
        //Create return button
        Button returnButton = new Button("Return");
        returnButton.setFont(Font.font(15));
        returnButton.setOnAction(e -> {
            HomeGUI.get_HomeScene();
        });
        //Add table headers
        layout.add(returnButton, 0, 0);
        layout.add(HelperGUI.createLabel("Order ID"), 0, counter); 
        layout.add(HelperGUI.createLabel("Book Name"), 1, counter);            
        layout.add(HelperGUI.createLabel("Cost"), 2, counter);
        layout.add(HelperGUI.createLabel("Date"), 3, counter++);
        
        //Add table content
        for (OrderModel order : list) {
            layout.add(HelperGUI.createLabel(String.valueOf(order.getOrderID())), 0, counter); 
            layout.add(HelperGUI.createLabel(order.getBookName()), 1, counter);            
            layout.add(HelperGUI.createLabel(String.valueOf(order.getCost())), 2, counter);
            layout.add(HelperGUI.createLabel(String.valueOf(order.getDate())), 3, counter++);
        }
        //Add layout to scroll pane
        ScrollPane pane = new ScrollPane(layout);
        pane.setPadding(new Insets(50, 0, 0, 50));
        return new Scene(pane);
    }
    //Stage orders scene
    public static void get_ordersScene(boolean isAdmin) {
        Stage oderStage = Main.PStage;
        oderStage.setScene(ordersScene(isAdmin));
        oderStage.setTitle("Bookstore - Orders Page");
    }
}
