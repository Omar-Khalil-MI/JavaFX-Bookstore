package com.asu.bookstore.gui;

import com.asu.bookstore.models.BookModel;
import com.asu.bookstore.main.Main;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class DeleteBookGUI {
    //Prepare delete book scene
    private static Scene deleteScene(BookModel book) {
        //Create the layout
        GridPane layout = new GridPane(0, 30);
        layout.setAlignment(Pos.CENTER);
        
        //Create delete button
        Button deleteButton = new Button("Delete Book");
        deleteButton.setFont(Font.font(15));
        deleteButton.setOnAction(e -> handleDelete(book));
        //Create cancel button
        Button cancelButton = new Button("Cancel");
        cancelButton.setFont(Font.font(15));
        cancelButton.setOnAction(e -> HomeGUI.get_HomeScene());
        
        //Create horizontal box for buttons
        HBox buttonBox = new HBox(20);
        buttonBox.getChildren().addAll(deleteButton, cancelButton);
        //Create label for the deletion message
        Label deleteLabel = new Label("Delete book: " + book.getName() + "?");
        deleteLabel.setFont(Font.font(20));
        //Add components to the layout
        layout.add(deleteLabel, 0, 0);
        layout.add(buttonBox, 0, 1);
        return new Scene(layout);    
    }
    
    private static void handleDelete(BookModel book) {
        book.deleteBook();
        HelperGUI.WarningStage("Book Deleted!");
        HomeGUI.get_HomeScene();
    }
    //Stage delete book scene
    public static void get_DeleteScene(BookModel book) {
        Stage deleteStage = Main.PStage;
        deleteStage.setScene(deleteScene(book));
        deleteStage.setTitle("Bookstore - Delete Book Page");
    }
}