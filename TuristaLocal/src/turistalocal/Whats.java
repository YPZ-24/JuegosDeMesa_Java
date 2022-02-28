package turistalocal;

import java.util.ArrayList;

/**
 *
 * @author Aylin Yepez
 */
public class Whats {
    private int id;
    private String msj;
    private int banco;

    public Whats() {
    }
    
    public Whats(int id, String msj, int banco) {
        this.id = id;
        this.msj = msj;
        this.banco = banco;
    }
    
    private Whats traeWhats(int numero){
        ArrayList<Whats> whatss = new ArrayList<Whats>();
        whatss.add(new Whats(1,"Por ayuda al gobierno cobre 100",100));
        whatss.add(new Whats(2,"Por seguro de accidente cobre 100",100));
        whatss.add(new Whats(3,"Por estacionarse en un lugar prohubido pague 100",-100));
        whatss.add(new Whats(4,"Por concepto de propinas pague 50",-50));
        whatss.add(new Whats(5,"Derechos de migracion pague 100",-100));
        whatss.add(new Whats(6,"Por exceso de equipaje pague 100",-100));
        whatss.add(new Whats(7,"Prima de seguro pague 150",-150));
        whatss.add(new Whats(8,"Por tratar de ahorrar va a pagar 150",-150));
        whatss.add(new Whats(9,"Por conferencia cobre 50",50));
        whatss.add(new Whats(10,"Renovacion de pasaporte pague 50",-50));
        whatss.add(new Whats(11,"Por viaticos cobre 100",100));
        whatss.add(new Whats(12,"Deposito electronico cobre 50",50));
        whatss.add(new Whats(13,"Comisiones por ventas cobre 200",200));
        whatss.add(new Whats(14,"Cobre por gastos de representacion 100",100));
        whatss.add(new Whats(15,"Por promocion turistica cobre 100",100));
        whatss.add(new Whats(16,"Por venta de fotos cobre 100",100));
        return whatss.get(numero-1);
    }
    
    public Whats getWhatsAleatorio(){
        return traeWhats(generarAleatorio());
    }
    
    private int generarAleatorio(){
        int totalWhats = 17;
        return (int) (Math.random() * totalWhats + 1);
    }
    
    public String getMsj() {
        return msj;
    }
    
    public int getBanco() {
        return banco;
    }
    
}
