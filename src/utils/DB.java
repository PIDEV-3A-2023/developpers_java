/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;
import java.sql.*;
/**
 *
 * @author khadija
 */
public class DB {
    private Connection conn;
 
    String url = "jdbc:mysql://localhost:3306/dbintegartion";
    String user ="root";
    String pwd = "";
    
    private static DB instance ;
    public DB() {
        try {
            
            conn = DriverManager.getConnection(url,user,pwd);
            System.out.println("connexion établie!!!");
            
        }catch(Exception ex) {
            System.out.println("Probleme de connexion!!!"+ex.getMessage());
        }
    }
    
    //SINGLETON 
    public static DB getInstance() {
        
        if(DB.instance == null) {
            
            DB.instance = new DB();
        }
        return DB.instance;
    }
 
    
    // APPEL GET CONNECTION 
    
    
    
    public static Connection getConnection() {
        return DB.getInstance().conn;
    }
}
