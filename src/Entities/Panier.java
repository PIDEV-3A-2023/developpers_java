package Entities;

import java.util.Date;

public class Panier {

    static int id;
    String ProdName;
    String ProdBy;
    int Price;
    int Quantity ;
    Date Date ; 

    public Panier(int id, String prodName, String prodBy, int price , int quantity , Date date) {
        this.id = id;
        ProdName = prodName;
        ProdBy = prodBy;
        Price = price;
        Quantity = quantity ;
        Date = date ; 
    }

    public static int getId() {
        return id;
    }

    public Panier(Date date, int id, int quantity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setId(int id) {
        this.id= id;
    }

    public String getProdName() {
        return ProdName;
    }

    public void setProdName(String prodName) {
        ProdName = prodName;
    }

    public String getProdBy() {
        return ProdBy;
    }

    public void setProdBy(String prodBy) {
        ProdBy = prodBy;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    public Date getDate() {
        return Date;
    }

    public void setDate(Date Date) {
        this.Date = Date;
    }
   
}
