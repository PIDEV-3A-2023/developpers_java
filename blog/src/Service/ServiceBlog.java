/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.Blog;
import Entities.Blog;
import Services.IServiceBlog;
import Utiles.Maconnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
/**
 *
 * @author User
 */
public class ServiceBlog implements IServiceBlog {

    Connection cnx;

    public ServiceBlog() {
        cnx = Maconnexion.getInstance().getConnection();
    }

    @Override
    public void AjouterBlog(Blog c) {
        try {
            Statement stm = cnx.createStatement();

            String query = "INSERT INTO blog(date,titre,description_b) VALUES ('" + c.getDate() + "','" + c.getTitre() + "','" + c.getDescription_b() + "')";
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("Confirmation ");
            alert.setContentText("Etes vous sur de vouloir ajouter ce blog");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                stm.executeUpdate(query);
                Alert alert2 = new Alert(AlertType.INFORMATION);
                alert2.setTitle("Ajout");
                alert2.setHeaderText("Blog ajoutée");
                alert2.setContentText("Le blog a été ajouter avec success!");
                alert2.showAndWait();
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceBlog.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public ObservableList<Blog> AfficherBlog() {
        ObservableList<Blog> livraisons = FXCollections.observableArrayList();
        try {
            Statement stm;

            stm = cnx.createStatement();

            String query = "SELECT * from `blog`";
            ResultSet rst = stm.executeQuery(query);

            while (rst.next()) {
                Blog c = new Blog();
                c.setId(rst.getInt("id"));
                c.setDate(rst.getDate("date"));
                c.setTitre(rst.getString("titre"));
                c.setDescription_b(rst.getString("description_b"));
                livraisons.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceBlog.class.getName()).log(Level.SEVERE, null, ex);
        }

        return livraisons;
    }

    @Override
    public void supprimerBlog(int id) {
        try {
            Statement stm = cnx.createStatement();

            String query = " Delete FROM blog where id='" + id + "'";
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("Confirmation ");
            alert.setContentText("Etes vous sur de vouloir supprimer ce blog?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                stm.executeUpdate(query);
                Alert alert2 = new Alert(AlertType.INFORMATION);
                alert2.setTitle("Suppression");
                alert2.setHeaderText("Blog Supprimé");
                alert2.setContentText("Le Blog a été supprimer avec success!");
                alert2.showAndWait();
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceBlog.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void ModifierBlog(Blog c) {
        try {

            PreparedStatement ps;

            ps = cnx.prepareStatement("UPDATE  blog set `date`=?,`titre`=?,`description_b`=? where id=" + c.getId());
            ps.setDate(1, (java.sql.Date) c.getDate());
            ps.setString(2, c.getTitre());
            ps.setString(3, c.getDescription_b());
            ps.executeUpdate();
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Update");
            alert.setHeaderText("Blog Modifié");
            alert.setContentText("Le Blog a été modifier avec success!");
            alert.showAndWait();

            alert.showAndWait();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceBlog.class.getName()).log(Level.SEVERE, null, ex);
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Ajout");
            alert.setHeaderText("Blog Ajouté");
            alert.setContentText("Le Blog a été Ajouter avec success!");
            alert.showAndWait();
        }
    }

    public ObservableList<Blog> search(String input) {
        ObservableList<Blog> livraisons = FXCollections.observableArrayList();
        try {
            Statement stm;
            stm = cnx.createStatement();

            String query = "SELECT * from blog where titre like '%" + input + "%'";
            ResultSet rst = stm.executeQuery(query);
            Blog form;
            while (rst.next()) {
                Blog c = new Blog();
                c.setId(rst.getInt("id"));
                c.setDate(rst.getDate("date"));
                c.setTitre(rst.getString("titre"));
                c.setDescription_b(rst.getString("description_b"));
                form = new Blog(rst.getInt("id"), rst.getDate("date"), rst.getString("titre"), rst.getString("description_b"));
                livraisons.add(form);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceBlog.class.getName()).log(Level.SEVERE, null, ex);
        }

        return livraisons;
    }

    public ObservableList<Blog> triasc() {
        ObservableList<Blog> livraisons = FXCollections.observableArrayList();
        try {
            Statement stm = cnx.createStatement();
            String query = "SELECT * from blog ORDER by titre ASC";
            ResultSet rst = stm.executeQuery(query);
            Blog form;
            while (rst.next()) {
                Blog c = new Blog();
                c.setId(rst.getInt("id"));
                c.setDate(rst.getDate("date"));
                c.setTitre(rst.getString("titre"));
                c.setDescription_b(rst.getString("description_b"));
                form = new Blog(rst.getInt("id"), rst.getDate("date"), rst.getString("titre"), rst.getString("description_b"));
                livraisons.add(form);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceBlog.class.getName()).log(Level.SEVERE, null, ex);
        }

        return livraisons;
    }

    public ObservableList<Blog> triadsc() {
        ObservableList<Blog> livraisons = FXCollections.observableArrayList();
        try {
            Statement stm = cnx.createStatement();
            String query = "SELECT * from blog ORDER by titre DESC";
            ResultSet rst = stm.executeQuery(query);
            Blog form;
            while (rst.next()) {
               Blog c = new Blog();
                c.setId(rst.getInt("id"));
                c.setDate(rst.getDate("date"));
                c.setTitre(rst.getString("titre"));
                c.setDescription_b(rst.getString("description_b"));
                form = new Blog(rst.getInt("id"), rst.getDate("date"), rst.getString("titre"), rst.getString("description_b"));
                livraisons.add(form);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceBlog.class.getName()).log(Level.SEVERE, null, ex);
        }

        return livraisons;
    }

}
