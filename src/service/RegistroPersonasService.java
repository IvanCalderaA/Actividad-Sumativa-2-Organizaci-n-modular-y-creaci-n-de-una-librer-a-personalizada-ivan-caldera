package service;

import model.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/** Administra la base de datos de personas y la carga desde un archivo CSV delimitado por punto y coma. */
public class RegistroPersonasService {
    private final ArrayList<Persona> personas = new ArrayList<>();

    public int cargarDesdeCsv(String ruta) throws IOException {
        int cargados = 0;
        int lineaNumero = 0;
        try (BufferedReader lector = Files.newBufferedReader(Path.of(ruta), StandardCharsets.UTF_8)) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                lineaNumero++;
                if (linea.trim().isEmpty() || linea.trim().startsWith("#") || lineaNumero == 1 && linea.toLowerCase(Locale.ROOT).startsWith("tipo;")) continue;
                try {
                    Persona persona = crearPersona(linea.split(";", -1));
                    if (buscarPorIdentificador(persona.getRut()) == null) {
                        personas.add(persona);
                        cargados++;
                    } else {
                        System.out.println("Aviso: identificador duplicado en línea " + lineaNumero + ": " + persona.getRut());
                    }
                } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException ex) {
                    System.out.println("Línea " + lineaNumero + " omitida: " + ex.getMessage());
                }
            }
        }
        return cargados;
    }

    private Persona crearPersona(String[] d) {
        if (d.length < 11) throw new IllegalArgumentException("faltan columnas; revisa el formato del CSV.");
        String tipo = d[0].trim().toUpperCase(Locale.ROOT);
        Direccion direccion = new Direccion(d[6], d[7], d[8], d[9]);
        switch (tipo) {
            case "PERSONA":
                return new Persona(d[1], d[2], d[3], d[4], d[5], direccion);
            case "EMPLEADO":
                if (d.length < 13) throw new IllegalArgumentException("EMPLEADO requiere cargo, sueldo y fecha de ingreso.");
                return new Empleado(d[1], d[2], d[3], d[4], d[5], direccion, d[10], Double.parseDouble(d[11]), d[12]);
            case "CLIENTE":
                if (d.length < 12) throw new IllegalArgumentException("CLIENTE requiere nacionalidad y tipo.");
                Cliente cliente = new Cliente(d[1], d[2], d[3], d[4], d[5], direccion, d[10], d[11]);
                if (d.length > 12 && !d[12].isBlank()) cliente.setTotalReservas(Integer.parseInt(d[12]));
                return cliente;
            case "GUIA":
                if (d.length < 15) throw new IllegalArgumentException(" requiere sueldo, fecha, idiomas, especialidad y experiencia.");
                return new GuiaTuristico(d[1], d[2], d[3], d[4], d[5], direccion,
                        Double.parseDouble(d[10]), d[11], d[12], d[13], Integer.parseInt(d[14]));
            default: throw new IllegalArgumentException("tipo de registro desconocido: " + tipo);
        }
    }

    public List<Persona> listarTodos() { return new ArrayList<>(personas); }
    public Persona buscarPorIdentificador(String identificador) {
        for (Persona persona : personas) if (persona.getRut().equalsIgnoreCase(identificador.trim())) return persona;
        return null;
    }
    public List<Persona> buscarPorNombre(String texto) {
        List<Persona> resultado = new ArrayList<>();
        String criterio = texto.trim().toLowerCase(Locale.ROOT);
        for (Persona p : personas) {
            String nombreCompleto = (p.getNombre() + " " + p.getApellido()).toLowerCase(Locale.ROOT);
            if (nombreCompleto.contains(criterio)) resultado.add(p);
        }
        return resultado;
    }
    public List<Persona> filtrarPorTipo(String tipo) {
        List<Persona> resultado = new ArrayList<>();
        for (Persona p : personas) {
            if (tipo.equalsIgnoreCase("GUIA") && p instanceof GuiaTuristico) resultado.add(p);
            else if (tipo.equalsIgnoreCase("EMPLEADO") && p instanceof Empleado && !(p instanceof GuiaTuristico)) resultado.add(p);
            else if (tipo.equalsIgnoreCase("CLIENTE") && p instanceof Cliente) resultado.add(p);
            else if (tipo.equalsIgnoreCase("PERSONA") && p.getClass() == Persona.class) resultado.add(p);
        }
        return resultado;
    }
    public int cantidad() { return personas.size(); }
}
