/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entites.Produit;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import static javafx.scene.input.KeyCode.P;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import services.ServiceProduit;

/**
 * FXML Controller class
 *
 * @author khadija
 */
public class ModifierProduitController implements Initializable {

    @FXML
    private TextField nametv;
    @FXML
    private TextField stocktv;
    @FXML
    private TextField prixtv;
    @FXML
    private TextArea descriptiontv;
    @FXML
    private AnchorPane anchorePaneEl;
    @FXML
    private Button ajoutbtn;
      private String nom;
    private double prix;
    private int stock;
    private String description;
    private int id;
    private  int idcat_p_id;
    private Produit pr = gui.AffichageProduitController.pr ;
    @FXML
    private ImageView imgview;
     String filePath="";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         System.out.println("heree");
           System.out.println(pr);
           FXMLLoader loader = new FXMLLoader();

            loader.setLocation(getClass().getResource("/gui/AffichageProduit.fxml"));
            Stage prStage = new Stage();

            Parent root;
        
        try {
                    root = loader.load();
                Scene scene = new Scene(root);
                prStage.setScene(scene);
                AffichageProduitController irc = loader.getController();
                ServiceProduit sp = new ServiceProduit();

                id = irc.A.getId();
                 

            } catch (IOException ex) {
            }
    }  
           int idS;

    public void setId(int id) {

        idS = id;
        System.out.println("her id " + idS);
    }

    @FXML
    private void ModifierProduitHandle(ActionEvent event) {
        
    try {

            ServiceProduit ss = new ServiceProduit();

            Produit  s = new Produit();
            s.setNom_p(nametv.getText());
            s.setStock(Integer.parseInt(stocktv.getText()));
            s.setPrix_p(Float.parseFloat(prixtv.getText()));
            s.setDescription_p(descriptiontv.getText());
            s.setIdcat_p_id(3);
             System.out.println("idddd"+idS);
            s.setId(idS);
            s.setImage_p(filePath);
            ss.modifierProduit(s);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("succes");
            alert.setHeaderText("Produit modifié avec succés !!!");
            alert.setContentText("Validé !");
            alert.showAndWait();

             FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/AffichageProduit.fxml"));
                                Parent root = loader.load();
                                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                stage.setScene(new Scene(root));
                                stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
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
    


    
    
    
    public void setNom(String nom) {
        nametv.setText(nom);
    }
    
    public void setPrix(double prix) {
        prixtv.setText(String.valueOf(prix));
    }

    public void setDescription(String desc) {
        descriptiontv.setText(desc);
        
        
    }
    
    public void setStock(int stock) {
        stocktv.setText(String.valueOf(stock));
        
        
    }

    void setImage_p(String img) {
Platform.runLater(() -> {
filePath = img;
if (img != null) {
File file = new File(img);
Image image = new Image(file.toURI().toString());
imgview.setImage(image);
}
});
}

   

    
}
