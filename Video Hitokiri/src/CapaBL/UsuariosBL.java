/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaBL;

import CapaDA.UsuariosDA;
import Entidades.FechaIngreso;
import Entidades.Usuarios;
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
public class UsuariosBL {

    UsuariosDA objUsuariosDA;

    public UsuariosBL() {
        objUsuariosDA = new UsuariosDA();
        try {
            LeerArchivoUsuarios();
        } catch (Exception ex) {
            Logger.getLogger(UsuariosBL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean Insertar(Usuarios Entidad) throws Exception {
        try {
            return objUsuariosDA.Insertar(Entidad);
        } catch (Exception ex) {
            throw new Exception("Error en la insercion");
        }
    }

    public boolean Eliminar(String StrUsuario) throws Exception {
        try {
            return objUsuariosDA.Eliminar(StrUsuario);
        } catch (Exception ex) {
            throw new Exception("Error en la eliminación");
        }
    }

    public boolean Actualizar(Usuarios Entidad) throws Exception {
        try {
            return objUsuariosDA.Actualizar(Entidad);
        } catch (Exception ex) {
            throw new Exception("Error en la actualización");
        }
    }

    public Usuarios ConsultaPorID(String StrUsuario) throws Exception {
        try {
            return objUsuariosDA.ConsultaPorID(StrUsuario);
        } catch (Exception ex) {
            throw new Exception("Error en la consulta");
        }
    }

    public ArrayList<Usuarios> Listado() throws Exception {
        try {
            return objUsuariosDA.Listado();
        } catch (Exception ex) {
            throw new Exception("Error al llamar la lista");
        }
    }

    public void LeerArchivoUsuarios() throws Exception {
        String pathProject = "";
        FileReader objfileReader = null;
        BufferedReader objbufferedReader = null;

        try {
            String line = null;
            String line1 = null;
            String[] VecLista = null;//new String[6];
            String[] VecFecha = null;//new String[3];
            Usuarios objUsuarios = null;
            FechaIngreso objFechaIngreso;
            pathProject = new File("").getAbsolutePath().trim();
            pathProject += "\\src\\ArchivosPlanos\\Usuarios.txt";;
            objfileReader = new FileReader(pathProject);
            objbufferedReader = new BufferedReader(objfileReader);

            while ((line = objbufferedReader.readLine()) != null) {
                VecLista = line.split(",");
                objUsuarios = new Usuarios();
                objUsuarios.setStrUsuario(VecLista[0]);
                objUsuarios.setStrContrasena(VecLista[1]);
                objUsuarios.setIntIdempleado(Integer.parseInt(VecLista[2]));
                line1 = VecLista[3];
                VecFecha = line1.split("/");
                int IntDia = Integer.parseInt(VecFecha[0]);
                int IntMes = Integer.parseInt(VecFecha[1]);
                int IntAno = Integer.parseInt(VecFecha[2]);
                objUsuarios.setClassFechaIngreso(objFechaIngreso = new FechaIngreso(IntDia, IntMes, IntAno));
                objUsuarios.setBoolBloqueado(Boolean.parseBoolean(VecLista[4]));
                objUsuarios.setStrEstado(VecLista[5]);
                objUsuariosDA.Insertar(objUsuarios);
            }
            objbufferedReader.close();
        } catch (Exception ex) {
            throw new Exception("Error al llamar la lista");
        } finally {
            objbufferedReader.close();
        }
    }
  
    public void grabarArchivoUsuarios() throws FileNotFoundException, Exception {
        String ruta = new File(".").getAbsolutePath() + "\\src\\ArchivosPlanos\\Usuarios.txt";
        PrintWriter pw = new PrintWriter(ruta); // si dejo (ruta) por default es false, lo que va a hacer es sobreescribir
        try // el archivo, si lo pongo (ruta, true), lo que va a hacer es salvar respetando 
        {                                       // la última línea
            for (Usuarios item : Listado()) {
                pw.println(item.getStrUsuario() + ","
                + item.getStrContrasena() + ","
                + item.getIntIdempleado() + ","
                + item.getClassFechaIngreso() + ","
                + item.isBoolBloqueado() + ","
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
        ArrayList<Usuarios> ListaConsecutivo = new ArrayList();
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