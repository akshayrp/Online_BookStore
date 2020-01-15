package com.thoughtworks.onlinebookstore.model;

public class Book {

   private int id;
   private String title;
   private int price;
   private int selectedQuantity;

    public Book() {
    }

    public Book(int id, String title, int price, int selectedQuantity) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.selectedQuantity = selectedQuantity;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getPrice() {
        return price;
    }

    public int getSelectedQuantity() {
        return selectedQuantity;
    }
}
