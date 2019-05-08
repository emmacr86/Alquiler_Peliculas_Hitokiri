/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaDA;

import Entidades.Vacaciones;
import java.util.ArrayList;

/**
 *
 * @author chaconqu
 */
public class VacacionesDA {
    
    private ArrayList<Vacaciones> listaVacaciones;
    
    public VacacionesDA()
    {
        listaVacaciones = new ArrayList<>();
    }

    public boolean Insertar(Vacaciones Entidad) throws Exception
    {
        try
        {
            if (Entidad != null)
            {
                listaVacaciones.add(Entidad);
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
    
    public boolean Eliminar (int IntIdempleado) throws Exception
    {
        try
        {
            int IntContador = 0;
            if (listaVacaciones.size()>0) 
            {
                for (Vacaciones item : listaVacaciones)
                {
                    if (item.getIntIdempleado() == IntIdempleado)
                    {
                        listaVacaciones.get(IntContador).setStrEstado("Inactivo");
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
    
    public boolean Actualizar(Vacaciones Entidad) throws Exception
    {
        try
        {
            int IntContador = 0;
            if (listaVacaciones.size()> 0)
            {
                for (Vacaciones item : listaVacaciones)
                {
                    if (item.getIntIdempleado() == Entidad.getIntIdempleado())
                    {
                        listaVacaciones.get(IntContador).setIntIdVacaciones(Entidad.getIntIdVacaciones());
                        listaVacaciones.get(IntContador).setIntIdempleado(Entidad.getIntIdempleado());
                        listaVacaciones.get(IntContador).setClassFechaSolicitud(Entidad.getClassFechaSolicitud());
                        listaVacaciones.get(IntContador).setIntCantidadDias(Entidad.getIntCantidadDias());
                        listaVacaciones.get(IntContador).setStrEstado(Entidad.getStrEstado());
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
    
    public Vacaciones ConsultaPorID(int IntIdempleado) throws Exception
    {
        try
        {
            if (listaVacaciones.size()> 0)
            {
                for (Vacaciones item : listaVacaciones)
                {
                    if (item.getIntIdempleado() == IntIdempleado)
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
    
    public ArrayList<Vacaciones> Listado() throws Exception
    {
        try
        {
            if (listaVacaciones.size()> 0)
            {
                return listaVacaciones;
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
