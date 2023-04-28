/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.itextpdf.text.DocumentException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import entites.Produit;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.Optional;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.util.Callback;
import services.ServiceProduit;
import javafx.stage.Stage;
import utils.PDFProduct;

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
    
    
    private TableColumn<Produit, Void> colModifBtn;
    private TableColumn<Produit, Void> colSuppBtn;
    private TableColumn<Produit, Void> colExpBtn;
    ServiceProduit a = new ServiceProduit();
    public static Produit pr ; 
    @FXML
    private TableColumn<String, Integer> ColID;
 ObservableList<Produit> obList;
    @FXML
    private TextField recherche;
    @FXML
    private Button triP;
    @FXML
    private Button exportPDF;
    @FXML
    private TableColumn<Produit, ImageView> ColQr;
    @FXML
    private TableColumn<Produit, ImageView> ColImage;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficherQRCode(ColQr);
        
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
        
      String destDir = "file:///C:/xampp/htdocs/img/";
    ColImage.setCellValueFactory(cellData -> {
        Produit produit = cellData.getValue();
        String imagePath = produit.getImage_p();
        if (imagePath != null) {
            try {
                // Create a new ImageView instance for each cell
                ImageView imageView = new ImageView();
                imageView.setFitWidth(150);
                imageView.setFitHeight(150);

                Image image = new Image(destDir+imagePath);
                if (image.isError()) {
                    System.err.println("Error loading image from URL: " + imagePath);
                    return null;
                }
                // Update the image property of the ImageView
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
    
//    ColQr.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Produit, ImageView>, ObservableValue<ImageView>>() {
//    @Override
//    public ObservableValue<ImageView> call(TableColumn.CellDataFeatures<Produit, ImageView> param) {
//        Produit produit = param.getValue();
//
//        try {
//            // Generate the QR code image and save it to a file
//           
//            // Load the saved image file
//            
//            File imageFile = new File(produit.getImage_qr_code());
//            Image image = new Image(imageFile.toURI().toString());
//
//            // Create an ImageView object with the loaded image
//            ImageView imageView = new ImageView(image);
//            imageView.setFitWidth(100);
//            imageView.setFitHeight(100);
//
//            // Return the ImageView as the value of the qrcode_image column
//            return new SimpleObjectProperty<ImageView>(imageView);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//});

      addButtonModifToTable();
        addButtonDeleteToTable();
        System.out.println("data ==="+obList);
     tableView.setItems(obList);
     addButtonModifToTable();

    
    }    
    private void afficherQRCode(TableColumn<Produit, ImageView> column) {
    column.setCellFactory(col -> new TableCell<Produit, ImageView>() {
        private final ImageView imageView = new ImageView();

        @Override
        protected void updateItem(ImageView item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setGraphic(null);
            } else {
                Produit produit = getTableView().getItems().get(getIndex());

                try {
                    // Charger l'image QR code à partir du dossier htdocs
                    String fileName = produit.getImage_qr_code();
                    File file = new File("C:/xampp/htdocs/img/" + fileName);
                    Image image = new Image(file.toURI().toString(), 150, 150, false, true);
                    imageView.setImage(image);
                    setGraphic(imageView);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    });
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

    @FXML
    private void RechercheHandle(KeyEvent event) {
         FilteredList<Produit> filteredList = new FilteredList<>(obList, b -> true);

        recherche.textProperty().addListener((observable, oldValue, newValue) -> {

            if (recherche.getText().isEmpty()) {

                addButtonModifToTable();
                addButtonDeleteToTable();

            }
            filteredList.setPredicate(reclamation -> {
                if (newValue == null || newValue.isEmpty()) {
                    btn = new Button("Modifier");
                    btnSupprimer = new Button("Supprimer");

                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
 if (String.valueOf(reclamation.getNom_p()).toLowerCase().indexOf(lowerCaseFilter) != -1) {

                    return true;
                } else if (String.valueOf(reclamation.getPrix_p()).toLowerCase().indexOf(lowerCaseFilter) != -1) {

                    return true;
                } else if (String.valueOf(reclamation.getStock()).toLowerCase().indexOf(lowerCaseFilter) != -1) {

                    return true;
                    
                    
                }
                else {
                    btn = new Button("Modifier");
                    btnSupprimer = new Button("Supprimer");
                    return false;
                }

            });

        });
        SortedList<Produit> sortedData = new SortedList<>(filteredList);
        sortedData.comparatorProperty().bind(tableView.comparatorProperty());

        tableView.setItems(sortedData);
    }

    @FXML
    private void triePrix(ActionEvent event) {
            a = new ServiceProduit();
        obList = a.affichageProduitTrieer ();
      
        ColID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom_p"));
        colPrix.setCellValueFactory(new PropertyValueFactory<>("prix_p"));
        ColImage.setCellValueFactory(new PropertyValueFactory<>("image_p"));
         afficherQRCode(ColQr);
                

        addButtonModifToTable();
        addButtonDeleteToTable();
         // First, create a single ImageView object to reuse for each cell
//ImageView imageView = new ImageView();
//imageView.setFitWidth(50);
//imageView.setFitHeight(50);


// Then, set up the cell value factory to return the ImageView for each cell



// Then, set up the cell value factory to return the ImageView for each cell

 String destDir = "file:///C:/xampp/htdocs/img/";
    ColImage.setCellValueFactory(cellData -> {
        Produit produit = cellData.getValue();
        String imagePath = produit.getImage_p();
        if (imagePath != null) {
            try {
                // Create a new ImageView instance for each cell
                ImageView imageView = new ImageView();
                imageView.setFitWidth(150);
                imageView.setFitHeight(150);

                Image image = new Image(destDir+imagePath);
                if (image.isError()) {
                    System.err.println("Error loading image from URL: " + imagePath);
                    return null;
                }
                // Update the image property of the ImageView
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

        addButtonModifToTable();
         addButtonDeleteToTable();
    }

  

    @FXML
    private void exportPDFAction(ActionEvent event) {
         try {
            PDFProduct pdf = new PDFProduct();
            pdf.pdfGeneration();
            System.out.println("done");
        } catch (DocumentException ex) {
            System.out.println(ex.getMessage());
        } catch (MalformedURLException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } catch (URISyntaxException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void affichageFront(ActionEvent event) {
        try {
                  Parent   root = FXMLLoader.load(getClass().getResource("ListeFront.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                };
    }

   

  


  

    }    
    

