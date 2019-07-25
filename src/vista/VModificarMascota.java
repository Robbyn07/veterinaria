
package vista;

import com.toedter.calendar.JYearChooser;
import conexionbd.Conexion;
import conexionbd.ControladorCaracter;
import conexionbd.ControladorCliente;
import conexionbd.ControladorEspecie;
import conexionbd.ControladorMascota;
import conexionbd.ControladorRaza;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import modelo.Cliente;
import modelo.Especie;
import modelo.Mascota;
import modelo.Raza;

/**
 *
 * @author Rakrad7101
 */
public class VModificarMascota extends JInternalFrame implements ActionListener{
    
    Conexion con;
    ControladorCaracter cca;
    ControladorCliente cc;
    ControladorMascota cm;
    ControladorEspecie ces;
    ControladorRaza cr;
    
    public VModificarMascota(Conexion con,ControladorCaracter cca,ControladorCliente cc,
            ControladorMascota cm,ControladorEspecie ces,ControladorRaza cr){
        this.con = con;
        this.cca = cca;
        this.cc = cc;
        this.cm = cm;
        this.ces = ces;
        this.cr = cr;
        initComponentes();
        ventanaModificarMas();
    }
    
    public void initComponentes(){
        setSize(800,400);
        setTitle("Modificar Mascotas");
        setClosable(true);
        setMaximizable(true);
    }
    
    private JButton b1;
    private JButton b2;
    private JButton b3;
    private JButton b4;
    private JComboBox<String> cb1;
    DefaultTableModel dt;
    private JTable tb1;
    private JScrollPane scr;
    private boolean[] editable = {true,true, true, true, true, true};
    private JYearChooser yc;
    private JTextField t1;
    private JLabel l2;
    private JLabel l3;
    private JLabel l4;
    
    String cedula;
    String nombreC = "Nombre";
    String telefonoC = "Teléfono";
    String direccionC = "Dirección";
    
    public void ventanaModificarMas(){
        
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
        p2.setLayout(new GridBagLayout());
        
        
        dt = new DefaultTableModel(new String[]{"Nombre","Especie","Raza","Género",
                                                "Color","Check"}, 0) {
 
            Class[] types = new Class[]{
                java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,
                java.lang.Object.class,java.lang.Object.class,java.lang.Boolean.class    
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
        TableColumn col=tb1.getColumnModel().getColumn(3);
        String op[]={"Macho","Hembra"};
        cb1 = new JComboBox(op);
        col.setCellEditor(new DefaultCellEditor(cb1));
        scr = new JScrollPane(tb1);
        g1.gridx = 0;
        g1.gridy = 0;
        g1.ipadx = 600;
        g1.ipady = 100;
        g1.gridwidth =3;
        p2.add(scr, g1);
        
        JPanel p3 = new JPanel(); 
        p3.setLayout(new FlowLayout());
        
        b2 = new JButton("Volver");
        b2.addActionListener(this);
        b2.setActionCommand("volver");
        p3.add(b2, g1);
        
        b3 = new JButton("Editar");
        b3.addActionListener(this);
        b3.setActionCommand("editar");
        p3.add(b3, g1); 
        
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
                
            case "editar":
                editarMascota();
                JOptionPane.showMessageDialog(null, "Operación Exitosa");
                break;   
 
        }
    }
    
    Cliente cli;
    
    public void buscarCliente(){
        cedula = t1.getText();
        cli = cc.cliBuscar(con, cedula);
        
        try {
            if(cca.verificarCedula(cedula) == true){
                if(cli.getPersonaCedula().equals(cedula)){
                    nombreC = cc.cliBuscar(con, cedula).getPersonaNombre();
                    l2.setText(nombreC);
                    
                    telefonoC = cli.getPersonaTelefono();
                    l3.setText(telefonoC);
                    
                    direccionC = cli.getPersonaDireccion();
                    l4.setText(direccionC); 
                    
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
        
        int n = cm.masObtener(con, cli.getClienteId()).size();
        
        for(int i = 0; i < n; i++){
            Object fila[] = new Object[6];
            fila[0] = cm.masObtener(con, cli.getClienteId()).get(i).getMascotaNombre();
            fila[1] = cm.masObtener(con, cli.getClienteId()).get(i).getEspecie();
            fila[2] = cm.masObtener(con, cli.getClienteId()).get(i).getRaza();
            fila[3] = cm.masObtener(con, cli.getClienteId()).get(i).getMascotaGenero();
            fila[4] = cm.masObtener(con, cli.getClienteId()).get(i).getMascotaColor();
            fila[5] = false;

            dt.addRow(fila);
        }
        
    }
    
    String nombre;
    String especie;
    String raza;
    String genero;
    String color;
    
    public void editarMascota(){
        Cliente cli;
        cli = cc.cliBuscar(con, cedula);
        
        int n = cm.masObtener(con, cli.getClienteId()).size();
        
        for(int i = 0; i < tb1.getRowCount(); i++){
            System.out.println("Check: " + tb1.getValueAt(i, 5));
            if((boolean)tb1.getValueAt(i, 5) == true){
                for(int j = 0; j < n; j++){
                    Mascota mas;
                    mas = cm.masBuscar(con, cli.getMascotas().get(j).getMascotaId());
                    nombre = String.valueOf(dt.getValueAt(i,0));
                    especie = String.valueOf(dt.getValueAt(i,1));
                    raza = String.valueOf(dt.getValueAt(i,2));
                    genero = String.valueOf(tb1.getModel().getValueAt(i, 3));
                    color = String.valueOf(dt.getValueAt(i, 4));
                    
                    mas.setMascotaNombre(nombre);
                    Especie esp = ces.espBuscar(con, especie);
                    Raza raz = cr.razBuscar(con, nombre);

                    mas.setMascotaNombre(nombre);

                    if(esp == null){
                        esp = new Especie();
                        esp.setEspecieNombre(especie);
                        ces.espAgregar(con, esp);
                    }

                    if(raz == null){
                        raz = new Raza();
                        raz.setRazaNombre(raza);
                        cr.razAgregar(con, raz);
                    }
                    mas.setEspecie(esp);
                    mas.setRaza(raz);
                    mas.setMascotaGenero(genero);
                    mas.setMascotaColor(color);
                    
                    int res = JOptionPane.showConfirmDialog(null,"Desea Confirmar la acción?",
                            "Alerta!",JOptionPane.QUESTION_MESSAGE,JOptionPane.YES_NO_OPTION);
        
                    if(res == 0){
                        if(cm.masEditar(con, mas) == true){
                            JOptionPane.showMessageDialog(null, "Operación Exitosa");
                        }else{
                            JOptionPane.showMessageDialog(null,"Error","No se pudo completar "
                                    + "la operación",JOptionPane.ERROR_MESSAGE); 
                        }
                    }else{

                    }
                }
            }
        }  
        
    }
    
    
}
