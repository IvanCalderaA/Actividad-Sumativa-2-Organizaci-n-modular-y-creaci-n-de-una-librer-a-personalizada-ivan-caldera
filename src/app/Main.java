package app;

import model.Persona;
import service.RegistroPersonasService;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/** Punto de entrada: carga los datos y permite listar, buscar y filtrar registros. */
public class Main {
    public static void main(String[] args) {
        RegistroPersonasService servicio = new RegistroPersonasService();
        String archivo = args.length > 0 ? args[0] : "data/personas.csv";
        try {
            int cargados = servicio.cargarDesdeCsv(archivo);
            System.out.println("===============================================");
            System.out.println("       SISTEMA DE GESTIÓN - LLANQUIHUE TOUR");
            System.out.println("===============================================");
            System.out.println("Archivo: " + archivo + " | Registros cargados: " + cargados);
            ejecutarMenu(servicio);
        } catch (IOException e) {
            System.out.println("No fue posible leer el archivo '" + archivo + "'.");
            System.out.println("Detalle: " + e.getMessage());
            System.out.println("Verifica que exista data/personas.csv o indica la ruta como argumento.");
        }
    }

    private static void ejecutarMenu(RegistroPersonasService servicio) {
        try (Scanner sc = new Scanner(System.in)) {
            boolean continuar = true;
            while (continuar) {
                System.out.println("\n1. Mostrar todos los registros\n2. Buscar por RUT/identificador\n3. Buscar por nombre\n4. Filtrar por tipo\n0. Salir");
                System.out.print("Selecciona una opción: ");
                String opcion = sc.nextLine().trim();
                switch (opcion) {
                    case "1": mostrar(servicio.listarTodos()); break;
                    case "2":
                        System.out.print("Ingresa RUT/identificador: ");
                        Persona encontrada = servicio.buscarPorIdentificador(sc.nextLine());
                        if (encontrada == null) System.out.println("No se encontraron registros.");
                        else System.out.println(encontrada);
                        break;
                    case "3":
                        System.out.print("Ingresa nombre o apellido: ");
                        mostrar(servicio.buscarPorNombre(sc.nextLine()));
                        break;
                    case "4":
                        System.out.print("Tipo (PERSONA, EMPLEADO, GUIA, CLIENTE): ");
                        mostrar(servicio.filtrarPorTipo(sc.nextLine().trim()));
                        break;
                    case "0": continuar = false; System.out.println("Programa finalizado."); break;
                    default: System.out.println("Opción no válida. Intenta nuevamente.");
                }
            }
        }
    }
    private static void mostrar(List<Persona> lista) {
        if (lista.isEmpty()) { System.out.println("No hay registros para mostrar."); return; }
        System.out.println("\n--- REGISTROS ENCONTRADOS: " + lista.size() + " ---");
        for (Persona persona : lista) System.out.println(persona + "\n");
    }
}
