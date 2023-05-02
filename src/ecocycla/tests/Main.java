/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecocycla.tests;

import API.Mail;
import ecocycla.entities.User;
import java.time.LocalDate;
import ecocycla.services.UserCrud;
import java.sql.SQLException;
import javax.jws.soap.SOAPBinding;
import javax.mail.MessagingException;

/**
 *
 * @author KHALED
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author KHALED
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws MessagingException, SQLException {

        UserCrud us = new UserCrud();
        /// User u=new User("test@gmailk.com", "user","hhhhh", "hh", "jjj", "kkk", LocalDate.of(2000, 1, 1), 0, "hhh", "ttt", "hjhjh"); 
        // us.createUser(u); 
        // User u1 = new User (53,"testuu@gmailk.com", "user","hhhhh", "hh", "jjj", "kkk", LocalDate.of(2000, 1, 1), 0, "hhh", "ttt", "hjhjh");
        //us.updateUser(u1); 
        //us.SupprimerUtilisateurid(53);
        User user = new User("testuser@example.com", "oldpassword");
        //us.createUser(user);
        // Update user's passwords
        System.out.println(us.afficherUtilisateurs());
        user.setPassword("newpassword");
        try {
            new UserCrud().updateUserPassword(user);
            System.out.println("Password updated successfully.");
        } catch (SQLException ex) {
            System.out.println("Error updating password: " + ex.getMessage());
        }
    }
       

        // Mail test= new Mail(); 
        //test.sendEmail();
    }

