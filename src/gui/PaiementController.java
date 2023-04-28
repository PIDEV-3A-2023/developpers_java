
package gui;

import entites.Commande;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import services.ServiceCommande;

/**
 *
 * @author oumeima
 */
public class PaiementController {
    
    Connection connection = null;
    Statement statemant = null;

    private Label label;
    
    @FXML
    private DatePicker datec;
    @FXML
    private TextField tfprix_commande;
    @FXML
    private TextField tfnomClient;
    @FXML
    private TableColumn<Commande,Integer> idt;
    @FXML
    private TableColumn<Commande,Date> datet;
    @FXML
    private TableColumn<Commande,Float> prixt;
    @FXML
    private TableColumn<Commande,String> etatt;
    @FXML
    private TableView<Commande> tablec;
    @FXML
    private TextField idsup;
    @FXML
    private TextField tfsearch;
    private int vartri=0;
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    


    @FXML
    private void searchkey(KeyEvent event) {
        ServiceCommande sc=new ServiceCommande();
        ObservableList<Commande> commandes =sc.search(tfsearch.getText());
         idt.setCellValueFactory(new PropertyValueFactory<Commande,Integer>("id"));
            datet.setCellValueFactory(new PropertyValueFactory<Commande,Date>("date"));
            prixt.setCellValueFactory(new PropertyValueFactory<Commande,Float>("prix"));
            etatt.setCellValueFactory(new PropertyValueFactory<Commande,String>("etat"));
            tablec.setItems(commandes);
    }

    
    
        
    //redirection vers le magasin
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
    


    
    
    
    
    
    
    
    
}
