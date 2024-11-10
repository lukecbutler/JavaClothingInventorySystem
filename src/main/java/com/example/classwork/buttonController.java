package com.example.classwork;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

public class buttonController {

    @FXML
    private TextField textID;

    @FXML
    private void calculateButtonPressed(ActionEvent event){
        textID.setText("Happy Birthday Motherfucker");
    }
}
