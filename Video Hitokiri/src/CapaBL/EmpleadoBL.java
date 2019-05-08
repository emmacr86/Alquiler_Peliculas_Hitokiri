/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaBL;

import CapaDA.EmpleadoDA;
import Entidades.Empleado;
import Entidades.FechaIngreso;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author chaconqu
 */
public class EmpleadoBL {

    EmpleadoDA objEmpleadoDA;

    public EmpleadoBL() {
        objEmpleadoDA = new EmpleadoDA();
        try {
            LeerArchivoEmpleado();
        } catch (Exception ex) {
            Logger.getLogger(EmpleadoBL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean Insertar(Empleado Entidad) throws Exception {
        try {
            return objEmpleadoDA.Insertar(Entidad);
        } catch (Exception ex) {
            throw new Exception("Error en la insercion");
        }
    }

    public boolean Eliminar(int IntIdempleado) throws Exception {
        try {
            return objEmpleadoDA.Eliminar(IntIdempleado);
        } catch (Exception ex) {
            throw new Exception("Error en la eliminación");
        }
    }

    public boolean Actualizar(Empleado Entidad) throws Exception {
        try {
            return objEmpleadoDA.Actualizar(Entidad);
        } catch (Exception ex) {
            throw new Exception("Error en la actualización");
        }
    }

    public Empleado ConsultaPorID(int IntIdempleado) throws Exception {
        try {
            return objEmpleadoDA.ConsultaPorID(IntIdempleado);
        } catch (Exception ex) {
            throw new Exception("Error en la consulta");
        }
    }

    public ArrayList<Empleado> Listado() throws Exception {
        try {
            return objEmpleadoDA.Listado();
        } catch (Exception ex) {
            throw new Exception("Error al llamar la lista");
        }
    }

    public void LeerArchivoEmpleado() throws Exception {
        String pathProject = "";
        FileReader objfileReader = null;
        BufferedReader objbufferedReader = null;
        
        try {
            String line = null;
            String line1 = null;
            String[] VecLista = null;//new String[7];
            String[] VecFecha = null;//new String[3];
            Empleado objEmpleado = null;
            FechaIngreso objFechaIngreso;
            pathProject = new File("").getAbsolutePath().trim();
            pathProject += "\\src\\ArchivosPlanos\\Empleados.txt";;
            objfileReader = new FileReader(pathProject);
            objbufferedReader = new BufferedReader(objfileReader);

            while ((line = objbufferedReader.readLine()) != null) {
                VecLista = line.split(",");
                objEmpleado = new Empleado();
                objEmpleado.setStrNombre(VecLista[0]);
                objEmpleado.setStrApellido1(VecLista[1]);
                objEmpleado.setStrApellido2(VecLista[2]);
                objEmpleado.setIntCedula(Integer.parseInt(VecLista[3]));
                objEmpleado.setIntIdempleado(Integer.parseInt(VecLista[4]));
                line1 = VecLista[5];
                VecFecha = line1.split("/");
                int IntDia = Integer.parseInt(VecFecha[0]);
                int IntMes = Integer.parseInt(VecFecha[1]);
                int IntAno = Integer.parseInt(VecFecha[2]);
                objEmpleado.setClassFechaIngreso(objFechaIngreso = new FechaIngreso(IntDia, IntMes, IntAno));
                objEmpleado.setStrEstado(VecLista[6]);
                objEmpleadoDA.Insertar(objEmpleado);
                
            }
            objbufferedReader.close();
        } catch (Exception ex) {
            throw new Exception("Error al llamar la lista");
        }
        finally
        {
            objbufferedReader.close();
        }
    }
    
    public void grabarArchivoEmpleado() throws FileNotFoundException, Exception {
        String ruta = new File(".").getAbsolutePath() + "\\src\\ArchivosPlanos\\Empleados.txt";
        PrintWriter pw = new PrintWriter(ruta); // si dejo (ruta) por default es false, lo que va a hacer es sobreescribir
        try // el archivo, si lo pongo (ruta, true), lo que va a hacer es salvar respetando 
        {                                       // la última línea
            for (Empleado item : Listado()) {
                pw.println(item.getStrNombre() + ","
                + item.getStrApellido1() + ","
                + item.getStrApellido2() + ","
                + item.getIntCedula() + ","
                + item.getIntIdempleado() + ","
                + item.getClassFechaIngreso() + ","
                + item.getStrEstado());
            }
        } catch (FileNotFoundException ex) {
            throw new Exception("Erros al salvar el archivo: Error-->" + ex.getMessage());
        } catch (IOException ex) {
            throw new Exception("Error al salvar el archivo: Error-->" + ex.getMessage());
        } catch (Exception ex) {
            throw new Exception("Error al salvar el archivo: Error-->" + ex.getMessage());
        } finally {
            pw.close();
        }
    }
    
     public int Consecutivo() throws Exception {
        int IntUltimoConsecutivo = 0;
        ArrayList<Empleado> ListaConsecutivo = new ArrayList();
        ListaConsecutivo = Listado();
        int Vector[] = new int[ListaConsecutivo.size()];

        for (int i = 0; i < ListaConsecutivo.size(); i++) {
            Vector[i] = ListaConsecutivo.get(i).getIntIdempleado();
        }

        for (int i = 0; i < Vector.length - 1; i++) {
            for (int j = i + 1; j < Vector.length; j++) {
                if (Vector[i] > Vector[j]) {
                    int variableauxiliar = Vector[i];
                    Vector[i] = Vector[j];
                    Vector[j] = variableauxiliar;
                }
            }
        }

        IntUltimoConsecutivo = Vector[Vector.length - 1];

        return IntUltimoConsecutivo;
    }
    
    
    
}