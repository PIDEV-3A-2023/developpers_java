/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecocycla.gui;


import API.Mail;
import ecocycla.services.UserCrud;
import ecocycla.utils.SessionManager;
import java.io.IOException;
import java.util.Random;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;
import javax.mail.MessagingException;


/**
 * FXML Controller class
 *
 * @author KHALED
 */
public class ForgotPasswordController implements Initializable {

    @FXML
    private TextField emailField;
    @FXML
    private Button backButton;
    @FXML
    private Button sendCodeButton;

    
     UserCrud fs = new UserCrud();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    

    @FXML
    private void goBack(ActionEvent event) throws IOException {
         // récupère la scène actuelle à partir de l'événement
        Scene currentScene = ((Node) event.getSource()).getScene();
        // récupère la fenêtre actuelle à partir de la scène
        Stage currentStage = (Stage) currentScene.getWindow();
        // charge la nouvelle vue depuis un fichier FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
        Parent newViewParent = loader.load();
        // crée une nouvelle scène à partir de la vue chargée
        Scene newScene = new Scene(newViewParent);
        // récupère la nouvelle fenêtre à partir de la scène
        Stage newStage = new Stage();
        newStage.setScene(newScene);
        // affiche la nouvelle fenêtre
        newStage.show();
        // ferme l'ancienne fenêtre
        currentStage.close();
    }
     private int generateVerificationCode() {
        Random random = new Random();
        return 100000 + random.nextInt(900000);
    }

       private boolean isValidEmail(String email) {

        return email.matches(".+@.+\\..+");
    }
       
       
    @FXML
    private void sendCode(ActionEvent event) throws IOException {
        String email = emailField.getText();

        if (!isValidEmail(email)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(" Email Invalid");
            alert.setHeaderText("Entrez votre email s'il vous plait .");
            alert.showAndWait();
            return; 
        }
    
        if (fs.SearchByMail(email)!= null) {
            SessionManager.setEmail(email);

            int code = generateVerificationCode();
            boolean emailSent = sendVerificationCodeByEmail(email, code);
            if (emailSent) {
                // Update database to store the verification code with the user's account
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Code Envoyé ");
                alert.setHeaderText("Un Code de vérification est envoyé vers votre email.");
                alert.showAndWait();
                Stage home = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Changermdp.fxml"));
                Parent root = loader.load();
                ChangermdpController cc = loader.getController();
                cc.setVerificationCode(code);
                Scene scene = new Scene(root);

                home.setScene(scene);
                home.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Un erreur s'est produit.");
                alert.showAndWait();
            }

        }
    }
    
    
     private boolean sendVerificationCodeByEmail(String email, int code) {
        // TODO: Implement email sending logic to send code to user's email
        String message = "  Votre  code de rénitialisation : " + code;
        Mail emailsend = new Mail("ecocycla@gmail.com", "pctywincdjjwjiib", email, "Rénitialisation Code ", message);

        try {
            emailsend.sendEmail();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Code Enovoyé ");
            alert.setHeaderText("Un Code de vérification est envoyé vers votre email.");
            alert.showAndWait();
            return true;
        } catch (MessagingException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Un erreur s'est produit .");
            alert.showAndWait();
            return false;
        }

    }

    }

   



