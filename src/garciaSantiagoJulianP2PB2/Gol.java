package garciaSantiagoJulianP2PB2;

public class Gol {
    private final Jugador jugador;
    private final int minuto;
    private final String equipo;

    public Gol(Jugador jugador, int minuto, String equipo) {
        this.jugador = jugador;
        this.minuto = minuto;
        this.equipo = equipo;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public int getMinuto() {
        return minuto;
    }

    public String getEquipo() {
        return equipo;
    }

    public String toString() {
        return "Jugador " + this.getJugador().getNombre() + " " + this.getMinuto() + "min";
    }
}
