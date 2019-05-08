/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaDA;

import Entidades.Peliculas;
import java.util.ArrayList;

/**
 *
 * @author chaconqu
 */
public class PeliculasDA {
    
    private ArrayList<Peliculas> listaPeliculas;
    
    public PeliculasDA()
    {
        listaPeliculas = new ArrayList<>();
    }
    
    public boolean Insertar(Peliculas Entidad) throws Exception
    {
        try
        {
            if (Entidad != null)
            {
                listaPeliculas.add(Entidad);
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
    
    public boolean Eliminar (int IntIdPelicula) throws Exception
    {
        try
        {
            int IntContador = 0;
            if (listaPeliculas.size()>0) 
            {
                for (Peliculas item : listaPeliculas)
                {
                    if (item.getIntIdPelicula() == IntIdPelicula)
                    {
                        listaPeliculas.get(IntContador).setStrEstado("Inactivo");
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
    
    public boolean Actualizar(Peliculas Entidad) throws Exception
    {
        try
        {
            int IntContador = 0;
            if (listaPeliculas.size()> 0)
            {
                for (Peliculas item : listaPeliculas)
                {
                    if (item.getIntIdPelicula() == Entidad.getIntIdPelicula())
                    {
                        listaPeliculas.get(IntContador).setIntIdPelicula(Entidad.getIntIdPelicula());
                        listaPeliculas.get(IntContador).setStrDescripcion(Entidad.getStrDescripcion());
                        listaPeliculas.get(IntContador).setStrGenero(Entidad.getStrGenero());
                        listaPeliculas.get(IntContador).setIntCantidadPeliculas(Entidad.getIntCantidadPeliculas());
                        listaPeliculas.get(IntContador).setStrEstado(Entidad.getStrEstado());
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
    
    public Peliculas ConsultaPorID(int IntIdPelicula) throws Exception
    {
        try
        {
            if (listaPeliculas.size()> 0)
            {
                for (Peliculas item : listaPeliculas)
                {
                    if (item.getIntIdPelicula() == IntIdPelicula)
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
    
    public ArrayList<Peliculas> Listado() throws Exception
    {
        try
        {
            if (listaPeliculas.size()> 0)
            {
                return listaPeliculas;
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
