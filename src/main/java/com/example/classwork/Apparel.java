package com.example.classwork;


public class Apparel {
    String product;
    int quantity;
    String size;
    String category;
    String brand;
    double price;

    Apparel(String product, int quantity, String size, String category, String brand, double price){
        this.product = product;
        this.quantity = quantity;
        this.size = size;
        this.category = category;
        this.brand = brand;
        this.price = price;
    }

    public String getProduct() {
        return product;
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

    public void setProduct(String product) {
        this.product = product;
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
        return String.format("%20s%20s%20s%20s%20s%20s", getProduct(), getQuantity(), getSize(), getCategory(), getBrand(), getPrice());
    }
}
