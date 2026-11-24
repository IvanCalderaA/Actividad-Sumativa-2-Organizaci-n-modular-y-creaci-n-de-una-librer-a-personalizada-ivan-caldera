package model;

import util.Validaciones;

/** Clase base para las personas vinculadas a Llanquihue Tour. */
public class Persona {
    private String rut;
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    private Direccion direccion;

    public Persona(String rut, String nombre, String apellido, String telefono, String email, Direccion direccion) {
        setRut(rut); setNombre(nombre); setApellido(apellido); setTelefono(telefono); setEmail(email); setDireccion(direccion);
    }
    public String getRut() { return rut; }
    public void setRut(String rut) { this.rut = Validaciones.textoObligatorio(rut, "RUT/identificador"); }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = Validaciones.textoObligatorio(nombre, "nombre"); }
    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = Validaciones.textoObligatorio(apellido, "apellido"); }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = Validaciones.textoObligatorio(telefono, "teléfono"); }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = Validaciones.email(email); }
    public Direccion getDireccion() { return direccion; }
    public void setDireccion(Direccion direccion) {
        if (direccion == null) throw new IllegalArgumentException("La dirección es obligatoria.");
        this.direccion = direccion;
    }
    @Override public String toString() {
        return "Tipo: Persona | Identificador: " + rut + " | Nombre: " + nombre + " " + apellido
                + " | Teléfono: " + telefono + " | Email: " + email + " | Dirección: " + direccion;
    }
}
