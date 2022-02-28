package adivinaquienlocal;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Aylin Yepez
 */
public class Juego {
    private ArrayList<Jugador> jugadores;
    private int turno;
    private int indiceC;
    private int indiceOpc;

    public Juego() {
        this.jugadores = jugadores = new ArrayList<Jugador>();
        this.turno = 0;
    }
    
    private void cambiaTurno(){
        if(turno==0){
            turno = 1;
        }else if(turno==1){
            turno = 0;
        }
        porTurno();
    }
    
    public void porTurno(){
        System.out.println("=============================================="+jugadores.get(turno).getTurno()+"==============================================");
        /*DESCOMENTAR PARA SABER LOS PERSONAJES AL INICIO DEL JUEGO
        System.out.println("JUGADOR1");
        System.out.println(jugadores.get(0).getPersonaje().getNombre());
        System.out.println("JUGADOR2");
        System.out.println(jugadores.get(1).getPersonaje().getNombre());
        */
        imprimeTableroCuadricula();
        //HACEMOS QUE EL USUARIO CREE LA PREGUNTA
        System.out.println("--------------------------------------CREA TU PREGUNTA---------------------------------------");
        menu1();
        //MANEJAMOS PREGUNTA
        if(manejaPregunta()){
            cambiaTurno();
        }else{
            FinDelJuego();
        }
        
    }
    
    //LEEMOS, IMPRIMIMOS PREGUNTA Y LA RESPONDEMOS
        //Regresamos si el juego continua
    public boolean manejaPregunta(){
        boolean continua = false;
        if(indiceC==0){
            if(personajeFinal()){
                continua = false;
            }else{
                System.out.println("NO ES ESE PERSONAJE");
                continua = true;
            }
        }else{
            System.out.println("------------------------------------------PREGUNTA-------------------------------------------");
            System.out.println(getPregunta());
            //VALIDAMOS SI ACERTO O NO SEGUN LA CARTA DEL ADVERSARIO
            boolean acerto = false;
            if(turno==0){
                acerto = jugadores.get(1).getPersonaje().isCaracteristicaIgual(indiceC, indiceOpc);
            }else if(turno==1){
                acerto = jugadores.get(0).getPersonaje().isCaracteristicaIgual(indiceC, indiceOpc);
            }
            if(acerto){
                System.out.println("--SI");
                jugadores.get(turno).bajaPersonajes(jugadores.get(turno).getPersonaje().getIdsPerSinCaracteristica(indiceC, indiceOpc));
            }else{
                System.out.println("--NO");
                jugadores.get(turno).bajaPersonajes(jugadores.get(turno).getPersonaje().getIdsPerConCaracteristica(indiceC, indiceOpc));
            }
            System.out.println("---------------------------------------------------------------------------------------------");
            imprimeTableroCuadricula();
            System.out.println("---------------------------------------------------------------------------------------------");
            continua = true;
        }
        return continua;
    }
    
    public void repartePersonajes(){
        Personaje personaje = new Personaje();
        int totalPeronajes = 24;
        
        int numAleatorio1, numAleatorio2;
        numAleatorio1 = (int) (Math.random() * totalPeronajes);
        do{
            numAleatorio2 = (int) (Math.random() * totalPeronajes);
        }while(numAleatorio1==numAleatorio2);
        
        Personaje p1 = personaje.getPersonaje(numAleatorio1);
        jugadores.add(new Jugador(1, p1));
        jugadores.get(0).setPersonajeAbajo(p1.getId());
        
        Personaje p2 = personaje.getPersonaje(numAleatorio2);
        jugadores.add(new Jugador(2, p2));
        jugadores.get(1).setPersonajeAbajo(p2.getId());
        
    }
    
    private String getPregunta(){
        String pregunta = "";
        switch(indiceC){
            case 1:
                if(indiceOpc==1){
                    pregunta = "es mujer";
                }else{
                    pregunta = "es hombre";
                }
                break;
            case 2:
                pregunta = "tiene ojos color "+AdivinaQuienLocal.colorOjos[indiceOpc-1];
                break;
            case 3:
                pregunta = "tiene cabello "+AdivinaQuienLocal.colorCabello[indiceOpc-1];
                break;
            case 4: case 5: case 6: case 7:
                if(indiceOpc==1){
                    pregunta = "tiene";
                }else{
                    pregunta = "no tiene";
                }
                break;
            case 8:
                if(indiceOpc==1){
                    pregunta = "es";
                }else{
                    pregunta = "no es";
                }
                break;
        }
        
        if(indiceC>=4 && indiceC<=8){
            pregunta = pregunta +" "+AdivinaQuienLocal.caracteristicas[indiceC-1];
        }
        
        pregunta = "¿Tu personaje "+pregunta+"?";
        return pregunta;
    }
    
    private boolean personajeFinal(){
        Scanner sc = new Scanner(System.in);
        String nombre = "", nombreAdversario = "";
        System.out.println("Ingresa nombre: ");
        nombre = sc.nextLine();
        if(turno==0){
            nombreAdversario = jugadores.get(1).getPersonaje().getNombre();
        }else if(turno==1){
            nombreAdversario = jugadores.get(0).getPersonaje().getNombre();
        }

        return nombreAdversario.equalsIgnoreCase(nombre);
    }
    
    private void FinDelJuego(){
        System.out.println("\n-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-GANASTE "+jugadores.get(turno).getTurno()+"-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-\n");
        System.out.println("----------------------------------------FIN DEL JUEGO----------------------------------------");
    }
    
    //MENU 1 caracteristicas
    private void menu1(){
        Scanner sc = new Scanner(System.in);
        indiceC = 0;
        indiceOpc = 0;
        System.out.println("0. Ya se el personaje");
        for(int i=0; i<AdivinaQuienLocal.caracteristicas.length; i++){
            System.out.println((i+1)+". "+AdivinaQuienLocal.caracteristicas[i]);
        }
        do{
            System.out.println("Indice: ");
            indiceC = sc.nextInt();
            if(indiceC<0 || indiceC>AdivinaQuienLocal.caracteristicas.length){
                System.out.println("El indice ingresado no existe");
            }
        }while(indiceC<0 || indiceC>AdivinaQuienLocal.caracteristicas.length);
                
        if(indiceC!=0){
            menu2();
        }        
    }
    
    //MENU 2 caracteristicas especificas
    private void menu2(){
        int tamaño = 2;
        Scanner sc = new Scanner(System.in);
        System.out.println("--------"+AdivinaQuienLocal.caracteristicas[indiceC-1].toUpperCase()+"--------");
        switch(indiceC){
            case 1: 
                System.out.println("1. Mujer");
                System.out.println("2. Hombre");
                break;
            case 2:
                tamaño = AdivinaQuienLocal.colorOjos.length;
                for(int i=0; i<AdivinaQuienLocal.colorOjos.length; i++){
                    System.out.println((i+1)+". "+AdivinaQuienLocal.colorOjos[i]);
                }
                break;
            case 3: 
                tamaño = AdivinaQuienLocal.colorCabello.length;
                for(int i=0; i<AdivinaQuienLocal.colorCabello.length; i++){
                    System.out.println((i+1)+". "+AdivinaQuienLocal.colorCabello[i]);
                }
                break;
            case 4: case 5: case 6: case 7:
                System.out.println("1. Tiene");
                System.out.println("2. No tiene");
                break;
            case 8:
                System.out.println("1. Es");
                System.out.println("2. No es");
                break;
        }
        do{
            System.out.println("Indice");
            indiceOpc = sc.nextInt();
            if(indiceOpc<=0 || indiceOpc>tamaño){
                System.out.println("El indice ingresado no existe");
            }
        }while(indiceOpc<=0 || indiceOpc>tamaño);
    }
    
    private void imprimeTableroCuadricula(){
        String colorTexto="\033[33m"; 

        System.out.print(llena10("Personaje")+" ");
        for(int j=0; j<AdivinaQuienLocal.caracteristicas.length; j++){
            System.out.print(llena10(AdivinaQuienLocal.caracteristicas[j])+" ");
        }
        System.out.println();
        for(int i=0; i<AdivinaQuienLocal.personajes.size(); i++){
            if(jugadores.get(turno).isPersonajeAbajo(AdivinaQuienLocal.personajes.get(i).getId())){
                colorTexto = "\033[33m";
            }else{
                colorTexto = "\033[30m"; 
            }
            System.out.print(colorTexto + llena10(AdivinaQuienLocal.personajes.get(i).getNombre())+" ");
            for(int j=0; j<AdivinaQuienLocal.caracteristicas.length; j++){
                System.out.print(colorTexto + llena10(AdivinaQuienLocal.personajes.get(i).imprimeCaracteristica(j+1))+" ");
            }
            if(AdivinaQuienLocal.personajes.get(i).getId()==jugadores.get(turno).getPersonaje().getId()){
                System.out.print("<= TUYA");
            }
            System.out.println();
        }
        
    }
    
    private String llena10(String txt){
        while(txt.length()<10){
            txt = txt + " ";
        }
        return txt;
    }
}
