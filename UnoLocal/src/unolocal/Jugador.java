package unolocal;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Aylin Yepez
 */
public class Jugador{
    private int turno;
    private int comida;
    private boolean pasa;

    public Jugador(int turno) {
        this.turno = turno;
        this.comida = 0;
        this.pasa = false;
    }
    
    public void setComida(int comida) {
        this.comida = comida;
    }
    
    public int getTurno() {
        return turno;
    }
    
    public boolean isPasa() {
        return pasa;
    }
    
    //Regresa el turno del jugador siguiente
    public int getTurnoSiguiente(int turno){
        if( (turno+1) < UnoLocal.jugadores.size() ){
            turno= turno + 1;
        }else{
            turno=0;
        }
        return turno;
    }
    
    public void setPasa(boolean pasa) {
        this.pasa = pasa;
    }
    
    public void imprimeCartasJugador(){
        ArrayList<Carta> cartasJugador = getCartasJugador();
        int conta = 1;
        for(Carta c : cartasJugador) {
            System.out.println(conta+".\t"+c.imprimeCarta());
            conta++;
        }
    }
    
    public ArrayList<Carta> getCartasJugador(){
        ArrayList<Carta> cartasJugador = new ArrayList<Carta>();
        for(Carta c : UnoLocal.cartas) {
            try{
                if(c.getJugador()==turno){
                    cartasJugador.add(c);
                }
            }catch(Exception e){

            }
        }
        return cartasJugador;
    }
    
    public int getComida() {
        return comida;
    }
    
}
