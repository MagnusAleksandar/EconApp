package Modelo;

public class Gasto {
    private static double costo;
    private static String descrip;

    public static double getCosto() {
        return costo;
    }

    public static void setCosto(double costo) {
        Gasto.costo = costo;
    }

    public static String getDescrip() {
        return descrip;
    }

    public static void setDescrip(String descrip) {
        Gasto.descrip = descrip;
    }
}
