/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecocycla.tests;

import ecocycla.entities.User;
import java.time.LocalDate;
import ecocycla.services.UserCrud;

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
    public static void main(String[] args) {
        
        UserCrud us= new UserCrud(); 
        User u=new User("jjj@gmailk.com", "user","hhhhh", "hh", "jjj", "kkk", LocalDate.of(2000, 1, 1), 0, "hhh", "ttt", "hjhjh"); 
        us.createUser(u); 
        User u1 = new User (53,"jjjuu@gmailk.com", "user","hhhhh", "hh", "jjj", "kkk", LocalDate.of(2000, 1, 1), 0, "hhh", "ttt", "hjhjh");
        us.updateUser(u1); 
        us.SupprimerUtilisateurid(52);
        
        
       
    }      
}
        
     
        
        
    
    
    
    
    
    
    