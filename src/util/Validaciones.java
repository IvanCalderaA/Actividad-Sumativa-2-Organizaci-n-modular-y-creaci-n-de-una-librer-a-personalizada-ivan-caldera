package util;

/** Validaciones sencillas reutilizables en el modelo. */
public final class Validaciones {
    private Validaciones() { }

    public static String textoObligatorio(String valor, String campo) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException("El campo " + campo + " es obligatorio.");
        }
        return valor.trim();
    }

    public static String email(String valor) {
        String limpio = textoObligatorio(valor, "email");
        if (!limpio.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            throw new IllegalArgumentException("El email no tiene un formato válido: " + limpio);
        }
        return limpio;
    }

    public static double numeroNoNegativo(double valor, String campo) {
        if (!Double.isFinite(valor) || valor < 0) {
            throw new IllegalArgumentException("El campo " + campo + " debe ser un número igual o mayor que cero.");
        }
        return valor;
    }

    public static int enteroNoNegativo(int valor, String campo) {
        if (valor < 0) throw new IllegalArgumentException("El campo " + campo + " no puede ser negativo.");
        return valor;
    }
}
