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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
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
    @FXML
    private AnchorPane anchorPaneE1;
    @FXML
    private TableColumn<Produit, ImageView> QrCode;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          afficherQRCode(QrCode);
         a = new ServiceProduit();
        obList = a.affichageProduit();
           
         ColID.setCellValueFactory(new PropertyValueFactory<>("id"));
          colNom.setCellValueFactory(new PropertyValueFactory<>("nom_p"));
        colPrix.setCellValueFactory(new PropertyValueFactory<>("prix_p"));
        String destDir = "file:///C:/xampp/htdocs/img/";
    ColImage.setCellValueFactory(cellData -> {
        Produit produit = cellData.getValue();
        String imagePath = produit.getImage_p();
        if (imagePath != null) {
            try {
                // Create a new ImageView instance for each cell
                ImageView imageView = new ImageView();
                imageView.setFitWidth(150);
                imageView.setFitHeight(150);

                Image image = new Image(destDir+imagePath);
                if (image.isError()) {
                    System.err.println("Error loading image from URL: " + imagePath);
                    return null;
                }
                // Update the image property of the ImageView
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

    tableView.setItems(obList);
   
        

    }    
     private void afficherQRCode(TableColumn<Produit, ImageView> column) {
    column.setCellFactory(col -> new TableCell<Produit, ImageView>() {
        private final ImageView imageView = new ImageView();

        @Override
        protected void updateItem(ImageView item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setGraphic(null);
            } else {
                Produit produit = getTableView().getItems().get(getIndex());

                try {
                    // Charger l'image QR code à partir du dossier htdocs
                    String fileName = produit.getImage_qr_code();
                    File file = new File("C:/xampp/htdocs/img/" + fileName);
                    Image image = new Image(file.toURI().toString(), 150, 150, false, true);
                    imageView.setImage(image);
                    setGraphic(imageView);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    });
}

    @FXML
    private void retour(ActionEvent event) {
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

    @FXML
    private void trieprix(ActionEvent event) {
           a = new ServiceProduit();
        obList = a.affichageProduitTrieer ();
      
        ColID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom_p"));
        colPrix.setCellValueFactory(new PropertyValueFactory<>("prix_p"));
        ColImage.setCellValueFactory(new PropertyValueFactory<>("image_p"));
         afficherQRCode(QrCode);
                

       
         // First, create a single ImageView object to reuse for each cell
//ImageView imageView = new ImageView();
//imageView.setFitWidth(50);
//imageView.setFitHeight(50);


// Then, set up the cell value factory to return the ImageView for each cell



// Then, set up the cell value factory to return the ImageView for each cell

 String destDir = "file:///C:/xampp/htdocs/img/";
    ColImage.setCellValueFactory(cellData -> {
        Produit produit = cellData.getValue();
        String imagePath = produit.getImage_p();
        if (imagePath != null) {
            try {
                // Create a new ImageView instance for each cell
                ImageView imageView = new ImageView();
                imageView.setFitWidth(150);
                imageView.setFitHeight(150);

                Image image = new Image(destDir+imagePath);
                if (image.isError()) {
                    System.err.println("Error loading image from URL: " + imagePath);
                    return null;
                }
                // Update the image property of the ImageView
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
    private void trienom(ActionEvent event) {
           a = new ServiceProduit();
        obList = a.affichageProduitTrieerNom();
      
        ColID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom_p"));
        colPrix.setCellValueFactory(new PropertyValueFactory<>("prix_p"));
        ColImage.setCellValueFactory(new PropertyValueFactory<>("image_p"));
         afficherQRCode(QrCode);
                

       
         // First, create a single ImageView object to reuse for each cell
//ImageView imageView = new ImageView();
//imageView.setFitWidth(50);
//imageView.setFitHeight(50);


// Then, set up the cell value factory to return the ImageView for each cell



// Then, set up the cell value factory to return the ImageView for each cell

 String destDir = "file:///C:/xampp/htdocs/img/";
    ColImage.setCellValueFactory(cellData -> {
        Produit produit = cellData.getValue();
        String imagePath = produit.getImage_p();
        if (imagePath != null) {
            try {
                // Create a new ImageView instance for each cell
                ImageView imageView = new ImageView();
                imageView.setFitWidth(150);
                imageView.setFitHeight(150);

                Image image = new Image(destDir+imagePath);
                if (image.isError()) {
                    System.err.println("Error loading image from URL: " + imagePath);
                    return null;
                }
                // Update the image property of the ImageView
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
