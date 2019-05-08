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
public class Cliente {
    private String StrNombre;
    private String StrApellido1;
    private String StrApellido2;
    private FechaAnos ClassFechaNacimiento;
    private int IntCedula;
    private int IntCarnet;
    private Direccion ClassDireccion;
    private String StrGenero;
    private String StrProfesion;
    private String StrEstado;

    public Cliente(String StrNombre, String StrApellido1, String StrApellido2, FechaAnos ClassFechaNacimiento, int IntCedula, int IntCarnet, Direccion ClassDireccion, String StrGenero, String StrProfesion, String StrEstado) {
        this.StrNombre = StrNombre;
        this.StrApellido1 = StrApellido1;
        this.StrApellido2 = StrApellido2;
        this.ClassFechaNacimiento = ClassFechaNacimiento;
        this.IntCedula = IntCedula;
        this.IntCarnet = IntCarnet;
        this.ClassDireccion = ClassDireccion;
        this.StrGenero = StrGenero;
        this.StrProfesion = StrProfesion;
        this.StrEstado = StrEstado;
    }

    public Cliente() {
        this.StrNombre = "";
        this.StrApellido1 = "";
        this.StrApellido2 = "";
        this.ClassFechaNacimiento = null;
        this.IntCedula = 0;
        this.IntCarnet = 0;
        this.ClassDireccion = null;
        this.StrGenero = "";
        this.StrProfesion = "";
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

    public FechaAnos getClassFechaNacimiento() {
        return ClassFechaNacimiento;
    }

    public void setClassFechaNacimiento(FechaAnos ClassFechaNacimiento) {
        this.ClassFechaNacimiento = ClassFechaNacimiento;
    }

    public int getIntCedula() {
        return IntCedula;
    }

    public void setIntCedula(int IntCedula) {
        this.IntCedula = IntCedula;
    }

    public int getIntCarnet() {
        return IntCarnet;
    }

    public void setIntCarnet(int IntCarnet) {
        this.IntCarnet = IntCarnet;
    }

    public Direccion getClassDireccion() {
        return ClassDireccion;
    }

    public void setClassDireccion(Direccion ClassDireccion) {
        this.ClassDireccion = ClassDireccion;
    }

    public String getStrGenero() {
        return StrGenero;
    }

    public void setStrGenero(String StrGenero) {
        this.StrGenero = StrGenero;
    }

    public String getStrProfesion() {
        return StrProfesion;
    }

    public void setStrProfesion(String StrProfesion) {
        this.StrProfesion = StrProfesion;
    }

    public String getStrEstado() {
        return StrEstado;
    }

    public void setStrEstado(String StrEstado) {
        this.StrEstado = StrEstado;
    }

    public Object getClassFechaIngreso() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
        
        
}
