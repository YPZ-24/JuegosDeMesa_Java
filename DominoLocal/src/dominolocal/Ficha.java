package dominolocal;

import java.util.ArrayList;

/**
 *
 * @author Aylin Yepez
 */
public class Ficha {
    private int id;
    private Integer n1;
    private Integer n2;
    private Integer jugador;
    private boolean tirada;

    public Ficha() {
        this.n1 = null;
        this.n2 = null;
        this.jugador = null;
        this.tirada = false;
    }
    
    public Ficha(int n1, int n2, int id) {
        this.id = id;
        this.n1 = n1;
        this.n2 = n2;
        this.jugador = null;
        this.tirada = false;
    }

    public void creaFichas(){
        int conta = 0;
        int noFFilas = 7;
        for (int i = 7; i > 0; i--) {
            for (int j = 0; j < noFFilas; j++) {
                DominoLocal.fichas.add(new Ficha((i-1), j, conta));
                conta++;
            }
            noFFilas--;
        }
    }
    
    public Integer getN1() {
        return n1;
    }

    public Integer getN2() {
        return n2;
    }
    
    public Integer getJugador() {
        return jugador;
    }
    
    public boolean isCartaValida(Ficha fTirada){
        return n1 == fTirada.getN1() || n2 == fTirada.getN2() || n1 == fTirada.getN2() || n2 == fTirada.getN1();
    }
    
    public void setN1(Integer n1) {
        this.n1 = n1;
    }
    
    public void setN2(Integer n2) {
        this.n2 = n2;
    }
    
    public int getId() {
        return id;
    }
    
    public void setPasada(){
        this.jugador = null;
        this.tirada = true;
    }
    
    public boolean isLibre(){
        return jugador==null && !tirada;
    }
    
    public void setJugador(Integer turno) {
        this.jugador = turno;
    }
    
    public boolean isTirada() {
        return tirada;
    }
    
     //TE DICE SI 2 CARTAS TIENEN LOS NUMEROS IGUALES
    public boolean isNumerosIguales(int N1, int N2){
        return (N1==n1 && N2==n2) || (N1==n2 && N2==n1);      
    }
}
