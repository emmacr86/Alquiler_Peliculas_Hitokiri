/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfazGrafica;

import Entidades.FechaIngreso;
import Entidades.Reservaciones;
import Utilidades.FechaHora;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Carlos
 */
public class Factura extends javax.swing.JFrame {

    private ArrayList<Reservaciones> listaReservaHistorico = new ArrayList();

    /**
     * Creates new form Factura
     */
    public Factura() throws Exception {
        initComponents();
        LeerArchivoHistoricoPeliculas();
        FechaHora objFechaHora = new FechaHora();
        String StrFechaSistema = objFechaHora.Fecha();

        for (int i = 0; i < listaReservaHistorico.size(); i++) {
            if (listaReservaHistorico.get(i).getStrFactura().equals("Facturar")) {
                String StrFechaArchivo = String.valueOf(listaReservaHistorico.get(i).getClassFechaReserva().getIntDia()) + "/" + String.valueOf(listaReservaHistorico.get(i).getClassFechaReserva().getIntMes()) + "/" + String.valueOf(listaReservaHistorico.get(i).getClassFechaReserva().getIntAno());
                int IntDias = ContarDias(StrFechaSistema, StrFechaArchivo);
                lblMovieName.setText(listaReservaHistorico.get(i).getStrNombrePelicula());
                lblFechaReserva.setText(listaReservaHistorico.get(i).getClassFechaReserva().toString());
                lblFechaActual.setText(StrFechaSistema);
                lblAmount.setText("1000");
                
                if (IntDias > 3)
                {
                    int IntDíasMulta = IntDias - 3;
                    int IntMontoMulta = (IntDíasMulta * 500);
                    lblMultas.setText(String.valueOf(IntMontoMulta));
                }else{
                    lblMultas.setText("0");
                }
                
                int IntSubtotal = (Integer.parseInt(lblAmount.getText())+(Integer.parseInt(lblMultas.getText())));
                lblTotal.setText(String.valueOf(IntSubtotal));
                listaReservaHistorico.get(i).setStrFactura("Facturado");

            }
        }

//        
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

    private int ContarDias(String StrFechaSistema, String StrFechaArchivo) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaInicial = dateFormat.parse(StrFechaArchivo);
        Date fechaFinal = dateFormat.parse(StrFechaSistema);
        int ContadorDias = (int) ((fechaFinal.getTime() - fechaInicial.getTime()) / 86400000);
        return ContadorDias;
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
        lblPelicula = new javax.swing.JLabel();
        lblMovieName = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblFechaReserva = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblFechaActual = new javax.swing.JLabel();
        lblMonto = new javax.swing.JLabel();
        lblAmount = new javax.swing.JLabel();
        lblPenalty = new javax.swing.JLabel();
        lblMultas = new javax.swing.JLabel();
        lblTo = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(430, 400));
        setMinimumSize(new java.awt.Dimension(430, 400));
        setPreferredSize(new java.awt.Dimension(430, 400));
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Video Hitokiri Factura Electrónica");

        lblPelicula.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblPelicula.setText("Película");

        lblMovieName.setText("txtPeli");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Fecha Reservación");

        lblFechaReserva.setText("fecha");

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Fecha Actual");

        lblFechaActual.setText("fecha2");

        lblMonto.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblMonto.setText("Monto");

        lblAmount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblAmount.setText("monto2");

        lblPenalty.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblPenalty.setText("Multa");

        lblMultas.setText("morosidad");

        lblTo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTo.setText("Total:");

        lblTotal.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTotal.setText("numerototal");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblPelicula, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblMonto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblFechaReserva, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblMovieName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblFechaActual, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblAmount, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPenalty, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                    .addComponent(lblMultas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPelicula)
                    .addComponent(lblMovieName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblFechaReserva, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lblFechaActual, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMonto)
                    .addComponent(lblAmount))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMultas, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPenalty))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTo)
                    .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(161, 161, 161))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Factura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Factura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Factura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Factura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Factura().setVisible(true);
                } catch (Exception ex) {
                    Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lblAmount;
    private javax.swing.JLabel lblFechaActual;
    private javax.swing.JLabel lblFechaReserva;
    private javax.swing.JLabel lblMonto;
    private javax.swing.JLabel lblMovieName;
    private javax.swing.JLabel lblMultas;
    private javax.swing.JLabel lblPelicula;
    private javax.swing.JLabel lblPenalty;
    private javax.swing.JLabel lblTo;
    private javax.swing.JLabel lblTotal;
    // End of variables declaration//GEN-END:variables
}
