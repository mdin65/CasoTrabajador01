package datos;

import java.io.IOException;
import java.util.List;
import modelo.Trabajador;

public interface InterfazDatos {
    List<Trabajador> cargarTrabajadores() throws IOException;
    void guardarTrabajadores(List<Trabajador> trabajadores) throws IOException;
}