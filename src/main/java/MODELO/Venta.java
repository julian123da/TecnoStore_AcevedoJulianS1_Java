package MODELO;

public class Venta {
    private int id;
    private Cliente cliente;
    private String fecha;
    private double total;

    public Venta(int id, Cliente cliente, String fecha, int total) {
        this.cliente = cliente;
        this.id = id;
        this.fecha = fecha;
        this.total = total;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
