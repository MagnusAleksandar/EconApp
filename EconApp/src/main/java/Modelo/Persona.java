package Modelo;

public class Persona {
    private boolean contribuye;
    private String nombre;

    public Persona(boolean cont, String nom) {
        this.contribuye=cont;
        this.nombre=nom;
    }

    public boolean getContribuye() {
        return contribuye;
    }

    public void setContribuye(boolean contribuye) {
        this.contribuye = contribuye;
    }

    public String getNombre() {
        return nombre;
    }
}
