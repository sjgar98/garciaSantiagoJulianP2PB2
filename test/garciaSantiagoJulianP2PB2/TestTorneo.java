package garciaSantiagoJulianP2PB2;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestTorneo {

    Torneo torneoDeFutbol;

    @Before
    public void crearTorneo() {
        torneoDeFutbol = new Torneo();
    }

    @Test
    public void debeRegistrarUnMiembro() {
        Jugador nuevoJugador = new Jugador(15500600, "Carlos", "Tevez", "Argentina", 32, Posicion.MED);
        torneoDeFutbol.registrarMiembro(nuevoJugador);
        assertEquals("debe registrar un miembro", 1, torneoDeFutbol.getMiembros().size());
    }

    @Test
    public void debeRegistrarUnPartido() {
        Jugador jugadorArgentina = new Jugador(15500600, "Carlos", "Tevez", "Argentina", 32, Posicion.MED);
        Jugador jugadorChile = new Jugador(25400600, "Roberto", "Garay", "Chile", 25, Posicion.MED);
        torneoDeFutbol.registrarMiembro(jugadorArgentina);
        torneoDeFutbol.registrarMiembro(jugadorChile);
        torneoDeFutbol.registrarPartido("Argentina", "Chile");
        assertEquals("debe registrar un partido", 1, torneoDeFutbol.getPartidos().size());
    }

    @Test
    public void debeRegistrarUnGol() {
        Jugador jugadorArgentina = new Jugador(15500600, "Carlos", "Tevez", "Argentina", 32, Posicion.MED);
        Jugador jugadorChile = new Jugador(25400600, "Roberto", "Garay", "Chile", 25, Posicion.MED);
        torneoDeFutbol.registrarMiembro(jugadorArgentina);
        torneoDeFutbol.registrarMiembro(jugadorChile);
        torneoDeFutbol.registrarPartido("Argentina", "Chile");
        try {
            torneoDeFutbol.registrarGol("ArgentinaChile", "Carlos Tevez", 15, "Argentina");
        } catch(Exception e) {}
        Partido partido = torneoDeFutbol.getPartido("ArgentinaChile");
        assertEquals("debe registrar un gol", 1, partido.getGoles().size());
    }

    @Test
    public void noDebeRegistrarUnGolParaPartidoNoEncontrado() {
        Jugador jugadorArgentina = new Jugador(15500600, "Carlos", "Tevez", "Argentina", 32, Posicion.MED);
        torneoDeFutbol.registrarMiembro(jugadorArgentina);
        assertThrows(
            PartidoNoEncontradoException.class,
            () -> torneoDeFutbol.registrarGol("ArgentinaChile", "Carlos Tevez", 15, "Argentina")
        );
    }

    @Test
    public void noDebeRegistrarUnGolParaJugadorNoEncontrado() {
        Jugador jugadorArgentina = new Jugador(15500600, "Carlos", "Tevez", "Argentina", 32, Posicion.MED);
        Jugador jugadorChile = new Jugador(25400600, "Roberto", "Garay", "Chile", 25, Posicion.MED);
        torneoDeFutbol.registrarMiembro(jugadorArgentina);
        torneoDeFutbol.registrarMiembro(jugadorChile);
        torneoDeFutbol.registrarPartido("Uruguay", "Chile");
        assertThrows(
            JugadorNoEncontradoException.class,
            () -> torneoDeFutbol.registrarGol("UruguayChile", "Carlos Tevez", 15, "Argentina")
        );
    }

    @Test
    public void debeGanarEquipoLocal() {
        Jugador jugadorArgentina = new Jugador(15500600, "Carlos", "Tevez", "Argentina", 32, Posicion.MED);
        Jugador jugadorChile = new Jugador(25400600, "Roberto", "Garay", "Chile", 25, Posicion.MED);
        torneoDeFutbol.registrarMiembro(jugadorArgentina);
        torneoDeFutbol.registrarMiembro(jugadorChile);
        torneoDeFutbol.registrarPartido("Argentina", "Chile");
        try {
            torneoDeFutbol.registrarGol("ArgentinaChile", "Carlos Tevez", 15, "Argentina");
            torneoDeFutbol.registrarGol("ArgentinaChile", "Roberto Garay", 35, "Chile");
            torneoDeFutbol.registrarGol("ArgentinaChile", "Carlos Tevez", 50, "Argentina");
        } catch(Exception e) {}
        Partido partido = torneoDeFutbol.getPartido("ArgentinaChile");
        assertEquals("debe haber 3 goles", 3, partido.getGoles().size());
        assertEquals("debe ganar Argentina como local", "Argentina(Local)", partido.getGanador());
    }

    @Test
    public void debenEmpatar() {
        Jugador jugadorArgentina = new Jugador(15500600, "Carlos", "Tevez", "Argentina", 32, Posicion.MED);
        Jugador jugadorChile = new Jugador(25400600, "Roberto", "Garay", "Chile", 25, Posicion.MED);
        torneoDeFutbol.registrarMiembro(jugadorArgentina);
        torneoDeFutbol.registrarMiembro(jugadorChile);
        torneoDeFutbol.registrarPartido("Argentina", "Chile");
        try {
            torneoDeFutbol.registrarGol("ArgentinaChile", "Carlos Tevez", 15, "Argentina");
            torneoDeFutbol.registrarGol("ArgentinaChile", "Roberto Garay", 35, "Chile");
        } catch(Exception e) {}
        Partido partido = torneoDeFutbol.getPartido("ArgentinaChile");
        assertEquals("debe haber 2 goles", 2, partido.getGoles().size());
        assertEquals("deben empatar los equipos", "Empate", partido.getGanador());
    }
}
