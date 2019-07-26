
package vista;

import conexionbd.Conexion;
import conexionbd.ControladorCaracter;
import conexionbd.ControladorCliente;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
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
public class VModificarCliente extends JInternalFrame implements ActionListener{
    
    Conexion con;
    ControladorCaracter cca;
    ControladorCliente cc;
    
    public VModificarCliente(Conexion con,ControladorCaracter cca,ControladorCliente cc){
        this.con = con;
        this.cca = cca;
        this.cc = cc;
        initComponentes();
        ventanaModificarCli();
    }
    
    public void initComponentes(){
        setSize(400,400);
        setTitle("Modificar Clientes");
        setClosable(true);
    }
    
    private JButton b1;
    private JButton b2;
    private JButton b3;
    private JTextField t1;
    private JTextField t2;
    private JTextField t3;
    private JTextField t4;
    private JTextField t5;
    private JTextField t6;
    
    public void ventanaModificarCli(){
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
        
        b1 = new JButton("Buscar");
        g1.gridx = 2;
        g1.gridy = 0;
        b1.addActionListener(this);
        b1.setActionCommand("buscar");
        cp.add(b1, g1);
        
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
        
        b2 = new JButton("Volver");
        g1.gridx = 0;
        g1.gridy = 6;
        g1.gridwidth = 2;
        b2.addActionListener(this);
        b2.setActionCommand("volver");
        cp.add(b2, g1);
        
        b3 = new JButton("Editar");
        g1.gridx = 1;
        g1.gridy = 6;
        g1.gridwidth = 2;
        b3.addActionListener(this);
        b3.setActionCommand("editar");
        cp.add(b3, g1);
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
                editarCliente();
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
    
    Cliente cli;
    
    public void buscarCliente(){
        cedula = t1.getText();
        
        cli = cc.cliBuscar(con, cedula);
        
        try {
            if(cca.verificarCedula(cedula) == true){
                if(cli.getPersonaCedula().equals(cedula)){
                    nombre = cc.cliBuscar(con, cedula).getPersonaNombre();//se puede mejorar? para no llamar otra vez al metodo: nombre=cli.getPersonaNombre()
                    t2.setText(nombre);

                    apellido = cli.getPersonaApellido();
                    t3.setText(apellido);

                    telefono = cli.getPersonaTelefono();
                    t4.setText(telefono);

                    email = cli.getPersonaEmail();
                    t5.setText(email);

                    direccion = cli.getPersonaDireccion();
                    t6.setText(direccion);  
                }else{
                    JOptionPane.showMessageDialog(null,"El cliente no existe",
                            "Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null,"Verifique la cédula","Error",
                    JOptionPane.ERROR_MESSAGE);        
        }
    }
    
    public void editarCliente(){
        nombre = t2.getText();
        apellido = t3.getText();
        telefono = t4.getText();
        email = t5.getText();
        direccion = t6.getText();
        
        cli.setPersonaNombre(nombre);
        cli.setPersonaApellido(apellido);
        cli.setPersonaTelefono(telefono);
        cli.setPersonaEmail(email);
        cli.setPersonaDireccion(direccion);
        
        int res = JOptionPane.showConfirmDialog(null,"Desea Confirmar la acción?",
                "Alerta!",JOptionPane.QUESTION_MESSAGE,JOptionPane.YES_NO_OPTION);
        
        if(res == 0){
            if(cc.cliEditar(con, cli) == true){
                JOptionPane.showMessageDialog(null, "Operación Exitosa");
            }else{
                JOptionPane.showMessageDialog(null,"No se completó la opración"
                        ,"Error",JOptionPane.ERROR_MESSAGE); 
            }
        }else{
            
        }
    }
 
}
