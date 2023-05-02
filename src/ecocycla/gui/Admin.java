/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecocycla.gui;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author KHALED
 */
public class Admin extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
           Parent root = FXMLLoader.load(getClass().getResource("Admin.fxml")) ;
            //Parent root = FXMLLoader.load(getClass().getResource("Inscription.fxml"));
            ///Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            //Parent root = FXMLLoader.load(getClass().getResource("ForgotPassword.fxml")) ;
          // Parent root = FXMLLoader.load(getClass().getResource("Changermdp.fxml"));
            Scene scene = new Scene(root);

            primaryStage.setTitle("Listes des Utilisateurs ");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
