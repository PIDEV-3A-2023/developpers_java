package entites;

import java.util.Date;
/**
 *
 * @author oumeima
 */


public class Commande {

    private int id ;
    private int panier_id ;
    private Date date_commande;
    private String nom_client ;
    private float prix_total;
    private String mail_client;
    private String adresse_livraison;
    private String status;
      
      
       public Commande(int id ){
        this.id = id;
    } 
    public Commande (){
    }

    public Commande(int id, int panier_id, Date date_commande, String nom_client, float prix_total, String mail_client, String adresse_livraison, String status) {
        this.id = id;
        this.panier_id = panier_id;
        this.date_commande = date_commande;
        this.nom_client = nom_client;
        this.prix_total = prix_total;
        this.mail_client = mail_client;
        this.adresse_livraison = adresse_livraison;
        this.status = status;
    }

    public Commande(int id, int panier_id, Date date_commande) {
        this.id = id;
        this.panier_id = panier_id;
        this.date_commande = date_commande;
     
    }

    public Commande(int panier_id, Date date_commande, float prix_total , String nom_client) {
        this.panier_id = panier_id;
        this.date_commande = date_commande;
         this.prix_total = prix_total;
        this.nom_client = nom_client;
       
    }

    
    
        public Commande(int panier_id, Date date_commande, String nom_client, float prix_total, String mail_client, String adresse_livraison, String status) {
        this.id = panier_id;
        this.date_commande = date_commande;
        this.nom_client = nom_client;
        this.prix_total = prix_total;
        this.mail_client = mail_client;
        this.adresse_livraison = adresse_livraison;
        this.status = status;
    }
        
        


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPanier_id() {
        return panier_id;
    }

    public void setPanier_id(int panier_id) {
        this.panier_id = panier_id;
    }

    public Date getDate_commande() {
        return date_commande;
    }

    public void setDate_commande(Date date_commande) {
        this.date_commande = date_commande;
    }

    public String getNom_client() {
        return nom_client;
    }

    public void setNom_client(String nom_client) {
        this.nom_client = nom_client;
    }

    public float getPrix_total() {
        return prix_total;
    }

    public void setPrix_total(float prix_total) {
        this.prix_total = prix_total;
    }

    public String getMail_client() {
        return mail_client;
    }

    public void setMail_client(String mail_client) {
        this.mail_client = mail_client;
    }

    public String getAdresse_livraison() {
        return adresse_livraison;
    }

    public void setAdresse_livraison(String adresse_livraison) {
        this.adresse_livraison = adresse_livraison;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
   
  
    
}
