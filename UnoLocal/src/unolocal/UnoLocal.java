package unolocal;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Aylin Yepez
 */
public class UnoLocal {
    
    public static ArrayList<Carta> cartas;
    public static ArrayList<Jugador> jugadores;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int noJugadores = 0;
        do{
            System.out.println("Cuantos jugadores (2-10)?");
            noJugadores = sc.nextInt();
        }while(noJugadores<2 || noJugadores>10);
        
        
        Carta carta = new Carta();
        cartas = carta.creaCartas();
        jugadores = new ArrayList<Jugador>();
        
        for(int i=0; i<noJugadores; i++){
            Jugador jugador = new Jugador(i);
            jugadores.add(jugador);
        }
        
        Juego juego = new Juego();
        juego.reparteCartas();
        juego.setPrimeraCarta();
        juego.cambiaTurno();
        
    }
    
}

