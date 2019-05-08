/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfazGrafica; 

import CapaBL.EmpleadoBL;
import Entidades.FechaIngreso;
import Entidades.Empleado;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractButton;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Emma
 */
public class VentanaInternaEmpleados extends javax.swing.JInternalFrame {

    private TableRowSorter trsFiltro;
    EmpleadoBL objEmpleadoBL;
    private int row;
    int IntConsecutivo = 0;

    /**
     * Creates new form VentanaInternaEmpleados
     */
    public VentanaInternaEmpleados() throws Exception {
        initComponents();
        objEmpleadoBL = new EmpleadoBL();
        ConsultarRegistros();

        Guardarbtn.setVisible(false);
        Ingresartbn.setVisible(false);
        Regresarbtn.setVisible(false);
        Filtradobx.setVisible(false);
        Busquedatxt.setVisible(false);

        OcultarDatos();

        Cedulatxt.addKeyListener(new KeyAdapter() {
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
        Tabla.setRowSorter(null);
        try {

            ArrayList<Empleado> lista;
            lista = objEmpleadoBL.Listado();

            if (lista != null) {
                String col[] = {"Nombre", "Primer Apellido", "Segundo Apellido", "Cédula", "Identificador", "Fecha de Ingreso", "Estado"};

                DefaultTableModel tableModel = new DefaultTableModel(col, 0);

                Tabla.setModel(tableModel);

                for (int i = 0; i < lista.size(); i++) {

                    String nombre = lista.get(i).getStrNombre();
                    String apellido1 = lista.get(i).getStrApellido1();
                    String apellido2 = lista.get(i).getStrApellido2();
                    int cedula = lista.get(i).getIntCedula();
                    int identificador = lista.get(i).getIntIdempleado();
                    String fechaIngreso = lista.get(i).getClassFechaIngreso().toString();
                    String estado = lista.get(i).getStrEstado();

                    Object[] data = {nombre, apellido1, apellido2, cedula, identificador, fechaIngreso, estado};

                    tableModel.addRow(data);
                }
            }
        } catch (Exception ex) {
            throw new Exception("Error");
        }
    }

    public void LimpiarRegistros() {
        Nombretxt.setText("");
        Apellido1txt.setText("");
        Apellido2txt.setText("");
        Cedulatxt.setText(String.valueOf(""));
        Consecutivolbl.setText("");
        FechaIngresosc.setDate(null);
        Estadogb.clearSelection();
        Busquedatxt.setText("");
    }

    public void MostrarDatos() {
        Nombrelbl.setVisible(true);
        Apellidolbl.setVisible(true);
        Apellido2lbl.setVisible(true);
        Cedulalbl.setVisible(true);
        Identificadorlbl.setVisible(true);
        Fechalbl.setVisible(true);
        Estadolbl.setVisible(true);

        Nombretxt.setVisible(true);
        Apellido1txt.setVisible(true);
        Apellido2txt.setVisible(true);
        Cedulatxt.setVisible(true);
        Consecutivolbl.setVisible(true);
        FechaIngresosc.setVisible(true);
        Activocb.setVisible(true);
        Inactivocb.setVisible(true);
    }

    public void OcultarDatos() {
        Nombrelbl.setVisible(false);
        Apellidolbl.setVisible(false);
        Apellido2lbl.setVisible(false);
        Cedulalbl.setVisible(false);
        Identificadorlbl.setVisible(false);
        Fechalbl.setVisible(false);
        Estadolbl.setVisible(false);

        Nombretxt.setVisible(false);
        Apellido1txt.setVisible(false);
        Apellido2txt.setVisible(false);
        Cedulatxt.setVisible(false);
        Consecutivolbl.setVisible(false);
        FechaIngresosc.setVisible(false);
        Activocb.setVisible(false);
        Inactivocb.setVisible(false);
    }

    public void RegresarMenuPrincipal() throws Exception {
        Eliminarbtn.setVisible(true);
        Cambiarbtn.setVisible(true);
        Nuevobtn.setVisible(true);
        Buscarbtn.setVisible(true);
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

    public void filtro() {
        int columnaABuscar = 0;
        if (Filtradobx.getSelectedItem() == "Nombre") {
            columnaABuscar = 0;
        }
        if (Filtradobx.getSelectedItem() == "Apellido1") {
            columnaABuscar = 1;
        }
        if (Filtradobx.getSelectedItem() == "Apellido2") {
            columnaABuscar = 2;
        }
        if (Filtradobx.getSelectedItem().toString() == "Cédula") {
            columnaABuscar = 3;
        }
        if (Filtradobx.getSelectedItem() == "Estado") {
            columnaABuscar = 6;
        }
        trsFiltro.setRowFilter(RowFilter.regexFilter(Busquedatxt.getText(), columnaABuscar));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Estadogb = new javax.swing.ButtonGroup();
        Titulolbl = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tabla = new javax.swing.JTable();
        Nombrelbl = new javax.swing.JLabel();
        Nombretxt = new javax.swing.JTextField();
        Apellidolbl = new javax.swing.JLabel();
        Apellido1txt = new javax.swing.JTextField();
        Apellido2lbl = new javax.swing.JLabel();
        Apellido2txt = new javax.swing.JTextField();
        Cedulalbl = new javax.swing.JLabel();
        Cedulatxt = new javax.swing.JTextField();
        Identificadorlbl = new javax.swing.JLabel();
        Fechalbl = new javax.swing.JLabel();
        FechaIngresosc = new com.toedter.calendar.JDateChooser();
        Estadolbl = new javax.swing.JLabel();
        Activocb = new javax.swing.JRadioButton();
        Inactivocb = new javax.swing.JRadioButton();
        Ingresartbn = new javax.swing.JButton();
        Eliminarbtn = new javax.swing.JButton();
        Cambiarbtn = new javax.swing.JButton();
        Guardarbtn = new javax.swing.JButton();
        Regresarbtn = new javax.swing.JButton();
        Nuevobtn = new javax.swing.JButton();
        Buscarbtn = new javax.swing.JButton();
        Busquedatxt = new javax.swing.JTextField();
        Filtradobx = new javax.swing.JComboBox<>();
        Consecutivolbl = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setMinimumSize(new java.awt.Dimension(640, 480));

        Titulolbl.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Titulolbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Titulolbl.setText("Mantenimiento Empleados");

        Tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Nombre", "Apellido 1", "Apellido 2", "Cédula", "Identificador", "Ingreso", "Estado"
            }
        ));
        Tabla.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        Tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(Tabla);

        Nombrelbl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Nombrelbl.setText("Nombre");

        Apellidolbl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Apellidolbl.setText("Primer Apelido");

        Apellido2lbl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Apellido2lbl.setText("Segundo Apellido");

        Cedulalbl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Cedulalbl.setText("Cédula");

        Cedulatxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                CedulatxtKeyTyped(evt);
            }
        });

        Identificadorlbl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Identificadorlbl.setText("Identificador");

        Fechalbl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Fechalbl.setText("Fecha de Ingreso");

        Estadolbl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Estadolbl.setText("Estado");

        Estadogb.add(Activocb);
        Activocb.setText("Activo");

        Estadogb.add(Inactivocb);
        Inactivocb.setText("Inactivo");

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

        Nuevobtn.setText("Nuevo");
        Nuevobtn.setMaximumSize(new java.awt.Dimension(73, 23));
        Nuevobtn.setMinimumSize(new java.awt.Dimension(73, 23));
        Nuevobtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                NuevobtnMouseClicked(evt);
            }
        });

        Buscarbtn.setText("Buscar");
        Buscarbtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BuscarbtnMouseClicked(evt);
            }
        });

        Busquedatxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                BusquedatxtKeyTyped(evt);
            }
        });

        Filtradobx.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nombre", "Apellido1", "Apellido2", "Cédula", "Estado" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Titulolbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(Apellido2lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(Apellido2txt, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(Estadolbl, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(Identificadorlbl, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(Activocb, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(Inactivocb))
                                .addComponent(Consecutivolbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Apellidolbl, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Apellido1txt, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Nombrelbl, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Nombretxt, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(Cedulalbl, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(Cedulatxt, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(Fechalbl, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(FechaIngresosc, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Nuevobtn, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Ingresartbn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Eliminarbtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Cambiarbtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Guardarbtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Regresarbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Buscarbtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Filtradobx, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Busquedatxt, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Titulolbl)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Nombrelbl)
                    .addComponent(Nombretxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Apellidolbl)
                    .addComponent(Apellido1txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Apellido2lbl)
                    .addComponent(Apellido2txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Cedulalbl)
                    .addComponent(Cedulatxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Identificadorlbl)
                    .addComponent(Consecutivolbl))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Fechalbl, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FechaIngresosc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Estadolbl)
                        .addComponent(Inactivocb))
                    .addComponent(Activocb, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Nuevobtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Ingresartbn)
                    .addComponent(Eliminarbtn)
                    .addComponent(Cambiarbtn)
                    .addComponent(Guardarbtn)
                    .addComponent(Regresarbtn)
                    .addComponent(Buscarbtn)
                    .addComponent(Busquedatxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Filtradobx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CedulatxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CedulatxtKeyTyped
        Cedulatxt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                    e.consume();  // ignore event
                }
            }
        });
        if (Cedulatxt.getText().length() >= 9) {
            evt.consume();
        }
    }//GEN-LAST:event_CedulatxtKeyTyped

    private void IngresartbnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_IngresartbnMouseClicked
        try {
            int consecutivo = objEmpleadoBL.Consecutivo() + 1;
            Empleado objEmpleado = new Empleado();
            FechaIngreso objFechaIngreso = null;

            if (FechaIngresosc.getCalendar() != null) {

                int dia = FechaIngresosc.getCalendar().get(Calendar.DAY_OF_MONTH);
                int mes = FechaIngresosc.getCalendar().get(Calendar.MONTH) + 1;
                int anno = FechaIngresosc.getCalendar().get(Calendar.YEAR);

                objFechaIngreso = new FechaIngreso(dia, mes, anno);

                if (objFechaIngreso != null) {

                    objEmpleado.setClassFechaIngreso(objFechaIngreso);
                    objEmpleado.setIntIdempleado(Integer.parseInt(Consecutivolbl.getText()));

                    if (Nombretxt.getText().equals("")) {
                        JOptionPane.showMessageDialog(this, "Espacio de Nombre vacío", "Error", 2);
                    } else {
                        objEmpleado.setStrNombre(Nombretxt.getText());
                        if (Apellido1txt.getText().equals("")) {
                            JOptionPane.showMessageDialog(this, "Espacio de primer apellido vacío", "Error", 2);
                        } else {
                            objEmpleado.setStrApellido1(Apellido1txt.getText());
                            if (Apellido2txt.getText().equals("")) {
                                JOptionPane.showMessageDialog(this, "Espacio de segundo apellido vacío", "Error", 2);
                            } else {
                                objEmpleado.setStrApellido2(Apellido2txt.getText());
                                if (Cedulatxt.getText().equals("")) {
                                    JOptionPane.showMessageDialog(this, "Espacio de cedula vacío", "Error", 2);
                                } else {
                                    objEmpleado.setIntCedula(Integer.parseInt(Cedulatxt.getText()));
                                    Activocb.setActionCommand(Activocb.getText());
                                    Inactivocb.setActionCommand(Inactivocb.getText());
                                    if (Activocb.isSelected() == false & Inactivocb.isSelected() == false) {
                                        JOptionPane.showMessageDialog(this, "Seleccion de estado vacío", "Error", 2);
                                    } else {
                                        objEmpleado.setStrEstado(Estadogb.getSelection().getActionCommand());
                                        try {
                                            objEmpleadoBL.Insertar(objEmpleado);
                                            objEmpleadoBL.grabarArchivoEmpleado();
                                            ConsultarRegistros();
                                            RegresarMenuPrincipal();
                                            OcultarDatos();
                                            LimpiarRegistros();
                                        } catch (Exception ex) {
                                            Logger.getLogger(VentanaInternaUsuarios.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    }
                                }
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
            Logger.getLogger(VentanaInternaEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_IngresartbnMouseClicked

    private void TablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaMouseClicked
        row = Tabla.rowAtPoint(evt.getPoint());
    }//GEN-LAST:event_TablaMouseClicked

    private void EliminarbtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EliminarbtnMouseClicked
        try {
            int identificador = objEmpleadoBL.Listado().get(row).getIntIdempleado();
            String nombre = objEmpleadoBL.Listado().get(row).getStrNombre();
            String apellido1 = objEmpleadoBL.Listado().get(row).getStrApellido1();
            String apellido2 = objEmpleadoBL.Listado().get(row).getStrApellido2();

            VentanaInternaEmpleados objVentanaInternaEmpleados = new VentanaInternaEmpleados();

            if ("Inactivo".equals(objEmpleadoBL.Listado().get(row).getStrEstado())) {
                JOptionPane.showMessageDialog(this, "Empleado inactivo anteriormente", "Mensaje", 2);
            } else {

                String botones[] = {"Sí", "No"};
                int eleccion = JOptionPane.showOptionDialog(objVentanaInternaEmpleados, "¿Desea inactivar al empleado " + nombre + " " + apellido1 + " " + apellido2 + "?", "Inactivar empleado", 0, 0, null, botones, this);
                if (eleccion == JOptionPane.YES_OPTION) {

                    objEmpleadoBL.Eliminar(identificador);
                    objEmpleadoBL.grabarArchivoEmpleado();
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

    private void CambiarbtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CambiarbtnMouseClicked
        try {
            Guardarbtn.setVisible(true);
            Ingresartbn.setVisible(false);
            Eliminarbtn.setVisible(false);
            Cambiarbtn.setVisible(false);
            Regresarbtn.setVisible(true);
            Nuevobtn.setVisible(false);
            Buscarbtn.setVisible(false);

            MostrarDatos();
            Empleado objEmpleado = new Empleado();

            int identificador = objEmpleadoBL.Listado().get(row).getIntIdempleado();

            Nombretxt.setText(objEmpleadoBL.ConsultaPorID(identificador).getStrNombre());
            Apellido1txt.setText(objEmpleadoBL.ConsultaPorID(identificador).getStrApellido1());
            Apellido2txt.setText(objEmpleadoBL.ConsultaPorID(identificador).getStrApellido2());
            Cedulatxt.setText(String.valueOf(objEmpleadoBL.ConsultaPorID(identificador).getIntCedula()));
            Consecutivolbl.setText(String.valueOf(objEmpleadoBL.ConsultaPorID(identificador).getIntIdempleado()));

            String fechaIngreso = objEmpleadoBL.ConsultaPorID(identificador).getClassFechaIngreso().toString();
            int dia = objEmpleadoBL.ConsultaPorID(identificador).getClassFechaIngreso().getIntDia();
            int mes = objEmpleadoBL.ConsultaPorID(identificador).getClassFechaIngreso().getIntMes() - 1;
            int anno = objEmpleadoBL.ConsultaPorID(identificador).getClassFechaIngreso().getIntAno();

            Calendar objCalendar = Calendar.getInstance();
            objCalendar.set(anno, mes, dia);
            FechaIngresosc.setCalendar(objCalendar);

            if ("Activo".equals(objEmpleadoBL.ConsultaPorID(identificador).getStrEstado())) {
                setButtonGroup("Activo", Estadogb.getElements());
            } else {
                setButtonGroup("Inactivo", Estadogb.getElements());
            }

            ConsultarRegistros();
        } catch (Exception ex) {
            Logger.getLogger(VentanaInternaEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_CambiarbtnMouseClicked

    private void RegresarbtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RegresarbtnMouseClicked
        LimpiarRegistros();

        Eliminarbtn.setVisible(true);
        Cambiarbtn.setVisible(true);
        Nuevobtn.setVisible(true);
        Buscarbtn.setVisible(true);
        Busquedatxt.setVisible(false);
        Ingresartbn.setVisible(false);
        Guardarbtn.setVisible(false);
        Regresarbtn.setVisible(false);
        Filtradobx.setVisible(false);
        Filtradobx.setSelectedIndex(0);

        try {
            ConsultarRegistros();
        } catch (Exception ex) {
            Logger.getLogger(VentanaInternaEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        }
        OcultarDatos();
    }//GEN-LAST:event_RegresarbtnMouseClicked

    private void NuevobtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NuevobtnMouseClicked
        try {
            Regresarbtn.setVisible(true);
            Ingresartbn.setVisible(true);

            Buscarbtn.setVisible(false);
            Eliminarbtn.setVisible(false);
            Cambiarbtn.setVisible(false);
            Nuevobtn.setVisible(false);

            int consecutivo = objEmpleadoBL.Consecutivo() + 1;
            Consecutivolbl.setText(String.valueOf(consecutivo));

            MostrarDatos();
        } catch (Exception ex) {
            Logger.getLogger(VentanaInternaEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_NuevobtnMouseClicked

    private void GuardarbtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GuardarbtnMouseClicked

        Empleado objEmpleado = new Empleado();
        FechaIngreso objFechaIngreso = null;

        if (FechaIngresosc.getCalendar() != null) {

            int dia = FechaIngresosc.getCalendar().get(Calendar.DAY_OF_MONTH);
            int mes = FechaIngresosc.getCalendar().get(Calendar.MONTH) + 1;
            int anno = FechaIngresosc.getCalendar().get(Calendar.YEAR);

            objFechaIngreso = new FechaIngreso(dia, mes, anno);

            if (objFechaIngreso != null) {

                objEmpleado.setClassFechaIngreso(objFechaIngreso);
                objEmpleado.setIntIdempleado(Integer.parseInt(Consecutivolbl.getText()));
                Activocb.setActionCommand(Activocb.getText());
                Inactivocb.setActionCommand(Inactivocb.getText());
                objEmpleado.setStrEstado(Estadogb.getSelection().getActionCommand());

                if (Nombretxt.getText().equals("")) {
                    JOptionPane.showMessageDialog(this, "Espacio de Nombre vacío", "Error", 2);
                } else {
                    objEmpleado.setStrNombre(Nombretxt.getText());
                    if (Apellido1txt.getText().equals("")) {
                        JOptionPane.showMessageDialog(this, "Espacio de primer apellido vacío", "Error", 2);
                    } else {
                        objEmpleado.setStrApellido1(Apellido1txt.getText());
                        if (Apellido2txt.getText().equals("")) {
                            JOptionPane.showMessageDialog(this, "Espacio de segundo apellido vacío", "Error", 2);
                        } else {
                            objEmpleado.setStrApellido2(Apellido2txt.getText());
                            if (Cedulatxt.getText().equals("")) {
                                JOptionPane.showMessageDialog(this, "Espacio de cedula vacío", "Error", 2);
                            } else {
                                objEmpleado.setIntCedula(Integer.parseInt(Cedulatxt.getText()));
                                try {

                                    objEmpleadoBL.Actualizar(objEmpleado);
                                    objEmpleadoBL.grabarArchivoEmpleado();
                                    ConsultarRegistros();
                                    LimpiarRegistros();
                                    RegresarMenuPrincipal();
                                    OcultarDatos();

                                } catch (Exception ex) {
                                    Logger.getLogger(VentanaInternaEmpleados.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        }
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Espacio de fecha vacío", "Error", 2);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Espacio de fecha vacío", "Error", 2);
        }
    }//GEN-LAST:event_GuardarbtnMouseClicked

    private void BuscarbtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BuscarbtnMouseClicked
        OcultarDatos();
        Regresarbtn.setVisible(true);
        Busquedatxt.setVisible(true);
        Filtradobx.setVisible(true);

        Buscarbtn.setVisible(false);
        Ingresartbn.setVisible(false);
        Eliminarbtn.setVisible(false);
        Cambiarbtn.setVisible(false);
        Nuevobtn.setVisible(false);
        try {
            ConsultarRegistros();
        } catch (Exception ex) {
            Logger.getLogger(VentanaInternaEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BuscarbtnMouseClicked

    private void BusquedatxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BusquedatxtKeyTyped
        Busquedatxt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(final KeyEvent e) {
                String cadena = (Busquedatxt.getText());
                Busquedatxt.setText(cadena);
                repaint();
                filtro();
            }
        });
        trsFiltro = new TableRowSorter(Tabla.getModel());
        Tabla.setRowSorter(trsFiltro);
    }//GEN-LAST:event_BusquedatxtKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton Activocb;
    private javax.swing.JTextField Apellido1txt;
    private javax.swing.JLabel Apellido2lbl;
    private javax.swing.JTextField Apellido2txt;
    private javax.swing.JLabel Apellidolbl;
    private javax.swing.JButton Buscarbtn;
    private javax.swing.JTextField Busquedatxt;
    private javax.swing.JButton Cambiarbtn;
    private javax.swing.JLabel Cedulalbl;
    private javax.swing.JTextField Cedulatxt;
    private javax.swing.JLabel Consecutivolbl;
    private javax.swing.JButton Eliminarbtn;
    private javax.swing.ButtonGroup Estadogb;
    private javax.swing.JLabel Estadolbl;
    private com.toedter.calendar.JDateChooser FechaIngresosc;
    private javax.swing.JLabel Fechalbl;
    private javax.swing.JComboBox<String> Filtradobx;
    private javax.swing.JButton Guardarbtn;
    private javax.swing.JLabel Identificadorlbl;
    private javax.swing.JRadioButton Inactivocb;
    private javax.swing.JButton Ingresartbn;
    private javax.swing.JLabel Nombrelbl;
    private javax.swing.JTextField Nombretxt;
    private javax.swing.JButton Nuevobtn;
    private javax.swing.JButton Regresarbtn;
    private javax.swing.JTable Tabla;
    private javax.swing.JLabel Titulolbl;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
