package VISTA;

import CONTROLADOR.GestorCelulares;
import CONTROLADOR.GestorClientes;
import CONTROLADOR.GestorVentas;
import MODELO.Celular;
import MODELO.Cliente;
import MODELO.Venta;
import MODELO.DetalleVenta;
import PERSISTENCIA.Conexion;
import java.sql.Connection;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Conexion conexion = new Conexion();
        Connection conn = conexion.conectar();

        GestorCelulares gestorCelular = new GestorCelulares(conn);
        GestorClientes gestorCliente = new GestorClientes(conn);
        GestorVentas gestorVenta = new GestorVentas(conn);

        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n=== MENU TECNOSTORE ===");
            System.out.println("1. Gestionar Celulares");
            System.out.println("2. Gestionar Clientes");
            System.out.println("3. Registrar Venta");
            System.out.println("4. Salir");
            System.out.print("Elige una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); 

            switch(opcion) {
                case 1:
                    System.out.print("Marca: ");
                    String marca = sc.nextLine();
                    System.out.print("Modelo: ");
                    String modelo = sc.nextLine();
                    System.out.print("Sistema Operativo: ");
                    String so = sc.nextLine();
                    System.out.print("Gama: ");
                    String gama = sc.nextLine();
                    System.out.print("Precio: ");
                    double precio = sc.nextDouble();
                    System.out.print("Stock: ");
                    int stock = sc.nextInt();
                    sc.nextLine();

                    Celular c = new Celular(0, marca, modelo, precio, stock, so, gama);
                    gestorCelular.registrarCelular(c);
                    break;

                case 2:
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("Apellido: ");
                    String apellido = sc.nextLine();
                    System.out.print("Correo: ");
                    String correo = sc.nextLine();
                    System.out.print("Telefono: ");
                    String tel = sc.nextLine();

                    Cliente cl = new Cliente(0, nombre, apellido, correo, tel);
                    gestorCliente.registrarCliente(cl);
                    break;

                case 3:
                    System.out.print("ID Cliente: ");
                    String idCliente = sc.nextLine();
                    System.out.print("ID Celular: ");
                    String idCelular = sc.nextLine();
                    System.out.print("Cantidad: ");
                    int cantidad = sc.nextInt();
                    System.out.print("Subtotal: ");
                    double subtotal = sc.nextDouble();
                    sc.nextLine();

                    Venta v = new Venta(0, idCliente, "2026-02-05", subtotal);
                    DetalleVenta dv = new DetalleVenta(0, idCliente, idCelular, cantidad, subtotal);
                    gestorVenta.registrarVenta(v, dv);
                    break;

                case 4:
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opción inválida.");
            }

        } while(opcion != 4);

        sc.close();
    }
}
