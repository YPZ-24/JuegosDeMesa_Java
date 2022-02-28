package unolocal;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Aylin Yepez
 */
public class Juego {
    private Carta cartaActual;
    private Jugador jugador;
    private int turno;
    
    
    public Juego() {
        this.cartaActual = new Carta();
        this.turno = 0;
        this.jugador = UnoLocal.jugadores.get(0);
    }
    
    public Carta getCartaActual() {
        return cartaActual;
    }
        
    public void cambiaTurno(){
        System.out.println("------------JUGADOR"+(jugador.getTurno()+1)+"------------");
        int conta=0;
        //SABER SI EL JUGADOR DEBE COMER
        while(jugador.getComida()!=0){
            Comer().setJugador(jugador.getTurno());
            jugador.setComida(jugador.getComida()-1);
            conta++;
        }
        if(conta!=0){
            System.out.println("Comes: "+conta);
        }
        
        //SABER SI EL JUGADOR NO PASA
        if(!jugador.isPasa()){
            porTurno();
        }else{
            jugador.setPasa(false);
            System.out.println("No pasas");
        }
        
        //CAMBIAMOS TURNO Y JUGADOR EN TURNO
        turno = jugador.getTurnoSiguiente(turno);
        jugador = UnoLocal.jugadores.get(turno);
        cambiaTurno();
    }
    
    public void porTurno(){
        Scanner sc = new Scanner(System.in);
        int opc=0;
        boolean isCartaValida=false;
        
        System.out.println("Carta Actual: "+cartaActual.imprimeCarta());
        
        //JUGADOR
        do{
            System.out.println("Tus cartas son: ");
            jugador.imprimeCartasJugador();
            System.out.println("0. Comer");
            System.out.println("Elige tu opcion: ");
            opc = sc.nextInt();
            switch(opc){
                case 0: //COMER 
                    Comer().setJugador(jugador.getTurno());
                    break;
                default:
                    Carta cartaSeleccionada = jugador.getCartasJugador().get(opc-1);
                    //VALIDAMOS QUE LA CARTA SEA IGUAL A LA ACTUAL
                    isCartaValida = cartaActual.validaCarta(cartaSeleccionada);
                    if(isCartaValida){
                        //TIRAMOS LA CARTA
                        cartaActual = cartaSeleccionada.tiraCarta();
                        //VALIDAMOS SI YA TERMINA EL JUEGO
                        FinDelJuego();
                    }else{
                        System.out.println("No puedes tirar esa carta");
                    }
                    break;
            }
        }while(opc==0 || !isCartaValida);
    }
    
    public void FinDelJuego(){
        if(jugador.getCartasJugador().isEmpty()){
            System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-GANASTE*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
            System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-"+jugador.getTurno()+"*-*-*-*-*-*-*-*-*-*-*-*-");
            System.out.println("Fin del Juego");
        }
    }
    
    public Carta Comer(){
        ArrayList<Carta> cartasLibres = new ArrayList<Carta>();
        int numAleatorio=0;
        for(Carta c : UnoLocal.cartas) {
            if(c.isLibre()){
                cartasLibres.add(c);
            }
        }
        //SI YA NO HAY CARTAS LIBRES
        if(cartasLibres.isEmpty()){
            //Pasamos todas las cartas pasadas menos la actual a libres
            boolean existenPasadas = setPasadasALibres();
            if(existenPasadas){
                Comer();
            }else{
                System.out.println("Ya no hay cartas para comer");
                System.out.println("Pasas");
                turno = jugador.getTurnoSiguiente(turno);
                jugador = UnoLocal.jugadores.get(turno);
                cambiaTurno();
            }
        }else{
            numAleatorio = (int) (Math.random() * cartasLibres.size());
        }
        return cartasLibres.get(numAleatorio);
    }
    
    public boolean setPasadasALibres(){
        boolean existenPasadas = false;
        for(Carta c : UnoLocal.cartas) {
            if(c.isPasada()){
                c.setLibre();
                existenPasadas = true;
            }
            if(c.getNumero()>12){
                c.setColor("Comodin");
            }
        }
        return existenPasadas;
    }
    
    public void reparteCartas(){
        for(int i=0; i<UnoLocal.jugadores.size(); i++){
            for(int j=0; j<7; j++){
                Comer().setJugador(i);
            }
        }
    }
    
    public void setPrimeraCarta(){
        Carta carta;
        do{
            carta = Comer();
        }while(carta.getNumero()>9);
        cartaActual = carta;
    }
    
}
