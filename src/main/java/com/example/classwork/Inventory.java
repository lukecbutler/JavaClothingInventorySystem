package com.example.classwork;
import java.util.ArrayList;
import java.io.*;


public class Inventory {

    ArrayList<Apparel> apparelItems;

    Inventory(ArrayList<Apparel> apparelItems) {
        this.apparelItems = apparelItems;
    }

    public void addItem(Apparel item) {
        apparelItems.add(item);
        rewriteCSV(apparelItems);
    }

    public void deleteItem(int index) {
        apparelItems.remove(index - 1);
        rewriteCSV(apparelItems);
    }

    public String displayApparelItems() throws IOException {
        ArrayList<Apparel> apparelItems = readCSV();
        String items = "";
        int index = 1; // Start index at 1

        for (Apparel item : apparelItems) {
            items = items + item.toString() + "[" + index + "]\n";
            index++;
        }

        return items;
    }

    public void rewriteCSV(ArrayList<Apparel> apparelItems) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("currentInventory.csv"))) {

            for (Apparel item : apparelItems) {
                writer.write(item.getProductName() + ',' + item.getQuantity() + ',' + item.getSize() + ',' + item.getCategory()+ ',' + item.getBrand() + ',' + item.getPrice());
                writer.newLine();
            }
        } catch (Exception e) {
            System.out.println("try again");
        }
    }

    public ArrayList<Apparel> readCSV() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("currentInventory.csv"));
        ArrayList<Apparel> apparelItems = new ArrayList<>();
        String line;

        // Read each line of the file
        while ((line = reader.readLine()) != null) {
            line = line.strip();
            String[] attributes = line.split(",");

            // walk thru attributes
            String productName = attributes[0];
            int quantity = Integer.parseInt(attributes[1]);
            String size = attributes[2];
            String category = attributes[3];
            String brand = attributes[4];
            double price = Double.parseDouble(attributes[5]);

            // Create an Apparel object
            Apparel item = new Apparel(productName, quantity, size, category, brand, price);
            apparelItems.add(item);
        }

        reader.close(); // Close the reader to release the file handle
        return apparelItems;
    }
}
