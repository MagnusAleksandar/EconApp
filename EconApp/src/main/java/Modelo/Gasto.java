package Modelo;

public class Gasto {
    private double costo;
    private String descrip;

    public Gasto(double costo, String descrip) {
        this.costo=costo;
        this.descrip=descrip;
    }
    
    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public String getDescrip() {
        return descrip;
    }
}
