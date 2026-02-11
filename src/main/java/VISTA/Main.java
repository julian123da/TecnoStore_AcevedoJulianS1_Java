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

        try (Scanner sc = new Scanner(System.in)) {

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

                switch (opcion) {

                    // ================= CELULARES =================
                    case 1 -> {
                        int subOpcionCelular;

                        do {
                            System.out.println("\n--- GESTIÓN DE CELULARES ---");
                            System.out.println("1. Registrar celular");
                            System.out.println("2. Listar celulares");
                            System.out.println("3. Actualizar celular");
                            System.out.println("4. Eliminar celular");
                            System.out.println("5. Volver al menú principal");
                            System.out.print("Elige una opción: ");

                            subOpcionCelular = sc.nextInt();
                            sc.nextLine();

                            switch (subOpcionCelular) {

                                case 1 -> {
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

                                    Celular c = new Celular(
                                            0, marca, modelo, precio, stock, so, gama
                                    );

                                    gestorCelular.registrarCelular(c);
                                }

                                case 2 ->
                                    gestorCelular.listarCelulares();

                                case 3 -> {
                                    System.out.print("ID del celular a actualizar: ");
                                    int idActualizar = sc.nextInt();
                                    sc.nextLine();
                                    System.out.print("Nueva marca: ");
                                    String nuevaMarca = sc.nextLine();
                                    System.out.print("Nuevo modelo: ");
                                    String nuevoModelo = sc.nextLine();
                                    System.out.print("Nuevo sistema operativo: ");
                                    String nuevoSO = sc.nextLine();
                                    System.out.print("Nueva gama: ");
                                    String nuevaGama = sc.nextLine();
                                    System.out.print("Nuevo precio: ");
                                    double nuevoPrecio = sc.nextDouble();
                                    System.out.print("Nuevo stock: ");
                                    int nuevoStock = sc.nextInt();
                                    sc.nextLine();

                                    Celular cActualizar = new Celular(
                                            idActualizar,
                                            nuevaMarca,
                                            nuevoModelo,
                                            nuevoPrecio,
                                            nuevoStock,
                                            nuevoSO,
                                            nuevaGama
                                    );

                                    gestorCelular.actualizarCelular(cActualizar);
                                }

                                case 4 -> {
                                    System.out.print("ID del celular a eliminar: ");
                                    int idEliminar = sc.nextInt();
                                    sc.nextLine();
                                    gestorCelular.eliminarCelular(idEliminar);
                                }

                                case 5 ->
                                    System.out.println("Volviendo al menú principal...");
                                default ->
                                    System.out.println("Opción inválida.");
                            }

                        } while (subOpcionCelular != 5);
                    }

                    // ================= CLIENTES =================
                    case 2 -> {
                        int subOpcionCliente;

                        do {
                            System.out.println("\n--- GESTIÓN DE CLIENTES ---");
                            System.out.println("1. Registrar cliente");
                            System.out.println("2. Listar clientes");
                            System.out.println("3. Actualizar cliente");
                            System.out.println("4. Eliminar cliente");
                            System.out.println("5. Volver al menú principal");
                            System.out.print("Elige una opción: ");

                            subOpcionCliente = sc.nextInt();
                            sc.nextLine();

                            switch (subOpcionCliente) {

                                case 1 -> {
                                    System.out.print("Nombre: ");
                                    String nombre = sc.nextLine();
                                    System.out.print("Identificación: ");
                                    String identificacion = sc.nextLine();
                                    System.out.print("Correo: ");
                                    String correo = sc.nextLine();
                                    System.out.print("Teléfono: ");
                                    String tel = sc.nextLine();

                                    Cliente cl = new Cliente(
                                            0, nombre, identificacion, correo, tel
                                    );

                                    gestorCliente.registrarCliente(cl);
                                }

                                case 2 ->
                                    gestorCliente.listarClientes();

                                case 3 -> {
                                    System.out.print("ID del cliente a actualizar: ");
                                    int idActualizarCliente = sc.nextInt();
                                    sc.nextLine();
                                    System.out.print("Nuevo nombre: ");
                                    String nuevoNombre = sc.nextLine();
                                    System.out.print("Nueva identificación: ");
                                    String nuevaIdentificacion = sc.nextLine();
                                    System.out.print("Nuevo correo: ");
                                    String nuevoCorreo = sc.nextLine();
                                    System.out.print("Nuevo teléfono: ");
                                    String nuevoTel = sc.nextLine();

                                    Cliente clActualizar = new Cliente(
                                            idActualizarCliente,
                                            nuevoNombre,
                                            nuevaIdentificacion,
                                            nuevoCorreo,
                                            nuevoTel
                                    );

                                    gestorCliente.actualizarCliente(clActualizar);
                                }

                                case 4 -> {
                                    System.out.print("ID del cliente a eliminar: ");
                                    int idEliminarCliente = sc.nextInt();
                                    sc.nextLine();
                                    gestorCliente.eliminarCliente(idEliminarCliente);
                                }

                                case 5 ->
                                    System.out.println("Volviendo al menú principal...");
                                default ->
                                    System.out.println("Opción inválida.");
                            }

                        } while (subOpcionCliente != 5);
                    }

                    // ================= VENTAS =================
                    case 3 -> {
                        System.out.print("ID Cliente: ");
                        int idCliente = sc.nextInt();

                        System.out.print("ID Celular: ");
                        String idCelular = sc.next();

                        System.out.print("Cantidad: ");
                        int cantidad = sc.nextInt();
                        sc.nextLine();

                        Venta v = new Venta(
                                0,
                                new Cliente(idCliente, "", "", "", ""),
                                "2026-02-05",
                                0
                        );

                        DetalleVenta dv = new DetalleVenta(
                                0,
                                String.valueOf(idCliente),
                                idCelular,
                                cantidad,
                                0
                        );

                        gestorVenta.registrarVenta(v, dv);
                    }

                    case 4 ->
                        System.out.println("Saliendo...");
                    default ->
                        System.out.println("Opción inválida.");
                }

            } while (opcion != 4);
        }
    }
}
