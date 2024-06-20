package com.asu.bookstore.models;

import com.asu.bookstore.main.Main;

public class AdminModel extends UserModel {

    //Constructors//
    public AdminModel(boolean isAdmin, String userName, String mail,
            int password, AddressModel address) {
        super(true, userName, mail, password, address);
    }
    
    public void removeBook(BookModel book) {
        Main.Inventories.remove(book.getBookIndex());
        Main.Books.remove(book);
        System.out.println("Book Removed.");
    }

    public void addBook(BookModel book, int amount) {
        if (amount < InventoryModel.MAX_BOOKS) {
            Main.Books.add(book);
            Main.Inventories.add(new InventoryModel(book.getName(), amount));
            System.out.println("Book Added");
        }
        else System.out.println("Inventory is full.");
    }

    public void updateBook(BookModel book, int amount) {
        if (amount < InventoryModel.MAX_BOOKS) {
            Main.Inventories.get(book.getBookIndex()).setAmount(amount);
            System.out.println("Inventory updated for " + book.getName()
                    + ". New amount: "
                    + Main.Inventories.get(book.getBookIndex()).getAmount());
        }
        else System.out.println("Amount exceeds limit.");
    }
}
