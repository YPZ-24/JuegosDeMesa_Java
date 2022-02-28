package dominolocal;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Aylin Yepez
 */
public class DominoLocal {
    
    public static ArrayList<Ficha> fichas = new ArrayList<Ficha>();
    public static ArrayList<Jugador> jugadores;
    
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        
        int noJugadores = 0;
        do{
            System.out.println("Cuantos jugadores (2-4)?");
            noJugadores = sc.nextInt();
        }while(noJugadores<2 || noJugadores>4);
        
        jugadores = new ArrayList<Jugador>();
        
        for(int i=0; i<noJugadores; i++){
            Jugador jugador = new Jugador(i);
            jugadores.add(jugador);
        }
        
        Ficha ficha = new Ficha();
        ficha.creaFichas();
        
        Juego juego = new Juego();
        juego.iniciaJuego();
        
    }
    
    
}
