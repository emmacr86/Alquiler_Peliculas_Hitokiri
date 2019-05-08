/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidades;

import InterfazGrafica.VentanaMenuPrincipal;
import javax.swing.JOptionPane;

/**
 *
 * @author Emma
 */
public class ConfirmacionCerrarVentanas {

    public void CerrarVentana() {
        VentanaMenuPrincipal cerrarVentanaMenuPrincipal = new VentanaMenuPrincipal();
        String botones[] = {"Si", "No"};
        int eleccion = JOptionPane.showOptionDialog(cerrarVentanaMenuPrincipal, "¿Desea salir de la aplicación?", "Salida del sistema", 0, 0, null, botones, this);
        if (eleccion == JOptionPane.YES_OPTION) {
            System.exit(0);
        } else {
            if (eleccion == JOptionPane.NO_OPTION) {
                System.out.println("Cierre cancelado");
            }
        }
    }
}
