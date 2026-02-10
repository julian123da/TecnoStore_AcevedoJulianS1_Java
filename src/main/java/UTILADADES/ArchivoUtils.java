package UTILADADES;

import MODELO.Venta;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ArchivoUtils {

    private static final String RUTA = "reporte_ventas.txt";

    public static void generarReporteVentas(List<Venta> ventas) {
        try (FileWriter fw = new FileWriter(RUTA)) {

            fw.write("===== REPORTE DE VENTAS TECNOSTORE =====\n\n");

            for (Venta v : ventas) {
                fw.write("ID Venta: " + v.getId() + "\n");
                fw.write("Cliente: " + v.getCliente() + "\n");
                fw.write("Fecha: " + v.getFecha() + "\n");
                fw.write("Total: $" + v.getTotal() + "\n");
                fw.write("----------------------------------\n");
            }

            System.out.println("Archivo reporte_ventas.txt generado correctamente.");

        } catch (IOException e) {
            System.out.println("Error al generar archivo: " + e.getMessage());
        }
    }
}
