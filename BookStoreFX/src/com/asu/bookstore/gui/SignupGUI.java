package com.asu.bookstore.gui;

import com.asu.bookstore.models.AddressModel;
import com.asu.bookstore.main.Main;
import com.asu.bookstore.models.ReaderModel;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class SignupGUI {
    //Stage signup scene
    public static void get_SignupScene() {
        Stage signupStage = Main.PStage;
        signupStage.setScene( signupScene() );
        signupStage.setTitle("Bookstore - Signup Page");
    }
    //Prepare the signup scene
    private static Scene signupScene() {
        //Create the layout
        GridPane layout = new GridPane(0, 20);
        layout.setAlignment(Pos.CENTER);
        
        //Create text fields
        TextField emailField = new TextField();
        TextField usernameField = new TextField();
        PasswordField passwordField = new PasswordField();
        PasswordField confirmPasswordField = new PasswordField();
        TextField phoneNumberField = new TextField();
        TextField cityField = new TextField();
        TextField ZIPcodeField = new TextField();
        
        //Create signup button
        Button signupButton = new Button("Sign up");
        signupButton.setFont(Font.font(15));
        signupButton.setOnAction(e -> handleSignup(emailField.getText().trim(),
                usernameField.getText().trim(), passwordField.getText(),
                confirmPasswordField.getText(), phoneNumberField.getText().trim(),
                cityField.getText().trim(), ZIPcodeField.getText().trim()));
        
        //Create cancel button
        Button cancelButton = new Button("Cancel");
        cancelButton.setFont(Font.font(15));
        cancelButton.setOnAction(e -> HomeGUI.get_HomeScene());
        
        //Create horizontal box for the buttons
        HBox buttonBox = new HBox(10, signupButton, cancelButton);
        // Add components to the layout
        layout.add(HelperGUI.createLabel("Email: "), 0, 0);
        layout.add(emailField, 1, 0);
        layout.add(HelperGUI.createLabel("Username: "), 0, 1);
        layout.add(usernameField, 1, 1);
        layout.add(HelperGUI.createLabel("Password: "), 0, 2);
        layout.add(passwordField, 1, 2);
        layout.add(HelperGUI.createLabel("Confirm Password: "), 0, 3);
        layout.add(confirmPasswordField, 1, 3);
        layout.add(HelperGUI.createLabel("Phone: "), 0, 4);
        layout.add(phoneNumberField, 1, 4);
        layout.add(HelperGUI.createLabel("City: "), 0, 5);
        layout.add(cityField, 1, 5);
        layout.add(HelperGUI.createLabel("ZIP: "), 0, 6);
        layout.add(ZIPcodeField, 1, 6);
        layout.add(buttonBox, 1, 8);

        return  new Scene(layout);   
    }
    //Signup the user
    private static void handleSignup(String email, String username, String password,
            String confirmPassword, String phoneNumber,String city, String ZIPcode) {
        if (email.isEmpty() || username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()
                || phoneNumber.isEmpty()|| city.isEmpty()|| ZIPcode.isEmpty()) {
            HelperGUI.get_ResultScene(false, "Please Fill All Fields!");
        }
        else {
            AddressModel address = new AddressModel(city, ZIPcode);
            ReaderModel reader = new ReaderModel(false, username, email,
                    password.hashCode(), address, phoneNumber);
            reader.signupReader(password, confirmPassword);
        }
    }
}