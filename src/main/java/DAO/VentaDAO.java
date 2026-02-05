package DAO;

import MODELO.Venta;
import MODELO.DetalleVenta;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VentaDAO {

    private Connection connection;

    public VentaDAO(Connection connection) {
        this.connection = connection;
    }

    public void insertarVenta(Venta v) throws SQLException {
        String sql = "INSERT INTO ventas (id_cliente, fecha, total) VALUES (?,?,?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, Integer.parseInt(v.getCliente())); // asumimos que cliente es el id
            ps.setString(2, v.getFecha());
            ps.setDouble(3, v.getTotal());
            ps.executeUpdate();

            // Obtener id generado
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    v.setId(rs.getInt(1));
                }
            }
        }
    }

    public void insertarDetalle(DetalleVenta dv) throws SQLException {
        String sql = "INSERT INTO detalle_ventas (id_venta, id_celular, cantidad, subtotal) VALUES (?,?,?,?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(dv.getVenta())); // id venta
            ps.setInt(2, Integer.parseInt(dv.getCelular())); // id celular
            ps.setInt(3, dv.getCantidad());
            ps.setDouble(4, dv.getSubtotal());
            ps.executeUpdate();
        }
    }

    public List<Venta> listarVentas() throws SQLException {
        List<Venta> lista = new ArrayList<>();
        String sql = "SELECT * FROM ventas";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Venta v = new Venta(
                        rs.getInt("id"),
                        String.valueOf(rs.getInt("id_cliente")),
                        rs.getString("fecha"),
                        rs.getDouble("total")
                );
                lista.add(v);
            }
        }
        return lista;
    }

    
}
