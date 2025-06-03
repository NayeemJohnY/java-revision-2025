package com.java_revision;

import java.time.LocalDate;

public class OrderService {

    private static int id = 1;
    int orderID;
    int customerID;
    String productName;
    int quantity;
    double pricePerItem;
    LocalDate orderDate;

    public OrderService(int customerID, String productName, int quantity, double pricePerItem) {
        this.orderID = id++;
        this.customerID = customerID;
        this.productName = productName;
        this.quantity = quantity;
        this.pricePerItem = pricePerItem;
    }

    public OrderService(int customerID, String productName, int quantity, double pricePerItem, LocalDate orderDate) {
        this.orderID = id++;
        this.customerID = customerID;
        this.productName = productName;
        this.quantity = quantity;
        this.pricePerItem = pricePerItem;
        this.orderDate = orderDate;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    @Override
    public String toString() {
        return "OrderService [orderID=" + orderID + ", customerID=" + customerID + ", productName=" + productName
                + ", quantity=" + quantity + ", pricePerItem=" + pricePerItem + ", orderDate=" + orderDate + "]";
    }

    public int getOrderID() {
        return orderID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPricePerItem() {
        return pricePerItem;
    }

}
