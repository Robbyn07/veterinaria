
package vista;

import conexionbd.Conexion;
import conexionbd.ControladorCaracter;
import conexionbd.ControladorCita;
import conexionbd.ControladorCliente;
import conexionbd.ControladorMascota;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelo.Cliente;
import modelo.Mascota;

/**
 *
 * @author Rakrad7101
 */
public class VModificarCita extends JInternalFrame implements ActionListener{
    
    Conexion con;
    ControladorCaracter cca;
    ControladorCliente cc;
    ControladorMascota cm;
    ControladorCita cct;
    
    public VModificarCita(Conexion con,ControladorCaracter cca,ControladorCliente cc,
            ControladorMascota cm,ControladorCita cct){
        this.con = con;
        this.cca = cca;
        this.cc = cc;
        this.cm = cm;
        this.cct = cct;
        initComponentes();
        ventanaModificarC();
    }
    
    public void initComponentes(){
        setSize(800,400);
        setTitle("Modificar Cita");
    }
    
    DefaultTableModel dt;
    private JTable tb1;
    private JScrollPane scr;
    private boolean[] editable = {false,false,false,true};
    
    private JButton b1;
    private JButton b2;
    private JButton b3;
    private JButton b4;
    private JTextField t1;
    
    public void ventanaModificarC(){
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
 
        JPanel p1 = new JPanel(); 
        p1.setLayout(new FlowLayout());
        
        JLabel l1 = new JLabel("Cédula:");
        p1.add(l1);
  
        t1 = new JTextField();
        p1.add(t1);
        
        b1 = new JButton("Buscar");
        b1.addActionListener(this);
        b1.setActionCommand("buscar");
        p1.add(b1);
        
        p1.add(l1);
        
        JPanel p2 = new JPanel();
        p2.setLayout(new BorderLayout());
        
        dt = new DefaultTableModel(new String[]{"Fecha","Cliente","Mascota","Check"}, 0) {
 
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
        p2.add(scr, BorderLayout.NORTH);
        
        JPanel p3 = new JPanel(); 
        p3.setLayout(new FlowLayout());
        
        b2 = new JButton("Volver");
        b2.addActionListener(this);
        b2.setActionCommand("volver");
        p3.add(b2);
        
        b3 = new JButton("Editar");
        b3.addActionListener(this);
        b3.setActionCommand("editar");
        p3.add(b3); 
        
        b4 = new JButton("Eiminar");
        b4.addActionListener(this);
        b4.setActionCommand("eliminar");
        p3.add(b4);
        
        cp.add(p2, BorderLayout.CENTER);
        cp.add(p3, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        System.out.println("Comando: " + comando);
        
        switch(comando){ 
            case "buscar":
                buscarInformacion();
                break;
            
            case "volver":
                setVisible(false);
                break;
                
            case "editar":
                editarCita();
                JOptionPane.showMessageDialog(null, "Operación Exitosa");
                break;
                
            case "eliminar":
                eliminarCita();
                JOptionPane.showMessageDialog(null, "Operación Exitosa");
                break;    
 
        }
    }
    
    String cedula;
    Cliente cli;
    Mascota mas;
    String fechaMC;
    String horaMC;
    String nombreC;
    String nombreM;
    
    public void buscarInformacion(){ 
        cedula = t1.getText();
        
        cli = cc.cliBuscar(con, cedula);
        mas = (Mascota) cli.getMascotas();

        int n = cli.getMascotas().size();
        
        for(int i = 0; i < n; i++){
            
            if(mas.getCitas() != null){
                Object fila[] = new Object[4];
                
                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                fechaMC = formato.format(mas.getCitas().get(i).getCitaFecha());
                System.out.println(fechaMC);
                
                formato = new SimpleDateFormat("HH.mm.ss");
                horaMC = formato.format(mas.getCitas().get(i).getCitaFecha());
                System.out.println(horaMC);
                
                fila[0] = fechaMC;
                
                nombreC = cli.getPersonaNombre() + " " + cli.getPersonaApellido();
                fila[1] = nombreC;
                
                nombreM = mas.getMascotaNombre();
                fila[2] = nombreM;
                
                fila[3] = false;

                dt.addRow(fila);
            }   
        } 
    }
    
    
    public void editarCita(){ 
        for(int i = 0; i < tb1.getRowCount(); i++){
            System.out.println("Check: " + tb1.getValueAt(i, 3));
            if((boolean)tb1.getValueAt(i, 3) == true){
                llamarVentanaCita(getDesktopPane());
            }
        }
    }
    
    public void llamarVentanaCita(JDesktopPane escritorio){
        VRealizarCita vrc = new VRealizarCita(con, cca, cc, cm, cct);
        vrc.setVisible(true);
        this.setVisible(false);
        
        escritorio.add(vrc);
    }
    
    
    public void eliminarCita(){
        
        for(int i = 0; i < tb1.getRowCount(); i++){
            System.out.println("Check: " + tb1.getValueAt(i, 3));
            if((boolean)tb1.getValueAt(i, 3) == true){
                
            }
        }
    }
    

    
}
