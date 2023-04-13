
package Services;

import Entities.Commande;
import java.sql.SQLException;
import javafx.collections.ObservableList;


public interface IServiceCommande {
    
    public void AjouterCommande(Commande c);
    public ObservableList<Commande>AfficherCommande();
    public void supprimercommande(int id);
    public void ModifierCommande(Commande c);
}
