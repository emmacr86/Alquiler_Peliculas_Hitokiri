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
public class Usuarios {
    private String StrUsuario;
    private String StrContrasena;
    private int IntIdempleado;
    private FechaIngreso ClassFechaIngreso;
    private boolean BoolBloqueado;
    private String StrEstado;

    public Usuarios(String StrUsuario, String StrContrasena, int IntIdempleado, FechaIngreso ClassFechaIngreso, boolean BoolBloqueado, String StrEstado) {
        this.StrUsuario = StrUsuario;
        this.StrContrasena = StrContrasena;
        this.IntIdempleado = IntIdempleado;
        this.ClassFechaIngreso = ClassFechaIngreso;
        this.BoolBloqueado = BoolBloqueado;
        this.StrEstado = StrEstado;
    }

    public Usuarios() {
        this.StrUsuario = "";
        this.StrContrasena = "";
        this.IntIdempleado = 0;
        this.ClassFechaIngreso = null;
        this.BoolBloqueado = false;
        this.StrEstado = "";
    }

    public String getStrUsuario() {
        return StrUsuario;
    }

    public void setStrUsuario(String StrUsuario) {
        this.StrUsuario = StrUsuario;
    }

    public String getStrContrasena() {
        return StrContrasena;
    }

    public void setStrContrasena(String StrContrasena) {
        this.StrContrasena = StrContrasena;
    }

    public int getIntIdempleado() {
        return IntIdempleado;
    }

    public void setIntIdempleado(int IntIdempleado) {
        this.IntIdempleado = IntIdempleado;
    }

    public FechaIngreso getClassFechaIngreso() {
        return ClassFechaIngreso;
    }

    public void setClassFechaIngreso(FechaIngreso ClassFechaIngreso) {
        this.ClassFechaIngreso = ClassFechaIngreso;
    }

    public boolean isBoolBloqueado() {
        return BoolBloqueado;
    }

    public void setBoolBloqueado(boolean BoolBloqueado) {
        this.BoolBloqueado = BoolBloqueado;
    }

    public String getStrEstado() {
        return StrEstado;
    }

    public void setStrEstado(String StrEstado) {
        this.StrEstado = StrEstado;
    }
    
    
}    
    