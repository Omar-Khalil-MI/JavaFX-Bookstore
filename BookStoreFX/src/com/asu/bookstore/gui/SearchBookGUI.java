package com.asu.bookstore.gui;

import com.asu.bookstore.models.BookModel;
import com.asu.bookstore.main.Main;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SearchBookGUI {
    //Prepare search scene
    private static Scene searchScene(String s) {
        //Create layout
        VBox layout = new VBox(20);
        layout.setPadding(new Insets(30, 20, 20, 30));
        //Create horizontal box for the buttons
        HBox buttonBox = new HBox(15);
        buttonBox.setAlignment(Pos.TOP_CENTER);
        buttonBox.setPadding(new Insets(0, 0, 40, 0));
        
        //Create search field
        TextField searchField = new TextField(s);
        searchField.setPrefWidth(750);
        //Create search button
        Button searchButton = new Button("Search");
        searchButton.setOnAction(e -> get_SearchScene(searchField.getText()));
        //Create home button
        Button homeButton = new Button("Return");
        homeButton.setOnAction(e -> HomeGUI.get_HomeScene());
        
        //Add components
        buttonBox.getChildren().addAll(searchField, searchButton, homeButton);
        layout.getChildren().add(buttonBox);
        layout.getChildren().add(showSearchedBooks(s));
        //Add layout to scroll pane
        ScrollPane scroll = new ScrollPane(layout);
        return new Scene(scroll);
    }
    //Stage search scene
    public static void get_SearchScene(String s){
        Stage searchStage = Main.PStage;
        searchStage.setScene(searchScene(s));
        searchStage.setTitle("Bookstore - Search Page");
    }
    //Return flow pane with all the searched books
    private static FlowPane showSearchedBooks(String s) {
        int z = (Main.SessionAdmin == null)? 0: -1;
        FlowPane flowPane = new FlowPane(110, 50);
        flowPane.setPrefWrapLength(Main.PStage.getWidth() - 125);
        flowPane.setPadding(new Insets(0, 0, 0, 40));
       
        s = s.toLowerCase();
        for (BookModel book : Main.Books) {
            if (Main.Inventories.get(book.getBookIndex()).getAmount() > z
                    && ((book.getName().toLowerCase()).contains(s)))
                HomeGUI.printBook(book, flowPane);
        }
        
        return flowPane;    
    }
    
}