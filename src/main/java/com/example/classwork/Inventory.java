/**
* @Name: Jack Cole, Chloe Smith, Luke Butler, Reese Larkin, Carter Soderena
* @Section: CSC 331
* @Date: 11/24/2024
* @ProgramPurpose: Holds a list of apparels and gives them properties such as deleting and adding apperals from the list and displaying the apperal in a string. A file is created to store the list
*/

package com.example.classwork;
import java.util.ArrayList;
import java.io.*;


public class Inventory {

    private final ArrayList<Apparel> apparelItems;

    Inventory(ArrayList<Apparel> apparelItems) {
        this.apparelItems = apparelItems;
    }

    public void addItem(Apparel item) {
        // Adds an apparel to the list by writing it to the file
        apparelItems.add(item);
        rewriteCSV(apparelItems);
    }

    public void deleteItem(int index) {
        // Removes an apparel from the list by rewriting the file
        apparelItems.remove(index - 1);
        rewriteCSV(apparelItems);
    }

    public String displayApparelItems(Apparel newItem) throws IOException {
        // Reads in the file and displays all of the apparels in the file
        ArrayList<Apparel> apparelItems = readCSV();
        String items = "";
        int index = 1; // Start index at 1

        // The for loop creates a table of each of the apparels properties
        for (Apparel item : apparelItems) {
            items = items + index + item.toString() + "\n";
            index++;
        }

        return items;
    }

    public void rewriteCSV(ArrayList<Apparel> apparelItems) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("currentInventory.csv"))) {

            // Reconstructs the inventory file so the list can be updated
            for (Apparel item : apparelItems) {
                writer.write(item.getProduct() + ','
                            + item.getQuantity() + ','
                            + item.getSize() + ','
                            + item.getCategory()+ ','
                            + item.getBrand() + ','
                            + item.getPrice());

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
            String product = attributes[0];
            int quantity = Integer.parseInt(attributes[1]);
            String size = attributes[2];
            String category = attributes[3];
            String brand = attributes[4];
            double price = Double.parseDouble(attributes[5]);

            // Create an Apparel object
            Apparel item = new Apparel(product, quantity, size, category, brand, price);
            apparelItems.add(item);
        }

        reader.close(); // Close the reader to release the file handle
        return apparelItems;
    }
}
