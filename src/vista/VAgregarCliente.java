
package vista;

import conexionbd.Conexion;
import conexionbd.ControladorCaracter;
import conexionbd.ControladorCliente;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import modelo.Cliente;

/**
 *
 * @author Rakrad7101
 */
public class VAgregarCliente extends JInternalFrame implements ActionListener{
    
    Conexion con;
    ControladorCaracter cca;
    ControladorCliente cc;
    
    public VAgregarCliente(Conexion con,ControladorCaracter cca,ControladorCliente cc){
        this.con = con;
        this.cca = cca;
        this.cc = cc;
        initComponentes();
        ventanaAgregarCli();
    }
    
    public void initComponentes(){
        setSize(400,400);
        setTitle("Agregar Clientes");
        setClosable(true);
    }
    
    private JButton b1;
    private JButton b2;
    private JTextField t1;
    private JTextField t2;
    private JTextField t3;
    private JTextField t4;
    private JTextField t5;
    private JTextField t6;
    
    public void ventanaAgregarCli(){
        
        Container cp = getContentPane();
        GridBagConstraints g1 = new GridBagConstraints();       
        cp.setLayout(new GridBagLayout()); 
        
        JLabel l1 = new JLabel("Cédula:");
        g1.gridx =0;
        g1.gridy =0;
        cp.add(l1, g1);
        
        t1 = new JTextField(12);
        g1.gridx =1;
        g1.gridy =0;
        cp.add(t1, g1);
        
        JLabel l2 = new JLabel("Nombres:");
        g1.gridx =0;
        g1.gridy =1;
        cp.add(l2, g1);
        
        t2 = new JTextField(12);
        g1.gridx =1;
        g1.gridy =1;
        cp.add(t2, g1);
        
        JLabel l3 = new JLabel("Apellidos:");
        g1.gridx =0;
        g1.gridy =2;
        cp.add(l3, g1);
        
        t3 = new JTextField(12);
        g1.gridx =1;
        g1.gridy =2;
        cp.add(t3, g1);
        
        JLabel l4 = new JLabel("Teléfono:");
        g1.gridx =0;
        g1.gridy =3;
        cp.add(l4, g1);
        
        t4 = new JTextField(12);
        g1.gridx =1;
        g1.gridy =3;
        cp.add(t4, g1);
        
        JLabel l5 = new JLabel("Correo:");
        g1.gridx =0;
        g1.gridy =4;
        cp.add(l5, g1);
        
        t5 = new JTextField(12);
        g1.gridx =1;
        g1.gridy =4;
        cp.add(t5, g1);
        
        JLabel l7 = new JLabel("Dirección:");
        g1.gridx =0;
        g1.gridy =5;
        cp.add(l7, g1);
        
        t6 = new JTextField(12);
        g1.gridx =1;
        g1.gridy =5;
        cp.add(t6, g1);
        
        b1 = new JButton("Volver");
        g1.gridx = 0;
        g1.gridy = 6;
        b1.addActionListener(this);
        b1.setActionCommand("volver");
        cp.add(b1, g1);
        
        b2 = new JButton("Agregar");
        g1.gridx = 1;
        g1.gridy = 6;
        b2.addActionListener(this);
        b2.setActionCommand("agregar");
        cp.add(b2, g1); 
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        System.out.println("Comando: " + comando);
        
        switch(comando){ 
            case "volver":
                setVisible(false);
                break;
                
            case "agregar":
                if(comprobarCampos() == true){
                    agregarCliente();
                }else{
                    JOptionPane.showMessageDialog(null,"Ingrese correctamente "
                            + "los campos","Error",JOptionPane.ERROR_MESSAGE);
                }
                
                break;
 
        }
    }

    String cedula;
    String nombre;
    String apellido;
    String telefono;
    String email;
    String direccion;
    Boolean v;
    
    public boolean comprobarCampos(){
        v = false;
        cedula= t1.getText();
        nombre = t2.getText();
        apellido = t3.getText();
        //try-catch para controlar si la cedula contiene caracteres? o en vez de devolver el error, devolver un false
        if(cca.verificarCedula(cedula) == true){
            try {
                if(cca.comprobarCaracteres(nombre) == true
                        && cca.comprobarCaracteres(apellido) == true){
                    v = true;
                }
            } catch (Throwable ex) {
                Logger.getLogger(VAgregarEmpleado.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return v;
    }
    
    private void agregarCliente(){
        cedula= t1.getText();
        nombre = t2.getText();
        apellido = t3.getText();
        telefono = t4.getText();
        email = t5.getText();
        direccion = t6.getText();
        
        Cliente cli = new Cliente();
        cli.setPersonaCedula(cedula);
        cli.setPersonaNombre(nombre);
        cli.setPersonaApellido(apellido);
        cli.setPersonaTelefono(telefono);
        cli.setPersonaEmail(email);
        cli.setPersonaDireccion(direccion);
        
        if(cc.cliAgregar(con, cli) == true){
            JOptionPane.showMessageDialog(null, "Operación Exitosa");
        }else{
            JOptionPane.showMessageDialog(null,"No se completó la operación"
                    ,"Error",JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
}
