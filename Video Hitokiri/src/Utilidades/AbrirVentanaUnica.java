/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidades;

import java.beans.PropertyVetoException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

/**
 *
 * @author Emma
 */
public class AbrirVentanaUnica {

    JDesktopPane jDesktopPane1;

    public AbrirVentanaUnica(JDesktopPane jDesktopPane1) {
        this.jDesktopPane1 = jDesktopPane1;
    }

    public void VentanaActiva(JInternalFrame frame) {
        for (JInternalFrame internal : jDesktopPane1.getAllFrames()) {
            if (internal.getClass().toString().equalsIgnoreCase(frame.getClass().toString())) {
                return;
            } else {
                jDesktopPane1.remove(internal);
                jDesktopPane1.repaint();
            }
        }
        jDesktopPane1.add(frame);
        frame.setVisible(true);
        try {
            frame.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(AbrirVentanaUnica.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
