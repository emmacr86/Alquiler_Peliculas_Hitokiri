/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

/**
 *
 * @author chaconqu
 */
public class Reservaciones {

    private int IntCarnet;
    private String strNombreCompleto;
    private String strNombrePelicula;
    private FechaIngreso ClassFechaReserva;
    private int IntCantidadDías;
    private String StrEstado;
    private String StrFactura;

    public Reservaciones(int IntCarnet, String strNombreCompleto, FechaIngreso ClassFechaReserva, int IntCantidadDías, String strNombrePelicula, String StrEstado, String StrFactura) {
        this.IntCarnet = IntCarnet;
        this.strNombreCompleto = strNombreCompleto;
        this.ClassFechaReserva = ClassFechaReserva;
        this.IntCantidadDías = IntCantidadDías;
        this.strNombrePelicula = strNombrePelicula;
        this.StrEstado = StrEstado;
        this.StrFactura = StrFactura;
    }

    public Reservaciones() {
        this.IntCarnet = 0;
        this.strNombreCompleto = "";
        this.ClassFechaReserva = null;
        this.IntCantidadDías = 0;
        this.strNombrePelicula = "";
        this.StrEstado = "";
        this.StrFactura = "";

    }

    public String getStrFactura() {
        return StrFactura;
    }

    public void setStrFactura(String StrFactura) {
        this.StrFactura = StrFactura;
    }

    public String getStrEstado() {
        return StrEstado;
    }

    public void setStrEstado(String StrEstado) {
        this.StrEstado = StrEstado;
    }

    public String getStrNombrePelicula() {
        return strNombrePelicula;
    }

    public void setStrNombrePelicula(String strNombrePelicula) {
        this.strNombrePelicula = strNombrePelicula;
    }

    public int getIntCarnet() {
        return IntCarnet;
    }

    public void setIntCarnet(int IntCarnet) {
        this.IntCarnet = IntCarnet;
    }

    public String getStrNombreCompleto() {
        return strNombreCompleto;
    }

    public void setStrNombreCompleto(String strNombreCompleto) {
        this.strNombreCompleto = strNombreCompleto;
    }

    public FechaIngreso getClassFechaReserva() {
        return ClassFechaReserva;
    }

    public void setClassFechaReserva(FechaIngreso ClassFechaReserva) {
        this.ClassFechaReserva = ClassFechaReserva;
    }

    public int getIntCantidadDías() {
        return IntCantidadDías;
    }

    public void setIntCantidadDías(int IntCantidadDías) {
        this.IntCantidadDías = IntCantidadDías;
    }

}
