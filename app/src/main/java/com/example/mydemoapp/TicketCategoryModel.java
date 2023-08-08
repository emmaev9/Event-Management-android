package com.example.mydemoapp;

public class TicketCategoryModel {
    String name;
    float price;

    public TicketCategoryModel(String name, float price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }
}
