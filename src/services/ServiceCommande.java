/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import entites.Commande;
import entites.Panier;
import entites.Produit ;
import java.util.List;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.DB;
import java.time.LocalDate;
import java.time.ZoneId;
/**
 *
 * @author oumeima
 */

public class ServiceCommande implements IServiceCommande<Produit>  {
    
ObservableList<Produit>obListProd = FXCollections.observableArrayList();
      Connection conn;
    public ServiceCommande() {
        conn = DB.getInstance().getConnection();
    }
    
    @Override
    public ObservableList<Commande> AfficherCommande(){
    String req="SELECT * FROM commande c ";
        // List<Commande> listcom = new ArrayList<>();
        ObservableList<Commande> listcom = FXCollections.observableArrayList();

        try{
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(req);
            
            while(rs.next()) {
                int id= rs.getInt("id");
                Date date_commande = rs.getDate("date_commande");
                String nom_client = rs.getString("nom_client");
                float prix_total = rs.getFloat("prix_total");
                String mail_client = rs.getString("mail_client");
                String adresse_livraison = rs.getString("adresse_livraison");
                String status = rs.getString("status");
                
                Commande c = new Commande(id,date_commande,nom_client,prix_total,mail_client,adresse_livraison,status); 
                listcom.add(c);
            }   

        }catch(Exception ex) {
            System.out.println("exception ="+ex.getMessage() );
        }
        return listcom ;
     
    }
        
    public void viderPanier(){
        String  req ="INSERT INTO panier (id, date_ajout) SELECT COALESCE(MAX(id), 0) + 1, NOW() FROM panier";
       PreparedStatement stm;
        try {
            stm = conn.prepareStatement(req);  
                    stm.execute();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void passerCommande(int id_pan, String nom,Double prix,String adresse,String mail){
           String  req ="INSERT INTO commande(panier_id,date_commande ,nom_client ,prix_total ,mail_client ,adresse_livraison , status )values(?,?,?,?,?,?,'En cours' )";
        // Get the current date
        LocalDate currentDate = LocalDate.now();

        // Convert LocalDate to java.sql.Date
        java.sql.Date sqlDate = java.sql.Date.valueOf(currentDate);
       PreparedStatement stm;
        try {
            stm = conn.prepareStatement(req);
                    stm.setInt(1, id_pan);
                    stm.setDate(2,sqlDate);           
                    stm.setString(3, nom);
                    stm.setDouble(4, prix);
                    stm.setString(5, mail);
                    stm.setString(6, adresse);
                    stm.executeUpdate();
                    viderPanier() ;
                    System.out.println("Commande passée avec succès");
                    
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }    
    @Override
    public int GetLastPanier() {
        // Get the maximum id value from the result set
              int maxId = 0;
        try{
            Statement stmt = conn.createStatement();

              // Execute the query and retrieve the result set
              ResultSet rs = stmt.executeQuery("SELECT Max(id) FROM panier");

              
              if (rs.next()) {
                  maxId = rs.getInt(1);
              }
        }catch(Exception ex1) {
            System.out.println("exception ="+ex1.getMessage() );
        }
        return maxId ;
              
    }

    public ObservableList<Commande> search(String text) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        
    
    
}
