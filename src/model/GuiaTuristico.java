package model;

import util.Validaciones;

/** Guía turístico, especializado en servicios de la zona. */
public class GuiaTuristico extends Empleado {
    private String idiomas;
    private String especialidad;
    private int aniosExperiencia;

    public GuiaTuristico(String rut, String nombre, String apellido, String telefono, String email,
                         Direccion direccion, double salario, String fechaIngreso,
                         String idiomas, String especialidad, int aniosExperiencia) {
        super(rut, nombre, apellido, telefono, email, direccion, "Guía Turístico", salario, fechaIngreso);
        setIdiomas(idiomas); setEspecialidad(especialidad); setAniosExperiencia(aniosExperiencia);
    }
    public String getIdiomas() { return idiomas; }
    public void setIdiomas(String idiomas) { this.idiomas = Validaciones.textoObligatorio(idiomas, "idiomas"); }
    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = Validaciones.textoObligatorio(especialidad, "especialidad"); }
    public int getAniosExperiencia() { return aniosExperiencia; }
    public void setAniosExperiencia(int aniosExperiencia) { this.aniosExperiencia = Validaciones.enteroNoNegativo(aniosExperiencia, "años de experiencia"); }
    @Override public String toString() {
        return "Tipo: Guía turístico | " + super.toString() + " | Idiomas: " + idiomas
                + " | Especialidad: " + especialidad + " | Experiencia: " + aniosExperiencia + " años";
    }
}
