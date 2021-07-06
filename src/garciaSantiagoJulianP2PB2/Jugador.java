package garciaSantiagoJulianP2PB2;

public class Jugador extends Miembro {

    private final int numeroCamiseta;
    private final Posicion posicion;

    public Jugador(int dni, String nombre, String apellido, String equipo, int numeroCamiseta, Posicion posicion) {
        super(dni, nombre, apellido, equipo);
        this.numeroCamiseta = numeroCamiseta;
        this.posicion = posicion;
    }

    @Override
    public String getTipo() {
        return "Jugador";
    }
}
