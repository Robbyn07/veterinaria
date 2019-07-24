/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import conexionbd.Conexion;
import conexionbd.ControladorEmpleado;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Rakrad7101
 */
public class VListarEmpleado extends JInternalFrame implements ActionListener{
    
    Conexion con;
    ControladorEmpleado cem;
    
    public VListarEmpleado(Conexion con,ControladorEmpleado cem){
        this.con = con;
        this.cem = cem;
        initComponentes();
        ventanaListarEmp();
    }
    
    public void initComponentes(){
        setSize(800,400);
        setTitle("Listar Empleados");
        setClosable(true);
    }
    
    private JButton b1;
    private JButton b2;
    DefaultTableModel dt;
    private JTable tb1;
    private JScrollPane scr;
    private boolean[] editable = {false,false,false,false,false};
    
    public void ventanaListarEmp(){
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        
        JPanel pa = new JPanel();
        pa.setLayout(new BorderLayout()); 
        
        dt = new DefaultTableModel(new String[]{"Cédula","Nombre","Teléfono",
            "Cargo","Correo","Dirección"}, 0) {
 
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
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
        pa.add(scr, BorderLayout.NORTH);
        
        JPanel pa2 = new JPanel();
        pa2.setLayout(new FlowLayout()); 
        b1 = new JButton("Volver");
        b1.addActionListener(this);
        b1.setActionCommand("volver");
        pa2.add(b1);
        
        b2 = new JButton("Listar");
        b2.addActionListener(this);
        b2.setActionCommand("mostrar");
        pa2.add(b2);
        
        cp.add(pa, BorderLayout.CENTER);
        cp.add(pa2, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        System.out.println("Comando: " + comando);
        
        switch(comando){
            case "volver":
                setVisible(false);
                break;
                
            case "mostrar":
                mostrar();
                break;    
                
        }
    }

    public void mostrar(){
        
        int n = cem.empObtener(con).size();
        
        for(int i = 0; i < n; i++){
            Object fila[] = new Object[6];
            fila[0] = cem.empObtener(con).get(i).getPersonaCedula();
            fila[1] = cem.empObtener(con).get(i).getPersonaNombre() + " " + 
                    cem.empObtener(con).get(i).getPersonaApellido();
            fila[2] = cem.empObtener(con).get(i).getPersonaTelefono();
            fila[3] = cem.empObtener(con).get(i).getEmpleadoPermiso();
            fila[4] = cem.empObtener(con).get(i).getPersonaEmail();
            fila[5] = cem.empObtener(con).get(i).getPersonaDireccion();

            dt.addRow(fila);
        }
       
    }
}
