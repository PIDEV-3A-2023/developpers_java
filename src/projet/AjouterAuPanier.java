package projet;

import Utiles.Maconnexion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.swing.*;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Objects;

public class AjouterAuPanier {


    @FXML
    private ImageView ProduitImage;

    @FXML
    private Label ProduitsDetails;

    @FXML
    private Label ProduitBrand;

    @FXML
    private Button AjouterAuPanierButton;

    @FXML
    private Label PrixTag;

    
@FXML
    private void AjouterAuPanierAction(ActionEvent event){

        if(event.getSource() == AjouterAuPanierButton){
            AjouterAuPanier();
            JOptionPane.showMessageDialog(null,"Ajouté au panier");
            System.out.println(PrixTag.getText());
            System.out.println(ProduitsDetails.getText());
        }
    }

    Connection connectDB = null;

    public void AjouterAuPanier() {

        Maconnexion connectNow = new Maconnexion();
        connectDB = connectNow.getConnection();

        String name = ProduitsDetails.getText();
        String By = ProduitBrand.getText();
        int prix = Integer.parseInt(PrixTag.getText());

        String insertFields="INSERT INTO panier(ProdName, ProdBy,Prix) VALUES ('";
        String insertValues= name + "','" + By + "','" + prix + "')";
        String insertToTable= insertFields+insertValues;

        // inside the try , the actual updating / writing process is being executed.

        try{
            //Creating an SQL statement
            Statement statement = connectDB.createStatement();

            //Executes the given SQL statement, which may be an INSERT, UPDATE, or DELETE statement
            // Passing the string containing The data I want to insert into my DB
            statement.executeUpdate(insertToTable);
            System.out.println("produit ajouté au panier !");
        }
        catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

 
}
