/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaDA;

import Entidades.Cliente;
import java.util.ArrayList;


/**
 *
 * @author chaconqu
 */
public class ClienteDA {
    
    private ArrayList<Cliente> listaClientes;
    
    public ClienteDA()
    {
        listaClientes = new ArrayList<>();
    }
    
    public boolean Insertar(Cliente Entidad) throws Exception
    {
        try
        {
            if (Entidad != null)
            {
                listaClientes.add(Entidad);
                return true;
            }
            else
            {
                return false;
            }
        }
        catch (Exception ex)
        {
            throw new Exception("Se presentó un error al ingresar los datos");
        }
    }
    
    public boolean Eliminar (int IntCarnet) throws Exception
    {
        try
        {
            int IntContador = 0;
            if (listaClientes.size()>0) 
            {
                for (Cliente item : listaClientes)
                {
                    if (item.getIntCarnet() == IntCarnet)
                    {
                        listaClientes.get(IntContador).setStrEstado("Inactivo");
                        return true;
                    }
                    IntContador += 1;
                }
                return false;
            }
            else
            {
                return false;
            }
        }
        catch (Exception ex)
        {
            throw new Exception("Se presentó un error al eliminar datos");
        }
    }
    
    public boolean Actualizar(Cliente Entidad) throws Exception
    {
        try
        {
            int IntContador = 0;
            if (listaClientes.size()> 0)
            {
                for (Cliente item : listaClientes)
                {
                    if (item.getIntCarnet() == Entidad.getIntCarnet())
                    {
                        listaClientes.get(IntContador).setStrNombre(Entidad.getStrNombre());
                        listaClientes.get(IntContador).setStrApellido1(Entidad.getStrApellido1());
                        listaClientes.get(IntContador).setStrApellido2(Entidad.getStrApellido2());
                        listaClientes.get(IntContador).setClassFechaNacimiento(Entidad.getClassFechaNacimiento());
                        listaClientes.get(IntContador).setIntCedula(Entidad.getIntCedula());
                        listaClientes.get(IntContador).setIntCarnet(Entidad.getIntCarnet());
                        listaClientes.get(IntContador).setClassDireccion(Entidad.getClassDireccion());
                        listaClientes.get(IntContador).setStrGenero(Entidad.getStrGenero());
                        listaClientes.get(IntContador).setStrProfesion(Entidad.getStrProfesion());
                        listaClientes.get(IntContador).setStrEstado(Entidad.getStrEstado());
                        return true;
                    }
                    IntContador += 1;
                }
                return false;
            }
            else
            {
                return false;
            }
        }
        catch (Exception ex)
        {
            throw new Exception("Se presento un error al insertar");
        }
    }
    
    public Cliente ConsultaPorID(int Intcedula) throws Exception
    {
        try
        {
            if (listaClientes.size()> 0)
            {
                for (Cliente item : listaClientes)
                {
                    if (item.getIntCedula() == Intcedula)
                    {
                        return item;
                    }
                }
                return null;
            }
            else
            {
                return null;
            }
        }
        catch (Exception ex)
        {
            throw new Exception("Se presento un error al consultar información");
        }
    }
    
    public ArrayList<Cliente> Listado() throws Exception
    {
        try
        {
            if (listaClientes.size()> 0)
            {
                return listaClientes;
            }
            else
            {
                return null;
            }
        }
        catch (Exception ex)
        {
            throw new Exception("Se presento un error al consultar la lista");
        }
    }
}
