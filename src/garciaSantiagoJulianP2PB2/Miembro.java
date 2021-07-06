package garciaSantiagoJulianP2PB2;

public abstract class Miembro {
    private final int dni;
    private final String nombre;
    private final String apellido;
    private final String equipo;

    public Miembro(int dni, String nombre, String apellido, String equipo) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.equipo = equipo;
    }

    public String getNombre() {
        return nombre + " " + apellido;
    }

    public String getEquipo() {
        return equipo;
    }

    public abstract String getTipo();
}
