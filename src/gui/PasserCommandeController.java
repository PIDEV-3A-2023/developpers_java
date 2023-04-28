
package gui;

import com.mysql.cj.protocol.Message;
//import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
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
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import entites.Categorie ; 
import entites.Commande;
import entites.Panier;
import java.net.PasswordAuthentication;
import java.text.DecimalFormat;
import java.util.Properties;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import services.ServicePanier;
import services.ServiceCommande;
import sun.plugin2.message.transport.Transport;


/**
 * FXML Controller class
 *
 * @author oumeima
 */
public class PasserCommandeController implements Initializable {

    
    //création de tableau 
    @FXML
    private TableView<Produit> tableView;
    
    @FXML
    private TableColumn<String, String> colNom;
    
    @FXML
    private TableColumn<String, Integer> colQuantity;
   
    
    //appel au service des produits 
    ServicePanier a = new ServicePanier();
    ServiceCommande service_c=new ServiceCommande();
    public static Panier pan ; 
    
    @FXML
    private Label myText;
    
    ObservableList<Produit> obList;

    
    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TableColumn<String, Double> colPrix;
    public static Produit pr ; 
      private TableColumn<Produit, Void> colPanBtn;

    ServiceProduit a_produit = new ServiceProduit();
    
    
    //affichage de contenu de panier
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Double str = CalculPrix() ;
        myText.setText( "      " + str.toString()+   "   dt");
        a = new ServicePanier();
        obList = a.affichagePanier();
           
       //  ColID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom_p"));
        colPrix.setCellValueFactory(new PropertyValueFactory<>("prix_p"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantiteproduit"));

    // Load the data from the CompetitionService into the TableView
   
     tableView.setItems(obList);
        }
    
    
    
    
    //formulaire de la commande

    
    //redirection vers le magasin
     @FXML
    private void MagasinShow(ActionEvent event) {
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
    
    
        //redirection vers le paiement
    @FXML
    private void PayShow(ActionEvent event) {
        try {
            Parent   root = FXMLLoader.load(getClass().getResource("Paiement.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

              stage.setScene(scene);
              stage.show();
          } catch (IOException ex) {
              System.out.println(ex.getMessage());
          };
    }
    
    
    @FXML
    private TextField nametv;
    @FXML
    private TextField nametv1;
    @FXML
    private TextField nametv11;
    @FXML
    private TextField nametv12;
    @FXML
    private Button ajoutbtn;
    private void showAlert(String title, String message) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}
   @FXML
    private void PasserCommande(ActionEvent event) {
    String nom = nametv.getText();
    String adresse = nametv1.getText();
    String tel = nametv11.getText();
    String email= nametv12.getText();

    try {
        
    if (nom.isEmpty()) {
        showAlert("Nom obligatoire", "Nom doit être non vide");
    } else if (adresse.isEmpty()) {
        showAlert("Description obligatoire", "Description doit être non vide");
    } else {
        int pan_id =service_c.GetLastPanier();
        Double prixT = CalculPrix();
        
        service_c.passerCommande(pan_id, nom, prixT, adresse, email);
        try{
         showAlert("Passer Commande","Commande passée");
         System.out.print("testing");
            Parent   root = FXMLLoader.load(getClass().getResource("HistoriqueCommande.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }catch (Exception ex ){
              ex.printStackTrace();}
        
    }
} catch (NumberFormatException e) {
    showAlert("Erreur de saisie", "Veuillez saisir des nombres valides pour le prix et le stock");
}
    }
    
    
       @FXML
    private Double CalculPrix() {
         a = new ServicePanier();
        obList = a.affichagePanier();
        Double prixT =0.0 ;
        for (Produit p : obList){
            prixT= prixT + p.getPrix_p() * p.getQuantiteproduit() ;
        }
        double rounded = Math.round(prixT* 1000.0) / 1000.0;
        return rounded ;
        
    }
    
    
    

    // recherche 
    
    
    
    
    // tri 
    
    
  

    
}
      
    
