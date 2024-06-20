package com.asu.bookstore.gui;

import com.asu.bookstore.models.BookModel;
import com.asu.bookstore.main.Main;
import com.asu.bookstore.models.InventoryModel;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class EditBookGUI {
    //Prepare edit book scene
    private static Scene editScene(BookModel book) {
        //Create the layout
        GridPane layout = new GridPane(15, 10);
        layout.setAlignment(Pos.CENTER);
        
        //Create the text fields and show their existing values
        TextField nameField = new TextField(book.getName());
        nameField.end();     //Put cursor at the end of the text
        TextField authorField = new TextField(book.getAuthor());
        TextField publisherField = new TextField(book.getPublisherName());
        TextField priceField = new TextField(String.valueOf(book.getPrice()));
        TextField amountField = new TextField( String.valueOf(
                Main.Inventories.get(book.getBookIndex()).getAmount() ) );
        TextField categoryField = new TextField(book.getCategory());
        TextField isbnField = new TextField(book.getIsbn());
        
        //Create update button
        Button updateButton = new Button("Update");
        updateButton.setFont(Font.font(15));
        updateButton.setOnAction(e -> handleEdit(book, nameField.getText().trim(),
                authorField.getText().trim(), publisherField.getText().trim(), priceField.getText().trim(),
                categoryField.getText().trim(),isbnField.getText().trim(), amountField.getText().trim()));
        //Create cancel button
        Button cancelButton = new Button("Cancel");
        cancelButton.setFont(Font.font(15));
        cancelButton.setOnAction(e -> HomeGUI.get_HomeScene());
        
        //Create horizontal box for buttons
        HBox buttonBox = new HBox(10, updateButton, cancelButton);
        //Add components to the layout
        layout.add(HelperGUI.createLabel("Name: "), 0, 0);
        layout.add(nameField, 1, 0);
        layout.add(HelperGUI.createLabel("Author: "), 0, 1);
        layout.add(authorField, 1, 1);
        layout.add(HelperGUI.createLabel("Publisher: "), 0, 2);
        layout.add(publisherField, 1, 2);
        layout.add(HelperGUI.createLabel("Price: "), 0, 3);
        layout.add(priceField, 1, 3);
        layout.add(HelperGUI.createLabel("Amount: "), 0, 4);
        layout.add(amountField, 1, 4);
        layout.add(HelperGUI.createLabel("Category: "), 0, 5);
        layout.add(categoryField, 1, 5);
        layout.add(HelperGUI.createLabel("ISBN: "), 0, 6);
        layout.add(isbnField, 1, 6);
        layout.add(buttonBox, 1, 8);

        return new Scene(layout);
    }
    //Edit book and show warning
    private static void handleEdit(BookModel book, String name, String author,
            String publisher, String price,String category, String isbn, String amount) {
        if (name.isEmpty()|| author.isEmpty()|| publisher.isEmpty()
                || price.isEmpty()|| category.isEmpty()|| isbn.isEmpty()|| amount.isBlank())
            HelperGUI.get_ResultScene(false, "Please Fill All Fields!");
        else {
            BookModel updated = new BookModel(name, author, publisher,
                    Double.parseDouble(price), category, isbn);
            InventoryModel inv = new InventoryModel(name, Integer.parseInt(amount));
            updated.editBook(book, inv);
        }
    }
    //Stage edit book scene
    public static void get_EditScene(BookModel book) {
        Stage editStage = Main.PStage;
        editStage.setScene(editScene(book));
        editStage.setTitle("Bookstore - Edit Book Page");
    }
}