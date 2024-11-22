/**
* @Name: Jack Cole, Chloe Smith, Luke Butler, Reese Larkin, Carter Soderena
* @Section: CSC 331
* @Date: 11/24/2024
* @ProgramPurpose: Initiallizes the GUI and displays it
*/

package com.example.classwork;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        /**
        * @Params: stage
        * @Purpose: Sets up the scene for the stage
        */
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("gui.fxml"));
            Parent root = loader.load();
            // Sets up the scene
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setHeight(500);
            stage.setWidth(1435);
            stage.setX(0);
            stage.setY(0);

            stage.setTitle("Clothing Inventory");
            stage.show();
        } catch (Exception e) {
            System.out.println("Error setting up the stage. Try again bucko");;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
