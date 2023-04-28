package services;
import entites.Panier;
import entites.Commande;
import java.util.List;
import javafx.collections.ObservableList;
/**
 *
 * @author oumeima
 */
public interface IServiceCommande<T> {
    
    public ObservableList<Commande> AfficherCommande();
    public void passerCommande(int id_pan, String nom,Double prix,String adresse,String mail);
    public int GetLastPanier() ;
}
