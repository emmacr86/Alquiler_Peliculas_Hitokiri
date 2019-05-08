/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaBL;

import CapaDA.VacacionesDA;
import Entidades.FechaIngreso;
import Entidades.Vacaciones;
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
public class VacacionesBL {

    VacacionesDA objVacacionesDA;

    public VacacionesBL() {
        objVacacionesDA = new VacacionesDA();
        try {
            LeerArchivoVacaciones();
        } catch (Exception ex) {
            Logger.getLogger(VacacionesBL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean Insertar(Vacaciones Entidad) throws Exception {
        try {
            return objVacacionesDA.Insertar(Entidad);
        } catch (Exception ex) {
            throw new Exception("Error en la insercion");
        }
    }

    public boolean Eliminar(int IntIdempleado) throws Exception {
        try {
            return objVacacionesDA.Eliminar(IntIdempleado);
        } catch (Exception ex) {
            throw new Exception("Error en la eliminación");
        }
    }

    public boolean Actualizar(Vacaciones Entidad) throws Exception {
        try {
            return objVacacionesDA.Actualizar(Entidad);
        } catch (Exception ex) {
            throw new Exception("Error en la actualización");
        }
    }

    public Vacaciones ConsultaPorID(int IntIdempleado) throws Exception {
        try {
            return objVacacionesDA.ConsultaPorID(IntIdempleado);
        } catch (Exception ex) {
            throw new Exception("Error en la consulta");
        }
    }

    public ArrayList<Vacaciones> Listado() throws Exception {
        try {
            return objVacacionesDA.Listado();
        } catch (Exception ex) {
            throw new Exception("Error al llamar la lista");
        }
    }

    public void LeerArchivoVacaciones() throws Exception {
        String pathProject = "";
        FileReader objfileReader = null;
        BufferedReader objbufferedReader = null;

        try {
            String line = null;
            String line1 = null;
            String[] VecLista = null;//new String[5];
            String[] VecFecha = null;//new String[3];
            Vacaciones objVacaciones = null;
            FechaIngreso objFechaIngreso;
            pathProject = new File("").getAbsolutePath().trim();
            pathProject += "\\src\\ArchivosPlanos\\Vacaciones.txt";;
            objfileReader = new FileReader(pathProject);
            objbufferedReader = new BufferedReader(objfileReader);

            while ((line = objbufferedReader.readLine()) != null) {
                VecLista = line.split(",");
                objVacaciones = new Vacaciones();
                objVacaciones.setIntIdVacaciones(Integer.parseInt(VecLista[0]));
                objVacaciones.setIntIdempleado(Integer.parseInt(VecLista[1]));
                line1 = VecLista[2];
                VecFecha = line1.split("/");
                int IntDia = Integer.parseInt(VecFecha[0]);
                int IntMes = Integer.parseInt(VecFecha[1]);
                int IntAno = Integer.parseInt(VecFecha[2]);
                objVacaciones.setClassFechaSolicitud(objFechaIngreso = new FechaIngreso(IntDia, IntMes, IntAno));
                objVacaciones.setIntCantidadDias(Integer.parseInt(VecLista[3]));
                objVacaciones.setStrEstado(VecLista[4]);
                objVacacionesDA.Insertar(objVacaciones);
            }
            objbufferedReader.close();
        } catch (Exception ex) {
            throw new Exception("Error al llamar la lista");
        } finally {
            objbufferedReader.close();
        }
    }

    public void grabarArchivoVacaciones() throws FileNotFoundException, Exception {
        String ruta = new File(".").getAbsolutePath() + "\\src\\ArchivosPlanos\\Vacaciones.txt";
        PrintWriter pw = new PrintWriter(ruta); // si dejo (ruta) por default es false, lo que va a hacer es sobreescribir
        try // el archivo, si lo pongo (ruta, true), lo que va a hacer es salvar respetando 
        {                                       // la última línea..
            for (Vacaciones item : Listado()) {
                pw.println(item.getIntIdVacaciones() + ","
                        + item.getIntIdempleado() + ","
                        + item.getClassFechaSolicitud() + ","
                        + item.getIntCantidadDias() + ","
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
        ArrayList<Vacaciones> ListaConsecutivo = new ArrayList();
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

    public int ConsecutivoIDVacaciones() throws Exception {
        int IntUltimoConsecutivo = 0;
        ArrayList<Vacaciones> ListaConsecutivo = new ArrayList();
        ListaConsecutivo = Listado();
        int Vector[] = new int[ListaConsecutivo.size()];

        for (int i = 0; i < ListaConsecutivo.size(); i++) {
            Vector[i] = ListaConsecutivo.get(i).getIntIdVacaciones();
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
