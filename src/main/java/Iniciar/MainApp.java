package Iniciar;

import datos.DatosTrabajador;
import modelo.Trabajador;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        DatosTrabajador datos = new DatosTrabajador();
        Scanner scanner = new Scanner(System.in);

        try {
            // Cargar trabajadores existentes
            List<Trabajador> trabajadores = datos.cargarTrabajadores();
            System.out.println("Trabajadores cargados: " + trabajadores.size());

            // Menú simple
            while (true) {
                System.out.println("\n--- Sistema de Trabajadores ---");
                System.out.println("1. Listar trabajadores");
                System.out.println("2. Agregar trabajador");
                System.out.println("3. Salir");
                System.out.print("Seleccione opción: ");

                int opcion = scanner.nextInt();
                scanner.nextLine(); // Limpiar buffer

                switch (opcion) {
                    case 1:
                        listarTrabajadores(trabajadores);
                        break;
                    case 2:
                        agregarTrabajador(trabajadores, scanner);
                        datos.guardarTrabajadores(trabajadores);
                        break;
                    case 3:
                        System.out.println("Saliendo del sistema...");
                        return;
                    default:
                        System.out.println("Opción no válida");
                }
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static void listarTrabajadores(List<Trabajador> trabajadores) {
        System.out.println("\n--- Lista de Trabajadores ---");
        for (Trabajador t : trabajadores) {
            System.out.println(t.toString());
        }
    }

    private static void agregarTrabajador(List<Trabajador> trabajadores, Scanner scanner) {
        System.out.println("\n--- Agregar Nuevo Trabajador ---");

        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Apellido: ");
        String apellido = scanner.nextLine();

        System.out.print("RUT: ");
        String rut = scanner.nextLine();

        System.out.print("Isapre: ");
        String isapre = scanner.nextLine();

        System.out.print("AFP: ");
        String afp = scanner.nextLine();

        trabajadores.add(new Trabajador(nombre, apellido, rut, isapre, afp));
        System.out.println("Trabajador agregado exitosamente!");
    }
}