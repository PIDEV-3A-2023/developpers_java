
package services;
import entites.Produit ;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.DB;


/**
 *
 * @author oumeima
 */
public class ServicePanier implements IServicePanier<Produit>  {
    
ObservableList<Produit>obListProd = FXCollections.observableArrayList();
      Connection conn;
    public ServicePanier() {
        conn = DB.getInstance().getConnection();
    }
    
  
    @Override
   public ObservableList<Produit> affichagePanier() {
           String req="SELECT p.* FROM produit p INNER JOIN panier_produit pp ON pp.produit_id = p.id INNER JOIN panier pa ON pa.id = pp.panier_id WHERE pa.id = (SELECT MAX(id) FROM panier)";
           
           
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
                int quantiteproduit = rs.getInt("quantiteproduit");
                
                Produit p = new Produit(id,nom_p,image_p,"null",description,prix_p,stock,quantiteproduit ,idcat_p_id);
              
                obListProd.add(p);

              
            }   
            
            
            
        }catch(Exception ex) {
            System.out.println("exception ="+ex.getMessage() );
        }
        return obListProd ;
    }
   
   
   //Calcul Prix Total
    public ObservableList<Produit> calculPrixTotal() {
         String req="SELECT SUM(produit.prix_p * panier_produit.quantite_produit) AS prix_total FROM produit  JOIN panier_produit ON produit.id_produit = panier_produit.id_produit  WHERE panier_produit.id_panier = :id_panier ";          
        List<Produit>listProd = new ArrayList<>();
        try{ 
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(req);
            
            while(rs.next()) {
                int id= rs.getInt("id");          
                double prix_p = rs.getDouble("prix_p");
                double prix_total = rs.getDouble("prix_total");
                
              //  Produit produit = new Produit(id,prix_p,prix_total);
             //   obListProd.add(produit);
            }   
        }catch(Exception ex) {
            System.out.println("exception ="+ex.getMessage() );
        }
        return obListProd ;
    }

   
    
}
