package modelo;
import java.util.ArrayList;

public class Empresa {
    private ArrayList<Trabajador> trabajadores;

    public Empresa() {
        this.trabajadores = new ArrayList<>();
    }

    public void agregarTrabajador(Trabajador trabajador) {
        trabajadores.add(trabajador);
    }

    public ArrayList<Trabajador> getTrabajadores() {
        return trabajadores;
    }
}
