/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import entites.Produit;
import services.ServiceProduit;
import services.ServiceCat;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.fxml.FXMLLoader;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.File;

/**
 * FXML Controller class
 *
 * @author khadija
 */
public class ListeFrontController implements Initializable {

    @FXML
    private VBox mainVBox;
 ServiceProduit ps = new ServiceProduit();
   ServiceCat cs = new ServiceCat();
    public static Produit p;
    public static int produitID = 0;
     ObservableList<Produit> obList;
    @FXML
    private AnchorPane mainPain;
    @FXML
    private Text topText;
    @FXML
    private Button retourbtn;
    @FXML
    private Button triePrix;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ps = new ServiceProduit();
        obList = ps.affichageProduit();

        if (!obList.isEmpty()) {
            for (Produit abo : obList) {
                mainVBox.getChildren().add(makecards(abo));
            }
        } else {
            StackPane stackPane = new StackPane();
            stackPane.setAlignment(Pos.CENTER);
            stackPane.setPrefHeight(200);
            stackPane.getChildren().add(new Text("Aucune donnée"));
            mainVBox.getChildren().add(stackPane);
        }
    }  
      public Parent makecards(Produit prod) {
          
        Parent parent = null;

        try {
            parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("cardsModel.fxml")));

            HBox innerContainer = ((HBox) ((AnchorPane) ((AnchorPane) parent).getChildren().get(0)).getChildren().get(0));
            ((Text) innerContainer.lookup("#id_txt")).setText("id : " + prod.getId());
            ((Text) innerContainer.lookup("#name_txt")).setText("name  : " + prod.getNom_p());
            ((Text) innerContainer.lookup("#description_txt")).setText("description  : " +  prod.getDescription_p());
            ((Text) innerContainer.lookup("#price_txt")).setText("price  : " +  prod.getPrix_p());
            ((Text) innerContainer.lookup("#quantity_txt")).setText("quantity  : " +  prod.getStock());
//              // Récupérer l'ImageView et charger l'image depuis le fichier


//           ((ImageView) innerContainer.lookup("#imgView")).setImage(new Image(getClass().getResourceAsStream("/xampp/htdocs/img/" + prod.getImage_p())));
           
        ((Pane) innerContainer.lookup("#commentPane")).setVisible(false);
        

            ((Pane) innerContainer.lookup("#sss")).setVisible(true);
          
 
         

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return parent;
    }

    @FXML
    private void retouraction(ActionEvent event) {
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
    private void triePrixaction(ActionEvent event) {
          ps = new ServiceProduit();
        obList = ps.affichageProduitTrieer();

        if (!obList.isEmpty()) {
            for (Produit abo : obList) {
                mainVBox.getChildren().add(makecards(abo));
            }
        } else {
            StackPane stackPane = new StackPane();
            stackPane.setAlignment(Pos.CENTER);
            stackPane.setPrefHeight(200);
            stackPane.getChildren().add(new Text("Aucune donnée"));
            mainVBox.getChildren().add(stackPane);
        }
    }

    
}
