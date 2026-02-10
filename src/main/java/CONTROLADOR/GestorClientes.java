package CONTROLADOR;

import DAO.ClienteDAO;
import MODELO.Cliente;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class GestorClientes {

    private ClienteDAO dao;

    public GestorClientes(Connection connection) {
        dao = new ClienteDAO(connection);
    }

    public void registrarCliente(Cliente c) {
        if (!c.getCorreo().contains("@")) {
            System.out.println("Correo inválido.");
            return;
        }
        try {
            dao.insertar(c);
            System.out.println("Cliente registrado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al insertar: " + e.getMessage());
        }
    }

    // 🔧 ALGUNAS MEJORAS PARA QUE SE VEA BIEN AL MOMENTO DE MOSTRAR LOS CLIENTES
    public void listarClientes() {
        try {
            List<Cliente> lista = dao.listar();

            if (lista.isEmpty()) {
                System.out.println("\n⚠ No hay clientes registrados.\n");
                return;
            }

            System.out.println("\n================= LISTA DE CLIENTES =================");
            System.out.printf("%-5s %-15s %-18s %-25s %-15s%n",
                    "ID", "NOMBRE", "IDENTIFICACIÓN", "CORREO", "TELÉFONO");
            System.out.println("-----------------------------------------------------");

            for (Cliente c : lista) {
                System.out.printf("%-5d %-15s %-18s %-25s %-15s%n",
                        c.getId(),
                        c.getNombre(),
                        c.getIdentificacion(),
                        c.getCorreo(),
                        c.getTelefono());
            }

            System.out.println("=====================================================\n");

        } catch (SQLException e) {
            System.out.println("Error al listar: " + e.getMessage());
        }
    }

    public void actualizarCliente(Cliente c) {
        if (!c.getCorreo().contains("@")) {
            System.out.println("Correo inválido.");
            return;
        }
        try {
            dao.actualizar(c);
            System.out.println("Cliente actualizado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al actualizar: " + e.getMessage());
        }
    }

    public void eliminarCliente(int id) {
        try {
            dao.eliminar(id);
            System.out.println("Cliente eliminado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al eliminar: " + e.getMessage());
        }
    }
}
