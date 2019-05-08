/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidades;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Emma
 */
public class FechaHora {

    public String Hora() {
        Date fecha = new Date();
        DateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
        String hora = formatoHora.format(fecha);
        return hora;
    }

    public String Fecha() {
        Date fecha = new Date();
        DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        String fechaActual = formatoFecha.format(fecha);
        return fechaActual;
    }

    public String HH() {
        Date fecha = new Date();
        DateFormat formatoHora = new SimpleDateFormat("HH");
        String hora = formatoHora.format(fecha);
        return hora;
    }

    public String MM() {
        Date fecha = new Date();
        DateFormat formatoHora = new SimpleDateFormat("mm");
        String minutos = formatoHora.format(fecha);
        return minutos;
    }

    public String SS() {
        Date fecha = new Date();
        DateFormat formatoHora = new SimpleDateFormat("ss");
        String segundos = formatoHora.format(fecha);
        return segundos;
    }

}
