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
import conexionbd.ControladorEspecie;
import conexionbd.ControladorMascota;
import conexionbd.ControladorRaza;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
import modelo.Mascota;

/**
 *
 * @author Usuario
 */
public class VDiagnostico extends JInternalFrame implements ActionListener{
   
    Conexion con;
    ControladorEmpleado cem;
    ControladorCliente cc;
    ControladorMascota cm;
    ControladorEspecie ces;
    ControladorRaza cr;
    ControladorCita cct;
    ControladorDiagnostico cd;
    
    public VDiagnostico(Conexion con,ControladorEmpleado cem,ControladorCliente cc,
            ControladorMascota cm,ControladorEspecie ces,ControladorRaza cr,
            ControladorCita cct,ControladorDiagnostico cd){
        this.con = con;
        this.cem = cem;
        this.cc = cc;
        this.cm = cm;
        this.ces = ces;
        this.cr = cr;
        this.cct = cct;
        this.cd = cd;
        initComponentes();
        ventanaDiagnostico();
           
    }
    private void initComponentes() {
        setSize(1300, 600);
        setTitle("Diagnosticos");
        setClosable(true);
    }
    
    DefaultTableModel dt;
    private JTable tb1;
    private JScrollPane scr;
    private boolean[] editable = {true, true, true};
    
    private JButton b1;
    private JButton b2;
    private JButton b3;
    private JLabel l2;
    private JLabel l3;
    private JLabel l4;
    private JLabel l5;
    private JLabel l6;
    private JLabel l7;
    private JLabel l8;
    private JLabel l9;
    private JTextField t1;
    private JTextArea a1;
    
    private String nombreC = "Robbyn Taurino Reyes Duchitanga";
    private String fechaC = "12/12/2000";
    private String horaC = "09:30";
    private String nombreM = "Tobi";
    private String especie = "Perro";
    private String raza = "Labrador";
    private String genero = "Macho";
    private String color = "Negro";
    private String anioN = "2012";

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
        
        JLabel l1 = new JLabel("# Cita:");
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
        
        // Panel Diagnóstico
        JPanel p4 = new JPanel();
        p4.setLayout(new BorderLayout());
        TitledBorder titulo2;
        titulo2 = BorderFactory.createTitledBorder("Diagnostico");
        titulo2.setTitlePosition(TitledBorder.ABOVE_TOP);
        p4.setBorder(titulo2);

        a1= new JTextArea();
        p4.add(a1, BorderLayout.CENTER);

        // Panel Receta
        JPanel p5 = new JPanel();
        p5.setLayout(new BorderLayout());
        TitledBorder titulo3;
        titulo3 = BorderFactory.createTitledBorder("Receta");
        titulo3.setTitlePosition(TitledBorder.ABOVE_TOP);
        p5.setBorder(titulo3);
        
        dt = new DefaultTableModel(new String[]{"Algo 1","Algo 2","Algo 3"}, 0) {
 
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
        scr = new JScrollPane(tb1);
        p5.add(scr, BorderLayout.CENTER);

         // Panel 4
        JPanel p6 = new JPanel();
        p6.setLayout(new FlowLayout());

        b2 = new JButton("Volver");
        b2.addActionListener(this);
        b2.setActionCommand("volver");
        p6.add(b2);
        
        b3 = new JButton("Aceptar");
        b3.addActionListener(this);
        b3.setActionCommand("aceptar");
        p6.add(b3);  
        

        cp.add(p1, BorderLayout.WEST);
        cp.add(p4, BorderLayout.CENTER);
        cp.add(p5, BorderLayout.EAST);
        cp.add(p6, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        switch (comando) {
            case "buscar":
                buscarCita();
                break;
            
            case "volver":
                this.setVisible(false);
                break;

            case "aceptar":
                JOptionPane.showMessageDialog(null, "Operación Exitosa");
                break;

            default:
                break;
        }
    }
    
    int numeroC;
    Cliente cli;
    Mascota mas;
    Cita cit;
    
    public void buscarCita(){
        
        try {
            numeroC = Integer.parseInt(t1.getText());
            cit = cct.citBuscar(con, numeroC);
            
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            fechaC = formato.format(cit.getCitaFecha());

            formato = new SimpleDateFormat("HH.mm.ss");
            horaC = formato.format(cit.getCitaFecha());
            
            
            
                    
        } catch (Exception e) {
            e.printStackTrace();
        }   
    }
}
