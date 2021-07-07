package garciaSantiagoJulianP2PB2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.stream.Collectors;

public class Torneo {
    private final HashSet<Miembro> miembros = new HashSet<>();
    private final HashMap<String, Partido> partidos = new HashMap<>();

    public HashSet<Miembro> getMiembros() {
        return miembros;
    }

    public HashMap<String, Partido> getPartidos() {
        return partidos;
    }

    public Partido getPartido(String codigoPartido) {
        return this.partidos.get(codigoPartido);
    }

    public void registrarMiembro(Miembro miembro) {
        this.miembros.add(miembro);
    }

    public void registrarPartido(String equipoLocal, String equipoVisitante) {
        HashSet<Miembro> jugadores = this.miembros.stream().filter(m -> m.getTipo().equals("Jugador") && (m.getEquipo().equals(equipoLocal) || m.getEquipo().equals(equipoVisitante))).collect(Collectors.toCollection(HashSet::new));
        Partido nuevoPartido = new Partido(jugadores, equipoLocal, equipoVisitante);
        this.partidos.put(nuevoPartido.getCodigoPartido(), nuevoPartido);
    }

    public void registrarGol(String codigoPartido, String nombreJugador, int minuto, String equipo) throws JugadorNoEncontradoException, PartidoNoEncontradoException {
        if (this.partidos.containsKey(codigoPartido)) {
            Partido partido = this.partidos.get(codigoPartido);
            partido.sumarGol(nombreJugador, minuto, equipo);
        } else {
            throw new PartidoNoEncontradoException("Partido no encontrado en el torneo");
        }
    }

    public String getGanadorPartido(String codigoPartido) throws PartidoNoEncontradoException {
        if (this.partidos.containsKey(codigoPartido)) {
            return this.partidos.get(codigoPartido).getGanador();
        } else {
            throw new PartidoNoEncontradoException("Partido no encontrado en el torneo");
        }
    }

    public ArrayList<Gol> getListaDeGoles(String codigoPartido) throws PartidoNoEncontradoException {
        if (this.partidos.containsKey(codigoPartido)) {
            Partido partido = this.partidos.get(codigoPartido);
            ArrayList<Gol> golesOrdenados = new ArrayList<>(partido.getGoles());
            Collections.sort(golesOrdenados, new MinutoGolComparator());
            System.out.println(golesOrdenados);
            return golesOrdenados;
        } else {
            throw new PartidoNoEncontradoException("Partido no encontrado en el torneo");
        }
    }
}
