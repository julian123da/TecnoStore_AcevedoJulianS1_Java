package MODELO;

public class Celular {
    
    private int id;
    private String marca;
    private String modelo;
    private double precio;
    private int stock;
    private String sistemaOperativo;
    private String gama;
    
    public Celular() {
    }
    
    public Celular(int id, String marca, String modelo, double precio, int stock, String sistemaOperativo, String gama ) {
        
        
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.precio = precio;
        this.stock = stock;
        this.sistemaOperativo = sistemaOperativo;
        this.gama = gama;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getSistemaOperativo() {
        return sistemaOperativo;
    }

    public void setSistemaOperativo(String sistemaOperativo) {
        this.sistemaOperativo = sistemaOperativo;
    }

    public String getGama() {
        return gama;
    }

    public void setGama(String gama) {
        this.gama = gama;
    }
    
@Override
    public String toString() {
        return """
               nombre  %s
               Apellido %s
               edad   %s
               deudas   %s
               """.formatted(id,marca,modelo,precio,stock,sistemaOperativo,gama);
        
    }
}         