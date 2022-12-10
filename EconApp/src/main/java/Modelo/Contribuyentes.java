package Modelo;

public class Contribuyentes extends Persona {
    private double contribucion;

    public Contribuyentes(boolean cont, String nom, double cntrbcn) {
        super(cont, nom);
        this.contribucion=cntrbcn;
    }

    public double getContribucion() {
        return contribucion;
    }

    public void setContribucion(double contribucion) {
        this.contribucion = contribucion;
    }
}
