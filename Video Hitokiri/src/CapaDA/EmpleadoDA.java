/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaDA;

import Entidades.Empleado;
import java.util.ArrayList;

/**
 *
 * @author chaconqu
 */
public class EmpleadoDA {
    
    private ArrayList<Empleado> listaEmpleados;
    
    public EmpleadoDA()
    {
        listaEmpleados = new ArrayList<>();
    }
    
    public boolean Insertar(Empleado Entidad) throws Exception
    {
        try
        {
            if (Entidad != null)
            {
                listaEmpleados.add(Entidad);
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
            if (listaEmpleados.size()>0) 
            {
                for (Empleado item : listaEmpleados)
                {
                    if (item.getIntIdempleado() == IntIdempleado)
                    {
                        listaEmpleados.get(IntContador).setStrEstado("Inactivo");
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
    
    public boolean Actualizar(Empleado Entidad) throws Exception
    {
        try
        {
            int IntContador = 0;
            if (listaEmpleados.size()> 0)
            {
                for (Empleado item : listaEmpleados)
                {
                    if (item.getIntIdempleado() == Entidad.getIntIdempleado())
                    {
                        listaEmpleados.get(IntContador).setStrNombre(Entidad.getStrNombre());
                        listaEmpleados.get(IntContador).setStrApellido1(Entidad.getStrApellido1());
                        listaEmpleados.get(IntContador).setStrApellido2(Entidad.getStrApellido2());
                        listaEmpleados.get(IntContador).setClassFechaIngreso(Entidad.getClassFechaIngreso());
                        listaEmpleados.get(IntContador).setIntCedula(Entidad.getIntCedula());
                        listaEmpleados.get(IntContador).setIntIdempleado(Entidad.getIntIdempleado());
                        listaEmpleados.get(IntContador).setStrEstado(Entidad.getStrEstado());
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
    
    public Empleado ConsultaPorID(int IntIdempleado) throws Exception
    {
        try
        {
            if (listaEmpleados.size()> 0)
            {
                for (Empleado item : listaEmpleados)
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
    
    public ArrayList<Empleado> Listado() throws Exception
    {
        try
        {
            if (listaEmpleados.size()> 0)
            {
                return listaEmpleados;
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
