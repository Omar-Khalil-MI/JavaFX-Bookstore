package com.asu.bookstore.gui;

import com.asu.bookstore.models.BookModel;
import com.asu.bookstore.main.Main;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.layout.FlowPane;

public class HomeGUI{
    //Stage home scene
    public static void get_HomeScene() {
        Stage homeStage = Main.PStage;
        homeStage.setScene(homeScene());
        homeStage.setTitle("Bookstore - Home Page");
    }
    //Prepare home scene
    private static Scene homeScene() {
        //Create layout
        VBox layout = new VBox(20);
        layout.setPadding(new Insets(30, 20, 20, 30));
        //Create horizontal box for buttons
        HBox buttonBox = new HBox(15);
        buttonBox.setAlignment(Pos.TOP_CENTER);
        buttonBox.setPadding(new Insets(0, 0, 40, 0));
        
        //Create search field
        TextField searchField = new TextField();
        searchField.setPrefWidth(750);
        //Create search button
        Button searchButton = new Button("Search");
        searchButton.setOnAction(e -> SearchBookGUI.get_SearchScene(searchField.getText()));
        buttonBox.getChildren().addAll(searchField, searchButton);
        
        //Create login button if signed out
        //Else create logut button
        if (Main.SessionUser == null & Main.SessionAdmin == null) {
            Button loginButton = new Button("Login");
            loginButton.setOnAction(event -> LoginGUI.get_LoginScene(event));
            buttonBox.getChildren().add(loginButton);
        }
        else {
            Button logoutButton = new Button("Logout");
            logoutButton.setOnAction(event -> get_LogoutScene(event));
            buttonBox.getChildren().add(logoutButton);
            
                Button viewOrdersButton = new Button("View Orders");
                viewOrdersButton.setOnAction(event -> OrdersGUI.get_ordersScene(
                                                Main.SessionAdmin != null));
                buttonBox.getChildren().add(viewOrdersButton);  
            //Create view profile and view orders buttons if user is reader
            //Else create add book and view all orders if user is admin
            if (Main.SessionAdmin == null) {
                Button viewProfileButton = new Button("View Profile");
                viewProfileButton.setOnAction(event -> ReaderProfileGUI.get_ReaderScene(event));
                buttonBox.getChildren().add(viewProfileButton);    
            }
            else {
                Button addButton = new Button("Add Book");
                addButton.setOnAction(e -> AddBookGUI.get_AddScene());
                buttonBox.getChildren().addAll(addButton);
            }
        }
        
        //Add components to layout
        layout.getChildren().addAll(buttonBox, showBooks());
        //Add layput to scroll pane
        ScrollPane scroll = new ScrollPane(layout);
        return new Scene(scroll);    
    }
    //Return flow pane with all the books
    private static FlowPane showBooks() {
        //Create flow pane
        FlowPane flowPane = new FlowPane(110, 50);
        flowPane.setPrefWrapLength(Main.PStage.getWidth() - 125);
        flowPane.setPadding(new Insets(0, 0, 0, 40));
        //Z used to compare with books amount
        int z = (Main.SessionAdmin == null)? 0: -1;
        for (BookModel book : Main.Books) {
            if (Main.Inventories.get(book.getBookIndex()).getAmount() > z) {
                printBook(book, flowPane);
            }
        }
        return flowPane;
    }
    //Add books the flow pane, auxiliary function for showBooks
    public static void printBook(BookModel book, FlowPane flowPane) {
        //Label for book details
        Label label = new Label("Name: " + book.getName() + "\nAuthor: " + book.getAuthor()
                + "\nCategory: " + book.getCategory() + "\n" + "Publisher: " 
                + book.getPublisherName() + "\nPrice: $" + String.valueOf(book.getPrice()));

        label.setFont(Font.font(16));
        
        //Book Image
        Image image = new Image("resources/" + book.getImage() + ".jpg");
        ImageView view = new ImageView(image);
        view.setFitHeight(200);
        view.setFitWidth(150);
        //Horizontal box for buttons
        HBox buttonBox = new HBox(15);
        
        //Create buy book button if user is reader
        //Else create edit book and delete book buttons
        if (Main.SessionUser != null) {
            Button buyButton = new Button("Buy Book");
            buyButton.setOnAction(e -> BookDetailsGUI.get_BookScene(book));
            buttonBox.getChildren().add(buyButton);
        }
        else if (Main.SessionAdmin != null) {
            Button editButton = new Button("Edit Book");
            editButton.setOnAction(e -> EditBookGUI.get_EditScene(book));
            
            Button delete = new Button("Delete Book");
            delete.setOnAction(e -> DeleteBookGUI.get_DeleteScene(book));
            buttonBox.getChildren().addAll(editButton, delete);
        }
        //Create verical box for the book components
        VBox bookBox = new VBox(10);
        bookBox.setAlignment(Pos.TOP_LEFT); // Center the content
        bookBox.setPrefWidth(220); // Set width for consistency
        
        bookBox.getChildren().addAll(view, buttonBox, label);
        flowPane.getChildren().add(bookBox);
    }
    //Logout user and show warning
    public static void get_LogoutScene(ActionEvent event){
        Main.SessionUser = null;
        Main.SessionAdmin = null;
        HelperGUI.WarningStage("Logged out Successfully!");
        get_HomeScene();
    }
}