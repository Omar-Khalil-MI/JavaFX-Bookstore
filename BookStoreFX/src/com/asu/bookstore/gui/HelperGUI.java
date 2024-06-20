package com.asu.bookstore.gui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class HelperGUI {
    //Create warning stage for other classes
    public static void WarningStage(String message) {
        Stage warningStage = new Stage();
        Label label = new Label(message);
        label.setFont(Font.font(15));
        
        Button okButton = new Button("Ok");
        okButton.setFont(Font.font(13));
        okButton.setOnAction(e -> {
            warningStage.close();
        });
        
        VBox layout = new VBox(15, label, okButton);
        layout.setAlignment(Pos.CENTER);
        
        warningStage.setScene(new Scene(layout, 300, 100));
        warningStage.setTitle("Alert");
        warningStage.getIcons().add(new Image("resources/w.png", 500, 500, true, true));
        warningStage.centerOnScreen();
        warningStage.show();
    }
    //Create labels for other classes
    public static Label createLabel(String labelName) {
        Label label = new Label(labelName);
        label.setFont(Font.font(20));
        return label;
    }
    //Show warning message and procceed to home page if signup successful
    public static void get_ResultScene(boolean valid, String message) {
        if (valid)
            HomeGUI.get_HomeScene();
        HelperGUI.WarningStage(message);
    }
}
