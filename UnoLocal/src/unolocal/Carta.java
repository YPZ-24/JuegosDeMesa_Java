package unolocal;

import java.util.ArrayList;

/**
 *
 * @author Aylin Yepez
 */
public class Carta {
    private String color;
    private int numero;
    private boolean usada;
    private Integer jugador;//Turno del jugador
    
    public Carta() {
    }

    public Carta(String color, int numero) {
        this.color = color;
        this.numero = numero;
        //LAS CARTAS SE CREAN COMO LIBRES
        this.jugador = null;
        this.usada = false;
    }
    
    public int getNumero() {
        return numero;
    }
    
    public Integer getJugador() {
        return jugador;
    }
    
    public void setColor(String color) {
        this.color = color;
    }
    
    public void setJugador(int turno){
        jugador=turno;
        usada=true;
    }
    
    public String imprimeCarta(){
        String nombre;
        switch(getNumero()){
            case 10: //REVERSA
                nombre = "Reversa";
                break;
            case 11: //+2
                nombre = "+2";
                break;
            case 12: //NO PASAS
                nombre = "No pasas";
                break;
            case 13: //COMODIN +4
                nombre = "+4";
                break;
            case 14: //COMODIN
                nombre = "Simple";
                break;
            default:
                nombre = Integer.toString(getNumero());
                break;
        }
        return Character.toString(getColor().charAt(0)) + Character.toString(getColor().charAt(1))+ "\t" + nombre;
    }
    
    //Valida que la carta sea igual a la que entra, si la que entra es comodin no hay que validar
    public boolean validaCarta(Carta cartaValidar){
        return cartaValidar.getColor().equals("Comodin") || getColor().equals(cartaValidar.getColor()) || getNumero() == cartaValidar.getNumero();
    }
    
    public Carta tiraCarta(){
        //VEMOS SI ES UNA CARTA ESPECIAL
        if(getNumero()>9){
            CartaEspecial ce = new CartaEspecial(this);
            ce.seleccionaEspecial();
        }
        setPasada();
        return this;
    }
    
    private void setPasada(){
        jugador=null;
        usada=true;
    }
    
    public String getColor() {
        return color;
    }
    
    public boolean isLibre(){
        return jugador==null && !usada;
    }
    
    public boolean isPasada(){
        return jugador==null && usada;
    }
    
    public void setLibre(){
        jugador=null;
        usada = false;
    }
    
    public ArrayList<Carta> creaCartas(){
        String[] colores = {"Azul", "Amarillo", "Verde", "Rojo", "Comodin"};
        ArrayList<Carta> cartas = new ArrayList<Carta>();
        Carta carta;
        for(int i = 0; i<4; i++){
            for(int j=0; j<13; j++){
                if(j==0){
                    carta = new Carta(colores[i], j);
                    cartas.add(carta);
                }else{
                    for(int k=0; k<2; k++){
                        carta = new Carta(colores[i], j);
                        cartas.add(carta);
                    }
                }
            }
        }
        for(int i=0; i<4; i++){
            carta = new Carta(colores[4], 13);
            cartas.add(carta);
            carta = new Carta(colores[4], 14);
            cartas.add(carta);
        }
        return cartas;
        
    }

}
