package Controlador;

import Modelo.Trabajador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class TrabajadorControllerTest {
    private TrabajadorController controller;
    private Map<String, Trabajador> trabajadores;

    @BeforeEach
    void setUp() {
        trabajadores = new HashMap<>();
        trabajadores.put("23.456.789-0",
                new Trabajador("Ana", "Rodriguez", "23.456.789-0", "Habitat", "Noroeste"));

        controller = new TrabajadorController(trabajadores);
        controller.setModoPruebas(true); // Desactiva el guardado automático
    }

    @Test
    @DisplayName("Eliminar trabajador no guarda en archivo real")
    void eliminarTrabajador_ModoPruebas() {
        String input = "23.456.789-0\nS";
        controller.eliminarTrabajador(new Scanner(input));

        // Verifica solo el estado en memoria
        assertFalse(trabajadores.containsKey("23.456.789-0"));

    }
    @Test
    @DisplayName("Cancelar eliminación mantiene al trabajador en el documento")
    void cancelarEliminacion_MantieneTrabajador() {
        String input = "23.456.789-0\nN"; // Usuario ingresa 'N' para cancelar
        controller.eliminarTrabajador(new Scanner(input));

        assertTrue(trabajadores.containsKey("23.456.789-0"),
                "El trabajador debería permanecer después de cancelar");
    }
    @Test
    @DisplayName("Eliminar RUT inexistente no modifica el documento")
    void eliminarRutInexistente_NoCambiaMapa() {
        int tamañoInicial = trabajadores.size();
        String input = "99.999.999-9\nS"; // RUT que no existe

        controller.eliminarTrabajador(new Scanner(input));

        assertEquals(tamañoInicial, trabajadores.size(),
                "El tamaño del mapa no debería cambiar");
        assertTrue(trabajadores.containsKey("23.456.789-0"),
                "El trabajador existente debería permanecer");
    }
    @Test
    @DisplayName("RUT con formato inválido no elimina ningún trabajador")
    void eliminarConRutInvalido_NoElimina() {
        int tamañoInicial = trabajadores.size();
        String input = "123456789\nS"; // RUT sin puntos ni guión

        controller.eliminarTrabajador(new Scanner(input));

        assertEquals(tamañoInicial, trabajadores.size(),
                "No debería eliminar con RUT mal formateado");
    }
    @Test
    @DisplayName("Eliminar múltiples trabajadores secuencialmente")
    void eliminarMultiplesTrabajadores() {
        Trabajador segundo = new Trabajador("Juan", "Pérez", "12.345.678-9", "Fonasa", "Modelo");
        trabajadores.put(segundo.getRut(), segundo);

        String input1 = "23.456.789-0\nS";
        controller.eliminarTrabajador(new Scanner(input1));
        assertFalse(trabajadores.containsKey("23.456.789-0"));

        String input2 = "12.345.678-9\nS";
        controller.eliminarTrabajador(new Scanner(input2));
        assertFalse(trabajadores.containsKey("12.345.678-9"));

        assertEquals(0, trabajadores.size(), "El mapa debería quedar vacío");
    }
    @Test
    @DisplayName("Verificar mensaje al cancelar eliminación")
    void cancelarEliminacion_MuestraMensaje() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        String input = "23.456.789-0\nN";
        controller.eliminarTrabajador(new Scanner(input));

        String output = outputStream.toString();
        assertTrue(output.contains("Eliminación cancelada"),
                "Debería mostrar mensaje de cancelación");

        System.setOut(System.out);
    }
}
