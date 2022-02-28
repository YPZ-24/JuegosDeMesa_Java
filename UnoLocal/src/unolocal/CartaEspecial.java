package unolocal;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Aylin Yepez
 */
public class CartaEspecial {
    
    Carta carta;

    public CartaEspecial(Carta carta) {
        this.carta = carta;
    }
    
    public void seleccionaEspecial(){
        int numero = carta.getNumero();
        switch(numero){
            case 10: //REVERSA
                UnoLocal.jugadores = Reversa();
                System.out.println("Reversa");
                break;
            case 11: //+2
                MasDos();
                System.out.println("Mas 2");
                break;
            case 12: //NO PASAS
                NoPasa();
                System.out.println("No pasa");
                break;
            case 13: //COMODIN +4
                ComodinMas(carta);
                System.out.println("COMODIN Mas 4");
                break;
            case 14: //COMODIN
                Comodin(carta);
                System.out.println("COMODIN");
                break;
        }
    }
    
    private ArrayList<Jugador> Reversa(){
        ArrayList<Jugador> nuevoArreglo = new ArrayList<Jugador>();
        int total = UnoLocal.jugadores.size();
        for(Jugador j : UnoLocal.jugadores) {
            nuevoArreglo.add(UnoLocal.jugadores.get(total-1));
            total--;
        }
        return nuevoArreglo;
    }
    
    private void MasDos(){
        //Carta tiene en jugador el turno del jugador el cual es el indice que tiene en el arreglo jugadores, lo obtengo y llamo a su metodo getTurnoSiguiente
        Jugador jugActual = UnoLocal.jugadores.get(carta.getJugador());
        int turnoSiguiente = jugActual.getTurnoSiguiente(jugActual.getTurno());
        Jugador jugSiguiente = UnoLocal.jugadores.get(turnoSiguiente);
        jugSiguiente.setComida(2);
        jugSiguiente.setPasa(true);
    }
    
    private void NoPasa(){
        ///Carta tiene en jugador el turno del jugador el cual es el indice que tiene en el arreglo jugadores, lo obtengo y llamo a su metodo getTurnoSiguiente
        Jugador jugActual = UnoLocal.jugadores.get(carta.getJugador());
        int turnoSiguiente = jugActual.getTurnoSiguiente(jugActual.getTurno());
        Jugador jugSiguiente = UnoLocal.jugadores.get(turnoSiguiente);
        System.out.println("JUGADOR SIGUIENTE: "+jugSiguiente.getTurno());
        jugSiguiente.setPasa(true);        
    }
    
    private void ComodinMas(Carta carta){
        //Carta tiene en jugador el turno del jugador el cual es el indice que tiene en el arreglo jugadores, lo obtengo y llamo a su metodo getTurnoSiguiente
        Jugador jugActual = UnoLocal.jugadores.get(carta.getJugador());
        int turnoSiguiente = jugActual.getTurnoSiguiente(jugActual.getTurno());
        Jugador jugSiguiente = UnoLocal.jugadores.get(turnoSiguiente);
        
        jugSiguiente.setComida(4);
        jugSiguiente.setPasa(true);
        Comodin(carta);
    }
    
    private void Comodin(Carta carta){
        System.out.println("*************************");
        int opc;
        String[] colores = {"Azul", "Amarillo", "Verde", "Rojo"};
        Scanner sc = new Scanner(System.in);
        for(int i=0; i<4; i++){
            System.out.println((i+1)+". "+colores[i]);
        }
        System.out.println("Elige un color: ");
        opc = sc.nextInt();
        System.out.println("*************************");
        String color = colores[opc-1];
        carta.setColor(color);
    }
    
}
