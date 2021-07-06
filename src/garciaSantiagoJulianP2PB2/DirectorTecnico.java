package garciaSantiagoJulianP2PB2;

public class DirectorTecnico extends Miembro {
    private final int edad;

    public DirectorTecnico(int dni, String nombre, String apellido, String equipo, int edad) {
        super(dni, nombre, apellido, equipo);
        this.edad = edad;
    }

    @Override
    public String getTipo() {
        return "Director Tecnico";
    }
}
