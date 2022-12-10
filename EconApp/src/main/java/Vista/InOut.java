package Vista;

import javax.swing.JOptionPane;

public class InOut {
    public String solicitarString(String m){
        String d=JOptionPane.showInputDialog(m);
        return d;
    }
    
    public double solicitarDouble(String m){
        String d=JOptionPane.showInputDialog(m);
        double dato=Double.parseDouble(d);
        return dato;
    }
    
    public int solicitarEntero(String m){
        String d=JOptionPane.showInputDialog(m);
        int dato=Integer.parseInt(d);
        return dato;
    }
    
    public void mostrarRes(String m){
            JOptionPane.showMessageDialog(null, m);
        }
}
