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
            System.out.println("Celular registrado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al insertar: " + e.getMessage());
        }
    }

    public void listarCelulares() {
        try {
            List<Celular> lista = dao.listar();
            for (Celular c : lista) {
                System.out.println(c);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar: " + e.getMessage());
        }
    }

    
}
