/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import entites.Categorie ; 
import entites.Produit;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.DB;
/**
 *
 * @author khadija
 */
public class ServiceCat implements IServiceCat <Categorie>  {
    
    ObservableList<Categorie>obListCat = FXCollections.observableArrayList();
      Connection conn;
    public ServiceCat() {
        conn = DB.getInstance().getConnection();
    }

    @Override
    public void ajouterCat(Categorie C) {
        String  req ="INSERT INTO categorie_p (nom_c,description_cat,date_creation )values(?,?,?)";

       PreparedStatement stm;
        try {
            stm = conn.prepareStatement(req);
                  stm.setString(1, C.getNom_c());
                    stm.setString(2, C.getDescription_cat());           
                   stm.setDate(3,(Date) new java.sql.Date(C.getDate_creation().getTime())) ;
            stm.executeUpdate();
                    System.out.println("Categorie ajoutée");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public  ObservableList<Categorie> affichageCat() {
           String req="SELECT * FROM categorie_p ";
         List<Categorie>listCat = new ArrayList<>();
        
        
        try{
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(req);
            
            while(rs.next()) {
                int id= rs.getInt("id");
                String nom_c = rs.getString("nom_c");
                String description_cat = rs.getString("description_cat");
                 java.util.Date date_creation= rs.getDate("date_creation");
                
                Categorie C = new Categorie (id,nom_c,description_cat,date_creation);
              
               obListCat.add(C);

              
            }   
            
            
            
        }catch(Exception ex) {
            System.out.println("exception ="+ex.getMessage() );
        }
        return obListCat ;
    }

    @Override
    public void supprimercat(Categorie C) {
          String req="DELETE from categorie_p WHERE id=?";
        try {
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setInt(1, C.getId());
            ps.executeUpdate();
            System.out.println("categorie supprimé avec succés!!!");
            
        }catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public Categorie getcat(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifierCat(Categorie C) {
      String req ="UPDATE categorie_p  SET nom_c =?, description_cat=? , date_creation=?  WHERE id=?";
      
        
       PreparedStatement stm;
        try {
            stm = conn.prepareStatement(req);
                  stm.setString(1, C.getNom_c());
                    stm.setString(2, C.getDescription_cat());           
                  stm.setDate(3, new java.sql.Date(C.getDate_creation().getTime()));
                  stm.setInt(4, C.getId());

        
            stm.executeUpdate();
                    System.out.println("Categorie modifié avec succés");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public boolean existsCat(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  
    
     
    
}
