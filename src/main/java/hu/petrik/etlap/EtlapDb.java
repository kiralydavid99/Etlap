package hu.petrik.etlap;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EtlapDb {
    Connection conn;

    public EtlapDb() throws SQLException {
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/etlapdb", "root", "");
    }
    public List<Etlap> getEtlap() throws SQLException {
        List<Etlap> etlap = new ArrayList<>();
        Statement stmt = conn.createStatement();
        String sql = "SELECT * FROM etlap;";
        ResultSet result = stmt.executeQuery(sql);
        while (result.next()) {
            int id = result.getInt("id");
            int ar = result.getInt("ar");
            String nev = result.getString("nev");
            String kategoria = result.getString("kategoria");
            Etlap etelek = new Etlap(id, ar,nev, kategoria);
            etlap.add(etelek);
        }
        return etlap;
    }
    public int etelHozzaadasa(String nev, String kategoria, int ar) throws SQLException {
        String sql = "INSERT INTO etelek(nev, kategoria, ar) VALUES (?,?,?,?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, nev);
        stmt.setString(2, kategoria);
        stmt.setInt(3, ar);
        return stmt.executeUpdate();
    }

    public boolean etelTorlese(int id) throws SQLException {
        String sql = "DELETE FROM etlap WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        int erintettSorok = stmt.executeUpdate();
        return erintettSorok == 1;
    }


}
