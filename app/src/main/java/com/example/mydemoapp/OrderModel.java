package com.example.mydemoapp;

public class OrderModel {
    int orderId;
    int totalPrice;
    String eventName;
    String ticketCategoryName;
    int numberOfTickets;
    String orderDate;

    public OrderModel(int orderId, int totalPrice, String eventName, String ticketCategoryName, int numberOfTickets, String orderDate) {
        this.orderId = orderId;
        this.totalPrice = totalPrice;
        this.eventName = eventName;
        this.ticketCategoryName = ticketCategoryName;
        this.numberOfTickets = numberOfTickets;
        this.orderDate = orderDate;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public String getEventName() {
        return eventName;
    }

    public String getTicketCategoryName() {
        return ticketCategoryName;
    }

    public int getNumberOfTickets() {
        return numberOfTickets;
    }

    public String getOrderDate() {
        return orderDate;
    }
}
