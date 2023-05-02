package ecocycla.gui;

//import ecocycla.utils.MyConnection;
//import ecocycla.utils.SessionManager;
//import java.io.IOException;
//import java.net.URL;
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//import java.util.ResourceBundle;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.fxml.Initializable;
//import javafx.scene.Node;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.Alert;
//import javafx.scene.control.Button;
//import javafx.scene.control.TextField;
//import javafx.stage.Stage;
//
//public class ChangermdpController implements Initializable {
//
//    private TextField codeField;
//    private TextField newPasswordField;
//    private TextField confirmNewPasswordField;
//  
//    @FXML
//    private Button btnvalider;
//
//    /**
//     * Initializes the controller class.
//     */
//    @Override
//    public void initialize(URL url, ResourceBundle rb) {
//        // TODO
//    }
//
//    private void validatePasswordChange(ActionEvent event) {
//        String code = codeField.getText();
//        String newPassword = newPasswordField.getText();
//        String confirmNewPassword = confirmNewPasswordField.getText();
//
//        // Verify that all fields are filled out
//        if (code.isEmpty() || newPassword.isEmpty() || confirmNewPassword.isEmpty()) {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Error");
//            alert.setHeaderText("Please fill out all fields.");
//            alert.showAndWait();
//            return;
//        }
//
//        // Verify that the new password and confirmation match
//        if (!newPassword.equals(confirmNewPassword)) {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Error");
//            alert.setHeaderText("New password and confirmation do not match.");
//            alert.showAndWait();
//            return;
//        }
//
//        // Verify that the code entered matches the one sent to the user's email
//        if (!verifyCode(code)) {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Error");
//            alert.setHeaderText("Verification code is incorrect.");
//            alert.showAndWait();
//            return;
//        }
//
//        // Update the user's password in the database
//        if (updatePassword(newPassword)) {
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("Success");
//            alert.setHeaderText("Password updated successfully.");
//            alert.showAndWait();
//
//            // Close the window
//            Stage stage = (Stage) btnvalider.getScene().getWindow();
//            stage.close();
//        } else {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Error");
//            alert.setHeaderText("An error occurred while updating the password.");
//            alert.showAndWait();
//        }
//    }
//
//    private boolean verifyCode(String code) {
//        // TODO: Implement code verification logic
//        // You can retrieve the code that was sent to the user's email from the database and compare it to the code entered by the user
//        // Return true if the codes match, false otherwise
//        return true;
//    }
//
//    private boolean updatePassword(String newPassword) {
//        // TODO: Implement password update logic
//        // Use an update query to update the user's password in the database with the new password
//        // Return true if the update was successful, false otherwise
//        return true;
//    }
//
//    @FXML
//    private void valider(ActionEvent event) throws IOException {
//         //if (newPasswordField.getText().equals(confirmNewPasswordField.getText())){
//            
//            try {
//                Connection cnx = MyConnection.getInstance().getConnection();
//                String req ="UPDATE user SET `password`=? WHERE email= ?";
//                
//                PreparedStatement ps = cnx.prepareStatement(req);
//                ps.setString(1, confirmNewPasswordField.getText());
//                
//                ps.setString(2,SessionManager.getEmail() );
//                ps.executeUpdate();
//                Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
//                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//                Scene scene = new Scene(root);
//                stage.setScene(scene);
//                stage.show();
//                
//            
//        
//                    // ChampsPassword.setText("Les mots de passe ne sont pas les mêmes");
//                            }catch (SQLException ex) {
//                Logger.getLogger(ChangermdpController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//    
//    }
//           
//    
//
//    public String hashMotDePasse(String motDePasse) {
//        try {
//            MessageDigest md = MessageDigest.getInstance("SHA-256");
//            byte[] hashBytes = md.digest(motDePasse.getBytes());
//            StringBuilder sb = new StringBuilder();
//            for (byte b : hashBytes) {
//                sb.append(String.format("%02x", b));
//            }
//            return sb.toString();
//        } catch (NoSuchAlgorithmException e) {
//            throw new RuntimeException(e);
//        }
//    }
//    }
//


import ecocycla.entities.User;
import ecocycla.services.UserCrud;
import ecocycla.utils.MyConnection;
import ecocycla.utils.SessionManager;
import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
import javafx.stage.Stage;
import jdk.nashorn.internal.runtime.regexp.joni.constants.CCSTATE;

public class ChangermdpController implements Initializable {

    @FXML
    private TextField codeField;
    @FXML
    private TextField newPasswordField;
    @FXML
    private TextField confirmNewPasswordField;
    @FXML
    private Button btnvalider;

    private int verificationCode;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setVerificationCode(int code) {
        this.verificationCode = code;
    }
     
    @FXML
    private void valider(ActionEvent event) throws IOException, SQLException {
        String pass = hashMotDePasse(newPasswordField.getText()); 
        if (newPasswordField.getText().equals(confirmNewPasswordField.getText())) {
            Connection cnx = MyConnection.getInstance().getConnection();
            String req = "UPDATE `user` SET `password`=? WHERE email= ?";
            try {
                PreparedStatement ps = cnx.prepareStatement(req);
                ps.setString(1, pass);
                ps.setString(2, SessionManager.getEmail());
                ps.executeUpdate();
                Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            } catch (SQLException ex) {
                Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Mot de passe modifié ");
            alert.setHeaderText("Mot de passe modifié avec succes .");
            alert.showAndWait();

        }

    }
     public String hashMotDePasse(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    

    private boolean isValidPassword(String password) {
        return password.length() >= 8;
    }
}
