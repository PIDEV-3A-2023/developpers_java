/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecocycla.services;


/**
 *
 * @author KHALED
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import ecocycla.entities.User;
import ecocycla.utils.MyConnection;
import java.sql.Statement;

public class UserCrud {
    
     Connection conn=MyConnection.getInstance().getConnection();
     
      
    
    
    // Create operation
    public boolean createUser(User user) {
        String sql = "INSERT INTO user (email, roles, password, nom, prenom, photo, datenaissance, cin, region, ville, adresse) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getRoles());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getNom());
            stmt.setString(5, user.getPrenom());
            stmt.setString(6, user.getPhoto());
            stmt.setDate(7, java.sql.Date.valueOf(user.getDatenaissance()));
            stmt.setInt(8, user.getCin());
            stmt.setString(9, user.getRegion());
            stmt.setString(10, user.getVille());
            stmt.setString(11, user.getAdresse());
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            System.err.println("Error creating user: " + ex.getMessage());
            return false;
        }
    }
    
    // Read operation
    public User getUserById(int id) {
        String sql = "SELECT * FROM user WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    User user = new User(
                        rs.getInt("id"),
                        rs.getString("email"),
                        rs.getString("roles"),
                        rs.getString("password"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("photo"),
                        rs.getDate("datenaissance").toLocalDate(),
                        rs.getInt("cin"),
                        rs.getString("region"),
                        rs.getString("ville"),
                        rs.getString("adresse")
                    );
                    return user;
                } else {
                    return null;
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error getting user: " + ex.getMessage());
            return null;
        }
    }
    
    // Update operation
    public boolean updateUser(User user) {
        String sql = "UPDATE user SET email=?, roles=?, password=?, nom=?, prenom=?, photo=?, datenaissance=?, cin=?, region=?, ville=?, adresse=? " +
                     "WHERE id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getRoles());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getNom());
            stmt.setString(5, user.getPrenom());
            stmt.setString(6, user.getPhoto());
            stmt.setDate(7, java.sql.Date.valueOf(user.getDatenaissance()));
            stmt.setInt(8, user.getCin());
            stmt.setString(9, user.getRegion());
            stmt.setString(10, user.getVille());
            stmt.setString(11, user.getAdresse());
            stmt.setInt(12, user.getId());
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            System.err.println("Error updating user: " + ex.getMessage());
            return false;
        }
    }
    
    //
    public void SupprimerUtilisateurid(int id) {
        try {
            Statement st = conn.createStatement(); //j'ai obtenu l'objet connexion par cerre ligne 
            String req = "DELETE FROM user WHERE id = " + id + "";
            st.executeUpdate(req);
            System.out.println("L'utilisateur  avec l'id = " + id + " a été supprimer avec succès...");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void supprimerUtilisateur(User user) {
        try {
            String requete="delete from user where id=?";
            PreparedStatement pst = conn.prepareStatement(requete);
            pst.setInt(1,user.getId());
            pst.executeUpdate();
           
            System.out.println("Utlisateur est supprimée");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
    }}




}