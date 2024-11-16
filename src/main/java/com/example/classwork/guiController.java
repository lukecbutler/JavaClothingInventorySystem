package com.example.classwork;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class guiController {

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
