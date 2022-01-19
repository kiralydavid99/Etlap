package hu.petrik.etlap;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EtlapDb {
    Connection conn;

    public EtlapDb() throws SQLException {
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/etlapdb", "root", "");
    }
    public List<Etlap> getEtelek() throws SQLException {
        List<Etlap> etelek = new ArrayList<>();
        Statement stmt = conn.createStatement();
        String sql = "SELECT * FROM etelek;";
        ResultSet result = stmt.executeQuery(sql);
        while (result.next()) {
            int id = result.getInt("id");
            int ar = result.getInt("ar");
            String nev = result.getString("nev");
            String kategoria = result.getString("kategoria");
            Etlap etlap = new Etlap(id, ar,nev, kategoria);
            etelek.add(etlap);
        }
        return etelek;
    }
    public int etelHozzaadasa(String nev, String kategoria, int ar) throws SQLException {
        String sql = "INSERT INTO filmek(cim, kategoria, hossz, ertekeles) VALUES (?,?,?,?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, nev);
        stmt.setString(2, kategoria);
        stmt.setInt(3, ar);
        return stmt.executeUpdate();
    }

    public boolean etelTorlese(int id) throws SQLException {
        String sql = "DELETE FROM filmek WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        int erintettSorok = stmt.executeUpdate();
        return erintettSorok == 1;
    }

    public boolean etlapModositasa(Etlap modositando) throws SQLException {
        String sql = "UPDATE filmek SET " +
                "nev = ?," +
                "kategoria = ?," +
                "ar = ?," +
                "WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, modositando.getNev());
        stmt.setString(2, modositando.getKategoria());
        stmt.setInt(3, modositando.getAr());
        stmt.setInt(5, modositando.getId());
        int erintettSorok = stmt.executeUpdate();
        return erintettSorok == 1;
    }
}
