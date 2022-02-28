package adivinaquienlocal;

import java.util.ArrayList;

/**
 *
 * @author Aylin Yepez
 */
public class Personaje {
    private int id;
    private String nombre;
    private int ojos;
    private int cabello;
    private boolean mujer;
    private boolean lentes;
    private boolean barba;
    private boolean bigote;
    private boolean sombrero;
    private boolean calvo;

    public Personaje() {
    }
    
    public Personaje(int id, String nombre, int ojos, int cabello, boolean mujer, boolean lentes, boolean barba, boolean bigote, boolean sombrero, boolean calvo) {
        this.id = id;
        this.nombre = nombre;
        this.ojos = ojos;
        this.cabello = cabello;
        this.mujer = mujer;
        this.lentes = lentes;
        this.barba = barba;
        this.bigote = bigote;
        this.sombrero = sombrero;
        this.calvo = calvo;
    }
    
    public ArrayList<Personaje> creaPersonajes(){
        String[] colorOjos = AdivinaQuienLocal.colorOjos;
        String[] colorCabello = AdivinaQuienLocal.colorCabello;
        ArrayList<Personaje> personajes = new ArrayList<Personaje>();
        personajes.add(new Personaje(0, "Erick", 0, 1, false, false, false, false, true, false)); //1
        personajes.add(new Personaje(1, "Jorge", 0, 0, false, false, false, false, true, false)); //2
        personajes.add(new Personaje(2, "Alberto", 1, 3, false, false, false, false, false, false)); //3
        personajes.add(new Personaje(3, "Leonor", 1, 0, true, false, false, false, false, false)); //4
        personajes.add(new Personaje(4, "Pedro",  1, 0, false, false, false, false, false, false)); //5
        personajes.add(new Personaje(5, "Daniela", 0, 4, true, true, false, false, true, false)); //6
        personajes.add(new Personaje(6, "Gerardo", 0, 3, false, false, false, false, true, false)); //7
        personajes.add(new Personaje(7, "Rodolfo", 0, 4, false, false, false, false, false, true)); //8
        personajes.add(new Personaje(8, "Ernesto", 0, 0, false, true, false, false, false, true)); //9
        personajes.add(new Personaje(9, "Enrique", 0, 0, false, true, false, false, false, false)); //10
        personajes.add(new Personaje(10, "Sergio", 0, 4, false, false, false, false, false, false)); //11
        personajes.add(new Personaje(11, "David", 0, 1, false, false, true, false, false, false)); //12
        personajes.add(new Personaje(12, "Hugo", 1, 4, false, false, false, true, false, false)); //13
        personajes.add(new Personaje(13, "Toño", 0, 1, false, true, false, false, false, false)); //14
        personajes.add(new Personaje(14, "Alejandro", 0, 2, false, false, false, true, false, false)); //15
        personajes.add(new Personaje(15, "Jesus", 0, 2, false, false, true, false, false, false)); //16
        personajes.add(new Personaje(16, "Carlos", 0, 1, false, false, false, true, false, false)); //17
        personajes.add(new Personaje(17, "Guillermo", 0, 4, false, false, true, false, false, true)); //18
        personajes.add(new Personaje(18, "Maribel", 0, 2, true, false, false, false, false, false)); //19
        personajes.add(new Personaje(19, "Susana", 0, 1, true, false, false, false, false, false)); //20
        personajes.add(new Personaje(20, "Maria", 0, 3, true, false, false, false, true, false)); //21
        personajes.add(new Personaje(21, "Ricardo", 0, 3, false, false, true, true, false, true)); //22
        personajes.add(new Personaje(22, "Oscar", 0, 2, false, false, false, true, false, false)); //23
        personajes.add(new Personaje(23, "Roberto", 1, 2, false, true, false, false, false, true)); //24
        return personajes;
    }
    
    public Boolean isCaracteristicaIgual(int indiceC, int indiceOpc){
        return indiceOpc == getCaracteristica(indiceC);
    }
    
    public ArrayList<Integer> getIdsPerSinCaracteristica(int indiceC, int indiceOpc){
        ArrayList<Integer> ids = new ArrayList<Integer>();
        for (Personaje p : AdivinaQuienLocal.personajes) {
            if(indiceOpc != p.getCaracteristica(indiceC)){
                ids.add(p.getId());
            }
        }
        return ids;
    }
    
    public int getId() {
        return id;
    }
    
    private int getCaracteristica(int indiceC){
        int num=0;
        switch(indiceC){
            case 1: 
                if(mujer) num=1; else num=2;
                break;
            case 2: //Los ojos y el cabello son arrayList que inician en 0 por eso se suma 1
                num = ojos+1;
                break;
            case 3:
                num = cabello+1;
                break;
            case 4:
                if(lentes) num=1; else num=2;
                break;
            case 5:
                if(barba) num=1; else num=2;
                break;
            case 6:
                if(bigote) num=1; else num=2;
                break;
            case 7:
                if(sombrero) num=1; else num=2;
                break;
            case 8:
                if(calvo) num=1; else num=2;
                break;
        }
        return num;
    }
    
    public ArrayList<Integer> getIdsPerConCaracteristica(int indiceC, int indiceOpc){
        ArrayList<Integer> ids = new ArrayList<Integer>();
        for (Personaje p : AdivinaQuienLocal.personajes) {
            if(indiceOpc == p.getCaracteristica(indiceC)){
                ids.add(p.getId());
            }
        }
        return ids;
    }
    
    public Personaje getPersonaje(int indice){
        return AdivinaQuienLocal.personajes.get(indice);
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public String imprimeCaracteristica(int indCaract){
        String msj = "";
        switch(indCaract){
            case 1:
                if(mujer) msj="M"; else msj="H";
                break;
            case 2:
                msj = AdivinaQuienLocal.colorOjos[ojos];
                break;
            case 3:
                msj = AdivinaQuienLocal.colorCabello[ojos];
                break;
            case 4:
                msj = Boolean.toString(lentes);
                break;
            case 5:
                msj = Boolean.toString(barba);
                break;
            case 6:
                msj = Boolean.toString(bigote);
                break;
            case 7:
                msj = Boolean.toString(sombrero);
                break;
            case 8:
                msj = Boolean.toString(calvo);
                break;
        }
        return msj;
    }

}
