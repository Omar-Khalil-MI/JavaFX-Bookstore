package com.asu.bookstore.gui;

import com.asu.bookstore.models.BookModel;
import com.asu.bookstore.models.InventoryModel;
import com.asu.bookstore.main.Main;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class AddBookGUI {
    
    private static void handleAdd(String name, String author, String price,
            String publisher,String category, String isbn, String amount) {
        if (name.isEmpty() || author.isEmpty() || price.isEmpty() || publisher.isEmpty()
                || category.isEmpty() || isbn.isEmpty() || amount.isEmpty())
            HelperGUI.get_ResultScene(false, "Please Fill All Fields!");
        else {
            try {
                BookModel book = new BookModel(name, author, publisher,
                    Double.parseDouble(price), category, isbn);
                InventoryModel i = new InventoryModel(name, Integer.parseInt(amount));
                boolean valid = true;
                String message = "Data Updated Successfully!";
                if (isbn.length() != 13 ) {
                    valid = false;
                    message = "Invalid ISBN!";
                }
                else if (book.getPrice() < 0) {
                    valid = false;
                    message = "Invalid Price!";
                }
                else if (i.getAmount() < 0) {
                    valid = false;
                    message = "Invalid Amount!";
                }
                if (valid)
                    book.addBook(i);
                HelperGUI.get_ResultScene(valid, message);
            } catch(NumberFormatException e){
                HelperGUI.get_ResultScene(false, "Amount and Price Must Be Numeric!");
            }
        }
    }
    //Stage adding book scene
    public static void get_AddScene() {
        Stage addStage = Main.PStage;
        addStage.setScene(addScene());
        addStage.setTitle("Bookstore - Add Book Page");
    }
    //Prepare adding book scene
    private static Scene addScene() {
        //Create the layout
        GridPane layout = new GridPane(10, 15);
        layout.setAlignment(Pos.CENTER);
    
        //Create text fieleds
        TextField nameField = new TextField();
        TextField authorField = new TextField();
        TextField pricefField = new TextField();
        TextField publisherNamField = new TextField();
        TextField categoryField = new TextField();
        TextField amountField = new TextField();
        TextField isbnField = new TextField();
        
        //Create add button
        Button addButton = new Button("Add Book");
        addButton.setFont(Font.font(15));
        addButton.setOnAction(e -> handleAdd(nameField.getText().trim(), authorField.getText().trim(),
                pricefField.getText().trim(), publisherNamField.getText().trim(),
                categoryField.getText().trim(), isbnField.getText().trim(), amountField.getText().trim()));
        //Create cancel button
        Button cancelButton = new Button("Cancel");
        cancelButton.setFont(Font.font(15));
        cancelButton.setOnAction(e -> HomeGUI.get_HomeScene());
        
        HBox buttonBox = new HBox(10, addButton, cancelButton);
        
        //Add components to the layout
        layout.add(HelperGUI.createLabel("Name: "), 0, 0);
        layout.add(nameField, 1, 0);
        layout.add(HelperGUI.createLabel("Author: "), 0, 1);
        layout.add(authorField, 1, 1);
        layout.add(HelperGUI.createLabel("Price: "), 0, 2);
        layout.add(pricefField, 1, 2);
        layout.add(HelperGUI.createLabel("Publisher: "), 0, 3);
        layout.add(publisherNamField, 1, 3);
        layout.add(HelperGUI.createLabel("Category: "), 0, 4);
        layout.add(categoryField, 1, 4);
        layout.add(HelperGUI.createLabel("Amount: "), 0, 5);
        layout.add(amountField, 1, 5);
        layout.add(HelperGUI.createLabel("ISBN: "), 0, 6);
        layout.add(isbnField, 1, 6);
        layout.add(buttonBox, 1, 8);
        
        // Return the add scene
        return new Scene(layout);
    }
}