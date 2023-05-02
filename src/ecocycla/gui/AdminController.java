/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecocycla.gui;

import ecocycla.entities.User;
import ecocycla.services.UserCrud;
import ecocycla.utils.MyConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author KHALED
 */
public class AdminController implements Initializable {

    @FXML
    private TableColumn<?, ?> nom;
    @FXML
    private TableColumn<?, ?> prenom;
    @FXML
    private TableColumn<?, ?> email;
    @FXML
    private TableColumn<?, ?> datenaissance;
    @FXML
    private TableColumn<?, ?> cin;
    @FXML
    private TableColumn<?, ?> region;
    @FXML
    private TableColumn<?, ?> ville;
    @FXML
    private TableColumn<?, ?> adresse;
    @FXML
    private Button ajouter;
    @FXML
    private Button modifier;
    @FXML
    private Button supprimer;
    @FXML
    private TableView<User> tableviewUser;
    @FXML
    private Button fxrefrech;
    @FXML
    private TextField searchField;
    @FXML
    private Button searchbtn;

    /**
     * Initializes the controller class.
     */
    
//    
//    @Override
//    public void initialize(URL url, ResourceBundle rb) {
//        // TODO
//        showRec();
//    }
    
    
    
    @Override
public void initialize(URL url, ResourceBundle rb) {
    showRec();
    tableviewUser.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE); 
    tableviewUser.getSelectionModel().setCellSelectionEnabled(true);
    getUserList("");
}


    @FXML
    private void btnmodifier(ActionEvent event) {
        User user = (User) tableviewUser.getSelectionModel().getSelectedItem(); //recuperation du utilisateur selectionné 
                            FXMLLoader loader = new FXMLLoader ();//creation de FXMLLoader 
                            loader.setLocation(getClass().getResource("Modifier.fxml")); //emplacement du fichier fxml 
                            try {
                                loader.load();
                            } catch (Exception ex) {
                               System.err.println(ex.getMessage());
                            }
                            
                           ModifierController muc = loader.getController(); //recuperation deu controller de modification 
                           //mrc.setUpdate(true);
                           muc.setTextFields(user); //bech taabili les text field eli hachty bihom 
                           //tkhajlk fenetre mtaa modification 
                            Parent parent = loader.getRoot(); 
                            Stage stage = new Stage(); //affichage de la fenetre 
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();
                            showRec();

    }

    public ObservableList<User> getUserList() { //methode affichage mtaa liste utilisateur  fe table view 
        Connection conn = MyConnection.getInstance().getConnection();
        ObservableList<User> UserList = FXCollections.observableArrayList();
        try {
            String query2 = "SELECT * FROM  user ";
            PreparedStatement smt = conn.prepareStatement(query2);
            User user;
            ResultSet rs = smt.executeQuery();
            while (rs.next()) { //parcour les enregistrement retoune par la requette sql 
                user = new User(rs.getInt("id"), rs.getString("email"), rs.getString("roles"), rs.getString("nom"), rs.getString("prenom"), LocalDate.MIN, rs.getInt("cin"), rs.getString("region"), rs.getString("region"), rs.getString("adresse"));
                UserList.add(user);//ajout utilisateur fe liste 
            }
            System.out.println(UserList);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return UserList;

    }

    @FXML
    private void btnajouter(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader();//creation de FXMLLoader 
        loader.setLocation(getClass().getResource("Ajout.fxml")); //emplacement du fichier fxml 
        try {
            loader.load();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }

        AjoutController muc = loader.getController(); //recuperation deu controller de modification 
        //mrc.setUpdate(true);

        Parent parent = loader.getRoot();
        Stage stage = new Stage(); //affichage de la fenetre 
        stage.setScene(new Scene(parent));
        stage.initStyle(StageStyle.UTILITY);
        stage.show();
        //showRec();
    }

    @FXML
    private void btnsupprimer(ActionEvent event) {
        UserCrud u = new UserCrud();

        User user = (User) tableviewUser.getSelectionModel().getSelectedItem();

        u.supprimerUtilisateur(user);
        //refresh(); 
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("ecocycla  :: Succes Message ");
        alert.setHeaderText(null);
        alert.setContentText("Utilisateur supprimé");
        alert.showAndWait();
    }

    @FXML
    private void showRec() {
        ObservableList<User> list = getUserList();
        //id.setCellValueFactory(new PropertyValueFactory<>("id"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        cin.setCellValueFactory(new PropertyValueFactory<>("cin"));
        datenaissance.setCellValueFactory(new PropertyValueFactory<>("datedenaissance"));

        region.setCellValueFactory(new PropertyValueFactory<>("region"));
        adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        ville.setCellValueFactory(new PropertyValueFactory<>("ville"));

        tableviewUser.setItems(list);
    }
    
//    public ObservableList<User> getUserList(String search) { // add search parameter
//    Connection conn = MyConnection.getInstance().getConnection();
//    ObservableList<User> UserList = FXCollections.observableArrayList();
//    try {
//        String query2 = "SELECT * FROM user WHERE nom LIKE ? OR prenom LIKE ?"; // modify query
//        PreparedStatement smt = conn.prepareStatement(query2);
//        smt.setString(1, "%" + search + "%"); // set search term
//        smt.setString(2, "%" + search + "%"); // set search term
//        User user;
//        ResultSet rs = smt.executeQuery();
//        while (rs.next()) { //parcour les enregistrement retoune par la requette sql 
//            user = new User(rs.getInt("id"), rs.getString("email"), rs.getString("roles"), rs.getString("nom"), rs.getString("prenom"), LocalDate.MIN, rs.getInt("cin"), rs.getString("region"), rs.getString("region"), rs.getString("adresse"));
//            UserList.add(user);//ajout utilisateur fe liste 
//        }
//        System.out.println(UserList);
//    } catch (SQLException ex) {
//        System.out.println(ex.getMessage());
//    }
//
//    return UserList;
//
//}
     
    public ObservableList<User> getUserList(String search) {
    ObservableList<User> userList = FXCollections.observableArrayList();
        UserCrud us = new UserCrud(); 
        List<User> allUsers = us.afficherUtilisateurs(); // méthode qui retourne tous les utilisateurs dans une liste

        userList = allUsers.stream()
                .filter(user -> user.getNom().contains(search) || user.getPrenom().contains(search))
                .collect(Collectors.toCollection(FXCollections::observableArrayList));

        System.out.println(userList);
        return userList;
    }

     private void refresh() //mettre a jour du continue du tableView 
    {
       ObservableList<User> list = getUserList();
         //id.setCellValueFactory(new PropertyValueFactory<>("id"));
         cin.setCellValueFactory(new PropertyValueFactory<>("CIN"));
         email.setCellValueFactory(new PropertyValueFactory<>("email"));
         datenaissance.setCellValueFactory(new PropertyValueFactory<>("datedenaissance"));
         nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
         prenom.setCellValueFactory(new PropertyValueFactory<>("prennom"));
         adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
         //roles.setCellValueFactory(new PropertyValueFactory<>("roles"));

      
         tableviewUser.setItems(list);
       
    }

   @FXML
private void btnSearch(ActionEvent event) {
    ObservableList<User> list = getUserList(searchField.getText());
    email.setCellValueFactory(new PropertyValueFactory<>("email"));
    nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
    prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
    cin.setCellValueFactory(new PropertyValueFactory<>("cin"));
    datenaissance.setCellValueFactory(new PropertyValueFactory<>("datedenaissance"));
    region.setCellValueFactory(new PropertyValueFactory<>("region"));
    adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
    ville.setCellValueFactory(new PropertyValueFactory<>("ville"));
    tableviewUser.setItems(list);
}

}
