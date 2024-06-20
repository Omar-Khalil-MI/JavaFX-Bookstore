package com.asu.bookstore.services;

import com.asu.bookstore.models.AddressModel;
import com.asu.bookstore.models.AdminModel;
import com.asu.bookstore.models.BookModel;
import com.asu.bookstore.models.InventoryModel;
import com.asu.bookstore.main.Main;
import com.asu.bookstore.models.OrderModel;
import com.asu.bookstore.models.ReaderModel;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;

public class DatabaseReader {
    
    public static void loadBooks(){
        File books_file = new File("Books.txt");
        
        try {
            Scanner input = new Scanner(books_file);
            while (input.hasNext()) {
            String line = input.nextLine();
            String[] tokens = line.split(" , ");
            BookModel book = new BookModel(tokens[0], tokens[1], tokens[2],
                    Double.parseDouble(tokens[3]), tokens[4], tokens[5], tokens[6]);
            Main.Books.add(book);
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
    }
    
    public static void loadUsers(){
        File users_file = new File("Users.txt");
        
        try {
            Scanner input = new Scanner(users_file);
            while (input.hasNext()) {                
            String line = input.nextLine();
            String[] tokens = line.split(" , ");
            if (tokens[0].equals("false"))
                Main.Users.add(new ReaderModel(false, tokens[1], tokens[2], Integer.parseInt(tokens[3]),
                        new AddressModel(tokens[4], tokens[5]), tokens[6]));
            
            else
                Main.Users.add(new AdminModel(true, tokens[1], tokens[2], Integer.parseInt(tokens[3]),
                        new AddressModel(tokens[4], tokens[5])));
            }
        } 
        catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
    }
    
    public static void loadInvent(){
        File inventory_file = new File("Inventory.txt");
        
        try {
            Scanner input = new Scanner(inventory_file);
            while(input.hasNext()) {
            String line = input.nextLine();
            String[] tokens = line.split(" , ");
            Main.Inventories.add(new InventoryModel(tokens[0], Integer.parseInt(tokens[1])));
            }
        } 
        catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
    }

    public static void loadOrders(){
        File orders_file = new File("Orders.txt");
        
        try {
            Scanner input = new Scanner(orders_file);
            while(input.hasNext()) {
            String line = input.nextLine();
            String[] tokens = line.split(" , ");

            Main.Orders.add(new OrderModel(tokens[0], Double.parseDouble(tokens[1]),
                    LocalDate.parse(tokens[2]), tokens[3]));
            }
        } 
        catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
    }
}
