package com.java_revision;

public class Product {
    String productName;
    String category;
    double cost;

    public Product(String productName, String cateogry, double cost) {
        this.productName = productName;
        this.category = cateogry;
        this.cost = cost;
    }

    public String getProductName() {
        return productName;
    }

    public String getCategory() {
        return category;
    }

    public double getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return "Product [productName=" + productName + ", cateogry=" + category + ", cost=" + cost + "]";
    }

}
