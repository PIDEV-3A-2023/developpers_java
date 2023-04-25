/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecocycla.entities;

import java.time.LocalDate;
import javafx.scene.control.TextField;

/**
 *
 * @author KHALED
 */

public class User {
    private int id;
    private String email;
    private String roles;
    private String password;
    private String nom;
    private String prenom;
    private String photo;
    private LocalDate datenaissance;
    private int cin;
    private String region;
    private String ville;
    private String adresse;

    public User(String email, String roles, String nom, String prenom, LocalDate datenaissance, int cin, String region, String ville, String adresse) {
        this.email = email;
        this.roles = roles;
        this.nom = nom;
        this.prenom = prenom;
        
        this.datenaissance = datenaissance;
        this.cin = cin;
        this.region = region;
        this.ville = ville;
        this.adresse = adresse;
    }

    
    public User(String email, String roles, String password, String nom, String prenom, String photo, LocalDate datenaissance, int cin, String region, String ville, String adresse) {
        this.email = email;
        this.roles = roles;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.photo = photo;
        this.datenaissance = datenaissance;
        this.cin = cin;
        this.region = region;
        this.ville = ville;
        this.adresse = adresse;
    }

    public User(int id, String email, String roles, String nom, String prenom, LocalDate datenaissance, int cin, String region, String ville, String adresse) {
        this.id = id;
        this.email = email;
        this.roles = roles;
        this.nom = nom;
        this.prenom = prenom;
        this.datenaissance = datenaissance;
        this.cin = cin;
        this.region = region;
        this.ville = ville;
        this.adresse = adresse;
    }
    
    
    public User(int id, String email,String roles, String password, String nom, String prenom, 
                String photo, LocalDate datenaissance, int cin, String region, String ville, String adresse) {
        this.id = id;
        this.email = email;
        this.roles = roles;
        
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.photo = photo;
        this.datenaissance = datenaissance;
        this.cin = cin;
        this.region = region;
        this.ville = ville;
        this.adresse = adresse;
    }

    public User() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public User(TextField email, TextField role, TextField nom, TextField prenom, LocalDate MIN, int i, TextField region, TextField ville, TextField adresse) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public User(TextField email, TextField role, TextField nom, TextField prenom, TextField datenaisssance, int i, TextField region, TextField ville, TextField adresse) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles (String roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public LocalDate getDatenaissance() {
        return datenaissance;
    }

    public void setDatenaissance(LocalDate datenaissance) {
        this.datenaissance = datenaissance;
    }

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", email=" + email + ", roles=" + roles + ", password=" + password + ", nom=" + nom + ", prenom=" + prenom + ", photo=" + photo + ", datenaissance=" + datenaissance + ", cin=" + cin + ", region=" + region + ", ville=" + ville + ", adresse=" + adresse + '}';
    }
}