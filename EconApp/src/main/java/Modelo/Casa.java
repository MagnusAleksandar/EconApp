package Modelo;
import Vista.InOut;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Casa {
    static InOut v=new InOut();
    double ahorro,gt;
    Contribuyentes co[];
    Gasto g[];
    Persona p[];
    double ToAporte,ToGastos;
    private ArrayList<Persona> personas;
    private ArrayList<Contribuyentes> cont;
    private ArrayList<Gasto> gast;
 
    public File gastos, ahorros;
 
    public void pedirIntegrantes(int ban){
        int in, a, prc;
        String nom;
        boolean b=false;
        double slr, cnt=0;
        in=v.solicitarEntero("Ingrese cantidad de integrantes: ");
        in=personas.size();
        do{
            for (Persona persona : personas) {
                nom=v.solicitarString("Nombre:");
                do{
                    if(ban==0)a=1;
                    else a=v.solicitarEntero("¿Es aportante?(1: si/0: no)");
                }while(a<0||a>1);
                if(a==1){
                    persona.setContribuye(false);
                    b=persona.getContribuye();
                    personas.add(new Persona(b, nom));
                }else{
                    b=persona.getContribuye();
                    slr=v.solicitarDouble("Ingrese el salario del contribuyente");
                    prc=v.solicitarEntero("Ingrese el porcentaje del salario a aportar");
                    prc=prc/100;
                    cnt=slr-slr*prc;
                    cont.add(new Contribuyentes(b,nom,cnt));
                }
                ToAporte+=cnt;
            }
        }while(in<=20&&in>1);
    }
    
    public void PedirGastos(){
       String des;
       double gas;
       boolean b;
       int i=0, a;
       
       do{
            des=v.solicitarString("Ingrese descripcion del gasto:");
            gas=v.solicitarDouble("Ingrese valor del gasto:");
            gast.add(new Gasto(gas, des));
            
            a=v.solicitarEntero("¿Desea agregar otro gasto? (1: si/0: no)");
       }while(a==1);
    }

    

    public void mostrarLInt(){
        String m="";
        for(Persona per:personas){
            if(!per.getContribuye())
                m+="Nombre: "+per.getNombre()+".";
            
        }
        v.mostrarRes(m);
    }
    public void mostrarLCont(){
        String m="";
        for(Contribuyentes con:cont){
            if(con.getContribuye())
                m+="Nombre: "+con.getNombre()+" Contribucion: "+con.getContribucion();
        }
        v.mostrarRes(m);
    }
 
    public void mostrarLGast(){
        String m="";
        for(Gasto gst:gast){
            m+=gst.getDescrip()+" $"+gst.getCosto();
        }
        v.mostrarRes(m);
    }

    public void crearArchivo(int c){
        try{
            if(c==1) gastos=new File("../gastos.txt");
                else ahorros=new File("../ahorros.txt");
        }catch(Exception e){
  
          e.printStackTrace();
        }
    }
    
    public void escribirArchivo(double valor, File arch){
        try{
            FileWriter l=new FileWriter(arch);
            l.write(valor+"\n");
            l.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public String leerArchivo(File f){
 String linea=null;
        try{
            FileReader fr=new FileReader(f);
            BufferedReader br=new BufferedReader(fr);
            
             while((linea=br.readLine())!=null){
                 System.out.println (linea + "\n");
                 
           }
          fr.close();
        }catch(Exception e){
           e.printStackTrace();
           return linea;
        }
        return linea;
    }

    public void compGastos() throws IOException{
        //compGastos(PedirGastos());
        String b=leerArchivo(gastos);
        if(b==null){
            escribirArchivo(0,gastos);
        }else{
           escribirArchivo(ToGastos,gastos);

           double GastosPas=Double.parseDouble(b);
           double aux=GastosPas;
           aux=+ToGastos;
           v.mostrarRes("Los gastos de los meses pasados fueron de un total acumulado de "
                            +GastosPas+"pesos, y los de este mes fueron de "+ToGastos+
                            " pesos. Todo esto"+ "nos da un total de "+aux+" pesos.");
           gastos.delete();
           crearArchivo(1);
           escribirArchivo(aux,gastos);
        }
        }


    public void calcularAhorro(){
        double restante=ToAporte-ToGastos;
        if(restante<0){
            v.mostrarRes("Sus deudas/gastos son mayores a la cantidad de ingresos que tiene.");
           
        }else{

            do{
                v.mostrarRes("El restante de sus ingresos es de "+restante+" pesos");
            ahorro=v.solicitarDouble("¿Cuanto de esto que le sobro desea depositar a su ahorro?");
            validarAhorro(restante,ahorro);
            }while(!validarAhorro(restante,ahorro));

        }

    }
    boolean validarAhorro(double restante, double ahorro){
        boolean ban=true;
        if(restante<ahorro){
            v.mostrarRes("Ingrese una cantidad valida");
            ban=false;
        }
        return ban;
    }

    public void compAhorros() throws IOException{
        //compGastos(PedirGastos());    
        String b=leerArchivo(ahorros);
        if(b==null){
            escribirArchivo(0,ahorros);
        }else{
           escribirArchivo(ahorro,ahorros);
           double ahorrosPas=Double.parseDouble(b);
           double aux=ahorrosPas;
           aux=+ahorro;
           v.mostrarRes("Los ahorros de los meses pasados fueron de un total acumulado de "
                            +ahorrosPas+"pesos, y los de este mes fueron de "+ahorro+
                            " pesos. Todo esto"+ "nos da un total de "+aux+" pesos.");
           ahorros.delete();
           crearArchivo(2);
           escribirArchivo(aux,ahorros);
        }
        }

    public void menu() throws IOException{
        if(personas==null||leerArchivo(gastos)==null){pedirIntegrantes(1); PedirGastos();}
        else{
            int op;
            do{
                op=v.solicitarEntero("Bienvenido\n ¿Cual de las siguientes acciones desea realizar?"
                    + "\n1. Mostrar onformacion."
                    + "\n2. Agregar aportantantes."
                    + "\n3. Comparar los gastos actuales con los pasados."
                    + "\n4. Comparar los ahorros actuaes con los pasados"
                    + "\n5. Ahorrar dinero."
                    + "\n6. Agregar gasto"
                    + "\n7. Salir"); 
                switch(op){
                case 1:
                    mostrarLInt();
                    mostrarLCont();
                    mostrarLGast();break;
                case 2://0 para ingresar aportante
                    pedirIntegrantes(0) ;break;
                case 3:compGastos();break;
                case 4:compAhorros();break;
                case 5:calcularAhorro();break;
                case 6:PedirGastos();break;
                case 7:;break;
                default:;break;
            }
            }while(op!=7);}
        }
    }