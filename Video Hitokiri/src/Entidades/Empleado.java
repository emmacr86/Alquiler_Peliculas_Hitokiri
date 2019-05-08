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
public class Empleado {
    private String StrNombre;
    private String StrApellido1;
    private String StrApellido2;
    private int IntCedula;
    private int IntIdempleado;
    private FechaIngreso ClassFechaIngreso;
    private String StrEstado;

    public Empleado(String StrNombre, String StrApellido1, String StrApellido2, int IntCedula, int IntIdempleado, FechaIngreso ClassFechaIngreso, String StrEstado) {
        this.StrNombre = StrNombre;
        this.StrApellido1 = StrApellido1;
        this.StrApellido2 = StrApellido2;
        this.IntCedula = IntCedula;
        this.IntIdempleado = IntIdempleado;
        this.ClassFechaIngreso = ClassFechaIngreso;
        this.StrEstado = StrEstado;
    }
    
        public Empleado() {
        this.StrNombre = "";
        this.StrApellido1 = "";
        this.StrApellido2 = "";
        this.IntCedula = 0;
        this.IntIdempleado = 0;
        this.ClassFechaIngreso = null;
        this.StrEstado = "";
    }

    public String getStrNombre() {
        return StrNombre;
    }

    public void setStrNombre(String StrNombre) {
        this.StrNombre = StrNombre;
    }

    public String getStrApellido1() {
        return StrApellido1;
    }

    public void setStrApellido1(String StrApellido1) {
        this.StrApellido1 = StrApellido1;
    }

    public String getStrApellido2() {
        return StrApellido2;
    }

    public void setStrApellido2(String StrApellido2) {
        this.StrApellido2 = StrApellido2;
    }

    public int getIntCedula() {
        return IntCedula;
    }

    public void setIntCedula(int IntCedula) {
        this.IntCedula = IntCedula;
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

    public String getStrEstado() {
        return StrEstado;
    }

    public void setStrEstado(String StrEstado) {
        this.StrEstado = StrEstado;
    }
        
    
    
       
    
}
