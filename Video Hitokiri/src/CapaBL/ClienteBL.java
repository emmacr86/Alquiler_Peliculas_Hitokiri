/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaBL;

import CapaDA.ClienteDA;
import Entidades.Cliente;
import Entidades.Direccion;
import Entidades.FechaAnos;
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
public class ClienteBL {

    private ClienteDA objClienteDA;
    //testing

    public ClienteBL() {
        objClienteDA = new ClienteDA();
        try {
            LeerArchivoCliente();
        } catch (Exception ex) {
            Logger.getLogger(ClienteBL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean Insertar(Cliente Entidad) throws Exception {
        try {
            return objClienteDA.Insertar(Entidad);
        } catch (Exception ex) {
            throw new Exception("Error en la insercion");
        }
    }

    public boolean Eliminar(int IntCarnet) throws Exception {
        try {
            return objClienteDA.Eliminar(IntCarnet);
        } catch (Exception ex) {
            throw new Exception("Error en la eliminación");
        }
    }

    public boolean Actualizar(Cliente Entidad) throws Exception {
        try {
            return objClienteDA.Actualizar(Entidad);
        } catch (Exception ex) {
            throw new Exception("Error en la actualización");
        }
    }

    public Cliente ConsultaPorID(int Intcedula) throws Exception {
        try {
            return objClienteDA.ConsultaPorID(Intcedula);
        } catch (Exception ex) {
            throw new Exception("Error en la consulta");
        }
    }

    public ArrayList<Cliente> Listado() throws Exception {
        try {
            return objClienteDA.Listado();
        } catch (Exception ex) {
            throw new Exception("Error al llamar la lista");
        }
    }

    public void LeerArchivoCliente() throws Exception {
        String pathProject = "";
        FileReader objfileReader = null;
        BufferedReader objbufferedReader = null;

        try {
            String line = null;
            String line1 = null;
            String[] VecLista = null;//new String[13];
            String[] VecFecha = null;//new String[3];
            Cliente objCliente = null;
            Direccion objDireccion = null;
            FechaAnos objFechaAnos;

            pathProject = new File("").getAbsolutePath().trim();
            pathProject += "\\src\\ArchivosPlanos\\Clientes.txt";;
            objfileReader = new FileReader(pathProject);
            objbufferedReader = new BufferedReader(objfileReader);

            while ((line = objbufferedReader.readLine()) != null) {
                VecLista = line.split(",");
                objCliente = new Cliente();
                objDireccion = new Direccion();
                objCliente.setStrNombre(VecLista[0]);
                objCliente.setStrApellido1(VecLista[1]);
                objCliente.setStrApellido2(VecLista[2]);
                line1 = VecLista[3];
                VecFecha = line1.split("/");
                int IntDia = Integer.parseInt(VecFecha[0]);
                int IntMes = Integer.parseInt(VecFecha[1]);
                int IntAno = Integer.parseInt(VecFecha[2]);
                objCliente.setClassFechaNacimiento(objFechaAnos = new FechaAnos(IntDia, IntMes, IntAno));
                objCliente.setIntCedula(Integer.parseInt(VecLista[4]));
                objCliente.setIntCarnet(Integer.parseInt(VecLista[5]));
                objDireccion.setStrCanton(VecLista[6]);
                objDireccion.setStrProvincia(VecLista[7]);
                objDireccion.setStrDistrito(VecLista[8]);
                objDireccion.setStrDetalle(VecLista[9]);
                objCliente.setStrGenero(VecLista[10]);
                objCliente.setStrProfesion(VecLista[11]);
                objCliente.setStrEstado(VecLista[12]);
                objCliente.setClassDireccion(objDireccion);
                objClienteDA.Insertar(objCliente);
            }

            objbufferedReader.close();
        } catch (Exception ex) {
            throw new Exception("Error al llamar la lista");
        } finally {
            objbufferedReader.close();
        }
    }

    public void grabarArchivoCliente() throws FileNotFoundException, Exception {
        String ruta = new File(".").getAbsolutePath() + "\\src\\ArchivosPlanos\\Clientes.txt";
        PrintWriter pw = new PrintWriter(ruta); // si dejo (ruta) por default es false, lo que va a hacer es sobreescribir
        try // el archivo, si lo pongo (ruta, true), lo que va a hacer es salvar respetando 
        {                                       // la última línea
            for (Cliente item : Listado()) {
                pw.println(item.getStrNombre() + ","
                        + item.getStrApellido1() + ","
                        + item.getStrApellido2() + ","
                        + item.getClassFechaNacimiento() + ","
                        + item.getIntCedula() + ","
                        + item.getIntCarnet() + ","
                        + item.getClassDireccion().getStrCanton() + ","
                        + item.getClassDireccion().getStrProvincia() + ","
                        + item.getClassDireccion().getStrDistrito() + ","
                        + item.getClassDireccion().getStrDetalle() + ","
                        + item.getStrGenero() + ","
                        + item.getStrProfesion() + ","
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
        ArrayList<Cliente> ListaConsecutivo = new ArrayList();
        ListaConsecutivo = Listado();
        int Vector[] = new int[ListaConsecutivo.size()];

        for (int i = 0; i < ListaConsecutivo.size(); i++) {
            Vector[i] = ListaConsecutivo.get(i).getIntCarnet();
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
