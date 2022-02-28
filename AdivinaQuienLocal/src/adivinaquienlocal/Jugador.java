package adivinaquienlocal;

import java.util.ArrayList;

/**
 *
 * @author Aylin Yepez
 */
public class Jugador {
    private ArrayList<Integer> perAbajo;
    private int turno;
    private Personaje personaje;

    public Jugador(int turno, Personaje personaje) {
        this.perAbajo = new ArrayList<Integer>();
        this.turno = turno;
        this.personaje = personaje;
    }
    
    public Personaje getPersonaje() {
        return personaje;
    }
    
    public void bajaPersonajes(ArrayList<Integer> ids){
        for (Integer id : ids) {
            perAbajo.add(id);
        }
    }
    
    public void setPersonajeAbajo(int id){
        perAbajo.add(id);
    }
    
    public int getTurno() {
        return turno;
    }
    
    public boolean isPersonajeAbajo(int idEntra){
        boolean res = false;
        for (int id : perAbajo) {
            if(id == idEntra){
                res = true;
            }
        }
        return res;
    }
}
