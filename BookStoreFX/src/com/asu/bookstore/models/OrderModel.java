package com.asu.bookstore.models;

import com.asu.bookstore.main.Main;
import java.time.LocalDate;

public class OrderModel {

    private int orderID;
    private String readerName;
    private String bookName;
    private double cost;
    private LocalDate date;

    //Constructors//
    public OrderModel(String name, double cost, LocalDate date, String book) {
        orderID = Main.Orders.size();
        readerName = name;
        this.cost = cost;
        this.date = date;
        this.bookName = book;
    }

    public void addOrder(BookModel book) {
        int size = Main.Inventories.get(book.getBookIndex()).getAmount();
        Main.Inventories.get(book.getBookIndex()).setAmount(size - 1);
        Main.Orders.add(this);
    }
    
    public void cancelOrder() {
        Main.Orders.remove(this);
    }
    
    //Getters-Setters//
    public int getOrderID() {
        return orderID;
    }

    public String getReaderName() {
        return readerName;
    }

    public String getBookName() {
        return bookName;
    }

    public double getCost() {
        return cost;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Order ID: " + orderID + "\nUsername: " + readerName + "\nBook: "
                + bookName+ "\nCost: $" + cost + "\nDate: " + date;
    }
}