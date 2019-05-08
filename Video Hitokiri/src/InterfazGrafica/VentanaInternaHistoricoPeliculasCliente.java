/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfazGrafica;

import CapaBL.ClienteBL;
import Entidades.Cliente;
import Entidades.FechaIngreso;
import Entidades.Reservaciones;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Emma
 */
public class VentanaInternaHistoricoPeliculasCliente extends javax.swing.JInternalFrame {

    ClienteBL objClienteBL;
    private ArrayList<Reservaciones> listaReservaHistorico = new ArrayList();

    /**
     * Creates new form VentanaInternaHistoricoPeliculasCliente
     */
    public VentanaInternaHistoricoPeliculasCliente() throws Exception {

        objClienteBL = new ClienteBL();
        initComponents();
        CargarComboBox();
        ActualizarNombre();
        LeerArchivoHistoricoPeliculas();
        ConsultarRegistrosHistorico();

    }
    
    public void ConsultarRegistrosHistoricoFiltrado() throws Exception {
        try {
            if (listaReservaHistorico != null) {
                String strColumnas[] = {"Carnet Cliente", "Nombre Completo", "Pelcula", "Fecha reservación", "Cantidad días reserva", "Estado", "Facturación"};
                DefaultTableModel TablaModelo = new DefaultTableModel(strColumnas, 0);
                tblHistorico.setModel(TablaModelo);
                int IntCarnetHistorico = (Integer) cbxName.getSelectedItem();
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

    public void ConsultarRegistrosHistorico() throws Exception {
        try {
            if (listaReservaHistorico != null) {
                String strColumnas[] = {"Carnet Cliente", "Nombre Completo", "Pelcula", "Fecha reservación", "Cantidad días reserva", "Estado", "Facturación"};
                DefaultTableModel TablaModelo = new DefaultTableModel(strColumnas, 0);
                tblHistorico.setModel(TablaModelo);
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

    public void ActualizarNombre() {
        try {
            ArrayList<Cliente> listaClientesLabel;
            listaClientesLabel = objClienteBL.Listado();
            int IntUserCarnet = (Integer) cbxName.getSelectedItem();
            String strNombreApellido = "";
            for (int i = 0; i < listaClientesLabel.size(); i++) {
                if (listaClientesLabel.get(i).getIntCarnet() == IntUserCarnet) {
                    strNombreApellido = listaClientesLabel.get(i).getStrNombre() + " " + listaClientesLabel.get(i).getStrApellido1();
                }
            }
            lblNombreUser.setText(strNombreApellido);
        } catch (Exception ex) {
            Logger.getLogger(VentanaInternaReservacionPeliculas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void CargarComboBox() throws Exception {
        Integer[] VectorClientes;
        ArrayList<Cliente> listaClientesCombo;
        listaClientesCombo = objClienteBL.Listado();
        VectorClientes = new Integer[listaClientesCombo.size()];
        for (int i = 0; i < listaClientesCombo.size(); i++) {
            VectorClientes[i] = listaClientesCombo.get(i).getIntCarnet();
        }
        cbxName.setModel(new DefaultComboBoxModel(VectorClientes));
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        cbxName = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblNombreUser = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHistorico = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Historico de Películas");

        cbxName.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxNameActionPerformed(evt);
            }
        });

        jLabel2.setText("Carnet");

        jLabel3.setText("Nombre");

        tblHistorico.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblHistorico);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 686, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbxName, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNombreUser, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbxName)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNombreUser, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbxNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxNameActionPerformed
        try {
            ActualizarNombre();
            ConsultarRegistrosHistoricoFiltrado();
        } catch (Exception ex) {
            Logger.getLogger(VentanaInternaHistoricoPeliculasCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cbxNameActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbxName;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblNombreUser;
    private javax.swing.JTable tblHistorico;
    // End of variables declaration//GEN-END:variables
}
