package turistalocal;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Aylin Yepez
 */
public class Propiedad extends Casilla{
    private int valor;
    private int renta;
    private int hipoteca;
    private int noJugador;

    public Propiedad() {
    }
    
    public Propiedad(int numero, String nombre, String lote, int valor, int renta, int hipoteca) {
        super.numero = numero;
        super.nombre = nombre;
        super.lote = lote;
        this.valor = valor;
        this.renta = renta;
        this.hipoteca = hipoteca;
        this.noJugador = 0;
    }
    
    public void Comprar(Jugador jugador, Banco banco){
        boolean res;
        Scanner sc = new Scanner(System.in);
        System.out.println("Quieres comprarlo?");
        System.out.println(getNombre());
        res = sc.nextBoolean();
        //SI QUIERE COMPRARLO?
        if(res){
            //PEDIMOS QUE LO PAGUE 
            jugador.pagar(getValor(), banco);
            //AGREGAMOS LA PROPIEDAD A SUS PROPIEDADES
            jugador.setPropiedad(this);
            setNoJugador(jugador.getTurno());
        }
    }
    
    public boolean isUsado() {
        return this.noJugador!=0;
    }
    
    public int getNoJugador() {
        return noJugador;
    }
    
    public void pagarRenta(Jugador jugador, Jugador adversario){
        int pago;
        //Vemos si el dueño tiene todo el color
        if(loteCompleto(adversario.getPropiedades())){
            System.out.println("Pagas doble renta");
            pago = getRenta() * 2;
        }else{
            System.out.println("Pagas renta");
            pago = getRenta();
        }
        jugador.pagar(pago, adversario);
    }
    
    private boolean loteCompleto(ArrayList<Propiedad> propiedades){
        int totalP=0, totalLote;
        for(Propiedad p : propiedades) {
            if(p.getLote().equals(this.lote)){
                totalP++;
            }
        }
        switch(this.lote){
            case "Edificio": totalLote = 4; break;
            case "Linea": totalLote = 2; break;
            default: totalLote = 3; break;
        }
        return totalP == totalLote;
    }
    
    public int getRenta() {
        return renta;
    }
    
    public int getValor() {
        return valor;
    }
    
    public void setNoJugador(int noJugador) {
        this.noJugador = noJugador;
    }
    
    public int getHipoteca() {
        return hipoteca;
    }
    
     public ArrayList<Propiedad> traePropiedades(){
        ArrayList<Propiedad> propiedades = new ArrayList<>();
        
        propiedades.add(new Propiedad(1,"SALIDA","Especial",0,0,0));
        propiedades.add(new Propiedad(2,"MEXICO","Rojo",100,50,50));
        propiedades.add(new Propiedad(3,"E.U.A","Rojo",200,50,100));
        propiedades.add(new Propiedad(4,"VISA","Especial",0,0,0));
        propiedades.add(new Propiedad(5,"COSTA RICA","Rojo",200,100,100));
        propiedades.add(new Propiedad(6,"LINEA AEREA AMERICANA","Linea",100,50,50));
        propiedades.add(new Propiedad(7,"PERU","Morado",100,50,50));
        propiedades.add(new Propiedad(8,"ARGENTINA","Morado",200,50,100));
        propiedades.add(new Propiedad(9,"WHATS","Especial",0,0,0));
        propiedades.add(new Propiedad(10,"BRASIL","Morado",200,100,100));
        propiedades.add(new Propiedad(11,"DEPORTADO","Especial",0,0,0));
        propiedades.add(new Propiedad(12,"ESPANA","Verde",200,100,100));
        propiedades.add(new Propiedad(13,"ITALIA","Verde",300,100,150));
        propiedades.add(new Propiedad(14,"EMBAJADA","Edificio",200,50,100));
        propiedades.add(new Propiedad(15,"FRANCIA","Verde",300,200,150));
        propiedades.add(new Propiedad(16,"LINEA AEREA EUROPEA","Linea",100,50,50));
        propiedades.add(new Propiedad(17,"INGLATERRA","Naranja",200,100,100));
        propiedades.add(new Propiedad(18,"WHATS","Especial",0,0,0));
        propiedades.add(new Propiedad(19,"ALEMANIA","Naranja",300,100,150));
        propiedades.add(new Propiedad(20,"HOLANDA","Naranja",300,150,150));
        propiedades.add(new Propiedad(21,"OCEANIA","Especial",0,0,0));
        propiedades.add(new Propiedad(22,"EGIPTO","Cafe",300,100,150));
        propiedades.add(new Propiedad(23,"MARRUECOS","Cafe",400,150,200));
        propiedades.add(new Propiedad(24,"WHATS","Especial",0,0,0));
        propiedades.add(new Propiedad(25,"ARGELIA","Cafe",400,150,200));
        propiedades.add(new Propiedad(26,"LINEA AEREA AFRICANA","Linea",100,50,50));
        propiedades.add(new Propiedad(27,"SUDAFRICA","Amarillo",300,150,150));
        propiedades.add(new Propiedad(28,"CONGO","Amarillo",400,200,200));
        propiedades.add(new Propiedad(29,"ADUANA","Especial",0,0,0));
        propiedades.add(new Propiedad(30,"MADAGASCAR","Amarillo",500,250,250));
        propiedades.add(new Propiedad(31,"GROENLANDIA","Especial",0,0,0));
        propiedades.add(new Propiedad(32,"RUSIA","Azul",300,150,150));
        propiedades.add(new Propiedad(33,"CONSULADO","Edificio",200,50,100));
        propiedades.add(new Propiedad(34,"INDIA","Azul",400,150,200));
        propiedades.add(new Propiedad(35,"TURQUIA","Azul",400,200,200));
        propiedades.add(new Propiedad(36,"LINEA AEREA ASIATICA","Linea",100,50,50));
        propiedades.add(new Propiedad(37,"CHINA","Rosa",400,200,200));
        propiedades.add(new Propiedad(38,"WHATS","Especial",0,0,0));
        propiedades.add(new Propiedad(39,"JAPON","Rosa",500,200,250));
        propiedades.add(new Propiedad(40,"TAILANDIA","Rosa",600,250,300));
        
        return propiedades;
    }
    
}
