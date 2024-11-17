package com.example.classwork;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;

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


    @FXML
    private void displayUserInput(ActionEvent event){
        //size.setText(String.valueOf(productID.getText()));
    }

    @FXML
    private void handleCreateProduct() {
        String productName = productNameField.getText();
        int quantity = Integer.parseInt(quantityField.getText());
        String size = sizeField.getText();
        String category = categoryField.getText();;
        String brand = brandField.getText();
        double price = Double.parseDouble(priceField.getText());

        Apparel newItem = new Apparel(productName, quantity, size, category, brand, price);

        inventory.addItem(newItem);

    }


}
