
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import modelo.Cita;
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
        setSize(800,500);
        setTitle("Modificar Cita");
    }
    
    DefaultTableModel dt;
    private JTable tb1;
    private JScrollPane scr;
    private boolean[] editable = {false,false,false,false,false,true};
    
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
  
        t1 = new JTextField(10);
        p1.add(t1);
        
        b1 = new JButton("Buscar");
        b1.addActionListener(this);
        b1.setActionCommand("buscar");
        p1.add(b1);
        
        JPanel p2 = new JPanel();
        p2.setLayout(new BorderLayout());
        
        dt = new DefaultTableModel(new String[]{"Cita","Fecha","Hora","Cliente","Mascota","Check"}, 0) {
 
            Class[] types = new Class[]{
                java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,
                java.lang.Object.class,java.lang.Boolean.class    
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
                buscarInformacion();
                break;
            
            case "volver":
                setVisible(false);
                break;
                
            case "editar":
                editarCita();
                break;
                
            case "eliminar":
                eliminarCita();
                break;    
 
        }
    }
    
    Cliente cli;
    Mascota mas;
    Cita cit;
    
    String cedula;
    String fechaMC;
    String horaMC;
    String nombreC;
    String nombreM;
    
    public void buscarInformacion(){ 
        cedula = t1.getText();
        
        cli = cc.cliBuscar(con, cedula);
        ArrayList<Mascota> listaM = (ArrayList<Mascota>) cli.getMascotas();//igual, no seria necesario
        ArrayList<Cita> listaC;
                
        int n = listaM.size();
        
        for(int i = 0; i < n; i++){
            mas = listaM.get(i);
            
            listaC = (ArrayList<Cita>) mas.getCitas();//las mascotas del cliente ya tendrian sus citas
            
            int m = listaC.size();
            
            for(int j = 0; j < m; j++){
                cit = listaC.get(j);//estaba con listaC.get(i)
                
                if(listaC.get(j) != null){
                    Object fila[] = new Object[6];

                    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                    fechaMC = formato.format(cit.getCitaFecha());

                    formato = new SimpleDateFormat("HH.mm.ss");
                    horaMC = formato.format(cit.getCitaFecha());

                    fila[0] = cit.getCitaId();
                    fila[1] = fechaMC;
                    fila[2] = horaMC;
                    nombreC = cli.getPersonaNombre() + " " + cli.getPersonaApellido();
                    fila[3] = nombreC;
                    nombreM = mas.getMascotaNombre();
                    fila[4] = nombreM;
                    fila[5] = false;

                    dt.addRow(fila);
                }   
            }
        } 
    }
    
    
    public void editarCita(){ 
        
        for(int i = 0; i < tb1.getRowCount(); i++){
            if((boolean)tb1.getValueAt(i, 5) == true){
                
                cit = cct.citBuscar(con, (int) tb1.getValueAt(i, 0));
                        
                
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
        /*
        for(int i = 0; i < tb1.getRowCount(); i++){
            
            if((boolean)tb1.getValueAt(i, 5) == true){
                cit = cct.citBuscar(con, (int) tb1.getValueAt(i, 0));
                mas.eliminarCitas(cit);
            }
        }*/
        
        for(int i = 0; i < tb1.getRowCount(); i++){
            
            if((boolean)tb1.getValueAt(i, 5) == true){
                cit = cct.citBuscar(con, (int) tb1.getValueAt(i, 0));
                //mas.eliminarCitas(cit);
                
                //agregado para eliminar la cita en la base:
                if(cct.citEliminar(con,(int) tb1.getValueAt(i, 0)) == true){
                    mas.eliminarCitas(cit);
                    JOptionPane.showMessageDialog(null, "Operación Exitosa"); 
                }else{
                    JOptionPane.showMessageDialog(null,"No se completó "
                            + "la operación","Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        
    }
    
}
