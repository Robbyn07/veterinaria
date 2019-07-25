
package vista;

import conexionbd.Conexion;
import conexionbd.ControladorCaracter;
import conexionbd.ControladorCita;
import conexionbd.ControladorCliente;
import conexionbd.ControladorMascota;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JButton;
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
public class VRealizarCita extends JInternalFrame implements ActionListener{
    
    Conexion con;
    ControladorCaracter cca;
    ControladorCliente cc;
    ControladorMascota cm;
    ControladorCita cct;
    
    public VRealizarCita(Conexion con,ControladorCaracter cca,ControladorCliente cc,
            ControladorMascota cm,ControladorCita cct){
        this.con = con;
        this.cca = cca;
        this.cc = cc;
        this.cm = cm;
        this.cct = cct;
        initComponentes();
        ventanaRealizarC();
    }
    
    public void initComponentes(){
        setSize(800,400);
        setTitle("Realizar Cita");
    }
    
    DefaultTableModel dt;
    private JTable tb1;
    private JScrollPane scr;
    private boolean[] editable = {false, true};
    
    private JButton b1;
    private JButton b2;
    private JButton b3;
    private JLabel l4;
    private JLabel l5;
    private JLabel l6;
    private JTextField t1;
    private JTextField t2;
    private JTextField t3;
    
    String cedula;
    String nombreC = "Nombre";
    String telefonoC = "Teléfono";
    String direccionC = "Dirección";
    
    public void ventanaRealizarC(){
        
        Container cp = getContentPane();      
        cp.setLayout(new BorderLayout());
 
        JPanel p1 = new JPanel();
        GridBagConstraints g1 = new GridBagConstraints(); 
        p1.setLayout(new GridBagLayout());
        
        JLabel l1 = new JLabel("Fecha: ");
        g1.gridx =0;
        g1.gridy =0;
        p1.add(l1, g1);
       
        t1 = new JTextField(12);
        g1.gridx =1;
        g1.gridy =0;
        p1.add(t1, g1);
        
        JLabel l2 = new JLabel("Hora: ");
        g1.gridx =0;
        g1.gridy =1;
        p1.add(l2, g1);
        
        t2 = new JTextField(12);
        g1.gridx =1;
        g1.gridy =1;
        p1.add(t2, g1);
        
        JLabel l3 = new JLabel("Cédula:");
        g1.gridx =0;
        g1.gridy =2;
        p1.add(l3, g1);
        
        t3 = new JTextField(12);
        g1.gridx =1;
        g1.gridy =2;
        p1.add(t3, g1);
        
        b1 = new JButton("Buscar");
        b1.addActionListener(this);
        b1.setActionCommand("buscar");
        g1.gridx =2;
        g1.gridy =2;
        p1.add(b1, g1);
        
        l4 = new JLabel(nombreC);
        g1.gridx =0;
        g1.gridy =3;
        p1.add(l4, g1);
        
        l5 = new JLabel(nombreC);
        g1.gridx =1;
        g1.gridy =3;
        p1.add(l5, g1);
        
        l6 = new JLabel(nombreC);
        g1.gridx =2;
        g1.gridy =3;
        p1.add(l6, g1);
        
        JPanel p2 = new JPanel();
        p2.setLayout(new BorderLayout());
        
        dt = new DefaultTableModel(new String[]{"Mascota", "Check"}, 0) {
 
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
        p2.add(scr, BorderLayout.NORTH);
        
        JPanel p3 = new JPanel(); 
        p3.setLayout(new FlowLayout());
        
        b2 = new JButton("Volver");
        b2.addActionListener(this);
        b2.setActionCommand("volver");
        p3.add(b2);
        
        b3 = new JButton("Realizar");
        b3.addActionListener(this);
        b3.setActionCommand("terminar");
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
                
            case "terminar":
                if(agregarCita()==true){
                   JOptionPane.showMessageDialog(null, "Operación Exitosa"); 
                }
                break;
        }
    }
    
    public void buscarCliente(){
        cedula = t3.getText();
        
        try {
            if(cca.verificarCedula(cedula) == true){
                if(cc.cliBuscar(con, cedula).getPersonaCedula().equals(cedula)){
                    nombreC = cc.cliBuscar(con, cedula).getPersonaNombre();
                    l4.setText(nombreC);
                    
                    telefonoC = cc.cliBuscar(con, cedula).getPersonaTelefono();
                    l5.setText(telefonoC);
                    
                    direccionC = cc.cliBuscar(con, cedula).getPersonaDireccion();
                    l6.setText(direccionC); 
                    
                    mostrar();
                }else{
                    JOptionPane.showMessageDialog(null,"Error","El cliente no"
                            + " existe",JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null,"Error","Verifique la cédula",
                    JOptionPane.ERROR_MESSAGE);        
        }
    }
    
    public void mostrar(){
        Cliente cli;
        cli = cc.cliBuscar(con, cedula);
        
        int n = cm.masObtener(con, cli.getClienteId()).size();
        
        for(int i = 0; i < n; i++){
            Object fila[] = new Object[2];
            fila[0] = cm.masObtener(con, cli.getClienteId()).get(i).getMascotaNombre();
            fila[1] = false;

            dt.addRow(fila);
        }
        
    }
    
    String fecha;
    String hora;
    String time;
    Timestamp fechaC;
    Boolean v;
    
    public boolean agregarCita(){
        v = false;
        fecha = t1.getText();
        hora = t2.getText();
        time = fecha +" "+ hora;
        cedula = t3.getText();
        
        try {
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH.mm.ss");
            Date dateN = formato.parse(time);
            fechaC = new Timestamp(dateN.getTime());
            
            Cliente cli;
            cli = cc.cliBuscar(con, cedula);
            Cita cit = new Cita();
            Mascota mas;
            
            int n = cm.masObtener(con, cli.getClienteId()).size();
        
            for(int i = 0; i < tb1.getRowCount(); i++){
                System.out.println("Check: " + tb1.getValueAt(i, 5));
                if((boolean)tb1.getValueAt(i, 5) == true){
                    for(int j = 0; j < n; j++){
                        mas = cm.masBuscar(con, cli.getMascotas().get(j).getMascotaId());
                        
                        cit.setCitaFecha(dateN);
                        cit.setCitaEstado("H");
                        
                        if(cct.citAgregar(con, cit, cli.getMascotas().get(j).getMascotaId())
                                == true){
                            mas.addCitas(cit);
                            v = true;
                        }
                    }
                }
            }    
            
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        return v;
    }

    
}
