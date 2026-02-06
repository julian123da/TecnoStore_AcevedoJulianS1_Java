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

    public GestorVentas(Connection connection) {
        ventaDAO = new VentaDAO(connection);
        celularDAO = new CelularDAO(connection);
    }

    public void registrarVenta(Venta v, DetalleVenta dv) {
        try {
            Celular celular = celularDAO.buscarPorId(
                    Integer.parseInt(dv.getCelular())
            );

            if (celular == null) {
                System.out.println("Celular no encontrado.");
                return;
            }

            if (celular.getStock() < dv.getCantidad()) {
                System.out.println("Stock insuficiente.");
                return;
            }

           
            double subtotal = celular.getPrecio() * dv.getCantidad();
            subtotal = Math.round(subtotal * 100.0) / 100.0; // 2 decimales
            dv.setSubtotal(subtotal);

            
            double iva = subtotal * 0.19;
            iva = Math.round(iva * 100.0) / 100.0; 

            double total = subtotal + iva;
            total = Math.round(total * 100.0) / 100.0; 
            v.setTotal(total);

           
            ventaDAO.insertarVenta(v);

            dv.setVenta(String.valueOf(v.getId()));
            ventaDAO.insertarDetalle(dv);

            // Con eso calculamos o reducimos el stock para ver si todavia tenemos celulares disponibles
            celularDAO.reducirStock(
                    Integer.parseInt(dv.getCelular()),
                    dv.getCantidad()
            );

            // Con esto podremos ver los resultados 
            System.out.println("Venta registrada correctamente.");
            System.out.println("Subtotal: " + subtotal);
            System.out.println("IVA (19%): " + iva);
            System.out.println("Total: " + total);

        } catch (SQLException e) {
            System.out.println("Error al registrar venta: " + e.getMessage());
        }
    }
}
