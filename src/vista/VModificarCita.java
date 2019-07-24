
package vista;

import com.mxrck.autocompleter.TextAutoCompleter;
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
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelo.Cliente;

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
    private boolean[] editable = {true,false,false,true};
    
    private JButton b1;
    private JButton b2;
    private JButton b3;
    private JButton b4;
    private JTextField t1;
    private TextAutoCompleter ac;
    
    public void ventanaModificarC(){
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
 
        JPanel p1 = new JPanel(); 
        p1.setLayout(new FlowLayout());
        
        JLabel l1 = new JLabel("Mascota:");
        p1.add(l1);
        
        auto();
        
        t1 = new JTextField();
        ac = new TextAutoCompleter(t1);
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
            case "busacr":
                buscarMascota();
                break;
            
            case "volver":
                setVisible(false);
                break;
                
            case "editar":
                JOptionPane.showMessageDialog(null, "Operación Exitosa");
                break;
                
            case "eliminar":
                JOptionPane.showMessageDialog(null, "Operación Exitosa");
                break;    
 
        }
    }
    
    public void auto(){
        int n = cm.masObtenerTodos(con).size();
        
        for(int i = 0; i < n; i++){
            ac.addItem(cm.masObtenerTodos(con).get(i).getMascotaNombre());
        } 
    }
    
    String nombreM;
    
    public void buscarMascota(){ 
        nombreM = t1.getText();
        
        int n = cm.masObtenerNombre(con, nombreM).size();
        
        for(int i = 0; i < n; i++){
            Object fila[] = new Object[4];
            fila[0] = cm.masObtenerNombre(con, nombreM).get(i).getCitas().get(i).getCitaFecha();
            fila[1] = 
            fila[2] = cm.masObtenerNombre(con, nombreM).get(i).getMascotaNombre();
            fila[3] = false;

            dt.addRow(fila);
        }
        
    }
    
    public void mostrar(){
        int n = cm.masObtenerTodos(con).size();
        
        for(int i = 0; i < n; i++){
            Object fila[] = new Object[3];
            fila[0] = cm.masObtenerTodos(con).get(i).getCitas().get(i).getCitaFecha();
            fila[1] = false;

            dt.addRow(fila);
        }
        
    }
    
}
