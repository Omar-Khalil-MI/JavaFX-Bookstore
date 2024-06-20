package com.asu.bookstore.gui;

import com.asu.bookstore.models.AddressModel;
import com.asu.bookstore.main.Main;
import com.asu.bookstore.models.ReaderModel;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ReaderProfileGUI {
    //Prepare the reader profile scene
    private static Scene readerScene(ReaderModel user) {
        //Create the layout
        GridPane layout = new GridPane(15, 10);
        layout.setAlignment(Pos.CENTER);
        
        //Create text fields
        TextField emailField = new TextField(user.getMail());
        emailField.end();   //Put cursor at the end of the text
        TextField usernameField = new TextField(user.getUserName());
        usernameField.setEditable(false);   //Can not edit the username
        TextField phoneNumberField = new TextField(user.getPhoneNumber());
        TextField cityField = new TextField(user.getAddress().getCity());
        TextField ZIPcodeField = new TextField(user.getAddress().getPostal_Code());
        
        //Create update button
        Button updateButton = new Button("Update");
        updateButton.setFont(Font.font(15));
        updateButton.setOnAction(e -> handleUpdate(emailField.getText().trim(),
                usernameField.getText(), Main.SessionUser.getPasswordHash(),
                phoneNumberField.getText().trim(),cityField.getText().trim(),
                ZIPcodeField.getText().trim()));
        
        //Create Cancel button
        Button cancelButton = new Button("Cancel");
        cancelButton.setFont(Font.font(15));
        cancelButton.setOnAction(e -> HomeGUI.get_HomeScene());
        
        //Create horizontal box fot the buttons
        HBox buttonBox = new HBox(10, updateButton, cancelButton);
        // Add components to the layout
        layout.add(HelperGUI.createLabel("Username:"), 0, 0);
        layout.add(usernameField, 1, 0);
        layout.add(HelperGUI.createLabel("Email:"), 0, 2);
        layout.add(emailField, 1, 2);
        layout.add(HelperGUI.createLabel("Phone Number:"), 0, 3);
        layout.add(phoneNumberField, 1, 3);
        layout.add(HelperGUI.createLabel("City:"), 0, 4);
        layout.add(cityField, 1, 4);
        layout.add(HelperGUI.createLabel("ZIP code:"), 0, 5);
        layout.add(ZIPcodeField, 1, 5);
        layout.add(buttonBox, 1, 7);
        
        return new Scene(layout, 400, 370);
    }
    //Update profile and show warnung
    private static void handleUpdate(String email, String username, int password,
            String phoneNumber,String city, String ZIPcode) {
        if (email.isEmpty()|| username.isEmpty()|| phoneNumber.isEmpty()
                || city.isEmpty()|| ZIPcode.isEmpty())
            HelperGUI.get_ResultScene(false, "Please Fill All Fields!");
        else if (phoneNumber.length() != 11)
            HelperGUI.get_ResultScene(false, "Phone Number Must Be 11 Digits!");
        else {
            AddressModel address = new AddressModel(city, ZIPcode);
            ReaderModel reader = new ReaderModel(false, username, email, password, address, phoneNumber);
            reader.updateReader();
            HomeGUI.get_HomeScene();
            HelperGUI.WarningStage("Data Updated Successfully!");
        }
    }
    //Stage the reader profile scene
    public static void get_ReaderScene(ActionEvent event){
        Stage loginStage = Main.PStage;
        loginStage.setScene(readerScene(Main.SessionUser));
        loginStage.setTitle("Bookstore - Profile Page");
    }
}