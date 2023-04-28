package entites;

import java.util.Date;
/**
 *
 * @author oumeima
 */

public class Panier {

    private int id ;
    private Date date_ajoute;
    private int quantite;

       public Panier(int id ){
        this.id = id;
    } 
    public Panier (){
    }

    public Panier(int id, Date date_ajoute, int quantite) {
        this.id = id;
        this.date_ajoute = date_ajoute;
        this.quantite = quantite;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate_ajoute() {
        return date_ajoute;
    }

    public void setDate_ajoute(Date date_ajoute) {
        this.date_ajoute = date_ajoute;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    
   
  
    
}
