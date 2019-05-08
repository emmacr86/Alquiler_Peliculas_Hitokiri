/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfazGrafica;

import CapaBL.ClienteBL;
import CapaBL.PeliculasBL;
import Entidades.Peliculas;
import Entidades.Cliente;
import Entidades.FechaIngreso;
import Entidades.Reservaciones;
import Utilidades.AbrirVentanaUnica;
import Utilidades.FechaHora;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Emma
 */
public class VentanaInternaReservacionPeliculas extends javax.swing.JInternalFrame {

    PeliculasBL objPeliculasBL;
    ClienteBL objClienteBL;
    private ArrayList<Reservaciones> listaReservaHistorico = new ArrayList();
    private ArrayList<Peliculas> listaPeliculas;
    private ArrayList<Peliculas> listaReserva;
    int row;

    /**
     * Creates new form VentanaInternaReservacionPeliculas
     *
     * @throws java.lang.Exception
     */
    public VentanaInternaReservacionPeliculas() throws Exception {
        initComponents();
        objPeliculasBL = new PeliculasBL();
        objClienteBL = new ClienteBL();
        ConsultarRegistros(); //consulta..
        CargarComboBox();
        ActualizarNombre();
        LeerArchivoHistoricoPeliculas();
        btnSearch.setVisible(false);
        btnClearSearch.setVisible(false);
        btnFacturar.setVisible(false);

    }

    public void CargarComboBox() throws Exception {
        Integer[] VectorClientes;
        ArrayList<Cliente> listaClientesCombo;
        listaClientesCombo = objClienteBL.Listado();
        VectorClientes = new Integer[listaClientesCombo.size()];
        for (int i = 0; i < listaClientesCombo.size(); i++) {
            VectorClientes[i] = listaClientesCombo.get(i).getIntCarnet();
        }
        cbxUsuarios.setModel(new DefaultComboBoxModel(VectorClientes));
    }

    public void ConsultarRegistros() throws Exception {
        try {
            ArrayList<Peliculas> listaPeliculas;
            listaPeliculas = objPeliculasBL.Listado();

            if (listaPeliculas != null) {
                String strColumnas[] = {"ID Película", "Película", "Genero", "Cantidad", "Estado"};
                DefaultTableModel TablaModelo = new DefaultTableModel(strColumnas, 0);
                TblConsultaRegistros.setModel(TablaModelo);
                for (int i = 0; i < listaPeliculas.size(); i++) {
                    if (listaPeliculas.get(i).getStrEstado().equals("Activo")) {
                        if (listaPeliculas.get(i).getIntCantidadPeliculas() > 0) {
                            int IntIdPelicula = listaPeliculas.get(i).getIntIdPelicula();
                            String StrDescripcion = listaPeliculas.get(i).getStrDescripcion();
                            String StrGenero = listaPeliculas.get(i).getStrGenero();
                            int IntCantidadPeliculas = listaPeliculas.get(i).getIntCantidadPeliculas();
                            String StrEstado = listaPeliculas.get(i).getStrEstado();
                            Object[] data = {IntIdPelicula, StrDescripcion, StrGenero, IntCantidadPeliculas, StrEstado};
                            TablaModelo.addRow(data);
                        }
                    }
                }
            }
        } catch (Exception ex) {
            throw new Exception("No se pudo cargar la lista en la tabla" + ex.getMessage());
        }
    }

    public void ConsultarRegistrosHistorico() throws Exception {
        try {
            if (listaReservaHistorico != null) {
                String strColumnas[] = {"Carnet Cliente", "Nombre Completo", "Pelcula", "Fecha reservación", "Cantidad días reserva", "Estado", "Facturación"};
                DefaultTableModel TablaModelo = new DefaultTableModel(strColumnas, 0);
                TblConsultaRegistros.setModel(TablaModelo);
                for (int i = 0; i < listaReservaHistorico.size(); i++) {
                    int InCarnet = listaReservaHistorico.get(i).getIntCarnet();
                    String StrNombreCompleto = listaReservaHistorico.get(i).getStrNombreCompleto();
                    String StrNombrePelicula = listaReservaHistorico.get(i).getStrNombrePelicula();
                    String StrFechaReserva = listaReservaHistorico.get(i).getClassFechaReserva().toString();
                    int IntDias = listaReservaHistorico.get(i).getIntCantidadDías();
                    String StrEstado = listaReservaHistorico.get(i).getStrEstado();
                    String StrFactura = listaReservaHistorico.get(i).getStrFactura();
                    Object[] data = {InCarnet, StrNombreCompleto, StrNombrePelicula, StrFechaReserva, IntDias, StrEstado, StrFactura};
                    TablaModelo.addRow(data);
                }
            }
        } catch (Exception ex) {
            throw new Exception("No se pudo cargar la lista en la tabla" + ex.getMessage());
        }
    }

    public void ConsultarRegistrosHistoricoFiltrado() throws Exception {
        try {
            if (listaReservaHistorico != null) {
                String strColumnas[] = {"Carnet Cliente", "Nombre Completo", "Pelcula", "Fecha reservación", "Cantidad días reserva", "Estado", "Facturación"};
                DefaultTableModel TablaModelo = new DefaultTableModel(strColumnas, 0);
                TblConsultaRegistros.setModel(TablaModelo);
                int IntCarnetHistorico = (Integer) cbxUsuarios.getSelectedItem();
                for (int i = 0; i < listaReservaHistorico.size(); i++) {
                    if (listaReservaHistorico.get(i).getIntCarnet() == IntCarnetHistorico) {

                        int InCarnet = listaReservaHistorico.get(i).getIntCarnet();
                        String StrNombreCompleto = listaReservaHistorico.get(i).getStrNombreCompleto();
                        String StrNombrePelicula = listaReservaHistorico.get(i).getStrNombrePelicula();
                        String StrFechaReserva = listaReservaHistorico.get(i).getClassFechaReserva().toString();
                        int IntDias = listaReservaHistorico.get(i).getIntCantidadDías();
                        String StrEstado = listaReservaHistorico.get(i).getStrEstado();
                        String StrFactura = listaReservaHistorico.get(i).getStrFactura();
                        Object[] data = {InCarnet, StrNombreCompleto, StrNombrePelicula, StrFechaReserva, IntDias, StrEstado, StrFactura};
                        TablaModelo.addRow(data);
                    }
                }
            }
        } catch (Exception ex) {
            throw new Exception("No se pudo cargar la lista en la tabla" + ex.getMessage());
        }
    }

    public void ConsultarRegistrosFiltrado() throws Exception {
        try {
            listaPeliculas = objPeliculasBL.Listado();

            if (listaPeliculas != null) {
                String strColumnas[] = {"ID Película", "Película", "Genero", "Cantidad", "Estado"};
                DefaultTableModel TablaModelo = new DefaultTableModel(strColumnas, 0);
                TblConsultaRegistros.setModel(TablaModelo);
                for (int i = 0; i < listaPeliculas.size(); i++) {
                    if (listaPeliculas.get(i).getStrDescripcion().toUpperCase().contains(txtBusquedaPelicula.getText().toUpperCase())) {
                        if (listaPeliculas.get(i).getStrEstado().equals("Activo")) {
                            if (listaPeliculas.get(i).getIntCantidadPeliculas() > 0) {
                                int IntIdPelicula = listaPeliculas.get(i).getIntIdPelicula();
                                String StrDescripcion = listaPeliculas.get(i).getStrDescripcion();
                                String StrGenero = listaPeliculas.get(i).getStrGenero();
                                int IntCantidadPeliculas = listaPeliculas.get(i).getIntCantidadPeliculas();
                                String StrEstado = listaPeliculas.get(i).getStrEstado();
                                Object[] data = {IntIdPelicula, StrDescripcion, StrGenero, IntCantidadPeliculas, StrEstado};
                                TablaModelo.addRow(data);
                            }
                        }
                    }
                }
            }
        } catch (Exception ex) {
            throw new Exception("No se pudo cargar la lista en la tabla" + ex.getMessage());
        }
    }

    public void ActualizarNombre() {
        try {
            ArrayList<Cliente> listaClientesLabel;
            listaClientesLabel = objClienteBL.Listado();
            int IntUserCarnet = (Integer) cbxUsuarios.getSelectedItem();
            String strNombreApellido = "";
            for (int i = 0; i < listaClientesLabel.size(); i++) {
                if (listaClientesLabel.get(i).getIntCarnet() == IntUserCarnet) {
                    strNombreApellido = listaClientesLabel.get(i).getStrNombre() + " " + listaClientesLabel.get(i).getStrApellido1();
                }
            }
            lblNombreCliente.setText(strNombreApellido);
        } catch (Exception ex) {
            Logger.getLogger(VentanaInternaReservacionPeliculas.class.getName()).log(Level.SEVERE, null, ex);
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

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TblConsultaRegistros = new javax.swing.JTable();
        btnBuscar = new javax.swing.JButton();
        btnReservar = new javax.swing.JButton();
        cbxUsuarios = new javax.swing.JComboBox<>();
        lblUsuario = new javax.swing.JLabel();
        lblCliente = new javax.swing.JLabel();
        lblNombreCliente = new javax.swing.JLabel();
        lblBusquedaPelicula = new javax.swing.JLabel();
        txtBusquedaPelicula = new javax.swing.JTextField();
        btnLimpiaBusqueda = new javax.swing.JButton();
        btnDevolución = new javax.swing.JButton();
        btnSearch = new javax.swing.JButton();
        btnClearSearch = new javax.swing.JButton();
        btnFacturar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Reservaciones de Películas");

        TblConsultaRegistros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        TblConsultaRegistros.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TblConsultaRegistrosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TblConsultaRegistros);

        btnBuscar.setText("Buscar Película");
        btnBuscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBuscarMouseClicked(evt);
            }
        });

        btnReservar.setText("Reservar Película");
        btnReservar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnReservarMouseClicked(evt);
            }
        });

        cbxUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxUsuariosActionPerformed(evt);
            }
        });

        lblUsuario.setText("Cliente reservación: ");

        lblCliente.setText("Nombre del cliente:");

        lblBusquedaPelicula.setText("Buscar película: ");

        btnLimpiaBusqueda.setText("Limpiar búsqueda");
        btnLimpiaBusqueda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLimpiaBusquedaMouseClicked(evt);
            }
        });

        btnDevolución.setText("Devolución Película");
        btnDevolución.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDevoluciónMouseClicked(evt);
            }
        });

        btnSearch.setText("Buscar Película");
        btnSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSearchMouseClicked(evt);
            }
        });

        btnClearSearch.setText("Limpiar Busqueda");
        btnClearSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnClearSearchMouseClicked(evt);
            }
        });

        btnFacturar.setText("Facturar");
        btnFacturar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnFacturarMouseClicked(evt);
            }
        });

        jButton1.setText("Reservación");

        jButton2.setText("Devolución");

        jButton3.setText("Regresar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCliente)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblUsuario)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(txtBusquedaPelicula)
                                            .addComponent(cbxUsuarios, 0, 161, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(btnLimpiaBusqueda)
                                                .addGap(18, 18, 18)
                                                .addComponent(btnClearSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(btnBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(btnReservar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(btnDevolución, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(btnSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                        .addGap(18, 18, 18)
                                        .addComponent(btnFacturar, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(lblBusquedaPelicula)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addGap(18, 18, 18)
                                .addComponent(jButton2)
                                .addGap(18, 18, 18)
                                .addComponent(jButton3)))
                        .addGap(142, 142, 142)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCliente))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReservar)
                    .addComponent(btnDevolución)
                    .addComponent(btnFacturar))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBusquedaPelicula)
                    .addComponent(txtBusquedaPelicula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar)
                    .addComponent(btnSearch))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLimpiaBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClearSearch))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbxUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxUsuariosActionPerformed
        ActualizarNombre();
    }//GEN-LAST:event_cbxUsuariosActionPerformed

    private void btnBuscarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuscarMouseClicked
        try {
            ConsultarRegistrosFiltrado();
        } catch (Exception ex) {
            Logger.getLogger(VentanaInternaReservacionPeliculas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnBuscarMouseClicked

    private void btnLimpiaBusquedaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLimpiaBusquedaMouseClicked
        try {
            ConsultarRegistros();
        } catch (Exception ex) {
            Logger.getLogger(VentanaInternaReservacionPeliculas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnLimpiaBusquedaMouseClicked

    private void TblConsultaRegistrosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblConsultaRegistrosMouseClicked
        row = TblConsultaRegistros.rowAtPoint(evt.getPoint());
    }//GEN-LAST:event_TblConsultaRegistrosMouseClicked

    public void grabarArchivoHistoricoPeliculas() throws FileNotFoundException, Exception {
        String ruta = new File(".").getAbsolutePath() + "\\src\\ArchivosPlanos\\Reservaciones.txt";
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(ruta, true))); // si dejo (ruta) por default es false, lo que va a hacer es sobreescribir
        String[] VecFecha = null;
        FechaHora objFechaHora = new FechaHora();
        String StrFechaSistema = objFechaHora.Fecha();
        VecFecha = StrFechaSistema.split("/");
        Reservaciones objReservaciones = new Reservaciones();
        Object strUser = cbxUsuarios.getSelectedItem();

        objReservaciones.setIntCarnet((int) strUser);
        objReservaciones.setStrNombreCompleto(lblNombreCliente.getText());
        objReservaciones.setStrNombrePelicula(listaReserva.get(row).getStrDescripcion());

        int dia = Integer.parseInt(VecFecha[0]);
        int mes = Integer.parseInt(VecFecha[1]);
        int año = Integer.parseInt(VecFecha[2]);

        objReservaciones.setClassFechaReserva(new FechaIngreso(dia, mes, año));
        objReservaciones.setIntCantidadDías(3);
        objReservaciones.setStrEstado("Activo");
        objReservaciones.setStrFactura("No Facturar");

        try {                                       // la última línea
            pw.println(objReservaciones.getIntCarnet() + ","
                    + objReservaciones.getStrNombreCompleto() + ","
                    + objReservaciones.getStrNombrePelicula() + ","
                    + objReservaciones.getClassFechaReserva() + ","
                    + objReservaciones.getIntCantidadDías() + ","
                    + objReservaciones.getStrEstado() + ","
                    + objReservaciones.getStrFactura());

        } catch (Exception ex) {
            throw new Exception("Error al salvar el archivo: Error-->" + ex.getMessage());
        } finally {
            pw.close();
        }
    }

    public void grabarArchivoHistoricoPeliculas1() throws FileNotFoundException, Exception {
        String ruta = new File(".").getAbsolutePath() + "\\src\\ArchivosPlanos\\Reservaciones.txt";
        PrintWriter pw = new PrintWriter(ruta);
        try {
            for (Reservaciones item : listaReservaHistorico) {

                pw.println(item.getIntCarnet() + ","
                        + item.getStrNombreCompleto() + ","
                        + item.getStrNombrePelicula() + ","
                        + item.getClassFechaReserva() + ","
                        + item.getIntCantidadDías() + ","
                        + item.getStrEstado() + ","
                        + item.getStrFactura());
            }
        } catch (Exception ex) {
            throw new Exception("Error al salvar el archivo: Error-->" + ex.getMessage());
        } finally {
            pw.close();
        }
    }

    public void LeerArchivoHistoricoPeliculas() throws Exception {
        String pathProject = "";
        FileReader objfileReader = null;
        BufferedReader objbufferedReader = null;

        try {
            String line = null;
            String line1 = null;
            String[] VecLista = null;//new String[5];
            String[] VecFecha = null;//new String[3];
            Reservaciones objReservaciones = null;
            FechaIngreso objFechaIngreso;
            pathProject = new File("").getAbsolutePath().trim();
            pathProject += "\\src\\ArchivosPlanos\\Reservaciones.txt";;
            objfileReader = new FileReader(pathProject);
            objbufferedReader = new BufferedReader(objfileReader);

            while ((line = objbufferedReader.readLine()) != null) {
                VecLista = line.split(",");
                objReservaciones = new Reservaciones();
                objReservaciones.setIntCarnet(Integer.parseInt(VecLista[0]));
                objReservaciones.setStrNombreCompleto(VecLista[1]);
                objReservaciones.setStrNombrePelicula(VecLista[2]);
                line1 = VecLista[3];
                VecFecha = line1.split("/");
                int IntDia = Integer.parseInt(VecFecha[0]);
                int IntMes = Integer.parseInt(VecFecha[1]);
                int IntAno = Integer.parseInt(VecFecha[2]);
                objReservaciones.setClassFechaReserva(objFechaIngreso = new FechaIngreso(IntDia, IntMes, IntAno));
                objReservaciones.setIntCantidadDías(Integer.parseInt(VecLista[4]));
                objReservaciones.setStrEstado(VecLista[5]);
                objReservaciones.setStrFactura(VecLista[6]);
                listaReservaHistorico.add(objReservaciones);
            }
            objbufferedReader.close();
        } catch (Exception ex) {
            throw new Exception("Error al llamar la lista");
        } finally {
            objbufferedReader.close();
        }
    }

    private void btnReservarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReservarMouseClicked
        try {
            listaReserva = objPeliculasBL.Listado();
            int IntCantidad = objPeliculasBL.Listado().get(row).getIntCantidadPeliculas();
            objPeliculasBL.Listado().get(row).setIntCantidadPeliculas(IntCantidad - 1);
            ConsultarRegistros();
            objPeliculasBL.grabarArchivoPeliculas();
            grabarArchivoHistoricoPeliculas();

        } catch (Exception ex) {
            Logger.getLogger(VentanaInternaReservacionPeliculas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnReservarMouseClicked

    private void btnDevoluciónMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDevoluciónMouseClicked
        try {
            btnReservar.setVisible(false);
            btnBuscar.setVisible(false);
            btnLimpiaBusqueda.setVisible(false);
            btnSearch.setVisible(true);
            btnClearSearch.setVisible(true);
            btnFacturar.setVisible(true);
            lblBusquedaPelicula.setVisible(false);
            txtBusquedaPelicula.setVisible(false);
            listaReservaHistorico.clear();
            LeerArchivoHistoricoPeliculas();
            ConsultarRegistrosHistorico();
        } catch (Exception ex) {
            Logger.getLogger(VentanaInternaReservacionPeliculas.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_btnDevoluciónMouseClicked


    private void btnFacturarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFacturarMouseClicked
        try {
            if (listaReservaHistorico.get(row).getStrEstado().equals("Inactivo")) {
                JOptionPane.showMessageDialog(null, "Esta película ya fue devuelta");
            } else {
                listaReservaHistorico.get(row).setStrEstado("Inactivo");
                listaReservaHistorico.get(row).setStrFactura("Facturar");
                grabarArchivoHistoricoPeliculas1();
                ConsultarRegistrosHistorico();
                Factura abrirFatura = new Factura();
                abrirFatura.setVisible(true);
            }

        } catch (Exception ex) {
            Logger.getLogger(VentanaInternaReservacionPeliculas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnFacturarMouseClicked

    private void btnSearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSearchMouseClicked
        try {
            ConsultarRegistrosHistoricoFiltrado();
        } catch (Exception ex) {
            Logger.getLogger(VentanaInternaReservacionPeliculas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSearchMouseClicked

    private void btnClearSearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnClearSearchMouseClicked
        try {
            ConsultarRegistrosHistorico();
        } catch (Exception ex) {
            Logger.getLogger(VentanaInternaReservacionPeliculas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnClearSearchMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TblConsultaRegistros;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnClearSearch;
    private javax.swing.JButton btnDevolución;
    private javax.swing.JButton btnFacturar;
    private javax.swing.JButton btnLimpiaBusqueda;
    private javax.swing.JButton btnReservar;
    private javax.swing.JButton btnSearch;
    private javax.swing.JComboBox<String> cbxUsuarios;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBusquedaPelicula;
    private javax.swing.JLabel lblCliente;
    private javax.swing.JLabel lblNombreCliente;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JTextField txtBusquedaPelicula;
    // End of variables declaration//GEN-END:variables
}
