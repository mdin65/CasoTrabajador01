package datos;

import modelo.Trabajador;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DatosTrabajador implements InterfazDatos {
    private static final String RutaArchivo = "src/main/java/documentos/Trabajadores.txt";

    @Override
    public List<Trabajador> cargarTrabajadores() throws IOException {
        List<Trabajador> trabajadores = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(RutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 5) {
                    trabajadores.add(new Trabajador(
                            datos[0].trim(),
                            datos[1].trim(),
                            datos[2].trim(),
                            datos[3].trim(),
                            datos[4].trim()
                    ));
                }
            }
        }

        return trabajadores;
    }

    @Override
    public void guardarTrabajadores(List<Trabajador> trabajadores) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(RutaArchivo))) {
            for (Trabajador t : trabajadores) {
                bw.write(String.format("%s,%s,%s,%s,%s",
                        t.getNombre(),
                        t.getApellido(),
                        t.getRut(),
                        t.getIsapre(),
                        t.getAfp()));
                bw.newLine();
            }
        }
    }
}