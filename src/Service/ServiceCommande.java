
package Service;

import Entities.Commande;
import Services.IServiceCommande;
import Utiles.Maconnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.Date;

import java.util.Optional;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author User
 */
public class ServiceCommande implements IServiceCommande{
    Connection cnx;

    public ServiceCommande() {
        cnx=Maconnexion.getInstance().getConnection();
    }
    
    @Override
    public void AjouterCommande(Commande c) {
        try {
            Statement stm=cnx.createStatement();
       
        String query="INSERT INTO commande(date,prix,etat) VALUES ('"+c.getDate()+"','"+c.getPrix()+"','"+c.getEtat()+"')";
         Alert alert = new Alert(AlertType.CONFIRMATION);
alert.setTitle("Confirmation Dialog");
alert.setHeaderText("Confirmation ");
alert.setContentText("Etes vous sur de vouloir ajouter cette commande?");

Optional<ButtonType> result = alert.showAndWait();
if (result.get() == ButtonType.OK){
    stm.executeUpdate(query);
    Alert alert2 = new Alert(AlertType.INFORMATION);
            alert2.setTitle("Ajout");
            alert2.setHeaderText("commande ajoutée");
            alert2.setContentText("La commande a été ajouter avec success!");
            alert2.showAndWait();
}

         } catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public ObservableList<Commande> AfficherCommande()  {
        ObservableList<Commande>commandes=FXCollections.observableArrayList();
       try {
            Statement stm;
        
            stm = cnx.createStatement();
       
            String query="SELECT * from `commande`";
            ResultSet rst=stm.executeQuery(query);
            
            while(rst.next()){
                Commande c=new Commande();
                c.setId(rst.getInt("id"));
                c.setDate(rst.getDate("date"));
                c.setPrix(rst.getFloat("prix"));
                c.setEtat(rst.getString("etat"));
                commandes.add(c);
            }
             } catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       return commandes; 
    }

    @Override
    public void supprimercommande(int id) {
         try {
            Statement stm=cnx.createStatement();
       
        String query=" Delete FROM commande where id='"+id+"'";
        Alert alert = new Alert(AlertType.CONFIRMATION);
alert.setTitle("Confirmation Dialog");
alert.setHeaderText("Confirmation ");
alert.setContentText("Etes vous sur de vouloir supprimer cette commande?");

Optional<ButtonType> result = alert.showAndWait();
if (result.get() == ButtonType.OK){
    stm.executeUpdate(query);
    Alert alert2 = new Alert(AlertType.INFORMATION);
            alert2.setTitle("Suppression");
            alert2.setHeaderText("commande Supprimé");
            alert2.setContentText("La commande a été supprimer avec success!");
            alert2.showAndWait();
}

         } catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void ModifierCommande(Commande c) {
        try {

        PreparedStatement ps;

        ps=cnx.prepareStatement("UPDATE  commande set `date`=?,`prix`=?,`etat`=? where id="+c.getId());
        ps.setDate(1, (java.sql.Date) c.getDate());
        ps.setFloat(2, c.getPrix());
        ps.setString(3, c.getEtat());
        ps.executeUpdate();
        Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Update");
            alert.setHeaderText("commande Modifié");
            alert.setContentText("Lacommande a été modifier avec success!");
            alert.showAndWait();

alert.showAndWait();
    } catch (SQLException ex) {
        Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
         Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Ajout");
            alert.setHeaderText("commande Ajouté");
            alert.setContentText("La commande a été Ajouter avec success!");
            alert.showAndWait();
    }
    }
    public ObservableList<Commande> search(String input){
         ObservableList<Commande>commandes = FXCollections.observableArrayList();
        try {
             Statement stm;
            stm= cnx.createStatement();

                String query="SELECT * from commande where etat like '%"+input+"%'";
           ResultSet rst=stm.executeQuery(query); 
           Commande form;
           while(rst.next())
        {
            Commande c =new Commande();
            c.setId(rst.getInt("id"));
                c.setDate(rst.getDate("date"));
                c.setPrix(rst.getFloat("prix"));
                c.setEtat(rst.getString("etat"));
            form= new Commande(rst.getInt("id"),rst.getDate("date"),rst.getFloat("prix"),rst.getString("etat"));
            commandes.add(form); 
        }
           
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
        }
             
        return commandes;
     }
     public ObservableList<Commande> triasc(){
         ObservableList<Commande> commandes=FXCollections.observableArrayList();
        try {
            Statement stm=cnx.createStatement();
                String query="SELECT * from commande ORDER by etat ASC";
            ResultSet rst=stm.executeQuery(query); 
          Commande form;
           while(rst.next())
        {
           Commande c =new Commande();
            c.setId(rst.getInt("id"));
                c.setDate(rst.getDate("date"));
                c.setPrix(rst.getFloat("prix"));
                c.setEtat(rst.getString("etat"));
            form= new Commande(rst.getInt("id"),rst.getDate("date"),rst.getFloat("prix"),rst.getString("etat"));
            commandes.add(form); 
        }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
        }
         
       return commandes;  
     } 
      public ObservableList<Commande> triadsc(){
         ObservableList<Commande> commandes=FXCollections.observableArrayList();
        try {
            Statement stm=cnx.createStatement();
                String query="SELECT * from commande ORDER by etat DESC";
            ResultSet rst=stm.executeQuery(query); 
          Commande form;
           while(rst.next())
        {
           Commande c =new Commande();
            c.setId(rst.getInt("id"));
                c.setDate(rst.getDate("date"));
                c.setPrix(rst.getFloat("prix"));
                c.setEtat(rst.getString("etat"));
            form= new Commande(rst.getInt("id"),rst.getDate("date"),rst.getFloat("prix"),rst.getString("etat"));
            commandes.add(form); 
        }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
        }
         
       return commandes;  
     } 
      
      
      
      
      
      
      public void sendmail(String mail,float prix) {
		
		String to = mail;
		String from = "azizbarguellil@gmail.com";
		final String username = "azizbarguellil@gmail.com";
		final String password = "Azem-2019mai";
		
		String host = "smtp.gmail.com";
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");  
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "587");
		
		javax.mail.Session session = javax.mail.Session.getInstance(props,
         new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
               return new PasswordAuthentication(username, password);
     }
         });
		
		try {
    //create a MimeMessage object
    Message message = new MimeMessage(session);
 
    //set From email field 
    message.setFrom(new InternetAddress(from));
 
    //set To email field
    message.setRecipients(javax.mail.Message.RecipientType.TO,
               InternetAddress.parse(to));
 
    //set email subject field
    message.setSubject("Your Order from skillapp ");
 
    //set the content of the email message
    message.setText("Merci pour votre achat , prix total est de :"+prix);

    //send the email message
    Transport.send(message);

    System.out.println("Merci pour votre achat , prix total est de :"+prix);

      } catch (MessagingException e) {
         throw new RuntimeException(e);
      }
    
    
}
      

}
