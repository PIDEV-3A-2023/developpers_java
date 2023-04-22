/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXDatePicker;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import services.ServiceCat ;
import entites.Categorie ;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author khadija
 */
public class AjoutCategorieController implements Initializable {

    @FXML
    private TextField NomTF;
    @FXML
    private TextArea DescTF;
    @FXML
    private Button ajoubtn;
 ServiceCat sc = new ServiceCat() ;
    @FXML
    private JFXDatePicker DateC;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AjouterCatHandle(ActionEvent event) {
         String Nom  = NomTF.getText();
         String Description = DescTF.getText();
          LocalDate datec = DateC.getValue();
       ZoneId defaultZoneId = ZoneId.systemDefault();
         Date date = (Date) Date.from(datec.atStartOfDay(defaultZoneId).toInstant());
          if (Nom.isEmpty()) {
        showAlert("nom obligatoire", "nom doit être non vide");
          }
          else if (Description.isEmpty()) {
               showAlert("description  obligatoire", "description doit être non vide");
          }
     else {
       
            Categorie Cat= new Categorie (Nom,Description,date  );
            sc.ajouterCat(Cat);
            showAlert("categorie   ajouté", "Categorie ajouté avec succès");
    }
    }
      private void showAlert(String title, String message) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}

    @FXML
    private void ListeCat(ActionEvent event) {
         try {
                  Parent   root = FXMLLoader.load(getClass().getResource("AffichageCategorie.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                };
    }
    
}
