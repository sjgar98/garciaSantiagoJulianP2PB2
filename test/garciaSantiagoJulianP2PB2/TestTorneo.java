package garciaSantiagoJulianP2PB2;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

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
        Partido partidoRegistrado = torneoDeFutbol.getPartido("ArgentinaChile");
        assertEquals("debe registrar un partido", 1, torneoDeFutbol.getPartidos().size());
        assertNotNull("debe existir el partido", partidoRegistrado);
        assertEquals("Argentina debe ser local", "Argentina", partidoRegistrado.getEquipoLocal());
        assertEquals("Chile debe ser visitante", "Chile", partidoRegistrado.getEquipoVisitante());
    }

    @Test
    public void debeRegistrarUnGol() {
        boolean exceptionThrown = false;
        Jugador jugadorArgentina = new Jugador(15500600, "Carlos", "Tevez", "Argentina", 32, Posicion.MED);
        Jugador jugadorChile = new Jugador(25400600, "Roberto", "Garay", "Chile", 25, Posicion.MED);
        torneoDeFutbol.registrarMiembro(jugadorArgentina);
        torneoDeFutbol.registrarMiembro(jugadorChile);
        torneoDeFutbol.registrarPartido("Argentina", "Chile");
        try {
            torneoDeFutbol.registrarGol("ArgentinaChile", "Carlos Tevez", 15, "Argentina");
        } catch(Exception e) {
            exceptionThrown = true;
        }
        Partido partido = torneoDeFutbol.getPartido("ArgentinaChile");
        assertEquals("debe registrar un gol", 1, partido.getGoles().size());
        assertFalse("no debe tirar error", exceptionThrown);
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
        boolean exceptionThrown = false;
        Jugador jugadorArgentina = new Jugador(15500600, "Carlos", "Tevez", "Argentina", 32, Posicion.MED);
        Jugador jugadorChile = new Jugador(25400600, "Roberto", "Garay", "Chile", 25, Posicion.MED);
        torneoDeFutbol.registrarMiembro(jugadorArgentina);
        torneoDeFutbol.registrarMiembro(jugadorChile);
        torneoDeFutbol.registrarPartido("Argentina", "Chile");
        try {
            torneoDeFutbol.registrarGol("ArgentinaChile", "Carlos Tevez", 15, "Argentina");
            torneoDeFutbol.registrarGol("ArgentinaChile", "Carlos Tevez", 50, "Argentina");
            torneoDeFutbol.registrarGol("ArgentinaChile", "Roberto Garay", 35, "Chile");
        } catch(Exception e) {
            exceptionThrown = true;
        }
        Partido partido = torneoDeFutbol.getPartido("ArgentinaChile");
        assertEquals("debe haber 3 goles", 3, partido.getGoles().size());
        assertEquals("debe ganar Argentina como local", "Argentina(Local)", partido.getGanador());
        assertFalse("no debe tirar error", exceptionThrown);
    }

    @Test
    public void debenEmpatar() {
        boolean exceptionThrown = false;
        Jugador jugadorArgentina = new Jugador(15500600, "Carlos", "Tevez", "Argentina", 32, Posicion.MED);
        Jugador jugadorChile = new Jugador(25400600, "Roberto", "Garay", "Chile", 25, Posicion.MED);
        torneoDeFutbol.registrarMiembro(jugadorArgentina);
        torneoDeFutbol.registrarMiembro(jugadorChile);
        torneoDeFutbol.registrarPartido("Argentina", "Chile");
        try {
            torneoDeFutbol.registrarGol("ArgentinaChile", "Carlos Tevez", 15, "Argentina");
            torneoDeFutbol.registrarGol("ArgentinaChile", "Roberto Garay", 35, "Chile");
        } catch(Exception e) {
            exceptionThrown = true;
        }
        Partido partido = torneoDeFutbol.getPartido("ArgentinaChile");
        assertEquals("debe haber 2 goles", 2, partido.getGoles().size());
        assertEquals("deben empatar los equipos", "Empate", partido.getGanador());
        assertFalse("no debe tirar error", exceptionThrown);
    }

    @Test
    public void debeDevolverGolesOrdenados() {
        boolean exceptionThrown = false;
        Jugador jugadorArgentina = new Jugador(15500600, "Carlos", "Tevez", "Argentina", 32, Posicion.MED);
        Jugador jugadorChile = new Jugador(25400600, "Roberto", "Garay", "Chile", 25, Posicion.MED);
        ArrayList<Gol> listaDeGoles = new ArrayList<>();
        torneoDeFutbol.registrarMiembro(jugadorArgentina);
        torneoDeFutbol.registrarMiembro(jugadorChile);
        torneoDeFutbol.registrarPartido("Argentina", "Chile");
        try {
            torneoDeFutbol.registrarGol("ArgentinaChile", "Carlos Tevez", 15, "Argentina");
            torneoDeFutbol.registrarGol("ArgentinaChile", "Carlos Tevez", 50, "Argentina");
            torneoDeFutbol.registrarGol("ArgentinaChile", "Roberto Garay", 35, "Chile");
            listaDeGoles = torneoDeFutbol.getListaDeGoles("ArgentinaChile");
        } catch(Exception e) {
            exceptionThrown = true;
        }
        assertEquals("debe retornar ordenado", "[Jugador Carlos Tevez 15min, Jugador Roberto Garay 35min, Jugador Carlos Tevez 50min]", listaDeGoles.toString());
        assertFalse("no debe tirar error", exceptionThrown);
    }
}
