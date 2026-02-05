package DAO;

import MODELO.Celular;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import MODELO.Celular;

public class CelularDAO {

    private Connection connection;

    public CelularDAO(Connection connection) {
        this.connection = connection;
    }

   
    public void insertar(Celular celular) throws SQLException {
        if (celular.getPrecio() <= 0 || celular.getStock() < 0) {
            throw new IllegalArgumentException("Precio y stock deben ser positivos.");
        }

        String sql = "INSERT INTO celulares (marca, modelo, sistema_operativo, gama, precio, stock) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, celular.getMarca());
            ps.setString(2, celular.getModelo());
            ps.setString(3, celular.getSistemaOperativo());
            ps.setString(4, celular.getGama());
            ps.setDouble(5, celular.getPrecio());
            ps.setInt(6, celular.getStock());
            ps.executeUpdate();
        }
    }


    public void actualizar(Celular celular) throws SQLException {
        if (celular.getPrecio() <= 0 || celular.getStock() < 0) {
            throw new IllegalArgumentException("Precio y stock deben ser positivos.");
        }

        String sql = "UPDATE celulares SET marca=?, modelo=?, sistema_operativo=?, gama=?, precio=?, stock=? WHERE id=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, celular.getMarca());
            ps.setString(2, celular.getModelo());
            ps.setString(3, celular.getSistemaOperativo());
            ps.setString(4, celular.getGama());
            ps.setDouble(5, celular.getPrecio());
            ps.setInt(6, celular.getStock());
            ps.setInt(7, celular.getId());
            ps.executeUpdate();
        }
    }


    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM celulares WHERE id=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }


    public List<Celular> listar() throws SQLException {
        List<Celular> lista = new ArrayList<>();
        String sql = "SELECT * FROM celulares";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Celular c = new Celular();
                c.setId(rs.getInt("id"));
                c.setMarca(rs.getString("marca"));
                c.setModelo(rs.getString("modelo"));
                c.setSistemaOperativo(rs.getString("sistema_operativo"));
                c.setGama(rs.getString("gama"));
                c.setPrecio(rs.getDouble("precio"));
                c.setStock(rs.getInt("stock"));
                lista.add(c);
            }
        }
        return lista;
    }

    
    public Celular buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM celulares WHERE id=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Celular c = new Celular();
                    c.setId(rs.getInt("id"));
                    c.setMarca(rs.getString("marca"));
                    c.setModelo(rs.getString("modelo"));
                    c.setSistemaOperativo(rs.getString("sistema_operativo"));
                    c.setGama(rs.getString("gama"));
                    c.setPrecio(rs.getDouble("precio"));
                    c.setStock(rs.getInt("stock"));
                    return c;
                }
            }
        }
        return null;
    }

    
    public void reducirStock(int id, int cantidad) throws SQLException {
        String sql = "UPDATE celulares SET stock = stock - ? WHERE id=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, cantidad);
            ps.setInt(2, id);
            ps.executeUpdate();
        }
    }
}
