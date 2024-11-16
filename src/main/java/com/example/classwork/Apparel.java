package com.example.classwork;


public class Apparel {
    String productName;
    int quantity;
    String size;
    String category;
    String brand;
    double price;

    Apparel(String productName, int quantity, String size, String category, String brand, double price){
        this.productName = productName;
        this.quantity = quantity;
        this.size = size;
        this.category = category;
        this.brand = brand;
        this.price = price;
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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    @Override
    public String toString() {
        // fix with correct %s and string formatting
        return "Product Name: " + getProductName() + "\tNumber of Items: " + getQuantity() + "\tSize: " + getSize() + "\tCategory: " + getCategory()+ "\tBrand: " + getBrand() + "\tPrice: " + getPrice();
    }
}
