package CONTROLADOR;

import DAO.CelularDAO;
import MODELO.Celular;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class GestorCelulares {

    private CelularDAO dao;

    public GestorCelulares(Connection connection) {
        dao = new CelularDAO(connection);
    }

    public void registrarCelular(Celular c) {
        try {
            if (c.getPrecio() <= 0 || c.getStock() < 0) {
                System.out.println("Precio y stock deben ser positivos.");
                return;
            }
            dao.insertar(c);
           System.out.println("=================================");
           System.out.println("CELULAR REGISTRADO CON ÉXITO ");
           System.out.println("=================================");

        } catch (SQLException e) {
            System.out.println("Error al insertar: " + e.getMessage());
        }
    }

    public void listarCelulares() {
        try {
            List<Celular> lista = dao.listar();

            
            System.out.printf("%-5s %-15s %-15s %-10s %-10s %-12s %-6s%n",
                              "ID", "Marca", "Modelo", "SO", "Gama", "Precio", "Stock");
            System.out.println("----------------------------------------------------------------------------");

           
            for (Celular c : lista) {
                System.out.printf("%-5d %-15s %-15s %-10s %-10s %-12.2f %-6d%n",
                                  c.getId(), c.getMarca(), c.getModelo(),
                                  c.getSistemaOperativo(), c.getGama(),
                                  c.getPrecio(), c.getStock());
            }
        } catch (SQLException e) {
            System.out.println("Error al listar: " + e.getMessage());
        }
    }

    public void actualizarCelular(Celular c) {
        try {
            dao.actualizar(c);
            System.out.println("Celular actualizado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al actualizar: " + e.getMessage());
        }
    }

    public void eliminarCelular(int id) {
        try {
            dao.eliminar(id);
            System.out.println("Celular eliminado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al eliminar: " + e.getMessage());
        }
    }

    public void mostrarReporteStockBajo() {
        try {
            List<Celular> lista = dao.listar();
            System.out.println("\n--- CELULARES CON STOCK BAJO (<=5) ---");
            System.out.printf("%-5s %-15s %-15s %-6s%n", "ID", "Marca", "Modelo", "Stock");
            System.out.println("-------------------------------------------");

            for (Celular c : lista) {
                if (c.getStock() <= 5) {
                    System.out.printf("%-5d %-15s %-15s %-6d%n",
                                      c.getId(), c.getMarca(), c.getModelo(), c.getStock());
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al generar reporte de stock bajo: " + e.getMessage());
        }
    }
}
