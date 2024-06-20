package com.asu.bookstore.models;

import com.asu.bookstore.main.Main;

public class BookModel {

    private String name;
    private String author;
    private String publisherName;
    private double price;
    private String category;
    private String image = "no-image";
    private String isbn;
    private int bookIndex;
    
    //Constructors//
    public BookModel(String name, String author, String publisher, double price,
            String category, String isbn) {
        this.name = name;
        this.author = author;
        publisherName = publisher;
        this.price = price;
        this.category = category;
        this.isbn = isbn;
        bookIndex = Main.Books.size();
    }

    public BookModel(String name, String author, String publisher, double price,
            String category, String isbn, String image) {
        this(name, author, publisher, price, category, isbn);
        this.image = image;
    }
    
    //Functions//
    public void editBook(BookModel book, InventoryModel inv) {
        Main.Inventories.get(book.getBookIndex()).setAmount(inv.getAmount());
        Main.Books.get(book.getBookIndex()).setName(name);
        Main.Books.get(book.getBookIndex()).setAuthor(author);
        Main.Books.get(book.getBookIndex()).setPublisherName(publisherName);
        Main.Books.get(book.getBookIndex()).setPrice((price));
        Main.Books.get(book.getBookIndex()).setCategory(category);
        Main.Books.get(book.getBookIndex()).setIsbn(isbn);
    }
    
    public void deleteBook() {
        for (int i = bookIndex + 1; i < Main.Books.size(); i++) {
            BookModel book_temp = Main.Books.get(i);
            book_temp.setBookIndex(i - 1);
            Main.Books.set(i - 1, book_temp);
        }
        Main.Books.removeLast();
        Main.Inventories.remove(bookIndex);
    }
    
    public void addBook(InventoryModel inventory) {
        Main.Books.add(this);
        Main.Inventories.add(inventory);
    }
    //Getters-Setters//
    public String getImage() {
        return image;
    }
    
    public int getBookIndex() {
        return bookIndex;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setBookIndex(int bookIndex) {
        this.bookIndex = bookIndex;
    }
    
    @Override
    public String toString() {
        return "BookModel{" + "name=" + name + ", author=" + author
                + ", publisherName=" + publisherName + ", price=" + price
                + ", category=" + category + ", image=" + image + ", isbn="
                + isbn + ", bookIndex=" + bookIndex + '}';
    }
}