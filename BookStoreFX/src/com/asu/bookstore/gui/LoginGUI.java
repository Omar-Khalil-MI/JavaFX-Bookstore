package com.asu.bookstore.gui;

import com.asu.bookstore.main.Main;
import com.asu.bookstore.models.UserModel;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class LoginGUI {
    //Prepare login scene
    private static Scene loginScene() {
        //Create layout
        GridPane layout = new GridPane(0, 25);
        layout.setAlignment(Pos.CENTER);
        
        //Create text fields
        TextField usernameField = new TextField();
        PasswordField passwordField = new PasswordField();
        //Create login button
        Button loginButton = new Button("Login");
        loginButton.setFont(Font.font(15));        
        loginButton.setOnAction( e -> UserModel.handleLogin( usernameField.getText().trim(),
                passwordField.getText().hashCode() ) );
        
        //Create first time label
        Label firstTimeLabel = new Label("First time?");
        firstTimeLabel.setFont(Font.font(20));
        //Create sign up link
        Hyperlink signupLink = new Hyperlink("Signup");
        signupLink.setFont(Font.font(20));
        signupLink.setOnAction(e -> SignupGUI.get_SignupScene());
        //Create cancel button
        Button cancelButton = new Button("Cancel");
        cancelButton.setFont(Font.font(15));
        cancelButton.setOnAction(e -> HomeGUI.get_HomeScene());
        
        //Create horizontal box for the buttons
        HBox buttonBox = new HBox(20);
        buttonBox.getChildren().addAll(loginButton,cancelButton);
        // Add components to the layout
        layout.add(HelperGUI.createLabel("Username: "), 0, 0);
        layout.add(usernameField, 1, 0);
        layout.add(HelperGUI.createLabel("Password: "), 0, 1);
        layout.add(passwordField, 1, 1);
        layout.add(buttonBox, 1, 2);
        layout.add(firstTimeLabel, 0, 3);
        layout.add(signupLink, 1, 3);

        return new Scene(layout);
    }
    //Stage login scene
    public static void get_LoginScene(ActionEvent event) {
        Stage loginStage = Main.PStage;
        loginStage.setScene(loginScene());
        loginStage.setTitle("Bookstore - Login Page");
    }
}