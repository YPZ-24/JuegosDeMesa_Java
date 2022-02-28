package dominolocal;

import static dominolocal.DominoLocal.fichas;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

/**
 *
 * @author Aylin Yepez
 */
public class Juego {
    private int turno;
    private int noJugPasaron;
    private Ficha fichaActual;

    public Juego() {
        this.fichaActual = new Ficha();
        this.turno = 0;
        this.noJugPasaron = 0;
    }
    
    public void iniciaJuego(){
        reparteFichas();
        //VER SI UN JUGADOR TIENE LA MULA
        if(tieneMula6()!=null){
            turno = tieneMula6();
        }
        porTurno();
    }
    
    private void cambiaTurno(){
        if( (turno+1) < DominoLocal.jugadores.size() ){
            turno= turno + 1;
        }else{
            turno=0;
        }
        porTurno();
    }
    
    private void porTurno(){
        //VEMOS SI TODOS LOS JUGADORES HAN DICHO "PASO"
        System.out.println("NO JUG PASARON "+noJugPasaron);
        System.out.println("TOTAL JUGADORES "+DominoLocal.jugadores.size());
        if(noJugPasaron>=DominoLocal.jugadores.size()){
            Empate();
        }else{
            //QUE NO SEA LA PRIMERA CARTA
            if(fichaActual.getN1()!=null && fichaActual.getN2()!=null){
                System.out.println("\n=================================================");
                System.out.println("Ficha Actual:\t"+fichaActual.getN1()+" | "+fichaActual.getN2());
                System.out.println("=================================================\n");
            }

            System.out.println("------------------------"+(DominoLocal.jugadores.get(turno).getTurno()+1)+"------------------------");
            DominoLocal.jugadores.get(turno).imprimeCartasParaTirar();
            System.out.println("-------------------------------------------------");
            imprimeMenu1();
        }
    }
    
    //Imprime el menu para ver si quiere tirar o comer
    private void imprimeMenu1(){
        Scanner sc = new Scanner(System.in);
        int opc=0;
        do{
            System.out.println("1. Come");
            System.out.println("2. Tira");
            System.out.println("Indice: ");
            opc = sc.nextInt();
            switch(opc){
                case 1: 
                    if(! DominoLocal.jugadores.get(turno).Comer()){
                        System.out.println("Ya no hay cartas para comer");
                        //SI AUN TIENE FICHAS VALIDAS
                            //--En la primera ronda a fuerza tiene fichas validas para tirar
                        boolean fichaValida = fichaActual.getN1()==null && fichaActual.getN2()==null;
                            //--Si no es la primera ronda, vemos si el jugador tiene fichas validas
                        if(!fichaValida){
                            fichaValida = DominoLocal.jugadores.get(turno).hasFichaValida(fichaActual);
                        }
                        if(fichaValida){
                            System.out.println("Elige una ficha");
                            imprimeMenu2();
                        }else{
                            System.out.println("Pasas");
                            noJugPasaron++;
                            cambiaTurno();
                        }
                    }
                    break;
                case 2:
                    imprimeMenu2();
                    break;
                default:
                    System.out.println("El indice no existe");
                    break;
            }
        }while(opc<1 || opc>2);
    }
    
    private void FinDelJuego(){
        System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-GANASTE jugador"+(DominoLocal.jugadores.get(turno).getTurno()+1)+"*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
        System.out.println("Fin del Juego");
    }
    
    private void Empate(){
        System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-EMPATE-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
        System.out.println("Fin del Juego");
    }
    
    private void reparteFichas(){
        for(int i=0; i<DominoLocal.jugadores.size(); i++){
            for(int j=0; j<7; j++){
                DominoLocal.jugadores.get(i).Comer();
            }
        }
        
    }
    
    //REGRESA EL TURNO DEL JUGADOR QUE TIENE LA MUJA DE 6
    private Integer tieneMula6(){
        return DominoLocal.fichas.get(6).getJugador();
    }
    
    //Imprime el menu para elegir la carta a tirar
    //Regresa la opcion elegida, la carta tirada
    private void imprimeMenu2(){
        Scanner sc = new Scanner(System.in);
        int opc = 0;
        ArrayList<Ficha> fichasJugador = DominoLocal.jugadores.get(turno).getFichasJugador();
        do{
            System.out.println("Indice Ficha (0 para regresar): ");
            opc = sc.nextInt();
            if(opc==0){
                imprimeMenu1();
            }else if(opc<1 || opc>fichasJugador.size()){
                System.out.println("El indice no existe");
            }else{
                if(! Tira(fichasJugador.get(opc-1))){
                    opc=0;
                }
            }
        }while(opc<1 || opc>fichasJugador.size());
        
        noJugPasaron=0;
        //SI AUN TIENE CARTAS NO TIRADAS
        if(DominoLocal.jugadores.get(turno).noTiradasSize()!=0){
            cambiaTurno();
        }else{
            FinDelJuego();
        }
    }
    
    //regresa si tiro o no
    private boolean Tira(Ficha fichaTirada){
        boolean tiro;
        boolean valida;
        //SI NO ES LA PRIMERA RONDA, VALIDA QUE SEA IGUAL A LA CARTA ACTUAL
        if(fichaActual.getN1()!=null && fichaActual.getN2()!=null){
            valida = fichaTirada.isCartaValida(fichaActual);
            if(valida){
                ArrayList<Integer> nums = obtenNumerosNumeros(fichaTirada.getN1(), fichaTirada.getN2());
                fichaTirada.setN1(nums.get(0));
                fichaTirada.setN2(nums.get(1));
            }
        }else{
            valida = true;
        }
        //SI ES IGUAL A LA CARTA ACTUAL O ES LA PRIMERA RONDA
        if(valida){
            //LA FICHA ES TIRADA
            DominoLocal.fichas.get(fichaTirada.getId()).setPasada();
            fichaActual = fichaTirada;
            tiro = true;
        }else{
            System.out.println("No puedes tirar esa ficha");
            tiro = false;
        }
        return tiro;
    }
    
    //RECIBE LOS NUMEROS DE LA FICHA QUE SELECCIONO EL JUGADOR
    private ArrayList<Integer> obtenNumerosNumeros(int n1, int n2){
        int N1 = fichaActual.getN1();
        int N2 = fichaActual.getN2();
        int perdido;
        
        //SABER SI LOS NUMEROS DE LA ACTUAL SON IGUALES A LOS INGRESADOS
        if(fichaActual.isNumerosIguales(n1, n2)){
            perdido = preguntaPerdido(n1, n2);
        }else{
            perdido = obtenPerdido(n1, n2, N1, N2);
        }
        
        //GUARDAMOS EN NUMS LOS NUMEROS NO PERDIDOS, CON LOS QUE NOS QUEDAMOS
        int[] a = {n1, n2, N1, N2};
        int conta = 0;
        ArrayList<Integer> nums = new ArrayList<Integer>(2);
        for(int i=0; i<4; i++){
            if(a[i]==perdido && conta<2){
                conta++;
            }else{
                nums.add(a[i]);
            }
        }
        return nums;
    }
    
    //OBTIENE AUTOMATICAMENTE EL NUMERO PERDIDO
    private int obtenPerdido(int n1, int n2, int N1, int N2){
        int perdido=0;
        if( n1==N1 || n1==N2 ){
            perdido = n1;
        }else if(n2==N1 || n2==N2){
            perdido = n2;
        }
        return perdido;
    }
    
    //PREGUNTA CUAL SERA EL NUMERO PERDIDO
    private int preguntaPerdido(int n1, int n2){
        int perdido=0;
        int opc = 0;
        Scanner sc = new Scanner(System.in);
        do{
            System.out.println("¿Con cual quieres jugar?");
            System.out.println("1. "+n1);
            System.out.println("2. "+n2);
            System.out.println("Indice: ");
            opc = sc.nextInt();
            switch(opc){
                case 1: perdido=n1; break;
                case 2: perdido=n2; break;
                default: System.out.println("El indice no existe"); break;
            }
        }while(opc!=1 && opc!=2);
        return perdido;
    }
    
}
