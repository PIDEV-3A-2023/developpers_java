
package projet;

import Entities.Commande;
import Service.ServiceCommande;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;


public class CommandeController implements Initializable {
    
    private Label label;
    
    @FXML
    private DatePicker datec;
    @FXML
    private TextField tfprix;
    @FXML
    private TextField tfetat;
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    

 @FXML
    private void AjouterCommande(ActionEvent event) {
      ServiceCommande sc = new ServiceCommande();
      Commande c = new Commande();
      c.setDate(java.sql.Date.valueOf(datec.getValue()));
      c.setPrix(Float.parseFloat(tfprix.getText()));
      c.setEtat(tfetat.getText());
      
      sc.AjouterCommande(c);
      this.AfficherCommande(event);
      
      
      Task<Void> task = new Task<Void>() {
            @Override
            public Void call() throws Exception {
                Thread.sleep(0);
                return null ;
            }
        };

        task.setOnSucceeded(event2 -> {
            
            //IL FAUT RECUBERER LE mail de utili
            sc.sendmail("aziz.barguellil@esprit.tn", c.getPrix());
            
        });

        new Thread(task).run();
        
        System.out.println("mail en cours");
      
    }
    @FXML
    private void AfficherCommande(ActionEvent event) {
        ServiceCommande sc = new ServiceCommande();
        ObservableList<Commande> commandes =sc.AfficherCommande();
            
            idt.setCellValueFactory(new PropertyValueFactory<Commande,Integer>("id"));
            datet.setCellValueFactory(new PropertyValueFactory<Commande,Date>("date"));
            prixt.setCellValueFactory(new PropertyValueFactory<Commande,Float>("prix"));
            etatt.setCellValueFactory(new PropertyValueFactory<Commande,String>("etat"));
            tablec.setItems(commandes);
       
    }

    @FXML
    private void selectionner(MouseEvent event) {
         
        Commande c =tablec.getSelectionModel().getSelectedItem();
        java.sql.Date dateget=convertUtilToSql(c.getDate());
        idsup.setText(String.valueOf(c.getId()));
        datec.setValue(dateget.toLocalDate());
        tfprix.setText(String.valueOf(c.getPrix()));
        tfetat.setText(c.getEtat());
    }

    @FXML
    private void SupprimerCommande(ActionEvent event) {
        ServiceCommande sc = new ServiceCommande();
        sc.supprimercommande(Integer.parseInt(idsup.getText()));
        this.AfficherCommande(event);
        
    }
    private static java.sql.Date convertUtilToSql(java.util.Date uDate) {
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;
        
    }

    @FXML
    private void ModifierCommande(ActionEvent event) {
        ServiceCommande sc = new ServiceCommande();
         Commande c = new Commande();
         c.setId(Integer.parseInt(idsup.getText()));
         c.setDate(java.sql.Date.valueOf(datec.getValue()));
         c.setPrix(Float.parseFloat(tfprix.getText()));
         c.setEtat(tfetat.getText());
        sc.ModifierCommande(c);
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

    @FXML
    private void tributton(MouseEvent event) {
        ServiceCommande sc=new ServiceCommande();
        ObservableList<Commande> commandes;
        if(vartri==1){
            vartri=0;
           commandes=sc.triasc();
           idt.setCellValueFactory(new PropertyValueFactory<Commande,Integer>("id"));
            datet.setCellValueFactory(new PropertyValueFactory<Commande,Date>("date"));
            prixt.setCellValueFactory(new PropertyValueFactory<Commande,Float>("prix"));
            etatt.setCellValueFactory(new PropertyValueFactory<Commande,String>("etat"));
            tablec.setItems(commandes);
           
        }
        else{
            vartri=1;
            commandes=sc.triadsc();
            idt.setCellValueFactory(new PropertyValueFactory<Commande,Integer>("id"));
            datet.setCellValueFactory(new PropertyValueFactory<Commande,Date>("date"));
            prixt.setCellValueFactory(new PropertyValueFactory<Commande,Float>("prix"));
            etatt.setCellValueFactory(new PropertyValueFactory<Commande,String>("etat"));
            tablec.setItems(commandes);
            
        }
    
    }
   
    
}
