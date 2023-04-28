/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import entites.Produit;
import java.util.List;
/**
 *
 * @author khadija
 */
public interface IService<T> {
    
    
    public void ajouterProduit(Produit p);
    public List<Produit>affichageProduit();
    public void supprimerProduit(Produit p);
    public Produit getProduit(int id);
    public void modifierProduit(Produit p);
    public boolean exists(String name);
     public List<Produit>affichageProduitTrieer ();
        public List<Produit>affichageProduitTrieerNom ();
    
}
