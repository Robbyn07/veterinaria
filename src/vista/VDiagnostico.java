/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Usuario
 */
public class VDiagnostico extends JInternalFrame implements ActionListener{
   
    private JButton ok;
    private JButton ok1;
    private JButton b1;
    private JButton b2;
    private JTextArea a1;
    DefaultTableModel dt;
    private JTable tb1;
    private JTable tabla;
    private JScrollPane scr;
    private boolean[] editable = {false,true};
    
    public VDiagnostico(){
        initComponentes();
        ventanaDiagnostico();
           
    }
    private void initComponentes() {
        setSize(1100, 600);
        setTitle("Diagnosticos");
        setClosable(true);
        setMaximizable(true);
    }

    private void ventanaDiagnostico() {
        Container cp = getContentPane();
        cp.setLayout(new GridBagLayout());
		
        //Panel 1
        JPanel p1 = new JPanel();
        p1.setLayout(new BorderLayout());
        Border loweredlevel = BorderFactory.createLoweredBevelBorder();
        TitledBorder titulo1;
        titulo1 = BorderFactory.createTitledBorder("Paciente");
        titulo1.setTitlePosition(TitledBorder.ABOVE_TOP);
        p1.setBorder(titulo1);
        
        GridBagConstraints pa1 = new GridBagConstraints();
        
        JPanel p2 = new JPanel();
        p2.setLayout(new GridBagLayout());
        GridBagConstraints g1 = new GridBagConstraints();
        
        JLabel l1 = new JLabel("Cédula:");
        g1.gridx=0;
        g1.gridy=0;
        p2.add(l1, g1);
        
        JTextField t1 = new JTextField(12);
        g1.gridx=1;
        g1.gridy=0;
        p2.add(t1, g1);
        
        b2 = new JButton("Buscar");
        b2.addActionListener(this);
        b2.setActionCommand("buscar");
        g1.gridx=3;
        g1.gridy=0;
        p2.add(b2, g1);
        
    
        JPanel p3 = new JPanel();
        p3.setLayout(new GridBagLayout());
        
        dt = new DefaultTableModel(new String[]{"Nombre","Check"}, 0) {
 
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
        
        //Método para listar los datos
        //mostrar();
        
        tb1.setModel(dt);
        scr = new JScrollPane(tb1);
        g1.gridx = 0;
        g1.gridy = 0;
        g1.ipadx = 300;
        g1.ipady = 300;
        p3.add(scr, g1);
        
        p1.add(p2, BorderLayout.NORTH);
        p1.add(p3, BorderLayout.CENTER);
        
        // Panel 2
        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridBagLayout());
        TitledBorder titulo2;
        titulo2 = BorderFactory.createTitledBorder("Diagnostico");
        titulo2.setTitlePosition(TitledBorder.ABOVE_TOP);
        panel2.setBorder(titulo2);

        GridBagConstraints gbDiagnostico = new GridBagConstraints();

        a1= new JTextArea();
        gbDiagnostico.gridx=0;
        gbDiagnostico.gridy=0;
        gbDiagnostico.ipadx=300;
        gbDiagnostico.ipady=300;
        panel2.add(a1, gbDiagnostico);

        // Panel 3
        JPanel panel3 = new JPanel();
        panel3.setLayout(new java.awt.GridBagLayout());
        TitledBorder titulo3;
        titulo3 = BorderFactory.createTitledBorder("Receta");
        titulo3.setTitlePosition(TitledBorder.ABOVE_TOP);
        panel3.setBorder(titulo3);

        GridBagConstraints gbReceta = new GridBagConstraints();

        tabla = new JTable(10,3);
        gbReceta.gridx=0;
        gbReceta.gridy=0;
        gbReceta.ipadx=300;
        gbReceta.ipady=300;

        panel3.add(tabla,gbReceta);

        tabla.setValueAt("FECHA", 0, 0);
        tabla.setValueAt("MASCOTA", 0, 1);
        tabla.setValueAt("ID-DIAGNOSTICO", 0, 2); 

         // Panel 4
        JPanel panel4 = new JPanel();
        panel4.setLayout(new FlowLayout());

        b1 = new JButton("Volver");
        b1.addActionListener(this);
        b1.setActionCommand("volver");
        panel4.add(b1);
        
        ok = new JButton("Aceptar");
        ok.addActionListener(this);
        ok.setActionCommand("aceptar");
        panel4.add(ok);  
        
        
        pa1.gridx=0;
        pa1.gridy=0;
        pa1.fill = GridBagConstraints.BOTH;
        cp.add(p1, pa1);
        
        pa1.gridx=1;
        pa1.gridy=0;
        pa1.fill = GridBagConstraints.BOTH;
        cp.add(panel2, pa1);
        
        pa1.gridx=3;
        pa1.gridy=0;
        pa1.fill = GridBagConstraints.BOTH;
        cp.add(panel3, pa1);
        
        pa1.gridx=0;
        pa1.gridy=1;
        pa1.gridwidth = 3;
        pa1.fill = GridBagConstraints.HORIZONTAL;
        cp.add(panel4, pa1);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        switch (comando) {

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
}
