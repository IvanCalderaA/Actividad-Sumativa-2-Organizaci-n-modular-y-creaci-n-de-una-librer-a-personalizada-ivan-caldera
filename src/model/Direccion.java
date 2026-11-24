package model;

import util.Validaciones;

/** Dirección asociada a una persona (composición). */
public class Direccion {
    private String calle;
    private String numero;
    private String ciudad;
    private String region;

    public Direccion(String calle, String numero, String ciudad, String region) {
        setCalle(calle);
        setNumero(numero);
        setCiudad(ciudad);
        setRegion(region);
    }
    public String getCalle() { return calle; }
    public void setCalle(String calle) { this.calle = Validaciones.textoObligatorio(calle, "calle"); }
    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = Validaciones.textoObligatorio(numero, "número"); }
    public String getCiudad() { return ciudad; }
    public void setCiudad(String ciudad) { this.ciudad = Validaciones.textoObligatorio(ciudad, "ciudad"); }
    public String getRegion() { return region; }
    public void setRegion(String region) { this.region = Validaciones.textoObligatorio(region, "región"); }

    @Override public String toString() { return calle + " " + numero + ", " + ciudad + ", " + region; }
}
