package turistalocal;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Aylin Yepez
 * La clase banco controla el juego
 */

public class Banco {
    private int dinero;
    private ArrayList<Propiedad> propiedades;
    private Jugador jugador1;
    private Jugador jugador2;
    private int turno;
   
    public Banco(){
        this.dinero = 25000;
        this.jugador1 = new Jugador(1);
        this.jugador2 = new Jugador(2);
        this.turno = 1;
    }
    
    public int tiraDados(){
        int n1 = (int) (Math.random() * 6 + 1);
        int n2 = (int) (Math.random() * 6 + 1);
        return n1 + n2;
    }
    
    public int getDinero() {
        return dinero;
    }
    
    public void setDinero(int dinero) {
        this.dinero = dinero;
    }
    
    public void setPropiedades(ArrayList<Propiedad> propiedades) {
        this.propiedades = propiedades;
    }
    
    public void cambiaTurno(){
       //CAMBIAR DE JUGADOR
        if(turno==1){
            imprime(jugador1);
            validaCambiaTurno(jugador1, 2);
        }else
            if(turno==2){
                imprime(jugador2);
                validaCambiaTurno(jugador2, 1);
            }
    }
    
    private void validaCambiaTurno(Jugador jugador, int nuevoTurno){
        //VER SI TIENE TURNO PERDIDOS
        if(jugador.gettPerdidos()>0){
            System.out.println("Tienes turnos perdidos");
            jugador.settPerdidos(jugador.gettPerdidos()-1);
        }else{
            //AVANZAR AL JUGADOR EL NUMERO QUE INDICA EL DADO
            porTurno(jugador);
        }
        turno=nuevoTurno;
        if(jugador.getDinero()==0 && jugador.getPropiedades().isEmpty()){
            System.out.println("FIN DEL JUEGO");
        }else{
            cambiaTurno();
        }
    }
    
    private void porTurno(Jugador jugador){
       //--OBTENEMOS LA NUEVA POSICION PARA EL JUGADOR
       int posicion = jugador.getPosicion() + tiraDados();
       
       //40 es el total de casillas en el tablero
       if(posicion>=40){
           posicion=posicion-40;
           CasillaEspecial casEspecial = new CasillaEspecial(jugador, this);
           casEspecial.Salida();
       }
       
       //--OBTENEMOS LOS DATOS DE LA NUEVA CASILLA
            //La posicion va desde 1, y el arreglo desde 0, por eso restamos 1
       Propiedad casilla = propiedades.get(posicion-1);
       
       //CAMBIAR AL JUGADOR A LA NUEVA POSICION
       jugador.setPosicion(posicion);
       
       //VEMOS SI LA CASILLA DONDE CAE ES PROPIEDAD O ESPECIAL
       if(casilla.getLote().equals("Especial")){
           CasillaEspecial casEspecial = new CasillaEspecial(jugador, this);
           casEspecial.seleccionaEspecial(posicion);
       }else{
           if(casilla.isUsado()){
                //SI YA TIENE DUEÑO
                    //Vemos si yo soy el dueño
                if(casilla.getNoJugador()==jugador.getTurno()){
                    System.out.println("Tu eres el dueño de esta propiedad");
                }else{
                    if(jugador.getTurno()==1){
                        casilla.pagarRenta(jugador, jugador2);
                    }else if(jugador.getTurno()==2){
                        casilla.pagarRenta(jugador, jugador1);
                    }
                }
            }else{
                //SI AUN NO TIENE DUEÑO
                casilla.Comprar(jugador, this);
            }
       }
              
   }
   
    private void imprime(Jugador jugador){
        System.out.println("\n----------------------------------JUGADOR "+jugador.getTurno()+"----------------------------------");
        System.out.println("Dinero: \t"+jugador.getDinero());
        System.out.println("T. Perdidos: \t"+jugador.gettPerdidos());
        System.out.println("Posicion: \t"+propiedades.get(jugador.getPosicion()-1).getNombre());
        System.out.println("Propiedades");
        jugador.imprimePropiedades();
        System.out.println("-----------------------------------------------------------------------------\n");
    }
}
