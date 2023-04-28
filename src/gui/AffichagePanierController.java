/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entites.Categorie;
import entites.Produit;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import services.ServicePanier;
import services.ServiceProduit;

/**
 * FXML Controller class
 *
 * @author khadija
 */
public class AffichagePanierController implements Initializable {

   @FXML
    private TableView<Produit> tableView;
    @FXML
    private TableColumn<String, String> colNom;
    @FXML
    private TableColumn<String, Double> colPrix;
  @FXML
    private TableColumn<Produit, ImageView> ColImage;
  
    @FXML
    private TableColumn<String, Integer> colQuantity;
      
    @FXML
    private TableColumn<String, Integer> ColID;
   ServicePanier a = new ServicePanier();
    public static Produit pr ; 
      private TableColumn<Produit, Void> colPanBtn;
     @FXML
    private Label myText;
 ObservableList<Produit> obList;
    ServiceProduit a_produit = new ServiceProduit();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        String str = CalculPrix() ;
        myText.setText( "      " + str+   "   dt");
         a = new ServicePanier();
        obList = a.affichagePanier();
           
        ColID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom_p"));
        colPrix.setCellValueFactory(new PropertyValueFactory<>("prix_p"));
        ColImage.setCellValueFactory(new PropertyValueFactory<>("image_p"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantiteproduit"));

      
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
    
    
        @FXML
    private String CalculPrix() {
         a = new ServicePanier();
        obList = a.affichagePanier();
        Double prixT =0.0 ;
        for (Produit p : obList){
            prixT= prixT + p.getPrix_p() * p.getQuantiteproduit() ;
        }
        DecimalFormat df = new DecimalFormat("#.###");
        String formatted = df.format(prixT);
        return formatted ;
        
    }
    
    
    
    
    
    @FXML
    private void boutiqueShow(ActionEvent event) {
         try {
                  Parent   root = FXMLLoader.load(getClass().getResource("AffichageProduitFront.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                };
    }
    
     @FXML
    private void CommandeShow(ActionEvent event) {
         try {
                  Parent   root = FXMLLoader.load(getClass().getResource("PasserCommande.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                };
    }
    
    @FXML
    private void HistoriqueShow(ActionEvent event) {
         try {
                  Parent   root = FXMLLoader.load(getClass().getResource("HistoriqueCommande.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                };
    }
    
}

