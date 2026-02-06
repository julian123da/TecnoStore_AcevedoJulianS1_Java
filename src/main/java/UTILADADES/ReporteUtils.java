package UTILADADES;

import MODELO.Celular;
import java.util.List;
import java.util.stream.Collectors;

public class ReporteUtils {

    public static void mostrarStockBajo(List<Celular> celulares) {
        System.out.println("\n Celulares con stock bajo:");

        List<Celular> bajos = celulares.stream()
                .filter(c -> c.getStock() < 5)
                .collect(Collectors.toList());

        if (bajos.isEmpty()) {
            System.out.println("No hay celulares con stock bajo.");
        } else {
            bajos.forEach(System.out::println);
        }
    }
}
