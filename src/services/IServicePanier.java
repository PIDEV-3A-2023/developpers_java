
package services;
import entites.Produit;
import java.util.List;
/**
 *
 * @author oumeima
 */
public interface IServicePanier<T> {
    
    
    public List<Produit>affichagePanier();
     
}
