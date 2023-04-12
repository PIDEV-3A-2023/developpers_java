/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXDatePicker;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import entites.Categorie; 
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import services.ServiceCat ; 

/**
 * FXML Controller class
 *
 * @author khadija
 */
public class ModifierCategorieController implements Initializable {

    @FXML
    private TextField NomTF;
    @FXML
    private TextArea DescTF;
    @FXML
    private JFXDatePicker DateC;
    @FXML
    private Button modifbtn;
    @FXML
    private AnchorPane anchorPaneE1;

    private String Nom ; 
    private String Description ; 
    private int id ; 
    private Categorie cat = gui.AffichageCategorieController.cat ; 
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       System.out.println("heree");
           System.out.println(cat);
           FXMLLoader loader = new FXMLLoader();

            loader.setLocation(getClass().getResource("/gui/AffichageCategorie.fxml"));
            Stage prStage = new Stage();

            Parent root;
        
        try {
                    root = loader.load();
                Scene scene = new Scene(root);
                prStage.setScene(scene);
                AffichageCategorieController irc = loader.getController();
                ServiceCat sc = new ServiceCat();

                id = irc.ct.getId();
                 

            } catch (IOException ex) {
            }
    }  
           int idS;

    public void setId(int id) {

        idS = id;
        System.out.println("her id " + idS);
    }

    @FXML
    private void ModifierCatHandle(ActionEvent event) {
         LocalDate datec = DateC.getValue();
           ZoneId defaultZoneId = ZoneId.systemDefault();
         Date date = (Date) Date.from(datec.atStartOfDay(defaultZoneId).toInstant());
          try {

            ServiceCat sc = new ServiceCat();

            Categorie c = new Categorie();
            c.setNom_c(NomTF.getText());
            c.setDescription_cat(DescTF.getText());
           c.setDate_creation(Date.from(DateC.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
         
             System.out.println("idddd"+idS);

            c.setId(idS);

            sc.modifierCat(c);
           
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("succes");
            alert.setHeaderText("rendez vous modifié avec succés !!!");
            alert.setContentText("Validé !");
            alert.showAndWait();

             FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/AffichageCategorie.fxml"));
                                Parent root = loader.load();
                                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                stage.setScene(new Scene(root));
                                stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     public void setNom_c(String Nom) {
        NomTF.setText(Nom);
    }
    public void setDescription_cat(String Description) {
        DescTF.setText(Description);
    }

    public void setDate_rdv(Date date) {
         LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    DateC.setValue(localDate);
    }

  
}
