package com.example.classwork;


public class Apparel {
    String productName;
    int quantity;
    String size;
    String category;

    Apparel(String productName, int quantity, String size, String category){
        this.productName = productName;
        this.quantity = quantity;
        this.size = size;
        this.category = category;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getSize() {
        return size;
    }

    public String getCategory() {
        return category;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        // fix with correct %s and string formatting
        return "Product Name: " + getProductName() + "\tNumber of Items: " + getQuantity() + "\tSize: " + getSize() + "\tCategory: " + getCategory();
    }
}
