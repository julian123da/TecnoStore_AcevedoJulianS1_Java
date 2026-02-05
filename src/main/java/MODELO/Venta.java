package MODELO;

public class Venta {
    
    
    private int id;
    private String cliente;
    private String fecha;
    private double total;

    public Venta(int id, String cliente, String fecha, double total) {
        this.id = id;
        this.cliente = cliente;
        this.fecha = fecha;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return """
               nombre  %s
               Apellido %s
               edad   %s
               deudas   %s
               """.formatted(id,cliente,fecha,total);
        
    }

}
