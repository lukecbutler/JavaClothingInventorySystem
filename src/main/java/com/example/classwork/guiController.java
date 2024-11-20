package com.example.classwork;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.util.ArrayList;


public class guiController {

    // create list of apparel items to be passed into inventory
    ArrayList<Apparel> apparelItems = new ArrayList<>();
    // create inventory object - storing apparel items
    Inventory inventory = new Inventory(apparelItems);


    @FXML
    private TextField productField;

    @FXML
    public TextField quantityField;

    @FXML
    private TextField sizeField;

    @FXML
    public TextField categoryField;

    @FXML
    public TextField brandField;

    @FXML
    public TextField priceField;

    @FXML
    public Button addItemButton;



    // create new table that takes in Apparel object
    @FXML
    private TableView<Apparel> tableView;

    // set columns to take in apparel object as a string - set them to respectice attributes
    @FXML
    private TableColumn<Apparel, String> productColumn;

    @FXML
    private TableColumn<Apparel, Integer> quantityColumn;

    @FXML
    private TableColumn<Apparel, String> sizeColumn;

    @FXML
    private TableColumn<Apparel, String> categoryColumn;

    @FXML
    private TableColumn<Apparel, String> brandColumn;

    @FXML
    private TableColumn<Apparel, Double> priceColumn;

    @FXML
    private TableColumn<Apparel, Void> decrementColumn;

    @FXML
    private TableColumn<Apparel, Void> incrementColumn;

    @FXML
    private TableColumn<Apparel, Double> totalPriceColumn;






    @FXML
    public void initialize(){
        loadTable(); // load csv into table
        initializeColumns(); // maps all columns to the getters in apparel (ie. productColumn to product getter)
        initializeQuantityChangeColumns();
        initializeTotalPriceColumn();
        formatTotalPriceColumn();

    }

    private void initializeTotalPriceColumn() {
        totalPriceColumn.setCellValueFactory(cellData -> {
            Apparel item = cellData.getValue();
            double totalPrice = item.getPrice() * item.getQuantity();
            return new javafx.beans.property.SimpleObjectProperty<>(totalPrice); // Return as Double
        });
    }

    private void formatTotalPriceColumn() {
        totalPriceColumn.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(Double item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(String.format("$%.2f", item)); // Format as currency
                }
            }
        });
    }

    @FXML
    private void initializeQuantityChangeColumns() {
        // Decrement Column
        decrementColumn.setCellFactory(param -> new TableCell<>() {
            private final Button decrementButton = new Button("-");

            {
                decrementButton.setOnAction(event -> {
                    Apparel item = getTableView().getItems().get(getIndex());
                    if (item.getQuantity() > 1) {
                        item.setQuantity(item.getQuantity() - 1); // Decrease quantity
                    } else {
                        tableView.getItems().remove(item); // Remove item if quantity hits 0
                    }
                    refreshTable();
                });
            }

            @Override
            protected void updateItem(Void unused, boolean empty) {
                super.updateItem(unused, empty);
                setGraphic(empty ? null : decrementButton);
            }
        });

        // Increment Column
        incrementColumn.setCellFactory(param -> new TableCell<>() {
            private final Button incrementButton = new Button("+");

            {
                incrementButton.setOnAction(event -> {
                    Apparel item = getTableView().getItems().get(getIndex());
                    item.setQuantity(item.getQuantity() + 1); // Increase quantity
                    refreshTable();
                });
            }

            @Override
            protected void updateItem(Void unused, boolean empty) {
                super.updateItem(unused, empty);
                setGraphic(empty ? null : incrementButton);
            }
        });

        // Helper method to refresh the table and update the CSV

    }

    @FXML
    private void refreshTable() {
        tableView.refresh(); // Refresh table to display updated quantities
        inventory.rewriteCSV(new ArrayList<>(tableView.getItems())); // Save changes to CSV
    }

    @FXML
    private void handleCreateProduct() {

        try {
            // set a String product name to the text in productField(the text field)
            String product = productField.getText();
            int quantity = Integer.parseInt(quantityField.getText());
            String size = sizeField.getText();
            String category = categoryField.getText();
            String brand = brandField.getText();
            double price = Double.parseDouble(priceField.getText());

            // make sure all string fields have text in them
            if (product.isEmpty() || size.isEmpty() || category.isEmpty() || brand.isEmpty()) {
                throw new IllegalArgumentException();
            }

            // create new item
            Apparel newItem = new Apparel(product, quantity, size, category, brand, price);

            // add item created to inventory and write it to csv
            inventory.addItem(newItem);

            // put item just created on table
            tableView.getItems().add(newItem);

        } catch (NumberFormatException e) {
            // handles string inputs for quantity & price
            showAlert("Try again bucko", "Please enter valid numbers for quantity and price.");
        } catch (IllegalArgumentException e) {
            showAlert("Try again bucko", "All fields must be filled out.");
        }
        clearText();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // loads table from csv on start up
    @FXML
    private void loadTable(){
        try{

            // make sure there is no leftover items in list
            apparelItems.clear();
            // load data from CSV into apparel Items array List
            apparelItems.addAll(inventory.readCSV());
            // convert array list into observable array list bc table view likes it that way
            ObservableList<Apparel> observableApparelItems = FXCollections.observableArrayList(apparelItems);
            // populate tabel with our oberservable array list named observableApparelItems
            tableView.setItems(observableApparelItems);
        } catch (IOException e) {
            throw new RuntimeException("csv didn't work buddy");
        }
    }

    // clear all text fields after user selects add item to inventory
    @FXML
    private void clearText(){
        productField.setText("");
        quantityField.setText("");
        sizeField.setText("");
        categoryField.setText("");
        brandField.setText("");
        priceField.setText("");

    }

    // initilizes columns to sync with text Fields & apparel variable getters
    @FXML
    private void initializeColumns(){

        // map Apparel object getter in it's column
        productColumn.setCellValueFactory(new PropertyValueFactory<>("product"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        sizeColumn.setCellValueFactory(new PropertyValueFactory<>("size"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
    }
}