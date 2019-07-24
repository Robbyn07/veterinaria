
package vista;

import conexionbd.Conexion;
import conexionbd.ControladorCaracter;
import conexionbd.ControladorEmpleado;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import modelo.Empleado;

/**
 *
 * @author Rakrad7101
 */
public class VModificarEmpleado extends JInternalFrame implements ActionListener{

    Conexion con;
    ControladorCaracter cca;
    ControladorEmpleado cem;
    
    public VModificarEmpleado(Conexion con,ControladorCaracter cca,ControladorEmpleado cem){
        this.con = con;
        this.cca = cca;
        this.cem = cem;
        initComponentes();
        ventanaModificarEmp();
    }
    
    public void initComponentes(){
        setSize(400, 400);
        setTitle("Modificar Empleados");
        setClosable(true);
        setMaximizable(true); 

    }
    
    private JButton b1;
    private JButton b2;
    private JButton b3;
    private JButton b4;
    private JTextField t1;
    private JTextField t2;
    private JTextField t3;
    private JTextField t4;
    private JTextField t5;
    private JTextField t6;
    private JComboBox<String> cb1;
    
    public void ventanaModificarEmp(){
        
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
        b1.addActionListener(this);
        b1.setActionCommand("buscar");
        g1.gridx =2;
        g1.gridy =0;
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
        
        JLabel l5 = new JLabel("Cargo:");
        g1.gridx =0;
        g1.gridy =4;
        cp.add(l5, g1);
        
        cb1 = new JComboBox<>();
            cb1.addItem("Administrador");
            cb1.addItem("Secretaria/o");
            cb1.addItem("Médico");
        g1.gridx =1;
        g1.gridy =4;
        cp.add(cb1, g1);
        
        JLabel l6 = new JLabel("Correo:");
        g1.gridx =0;
        g1.gridy =5;
        cp.add(l6, g1);
        
        t5 = new JTextField(12);
        g1.gridx =1;
        g1.gridy =5;
        cp.add(t5, g1);
        
        JLabel l7 = new JLabel("Dirección:");
        g1.gridx =0;
        g1.gridy =6;
        cp.add(l7, g1);
        
        t6 = new JTextField(12);
        g1.gridx =1;
        g1.gridy =6;
        cp.add(t6, g1);
        
        b2 = new JButton("Volver");
        g1.gridx = 0;
        g1.gridy = 7;
        b2.addActionListener(this);
        b2.setActionCommand("volver");
        cp.add(b2, g1); 
        
        b3 = new JButton("Editar");
        g1.gridx = 1;
        g1.gridy = 7;
        b3.addActionListener(this);
        b3.setActionCommand("editar");
        cp.add(b3, g1);
        
        b4 = new JButton("Eliminar");
        g1.gridx = 2;
        g1.gridy = 7;
        b4.addActionListener(this);
        b4.setActionCommand("eliminar");
        cp.add(b4, g1); 
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        System.out.println("Comando: " + comando);
        
        switch(comando){
            case "buscar":
                buscarEmpleado();
                break;
            
            case "volver":
                setVisible(false);
                break;
                
            case "editar":
                editarEmpleado();
                break;
                
            case "eliminar":
                eliminarEmpleado();
                break;
        }
    }
    
    String cedula;
    String nombre;
    String apellido;
    String telefono;
    String cargo;
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
    
    public void buscarEmpleado(){
        cedula = t1.getText();
        
        try {
            if(cca.verificarCedula(cedula) == true){
                if(cem.empBuscar(con, cedula).getPersonaCedula().equals(cedula)){
                    nombre = cem.empBuscar(con, cedula).getPersonaNombre();
                    t2.setText(nombre);

                    apellido = cem.empBuscar(con, cedula).getPersonaApellido();
                    t3.setText(apellido);

                    telefono = cem.empBuscar(con, cedula).getPersonaTelefono();
                    t4.setText(telefono);

                    cargo = cem.empBuscar(con, cedula).getEmpleadoPermiso();
                    cb1.setSelectedItem(cargo);

                    email = cem.empBuscar(con, cedula).getPersonaEmail();
                    t5.setText(email);

                    direccion = cem.empBuscar(con, cedula).getPersonaDireccion();
                    t6.setText(direccion);  
                }else{
                    JOptionPane.showMessageDialog(null,"Error","El empleado no"
                            + " existe",JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error","Verifique la cédula",
                    JOptionPane.ERROR_MESSAGE);        
        }
    }
    
    public void editarEmpleado(){
        cedula = t1.getText();
        nombre = t2.getText();
        apellido = t3.getText();
        telefono = t4.getText();
        cargo = cb1.getSelectedItem().toString();
        email = t5.getText();
        direccion = t6.getText();
        
        Empleado emp;
        emp = cem.empBuscar(con, cedula);
        emp.setPersonaNombre(nombre);
        emp.setPersonaApellido(apellido);
        emp.setPersonaTelefono(telefono);
        emp.setEmpleadoPermiso(cargo);
        emp.setPersonaEmail(email);
        emp.setPersonaDireccion(direccion);
        
        int res = JOptionPane.showConfirmDialog(null,"Desea Confirmar la acción?",
                "Alerta!",JOptionPane.QUESTION_MESSAGE,JOptionPane.YES_NO_OPTION);
        
        if(res == 0){
            if(cem.empEditar(con, emp) == true){
                JOptionPane.showMessageDialog(null, "Operación Exitosa");
            }else{
                JOptionPane.showMessageDialog(null,"Error","No se pudo completar "
                        + "la operación",JOptionPane.ERROR_MESSAGE); 
            }
        }else{
            
        }
    }
    
    public void eliminarEmpleado(){
        cedula = t1.getText();
        
        Empleado emp;
        emp = cem.empBuscar(con, cedula);
        emp.setEmpleadoEstado("I");
        
        int res = JOptionPane.showConfirmDialog(null,"Desea Confirmar la acción?",
                "Alerta!",JOptionPane.QUESTION_MESSAGE,JOptionPane.YES_NO_OPTION);
        
        if(res == 0){
            if(cem.cancelarEmpleado(con, emp) == true){
                JOptionPane.showMessageDialog(null, "Operación Exitosa");
            }else{
                JOptionPane.showMessageDialog(null,"Error","No se pudo completar "
                        + "la operación",JOptionPane.ERROR_MESSAGE); 
            }
        }else{
            
        }
    }
}
