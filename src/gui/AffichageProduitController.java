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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import entites.Produit;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Optional;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
import services.ServiceProduit;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author khadija
 */
public class AffichageProduitController implements Initializable {

   
    @FXML
    private TableView<Produit> tableView;
    @FXML
    private TableColumn<String, String> colNom;
    @FXML
    private TableColumn<String, Double> colPrix;
  @FXML
    private TableColumn<Produit, ImageView> ColImage;
    
    
    private TableColumn<Produit, Void> colModifBtn;
    private TableColumn<Produit, Void> colSuppBtn;
    private TableColumn<Produit, Void> colExpBtn;
    ServiceProduit a = new ServiceProduit();
    public static Produit pr ; 
    @FXML
    private TableColumn<String, Integer> ColID;
 ObservableList<Produit> obList;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
     colSuppBtn = new TableColumn<>("Supprimer");
        tableView.getColumns().add(colSuppBtn);

        colModifBtn = new TableColumn<>("Modifier");
        tableView.getColumns().add(colModifBtn);

//
    a = new ServiceProduit();
        obList = a.affichageProduit();
//        
         addButtonModifToTable();
        addButtonDeleteToTable();
        
       
      
        
         ColID.setCellValueFactory(new PropertyValueFactory<>("id"));
          colNom.setCellValueFactory(new PropertyValueFactory<>("nom_p"));
        colPrix.setCellValueFactory(new PropertyValueFactory<>("prix_p"));
        
         ColImage.setCellValueFactory(new PropertyValueFactory<>("image_p"));
      
        // First, create a single ImageView object to reuse for each cell
ImageView imageView = new ImageView();
imageView.setFitWidth(50);
imageView.setFitHeight(50);


// Then, set up the cell value factory to return the ImageView for each cell



// Then, set up the cell value factory to return the ImageView for each cell

 
  String destDir = "file:///C:/xampp/htdocs/img/";
ColImage.setCellValueFactory(cellData -> {
    Produit produit = cellData.getValue();
    String imagePath = produit.getImage_p();
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


    // Load the data from the CompetitionService into the TableView
   
     tableView.setItems(obList);

    
    }    

    
    Button btn;
    Produit A = new Produit();
    public void addButtonModifToTable() {
        Callback<TableColumn<Produit, Void>, TableCell<Produit, Void>> cellFactory = new Callback<TableColumn<Produit, Void>, TableCell<Produit, Void>>() {
            @Override
            public TableCell<Produit, Void> call(final TableColumn<Produit, Void> param) {

                final TableCell<Produit, Void> cell = new TableCell<Produit, Void>() {

                    {
                        btn = new Button("Modifier");
                        btn.setOnAction((ActionEvent event) -> {
                            try {
                                A = tableView.getSelectionModel().getSelectedItem();//
                                System.out.println("hello");
                                System.out.println("DATA ="+A);
                                pr = new Produit (A.getId(), A.getNom_p(),A.getImage_p(),A.getDescription_p(),A.getPrix_p() ,A.getStock(),A.getIdcat_p_id()) ;
                                  FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/ModifierProduit.fxml"));
                                Parent root = loader.load();
                                ModifierProduitController controller = loader.getController();
                                controller.setNom(A.getNom_p());
                                controller.setPrix(A.getPrix_p());
                                controller.setId(A.getId());
                                controller.setStock(A.getStock());
                                controller.setDescription(A.getDescription_p());
                                controller.setImage_p(A.getImage_p());
                                
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

    private void showConfirmation(Produit pr) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Suppression");
        alert.setHeaderText("Voulez-vous vraiment supprimer ce produit ?");
        alert.setContentText("");

        Optional<ButtonType> option = alert.showAndWait();

        if (option.get() == null) {
            this.label.setText("pas selection!");
        } else if (option.get() == ButtonType.OK) {
            System.out.println(" iddd=" + pr.getId());
            a.supprimerProduit(pr);
            obList.clear();
            
        } else if (option.get() == ButtonType.CANCEL) {
            this.label.setText("Exit!");
        } else {
            this.label.setText("-");
        }
    }

    public void addButtonDeleteToTable() {
        Callback<TableColumn<Produit, Void>, TableCell<Produit, Void>> cellFactory = new Callback<TableColumn<Produit, Void>, TableCell<Produit, Void>>() {
            @Override
            public TableCell<Produit, Void> call(final TableColumn<Produit, Void> param) {

                final TableCell<Produit, Void> cell = new TableCell<Produit, Void>() {

                    {
                        btnSupprimer = new Button("Supprimer");
                        btnSupprimer = new Button("Supprimer");
                        btnSupprimer.setOnAction((ActionEvent event) -> {

                            A = tableView.getSelectionModel().getSelectedItem();
                         pr=A;
                          showConfirmation(pr);
                           try {
                  Parent   root = FXMLLoader.load(getClass().getResource("AffichageProduit.fxml"));
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
    private void ajouterP(ActionEvent event) {
          try {
                  Parent   root = FXMLLoader.load(getClass().getResource("AjoutProduit.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                };
    }

    @FXML
    private void boutiqueShow(ActionEvent event) {
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

  


  

    }    
    

