package turistalocal;

/**
 *
 * @author Aylin Yepez
 */
public class CasillaEspecial extends Casilla{
    private Jugador jugador;
    private Banco banco;

    public CasillaEspecial(Jugador jugador, Banco banco) {
        this.jugador = jugador;
        this.banco = banco;
    }
    
    public void seleccionaEspecial(int posicion){
        String msj;
        switch(posicion){
                case 1: //SALIDA
                    msj = Salida();
                   break;
                case 4://VISA
                    msj = Visa();
                   break;
                case 11://DEPORTADO
                    msj = Deportado();
                   break;
                case 21: //OCEANIA
                    msj = Oceania();
                   break;
                case 29: //ADUANA
                    msj = Aduana();
                   break;
                case 31: //GROENLANDIA
                    msj = Groenlandia();
                   break;
                default: //WHATS
                    msj = Whats();
                   break;
           }
        System.out.println(msj);
    }
    
    public String Salida(){
        jugador.setDinero(jugador.getDinero() + 100);
        return "Salida";
    }
    
    private String Visa(){
        jugador.pagar(100, banco);
        return "Visa";
    }
    
    private String Whats(){
        Whats whats = new Whats();
        Whats what = whats.getWhatsAleatorio();
        //Paga=Negativo ; Cobra=Positivo
        //COBRA?
        if(what.getBanco()<0){
            //Pasamos a positivo y pagamos
            jugador.pagar(what.getBanco() * (-1), banco);
        }else{
            jugador.setDinero(jugador.getDinero()+what.getBanco());
        }
        return what.getMsj();
    }
    
    
    
    private String Deportado(){
        jugador.setPosicion(1);        
        return "Deportado";
    }
    
    private String Oceania(){
        jugador.settPerdidos(3);
        return "Oceania";
    }
    
    private String Aduana(){
        int dados = banco.tiraDados();
        System.out.println("Tu dados salieron igual a "+dados);
        String msj;
        if(dados==6 || dados==7 || dados==8){
            msj = "Te has salvado, no pagas";
        }else{
            jugador.pagar(100, banco);
            msj = "Por conceptos de Aduana pagas 100";
        }
        return msj;
    }
    
    private String Groenlandia(){
        jugador.pagar(50, banco);
        return "Groenlandia";
    }
    
}


