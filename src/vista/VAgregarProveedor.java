
package vista;

import conexionbd.Conexion;
import conexionbd.ControladorCaracter;
import conexionbd.ControladorProveedor;
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
import modelo.Proveedor;

/**
 *
 * @author Rakrad7101
 */
public class VAgregarProveedor extends JInternalFrame implements ActionListener{
    
    Conexion con;
    ControladorProveedor cpv;
    
    public VAgregarProveedor(Conexion con,ControladorProveedor cpv){
        this.con = con;
        this.cpv = cpv;
        initComponentes();
        ventanaAgregarProv();
    }
    
    public void initComponentes(){
        setSize(400,400);
        setTitle("Agregar Proveedores");
        setClosable(true);
        setMaximizable(true);
    }
    
    private JButton b1;
    private JButton b2;
    private JTextField t1;
    private JTextField t2;
    private JTextField t3;
    private JTextField t4;
    
    public void ventanaAgregarProv(){
        Container cp = getContentPane();
        GridBagConstraints g1 = new GridBagConstraints();       
        cp.setLayout(new GridBagLayout()); 
        
        JLabel l1 = new JLabel("RUC:");
        g1.gridx =0;
        g1.gridy =0;
        cp.add(l1, g1);
        
        t1 = new JTextField(12);
        g1.gridx =1;
        g1.gridy =0;
        cp.add(t1, g1);
        
        JLabel l2 = new JLabel("Razón Social:");
        g1.gridx =0;
        g1.gridy =1;
        cp.add(l2, g1);
        
        t2 = new JTextField(12);
        g1.gridx =1;
        g1.gridy =1;
        cp.add(t2, g1);
        
        JLabel l3 = new JLabel("Correo:");
        g1.gridx =0;
        g1.gridy =2;
        cp.add(l3, g1);
        
        t3 = new JTextField(12);
        g1.gridx =1;
        g1.gridy =2;
        cp.add(t3, g1);
        
        JLabel l4 = new JLabel("Dirección:");
        g1.gridx =0;
        g1.gridy =3;
        cp.add(l4, g1);
        
        t4 = new JTextField(12);
        g1.gridx =1;
        g1.gridy =3;
        cp.add(t4, g1);
        
        b1 = new JButton("Volver");
        g1.gridx = 0;
        g1.gridy = 4;
        b1.addActionListener(this);
        b1.setActionCommand("volver");
        cp.add(b1, g1);
        
        b2 = new JButton("Agregar");
        g1.gridx = 1;
        g1.gridy = 4;
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
                if(agregarProveedor() == true){
                    JOptionPane.showMessageDialog(null, "Operación Exitos");
                }
                break;
 
        }
    }
    
    String ruc;
    String razonS;
    String email;
    String direccion;
    Boolean v;
    
    public boolean agregarProveedor(){
        v = false;
        ruc = t1.getText();
        razonS = t2.getText();
        email = t3.getText();
        direccion = t4.getText();
        
        Proveedor prov = new Proveedor();
        prov.setProveedorRuc(ruc);
        prov.setProveedorRazonSocial(razonS);
        prov.setProveedorEmail(email);
        prov.setProveedorDireccion(direccion);
        
        if(cpv.pvdAgregar(con, prov)==true){
            v = true;
        }
        
        return v;
    }
}
