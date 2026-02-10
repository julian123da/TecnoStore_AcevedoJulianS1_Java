package MODELO;

public class DetalleVenta {
    
    private int id;
    private String venta;
    private String Celular;
    private int cantidad;
    private double subtotal;
    
    public DetalleVenta() {
        
        
    }

    public DetalleVenta(int id, String venta, String Celular, int cantidad, double subtotal) {
        this.id = id;
        this.venta = venta;
        this.Celular = Celular;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVenta() {
        return venta;
    }

    public void setVenta(String venta) {
        this.venta = venta;
    }

    public String getCelular() {
        return Celular;
    }

    public void setCelular(String Celular) {
        this.Celular = Celular;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
    
    
    @Override
    public String toString() {
        return """
               nombre  %s
               Apellido %s
               edad   %s
               deudas   %s
               """.formatted(id,venta,Celular,cantidad,subtotal);
        
    }
}