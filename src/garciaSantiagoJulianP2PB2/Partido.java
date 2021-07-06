package garciaSantiagoJulianP2PB2;

import java.util.ArrayList;
import java.util.HashSet;

public class Partido {
    private final String codigoPartido;
    private final String equipoLocal;
    private final String equipoVisitante;
    private final HashSet<Miembro> jugadores;
    private final ArrayList<Gol> goles = new ArrayList();

    public Partido(HashSet<Miembro> jugadores, String equipoLocal, String equipoVisitante) {
        this.jugadores = jugadores;
        this.codigoPartido = equipoLocal + equipoVisitante;
        this.equipoLocal = equipoLocal;
        this.equipoVisitante = equipoVisitante;
    }

    public String getCodigoPartido() {
        return codigoPartido;
    }

    public String getEquipoLocal() {
        return equipoLocal;
    }

    public String getEquipoVisitante() {
        return equipoVisitante;
    }

    public ArrayList<Gol> getGoles() {
        return goles;
    }

    public void sumarGol(String nombreJugador, int minuto, String equipo) throws JugadorNoEncontradoException {
        Jugador jugador = (Jugador)this.jugadores.stream().filter(m -> m.getNombre().equals(nombreJugador)).findFirst().orElse(null);
        if (jugador != null) {
            Gol nuevoGol = new Gol(jugador, minuto, equipo);
            this.goles.add(nuevoGol);
        } else {
            throw new JugadorNoEncontradoException("Jugador no encontrado en el partido");
        }
    }

    public String getGanador() {
        long golesLocal = this.goles.stream().filter(g -> g.getEquipo().equals(this.equipoLocal)).count();
        long golesVisitante = this.goles.stream().filter(g -> g.getEquipo().equals(this.equipoVisitante)).count();
        String ganador;
        if (golesLocal > golesVisitante) {
            ganador = this.equipoLocal + "(Local)";
        } else if (golesVisitante > golesLocal) {
            ganador = this.equipoVisitante + "(Visitante)";
        } else {
            ganador = "Empate";
        }
        return ganador;
    }
}
