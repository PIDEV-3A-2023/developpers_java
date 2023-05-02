/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecocycla.utils;
import java.time.LocalDate;

/**
 *
 * @author KHALED
 */
public class SessionManager {
    private static SessionManager instance;
 
     private static int id;
    private static int cin;
    private static String email;
    private static String ville;
    private static String nom;
    private static String adresse;
    private static String prenom ,region ; 
    private static LocalDate datedenaissance; 
    
   
    private static String roles;

     private SessionManager(int id , int cin , String email, String ville , String nom ,String adresse,String prenom,String region ,String role ) {
    SessionManager.id=id;
    SessionManager.cin=cin;
    SessionManager.email=email;
    SessionManager.ville=ville;
    SessionManager.nom=nom;
    SessionManager.adresse=adresse;
    SessionManager.prenom=prenom;
    SessionManager.region=region; 
    SessionManager.roles=role;
    }
 
    public static SessionManager getInstace(int id , int cin , String email,String ville  , String nom ,String adresse,String region,String prenom,String role) {
        if(instance == null) {
            instance = new SessionManager( id , cin ,  email ,  ville ,  nom,adresse,region,prenom, role);
        }
        return instance;
    }

    
    public static SessionManager getInstance() {
        return instance;
    }

    public static void setInstance(SessionManager instance) {
        SessionManager.instance = instance;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        SessionManager.id = id;
    }

    public static int getCin() {
        return cin;
    }

    public static void setCin(int cin) {
        SessionManager.cin = cin;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        SessionManager.email = email;
    }

    public static String getVille() {
        return ville;
    }

    public static void setVille(String ville) {
        SessionManager.ville = ville;
    }

    public static String getNom() {
        return nom;
    }

    public static void setNom(String nom) {
        SessionManager.nom = nom;
    }

    public static String getAdresse() {
        return adresse;
    }

    public static void setAdresse(String adresse) {
        SessionManager.adresse = adresse;
    }

    public static String getPrenom() {
        return prenom;
    }

    public static void setPrenom(String prenom) {
        SessionManager.prenom = prenom;
    }

    public static String getRegion() {
        return region;
    }

    public static void setRegion(String region) {
        SessionManager.region = region;
    }

    public static LocalDate getDatedenaissance() {
        return datedenaissance;
    }

    public static void setDatedenaissance(LocalDate datedenaissance) {
        SessionManager.datedenaissance = datedenaissance;
    }

    public static String getRoles() {
        return roles;
    }

    public static void setRoles(String roles) {
        SessionManager.roles = roles;
    }
    
}
