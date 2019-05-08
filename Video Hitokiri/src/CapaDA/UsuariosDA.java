/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaDA;

import Entidades.Usuarios;
import java.util.ArrayList;

/**
 *
 * @author chaconqu
 */
public class UsuariosDA {
        
    private ArrayList<Usuarios> listaUsuarios;
    
    public UsuariosDA()
    {
        listaUsuarios = new ArrayList<>();
    }
    
    public boolean Insertar(Usuarios Entidad) throws Exception
    {
        try
        {
            if (Entidad != null)
            {
                listaUsuarios.add(Entidad);
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
    
    public boolean Eliminar (String StrUsuario) throws Exception //REVISAR ESTO, NO SE SI HAY QUE CAMBIAR EL ESTADO A INACTIVO O BLOQUEARLO
    {
        try
        {
            int IntContador = 0;
            if (listaUsuarios.size()>0) 
            {
                for (Usuarios item : listaUsuarios)
                {
                    if (item.getStrUsuario().equals(StrUsuario))
                    {
                        listaUsuarios.get(IntContador).setStrEstado("Inactivo");
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
    
    public boolean Actualizar(Usuarios Entidad) throws Exception
    {
        try
        {
            int IntContador = 0;
            if (listaUsuarios.size()> 0)
            {
                for (Usuarios item : listaUsuarios)
                {
                    if (item.getIntIdempleado()==Entidad.getIntIdempleado())
                    {
                        listaUsuarios.get(IntContador).setStrUsuario(Entidad.getStrUsuario());
                        listaUsuarios.get(IntContador).setStrContrasena(Entidad.getStrContrasena());
                        listaUsuarios.get(IntContador).setIntIdempleado(Entidad.getIntIdempleado());
                        listaUsuarios.get(IntContador).setClassFechaIngreso(Entidad.getClassFechaIngreso());
                        listaUsuarios.get(IntContador).setStrEstado(Entidad.getStrEstado());
                        listaUsuarios.get(IntContador).setBoolBloqueado(Entidad.isBoolBloqueado());
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
            throw new Exception("Se presento un error al actualizar");
        }
    }
        
    public Usuarios ConsultaPorID(String StrUsuario) throws Exception
    {
        try
        {
            if (listaUsuarios.size()> 0)
            {
                for (Usuarios item : listaUsuarios)
                {
                    if (item.getStrUsuario().equals(StrUsuario))
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
    
    public ArrayList<Usuarios> Listado() throws Exception
    {
        try
        {
            if (listaUsuarios.size()> 0)
            {
                return listaUsuarios;
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
