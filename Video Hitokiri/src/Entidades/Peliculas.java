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
public class Peliculas {
    private int IntIdPelicula;
    private String StrDescripcion;
    private String StrGenero;
    private int IntCantidadPeliculas;
    private String StrEstado;

    public Peliculas(int IntIdPelicula, String StrDescripcion, String StrGenero, int IntCantidadPeliculas, String StrEstado) {
        this.IntIdPelicula = IntIdPelicula;
        this.StrDescripcion = StrDescripcion;
        this.StrGenero = StrGenero;
        this.IntCantidadPeliculas = IntCantidadPeliculas;
        this.StrEstado = StrEstado;
    }

    public Peliculas() {
        this.IntIdPelicula = 0;
        this.StrDescripcion = "";
        this.StrGenero = "";
        this.IntCantidadPeliculas = 0;
        this.StrEstado = "";
    }

    public int getIntIdPelicula() {
        return IntIdPelicula;
    }

    public void setIntIdPelicula(int IntIdPelicula) {
        this.IntIdPelicula = IntIdPelicula;
    }

    public String getStrDescripcion() {
        return StrDescripcion;
    }

    public void setStrDescripcion(String StrDescripcion) {
        this.StrDescripcion = StrDescripcion;
    }

    public String getStrGenero() {
        return StrGenero;
    }

    public void setStrGenero(String StrGenero) {
        this.StrGenero = StrGenero;
    }

    public int getIntCantidadPeliculas() {
        return IntCantidadPeliculas;
    }

    public void setIntCantidadPeliculas(int IntCantidadPeliculas) {
        this.IntCantidadPeliculas = IntCantidadPeliculas;
    }

    public String getStrEstado() {
        return StrEstado;
    }

    public void setStrEstado(String StrEstado) {
        this.StrEstado = StrEstado;
    }
    
    
    
    
}
