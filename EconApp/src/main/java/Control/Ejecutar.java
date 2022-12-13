package Control;
import Modelo.Casa;
import Vista.InOut;
import java.io.IOException;

public class Ejecutar {
    static Casa ob1=new Casa();
    static InOut ob2=new InOut();
    
    public static void main(String[] args) throws IOException {
        ob1.menu();
    }
    
}
