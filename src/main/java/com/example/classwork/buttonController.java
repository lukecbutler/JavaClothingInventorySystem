package com.example.classwork;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;

public class buttonController {

    @FXML
    public Button buttonID;

    @FXML
    private TextField userInput;

    @FXML
    private TextField userOutput;

    @FXML
    private void displayUserInput(ActionEvent event){
        userOutput.setText(String.valueOf(userInput.getText()));
    }
}
