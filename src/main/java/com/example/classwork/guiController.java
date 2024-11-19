package com.example.classwork;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.*;


import java.util.ArrayList;

public class guiController {

    // create list of apparel items to be passed into inventory
    ArrayList<Apparel> apparelItems = new ArrayList<>();
    // create inventory object - storing apparel items
    Inventory inventory = new Inventory(apparelItems);

    // create new table that takes in Apparel object
    @FXML
    private
    TableView<Apparel> tableView;


    // set column to take in apparel object as a string
    @FXML
    private TableColumn<Apparel, String> productNameColumn;



    @FXML
    private TextField productNameField;

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

    @FXML
    public void initialize(){
        handleCreateProduct();

    }

    @FXML
    private void handleCreateProduct() {
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
        String productName = productNameField.getText();

        Apparel newItem = new Apparel(productName, null, null, null, null, null);

        inventory.addItem(newItem);
        tableView.getItems().add(newItem);

        ArrayList<Apparel> apparelItems = new ArrayList<>(tableView.getItems());

        // Call rewriteCSV with the ArrayList
        inventory.rewriteCSV(apparelItems);

    }


}
