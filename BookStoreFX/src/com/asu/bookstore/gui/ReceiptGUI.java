package com.asu.bookstore.gui;

import com.asu.bookstore.models.OrderModel;
import com.asu.bookstore.main.Main;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ReceiptGUI {
    //Prepare receipt scene
    private static Scene receiptScene(OrderModel order) {
        //Create layout
        VBox layout = new VBox(30);
        layout.setAlignment(Pos.CENTER);
        
        //Create order label
        Label lablel = new Label(order.toString());
        lablel.setFont(Font.font(18));
        //Create return button
        Button returnButton = new Button("Return Home");
        returnButton.setOnAction(e -> HomeGUI.get_HomeScene());
        //Add components to layout
        layout.getChildren().addAll(lablel, returnButton);
        
        return new Scene(layout);
    }
    //Stage receipt scene
    public static void get_ReceiptScene(OrderModel order) {
        Stage receipt = Main.PStage;
        receipt.setScene(receiptScene(order));
        receipt.setTitle("Bookstore - Receipt Page");
    }
}