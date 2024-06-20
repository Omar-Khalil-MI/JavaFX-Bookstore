package com.asu.bookstore.services;

import com.asu.bookstore.models.BookModel;
import com.asu.bookstore.models.InventoryModel;
import com.asu.bookstore.main.Main;
import com.asu.bookstore.models.OrderModel;
import com.asu.bookstore.models.ReaderModel;
import com.asu.bookstore.models.UserModel;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;

public class DatabaseWriter {
    
    public static void writeBooks(){
        File books_file = new File("Books.txt");
        
        try {
            PrintWriter output = new PrintWriter(books_file);
            for (int i = 0; i < Main.Books.size(); i++) {
                BookModel book = Main.Books.get(i);
                output.print(book.getName() + " , ");
                output.print(book.getAuthor() + " , ");
                output.print(book.getPublisherName() + " , ");
                output.print(book.getPrice() + " , ");
                output.print(book.getCategory() + " , ");
                output.print(book.getIsbn() + " , ");
                output.println(book.getImage());
            }
            output.close();
        } 
        catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
    }
    
    public static void writeUsers(){
        File users_file = new File("Users.txt");
        
        try {
            PrintWriter output = new PrintWriter(users_file);
            for (int i = 0; i < Main.Users.size(); i++) {
                UserModel user = Main.Users.get(i);
                output.print(user.getIsAdmin() + " , ");
                output.print(user.getUserName() + " , ");
                output.print(user.getMail() + " , ");
                output.print(user.getPasswordHash() + " , ");
                output.print(user.getAddress().getCity() + " , ");
                output.print(user.getAddress().getPostal_Code());
            if (user.getIsAdmin() == false)
                output.print(" , " + ((ReaderModel)user).getPhoneNumber());
            output.println();
            }
            output.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
    }
    
    public static void writeInvent(){
        File inventory_file = new File("Inventory.txt");
        
        try {
            PrintWriter output = new PrintWriter(inventory_file);
            for (int i = 0; i < Main.Inventories.size(); i++) {
                InventoryModel inv = Main.Inventories.get(i);
                output.print(inv.getBookName() + " , ");
                output.println(inv.getAmount());
            }
            output.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
    }
    
    public static void writeOrders(){
        File orders_file = new File("Orders.txt");
        
        try {
            PrintWriter output = new PrintWriter(orders_file);
            for (int i = 0; i < Main.Orders.size(); i++) {
                OrderModel ord = Main.Orders.get(i);
                output.print(ord.getReaderName() + " , ");
                output.print(ord.getCost() + " , ");
                output.print(ord.getDate() + " , ");
                output.println(ord.getBookName());
            } 
            output.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
    }
}