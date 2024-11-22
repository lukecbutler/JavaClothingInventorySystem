/**
* @Name: Jack Cole, Chloe Smith, Luke Butler, Reese Larkin, Carter Soderena
* @Section: CSC 331
* @Date: 11/24/2024
* @ProgramPurpose: Creates an interactive GUI to run the program from
*/

package com.example.classwork;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.IOException;
import java.util.ArrayList;

public class guiController {

    // Create list of apparel items and inventory object
    ArrayList<Apparel> apparelItems = new ArrayList<>();
    Inventory inventory = new Inventory(apparelItems);

    @FXML
    private TextField productField, quantityField, sizeField, categoryField, brandField, priceField, storeInventoryWorth;

    @FXML
    private TableView<Apparel> tableView;

    @FXML
    private TableColumn<Apparel, String> productColumn, sizeColumn, categoryColumn, brandColumn;

    @FXML
    private TableColumn<Apparel, Integer> quantityColumn;

    @FXML
    private TableColumn<Apparel, Double> priceColumn, totalPriceColumn;

    @FXML
    private TableColumn<Apparel, Void> decrementColumn, incrementColumn;

    @FXML
    public void initialize() {
        /**
        * @Purpose: Initializes part of the GUI
        */
        // Load table and initialize columns
        loadTable();
        initializeColumns();

        // Add increment and decrement buttons
        initializeQuantityChangeColumns();

        // Update the total inventory worth initially
        updateStoreInventoryWorth();
    }

    /**
     * @Purpose: Initializes the columns of the GUI
     */
    private void initializeColumns() {
        // Map Apparel attributes to table columns
        productColumn.setCellValueFactory(new PropertyValueFactory<>("product"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        sizeColumn.setCellValueFactory(new PropertyValueFactory<>("size"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        // Format price column with $ sign
        priceColumn.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(Double price, boolean empty) {
                super.updateItem(price, empty);
                if (empty || price == null) {
                    setText(null); // Clear cell if empty
                } else {
                    setText(String.format("$%.2f", price)); // Format and display price
                }
            }
        });

        // Calculate and display total price (price * quantity) in the total price column
        totalPriceColumn.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(Double item, boolean empty) {
                super.updateItem(item, empty);
                try {
                    if (empty || getTableRow() == null || getTableRow().getItem() == null) {
                        setText(null); // Clear the cell if it's empty or invalid
                        return;
                    }

                    // Safely retrieve the Apparel item
                    Apparel itemData = getTableRow().getItem();
                    double totalPrice = itemData.getPrice() * itemData.getQuantity();
                    setText(String.format("$%.2f", totalPrice)); // Format and display total price
                } catch (Exception e) {
                    setText(null); // Gracefully handle unexpected errors by clearing the cell
                }
            }
        });
    }




    /**
     * @Purpose: Allows the user to enter data of an apparel in the GUI and output it into the inventory
     */
    @FXML
    private void handleCreateProduct() {
        try {
            // Takes all of the user data in the text boxes and puts them into their respected variables
            String product = productField.getText();
            int quantity = Integer.parseInt(quantityField.getText());
            String size = sizeField.getText();
            String category = categoryField.getText();
            String brand = brandField.getText();
            double price = Double.parseDouble(priceField.getText());

            // If any of the boxes are empty then throw an error
            if (product.isEmpty() || size.isEmpty() || category.isEmpty() || brand.isEmpty()) {
                throw new IllegalArgumentException("All fields must be filled out.");
            }

            // Create new Apparel item
            Apparel newItem = new Apparel(product, quantity, size, category, brand, price);

            // Add item to inventory and table
            inventory.addItem(newItem);
            tableView.getItems().add(newItem);

            // Update total inventory worth
            updateStoreInventoryWorth();

            // Clear input fields
            clearText();
        } catch (NumberFormatException e) {
            // handles string inputs for quantity & price
            showAlert("Invalid Input", "Please enter valid numbers for quantity and price.");
        } catch (IllegalArgumentException e) {
            showAlert("Invalid Input", "All fields must be filled out.");
        }

    }

    /**
     * @Purpose: Updates any changes to the total value of an apparel
     */
    private void updateStoreInventoryWorth() {
        // Initialize total worth to 0
        double totalWorth = 0.0;

        // Iterate through the items in the TableView
        for (Apparel item : tableView.getItems()) {
            totalWorth += item.getPrice() * item.getQuantity(); // Add price * quantity to total
        }

        // Update the storeInventoryWorth TextField
        storeInventoryWorth.setText(String.format("$%.2f", totalWorth));
    }

    /**
     * @Purpose: Loads all of the apparel into a table in the GUI
     */
    private void loadTable() {
        try {
            // Reads in the inventory file and puts all of the apparels in the table
            apparelItems.clear();
            apparelItems.addAll(inventory.readCSV());
            ObservableList<Apparel> observableApparelItems = FXCollections.observableArrayList(apparelItems);
            tableView.setItems(observableApparelItems);
        } catch (IOException e) {
            // If the file failed to load then display an error
            showAlert("Error", "Failed to load inventory from CSV.");
        }
    }

    /**
     * @Purpose: A shortcut to reload the table in the GUI
     */
    private void refreshTable() {
        tableView.refresh();
        inventory.rewriteCSV(new ArrayList<>(tableView.getItems()));
    }

    /**
     * @Purpose: Clears all of the user created text in the GUI
     */
    private void clearText() {
        productField.clear();
        quantityField.clear();
        sizeField.clear();
        categoryField.clear();
        brandField.clear();
        priceField.clear();
    }

    /**
     * @Params: String, String
     * @Purpose: Shows an error message to the user if they input incorrect information
     */
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * @Purpose: Allows the quantity of an apparel to change in the GUI and deletes the apparel if the quantity is 0
     */
    @FXML
    private void initializeQuantityChangeColumns() {
        // Set up decrement column
        decrementColumn.setCellFactory(column -> new TableCell<>() {
            @Override
            public void updateItem(Void unused, boolean empty) {
                /**
                * @Purpose: Deletes the apparel if the quantity is 0
                */
                super.updateItem(unused, empty);

                try {
                    // Ensure the cell is valid and associated with a row
                    if (empty || getTableRow() == null || getTableRow().getItem() == null) {
                        setGraphic(null);
                        return;
                    }

                    // Create the decrement button
                    Button decrementButton = new Button("-");
                    decrementButton.setOnAction(event -> {
                        Apparel item = getTableRow().getItem();
                        if (item.getQuantity() > 1) {
                            item.setQuantity(item.getQuantity() - 1);
                        } else {
                            tableView.getItems().remove(item);
                        }
                        refreshTable();
                        updateStoreInventoryWorth();
                    });
                    setGraphic(decrementButton); // Set button as the cell's graphic
                } catch (Exception e) {
                    // Gracefully handle any unexpected errors
                    System.out.println("Error at decrement button");
                }
            }
        });

        // Set up increment column
        incrementColumn.setCellFactory(column -> new TableCell<>() {
            @Override
            public void updateItem(Void unused, boolean empty) {
                super.updateItem(unused, empty);

                try {
                    // Ensure the cell is valid and associated with a row
                    if (empty || getTableRow() == null || getTableRow().getItem() == null) {
                        setGraphic(null);
                        return;
                    }

                    // Create the increment button
                    Button incrementButton = new Button("+");
                    incrementButton.setOnAction(event -> {
                        Apparel item = getTableRow().getItem();
                        item.setQuantity(item.getQuantity() + 1);
                        refreshTable();
                        updateStoreInventoryWorth();
                    });
                    setGraphic(incrementButton); // Set button as the cell's graphic
                } catch (Exception e) {
                    // Gracefully handle any unexpected errors
                    System.out.println("Error at increment button");
                }
            }
        });
    }



}
