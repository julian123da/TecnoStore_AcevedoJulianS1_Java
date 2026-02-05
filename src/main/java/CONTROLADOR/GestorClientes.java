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

    public void listarClientes() {
        try {
            List<Cliente> lista = dao.listar();
            for (Cliente c : lista) {
                System.out.println(c);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar: " + e.getMessage());
        }
    }
}
