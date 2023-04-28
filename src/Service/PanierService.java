
package Service;

import Entities.Panier;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PanierService {

    private Connection connection;

    public PanierService(Connection connection) {
        this.connection = connection;
    }

    public void createPanier(Panier panier) throws SQLException {
        String sql = "INSERT INTO paniers (date, id, quantity) VALUES (?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setDate(1, new java.sql.Date(panier.getDate().getTime()));
        statement.setInt(2, panier.getId());
        statement.setInt(3, panier.getQuantity());
        statement.executeUpdate();
    }

    public Panier getPanierById(int id) throws SQLException {
        String sql = "SELECT * FROM paniers WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet result = statement.executeQuery();
        if (result.next()) {
            Date date = result.getDate("date");
            int quantity = result.getInt("quantity");
            return new Panier(date, id, quantity);
        } else {
            return null;
        }
    }

    public List<Panier> getAllPaniers() throws SQLException {
        String sql = "SELECT * FROM paniers";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet result = statement.executeQuery();
        List<Panier> paniers = new ArrayList<>();
        while (result.next()) {
            Date date = result.getDate("date");
            int id = result.getInt("id");
            int quantity = result.getInt("quantity");
            paniers.add(new Panier(date, id, quantity));
        }
        return paniers;
    }

    public void updatePanier(Panier panier) throws SQLException {
        String sql = "UPDATE paniers SET date = ?, quantity = ? WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setDate(1, new java.sql.Date(panier.getDate().getTime()));
        statement.setInt(2, panier.getQuantity());
        statement.setInt(3, panier.getId());
        statement.executeUpdate();
    }

    public void deletePanier(int id) throws SQLException {
        String sql = "DELETE FROM paniers WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        statement.executeUpdate();
    }

    
}
