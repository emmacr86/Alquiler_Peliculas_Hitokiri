/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfazGrafica;

import CapaBL.VacacionesBL;
import Entidades.FechaIngreso;
import Entidades.Vacaciones;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractButton;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Emma
 */
public class VentanaInternaVacaciones extends javax.swing.JInternalFrame {

    VacacionesBL objVacacionesBL;
    private int row;
    int IntConsecutivo = 0;
    int ConsecutivoVacaciones = 0;

    /**
     * Creates new form VentanaInternaVacaciones
     */
    public VentanaInternaVacaciones() throws Exception {
        initComponents();
        objVacacionesBL = new VacacionesBL();
        ConsultarRegistros();

        Guardarbtn.setVisible(false);
        Ingresartbn.setVisible(false);
        Regresarbtn.setVisible(false);

        OcultarDatos();

        CantidadDiastxt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                    e.consume();
                }
            }
        });
    }

    public void ConsultarRegistros() throws Exception {

        try {
            ArrayList<Vacaciones> lista;
            lista = objVacacionesBL.Listado();

            if (lista != null) {
                String col[] = {"ID Vacaciones", "Identificador", "Fecha Solicitada", "Cantidad de dias", "Estado"};

                DefaultTableModel tableModel = new DefaultTableModel(col, 0);

                Tabla.setModel(tableModel);

                for (int i = 0; i < lista.size(); i++) {

                    int idVacaciones = lista.get(i).getIntIdVacaciones();
                    int identificador = lista.get(i).getIntIdempleado();
                    String fechaSolicitud = lista.get(i).getClassFechaSolicitud().toString();
                    int cantidadDias = lista.get(i).getIntCantidadDias();
                    String estado = lista.get(i).getStrEstado();

                    Object[] data = {idVacaciones, identificador, fechaSolicitud, cantidadDias, estado};

                    tableModel.addRow(data);
                }
            }
        } catch (Exception ex) {
            throw new Exception("Error");
        }
    }

    public void LimpiarRegistros() {
        ConsecutivoIDVacaciones.setText("");
        Consecutivolbl.setText("");
        FechaIngresosc.setDate(null);
        CantidadDiastxt.setText(String.valueOf(""));
        Estadobg.clearSelection();
    }

    public void MostrarDatos() {
        IDlbl.setVisible(true);
        identificadorlbl.setVisible(true);
        Fechalbl.setVisible(true);
        Diaslbl.setVisible(true);
        Estadolbl.setVisible(true);

        ConsecutivoIDVacaciones.setVisible(true);
        Consecutivolbl.setVisible(true);
        FechaIngresosc.setVisible(true);
        CantidadDiastxt.setVisible(true);
        Activocb.setVisible(true);
        Inactivocb.setVisible(true);
    }

    public void OcultarDatos() {
        IDlbl.setVisible(false);
        identificadorlbl.setVisible(false);
        Fechalbl.setVisible(false);
        Diaslbl.setVisible(false);
        Estadolbl.setVisible(false);

        ConsecutivoIDVacaciones.setVisible(false);
        Consecutivolbl.setVisible(false);
        FechaIngresosc.setVisible(false);
        CantidadDiastxt.setVisible(false);
        Activocb.setVisible(false);
        Inactivocb.setVisible(false);
    }

    public void RegresarMenuPrincipal() {
        Eliminarbtn.setVisible(true);
        Cambiarbtn.setVisible(true);
        Nuevobtn.setVisible(true);

        Ingresartbn.setVisible(false);
        Guardarbtn.setVisible(false);
        Regresarbtn.setVisible(false);
    }

    public void setButtonGroup(String rdValue, Enumeration elements) {
        while (elements.hasMoreElements()) {
            AbstractButton button = (AbstractButton) elements.nextElement();
            if (button.getActionCommand().equals(rdValue)) {
                button.setSelected(true);
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Estadobg = new javax.swing.ButtonGroup();
        Titulolbl = new javax.swing.JLabel();
        IDlbl = new javax.swing.JLabel();
        identificadorlbl = new javax.swing.JLabel();
        Fechalbl = new javax.swing.JLabel();
        FechaIngresosc = new com.toedter.calendar.JDateChooser();
        Diaslbl = new javax.swing.JLabel();
        CantidadDiastxt = new javax.swing.JTextField();
        Estadolbl = new javax.swing.JLabel();
        Activocb = new javax.swing.JRadioButton();
        Inactivocb = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tabla = new javax.swing.JTable();
        Ingresartbn = new javax.swing.JButton();
        Eliminarbtn = new javax.swing.JButton();
        Cambiarbtn = new javax.swing.JButton();
        Nuevobtn = new javax.swing.JButton();
        Guardarbtn = new javax.swing.JButton();
        Regresarbtn = new javax.swing.JButton();
        Consecutivolbl = new javax.swing.JLabel();
        ConsecutivoIDVacaciones = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        Titulolbl.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Titulolbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Titulolbl.setText("Mantenimiento Vacaciones");

        IDlbl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        IDlbl.setText("ID Vacaciones");

        identificadorlbl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        identificadorlbl.setText("Identificador");

        Fechalbl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Fechalbl.setText("Fecha de Solicitud");

        Diaslbl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Diaslbl.setText("Cantidad de dias");

        CantidadDiastxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                CantidadDiastxtKeyTyped(evt);
            }
        });

        Estadolbl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Estadolbl.setText("Estado");

        Estadobg.add(Activocb);
        Activocb.setText("Activo");

        Estadobg.add(Inactivocb);
        Inactivocb.setText("Inactivo");

        Tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID Vacaciones", "Identificador", "Fecha de Solicitud", "Cantidad dias", "Estado"
            }
        ));
        Tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(Tabla);

        Ingresartbn.setText("Ingresar");
        Ingresartbn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                IngresartbnMouseClicked(evt);
            }
        });

        Eliminarbtn.setText("Inactivar");
        Eliminarbtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                EliminarbtnMouseClicked(evt);
            }
        });

        Cambiarbtn.setText("Actualizar");
        Cambiarbtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CambiarbtnMouseClicked(evt);
            }
        });

        Nuevobtn.setText("Nuevo");
        Nuevobtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                NuevobtnMouseClicked(evt);
            }
        });

        Guardarbtn.setText("Guardar");
        Guardarbtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                GuardarbtnMouseClicked(evt);
            }
        });

        Regresarbtn.setText("Regresar");
        Regresarbtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RegresarbtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Diaslbl, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(CantidadDiastxt, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Fechalbl, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(FechaIngresosc, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(IDlbl, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ConsecutivoIDVacaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(identificadorlbl, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Consecutivolbl, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Titulolbl, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Estadolbl, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Activocb, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Inactivocb)
                        .addContainerGap(451, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Nuevobtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Ingresartbn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Eliminarbtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Cambiarbtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Guardarbtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Regresarbtn)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Titulolbl)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(IDlbl)
                    .addComponent(ConsecutivoIDVacaciones))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(identificadorlbl)
                    .addComponent(Consecutivolbl))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Fechalbl, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FechaIngresosc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Diaslbl)
                    .addComponent(CantidadDiastxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Inactivocb)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Activocb, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Estadolbl)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Cambiarbtn)
                    .addComponent(Eliminarbtn)
                    .addComponent(Nuevobtn)
                    .addComponent(Ingresartbn)
                    .addComponent(Guardarbtn)
                    .addComponent(Regresarbtn))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CantidadDiastxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CantidadDiastxtKeyTyped
        char c = evt.getKeyChar();
        if (Character.isLetter(c) && !evt.isAltDown()) {
            evt.consume();
        }
        if (CantidadDiastxt.getText().length() >= 9) {
            evt.consume();
        }
    }//GEN-LAST:event_CantidadDiastxtKeyTyped

    private void IngresartbnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_IngresartbnMouseClicked
        try {
            Vacaciones objVacaciones = new Vacaciones();
            FechaIngreso objFechaIngreso = null;
            int consecutivo = objVacacionesBL.Consecutivo() + 1;

            if (FechaIngresosc.getCalendar() != null) {

                int dia = FechaIngresosc.getCalendar().get(Calendar.DAY_OF_MONTH);
                int mes = FechaIngresosc.getCalendar().get(Calendar.MONTH) + 1;
                int anno = FechaIngresosc.getCalendar().get(Calendar.YEAR);

                objFechaIngreso = new FechaIngreso(dia, mes, anno);

                if (objFechaIngreso != null) {

                    objVacaciones.setIntIdVacaciones(Integer.parseInt(ConsecutivoIDVacaciones.getText()));
                    objVacaciones.setIntIdempleado(Integer.parseInt(Consecutivolbl.getText()));
                    objVacaciones.setClassFechaSolicitud(objFechaIngreso);

                    if (CantidadDiastxt.getText().equals("")) {
                        JOptionPane.showMessageDialog(this, "Espacio de cantidad de días vacío", "Error", 2);
                    } else {
                        objVacaciones.setIntCantidadDias(Integer.parseInt(CantidadDiastxt.getText()));
                        Activocb.setActionCommand(Activocb.getText());
                        Inactivocb.setActionCommand(Inactivocb.getText());
                        if (Activocb.isSelected() == false & Inactivocb.isSelected() == false) {
                            JOptionPane.showMessageDialog(this, "Seleccion de estado vacío", "Error", 2);
                        } else {
                            objVacaciones.setStrEstado(Estadobg.getSelection().getActionCommand());
                            try {
                                objVacacionesBL.Insertar(objVacaciones);
                                objVacacionesBL.grabarArchivoVacaciones();
                                ConsultarRegistros();
                                LimpiarRegistros();
                                RegresarMenuPrincipal();
                                OcultarDatos();
                            } catch (Exception ex) {
                                Logger.getLogger(VentanaInternaUsuarios.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Espacio de fecha vacío", "Error", 2);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Espacio de fecha vacío", "Error", 2);
            }
        } catch (Exception ex) {
            Logger.getLogger(VentanaInternaVacaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_IngresartbnMouseClicked

    private void TablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaMouseClicked
        row = Tabla.rowAtPoint(evt.getPoint());
    }//GEN-LAST:event_TablaMouseClicked

    private void EliminarbtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EliminarbtnMouseClicked
        try {
            int identificador = objVacacionesBL.Listado().get(row).getIntIdempleado();
            int IDvacaciones = objVacacionesBL.Listado().get(row).getIntIdVacaciones();

            VentanaInternaVacaciones objVenatanaInternaVacaciones = new VentanaInternaVacaciones();

            if ("Inactivo".equals(objVacacionesBL.Listado().get(row).getStrEstado())) {
                JOptionPane.showMessageDialog(this, "Vacaciones inactivas anteriormente", "Mensaje", 2);
            } else {

                String botones[] = {"Sí", "No"};
                int eleccion = JOptionPane.showOptionDialog(objVenatanaInternaVacaciones, "¿Desea inactivar las vacaciones ID: " + IDvacaciones + "?", "Inactivar vacaciones", 0, 0, null, botones, this);
                if (eleccion == JOptionPane.YES_OPTION) {

                    objVacacionesBL.Eliminar(identificador);
                    objVacacionesBL.grabarArchivoVacaciones();
                    ConsultarRegistros();

                } else {

                    if (eleccion == JOptionPane.NO_OPTION) {
                        System.out.println("Opcion Inactivo cancelado");
                    }
                }
            }

        } catch (Exception ex) {
            Logger.getLogger(VentanaInternaClientes.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_EliminarbtnMouseClicked

    private void NuevobtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NuevobtnMouseClicked
        try {
            Regresarbtn.setVisible(true);
            Ingresartbn.setVisible(true);
            Nuevobtn.setVisible(false);
            Cambiarbtn.setVisible(false);
            Eliminarbtn.setVisible(false);

            int consecutivo = objVacacionesBL.Consecutivo() + 1;
            Consecutivolbl.setText(String.valueOf(consecutivo));

            int consecutivoVacaciones = objVacacionesBL.ConsecutivoIDVacaciones() + 1;
            ConsecutivoIDVacaciones.setText(String.valueOf(consecutivoVacaciones));

            MostrarDatos();
        } catch (Exception ex) {
            Logger.getLogger(VentanaInternaVacaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_NuevobtnMouseClicked

    private void RegresarbtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RegresarbtnMouseClicked
        LimpiarRegistros();

        Eliminarbtn.setVisible(true);
        Cambiarbtn.setVisible(true);
        Nuevobtn.setVisible(true);

        Ingresartbn.setVisible(false);
        Guardarbtn.setVisible(false);
        Regresarbtn.setVisible(false);

        OcultarDatos();
    }//GEN-LAST:event_RegresarbtnMouseClicked

    private void CambiarbtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CambiarbtnMouseClicked
        try {
            Guardarbtn.setVisible(true);
            Ingresartbn.setVisible(false);
            Eliminarbtn.setVisible(false);
            Cambiarbtn.setVisible(false);
            Regresarbtn.setVisible(true);
            Nuevobtn.setVisible(false);

            MostrarDatos();
            Vacaciones objVacaciones = new Vacaciones();

            int identificador = objVacacionesBL.Listado().get(row).getIntIdempleado();

            ConsecutivoIDVacaciones.setText(String.valueOf(objVacacionesBL.ConsultaPorID(identificador).getIntIdVacaciones()));
            Consecutivolbl.setText(String.valueOf(objVacacionesBL.ConsultaPorID(identificador).getIntIdempleado()));

            String fechaIngreso = objVacacionesBL.ConsultaPorID(identificador).getClassFechaSolicitud().toString();
            int dia = objVacacionesBL.ConsultaPorID(identificador).getClassFechaSolicitud().getIntDia();
            int mes = objVacacionesBL.ConsultaPorID(identificador).getClassFechaSolicitud().getIntMes() - 1;
            int anno = objVacacionesBL.ConsultaPorID(identificador).getClassFechaSolicitud().getIntAno();

            Calendar objCalendar = Calendar.getInstance();
            objCalendar.set(anno, mes, dia);
            FechaIngresosc.setCalendar(objCalendar);

            CantidadDiastxt.setText(String.valueOf(objVacacionesBL.ConsultaPorID(identificador).getIntCantidadDias()));

            if ("Activo".equals(objVacacionesBL.ConsultaPorID(identificador).getStrEstado())) {
                setButtonGroup("Activo", Estadobg.getElements());
            } else {
                setButtonGroup("Inactivo", Estadobg.getElements());
            }

            ConsultarRegistros();
        } catch (Exception ex) {
            Logger.getLogger(VentanaInternaEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_CambiarbtnMouseClicked

    private void GuardarbtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GuardarbtnMouseClicked

        Vacaciones objVacaciones = new Vacaciones();
        FechaIngreso objFechaIngreso = null;

        if (FechaIngresosc.getCalendar() != null) {

            int dia = FechaIngresosc.getCalendar().get(Calendar.DAY_OF_MONTH);
            int mes = FechaIngresosc.getCalendar().get(Calendar.MONTH) + 1;
            int anno = FechaIngresosc.getCalendar().get(Calendar.YEAR);

            objFechaIngreso = new FechaIngreso(dia, mes, anno);

            if (objFechaIngreso != null) {

                objVacaciones.setIntIdVacaciones(Integer.parseInt(ConsecutivoIDVacaciones.getText()));
                objVacaciones.setIntIdempleado(Integer.parseInt(Consecutivolbl.getText()));
                objVacaciones.setClassFechaSolicitud(objFechaIngreso);
                Activocb.setActionCommand(Activocb.getText());
                Inactivocb.setActionCommand(Inactivocb.getText());
                objVacaciones.setStrEstado(Estadobg.getSelection().getActionCommand());

                if (CantidadDiastxt.getText().equals("")) {
                    JOptionPane.showMessageDialog(this, "Espacio de cantidad de días vacío", "Error", 2);
                } else {
                    objVacaciones.setIntCantidadDias(Integer.parseInt(CantidadDiastxt.getText()));
                    try {
                        objVacacionesBL.Actualizar(objVacaciones);
                        objVacacionesBL.grabarArchivoVacaciones();
                        ConsultarRegistros();
                        LimpiarRegistros();
                        RegresarMenuPrincipal();
                        OcultarDatos();
                    } catch (Exception ex) {
                        Logger.getLogger(VentanaInternaEmpleados.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Espacio de fecha vacío", "Error", 2);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Espacio de fecha vacío", "Error", 2);
        }
    }//GEN-LAST:event_GuardarbtnMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton Activocb;
    private javax.swing.JButton Cambiarbtn;
    private javax.swing.JTextField CantidadDiastxt;
    private javax.swing.JLabel ConsecutivoIDVacaciones;
    private javax.swing.JLabel Consecutivolbl;
    private javax.swing.JLabel Diaslbl;
    private javax.swing.JButton Eliminarbtn;
    private javax.swing.ButtonGroup Estadobg;
    private javax.swing.JLabel Estadolbl;
    private com.toedter.calendar.JDateChooser FechaIngresosc;
    private javax.swing.JLabel Fechalbl;
    private javax.swing.JButton Guardarbtn;
    private javax.swing.JLabel IDlbl;
    private javax.swing.JRadioButton Inactivocb;
    private javax.swing.JButton Ingresartbn;
    private javax.swing.JButton Nuevobtn;
    private javax.swing.JButton Regresarbtn;
    private javax.swing.JTable Tabla;
    private javax.swing.JLabel Titulolbl;
    private javax.swing.JLabel identificadorlbl;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
