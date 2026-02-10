package DAO;

import MODELO.Venta;
import MODELO.DetalleVenta;
import MODELO.Cliente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VentaDAO {

    private final Connection connection;

    public VentaDAO(Connection connection) {
        this.connection = connection;
    }

    // ================= INSERTAR VENTA =================
    public void insertarVenta(Venta v) throws SQLException {
        String sql = "INSERT INTO ventas (id_cliente, fecha, total) VALUES (?,?,?)";

        try (PreparedStatement ps = connection.prepareStatement(
                sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, v.getCliente().getId());
            ps.setString(2, v.getFecha());
            ps.setDouble(3, v.getTotal());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    v.setId(rs.getInt(1));
                }
            }
        }
    }

    // ================= INSERTAR DETALLE =================
    public void insertarDetalle(DetalleVenta dv) throws SQLException {
        String sql = "INSERT INTO detalle_ventas (id_venta, id_celular, cantidad, subtotal) VALUES (?,?,?,?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(dv.getVenta()));
            ps.setInt(2, Integer.parseInt(dv.getCelular()));
            ps.setInt(3, dv.getCantidad());
            ps.setDouble(4, dv.getSubtotal());
            ps.executeUpdate();
        }
    }

    // ================= LISTAR VENTAS =================
    public List<Venta> listarVentas() throws SQLException {
        List<Venta> ventas = new ArrayList<>();
        String sql = "SELECT * FROM ventas";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {

                Cliente cliente = new Cliente(
                        rs.getInt("id_cliente"),
                        "",
                        "",
                        "",
                        ""
                );

                Venta v = new Venta(
                        rs.getInt("id"),
                        cliente,
                        rs.getString("fecha"), (int) rs.getDouble("total"));

                ventas.add(v);
            }
        }
        return ventas;
    }
}
