
package gui;

import static com.sun.deploy.security.BlockedDialog.show;
import entites.Commande;
import entites.Produit;
import java.awt.Insets;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import pdfviewer.PdfViewer;
import services.ServiceCommande;
import javafx.scene.control.Pagination;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


/**
 *
 * @author oumeima
 */
public class HistoriqueCommandeController  implements Initializable  {
    
    Connection connection = null;
    Statement statemant = null;

    
    //création de tableau 
    @FXML
    private Pagination pagination ;
    //appel au service des produits 
    PdfViewer a = new PdfViewer();
    public static Commande cmd ; 
    
    //colonne pour ajouter le produit au panier ( + ) 
    private TableColumn<Produit, Void> colPanBtn;
    
    private Label label;
    

    @FXML
    private TextField idsup;
    @FXML
    private TextField tfsearch;


    private final static int rowsPerPage = 7; 
    private final TableView<Commande> table = createTable();

    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
        @Override
    public void initialize(URL url, ResourceBundle rb) {
         ServiceCommande sc = new ServiceCommande();
        ObservableList<Commande> commandes =sc.AfficherCommande();
         // Create a Pagination control
        pagination.setPageCount((int) Math.ceil((double) commandes.size() / rowsPerPage));
        pagination.setPageFactory(this::createPage);    
    
    }

    //method to create page inside pagination view
    private Node createPage(int pageIndex) {
         ServiceCommande sc = new ServiceCommande();
        ObservableList<Commande> commandes =sc.AfficherCommande();
        int fromIndex = pageIndex * rowsPerPage;
        int toIndex = Math.min(fromIndex + rowsPerPage, commandes.size());
        table.setItems(FXCollections.observableArrayList(commandes.subList(fromIndex, toIndex)));
        return table;
    }
    

 private TableView<Commande> createTable() {

       TableView<Commande> table = new TableView<>();

        TableColumn<Commande, Integer> idColumn = new TableColumn<>("id");
        idColumn.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getId()).asObject());
        idColumn.setPrefWidth(100);

      TableColumn<Commande, String> nameColumn = new TableColumn<>("Nom");
      nameColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getNom_client()));
     nameColumn.setPrefWidth(120);     

      TableColumn<Commande, Date> dateColumn = new TableColumn<>("Date");
      dateColumn.setCellValueFactory(new PropertyValueFactory<Commande,Date>("date_commande"));
     dateColumn.setPrefWidth(70); 
     
     TableColumn<Commande, Float> PrixColumn = new TableColumn<>("Prix");
     PrixColumn.setCellValueFactory(new PropertyValueFactory<Commande,Float>("prix_total"));
     PrixColumn.setPrefWidth(70); 
     
     TableColumn<Commande, String> adresseColumn = new TableColumn<>("adresse");
     adresseColumn.setCellValueFactory(new PropertyValueFactory<Commande,String>("adresse_livraison"));
     adresseColumn.setPrefWidth(70);
     
     TableColumn<Commande, String> MailColumn = new TableColumn<>("Mail");
     MailColumn.setCellValueFactory(new PropertyValueFactory<Commande,String>("adresse_livraison"));
     MailColumn.setPrefWidth(70);
     
     table.getColumns().addAll(idColumn,nameColumn,dateColumn,PrixColumn,adresseColumn,MailColumn);
     return table;       
    }
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
    

    
    Button btn;
    Commande A = new Commande();
    
    //imprime facture
        private void addButtonImpToTable() {
 
    }
        

  
    

    
}
