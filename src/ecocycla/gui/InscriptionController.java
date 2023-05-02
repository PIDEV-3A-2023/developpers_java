/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecocycla.gui;

import API.Mail;
import ecocycla.entities.User;
import ecocycla.services.UserCrud;
import ecocycla.utils.MyConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.mail.MessagingException;

/**
 * FXML Controller class
 *
 * @author KHALED
 */
public class InscriptionController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField email;
    @FXML
    private TextField cin;
    @FXML
    private TextField datedenaissance;
    @FXML
    private TextField region;
    @FXML
    private TextField ville;
    @FXML
    private TextField adresse;
    @FXML
    private PasswordField mdp;
    @FXML
    private Button btninscription;
    
    
    private Connection cnx;
    private Statement statement;
    private PreparedStatement prepare;
    private ResultSet result;
    @FXML
    private PasswordField confirmerPassword;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    
    public boolean verifierEmailNonDuplique(String email) {
        String requete = "SELECT * FROM user WHERE email = ?";
        PreparedStatement statement;
        ResultSet resultSet;
        Connection Ds = MyConnection.getInstance().getConnection();
        boolean emailExiste = false;

        try {
            statement = Ds.prepareStatement(requete);
            statement.setString(1, email);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {

                emailExiste = true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return !emailExiste;
    }
    private boolean isValidEmail(String email) {
        // TODO: Ajouter une validation d'email plus avancée
        return email.matches(".+@.+\\..+");
    }

    public boolean ValidationEmail() {
        String email = this.email.getText(); 
        Pattern pattern = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9._]+([.][a-zA-Z0-9]+)+");
        Matcher match = pattern.matcher(email);

        if (match.find() && match.group().equals(email)) {
            return true;
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Errore message");
            alert.setHeaderText(null);
            alert.setContentText("Invalid Email");
            alert.showAndWait();

            return false;
        }
    }

  

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public String hashMotDePasse(String motDePasse) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(motDePasse.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

   

    @FXML
    private void INSCRIPTION(ActionEvent event) {
         
        boolean saisieValide = true;
        String messageErreur = "";
        String activation_token = null;
        String EMAIL = email.getText();
          if (nom.getText().isEmpty() || prenom.getText().isEmpty()||email.getText().isEmpty() || cin.getText().isEmpty()|| ville.getText().isEmpty() || adresse.getText().isEmpty() ) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Champs invalides ! ", ButtonType.OK);
            a.showAndWait();
           
        }
          else if (confirmerPassword.getText().length() < 8 | confirmerPassword.getText() == mdp.getText()) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ecocycla:: Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Password doit etre sup 8 caractéres !!");
            alert.showAndWait();

        } 
          else if (cin.getText().length() < 8 ){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ecocycla:: Error Message");
            alert.setHeaderText(null);
            alert.setContentText("CIN doit etre de 8 chiffres  !!");
            alert.showAndWait();
            
        } 
          
           
        else if (!EMAIL.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}") || verifierEmailNonDuplique(EMAIL) == false) {
            saisieValide = false;
            messageErreur += "L'email n'est pas valide.\n";
         Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ecocycla:: Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Email non valide   !!");
            alert.showAndWait();
            }
          else {
            
             
                  UserCrud us = new UserCrud();
                  User p = new User(email, mdp, nom, prenom, datedenaissance, 0, region, ville, adresse);
                  us.createUser(p);
                    String message = "Bonjour " + nom.getText() + " " + prenom.getText() + "\n"
                    + "Merci de vous être inscrit! Votre code de confirmation est : " ;
            String Email = email.getText();
            Mail emailsend = new Mail("ecocycla@gmail.com", "pctywincdjjwjiib", Email, "Confirmation d'inscription", message);

            try {
                emailsend.sendEmail();
                Alert alerte = new Alert(AlertType.INFORMATION);
                alerte.setTitle("Confirmation d'inscription");
                alerte.setHeaderText("Inscription réussie!");
                alerte.setContentText("Un email de confirmation a été envoyé à l'adresse " + Email + ".");
                alerte.showAndWait();
                Stage home = new Stage();
                
            } catch (MessagingException ex) {
                Alert alerte = new Alert(AlertType.ERROR);
                alerte.setTitle("Erreur lors de l'envoi de l'email");
                alerte.setHeaderText("Erreur lors de l'envoi de l'email de confirmation");
                alerte.setContentText("Veuillez réessayer plus tard.");
                alerte.showAndWait();
                System.out.println(ex.getMessage());
            }
                  
                  Alert a = new Alert(Alert.AlertType.INFORMATION, "Utilisateur inscrit avec succes !", ButtonType.OK);
                  a.showAndWait();
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ecocycla :: BIENVENNUE");
            alert.setHeaderText(null);
            alert.setContentText("Vous Etes Inscrit !!");
            alert.showAndWait();
                  
                  
                 
        }
    }}
