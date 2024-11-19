package com.example.classwork;

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
    public void initialize(){
        loadTable(); // load csv into table
        initializeColumns(); // maps all columns to the getters in apparel (ie. productColumn to product getter)


    }

    @FXML
    private void handleCreateProduct() {

        // set a String product name to the text in productField(the text field)
        String product = productField.getText();
        int quantity = Integer.parseInt(quantityField.getText());
        String size = sizeField.getText();
        String category = categoryField.getText();
        String brand = brandField.getText();
        double price = Double.parseDouble(priceField.getText());


        // create new item
        Apparel newItem = new Apparel(product, quantity, size, category, brand, price);

        // add item created to inventory and write it to csv
        inventory.addItem(newItem);

        // put item just created on table
        tableView.getItems().add(newItem);
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