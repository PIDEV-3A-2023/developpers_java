/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import services.ServiceProduit;
import entites.Produit;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.image.Image;

/**
 * FXML Controller class
 *
 * @author khadija
 */
public class AjoutProduitController implements Initializable {

    @FXML
    private TextField nametv;
    @FXML
    private TextField stocktv;
    @FXML
    private TextField prixtv;
    @FXML
    private ImageView imgview;
    @FXML
    private Button ajoutbtn;
    
     String filePath="";
    
    
    ServiceProduit sp = new ServiceProduit();
    @FXML
    private AnchorPane anchorePaneEl;
    @FXML
    private TextArea descriptiontv;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     int stock;
    double prix;
    @FXML
    private void AjouterProduitHandle(ActionEvent event) {
        
          String nom = nametv.getText();
String description = descriptiontv.getText();
int stock;
double prix;

try {
    stock = Integer.parseInt(stocktv.getText());
    prix = Double.parseDouble(prixtv.getText());
    
    if (nom.isEmpty()) {
        showAlert("Nom obligatoire", "Nom doit être non vide");
    } else if (description.isEmpty()) {
        showAlert("Description obligatoire", "Description doit être non vide");
    } else {
        String regex = "^[0-9]\\.{0,1}[0-9]{0,2}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(prixtv.getText());
        
        if (matcher.matches()) {
            Produit produit = new Produit(nom, filePath, "null", description, prix, stock, 2);
            sp.ajouterProduit(produit);
            showAlert("Produit ajouté", "Produit ajouté avec succès");
        } else {
            showAlert("Prix non valide", "Prix doit être valide");
        }
    }
} catch (NumberFormatException e) {
    showAlert("Erreur de saisie", "Veuillez saisir des nombres valides pour le prix et le stock");
}
    }
// ...

private void showAlert(String title, String message) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}

    @FXML
    private void UploadImageHandle(MouseEvent event) {
        FileChooser fileOpen = new FileChooser();
    Stage stage = (Stage) anchorePaneEl.getScene().getWindow();
    File file = fileOpen.showOpenDialog(stage);
    
    if(file != null) {
        try {
            String path = file.getName(); //file.getPath() 0 security...
            filePath=path;
            //https://img.png 
            Image image = new Image(file.toURI().toString(),100,100,false,true);
            imgview.setImage(image);
            
            // Set the destination directory path
            String destDir = "C:/xampp/htdocs/img/";
            
            // Copy the uploaded file to the destination directory
            Path source = Paths.get(file.getAbsolutePath());
            Path dest = Paths.get(destDir + file.getName());
            Files.copy(source, dest, StandardCopyOption.REPLACE_EXISTING);
            
            System.out.println("File uploaded successfully to " + destDir + file.getName());
        } catch (IOException ex) {
            System.out.println("Error uploading file: " + ex.getMessage());
        }
    }
    else {
        System.out.println("NO PICTURE EXISTS!!");
    }
    }

    @FXML
    private void listePr(ActionEvent event) {
            try {
                  Parent   root = FXMLLoader.load(getClass().getResource("AffichageProduit.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                };
    }
}
    

