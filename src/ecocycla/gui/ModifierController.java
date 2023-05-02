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
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author KHALED
 */
public class ModifierController implements Initializable {

    @FXML
    private TextField ville;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField cin;
    @FXML
    private TextField role;
    @FXML
    private TextField region;
    @FXML
    private TextField datenaissance;
    @FXML
    private TextField email;
    @FXML
    private TextField adresse;
    @FXML
    private Button btnmodifier;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void modifier(ActionEvent event) {
        
          UserCrud rec= new UserCrud();
        
         //Integer id=Integer.parseInt(fxid.getText());
         Integer cin=Integer.parseInt(this.cin.getText()); //conversion 
         
        
         
         User R;
          R = new User(0, email, role, nom, prenom, datenaissance, 0, region, ville, adresse);
           rec.updateUser(R);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Ecocycla :: Success Message");
                alert.setHeaderText(null);
                alert.setContentText("Utilsateur modifié");
                alert.showAndWait(); 
          
             
    }
    
     public void setText(User user) //modifier les donnes eli hatynehom 
    {
     
       
        nom.setText(user.getNom());
        prenom.setText(user.getPrenom());

        adresse.setText(user.getAdresse());
       
       
        email.setText(user.getEmail());
     
    }

    void setTextFields(User user) ///taabili le fenetre bel les donnes 
    {
       
       nom.setText(user.getNom());
       prenom.setText(user.getPrenom());
       email.setText(user.getEmail());
       cin.setText(String.valueOf(user.getCin()));
       
        adresse.setText(user.getAdresse());
        role.setText(user.getRoles());
    }
    
}
