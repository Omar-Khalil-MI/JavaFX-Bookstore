package com.asu.bookstore.models;

public class InventoryModel {

    static final int MAX_BOOKS = 100;
    private String bookName;
    private int amount;

    //Constructors//
    public InventoryModel(String name, int amount) {
        bookName = name;
        this.amount = amount;
    }

    //Getters-Setters//
    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getBookName() {
        return bookName;
    }
    
    @Override
    public String toString() {
        return "Inventory{" + "bookName=" + bookName + ", amount=" + amount + '}';
    }
}