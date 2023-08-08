package com.example.mydemoapp;

public class EventModel {
    String eventName;
    String eventDescription;
    String location;
    String date;
    int image;
    float price;

    public float getPrice() {
        return price;
    }

    public EventModel(String eventName, String eventDescription, String location, String date, int image, float price) {
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.location = location;
        this.date = date;
        this.image = image;
        this.price = price;
    }

    public String getEventName() {
        return eventName;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public String getLocation() {
        return location;
    }

    public String getDate() {
        return date;
    }

    public int getImage() {
        return image;
    }
}
