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
import services.ServiceProduit;

/**
 * FXML Controller class
 *
 * @author khadija
 */
public class AffichageProduitFrontController implements Initializable {

    //création de tableau 
    @FXML
    private TableView<Produit> tableView;
    
    //les colonnes
    @FXML
    private TableColumn<String, Integer> ColID;
    @FXML
    private TableColumn<String, String> colNom;
    @FXML
    private TableColumn<String, Double> colPrix;
    @FXML
    private TableColumn<Produit, ImageView>ColImage;
   

    //appel au service des produits 
    ServiceProduit a = new ServiceProduit();
    public static Produit pr ; 
    
    //colonne pour ajouter le produit au panier ( + ) 
    private TableColumn<Produit, Void> colPanBtn;

    ObservableList<Produit> obList;

    
    /**
     * Initializes the controller class.
     */
    
    //affichage de liste des produits 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        colPanBtn = new TableColumn<>("Ajouter Au Panier");
        tableView.getColumns().add(colPanBtn);
        a = new ServiceProduit();
        obList = a.affichageProduit();
        
        //les colonnes de tableau
        ColID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom_p"));
        colPrix.setCellValueFactory(new PropertyValueFactory<>("prix_p"));
        ColImage.setCellValueFactory(new PropertyValueFactory<>("image_p"));
      
        // 1.  First, create a single ImageView object to reuse for each cell
        ImageView imageView = new ImageView();
        imageView.setFitWidth(150);
        imageView.setFitHeight(150);


        //2. Then, set up the cell value factory to return the ImageView for each cell
        //3. Then, set up the cell value factory to return the ImageView for each cell

 
        String destDir = "file:///C:/xampp/htdocs/img/";
        ColImage.setCellValueFactory(cellData -> {
            
            Produit produit = cellData.getValue();
            String imagePath = produit.getImage_p();

            //téléchargement de l'image 
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
        
        addButtonPanToTable();

       // Load the data from the CompetitionService into the TableView
       tableView.setItems(obList);
    }    
    
    Button btn;
    Produit A = new Produit();
    
    
 
    // Ajout un produit au panier ( + )     
    private void addButtonPanToTable() {
        Callback<TableColumn<Produit , Void>, TableCell<Produit, Void>> cellFactory = new Callback<TableColumn<Produit, Void>, TableCell<Produit, Void>>() {
        
            @Override
            public TableCell<Produit, Void> call(final TableColumn<Produit, Void> param) {
               final TableCell<Produit, Void> cell = new TableCell<Produit, Void>() {
                    {
                        btn = new Button("+");
                        btn.setOnAction((ActionEvent event) -> {
                            try {
                                A = tableView.getSelectionModel().getSelectedItem();//
                                pr = new Produit (A.getId(), A.getNom_p(),A.getImage_p(),A.getDescription_p(),A.getPrix_p() ,A.getStock(),A.getIdcat_p_id()) ;
                                a.ajouterProduitAuPanier(pr);
                                 Parent   root = FXMLLoader.load(getClass().getResource("AffichageProduitFront.fxml"));
                                Scene scene = new Scene(root);
                                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            } catch (Exception e)
                                {
                                 e.printStackTrace();
                                }
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        }
                        else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };
      colPanBtn.setCellFactory(cellFactory);
    }
        

    //redirection vers le panier 
    @FXML
    private void PanierShow(ActionEvent event) {
        try {
            Parent   root = FXMLLoader.load(getClass().getResource("AffichagePanier.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
           };
    }
}
