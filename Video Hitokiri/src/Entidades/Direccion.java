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
public class Direccion {
    private String StrProvincia;
    private String StrCanton;
    private String StrDistrito;
    private String StrDetalle;

    public Direccion(String StrProvincia, String StrCanton, String StrDistrito, String StrDetalle) {
        this.StrProvincia = StrProvincia;
        this.StrCanton = StrCanton;
        this.StrDistrito = StrDistrito;
        this.StrDetalle = StrDetalle;
    }
    
    public Direccion() {
        this.StrProvincia = "";
        this.StrCanton = "";
        this.StrDistrito = "";
        this.StrDetalle = "";
    }    

    public String getStrProvincia() {
        return StrProvincia;
    }

    public void setStrProvincia(String StrProvincia) {
        this.StrProvincia = StrProvincia;
    }

    public String getStrCanton() {
        return StrCanton;
    }

    public void setStrCanton(String StrCanton) {
        this.StrCanton = StrCanton;
    }

    public String getStrDistrito() {
        return StrDistrito;
    }

    public void setStrDistrito(String StrDistrito) {
        this.StrDistrito = StrDistrito;
    }

    public String getStrDetalle() {
        return StrDetalle;
    }

    public void setStrDetalle(String StrDetalle) {
        this.StrDetalle = StrDetalle;
    }
    
    
}
