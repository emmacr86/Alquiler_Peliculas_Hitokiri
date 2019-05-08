/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaBL;

import CapaDA.PeliculasDA;
import Entidades.Peliculas;
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
public class PeliculasBL {

    PeliculasDA objPeliculasDA;

    public PeliculasBL() {
        objPeliculasDA = new PeliculasDA();
        try {
            LeerArchivoPeliculas();
        } catch (Exception ex) {
            Logger.getLogger(PeliculasBL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean Insertar(Peliculas Entidad) throws Exception {
        try {
            return objPeliculasDA.Insertar(Entidad);
        } catch (Exception ex) {
            throw new Exception("Error en la insercion");
        }
    }

    public boolean Eliminar(int IntIdPelicula) throws Exception {
        try {
            return objPeliculasDA.Eliminar(IntIdPelicula);
        } catch (Exception ex) {
            throw new Exception("Error en la eliminación");
        }
    }

    public boolean Actualizar(Peliculas Entidad) throws Exception {
        try {
            return objPeliculasDA.Actualizar(Entidad);
        } catch (Exception ex) {
            throw new Exception("Error en la actualización");
        }
    }

    public Peliculas ConsultaPorID(int IntIdPelicula) throws Exception {
        try {
            return objPeliculasDA.ConsultaPorID(IntIdPelicula);
        } catch (Exception ex) {
            throw new Exception("Error en la consulta");
        }
    }

    public ArrayList<Peliculas> Listado() throws Exception {
        try {
            return objPeliculasDA.Listado();
        } catch (Exception ex) {
            throw new Exception("Error al llamar la lista");
        }
    }

    public void LeerArchivoPeliculas() throws Exception {
        String pathProject = "";
        FileReader objfileReader = null;
        BufferedReader objbufferedReader = null;
        
        try {
            String line = null;
            String[] VecLista = null;//new String[5];
            Peliculas objPeliculas = null;
            pathProject = new File("").getAbsolutePath().trim();
            pathProject += "\\src\\ArchivosPlanos\\Peliculas.txt";;
            objfileReader = new FileReader(pathProject);
            objbufferedReader = new BufferedReader(objfileReader);

            while ((line = objbufferedReader.readLine()) != null) {
                VecLista = line.split(",");
                objPeliculas = new Peliculas();
                objPeliculas.setIntIdPelicula(Integer.parseInt(VecLista[0]));
                objPeliculas.setStrDescripcion(VecLista[1]);
                objPeliculas.setStrGenero(VecLista[2]);
                objPeliculas.setIntCantidadPeliculas(Integer.parseInt(VecLista[3]));
                objPeliculas.setStrEstado(VecLista[4]);
                objPeliculasDA.Insertar(objPeliculas);
            }
            objbufferedReader.close();
        } catch (Exception ex) {
            throw new Exception("Error al llamar la lista");
        } finally {
            objbufferedReader.close();
        }
    }
    
    public void grabarArchivoPeliculas() throws FileNotFoundException, Exception {
        String ruta = new File(".").getAbsolutePath() + "\\src\\ArchivosPlanos\\Peliculas.txt";
        PrintWriter pw = new PrintWriter(ruta); // si dejo (ruta) por default es false, lo que va a hacer es sobreescribir
        try // el archivo, si lo pongo (ruta, true), lo que va a hacer es salvar respetando 
        {                                       // la última línea
            for (Peliculas item : Listado()) {
                pw.println(item.getIntIdPelicula() + ","
                + item.getStrDescripcion() + ","
                + item.getStrGenero() + ","
                + item.getIntCantidadPeliculas() + ","
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
        ArrayList<Peliculas> ListaConsecutivo = new ArrayList();
        ListaConsecutivo = Listado();
        int Vector[] = new int[ListaConsecutivo.size()];

        for (int i = 0; i < ListaConsecutivo.size(); i++) {
            Vector[i] = ListaConsecutivo.get(i).getIntIdPelicula();
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