package turistalocal;

/**
 *
 * @author Aylin Yepez
 */
public class TuristaLocal {
    public static void main(String[] args) {
        Banco banco = new Banco();
        Propiedad propiedad = new Propiedad();
        banco.setPropiedades(propiedad.traePropiedades());
        banco.cambiaTurno();
    }
}
