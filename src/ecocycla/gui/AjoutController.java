/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecocycla.gui;

import ecocycla.entities.User;
import ecocycla.services.UserCrud;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author KHALED
 */
public class AjoutController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField email;
    @FXML
    private TextField datenaisssance;
    @FXML
    private TextField cin;
    @FXML
    private TextField role;
    @FXML
    private TextField ville;
    @FXML
    private TextField region;
    @FXML
    private TextField adresse;
    @FXML
    private PasswordField motdepasse;
    @FXML
    private Button btnajouter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouter(ActionEvent event) {
        
          if (nom.getText().isEmpty() || prenom.getText().isEmpty()||email.getText().isEmpty() || cin.getText().isEmpty()|| ville.getText().isEmpty() || adresse.getText().isEmpty()||role.getText().isEmpty() ) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Champs invalides ! ", ButtonType.OK);
            a.showAndWait();
           
        }
          else {
             
                  UserCrud us = new UserCrud();
                  User p = new User(email, role, motdepasse, nom, prenom, datenaisssance, 0, region, ville, adresse);
                  us.createUser(p);
                  Alert a = new Alert(Alert.AlertType.INFORMATION, "Utilisateur ajouté(e) avec succes !", ButtonType.OK);
                  a.showAndWait();
                  
                 
        }
    }
    
}
