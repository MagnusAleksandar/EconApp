package Control;
import Modelo.Casa;
import Vista.InOut;

public class Ejecutar {
    static Casa ob1=new Casa();
    static InOut ob2=new InOut();
    
    public void menu(int op){
        
        /*switch(op){
            case 1: ob1.; break;
            case 2: ob1.; break;
            case 3: ob1.; break;
        }*/
    }
    
    public static void main(String[] args) {
        int op;
        do{
            op=ob2.solicitarEntero("Elija una opcion:"
                    + "\n1. Determinar la cantidad de personas que hay en una empresa y que se llaman Maria del Perpetuo Socorro y que ademas tienen una edad entre 30 y 50 años."
                    + "\n2. Determinar que letras del abecedario no tiene una frase ingresada por el usuario."
                    + "\n3. Determinar si una palabra a ingresar hace parte de una serie de frases guardada en una lista, y si con las letras que hay en otra lista se puede formar "
                    + "la palabra ingresada.");
        }while(op<=3&&op>0);
    }
    
}
