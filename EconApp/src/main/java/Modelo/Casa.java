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
    private ArrayList<Contribuyente> cont;
    private ArrayList<Gasto> gast;
 
    public File gastos, ahorros;
 
    void pedirIntegrantes(){
       int in=0,c = 0;
       String nom;
       char a;
       boolean b;
       in=v.solicitarEntero("ingrese cantidad de integrantes: ");
         for(int i=0;i<in;i++){
             nom=v.solicitarString("Nombre:");
             a=v.pedirChar("Es aportante?(s/n)");
             b=validarChar(a);
             do{
                  if(!b){
                 v.mostrarRes("Ingrese una opcion valida");
             }else{
                 if(a=='s'||a=='S'){
                     pedirAportante(nom,c);
                 }
             }
             }while(!b);
                     }
   }
    void pediAportante(String nom,int c){
    double ap;
    ap=v.solicitarDouble("ingrese valor del aporte");
     co[c]=Contribuyente(nom,ap);
             ToAporte+=ap;
            

}


    void mostar(){
       String m="";
      for(int i=0;i<co.length;i++){
            m +="Nombre: " + p.getNom()+" aporte:"+co.getContribucion();
        }
       v.mostrarRes(m);
   }
    void PedirGastos(){
       String des;
       double gas;
       boolean b;
       int i=0;
       char a = 0;
       while(a!='N'||a!='n'){
           des=v.solicitarString("Ingrese descripcion del gasto:");
           gas=v.solicitarDouble("Ingrese valor del gasto:");
           
           do{
           a=v.pedirChar("Quiere Agregar otro gasto:");
           b=validarChar(a);
           if(!b)
             v.mostrarRes("ingrese una opcion valida");
       }while(!b);
           g[i]=Gasto(des,gas);
           i++;
           ToGastos+=gas;
       }
       
   }
    boolean validarChar(char a){
       if(a=='S' || a=='s' || a=='N' || 'n'==a){
           return true;
       }else{
           return false;
       }
   }


    void ListIntegrantes(){
   
    ArrayList<Persona> personas = new ArrayList<Persona>();
     int in=0;
       String nom = null;
       char a;
       boolean b;
       in=v.solicitarEntero("ingrese cantidad de integrantes: ");
         for(int i=0;i<in;i++){
             nom=v.solicitarString("Nombre:");
             a=v.pedirChar("Es aportante?(s/n)");
             b=validarChar(a);
             do{
                  if(!b){
                 v.mostrarRes("Ingrese una opcion valida");
             }else{
                 if(a=='s'||a=='S'){
                     ListAportante(nom);
                 }
             }
             }while(!b);
                     }
        personas.add(new Persona(nom));
}

    void ListAportante(String nom){
    ArrayList<Contribuyente> cont=new ArrayList<Contribuyente>();
    double ap;
    ap=v.solicitarDouble("ingrese valor del aporte");
             ToAporte+=ap;
         cont.add(new Contribuyentes(nom,ap));   

    }
    void ListGastos(){
    ArrayList<Gasto> gast=new ArrayList<Gasto>();
       String des;
       double gas;
       boolean b;
       int i=0;
       char a = 0;
       while(a!='N'||a!='n'){
           des=v.solicitarString("Ingrese descripcion del gasto:");
           gas=v.solicitarDouble("Ingrese valor del gasto:");
           
           do{
           a=v.pedirChar("Quiere Agregar otro gasto:");
           b=validarChar(a);
           if(!b)
             v.mostrarRes("ingrese una opcion valida");
       }while(!b);
          gast.add(new Gasto(des,gas));
           i++;
           ToGastos+=gas;
       }
       
   }

    void mostrarLInt(){
    
     for( int i=0;i<personas.length;i++)
     v.mostrarRes(personas.get(i));
    }
    void mostrarLCont(){
    
     for( int i=0;i<cont.length;i++)
     v.mostrarRes(cont.get(i));
    }
 
    void mostrarLGast(){
    
     for( int i=0;i<gast.length;i++)
     v.mostrarRes(gast.get(i));
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
        
        try{
            FileReader fr=new FileReader(f);
            BufferedReader br=new BufferedReader(fr);
             String linea=null;
             while((linea=br.readLine())!=null){
                 System.out.println (linea + "\n");
                 return linea;
           }
          fr.close();
        }catch(Exception e){
           e.printStackTrace();
        }
    }
    
    public void compGastos() throws IOException{
        //compGastos(PedirGastos());
        String b=leerArchivo(gastos);
        if(b==null){
            escribirArchivo(0,gastos);
        }else{
           escribirArchivo(gt,gastos);
           
           double GastosPas=Double.parseDouble(b);
           double aux=GastosPas;
           aux=+gt;
           v.mostrarRes("Los gastos de los meses pasados fueron de un total acumulado de "
                            +GastosPas+"pesos, y los de este mes fueron de "+gt+
                            " pesos. Todo esto"+ "nos da un total de "+aux+" pesos.");
           //gastos.delete();
           BufferedWriter bw = new BufferedWriter(new FileWriter(gastos));
           String l="";
           bw.write(l);
           bw.close();
           escribirArchivo(aux,gastos);
        }
        }
    
    
    public void calcularAhorro(double gastoMensual,double ingresoMensual){
        double restante=ingresoMensual-gastoMensual;
        if(restante<0){
            v.mostrarRes("""
                         Sus deudas/gastos son mayores a la cantidad de ingresos que tiene.
                          \nPor favor intente ingresar nuevamente la cantidad de diero que aporta cada uno.""");
            pideAportantes();//Cristo no la ha creado
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
           //gastos.delete();
           BufferedWriter bw = new BufferedWriter(new FileWriter(ahorros));
           String l="";
           bw.write(l);
           bw.close();
           escribirArchivo(aux,ahorros);
        }
        }
    
    public void menu() throws IOException{
        //pedirIntegrantes();
        //PedirGastos();
        int op;
        do{
            op=v.solicitarEntero("Bienvenido\n ¿Cual de las siguientes acciones desea realizar?"
                + "\n1. Mostrar onformacion."
                + "\n2. Agregar aportantantes."
                + "\n3. Comparar los gastos actuales con los pasados."
                + "\n4. Comparar los ahorros actuaes con los pasados"
                + "\n5. Agregar gasto."
                + "\n6. Salir"); 
            switch(op){
            case 1:mostrar();break;
            case 2: ;break;
            case 3:compGastos();break;
            case 4:compAhorros();break;
            case 5: ;break;
            case 6:;break;
            default:;break;
        }
        }while(op!=6);}
    }