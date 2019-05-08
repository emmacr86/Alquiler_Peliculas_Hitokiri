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
public class Vacaciones {
    private int IntIdVacaciones;
    private int IntIdempleado;
    private FechaIngreso ClassFechaSolicitud;
    private int IntCantidadDias;
    private String StrEstado;

    public Vacaciones(int IntIdVacaciones, int IntIdempleado, FechaIngreso ClassFechaSolicitud, int IntCantidadDias, String StrEstado) {
        this.IntIdVacaciones = IntIdVacaciones;
        this.IntIdempleado = IntIdempleado;
        this.ClassFechaSolicitud = ClassFechaSolicitud;
        this.IntCantidadDias = IntCantidadDias;
        this.StrEstado = StrEstado;
    }
    
    public Vacaciones() {
        this.IntIdVacaciones = 0;
        this.IntIdempleado = 0;
        this.ClassFechaSolicitud = null;
        this.IntCantidadDias = 0;
        this.StrEstado = "";
    }    

    public int getIntIdVacaciones() {
        return IntIdVacaciones;
    }

    public void setIntIdVacaciones(int IntIdVacaciones) {
        this.IntIdVacaciones = IntIdVacaciones;
    }

    public int getIntIdempleado() {
        return IntIdempleado;
    }

    public void setIntIdempleado(int IntIdempleado) {
        this.IntIdempleado = IntIdempleado;
    }

    public FechaIngreso getClassFechaSolicitud() {
        return ClassFechaSolicitud;
    }

    public void setClassFechaSolicitud(FechaIngreso ClassFechaSolicitud) {
        this.ClassFechaSolicitud = ClassFechaSolicitud;
    }

    public int getIntCantidadDias() {
        return IntCantidadDias;
    }

    public void setIntCantidadDias(int IntCantidadDias) {
        this.IntCantidadDias = IntCantidadDias;
    }

    public String getStrEstado() {
        return StrEstado;
    }

    public void setStrEstado(String StrEstado) {
        this.StrEstado = StrEstado;
    }
    
    
}
