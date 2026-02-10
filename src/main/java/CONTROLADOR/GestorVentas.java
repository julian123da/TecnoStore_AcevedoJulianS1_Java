package CONTROLADOR;

import DAO.VentaDAO;
import DAO.CelularDAO;
import MODELO.Venta;
import MODELO.DetalleVenta;
import MODELO.Celular;
import java.sql.Connection;
import java.sql.SQLException;

public class GestorVentas {

    private final VentaDAO ventaDAO;
    private final CelularDAO celularDAO;
    private final Connection connection;

    public GestorVentas(Connection connection) {
        this.connection = connection;
        this.ventaDAO = new VentaDAO(connection);
        this.celularDAO = new CelularDAO(connection);
    }

    public void registrarVenta(Venta v, DetalleVenta dv) {
        try {
            connection.setAutoCommit(false);

            Celular celular = celularDAO.buscarPorId(
                    Integer.parseInt(dv.getCelular())
            );

            if (celular == null) {
                System.out.println("Celular no encontrado.");
                connection.rollback();
                return;
            }

            if (celular.getStock() < dv.getCantidad()) {
                System.out.println("Stock insuficiente.");
                connection.rollback();
                return;
            }

            double subtotal = celular.getPrecio() * dv.getCantidad();
            subtotal = Math.round(subtotal * 100.0) / 100.0;
            dv.setSubtotal(subtotal);

            double iva = Math.round(subtotal * 0.19 * 100.0) / 100.0;
            double total = Math.round((subtotal + iva) * 100.0) / 100.0;
            v.setTotal(total);

            ventaDAO.insertarVenta(v);

            dv.setVenta(String.valueOf(v.getId()));
            ventaDAO.insertarDetalle(dv);

            celularDAO.reducirStock(
                    Integer.parseInt(dv.getCelular()),
                    dv.getCantidad()
            );

            connection.commit();

            System.out.println("Venta registrada correctamente.");
            System.out.println("Subtotal: " + subtotal);
            System.out.println("IVA (19%): " + iva);
            System.out.println("Total: " + total);

        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                System.out.println("Error al hacer rollback.");
            }
            System.out.println("Error al registrar venta: " + e.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                System.out.println("Error al restaurar autocommit.");
            }
        }
    }
}
