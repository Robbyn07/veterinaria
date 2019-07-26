/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import conexionbd.Conexion;
import conexionbd.ControladorCita;
import conexionbd.ControladorCliente;
import conexionbd.ControladorDiagnostico;
import conexionbd.ControladorEmpleado;
import conexionbd.ControladorMascota;
import conexionbd.ControladorRecetaCabecera;
import conexionbd.ControladorRecetaDetalle;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import modelo.Cita;
import modelo.Cliente;
import modelo.Diagnostico;
import modelo.Empleado;
import modelo.Mascota;
import modelo.RecetaCabecera;
import modelo.RecetaDetalle;

/**
 *
 * @author Usuario
 */
public class VDiagnostico extends JInternalFrame implements ActionListener{
   
    Calendar fecha;
    Conexion con;
    ControladorEmpleado cem;
    ControladorCliente cc;
    ControladorMascota cm;
    ControladorCita cct;
    ControladorDiagnostico cd;
    ControladorRecetaCabecera crc;
    ControladorRecetaDetalle crd;
    Empleado emp;
    
    public VDiagnostico(Conexion con,ControladorEmpleado cem,ControladorCliente cc,
            ControladorMascota cm,ControladorCita cct,ControladorDiagnostico cd,
            ControladorRecetaCabecera crc,ControladorRecetaDetalle crd,Empleado emp){
        this.con = con;
        this.cem = cem;
        this.cc = cc;
        this.cm = cm;
        this.cct = cct;
        this.cd = cd;
        this.crc = crc;
        this.crd = crd;
        this.emp = emp;
        initComponentes();
        ventanaDiagnostico();
           
    }
    
    private void initComponentes() {
        setSize(1000, 700);
        setTitle("Diagnosticos");
        setClosable(true);
    }
    
    DefaultTableModel dt;
    private JTable tb1;
    private JScrollPane scr1;
    private JScrollPane scr2;
    private JScrollPane scr3;
    private boolean[] editable = {false, false, false};
    
    private JButton b1;
    private JButton b2;
    private JButton b3;
    private JButton b4;
    private JLabel l2;
    private JLabel l3;
    private JLabel l4;
    private JLabel l5;
    private JLabel l6;
    private JLabel l7;
    private JLabel l8;
    private JLabel l9;
    private JTextField t1;
    private JTextField t2;
    private JTextField t3;
    private JTextArea a1;
    private JTextArea a2;
    
    private String nombreC = " ";
    private String fechaC = " ";
    private String horaC = " ";
    private String nombreM = " ";
    private String especie = " ";
    private String raza = " ";
    private String genero = " ";
    private String color = " ";
    private String anioN = " ";

    private void ventanaDiagnostico() {
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
		
        //Panel Ficha Médica
        JPanel p1 = new JPanel();
        p1.setLayout(new BorderLayout());
        Border loweredlevel = BorderFactory.createLoweredBevelBorder();
        TitledBorder titulo1;
        titulo1 = BorderFactory.createTitledBorder("Paciente");
        titulo1.setTitlePosition(TitledBorder.ABOVE_TOP);
        p1.setBorder(titulo1);
        
        JPanel p2 = new JPanel();
        p2.setLayout(new GridBagLayout());
        GridBagConstraints g1 = new GridBagConstraints();
        
        JLabel l1 = new JLabel("Número de Cita:");
        g1.gridx=0;
        g1.gridy=0;
        p2.add(l1, g1);
        
        t1 = new JTextField(5);
        g1.gridx=1;
        g1.gridy=0;
        p2.add(t1, g1);
       
        b1 = new JButton("Buscar");
        b1.addActionListener(this);
        b1.setActionCommand("buscar");
        g1.gridx=2;
        g1.gridy=0;
        p2.add(b1, g1);
        
    
        JPanel p3 = new JPanel();
        p3.setLayout(new GridBagLayout());
        GridBagConstraints g2 = new GridBagConstraints();
        
        l2 = new JLabel(fechaC);
        g2.gridx=0;
        g2.gridy=0;
        p3.add(l2, g2);
        
        l3 = new JLabel(horaC);
        g2.gridx=1;
        g2.gridy=0;
        p3.add(l3, g2);
        
        JLabel l10 = new JLabel(" ");
        g2.gridx=0;
        g2.gridy=1;
        g2.gridwidth = 2;
        p3.add(l10, g2);

        l4 = new JLabel(nombreC);
        g2.gridx=0;
        g2.gridy=2;
        g2.gridwidth = 2;
        p3.add(l4, g2);
        
        JLabel l11 = new JLabel(" ");
        g2.gridx=0;
        g2.gridy=3;
        g2.gridwidth = 2;
        p3.add(l11, g2);
        
        l5 = new JLabel(nombreM);
        g2.gridx=0;
        g2.gridy=4;
        g2.gridwidth = 2;
        p3.add(l5, g2);
        
        l6 = new JLabel(especie);
        g2.gridx=0;
        g2.gridy=5;
        g2.gridwidth = 2;
        p3.add(l6, g2);

        l6 = new JLabel(raza);
        g2.gridx=0;
        g2.gridy=6;
        g2.gridwidth = 2;
        p3.add(l6, g2);
        
        l7 = new JLabel(genero);
        g2.gridx=0;
        g2.gridy=7;
        g2.gridwidth = 2;
        p3.add(l7, g2);
        
        l8 = new JLabel(color);
        g2.gridx=0;
        g2.gridy=8;
        g2.gridwidth = 2;
        p3.add(l8, g2);

        l9 = new JLabel(anioN);
        g2.gridx=0;
        g2.gridy=9;
        g2.gridwidth = 2;
        p3.add(l9, g2);
        
        p1.add(p2, BorderLayout.NORTH);
        p1.add(p3, BorderLayout.CENTER);
        
        //Panel que contiene a Diagnóstico y Receta
        JPanel p4 = new JPanel();
        p4.setLayout(new BorderLayout());
        
        // Panel Diagnóstico
        JPanel p5 = new JPanel();
        p5.setLayout(new GridBagLayout());
        GridBagConstraints g3 = new GridBagConstraints();
        TitledBorder titulo2;
        titulo2 = BorderFactory.createTitledBorder("Diagnóstico");
        titulo2.setTitlePosition(TitledBorder.ABOVE_TOP);
        p5.setBorder(titulo2);

        g3.insets = new Insets(1,1,1,1);        
        g3.weightx = 0.1; 
        
        a1= new JTextArea();
        scr1 = new JScrollPane(a1);
        a1.setLineWrap(true);
        a1.setWrapStyleWord(true);
        g3.gridx = 0;
        g3.gridy = 0;
        g3.ipadx = 610;
        g3.ipady = 200;
        p5.add(scr1,g3);

        // Panel Receta
        JPanel p6 = new JPanel();
        p6.setLayout(new BorderLayout());
        TitledBorder titulo3;
        titulo3 = BorderFactory.createTitledBorder("Receta");
        titulo3.setTitlePosition(TitledBorder.ABOVE_TOP);
        p6.setBorder(titulo3);
        
        JPanel p8 = new JPanel();
        p8.setLayout(new GridBagLayout());
        GridBagConstraints g4 = new GridBagConstraints();
        
        JLabel l12 = new JLabel("Nombre: ");
        g4.gridx=0;
        g4.gridy=0;
        p8.add(l12, g4);
        
        t2 = new JTextField(10);
        g4.gridx=1;
        g4.gridy=0;
        p8.add(t2, g4);
        
        JLabel l13 = new JLabel("Cantidad: ");
        g4.gridx=0;
        g4.gridy=1;
        p8.add(l13, g4);
        
        t3 = new JTextField(10);
        g4.gridx=1;
        g4.gridy=1;
        p8.add(t3, g4);
        
        JLabel l14 = new JLabel("Dosificación: ");
        g4.gridx=0;
        g4.gridy=2;
        p8.add(l14, g4);
        
        b4 = new JButton("Agregar");
        g4.gridx=0;
        g4.gridy=3;
        g4.gridwidth = 2;
        b4.addActionListener(this);
        b4.setActionCommand("agregarR");
        p8.add(b4, g4);
        
        a2 = new JTextArea();
        a2.setLineWrap(true);
        a2.setWrapStyleWord(true);
        scr2 = new JScrollPane(a2);
        g4.gridx=1;
        g4.gridy=2;
        g4.ipadx = 50;
        g4.ipady = 20;
        g4.fill = GridBagConstraints.BOTH;
        p8.add(scr2, g4);
        

        JPanel p9 = new JPanel();
        p9.setLayout(new BorderLayout());
        
        dt = new DefaultTableModel(new String[]{"Nombre","Cantidad","Dosificación"}, 0) {
 
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Boolean.class    
            };
 
            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
            
            @Override
            public boolean isCellEditable(int row, int column){
                return editable[column];
            }
        };
        
        tb1 = new JTable();
        tb1.setModel(dt);
        scr3 = new JScrollPane(tb1);
        p9.add(scr3, BorderLayout.CENTER);

        p6.add(p8, BorderLayout.NORTH);
        p6.add(p9, BorderLayout.CENTER);
        
        p4.add(p5, BorderLayout.NORTH);
        p4.add(p6, BorderLayout.CENTER);
        
        //Panel de botones
        JPanel p7 = new JPanel();
        p7.setLayout(new FlowLayout());

        b2 = new JButton("Volver");
        b2.addActionListener(this);
        b2.setActionCommand("volver");
        p7.add(b2);
        
        b3 = new JButton("Aceptar");
        b3.addActionListener(this);
        b3.setActionCommand("aceptar");
        p7.add(b3);  
        
        cp.add(p1, BorderLayout.WEST);
        cp.add(p4, BorderLayout.CENTER);
        cp.add(p7, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        switch (comando) {
            case "buscar":
                buscarCita();
                break;
                
            case "agregar":
                agregarReceta();
                break;
            
            case "volver":
                this.setVisible(false);
                break;

            case "aceptar":
                agregarDiagnostico();
                break;

            default:
                break;
        }
    }
    
    int numeroC;
    int mascotaI;
    int clienteI;
    Cliente cli;
    Mascota mas;
    Cita cit;
    
    public void buscarCita(){
        
        try {
            numeroC = Integer.parseInt(t1.getText());
            cit = cct.citBuscar(con, numeroC);
            
            mascotaI = cct.buscarMascotaId(con, numeroC);
            mas = cm.masBuscar(con, mascotaI);
            
            clienteI = cm.buscarClienteId(con, mascotaI);
            cli = cc.cliBuscarId(con, clienteI);
            
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            fechaC = formato.format(cit.getCitaFecha());
            formato = new SimpleDateFormat("HH:mm.ss");
            horaC = formato.format(cit.getCitaFecha());
            
            nombreC = cli.getPersonaNombre() +" "+ cli.getPersonaApellido();
            nombreM = mas.getMascotaNombre();
            especie = mas.getEspecie().getEspecieNombre();
            raza = mas.getRaza().getRazaNombre();
            genero = mas.getMascotaGenero();
            color = mas.getMascotaColor();
            anioN = mas.getAnio().toString();
                    
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }   
    }
    
    String recetaNombre;
    String recetaCantidad;
    String recetaDosificacion;
    String fechaRC;
    Date fechaFinal;
    
    int dia;
    int mes;
    int anio;
    
    public void agregarReceta(){
        anio = fecha.get(Calendar.YEAR);
        mes = fecha.get(Calendar.MONTH);
        dia = fecha.get(Calendar.DAY_OF_MONTH);
        
        try {
            String diaS = Integer.toString(dia);
            String mesS = Integer.toString(mes);
            String anioS = Integer.toString(anio);
            fechaRC = diaS +"/"+ mesS +"/"+ anioS;

            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            java.util.Date dateN = formato.parse(fechaRC);
            fechaFinal = new java.sql.Date(dateN.getTime());

            recetaNombre = t2.getText();
            recetaCantidad = t3.getText();
            recetaDosificacion = a2.getText();

            Object fila[] = new Object[3];
            fila[0] = recetaNombre;
            fila[1] = recetaCantidad;
            fila[2] = recetaDosificacion;
        
        dt.addRow(fila);
        
        } catch (ParseException e) {
            e.printStackTrace();
        }
               
    }
    
    String diagnostico;
    Diagnostico diag; 
    RecetaCabecera rc;
    RecetaDetalle rd;
    
    public void agregarDiagnostico(){
        //Preguntar si es necesario sumarle 1 los id
        int idDiagnostico = cd.obtenerId(con) + 1;
        int idRecetaC = crc.obtenerId(con) + 1;
        diagnostico = a1.getText();
        
        diag = new Diagnostico();
        diag.setDiagnosticoNomEnfermedad(diagnostico);
        diag.setEmpleado(emp);
        cd.diaAgregar(con, diag, numeroC, emp);
               
        rc = new RecetaCabecera();
        rc.setRecetaCabeceraFecha(fechaFinal);
        crc.recCabAgregar(con, rc, idDiagnostico);
        
        for(int i = 0; i < tb1.getRowCount(); i++){
            rd = new RecetaDetalle();
            recetaNombre = (String) tb1.getValueAt(i, 0);
            rd.setRecetaDetalleNombre(recetaNombre);
            recetaCantidad = (String) tb1.getValueAt(i, 1);
            rd.setRecetaDetalleCantidad(recetaCantidad);
            recetaDosificacion = (String) tb1.getValueAt(i, 2);
            rd.setRecetaDetalleDosificacion(recetaDosificacion);
            
            crd.recDetAgregar(con, rd, idRecetaC);  
        }
        
    }
}
