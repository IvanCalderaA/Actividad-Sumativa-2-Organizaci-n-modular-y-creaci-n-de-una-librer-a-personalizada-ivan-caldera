package model;

import util.Validaciones;

/** Empleado de la agencia. */
public class Empleado extends Persona {
    private String cargo;
    private double sueldo;
    private String fechaIngreso;

    public Empleado(String rut, String nombre, String apellido, String telefono, String email,
                    Direccion direccion, String cargo, double sueldo, String fechaIngreso) {
        super(rut, nombre, apellido, telefono, email, direccion);
        setCargo(cargo); setSueldo(sueldo); setFechaIngreso(fechaIngreso);
    }
    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = Validaciones.textoObligatorio(cargo, "cargo"); }
    public double getSueldo() { return sueldo; }
    public void setSueldo(double sueldo) { this.sueldo = Validaciones.numeroNoNegativo(sueldo, "sueldo"); }
    public String getFechaIngreso() { return fechaIngreso; }
    public void setFechaIngreso(String fechaIngreso) { this.fechaIngreso = Validaciones.textoObligatorio(fechaIngreso, "fecha de ingreso"); }
    @Override public String toString() {
        return "Tipo: Empleado | " + super.toString() + " | Cargo: " + cargo
                + " | sueldo: $" + String.format(java.util.Locale.US, "%.0f", sueldo) + " | Fecha ingreso: " + fechaIngreso;
    }
}
