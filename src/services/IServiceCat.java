/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import entites.Categorie;
import java.util.List;
/**
 *
 * @author khadija
 */
public interface IServiceCat <T> {
    public void ajouterCat(Categorie C);
    public List<Categorie>affichageCat();
    public void supprimercat(Categorie C);
    public Categorie getcat(int id);
    public void modifierCat(Categorie C);
    public boolean existsCat(String name);
   
     public Categorie getCategorieByNom(String nom_c);
    
}
