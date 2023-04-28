/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

/**
 *
 * @author khadija
 */
public class Produit {
    private int id ; 
    private String  nom_p , image_p ,image_qr_code ,  description_p  ; 
    private double prix_p ;
    private int stock ; 
    private int quantiteproduit; 
    private int idcat_p_id;  

    public int getQuantiteproduit() {
        return quantiteproduit;
    }

    public void setQuantiteproduit(int quantiteproduit) {
        this.quantiteproduit = quantiteproduit;
    }
    
      
       public Produit(int id ){
        this.id = id;
    } 
    public Produit (){
    }
    
      public Produit(int id, String nom_p, String image_p, String image_qr_code, String description_p, double prix_p, int stock) {
        this.id = id;
        this.nom_p = nom_p;
        this.image_p = image_p;
      
        this.description_p = description_p;
        this.prix_p = prix_p;
        this.stock = stock;
    }

    public Produit(String nom_p, String image_p, String image_qr_code, String description_p, double prix_p, int stock,int idcat_p_id) {
        this.nom_p = nom_p;
        this.image_p = image_p;
        this.idcat_p_id = idcat_p_id;
        this.description_p = description_p;
        this.prix_p = prix_p;
        this.stock = stock;
    }
        public Produit(int id,String nom_p, String image_p, String image_qr_code, String description_p, double prix_p, int stock,int idcat_p_id) {
        this.nom_p = nom_p;
        this.id=id;
        this.image_p = image_p;
        this.idcat_p_id = idcat_p_id;
        this.description_p = description_p;
        this.prix_p = prix_p;
        this.stock = stock;
    }

    public Produit(int id, String nom_p, String image_p, String description_p, double prix_p, int stock, int idcat_p_id) {
        this.id = id;
        this.nom_p = nom_p;
        this.image_p = image_p;
        this.description_p = description_p;
        this.prix_p = prix_p;
        this.stock = stock;
        this.idcat_p_id = idcat_p_id;
    }

    

 
        


    @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", nom_p=" + nom_p + ", image_p=" + image_p + ", image_qr_code=" + image_qr_code + ", description_p=" + description_p + ", prix_p=" + prix_p + ", stock=" + stock + ", idcat_p_id=" + idcat_p_id+ '}';
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_p() {
        return nom_p;
    }

    public String getImage_p() {
        return image_p;
    }

    public void setImage_p(String image_p) {
        this.image_p = image_p;
    }

    public void setNom_p(String nom_p) {
        this.nom_p = nom_p;
    }
    public String getDescription_p() {
        return description_p;
    }

    public void setDescription_p(String description_p) {
        this.description_p = description_p;
    }

    public double getPrix_p() {
        return prix_p;
    }

    public void setPrix_p(double prix_p) {
        this.prix_p = prix_p;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getImage_qr_code() {
        return image_qr_code;
    }

    public void setImage_qr_code(String image_qr_code) {
        this.image_qr_code = image_qr_code;
    }

    public int getIdcat_p_id() {
        return idcat_p_id;
    }

    public void setIdcat_p_id(int idcat_p_id) {
        this.idcat_p_id = idcat_p_id;
    }

    public Produit(int id, String nom_p, String image_p, String image_qr_code, String description_p, double prix_p, int stock, int quantiteproduit, int idcat_p_id) {
        this.id = id;
        this.nom_p = nom_p;
        this.image_p = image_p;
        this.image_qr_code = image_qr_code;
        this.description_p = description_p;
        this.prix_p = prix_p;
        this.stock = stock;
        this.quantiteproduit = quantiteproduit;
        this.idcat_p_id = idcat_p_id;
    }


  
    
}
