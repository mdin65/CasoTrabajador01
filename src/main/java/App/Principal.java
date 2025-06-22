package App;

import Util.CargaDatos;
import Modelo.*;

import Vista.*;
import java.util.*;

public class Principal {
    public static void main(String[] args) {
        // Cargar datos de trabajadores
        Map<String, Trabajador> trabajadores = CargaDatos.cargaTrabajadoresDesdeResources();

        // Iniciar aplicación
        new MenuCLI(trabajadores).iniciar();
    }
}