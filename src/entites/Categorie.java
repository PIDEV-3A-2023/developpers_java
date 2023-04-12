/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.util.Date;

/**
 *
 * @author khadija
 */
public class Categorie {
    private int id ; 
    private String nom_c ; 
    private String description_cat; 
    private Date date_creation; 
    
    
    
    public Categorie (){
    }

    public Categorie(String nom_c, String description_cat, Date date_creation) {
        this.nom_c = nom_c;
        this.description_cat = description_cat;
        this.date_creation = date_creation;
    }

    public Categorie(int id, String nom_c, String description_cat, Date date_creation) {
        this.id = id;
        this.nom_c = nom_c;
        this.description_cat = description_cat;
        this.date_creation = date_creation;
    }

    @Override
    public String toString() {
        return "Categorie{" + "id=" + id + ", nom_c=" + nom_c + ", description_cat=" + description_cat + ", date_creation=" + date_creation + '}';
    }

   

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_c() {
        return nom_c;
    }

    public void setNom_c(String nom_c) {
        this.nom_c = nom_c;
    }
    
    public String getDescription_cat() {
        return description_cat;
    }

    public void setDescription_cat(String description_cat) {
        this.description_cat = description_cat;
    }

    public Date getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(Date date_creation) {
        this.date_creation = date_creation;
    }
    
    
    
    
}
