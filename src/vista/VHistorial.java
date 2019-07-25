/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import javax.swing.BorderFactory;
import javax.swing.*;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Usuario
 */
public class VHistorial extends JInternalFrame implements ActionListener{
    
    private String nombre = "Roberto";
    
    public VHistorial(){
        initComponentes();
        ventanaHistorial();
    }
    
    public void initComponentes(){
        setSize(700,700);
        setClosable(true);
        setTitle("Ventana Buscar Historial");
    }
    
    private JButton b1;
    private JButton b2;
    private JButton b3;
    DefaultTableModel dt;
    private JTable tb1;
    private JScrollPane scr;
    private boolean[] editable = {false,false,false,true};
    
    public void ventanaHistorial(){
        Container cp = getContentPane();
	cp.setLayout(new java.awt.GridBagLayout());
        
	//Panel 1
	JPanel panel1 = new JPanel();
	panel1.setLayout(new java.awt.GridBagLayout());
                
	GridBagConstraints gbcPanel1 = new GridBagConstraints();
	gbcPanel1.gridx=0;
        gbcPanel1.gridy=0;
        gbcPanel1.gridwidth=4;
		
        cp.add(panel1, gbcPanel1);
                
        GridBagConstraints gbP1 = new GridBagConstraints();
		
        JLabel l1 = new JLabel("Codigo de la Mascota: ");
        gbP1.gridx =0;
        gbP1.gridy =0;
        panel1.add(l1, gbP1);
        
        JTextField t1 = new JTextField(5);
        gbP1.gridx =1;
        gbP1.gridy =0;
        panel1.add(t1, gbP1);
        
        
        b1 = new JButton("Buscar");
        b1.addActionListener(this);
        b1.setActionCommand("buscar");
        gbP1.gridx =2;
        gbP1.gridy =0;
        panel1.add(b1, gbP1);
        
        
        // Panel 2
        JPanel panel2 = new JPanel();
		panel2.setLayout(new java.awt.GridBagLayout());
                javax.swing.border.Border loweredlevel = BorderFactory.createLoweredBevelBorder();
                TitledBorder titulo1;
                titulo1 = BorderFactory.createTitledBorder("Diagnosticos de la Mascota");
                titulo1.setTitlePosition(TitledBorder.ABOVE_TOP);
                panel2.setBorder(titulo1);
                
		GridBagConstraints gbcPanel2 = new GridBagConstraints();
		gbcPanel2.gridx=0;
		gbcPanel2.gridy=2;
                gbcPanel2.gridwidth =2;
		
		cp.add(panel2, gbcPanel2);
		
		GridBagConstraints gbP2 = new GridBagConstraints();
        
        dt = new DefaultTableModel(new String[]{"Fecha","Mascota", "ID-Diagnostico","Check"}, 0) {
 
            Class[] types = new Class[]{
                java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,
                java.lang.Boolean.class
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
        gbP2.gridx = 0;
        gbP2.gridy = 5;
        gbP2.ipadx = 200;
        gbP2.ipady = 100;
        gbP2.gridwidth =3;
        panel2.add(scr, gbP2);
        
        // Panel 3
         JPanel panel3 = new JPanel();
		panel3.setLayout(new java.awt.GridBagLayout());
                
		GridBagConstraints gbcPanel3 = new GridBagConstraints();
		gbcPanel3.gridx=0;
		gbcPanel3.gridy=4;
                gbcPanel3.gridwidth =2;
		
		cp.add(panel3, gbcPanel3);
		
		GridBagConstraints gbP3 = new GridBagConstraints();
                
		b2= new JButton("Buscar");
		gbP3.gridx=0;
		gbP3.gridy=1;
                b2.addActionListener(this);
                b2.setActionCommand("buscar1");
		panel3.add(b2, gbP3);
                
                b3= new JButton("Volver");
		gbP3.gridx=1;
		gbP3.gridy=1;
                b3.addActionListener(this);
                b3.setActionCommand("volver");
		panel3.add(b3, gbP3);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        System.out.println("Comando: " + comando);
        
        switch(comando){
            case "buscar":
                break;
            
            case "volver":
                setVisible(false);
                
                break;
                
            case "buscar1":
                llamarVentanaBusquedaHistorial(getDesktopPane());
                break;
        }
    }
    

    private void llamarVentanaBusquedaHistorial(JDesktopPane escritorioM) {
        VBusquedaHistorial vBH = new  VBusquedaHistorial();
        vBH.setVisible(true);
        this.setVisible(false);
        escritorioM.add(vBH);
        try {
			vBH.setSelected(true);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
        }
}
