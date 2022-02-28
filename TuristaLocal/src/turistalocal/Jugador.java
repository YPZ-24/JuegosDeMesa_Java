package turistalocal;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Aylin Yepez
 */
public class Jugador {
    private ArrayList<Propiedad> Propiedades;
    private int turno;
    private int dinero;
    private int tPerdidos;
    private int posicion;

    public Jugador(int turno) {
        this.turno = turno;
        this.dinero = 3500;
        this.tPerdidos = 0;
        this.posicion = 1;
        this.Propiedades = new ArrayList<Propiedad>();
    }
    
    public int gettPerdidos() {
        return tPerdidos;
    }
    
    public void settPerdidos(int tPerdidos) {
        this.tPerdidos = tPerdidos;
    }
    
    public ArrayList<Propiedad> getPropiedades() {
        return Propiedades;
    }
    
    public int getPosicion() {
        return posicion;
    }
    
    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }
    
    public void setDinero(int dinero) {
        this.dinero = dinero;
    }
    
    public int getTurno() {
        return turno;
    }
    
    public int getDinero() {
        return dinero;
    }
    
    public void setPropiedades(ArrayList<Propiedad> Propiedades) {
        this.Propiedades = Propiedades;
    }
    
    public void imprimePropiedades(){
        int conta=1;
        for(Propiedad p : this.Propiedades) {
            System.out.println(conta+". "+p.getNombre()+ "\t" + p.getNumero() + "\t" + p.getLote() + "\t" + p.getValor() + "\t" + p.getRenta() + "\t" + p.getHipoteca());
            conta++;
        }
    }
    
    public void pagar(int pago, Jugador jugador){
        //SI TIENE SUFICIENTE DINERO PARA PAGAR?
        if(getDinero()>pago){
                setDinero(getDinero() - pago);
                jugador.setDinero(jugador.getDinero() + pago);
                System.out.println("Saldo final $"+getDinero());
        }else{
            System.out.println("No tienes suficiente dinero para pagar");
            if(sinDinero()){
                pagar(pago, jugador);
            }
        } 
    }
    
    private boolean sinDinero(){
        //SABER SI NO TIENE PROPIEDADES
        if(this.Propiedades.isEmpty()){
            //SE DECLARA EN BANCARROTA Y ACABA EL JUEGO
            System.out.println("ESTAS EN BANCARROTA");
            System.out.println("\nXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n");
            System.out.println("FIN DEL JUEGO");
            return false;
        }else{  
            //HIPOTECAR
            Scanner sc = new Scanner(System.in);
            int indiceProp;
            System.out.println("¿Cual quieres hipotecar?");
            imprimePropiedades();
            System.out.println("Ingresa el numero de la propiedad: ");
            indiceProp = sc.nextInt() - 1;
            
            //TRAEMOS LA PROPIEDAD ELEGIDA
            Propiedad propiedad = getPropiedad(indiceProp);
            
            //DAMOS DINERO DE HIPOTECA
            setDinero(getDinero() + propiedad.getHipoteca());
            //QUITAMOS LA PROPIEDAD DE SUS PROPIEDADES
                //Recordar que opc tiene el indice de la propiedad +1 en el arraylist
            removePropiedad(indiceProp);
            propiedad.setNoJugador(0);
            return true;
        }
    }
    
    public void pagar(int pago, Banco banco){
        //SI TIENE SUFICIENTE DINERO PARA PAGAR?
        if(getDinero()>pago){
                setDinero(getDinero() - pago);
                banco.setDinero(banco.getDinero() + pago);
                System.out.println("Saldo final $"+getDinero());
        }else{
            System.out.println("No tienes suficiente dinero para pagar");
            if(sinDinero()){
                pagar(pago, banco);
            }
        } 
    }
    
    private Propiedad getPropiedad(int indice){
        return this.Propiedades.get(indice);
    }
    
    private Propiedad removePropiedad(int indice){
        return this.Propiedades.remove(indice);
    }
    
    public void setPropiedad(Propiedad propiedad){
        this.Propiedades.add(propiedad);
    }
    
}
