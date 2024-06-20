package com.asu.bookstore.main;

import com.asu.bookstore.gui.HomeGUI;
import com.asu.bookstore.models.BookModel;
import com.asu.bookstore.models.InventoryModel;
import com.asu.bookstore.models.OrderModel;
import com.asu.bookstore.models.ReaderModel;
import com.asu.bookstore.models.UserModel;
import com.asu.bookstore.services.DatabaseReader;
import com.asu.bookstore.services.DatabaseWriter;
import javafx.application.Application;
import java.util.ArrayList;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application{

    //Session//
    public static ReaderModel SessionUser = null;
    public static UserModel SessionAdmin = null;
    
    //Databases//
    public static ArrayList<BookModel> Books = new ArrayList<>();
    public static ArrayList<InventoryModel> Inventories = new ArrayList<>();
    public static ArrayList<UserModel> Users = new ArrayList<>();
    public static ArrayList<OrderModel> Orders = new ArrayList<>();
    
    public static Stage PStage; //Primary Stage
    
    public static void main(String[] args){

        //Load Databases//
        DatabaseReader.loadUsers();
        DatabaseReader.loadBooks();
        DatabaseReader.loadInvent();
        DatabaseReader.loadOrders();
        launch(args);
    }   

    @Override
    public void start(Stage primaryStage) {
        Platform.setImplicitExit(false);
        
        PStage = primaryStage;
        PStage.getIcons().add(new Image("resources/logo.png", 800, 800,
                true, true));   //Get Icon
        PStage.setMaximized(true);  //Maximize Window
        
        //Workaround for the changing window size//
        PStage.show();
        PStage.hide();
        PStage.setWidth(PStage.getWidth());
        PStage.setHeight(PStage.getHeight());
        
        //Save Databases when exiting//
        PStage.setOnCloseRequest(e -> {
            DatabaseWriter.writeBooks();
            DatabaseWriter.writeInvent();
            DatabaseWriter.writeOrders();
            DatabaseWriter.writeUsers();
            Platform.exit();
        });
        
        HomeGUI.get_HomeScene();    //Call Home Scene
        PStage.show();              //Show Window
   }
}