package DAO;

import MODELO.DetalleVenta;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DetalleVentaDAO {

    private Connection connection;

    public DetalleVentaDAO(Connection connection) {
        this.connection = connection;
    }

    public void insertar(DetalleVenta detalle) throws SQLException {
        if (detalle.getCantidad() <= 0 || detalle.getSubtotal() < 0) {
            throw new IllegalArgumentException("Cantidad y subtotal deben ser positivos.");
        }

        String sql = "INSERT INTO detalle_ventas (id_venta, id_celular, cantidad, subtotal) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(detalle.getVenta()));   // id de la venta
            ps.setInt(2, Integer.parseInt(detalle.getCelular())); // id del celular
            ps.setInt(3, detalle.getCantidad());
            ps.setDouble(4, detalle.getSubtotal());
            ps.executeUpdate();
        }
    }

    
    public List<DetalleVenta> listar() throws SQLException {
        List<DetalleVenta> lista = new ArrayList<>();
        String sql = "SELECT * FROM detalle_ventas";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                DetalleVenta d = new DetalleVenta();
                d.setId(rs.getInt("id"));
                d.setVenta(String.valueOf(rs.getInt("id_venta")));
                d.setCelular(String.valueOf(rs.getInt("id_celular")));
                d.setCantidad(rs.getInt("cantidad"));
                d.setSubtotal(rs.getDouble("subtotal"));
                lista.add(d);
            }
        }
        return lista;
    }

    public DetalleVenta buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM detalle_ventas WHERE id=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    DetalleVenta d = new DetalleVenta();
                    d.setId(rs.getInt("id"));
                    d.setVenta(String.valueOf(rs.getInt("id_venta")));
                    d.setCelular(String.valueOf(rs.getInt("id_celular")));
                    d.setCantidad(rs.getInt("cantidad"));
                    d.setSubtotal(rs.getDouble("subtotal"));
                    return d;
                }
            }
        }
        return null;
    }

    
    public List<DetalleVenta> listarPorVenta(int idVenta) throws SQLException {
        List<DetalleVenta> lista = new ArrayList<>();
        String sql = "SELECT * FROM detalle_ventas WHERE id_venta=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idVenta);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    DetalleVenta d = new DetalleVenta();
                    d.setId(rs.getInt("id"));
                    d.setVenta(String.valueOf(rs.getInt("id_venta")));
                    d.setCelular(String.valueOf(rs.getInt("id_celular")));
                    d.setCantidad(rs.getInt("cantidad"));
                    d.setSubtotal(rs.getDouble("subtotal"));
                    lista.add(d);
                }
            }
        }
        return lista;
    }

    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM detalle_ventas WHERE id=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
