package com.example.classwork;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


import java.io.IOException;
import java.util.ArrayList;

public class guiController {

    // create list of apparel items to be passed into inventory
    ArrayList<Apparel> apparelItems = new ArrayList<>();
    // create inventory object - storing apparel items
    Inventory inventory = new Inventory(apparelItems);


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


    // create new table that takes in Apparel object
    @FXML
    private TableView<Apparel> tableView;

    // set column to take in apparel object as a string
    @FXML
    private TableColumn<Apparel, String> productNameColumn;

    @FXML
    public void initialize(){
        loadTable();
        initializeColumns(); // maps all columns to the getters in apparel (ie. productNameColumn to productName getter)


    }

    @FXML
    private void handleCreateProduct() {

        // set a String product name to the text in productNameField(the text field)
        String productName = productNameField.getText();

        // create new item
        Apparel newItem = new Apparel(productName, 0, null, null, null, 0);

        // add item created to inventory and write it to csv
        inventory.addItem(newItem);

        // put item just created on table
        tableView.getItems().add(newItem);
    }

    // loads table from csv on start up
    @FXML
    private void loadTable(){
        try{

            // make sure there is no left over items in list
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

    // initilizes columns to sync with text Fields & apparel variable getters
    @FXML
    private void initializeColumns(){

        // maps the getter productName to productNameColumn
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
    }
}