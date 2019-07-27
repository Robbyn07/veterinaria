
package vista;

import conexionbd.Conexion;
import conexionbd.ControladorEmpleado;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import modelo.Empleado;

/**
 *
 * @author Rakrad7101
 */
public class VCambiarContrasena extends JInternalFrame implements ActionListener{
    
    Conexion con;
    ControladorEmpleado cem;
    Empleado emp;
    
    public VCambiarContrasena(Conexion con,ControladorEmpleado cem,Empleado emp){
        this.con = con;
        this.cem = cem;
        this.emp = emp;
        initComponentes();
        ventanaCambiarC();
    }
    
    public void initComponentes(){
        setSize(300,300);
        setTitle("Cambiar Contraseña");
        setClosable(true);
    }
    
    private JTextField t1;
    private JTextField t2;
    private JButton b1;
    private JButton b2;
    
    public void ventanaCambiarC(){
        Container cp = getContentPane();
        cp.setLayout(new GridBagLayout());
        GridBagConstraints g1 = new GridBagConstraints(); 
    
        JLabel l1 = new JLabel("Contraseña Actual:");
        g1.gridx = 0;
        g1.gridy = 0;
        cp.add(l1,g1);
        
        t1 = new JTextField(12);
        g1.gridx = 1;
        g1.gridy = 0;
        cp.add(t1,g1);
        
        JLabel l2 = new JLabel("Nueva Contraseña:");
        g1.gridx = 0;
        g1.gridy = 1;
        cp.add(l2,g1);
        
        t2 = new JTextField(12);
        g1.gridx = 1;
        g1.gridy = 1;
        cp.add(t2,g1);
        
        b1 = new JButton("Cancelar");
        b1.addActionListener(this);
        b1.setActionCommand("cancelar");
        g1.gridx = 0;
        g1.gridy = 2;
        cp.add(b1,g1);
        
        b2 = new JButton("Cambiar");
        b2.addActionListener(this);
        b2.setActionCommand("cambiar");
        g1.gridx = 1;
        g1.gridy = 2;
        cp.add(b2,g1);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        System.out.println("Comando: " + comando);
        
        switch(comando){
            case "cancelar":
                setVisible(false);
                break;
                
            case "cambiar":
                cambiarContraseña();
                break;     
        } 
    }
    
    String contraseniaA;
    String nuevaC;
    
    public void cambiarContraseña(){
        contraseniaA = t1.getText();
        nuevaC = t2.getText();
        
        if(emp.getEmpleadoContrasena().equals(contraseniaA)){
            emp.setEmpleadoContrasena(nuevaC);
            
            cem.empEditar(con, emp);
        }else{
            JOptionPane.showMessageDialog(null,"Contraseña Incorrecta",
                        "Error",JOptionPane.ERROR_MESSAGE);
        }
        
    }

}
