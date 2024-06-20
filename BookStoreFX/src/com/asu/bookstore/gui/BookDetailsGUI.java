package com.asu.bookstore.gui;

import com.asu.bookstore.models.BookModel;
import com.asu.bookstore.main.Main;
import com.asu.bookstore.models.OrderModel;
import java.time.LocalDate;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class BookDetailsGUI {
    //Prepare book details scene
    private static Scene bookScene(BookModel book) {
        //Create the layout
        VBox layout = new VBox();
        layout.setPadding(new Insets(30, 20, 20, 30));
        
        //Create buy button
        Button buyButton = new Button("Buy Book");
        buyButton.setOnAction(e -> {
            OrderModel order = new OrderModel(Main.SessionUser.getUserName(),
                    book.getPrice(), LocalDate.now(), book.getName());
            order.addOrder(book);
            ReceiptGUI.get_ReceiptScene(order);
        });
        //Create cancel button
        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(e -> HomeGUI.get_HomeScene());
        
        //Create label for book details
        Label lablel = new Label("Name: " + book.getName() + "\nAuthor: "
                + book.getAuthor() + "\nCateogry: " + book.getCategory()
                + "\n" + "Publisher: " + book.getPublisherName() + "\nPrice: $"
                + String.valueOf(book.getPrice() + "\nISBN: " + book.getIsbn() + "\n\n\n"));
        lablel.setFont(Font.font(18));
        
        //Create book image
        Image image = new Image("resources/" + book.getImage() + ".jpg");
        ImageView view = new ImageView(image);
        view.setFitHeight(350);
        view.setFitWidth(250);
        
        //Create horizontal box for the buttons
        HBox buttonBox = new HBox(15);
        buttonBox.getChildren().addAll(buyButton, cancelButton);
        //Add components to layout
        layout.getChildren().addAll(view, lablel, buttonBox);
        //Add layout to a scroll pane
        ScrollPane pane = new ScrollPane(layout);
        return new Scene(pane);
    }
    //Stage book details scene
    public static void get_BookScene(BookModel book) {
        Stage bookStage = Main.PStage;
        bookStage.setScene(bookScene(book));
        bookStage.setTitle("Bookstore - " + book.getName());
    }
}