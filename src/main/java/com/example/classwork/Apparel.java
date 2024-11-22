/**
* @Name: Jack Cole, Chloe Smith, Luke Butler, Reese Larkin, Carter Soderena
* @Section: CSC 331
* @Date: 11/24/2024
* @ProgramPurpose: Gives properties to an object such as its name, size, category, brand, and price and allows the user to set the quantity. 
*/

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
        // Displays the price with the proper formating
        String formatPrice = Double.toString(price);
        formatPrice = String.format("%.2f", price);
        price = Double.parseDouble(formatPrice);
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    @Override
    public String toString() {
        // Gives the apparel proper formating if printed out. The format allows creates a table and the headers are in main
        return String.format("%20s%20s%20s%20s%20s%20s", getProduct(), getQuantity(), getSize(), getCategory(), getBrand(), getPrice());
    }
}
