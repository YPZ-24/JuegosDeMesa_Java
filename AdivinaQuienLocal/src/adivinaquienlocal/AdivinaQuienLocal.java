package adivinaquienlocal;

import java.util.ArrayList;

/**
 *
 * @author Aylin Yepez
 */
public class AdivinaQuienLocal {
    
    public static ArrayList<Personaje> personajes = new ArrayList<Personaje>();
    public static String[] caracteristicas = {"sexo", "ojos", "cabello", "lentes", "barba", "bigote", "sombrero", "calvo"};
    public static String[] colorOjos = {"Cafe", "Azul"};
    public static String[] colorCabello = {"Canoso", "Rubio", "Negro", "Castaño", "Pelirrojo"};
    
    public static void main(String[] args) {
        Personaje personaje = new Personaje();
        personajes = personaje.creaPersonajes();
        
        Juego juego = new Juego();
        juego.repartePersonajes();
        juego.porTurno();
    }
    
}
