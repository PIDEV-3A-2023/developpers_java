/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import entites.Produit ;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import utils.DB;



public class ServiceProduit implements IService<Produit>  {
    
ObservableList<Produit>obListProd = FXCollections.observableArrayList();
      Connection conn;
    public ServiceProduit() {
        conn = DB.getInstance().getConnection();
    }
    
    
    @Override
    public void ajouterProduit(Produit p) {
       String  req ="INSERT INTO produit(nom_p,image_p,image_qr_code,description_p,prix_p,stock,idcat_p_id )values(?,?,?,?,?,?,?)";

       PreparedStatement stm;
        try {
            stm = conn.prepareStatement(req);
                  stm.setString(1, p.getNom_p());
                    stm.setString(2, p.getImage_p());           
                    stm.setString(3, p.getImage_qr_code());

        stm.setString(4, p.getDescription_p());
    stm.setDouble(5, p.getPrix_p());
    stm.setInt(6, p.getStock());
        stm.setInt(7, p.getIdcat_p_id());


        
            stm.executeUpdate();
                    System.out.println("Produit ajoutée");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }
      @Override
    public ObservableList<Produit> affichageProduitTrieer() {
         String req= "SELECT * FROM produit order by prix_p DESC";
         List<Produit>listProd = new ArrayList<>();
        
        
        try{
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(req);
            
            while(rs.next()) {
                int id= rs.getInt("id");
                String nom_p = rs.getString("nom_p");
                String image_p = rs.getString("image_p");
                double prix_p = rs.getDouble("prix_p");
                int stock = rs.getInt("stock");
                String description = rs.getString("description_p");
                int idcat_p_id = rs.getInt("idcat_p_id");
                
                Produit p = new Produit(id,nom_p,image_p,"null",description,prix_p,stock,idcat_p_id);
              
                obListProd.add(p);

              
            }   
            
            
            
        }catch(Exception ex) {
            System.out.println("exception ="+ex.getMessage() );
        }
        return obListProd ;
    }

    @Override
   public ObservableList<Produit> affichageProduit() {
           String req="SELECT * FROM produit P  JOIN categorie_p C ON p.idcat_p_id=c.id";
         List<Produit>listProd = new ArrayList<>();
        
        
        try{
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(req);
            
            while(rs.next()) {
                int id= rs.getInt("id");
                String nom_p = rs.getString("nom_p");
                String image_p = rs.getString("image_p");
                double prix_p = rs.getDouble("prix_p");
                int stock = rs.getInt("stock");
                String description = rs.getString("description_p");
                int idcat_p_id = rs.getInt("idcat_p_id");
                
                Produit p = new Produit(id,nom_p,image_p,"null",description,prix_p,stock,idcat_p_id);
              
                obListProd.add(p);

              
            }   
            
            
            
        }catch(Exception ex) {
            System.out.println("exception ="+ex.getMessage() );
        }
        return obListProd ;
    }

    @Override
    public void supprimerProduit(Produit p) {
       String req="DELETE from produit WHERE id=?";
        try {
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setInt(1, p.getId());
            ps.executeUpdate();
            System.out.println("produit supprimé avec succés!!!");
            
        }catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public Produit getProduit(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifierProduit(Produit p) {
        String req ="UPDATE produit  SET nom_p =?, image_p=?, image_qr_code=?, prix_p=?, description_p=?, stock=?, idcat_p_id=?  WHERE id=?";
      
        
       PreparedStatement stm;
        try {
            stm = conn.prepareStatement(req);
                  stm.setString(1, p.getNom_p());
                    stm.setString(2, p.getImage_p());           
                    stm.setString(3, p.getImage_qr_code());

    stm.setDouble(4, p.getPrix_p());
            stm.setString(5, p.getDescription_p());

    stm.setInt(6, p.getStock());
        stm.setInt(7, p.getIdcat_p_id());
        stm.setInt(8, p.getId());

        
            stm.executeUpdate();
                    System.out.println("Produit modifié avec succés");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public boolean exists(String name) {
        String req = "SELECT COUNT(*) FROM produit WHERE nom_p = ?";
        int count = 0;
       try {
           
            PreparedStatement s = conn.prepareStatement(req);
       
            s.setString(1,name);
            ResultSet rs= s.executeQuery();
            rs.next();
             count = rs.getInt(1);
            
            
       } catch(Exception e) {
           System.out.println(e.getMessage());
       }
                   return count>0;//  0 > 0 : false 
    }
    private void showAlert(String title, String message) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
} 
    
    
    @Override
    public void ajouterProduitAuPanier(Produit p) {
       String  req ="INSERT INTO panier_produit (panier_id, produit_id)  VALUES ( (SELECT MAX(id) FROM panier),? );";
       String req2 = "UPDATE produit SET quantiteproduit = 1  where id = ? ;" ;
       String req3 ="UPDATE produit SET stock = stock - 1  where id = ?" ;
       PreparedStatement stm;
       
       
       String req4 ="SELECT CASE WHEN stock <= 0 THEN true ELSE false END AS comparison_result FROM produit WHERE id= ?" ;
       
        try {
       PreparedStatement s3 = conn.prepareStatement(req4);
       s3.setInt(1, p.getId());
       ResultSet rs = s3.executeQuery();
       boolean comparisonResult = false ;
        if (rs.next()) {
            comparisonResult = rs.getBoolean("comparison_result");
            System.out.println("Comparison result: " + comparisonResult);
        }
        
         if(comparisonResult){showAlert("Repture de stock", "Ce produit n'est plus disponible"); return ;}
         System.out.print("test");
        } catch (SQLException ex) {ex.printStackTrace();}
       
       
        try {
            stm = conn.prepareStatement(req);
            stm.setInt(1, p.getId());
            stm.executeUpdate();
            PreparedStatement s = conn.prepareStatement(req2);
            PreparedStatement s1 = conn.prepareStatement(req3);
            s.setInt(1, p.getId());
            s1.setInt(1, p.getId());
            s.executeUpdate();
            s1.executeUpdate();
            showAlert("Produit disponible", "Produit ajoutée avec succés");
            System.out.println("Produit ajoutée");
                    
        } catch (SQLException ex) {
             String req1 = "UPDATE produit SET quantiteproduit = quantiteproduit + 1 where id = ? ";
     
           try {

                PreparedStatement s = conn.prepareStatement(req1);
                PreparedStatement s1 = conn.prepareStatement(req3);
                s.setInt(1,p.getId());
                s1.setInt(1, p.getId());
                s.executeUpdate();
                s1.executeUpdate();
                showAlert("Produit disponible", "Produit ajoutée avec succés");
                System.out.println("Produit ajoutée");


            } catch (SQLException ex1) {
                ex1.printStackTrace();
            }
           
        }
        
    }


  
    
}
