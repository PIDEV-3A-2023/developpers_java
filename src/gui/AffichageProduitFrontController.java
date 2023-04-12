/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entites.Produit;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import services.ServiceProduit;

/**
 * FXML Controller class
 *
 * @author khadija
 */
public class AffichageProduitFrontController implements Initializable {

   @FXML
    private TableView<Produit> tableView;
    @FXML
    private TableColumn<String, String> colNom;
    @FXML
    private TableColumn<String, Double> colPrix;
  @FXML
    private TableColumn<Produit, ImageView> ColImage;
    @FXML
    private TableColumn<String, Integer> ColID;
   ServiceProduit a = new ServiceProduit();
    public static Produit pr ; 
  
 ObservableList<Produit> obList;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         a = new ServiceProduit();
        obList = a.affichageProduit();
           
         ColID.setCellValueFactory(new PropertyValueFactory<>("id"));
          colNom.setCellValueFactory(new PropertyValueFactory<>("nom_p"));
        colPrix.setCellValueFactory(new PropertyValueFactory<>("prix_p"));
        
         ColImage.setCellValueFactory(new PropertyValueFactory<>("image_p"));
      
        // First, create a single ImageView object to reuse for each cell
ImageView imageView = new ImageView();
imageView.setFitWidth(150);
imageView.setFitHeight(150);


// Then, set up the cell value factory to return the ImageView for each cell



// Then, set up the cell value factory to return the ImageView for each cell

 
  String destDir = "file:///C:/xampp/htdocs/img/";
ColImage.setCellValueFactory(cellData -> {
    Produit produit = cellData.getValue();
    String imagePath = produit.getImage_p();
    if (imagePath != null) {
        try {
            Image image = new Image(destDir+imagePath);
            if (image.isError()) {
                System.err.println("Error loading image from URL: " + imagePath);
                return null;
            }
            // Update the image property of the reusable ImageView
            imageView.setImage(image);
            return new SimpleObjectProperty<>(imageView);
        } catch (Exception e) {
            System.err.println("Error loading image: " + e.getMessage());
            return null;
        }
    } else {
        // If the image path is null, return null to display an empty cell
        return null;
    }
});


    // Load the data from the CompetitionService into the TableView
   
     tableView.setItems(obList);
    }    
    
}
