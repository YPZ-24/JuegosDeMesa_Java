package dominolocal;

import java.util.ArrayList;

/**
 *
 * @author Aylin Yepez
 */
public class Jugador {
    private int turno;

    public Jugador(int turno) {
        this.turno = turno;
    }
    
    public void imprimeCartasParaTirar(){
        ArrayList<Ficha> fichasJugador = getFichasJugador();
        int conta = 1;
        for(Ficha f : fichasJugador) {
            if(!f.isTirada()){
                System.out.println(conta+".\t"+f.getN1()+" | "+f.getN2());
                conta++;
            }
        }
    }
    
    public ArrayList<Ficha> getFichasJugador(){
        ArrayList<Ficha> fichasJugador = new ArrayList<Ficha>();
        for(Ficha f : DominoLocal.fichas) {
            try{
                if(f.getJugador()==turno){
                    fichasJugador.add(f);
                }
            }catch(Exception e){

            }
        }
        return fichasJugador;
    }
    
    //REGRESA SI COMIO O NO
    public boolean Comer(){
        boolean comio = false;
        ArrayList<Ficha> fichasLibres = new ArrayList<Ficha>();
        int numAleatorio=0;
        //OBTENEMOS FICHAS LIBRES
        for(Ficha f : DominoLocal.fichas) {
            if(f.isLibre()){
                fichasLibres.add(f);
            }
        }
        //SI YA NO HAY FICHAS LIBRES
        if(fichasLibres.isEmpty()){
            //Pasamos todas las cartas pasadas menos la actual a libres
            comio = false;
        }else{
            numAleatorio = (int) (Math.random() * fichasLibres.size());
            DominoLocal.fichas.get(fichasLibres.get(numAleatorio).getId()).setJugador(this.turno);
            comio = true;
        }
        return comio;
    }
    
    public boolean hasFichaValida(Ficha fichaActual){
        boolean hasFicha = false;
        ArrayList<Ficha> fichasJug = getFichasJugador();
        for(Ficha f : fichasJug){
            if(f.isCartaValida(fichaActual)){
                hasFicha = true;
                break;
            };
        }
        return hasFicha;
    }
    
    public int getTurno() {
        return turno;
    }
    
    public int noTiradasSize(){
        int conta = 0;
        for(Ficha f : getFichasJugador()){
            if(!f.isTirada()){
                conta++;
            }
        }
        return conta;
    }
}
