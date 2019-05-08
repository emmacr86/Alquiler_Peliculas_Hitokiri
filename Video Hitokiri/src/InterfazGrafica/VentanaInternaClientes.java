/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfazGrafica;

import CapaBL.ClienteBL;
import Entidades.Cliente;
import Entidades.Direccion;
import Entidades.FechaAnos;
import Utilidades.Distritos;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import Utilidades.FechaHora;

/**
 *
 * @author Emma
 */
public class VentanaInternaClientes extends javax.swing.JInternalFrame {

    private TableRowSorter trsFiltro;
    private int row;
    ClienteBL objClienteBL;
    int IntConsecutivo = 0;

    final DefaultComboBoxModel cantonesSanjose = new DefaultComboBoxModel(new String[]{"San José", "Escazú", "Desamparados", "Puriscal", "Tarrazú", "Aserrí", "Mora", "Goicoechea", "Santa Ana", "Alajuelita", "Vázquez de Coronado", "Acosta", "Tibás", "Moravia", "Montes de Oca", "Turrubares", "Dota", "Curridabat", "Pérez Zeledón", "León Cortés"});
    final DefaultComboBoxModel cantonesHeredia = new DefaultComboBoxModel(new String[]{"Heredia", "Barva", "Santo Domingo", "Santa Bárbara", "San Rafael", "San Isidro", "Belén", "Flores", "San Pablo", "Sarapiquí"});
    final DefaultComboBoxModel cantonesCartago = new DefaultComboBoxModel(new String[]{"Cartago", "Paraíso", "La Unión", "Jiménez", "Turrialba", "Alvarado", "Oreamuno", "El Guarco"});
    final DefaultComboBoxModel cantonesAlajuela = new DefaultComboBoxModel(new String[]{"Alajuela", "San Ramón", "Grecia", "San Mateo", "Atenas", "Naranjo", "Palmares", "Poás", "Orotina", "San Carlos", "Zarcero", "Valverde Vega", "Upala", "Los Chiles", "Guatuso"});
    final DefaultComboBoxModel cantonesPuntarenas = new DefaultComboBoxModel(new String[]{"Puntarenas", "Esparza", "Buenos Aires", "Montes de Oro", "Osa", "Quepos", "Golfito", "Coto Brus", "Parrita", "Corredores", "Garabito"});
    final DefaultComboBoxModel cantonesGuanacaste = new DefaultComboBoxModel(new String[]{"Liberia", "Nicoya", "Santa Cruz", "Bagaces", "Carrillo", "Cañas", "Abangares", "Tilarán", "Nandayure", "La Cruz", "Hojancha"});
    final DefaultComboBoxModel cantonesLimon = new DefaultComboBoxModel(new String[]{"Limón", "Pococí", "Siquirres", "Talamanca", "Matina", "Guácimo"});

    /**
     * Creates new form VentanaInternaClientes
     */
    public VentanaInternaClientes() throws Exception {
        initComponents();
        objClienteBL = new ClienteBL();
        ConsultarRegistros();
        Guardarbtn.setVisible(false);

        Guardarbtn.setVisible(false);
        Ingresartbn.setVisible(false);
        Regresarbtn.setVisible(false);
        Filtradobx.setVisible(false);
        Busquedatxt.setVisible(false);

        OcultarDatos();
        SeleccionLugares();

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

    private int ContarDias(String StrFechaSistema, String StrFechaArchivo) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaInicial = dateFormat.parse(StrFechaArchivo);
        Date fechaFinal = dateFormat.parse(StrFechaSistema);
        int ContadorDias = (int) ((fechaFinal.getTime() - fechaInicial.getTime()) / 86400000);
        return ContadorDias;

    }

    private void ConsultarRegistros() throws Exception {
        Tabla.setRowSorter(null);
        FechaHora objFechaHora = new FechaHora();
        String StrFechaSistema = objFechaHora.Fecha();
        try {

            ArrayList<Cliente> lista;
            lista = objClienteBL.Listado();

            if (lista != null) {

                String col[] = {"Nombre", "Primer Apellido", "Segundo Apellido", "Nacimiento", "Edad", "Cédula", "Carnet", "Dirección", "Provincia", "Cantón", "Distrito", "Género", "Profesión", "Estado"};

                DefaultTableModel tableModel = new DefaultTableModel(col, 0);

                Tabla.setModel(tableModel);

                for (int i = 0; i < lista.size(); i++) {

                    String Nombre = lista.get(i).getStrNombre();
                    String Apellido1 = lista.get(i).getStrApellido1();
                    String Apellido2 = lista.get(i).getStrApellido2();
                    String Nacimiento = lista.get(i).getClassFechaNacimiento().toString();
                    int Annos = ContarDias(StrFechaSistema, Nacimiento);
                    int Edad = Annos / 365;
                    int Cedula = lista.get(i).getIntCedula();
                    int Carnet = lista.get(i).getIntCarnet();
                    String Direccion = lista.get(i).getClassDireccion().getStrDetalle();
                    String Provincia = lista.get(i).getClassDireccion().getStrProvincia();
                    String Canton = lista.get(i).getClassDireccion().getStrCanton();
                    String Distrito = lista.get(i).getClassDireccion().getStrDistrito();
                    String Genero = lista.get(i).getStrGenero();
                    String Profesion = lista.get(i).getStrProfesion();
                    String Estado = lista.get(i).getStrEstado();

                    Object[] data = {Nombre, Apellido1, Apellido2, Nacimiento, Edad, Cedula, Carnet, Direccion, Provincia, Canton, Distrito, Genero, Profesion, Estado};

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
        FechaNacimientosc.setDate(null);
        Cedulatxt.setText(String.valueOf(""));
        Consecutivolbl.setText(String.valueOf(""));
        Direcciontxt.setText("");
        Provinciabx.setSelectedIndex(0);
        Cantonbx.setSelectedIndex(0);
        Distritobx.setSelectedIndex(0);
        Generogb.clearSelection();
        ProfesionesList.setSelectedIndex(0);
        Estadogb.clearSelection();
        Busquedatxt.setText("");
    }

    public void MostrarDatos() {
        Nombrelbl.setVisible(true);
        Apellido1lbl.setVisible(true);
        Apellido2lbl.setVisible(true);
        Fechalbl.setVisible(true);
        Cedulalbl.setVisible(true);
        Carnetlbl.setVisible(true);
        Direccionlbl.setVisible(true);
        Provincialbl.setVisible(true);
        Cantonlbl.setVisible(true);
        Distritolbl.setVisible(true);
        Generolbl.setVisible(true);
        Profesionlbl.setVisible(true);
        Estadolbl.setVisible(true);

        Nombretxt.setVisible(true);
        Apellido1txt.setVisible(true);
        Apellido2txt.setVisible(true);
        FechaNacimientosc.setVisible(true);
        Cedulatxt.setVisible(true);
        Consecutivolbl.setVisible(true);
        Direcciontxt.setVisible(true);
        Provinciabx.setVisible(true);
        Cantonbx.setVisible(true);
        Distritobx.setVisible(true);
        Masculinobx.setVisible(true);
        Femeninobx.setVisible(true);
        ProfesionesList.setVisible(true);
        Activocb.setVisible(true);
        Inactivocb.setVisible(true);
    }

    public void OcultarDatos() {
        Nombrelbl.setVisible(false);
        Apellido1lbl.setVisible(false);
        Apellido2lbl.setVisible(false);
        Fechalbl.setVisible(false);
        Cedulalbl.setVisible(false);
        Carnetlbl.setVisible(false);
        Direccionlbl.setVisible(false);
        Provincialbl.setVisible(false);
        Cantonlbl.setVisible(false);
        Distritolbl.setVisible(false);
        Generolbl.setVisible(false);
        Profesionlbl.setVisible(false);
        Estadolbl.setVisible(false);

        Nombretxt.setVisible(false);
        Apellido1txt.setVisible(false);
        Apellido2txt.setVisible(false);
        FechaNacimientosc.setVisible(false);
        Cedulatxt.setVisible(false);
        Consecutivolbl.setVisible(false);
        Direcciontxt.setVisible(false);
        Provinciabx.setVisible(false);
        Cantonbx.setVisible(false);
        Distritobx.setVisible(false);
        Masculinobx.setVisible(false);
        Femeninobx.setVisible(false);
        ProfesionesList.setVisible(false);
        Activocb.setVisible(false);
        Inactivocb.setVisible(false);
    }

    public void RegresarMenuPrincipal() {
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
        if (Filtradobx.getSelectedItem() == "Profesión") {
            columnaABuscar = 11;
        }
        if (Filtradobx.getSelectedItem() == "Estado") {
            columnaABuscar = 12;
        }
        trsFiltro.setRowFilter(RowFilter.regexFilter(Busquedatxt.getText(), columnaABuscar));
    }

    public void SeleccionLugares() {
        if ("San José".equals(Provinciabx.getSelectedItem())) {
            Cantonbx.setModel(cantonesSanjose);
        } else {
            if ("Heredia".equals(Provinciabx.getSelectedItem())) {
                Cantonbx.setModel(cantonesHeredia);
            } else {
                if ("Cartago".equals(Provinciabx.getSelectedItem())) {
                    Cantonbx.setModel(cantonesCartago);
                } else {
                    if ("Alajuela".equals(Provinciabx.getSelectedItem())) {
                        Cantonbx.setModel(cantonesAlajuela);
                    } else {
                        if ("Guanacaste".equals(Provinciabx.getSelectedItem())) {
                            Cantonbx.setModel(cantonesGuanacaste);
                        } else {
                            if ("Puntarenas".equals(Provinciabx.getSelectedItem())) {
                                Cantonbx.setModel(cantonesPuntarenas);
                            } else {
                                if ("Limón".equals(Provinciabx.getSelectedItem())) {
                                    Cantonbx.setModel(cantonesLimon);
                                } else {
                                    JOptionPane.showMessageDialog(this, "Error al seleccionar dato", "Error", 2);
                                }
                            }
                        }
                    }
                }
            }
        }
        Distritos objDistritos = new Distritos();
        String canton = (String) Cantonbx.getSelectedItem();
        ArrayList<String> listaDistritos = objDistritos.SeleccionDistritos(canton);
        String vectorDistritos[] = objDistritos.vectorDistritos(listaDistritos);
        DefaultComboBoxModel distritos = new DefaultComboBoxModel(vectorDistritos);
        Distritobx.setModel(distritos);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Generogb = new javax.swing.ButtonGroup();
        Estadogb = new javax.swing.ButtonGroup();
        Eliminarbtn = new javax.swing.JButton();
        Titulolbl = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tabla = new javax.swing.JTable();
        Cambiarbtn = new javax.swing.JButton();
        Ingresartbn = new javax.swing.JButton();
        Nombrelbl = new javax.swing.JLabel();
        Apellido1lbl = new javax.swing.JLabel();
        Apellido2lbl = new javax.swing.JLabel();
        Fechalbl = new javax.swing.JLabel();
        Cedulalbl = new javax.swing.JLabel();
        Carnetlbl = new javax.swing.JLabel();
        Direccionlbl = new javax.swing.JLabel();
        Provincialbl = new javax.swing.JLabel();
        Cantonlbl = new javax.swing.JLabel();
        Distritolbl = new javax.swing.JLabel();
        Generolbl = new javax.swing.JLabel();
        Profesionlbl = new javax.swing.JLabel();
        Estadolbl = new javax.swing.JLabel();
        Nombretxt = new javax.swing.JTextField();
        Apellido1txt = new javax.swing.JTextField();
        Cedulatxt = new javax.swing.JTextField();
        FechaNacimientosc = new com.toedter.calendar.JDateChooser();
        Apellido2txt = new javax.swing.JTextField();
        Direcciontxt = new javax.swing.JTextField();
        Provinciabx = new javax.swing.JComboBox<>();
        Distritobx = new javax.swing.JComboBox<>();
        Cantonbx = new javax.swing.JComboBox<>();
        Masculinobx = new javax.swing.JRadioButton();
        Femeninobx = new javax.swing.JRadioButton();
        Activocb = new javax.swing.JRadioButton();
        Inactivocb = new javax.swing.JRadioButton();
        ProfesionesList = new javax.swing.JComboBox<>();
        Guardarbtn = new javax.swing.JButton();
        Regresarbtn = new javax.swing.JButton();
        Nuevobtn = new javax.swing.JButton();
        Buscarbtn = new javax.swing.JButton();
        Filtradobx = new javax.swing.JComboBox<>();
        Busquedatxt = new javax.swing.JTextField();
        Consecutivolbl = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        Eliminarbtn.setText("Inactivar");
        Eliminarbtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                EliminarbtnMouseClicked(evt);
            }
        });

        Titulolbl.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Titulolbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Titulolbl.setText("Mantenimiento Clientes");

        Tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Nombre", "Apellido 1", "Apellido 2", "Nacimiento", "Cédula", "Carnet", "Dirección", "Provincia", "Cantón", "Distrito", "Genero", "Profesión", "Estado"
            }
        ));
        Tabla.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        Tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(Tabla);

        Cambiarbtn.setText("Actualizar");
        Cambiarbtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CambiarbtnMouseClicked(evt);
            }
        });

        Ingresartbn.setText("Ingresar");
        Ingresartbn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                IngresartbnMouseClicked(evt);
            }
        });

        Nombrelbl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Nombrelbl.setText("Nombre");

        Apellido1lbl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Apellido1lbl.setText("Primer Apelido");

        Apellido2lbl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Apellido2lbl.setText("Segundo Apellido");

        Fechalbl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Fechalbl.setText("Fecha de Nacimiento");

        Cedulalbl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Cedulalbl.setText("Cédula");

        Carnetlbl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Carnetlbl.setText("Carnet");

        Direccionlbl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Direccionlbl.setText("Dirección ");

        Provincialbl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Provincialbl.setText("Provincia");

        Cantonlbl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Cantonlbl.setText("Cantón");

        Distritolbl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Distritolbl.setText("Distrito");

        Generolbl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Generolbl.setText("Genero");

        Profesionlbl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Profesionlbl.setText("Profesión");

        Estadolbl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Estadolbl.setText("Estado");

        Cedulatxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                CedulatxtKeyTyped(evt);
            }
        });

        FechaNacimientosc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                FechaNacimientoscKeyTyped(evt);
            }
        });

        Provinciabx.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "San José", "Alajuela", "Cartago", "Heredia", "Guanacaste", "Puntarenas", "Limón" }));
        Provinciabx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProvinciabxActionPerformed(evt);
            }
        });

        Distritobx.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-Seleccionar-" }));

        Cantonbx.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-Seleccionar-" }));
        Cantonbx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CantonbxActionPerformed(evt);
            }
        });

        Generogb.add(Masculinobx);
        Masculinobx.setText("Masculino");

        Generogb.add(Femeninobx);
        Femeninobx.setText("Femenino");

        Estadogb.add(Activocb);
        Activocb.setText("Activo");

        Estadogb.add(Inactivocb);
        Inactivocb.setText("Inactivo");

        ProfesionesList.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Abogado/a", "Actor /Actriz", "Agente de viaje", "Arquitecto/a", "Astrónomo/a", "Autor/a", "Barrendero/a", "Bibliotecario/a ", "Bombero/a", "Carabinero/a", "Cartero/a", "Carnicero/a", "Carpintero/a", "Científico/a", "Cirujano/a", "Conductor/a ", "Contador/a", "Corredor de bienes", "Chef/Cocinero/a", "Dentista", "Diseñador/a", "Doctor/a", "Electricista", "Enfermero/a", "Estilista", "Farmacéutico/a", "Fontanero/a", "Florista", "Fotógrafo/a ", "Gásfiter", "Gasfitero", "Granjero/a", "Ingeniero/a", "Jardinero/a", "Juez/a", "Limpiador/a de vidrios", "Maestro de obras", "Mecánico/a", "Mesero/a", "Modelo/a", "Oftalmólogo", "Panadero/a", "Peluquero/a", "Periodista", "Pescador/a", "Pintor/a", "Piloto", "Plomero/a", "Policía", "Político", "Profesor/a", "Psiquiatra", "Recepcionista", "Salvavidas", "Sastre", "Secretario/a", "Taxista", "Trabajador/a de fabrica", "Traductor/a", "Vendedor/a", "Veterinario/a", "Otros" }));

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

        Filtradobx.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nombre", "Apellido1", "Apellido2", "Profesión", "Estado" }));

        Busquedatxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                BusquedatxtKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Titulolbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Direccionlbl, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Nombrelbl, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Fechalbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Provincialbl, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Generolbl, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Profesionlbl, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Estadolbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Masculinobx)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Femeninobx))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Activocb)
                                .addGap(18, 18, 18)
                                .addComponent(Inactivocb))
                            .addComponent(ProfesionesList, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(FechaNacimientosc, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Nombretxt, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(Apellido1lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(Apellido1txt, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(Cedulalbl, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(Cedulatxt, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(Apellido2lbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(Carnetlbl, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(Apellido2txt, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                                    .addComponent(Consecutivolbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(Direcciontxt, javax.swing.GroupLayout.PREFERRED_SIZE, 504, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Provinciabx, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(Cantonlbl, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(Cantonbx, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(Distritolbl, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(Distritobx, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(Nuevobtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Ingresartbn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Eliminarbtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Cambiarbtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Guardarbtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Regresarbtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Buscarbtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Filtradobx, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Busquedatxt)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(Titulolbl)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Nombrelbl)
                    .addComponent(Nombretxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Apellido1lbl)
                    .addComponent(Apellido1txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Apellido2lbl)
                    .addComponent(Apellido2txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(FechaNacimientosc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Cedulatxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Cedulalbl)
                        .addComponent(Carnetlbl)
                        .addComponent(Consecutivolbl))
                    .addComponent(Fechalbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Direccionlbl)
                    .addComponent(Direcciontxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Provinciabx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Cantonlbl)
                    .addComponent(Cantonbx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Distritolbl)
                    .addComponent(Distritobx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Provincialbl))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Generolbl)
                    .addComponent(Masculinobx)
                    .addComponent(Femeninobx))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Profesionlbl)
                    .addComponent(ProfesionesList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Estadolbl)
                    .addComponent(Activocb)
                    .addComponent(Inactivocb))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Buscarbtn)
                        .addComponent(Busquedatxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Filtradobx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Ingresartbn)
                        .addComponent(Cambiarbtn)
                        .addComponent(Eliminarbtn)
                        .addComponent(Guardarbtn)
                        .addComponent(Regresarbtn)
                        .addComponent(Nuevobtn)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 443, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void IngresartbnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_IngresartbnMouseClicked

        try {
            Cliente objCliente = new Cliente();
            int consecutivo = objClienteBL.Consecutivo() + 1;
            Direccion objDireccion = new Direccion();
            FechaAnos objFechaAnos = null;
            FechaHora objFechaHora = new FechaHora();
            String StrFechaSistema = objFechaHora.Fecha();

            if (FechaNacimientosc.getCalendar() != null) {

                int dia = FechaNacimientosc.getCalendar().get(Calendar.DAY_OF_MONTH);
                int mes = FechaNacimientosc.getCalendar().get(Calendar.MONTH) + 1;
                int anno = FechaNacimientosc.getCalendar().get(Calendar.YEAR);

                objFechaAnos = new FechaAnos(dia, mes, anno);

                if (objFechaAnos != null) {

                    int annos = ContarDias(StrFechaSistema, objFechaAnos.toString());
                    int mayorEdad = annos / 365;

                    if (18 > mayorEdad) {
                        JOptionPane.showMessageDialog(this, "El cliente es menor de Edad", "Error", 2);
                    } else {

                        objCliente.setClassFechaNacimiento(objFechaAnos);
                        objCliente.setIntCarnet(consecutivo);
                        String provincia = (String) Provinciabx.getSelectedItem();
                        String canton = (String) Cantonbx.getSelectedItem();
                        String distrito = (String) Distritobx.getSelectedItem();
                        objDireccion.setStrProvincia(provincia);
                        objDireccion.setStrCanton(canton);
                        objDireccion.setStrDistrito(distrito);
                        String profesion = (String) ProfesionesList.getSelectedItem();
                        objCliente.setStrProfesion(profesion);

                        if (Nombretxt.getText().equals("")) {
                            JOptionPane.showMessageDialog(this, "Espacio de nombre vacío", "Error", 2);
                        } else {
                            objCliente.setStrNombre(Nombretxt.getText());
                            if (Apellido1txt.getText().equals("")) {
                                JOptionPane.showMessageDialog(this, "Espacio de primer Apellido vacío", "Error", 2);
                            } else {
                                objCliente.setStrApellido1(Apellido1txt.getText());
                                if (Apellido2txt.getText().equals("")) {
                                    JOptionPane.showMessageDialog(this, "Espacio de segundo Apellido vacío", "Error", 2);
                                } else {
                                    objCliente.setStrApellido2(Apellido2txt.getText());
                                    if (Cedulatxt.getText().equals("")) {
                                        JOptionPane.showMessageDialog(this, "Espacio de Cedula vacío", "Error", 2);
                                    } else {
                                        objCliente.setIntCedula(Integer.parseInt(Cedulatxt.getText()));
                                        if (Direcciontxt.getText().equals("")) {
                                            JOptionPane.showMessageDialog(this, "Espacio de Dirección vacío", "Error", 2);
                                        } else {
                                            objDireccion.setStrDetalle(Direcciontxt.getText());
                                            objCliente.setClassDireccion(objDireccion);
                                            Masculinobx.setActionCommand(Masculinobx.getText());
                                            Femeninobx.setActionCommand(Femeninobx.getText());
                                            if (Masculinobx.isSelected() == false & Femeninobx.isSelected() == false) {
                                                JOptionPane.showMessageDialog(this, "Selección de genero vacío", "Error", 2);
                                            } else {
                                                objCliente.setStrGenero(Generogb.getSelection().getActionCommand());
                                                Activocb.setActionCommand(Activocb.getText());
                                                Inactivocb.setActionCommand(Inactivocb.getText());
                                                if (Activocb.isSelected() == false & Inactivocb.isSelected() == false) {
                                                    JOptionPane.showMessageDialog(this, "Selección de estado vacío", "Error", 2);
                                                } else {
                                                    objCliente.setStrEstado(Estadogb.getSelection().getActionCommand());
                                                    try {
                                                        objClienteBL.Insertar(objCliente);
                                                        objClienteBL.grabarArchivoCliente();
                                                        ConsultarRegistros();
                                                        LimpiarRegistros();
                                                        RegresarMenuPrincipal();
                                                        OcultarDatos();

                                                    } catch (Exception ex) {
                                                        Logger.getLogger(VentanaInternaClientes.class.getName()).log(Level.SEVERE, null, ex);
                                                    }
                                                }
                                            }
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
            Logger.getLogger(VentanaInternaClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_IngresartbnMouseClicked

    private void CedulatxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CedulatxtKeyTyped
        Cedulatxt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                    e.consume();
                }
            }
        });
        if (Cedulatxt.getText().length() >= 9) {
            evt.consume();
        }
    }//GEN-LAST:event_CedulatxtKeyTyped

    private void FechaNacimientoscKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FechaNacimientoscKeyTyped
        char c = evt.getKeyChar();
        if (Character.isLetter(c) && !evt.isAltDown()) {
            evt.consume();
        }
    }//GEN-LAST:event_FechaNacimientoscKeyTyped

    private void EliminarbtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EliminarbtnMouseClicked
        try {
            int identificador = objClienteBL.Listado().get(row).getIntCedula();
            String nombre = objClienteBL.Listado().get(row).getStrNombre();
            String apellido1 = objClienteBL.Listado().get(row).getStrApellido1();
            String apellido2 = objClienteBL.Listado().get(row).getStrApellido2();

            VentanaInternaClientes objVentanaInternaClientes = new VentanaInternaClientes();

            if ("Inactivo".equals(objClienteBL.Listado().get(row).getStrEstado())) {
                JOptionPane.showMessageDialog(this, "Cliente inactivo anteriormente", "Mensaje", 2);
            } else {

                String botones[] = {"Sí", "No"};
                int eleccion = JOptionPane.showOptionDialog(objVentanaInternaClientes, "¿Desea inactivar al cliente " + nombre + " " + apellido1 + " " + apellido2 + "?", "Inactivar cliente", 0, 0, null, botones, this);
                if (eleccion == JOptionPane.YES_OPTION) {

                    objClienteBL.Eliminar(objClienteBL.Listado().get(row).getIntCarnet());
                    objClienteBL.grabarArchivoCliente();
                    ConsultarRegistros();

                } else {

                    if (eleccion == JOptionPane.NO_OPTION) {
                        System.out.println("Opcion Inactivo cancelado");

                    }
                }
            }

        } catch (Exception ex) {
            Logger.getLogger(VentanaInternaClientes.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_EliminarbtnMouseClicked

    private void TablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaMouseClicked
        row = Tabla.rowAtPoint(evt.getPoint());
    }//GEN-LAST:event_TablaMouseClicked

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
            Cliente objCliente = new Cliente();

            int identificador = objClienteBL.Listado().get(row).getIntCedula();

            Nombretxt.setText(objClienteBL.ConsultaPorID(identificador).getStrNombre());
            Apellido1txt.setText(objClienteBL.ConsultaPorID(identificador).getStrApellido1());
            Apellido2txt.setText(objClienteBL.ConsultaPorID(identificador).getStrApellido2());

            String fechaNacimiento = objClienteBL.ConsultaPorID(identificador).getClassFechaNacimiento().toString();
            int dia = objClienteBL.ConsultaPorID(identificador).getClassFechaNacimiento().getIntDia();
            int mes = objClienteBL.ConsultaPorID(identificador).getClassFechaNacimiento().getIntMes() - 1;
            int anno = objClienteBL.ConsultaPorID(identificador).getClassFechaNacimiento().getIntAno();

            Calendar objCalendar = Calendar.getInstance();
            objCalendar.set(anno, mes, dia);
            FechaNacimientosc.setCalendar(objCalendar);

            Cedulatxt.setText(String.valueOf(objClienteBL.ConsultaPorID(identificador).getIntCedula()));
            Consecutivolbl.setText(String.valueOf(objClienteBL.ConsultaPorID(identificador).getIntCarnet()));
            Direcciontxt.setText(objClienteBL.ConsultaPorID(identificador).getClassDireccion().getStrDetalle());
            Provinciabx.setSelectedItem(objClienteBL.ConsultaPorID(identificador).getClassDireccion().getStrProvincia());
            Cantonbx.setSelectedItem(objClienteBL.ConsultaPorID(identificador).getClassDireccion().getStrCanton());
            Distritobx.setSelectedItem(objClienteBL.ConsultaPorID(identificador).getClassDireccion().getStrDistrito());

            if ("Masculino".equals(objClienteBL.ConsultaPorID(identificador).getStrGenero())) {
                setButtonGroup("Masculino", Generogb.getElements());
            } else {
                setButtonGroup("Femenino", Generogb.getElements());
            }

            ProfesionesList.setSelectedItem(objClienteBL.ConsultaPorID(identificador).getStrProfesion());

            if ("Activo".equals(objClienteBL.ConsultaPorID(identificador).getStrEstado())) {
                setButtonGroup("Activo", Estadogb.getElements());
            } else {
                setButtonGroup("Inactivo", Estadogb.getElements());
            }

            ConsultarRegistros();

        } catch (Exception ex) {
            Logger.getLogger(VentanaInternaEmpleados.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_CambiarbtnMouseClicked

    private void NuevobtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NuevobtnMouseClicked
        try {
            Regresarbtn.setVisible(true);
            Ingresartbn.setVisible(true);
            Buscarbtn.setVisible(false);

            Eliminarbtn.setVisible(false);
            Cambiarbtn.setVisible(false);
            Nuevobtn.setVisible(false);

            int consecutivo = objClienteBL.Consecutivo() + 1;
            Consecutivolbl.setText(String.valueOf(consecutivo));

            MostrarDatos();

        } catch (Exception ex) {
            Logger.getLogger(VentanaInternaClientes.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_NuevobtnMouseClicked

    private void RegresarbtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RegresarbtnMouseClicked
        LimpiarRegistros();

        Eliminarbtn.setVisible(true);
        Cambiarbtn.setVisible(true);
        Nuevobtn.setVisible(true);
        Buscarbtn.setVisible(true);

        Ingresartbn.setVisible(false);
        Guardarbtn.setVisible(false);
        Regresarbtn.setVisible(false);
        Filtradobx.setVisible(false);
        Busquedatxt.setVisible(false);
        Filtradobx.setSelectedIndex(0);

        try {
            ConsultarRegistros();

        } catch (Exception ex) {
            Logger.getLogger(VentanaInternaClientes.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        OcultarDatos();
    }//GEN-LAST:event_RegresarbtnMouseClicked

    private void GuardarbtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GuardarbtnMouseClicked
        Direccion objDireccion = new Direccion();
        Cliente objCliente = new Cliente();
        FechaAnos objFechaAnos = null;
        FechaHora objFechaHora = new FechaHora();
        String StrFechaSistema = objFechaHora.Fecha();

        if (FechaNacimientosc.getCalendar() != null) {

            int dia = FechaNacimientosc.getCalendar().get(Calendar.DAY_OF_MONTH);
            int mes = FechaNacimientosc.getCalendar().get(Calendar.MONTH) + 1;
            int anno = FechaNacimientosc.getCalendar().get(Calendar.YEAR);

            objFechaAnos = new FechaAnos(dia, mes, anno);

            if (objFechaAnos != null) {

                try {
                    int annos = ContarDias(StrFechaSistema, objFechaAnos.toString());
                    int mayorEdad = annos / 365;

                    if (18 > mayorEdad) {
                        JOptionPane.showMessageDialog(this, "El cliente es menor de Edad", "Error", 2);
                    } else {

                        objCliente.setClassFechaNacimiento(objFechaAnos);
                        String provincia = (String) Provinciabx.getSelectedItem();
                        String canton = (String) Cantonbx.getSelectedItem();
                        String distrito = (String) Distritobx.getSelectedItem();
                        objDireccion.setStrProvincia(provincia);
                        objDireccion.setStrCanton(canton);
                        objDireccion.setStrDistrito(distrito);
                        objCliente.setIntCarnet(Integer.parseInt(Consecutivolbl.getText()));
                        String profesion = (String) ProfesionesList.getSelectedItem();
                        objCliente.setStrProfesion(profesion);

                        if (Nombretxt.getText().equals("")) {
                            JOptionPane.showMessageDialog(this, "Espacio de nombre vacío", "Error", 2);
                        } else {
                            objCliente.setStrNombre(Nombretxt.getText());
                            if (Apellido1txt.getText().equals("")) {
                                JOptionPane.showMessageDialog(this, "Espacio de primer Apellido vacío", "Error", 2);
                            } else {
                                objCliente.setStrApellido1(Apellido1txt.getText());
                                if (Apellido2txt.getText().equals("")) {
                                    JOptionPane.showMessageDialog(this, "Espacio de segundo Apellido vacío", "Error", 2);
                                } else {
                                    objCliente.setStrApellido2(Apellido2txt.getText());
                                    if (Cedulatxt.getText().equals("")) {
                                        JOptionPane.showMessageDialog(this, "Espacio de Cedula vacío", "Error", 2);
                                    } else {
                                        objCliente.setIntCedula(Integer.parseInt(Cedulatxt.getText()));
                                        if (Direcciontxt.getText().equals("")) {
                                            JOptionPane.showMessageDialog(this, "Espacio de Dirección vacío", "Error", 2);
                                        } else {
                                            objDireccion.setStrDetalle(Direcciontxt.getText());
                                            objCliente.setClassDireccion(objDireccion);
                                            Masculinobx.setActionCommand(Masculinobx.getText());
                                            Femeninobx.setActionCommand(Femeninobx.getText());
                                            if (Masculinobx.isSelected() == false & Femeninobx.isSelected() == false) {
                                                JOptionPane.showMessageDialog(this, "Selección de genero vacío", "Error", 2);
                                            } else {
                                                objCliente.setStrGenero(Generogb.getSelection().getActionCommand());
                                                Activocb.setActionCommand(Activocb.getText());
                                                Inactivocb.setActionCommand(Inactivocb.getText());
                                                if (Activocb.isSelected() == false & Inactivocb.isSelected() == false) {
                                                    JOptionPane.showMessageDialog(this, "Selección de estado vacío", "Error", 2);
                                                } else {
                                                    objCliente.setStrEstado(Estadogb.getSelection().getActionCommand());
                                                    try {
                                                        objClienteBL.Actualizar(objCliente);
                                                        objClienteBL.grabarArchivoCliente();
                                                        ConsultarRegistros();
                                                        LimpiarRegistros();
                                                        RegresarMenuPrincipal();
                                                        OcultarDatos();
                                                    } catch (Exception ex) {
                                                        Logger.getLogger(VentanaInternaEmpleados.class
                                                                .getName()).log(Level.SEVERE, null, ex);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                } catch (ParseException ex) {
                    Logger.getLogger(VentanaInternaClientes.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(VentanaInternaEmpleados.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BuscarbtnMouseClicked

    private void BusquedatxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BusquedatxtKeyTyped
        Busquedatxt.addKeyListener(new KeyAdapter() {
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

    private void ProvinciabxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProvinciabxActionPerformed
        SeleccionLugares();
    }//GEN-LAST:event_ProvinciabxActionPerformed

    private void CantonbxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CantonbxActionPerformed
        Distritos objDistritos = new Distritos();
        String canton = (String) Cantonbx.getSelectedItem();
        ArrayList<String> listaDistritos = objDistritos.SeleccionDistritos(canton);
        String vectorDistritos[] = objDistritos.vectorDistritos(listaDistritos);
        DefaultComboBoxModel distritos = new DefaultComboBoxModel(vectorDistritos);
        Distritobx.setModel(distritos);
    }//GEN-LAST:event_CantonbxActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton Activocb;
    private javax.swing.JLabel Apellido1lbl;
    private javax.swing.JTextField Apellido1txt;
    private javax.swing.JLabel Apellido2lbl;
    private javax.swing.JTextField Apellido2txt;
    private javax.swing.JButton Buscarbtn;
    private javax.swing.JTextField Busquedatxt;
    private javax.swing.JButton Cambiarbtn;
    private javax.swing.JComboBox<String> Cantonbx;
    private javax.swing.JLabel Cantonlbl;
    private javax.swing.JLabel Carnetlbl;
    private javax.swing.JLabel Cedulalbl;
    private javax.swing.JTextField Cedulatxt;
    private javax.swing.JLabel Consecutivolbl;
    private javax.swing.JLabel Direccionlbl;
    private javax.swing.JTextField Direcciontxt;
    private javax.swing.JComboBox<String> Distritobx;
    private javax.swing.JLabel Distritolbl;
    private javax.swing.JButton Eliminarbtn;
    private javax.swing.ButtonGroup Estadogb;
    private javax.swing.JLabel Estadolbl;
    private com.toedter.calendar.JDateChooser FechaNacimientosc;
    private javax.swing.JLabel Fechalbl;
    private javax.swing.JRadioButton Femeninobx;
    private javax.swing.JComboBox<String> Filtradobx;
    private javax.swing.ButtonGroup Generogb;
    private javax.swing.JLabel Generolbl;
    private javax.swing.JButton Guardarbtn;
    private javax.swing.JRadioButton Inactivocb;
    private javax.swing.JButton Ingresartbn;
    private javax.swing.JRadioButton Masculinobx;
    private javax.swing.JLabel Nombrelbl;
    private javax.swing.JTextField Nombretxt;
    private javax.swing.JButton Nuevobtn;
    private javax.swing.JComboBox<String> ProfesionesList;
    private javax.swing.JLabel Profesionlbl;
    private javax.swing.JComboBox<String> Provinciabx;
    private javax.swing.JLabel Provincialbl;
    private javax.swing.JButton Regresarbtn;
    private javax.swing.JTable Tabla;
    private javax.swing.JLabel Titulolbl;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
