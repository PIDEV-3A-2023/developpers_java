/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import entites.Categorie ; 
import java.io.IOException;
import java.util.Optional;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import services.ServiceCat ;

/**
 * FXML Controller class
 *
 * @author khadija
 */
public class AffichageCategorieController implements Initializable {

    @FXML
    private TableColumn<String, Integer> ColID;
    @FXML
    private TableColumn<String, Date> ColDate;
    @FXML
    private TableColumn<String, String> ColNom;
    @FXML
    private TableColumn<String, String> ColDesc;
    @FXML
    private TableView<Categorie> tableView;
    
      private TableColumn<Categorie, Void> colModifBtn;
    private TableColumn<Categorie, Void> colSuppBtn;
    private TableColumn<Categorie, Void> colExpBtn;
    ServiceCat C = new  ServiceCat();
     ObservableList<Categorie> obList;
    public static Categorie cat ; 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colSuppBtn = new TableColumn<>("Supprimer");
        tableView.getColumns().add(colSuppBtn);

        colModifBtn = new TableColumn<>("Modifier");
        tableView.getColumns().add(colModifBtn);


        C = new ServiceCat();
        obList = C.affichageCat();
        ColID.setCellValueFactory(new PropertyValueFactory<>("id"));
        ColNom.setCellValueFactory(new PropertyValueFactory<>("nom_c"));
   ColDesc.setCellValueFactory(new PropertyValueFactory<>("description_cat"));
        ColDate.setCellValueFactory(new PropertyValueFactory<>("date_creation"));
       
                

        addButtonModifToTable();
        addButtonDeleteToTable();
     

        tableView.setItems(obList);

        addButtonModifToTable();

    }    
     
    Button btn;
  Categorie ct = new Categorie();

    private void addButtonModifToTable() {
         Callback<TableColumn<Categorie , Void>, TableCell<Categorie, Void>> cellFactory = new Callback<TableColumn<Categorie, Void>, TableCell<Categorie, Void>>() {
            @Override
            public TableCell<Categorie, Void> call(final TableColumn<Categorie, Void> param) {

                final TableCell<Categorie, Void> cell = new TableCell<Categorie, Void>() {

                    {
                        btn = new Button("Modifier");
                        btn.setOnAction((ActionEvent event) -> {
                            try {
                   ct = tableView.getSelectionModel().getSelectedItem();
                   cat = ct ;
                      FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/ModifierCategorie.fxml"));
                      Parent root = loader.load();
                   ModifierCategorieController controller = loader.getController();
                             controller.setNom_c(ct.getNom_c());
                                controller.setDescription_cat(ct.getDescription_cat());
                     // controller.setDate_creation(ct.getDate_creation());
                                controller.setId(ct.getId());
                              
                             
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(new Scene(root));
                     stage.show();
                 } catch (Exception e) {
                      e.printStackTrace();
                      }
 });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        colModifBtn.setCellFactory(cellFactory);
    }
    Button btnSupprimer;
    private Label label;

    private void showConfirmation(Categorie cat) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Suppression");
        alert.setHeaderText("Voulez-vous vraiment supprimer ce rendez vous ?");
        alert.setContentText("");

        Optional<ButtonType> option = alert.showAndWait();

        if (option.get() == null) {
            this.label.setText("pas selection!");
        } else if (option.get() == ButtonType.OK) {
            System.out.println(" iddd=" + cat.getId());
            C.supprimercat(cat);
            obList.clear();
            
        } else if (option.get() == ButtonType.CANCEL) {
            this.label.setText("Exit!");
        } else {
            this.label.setText("-");
        }
    }

    private void addButtonDeleteToTable() {
       Callback<TableColumn<Categorie, Void>, TableCell<Categorie, Void>> cellFactory = new Callback<TableColumn<Categorie, Void>, TableCell<Categorie, Void>>() {
            @Override
            public TableCell<Categorie, Void> call(final TableColumn<Categorie, Void> param) {

                final TableCell<Categorie, Void> cell = new TableCell<Categorie, Void>() {

                    {
                        btnSupprimer = new Button("Supprimer");
                        btnSupprimer = new Button("Supprimer");
                        btnSupprimer.setOnAction((ActionEvent event) -> {

                            ct = tableView.getSelectionModel().getSelectedItem();
                         showConfirmation(ct);
                          try {
                  Parent   root = FXMLLoader.load(getClass().getResource("AffichageCategorie.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                };
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btnSupprimer);
                        }
                    }
                };
                return cell;
            }
        };
        colSuppBtn.setCellFactory(cellFactory);
    }

    @FXML
    private void AjouterCat(ActionEvent event) {
         try {
                  Parent   root = FXMLLoader.load(getClass().getResource("AjoutCategorie.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                };
    }

    @FXML
    private void listProd(ActionEvent event) {
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
}
