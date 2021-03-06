/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfazGrafica;

import CapaBL.PeliculasBL;
import Entidades.Peliculas;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
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
public class VentanaInternaInventarioPeliculas extends javax.swing.JInternalFrame {

    PeliculasBL objPeliculasBL;
    private int row;
    int IntConsecutivo = 0;

    /**
     * Creates new form VentanaInternaInventarioPeliculas
     */
    public VentanaInternaInventarioPeliculas() throws Exception {
        initComponents();
        objPeliculasBL = new PeliculasBL();
        ConsultarRegistros();

        Guardarbtn.setVisible(false);
        Ingresartbn.setVisible(false);
        Regresarbtn.setVisible(false);

        OcultarDatos();

        Cantidadtxt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                    e.consume();
                }
            }
        });
    }

    private void ConsultarRegistros() throws Exception {
        try {
            ArrayList<Peliculas> lista;
            lista = objPeliculasBL.Listado();

            if (lista != null) {

                String col[] = {"ID Pelicula", "Descripcion", "Genero", "Cantidad", "Estado"};

                DefaultTableModel tableModel = new DefaultTableModel(col, 0);

                Tabla.setModel(tableModel);

                for (int i = 0; i < lista.size(); i++) {

                    int IDPelicula = lista.get(i).getIntIdPelicula();
                    String Descripcion = lista.get(i).getStrDescripcion();
                    String Genero = lista.get(i).getStrGenero();
                    int Cantidad = lista.get(i).getIntCantidadPeliculas();
                    String Estado = lista.get(i).getStrEstado();

                    Object[] data = {IDPelicula, Descripcion, Genero, Cantidad, Estado};

                    tableModel.addRow(data);
                }
            }
        } catch (Exception ex) {
            throw new Exception("Error en tabla");
        }

    }

    public void LimpiarRegistros() {
        Consecutivolbl.setText("");
        Descripciontxt.setText("");
        GeneroList.setSelectedIndex(0);
        Cantidadtxt.setText("");
        Estadogb.clearSelection();
    }

    public void MostrarDatos() {
        IDpeliculaslbl.setVisible(true);
        Descripcionlbl.setVisible(true);
        Generolbl.setVisible(true);
        Cantidadlbl.setVisible(true);
        Estadolbl.setVisible(true);

        Consecutivolbl.setVisible(true);
        Descripciontxt.setVisible(true);
        GeneroList.setVisible(true);
        Cantidadtxt.setVisible(true);
        Activocb.setVisible(true);
        Inactivocb.setVisible(true);
    }

    public void OcultarDatos() {
        IDpeliculaslbl.setVisible(false);
        Descripcionlbl.setVisible(false);
        Generolbl.setVisible(false);
        Cantidadlbl.setVisible(false);
        Estadolbl.setVisible(false);

        Consecutivolbl.setVisible(false);
        Descripciontxt.setVisible(false);
        GeneroList.setVisible(false);
        Cantidadtxt.setVisible(false);
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

        Estadogb = new javax.swing.ButtonGroup();
        Titulolbl = new javax.swing.JLabel();
        IDpeliculaslbl = new javax.swing.JLabel();
        Generolbl = new javax.swing.JLabel();
        GeneroList = new javax.swing.JComboBox<>();
        Descripcionlbl = new javax.swing.JLabel();
        Descripciontxt = new javax.swing.JTextField();
        Cantidadlbl = new javax.swing.JLabel();
        Estadolbl = new javax.swing.JLabel();
        Activocb = new javax.swing.JRadioButton();
        Inactivocb = new javax.swing.JRadioButton();
        Ingresartbn = new javax.swing.JButton();
        Eliminarbtn = new javax.swing.JButton();
        Cambiarbtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tabla = new javax.swing.JTable();
        Cantidadtxt = new javax.swing.JTextField();
        Nuevobtn = new javax.swing.JButton();
        Guardarbtn = new javax.swing.JButton();
        Regresarbtn = new javax.swing.JButton();
        Consecutivolbl = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        Titulolbl.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Titulolbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Titulolbl.setText("Inventario de Películas");

        IDpeliculaslbl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        IDpeliculaslbl.setText("ID Peliculas");

        Generolbl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Generolbl.setText("Género");

        GeneroList.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Acción", "Bélico", "Biográfico", "Ciencia ficción", "Comedia", "Documental", "Drama", "Histórico", "Infantil", "Musical", "Porno-erótico", "Suspenso", "Terror / horror", "Western", "Otras" }));

        Descripcionlbl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Descripcionlbl.setText("Descripción");

        Cantidadlbl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Cantidadlbl.setText("Cantidad");

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
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID Películas", "Descripción", "Género", "Cantidad", "Estado"
            }
        ));
        Tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(Tabla);

        Cantidadtxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                CantidadtxtKeyTyped(evt);
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
            .addComponent(Titulolbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(Generolbl, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(GeneroList, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(Cantidadlbl, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(Cantidadtxt, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Nuevobtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Ingresartbn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Eliminarbtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Guardarbtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Cambiarbtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Regresarbtn))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Estadolbl, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Activocb, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(Inactivocb))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(IDpeliculaslbl, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(Consecutivolbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(Descripcionlbl, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(Descripciontxt, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 192, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Titulolbl)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(IDpeliculaslbl)
                    .addComponent(Consecutivolbl))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Descripcionlbl)
                    .addComponent(Descripciontxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Generolbl)
                    .addComponent(GeneroList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Cantidadlbl)
                    .addComponent(Cantidadtxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Inactivocb)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Activocb, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Estadolbl)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Nuevobtn)
                    .addComponent(Ingresartbn)
                    .addComponent(Eliminarbtn)
                    .addComponent(Guardarbtn)
                    .addComponent(Cambiarbtn)
                    .addComponent(Regresarbtn))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 442, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void IngresartbnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_IngresartbnMouseClicked
        try {
            Peliculas objPeliculas = new Peliculas();
            int consecutivo = objPeliculasBL.Consecutivo() + 1;

            if (objPeliculasBL != null) {

                objPeliculas.setIntIdPelicula(Integer.parseInt(Consecutivolbl.getText()));
                objPeliculas.setStrGenero((String) GeneroList.getSelectedItem());

                if (Descripciontxt.getText().equals("")) {
                    JOptionPane.showMessageDialog(this, "Espacio de descripción vacío", "Error", 2);
                } else {
                    objPeliculas.setStrDescripcion(Descripciontxt.getText());
                    if (Cantidadtxt.getText().equals("")) {
                        JOptionPane.showMessageDialog(this, "Espacio de cantidad vacío", "Error", 2);
                    } else {
                        objPeliculas.setIntCantidadPeliculas(Integer.parseInt(Cantidadtxt.getText()));
                        Activocb.setActionCommand(Activocb.getText());
                        Inactivocb.setActionCommand(Inactivocb.getText());
                        if (Activocb.isSelected() == false & Inactivocb.isSelected() == false) {
                            JOptionPane.showMessageDialog(this, "Seleccion de estado vacío", "Error", 2);
                        } else {
                            objPeliculas.setStrEstado(Estadogb.getSelection().getActionCommand());
                            try {
                                objPeliculasBL.Insertar(objPeliculas);
                                objPeliculasBL.grabarArchivoPeliculas();
                                ConsultarRegistros();
                                LimpiarRegistros();
                                RegresarMenuPrincipal();
                                OcultarDatos();
                            } catch (Exception ex) {
                                Logger.getLogger(VentanaInternaInventarioPeliculas.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(VentanaInternaInventarioPeliculas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_IngresartbnMouseClicked

    private void CantidadtxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CantidadtxtKeyTyped
        Cantidadtxt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                    e.consume();  // ignore event
                }
            }
        });
        if (Cantidadtxt.getText().length() >= 9) {
            evt.consume();
        }
    }//GEN-LAST:event_CantidadtxtKeyTyped

    private void TablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaMouseClicked
        row = Tabla.rowAtPoint(evt.getPoint());
    }//GEN-LAST:event_TablaMouseClicked

    private void EliminarbtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EliminarbtnMouseClicked
        try {
            int identificador = objPeliculasBL.Listado().get(row).getIntIdPelicula();
            String nombre = objPeliculasBL.Listado().get(row).getStrDescripcion();

            VentanaInternaInventarioPeliculas objVentanaInternaInventarioPeliculas = new VentanaInternaInventarioPeliculas();

            if ("Inactivo".equals(objPeliculasBL.Listado().get(row).getStrEstado())) {
                JOptionPane.showMessageDialog(this, "Pelicula inactivada anteriormente");
            } else {

                String botones[] = {"Sí", "No"};
                int eleccion = JOptionPane.showOptionDialog(objVentanaInternaInventarioPeliculas, "¿Desea inactivar la película: " + nombre + "?", "Inactivar película", 0, 0, null, botones, this);
                if (eleccion == JOptionPane.YES_OPTION) {

                    objPeliculasBL.Eliminar(identificador);
                    objPeliculasBL.grabarArchivoPeliculas();
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

    private void NuevobtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NuevobtnMouseClicked
        try {
            Regresarbtn.setVisible(true);
            Ingresartbn.setVisible(true);

            Eliminarbtn.setVisible(false);
            Cambiarbtn.setVisible(false);
            Nuevobtn.setVisible(false);

            int consecutivo = objPeliculasBL.Consecutivo() + 1;
            Consecutivolbl.setText(String.valueOf(consecutivo));

            MostrarDatos();
        } catch (Exception ex) {
            Logger.getLogger(VentanaInternaInventarioPeliculas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_NuevobtnMouseClicked

    private void CambiarbtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CambiarbtnMouseClicked
        try {
            Guardarbtn.setVisible(true);
            Ingresartbn.setVisible(false);
            Eliminarbtn.setVisible(false);
            Cambiarbtn.setVisible(false);
            Regresarbtn.setVisible(true);
            Nuevobtn.setVisible(false);

            MostrarDatos();
            Peliculas objPeliculas = new Peliculas();

            int identificador = objPeliculasBL.Listado().get(row).getIntIdPelicula();

            Consecutivolbl.setText(String.valueOf(objPeliculasBL.ConsultaPorID(identificador).getIntIdPelicula()));
            Descripciontxt.setText(objPeliculasBL.ConsultaPorID(identificador).getStrDescripcion());
            GeneroList.setSelectedItem(objPeliculasBL.ConsultaPorID(identificador).getStrGenero());
            Cantidadtxt.setText(String.valueOf(objPeliculasBL.ConsultaPorID(identificador).getIntCantidadPeliculas()));

            if ("Activo".equals(objPeliculasBL.ConsultaPorID(identificador).getStrEstado())) {
                setButtonGroup("Activo", Estadogb.getElements());
            } else {
                setButtonGroup("Inactivo", Estadogb.getElements());
            }

            ConsultarRegistros();
        } catch (Exception ex) {
            Logger.getLogger(VentanaInternaEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_CambiarbtnMouseClicked

    private void GuardarbtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GuardarbtnMouseClicked
        Peliculas objPeliculas = new Peliculas();

        objPeliculas.setIntIdPelicula(Integer.parseInt(Consecutivolbl.getText()));
        objPeliculas.setStrGenero((String) GeneroList.getSelectedItem());
        Activocb.setActionCommand(Activocb.getText());
        Inactivocb.setActionCommand(Inactivocb.getText());
        objPeliculas.setStrEstado(Estadogb.getSelection().getActionCommand());

        if (Descripciontxt.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Espacio de descripción vacío", "Error", 2);
        } else {
            objPeliculas.setStrDescripcion(Descripciontxt.getText());
            if (Cantidadtxt.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Espacio de cantidad de películas vacío", "Error", 2);
            } else {
                objPeliculas.setIntCantidadPeliculas(Integer.parseInt(Cantidadtxt.getText()));

                try {
                    objPeliculasBL.Actualizar(objPeliculas);
                    objPeliculasBL.grabarArchivoPeliculas();
                    ConsultarRegistros();
                    LimpiarRegistros();
                    RegresarMenuPrincipal();
                    OcultarDatos();
                } catch (Exception ex) {
                    Logger.getLogger(VentanaInternaEmpleados.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_GuardarbtnMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton Activocb;
    private javax.swing.JButton Cambiarbtn;
    private javax.swing.JLabel Cantidadlbl;
    private javax.swing.JTextField Cantidadtxt;
    private javax.swing.JLabel Consecutivolbl;
    private javax.swing.JLabel Descripcionlbl;
    private javax.swing.JTextField Descripciontxt;
    private javax.swing.JButton Eliminarbtn;
    private javax.swing.ButtonGroup Estadogb;
    private javax.swing.JLabel Estadolbl;
    private javax.swing.JComboBox<String> GeneroList;
    private javax.swing.JLabel Generolbl;
    private javax.swing.JButton Guardarbtn;
    private javax.swing.JLabel IDpeliculaslbl;
    private javax.swing.JRadioButton Inactivocb;
    private javax.swing.JButton Ingresartbn;
    private javax.swing.JButton Nuevobtn;
    private javax.swing.JButton Regresarbtn;
    private javax.swing.JTable Tabla;
    private javax.swing.JLabel Titulolbl;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
