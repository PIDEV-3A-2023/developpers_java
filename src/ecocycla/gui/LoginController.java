/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecocycla.gui;

import ecocycla.entities.CurrentUser;
import ecocycla.entities.User;
import ecocycla.utils.MyConnection;
import ecocycla.utils.SessionManager;
import java.io.IOException;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author KHALED
 */
public class LoginController implements Initializable {

    @FXML
    private TextField email;
    @FXML
    private PasswordField mdp;
    @FXML
    private Button btnseconnecter;
    @FXML
    private Button btnseconnecter1;
    @FXML
    private Button btnf;
    Connection DS;
    Statement ste;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void btnconnexion(ActionEvent event) throws SQLException, IOException {
        
     String mdp2 = hashMotDePasse(mdp.getText());
     String Email = email.getText(); 
        
        if (email.getText().equals("test@gmailk.com") && mdp2.equals("hhhhh")) {
            
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Ecocycla : Success Message");
                alert.setHeaderText(null);
                alert.setContentText("Bienvenu Admin");
                alert.showAndWait();

                Parent root = FXMLLoader.load(getClass().getResource("Admin.fxml"));
                Scene scene;
                scene = new Scene(root);
                Stage stage = new Stage();
                stage.initStyle(StageStyle.TRANSPARENT);
                stage.setScene(scene);
                stage.show();
                Parent fxml = FXMLLoader.load(getClass().getResource("Admin.fxml"));
                
            }else {
                String query2 = "select * from `user` where email= '"+Email + " 'and password= '"+mdp2 +"'";
                Connection cnx = MyConnection.getInstance().getConnection();
                
                PreparedStatement smt = cnx.prepareStatement(query2);    
                ResultSet rs = smt.executeQuery();
                
                if (rs.next()) {
                    User p = new User(rs.getInt("id"), rs.getString("email"), rs.getString("roles"), rs.getString("nom"), rs.getString("prenom"), 0, rs.getString("region"), rs.getString("ville"), rs.getString("adresse"));
                    User.setCurrent_User(p);
                    SessionManager.getInstace(rs.getInt("id"), rs.getInt("cin"), rs.getString("email"), rs.getString("ville"), rs.getString("nom"), rs.getString("adresse"), rs.getString("region"),rs.getString("prenom"), rs.getString("roles"));
                    System.out.println(User.Current_User.getEmail());
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Ecocycla :: Success Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Vous etes connecté");
                    alert.showAndWait();
                    
                Parent root = FXMLLoader.load(getClass().getResource("User.fxml"));
                Scene scene;
                scene = new Scene(root);
                Stage stage = new Stage();
                stage.initStyle(StageStyle.TRANSPARENT);
                stage.setScene(scene);
                stage.show();
                Parent fxml = FXMLLoader.load(getClass().getResource("User.fxml"));
                    
                }    
                
                 
    
}
}

        
    
//    }

    


@FXML
        private void inscrit(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader();//creation de FXMLLoader 
        loader.setLocation(getClass().getResource("Inscription.fxml")); //emplacement du fichier fxml 
        try {
            loader.load();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }

        Parent parent = loader.getRoot();
        Stage stage = new Stage(); //affichage de la fenetre 
        stage.setScene(new Scene(parent));
        stage.initStyle(StageStyle.UTILITY);
        stage.show();
        
    }

    private void forgotPass(MouseEvent event) {
        FXMLLoader loader = new FXMLLoader();//creation de FXMLLoader 
        loader.setLocation(getClass().getResource("ForgotPassword.fxml")); //emplacement du fichier fxml 
        try {
            loader.load();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }

        AjoutController muc = loader.getController(); //recuperation deu controller de modification 
        //mrc.setUpdate(true);

        Parent parent = loader.getRoot();
        Stage stage = new Stage(); //affichage de la fenetre 
        stage.setScene(new Scene(parent));
        stage.initStyle(StageStyle.UTILITY);
        stage.show();
    }

    @FXML
        private void Forgotpass(ActionEvent event) {
         FXMLLoader loader = new FXMLLoader();//creation de FXMLLoader 
        loader.setLocation(getClass().getResource("ForgotPassword.fxml")); //emplacement du fichier fxml 
        try {
            loader.load();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }

        ForgotPasswordController muc = loader.getController(); //recuperation deu controller de modification 
        //mrc.setUpdate(true);

        Parent parent = loader.getRoot();
        Stage stage = new Stage(); //affichage de la fenetre 
        stage.setScene(new Scene(parent));
        stage.initStyle(StageStyle.UTILITY);
        stage.show();
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
}
