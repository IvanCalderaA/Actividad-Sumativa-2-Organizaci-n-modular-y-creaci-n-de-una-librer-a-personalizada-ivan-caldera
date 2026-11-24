package model;

import util.Validaciones;

/** Cliente de la agencia. */
public class Cliente extends Persona {
    private String nacionalidad;
    private String tipoCliente;
    private int totalReservas;

    public Cliente(String rut, String nombre, String apellido, String telefono, String email,
                   Direccion direccion, String nacionalidad, String tipoCliente) {
        super(rut, nombre, apellido, telefono, email, direccion);
        setNacionalidad(nacionalidad); setTipoCliente(tipoCliente); this.totalReservas = 0;
    }
    public String getNacionalidad() { return nacionalidad; }
    public void setNacionalidad(String nacionalidad) { this.nacionalidad = Validaciones.textoObligatorio(nacionalidad, "nacionalidad"); }
    public String getTipoCliente() { return tipoCliente; }
    public void setTipoCliente(String tipoCliente) {
        String tipo = Validaciones.textoObligatorio(tipoCliente, "tipo de cliente");
        if (!tipo.equalsIgnoreCase("Nacional") && !tipo.equalsIgnoreCase("Internacional"))
            throw new IllegalArgumentException("El tipo de cliente debe ser Nacional o Internacional.");
        this.tipoCliente = tipo;
    }
    public int getTotalReservas() { return totalReservas; }
    public void setTotalReservas(int totalReservas) { this.totalReservas = Validaciones.enteroNoNegativo(totalReservas, "total de reservas"); }
    public void registrarReserva() { totalReservas++; }
    @Override public String toString() {
        return "Tipo: Cliente | " + super.toString() + " | Nacionalidad: " + nacionalidad
                + " | Tipo cliente: " + tipoCliente + " | Reservas: " + totalReservas;
    }
}
