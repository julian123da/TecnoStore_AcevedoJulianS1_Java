package UTILADADES;

import MODELO.Venta;
import MODELO.DetalleVenta;
import java.util.*;
import java.util.stream.Collectors;

public class ReporteVentasUtils {

    // Top 3 celulares más vendidos
    public static void top3CelularesMasVendidos(List<DetalleVenta> detalles) {

        System.out.println("\n--- TOP 3 CELULARES MÁS VENDIDOS ---");

        Map<String, Integer> conteo = detalles.stream()
                .collect(Collectors.groupingBy(
                        DetalleVenta::getCelular,
                        Collectors.summingInt(DetalleVenta::getCantidad)
                ));

        conteo.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(3)
                .forEach(e ->
                        System.out.println("Celular ID: " + e.getKey() +
                                " | Vendidos: " + e.getValue())
                );
    }

    // Ventas totales por mes
    public static void ventasPorMes(List<Venta> ventas) {

        System.out.println("\n--- VENTAS TOTALES POR MES ---");

        Map<String, Double> totalPorMes = ventas.stream()
                .collect(Collectors.groupingBy(
                        v -> v.getFecha().substring(0, 7),
                        Collectors.summingDouble(Venta::getTotal)
                ));

        totalPorMes.forEach((mes, total) ->
                System.out.println("Mes: " + mes + " | Total: $" + total)
        );
    }
}
