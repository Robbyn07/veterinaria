
package vista;

import conexionbd.Conexion;
import conexionbd.ControladorCaracter;
import conexionbd.ControladorCliente;
import conexionbd.ControladorDiagnostico;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Cita;
import modelo.Cliente;
import modelo.Diagnostico;
import modelo.Mascota;

/**
 *
 * @author Usuario
 */
public class VHistorial extends JInternalFrame implements ActionListener{
 
    Conexion con;
    ControladorCaracter cca;
    ControladorCliente cc;
    ControladorDiagnostico cd;
    
    public VHistorial(Conexion con,ControladorCaracter cca,ControladorCliente cc,
            ControladorDiagnostico cd){
        this.con = con;
        this.cca = cca;
        this.cc = cc;
        this.cd = cd;
        initComponentes();
        ventanaHistorial();
    }
    
    public void initComponentes(){
        setSize(700,700);
        setClosable(true);
        setTitle("Ventana Buscar Historial");
    }
    
    DefaultTableModel dt;
    private JTable tb1;
    private JScrollPane scr;
    private boolean[] editable = {false,false,false,true};
    
    private JButton b1;
    private JButton b2;
    private JButton b3;
    private JTextField t1;
    private JLabel l2;
    private JLabel l3;
    private JLabel l4;
    
    String cedula;
    String nombreC = "Nombre";
    String telefonoC = "Teléfono";
    String direccionC = "Dirección";
    
    public void ventanaHistorial(){
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
 
        JPanel p1 = new JPanel();
        GridBagConstraints g1 = new GridBagConstraints(); 
        p1.setLayout(new GridBagLayout());
        
        JLabel l1 = new JLabel("Cédula:");
        g1.gridx =0;
        g1.gridy =0;
        p1.add(l1, g1);
        
        t1 = new JTextField(12);
        g1.gridx =1;
        g1.gridy =0;
        p1.add(t1, g1);
        
        b1 = new JButton("Buscar");
        g1.gridx = 2;
        g1.gridy = 0;
        b1.addActionListener(this);
        b1.setActionCommand("buscar");
        p1.add(b1, g1);
        
        l2 = new JLabel(nombreC);
        g1.gridx =0;
        g1.gridy =1;
        p1.add(l2, g1);
        
        l3 = new JLabel(telefonoC);
        g1.gridx =1;
        g1.gridy =1;
        p1.add(l3, g1);
        
        l4 = new JLabel(direccionC);
        g1.gridx =2;
        g1.gridy =1;
        p1.add(l4, g1);
        
        JPanel p2 = new JPanel();
        p2.setLayout(new BorderLayout());
        
        
        dt = new DefaultTableModel(new String[]{"Fecha","Mascota","Diagnóstico","Check"}, 0) {
 
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
        p2.add(scr, BorderLayout.CENTER);
        
        JPanel p3 = new JPanel(); 
        p3.setLayout(new FlowLayout());
        
        b2 = new JButton("Volver");
        b2.addActionListener(this);
        b2.setActionCommand("volver");
        p3.add(b2);
        
        b3 = new JButton("Buscar");
        b3.addActionListener(this);
        b3.setActionCommand("buscarHistorial");
        p3.add(b3); 
        
        cp.add(p1, BorderLayout.NORTH);
        cp.add(p2, BorderLayout.CENTER);
        cp.add(p3, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        System.out.println("Comando: " + comando);
        
        switch(comando){
            case "buscar":
                buscarCliente();
                break;
            
            case "volver":
                setVisible(false);
                
                break;
                
            case "buscarHistorial":
                buscarHistorial();
                break;
        }
    }
    
    Cliente cli;
    
    public void buscarCliente(){
        cedula = t1.getText();
        cli = cc.cliBuscar(con, cedula);
        
        try {
            if(cca.verificarCedula(cedula) == true){
                nombreC = cli.getPersonaNombre();
                telefonoC = cli.getPersonaTelefono();
                direccionC = cli.getPersonaDireccion();

                mostrar();          

            }
        } catch (HeadlessException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"Verifique la cédula","Error",
                    JOptionPane.ERROR_MESSAGE);        
        }
    }
    
    ArrayList<Mascota> listaM;
    ArrayList<Cita> listaC;
    Mascota mas;
    Cita cit;
    
    String fecha;
    String mascota;
    int diagnostico;
    
    public void mostrar(){
        listaM = (ArrayList<Mascota>) cli.getMascotas();
        int n = listaM.size();
        
        for(int i = 0; i < n; i++){
            mas = listaM.get(i);
            
            if(mas.getCitas() != null){
                listaC = (ArrayList<Cita>) mas.getCitas();//mas.getCitas->lo mismo xd
                int m = listaC.size();

                for(int j = 0; j < m; j++){
                    cit = listaC.get(j);
                    
                    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                    fecha = formato.format(cit.getCitaFecha());
                    mascota = mas.getMascotaNombre();
                    diagnostico = cit.getDiagnostico().getDiagnosticoId();
                    
                    Object fila[] = new Object[4];
                    fila[0] = fecha;
                    fila[1] = mascota;
                    fila[2] = diagnostico;
                    fila[3] = false;
                    
                    dt.addRow(fila);
                    
                }  
            }
        }
    }
    
    int diagnosticoID;
    Diagnostico diag;
    
    public void buscarHistorial(){
        for(int i = 0; i < tb1.getRowCount(); i++){
            if((boolean)tb1.getValueAt(i,3) == true){
                diagnosticoID = (int) tb1.getValueAt(i, 2);
                
                diag = cd.diaBuscar(con, diagnosticoID);
                llamarVentanaBusquedaHistorial(getDesktopPane());
                break;
            }
        }  
    }
    
    
    private void llamarVentanaBusquedaHistorial(JDesktopPane escritorioM) {
        VBusquedaHistorial vBH = new  VBusquedaHistorial(con,cd,diag);
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
