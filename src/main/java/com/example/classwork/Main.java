package com.example.classwork;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws IOException {

        // create list of apparel items to be passed into inventory
        ArrayList<Apparel> apparelItems = new ArrayList<>();
        // create inventory object - storing apparel items
        Inventory inventory = new Inventory(apparelItems);

        int checker = 0;

        while(checker == 0) {
            // Main Menu
            Scanner scanner = new Scanner(System.in);
            Boolean invalid;
            int quantity = 0;
            double price = 0;
            int choice = 0;

            // while loop keeps looping if invalid input is given
            invalid = Boolean.TRUE;
            while (invalid) {
                System.out.println("[1] Add Item to Inventory");
                System.out.println("[2] Display Inventory");
                System.out.println("[3] Delete Item");
                System.out.println("[0] Exit");
                try {
                    choice = scanner.nextInt();
                    if (choice >= 0 && choice <= 3){
                        invalid = Boolean.FALSE; // This line breaks out of the loop
                    }
                    else{System.out.println("Error - Number is not between 0 to 3");}
                }
                catch (InputMismatchException e) {System.out.println("Error - Non-Integer given");}
                scanner.nextLine();// Consume newline
            }

            switch (choice){
                case 1:
                    System.out.println("Enter name of product: ");
                    String productName = scanner.nextLine();

                    // while loop keeps looping if invalid input is given
                    invalid = Boolean.TRUE;
                    while (invalid) {
                        System.out.println("Enter the number of items: ");
                        try {
                            quantity = scanner.nextInt();
                            invalid = Boolean.FALSE; // This line breaks out of the loop
                        }
                        catch (InputMismatchException e) {System.out.println("Error - Non-Integer given");}
                        scanner.nextLine(); // Consume newline
                    }

                    System.out.println("Enter size of product - if no size enter N/A: ");
                    String size = scanner.nextLine();

                    System.out.println("Enter category of product (Top, Pants, Shoe, Accessory): ");
                    String category = scanner.nextLine();

                    System.out.println("Enter brand of product: ");
                    String brand = scanner.nextLine();

                    // while loop keeps looping if invalid input is given
                    invalid = Boolean.TRUE;
                    while (invalid) {
                        System.out.println("Enter price of product: ");
                        try {
                            price = scanner.nextDouble();
                            invalid = Boolean.FALSE; // This line breaks out of the loop
                        }
                        catch (InputMismatchException e) {System.out.println("Error - Non-Integer given");}
                        scanner.nextLine(); // Consume newline
                    }

                    Apparel item = new Apparel(productName, quantity, size, category, brand, price);
                    inventory.addItem(item);
                    break;

                case 2:
                    System.out.printf("%s%20s%20s%20s%20s%20s%20s\n", "#", "Product", "Amount", "Size", "Category", "Brand", "Price");
                    System.out.println(inventory.displayApparelItems());
                    break;
                case 3:

                    //System.out.println(inventory.displayApparelItems(newItem));
                    System.out.println("Enter the item number you wish to delete: ");
                    try{inventory.deleteItem(scanner.nextInt());}
                    catch(IndexOutOfBoundsException e){System.out.println(e);}
                    break;
                case 0:
                    checker++;
            }
        }
    }
}
