package CONTROLADOR;

import DAO.VentaDAO;
import DAO.CelularDAO;
import MODELO.Venta;
import MODELO.DetalleVenta;
import java.sql.Connection;
import java.sql.SQLException;

public class GestorVentas {

    private VentaDAO ventaDAO;
    private CelularDAO celularDAO;

    public GestorVentas(Connection connection) {
        ventaDAO = new VentaDAO(connection);
        celularDAO = new CelularDAO(connection);
    }

    public void registrarVenta(Venta v, DetalleVenta dv) {
        try {
            double subtotalConIVA = dv.getSubtotal() * 1.19;
            v.setTotal(subtotalConIVA);
            ventaDAO.insertarVenta(v);
            dv.setVenta(String.valueOf(v.getId()));
            ventaDAO.insertarDetalle(dv);
            celularDAO.reducirStock(Integer.parseInt(dv.getCelular()), dv.getCantidad());
            System.out.println("Venta registrada correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al registrar venta: " + e.getMessage());
        }
    }
}
