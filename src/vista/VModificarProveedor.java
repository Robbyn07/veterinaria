
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
public class VModificarProveedor extends JInternalFrame implements ActionListener{
    
    Conexion con;
    ControladorCaracter cca;
    ControladorProveedor cpv;
    
    public VModificarProveedor(Conexion con,ControladorCaracter cca,ControladorProveedor cpv){
        this.con = con;
        this.cca = cca;
        this.cpv = cpv;
        initComponentes();
        ventanaModificarProv();
    }
    
    public void initComponentes(){
        setSize(400, 400);
        setTitle("Modificar Proveedores");
    }
    
    private JButton b1;
    private JButton b2;
    private JButton b3;
    private JButton b4;
    private JTextField t1;
    private JTextField t2;
    private JTextField t3;
    private JTextField t4;
    
    public void ventanaModificarProv(){
        
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
        
        b1 = new JButton("Buscar");
        b1.addActionListener(this);
        b1.setActionCommand("buscar");
        g1.gridx =2;
        g1.gridy =0;
        cp.add(b1, g1);
        
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
        
        b2 = new JButton("Volver");
        g1.gridx = 0;
        g1.gridy = 4;
        b2.addActionListener(this);
        b2.setActionCommand("volver");
        cp.add(b2, g1); 
        
        b3 = new JButton("Editar");
        g1.gridx = 1;
        g1.gridy = 4;
        b3.addActionListener(this);
        b3.setActionCommand("editar");
        cp.add(b3, g1);
        
        b4 = new JButton("Eliminar");
        g1.gridx = 2;
        g1.gridy = 4;
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
                buscarProveedor();
                break;
            
            case "volver":
                setVisible(false);
                break;
                
            case "editar":
                editarProveedor();
                break;
                
            case "eliminar":
                eliminarProveedor();
                break;
        }
    }
    
    String ruc;
    String razonS;
    String email;
    String direccion;
    Boolean v;
    
    public void buscarProveedor(){
        ruc = t1.getText();
        Proveedor proveedor = cpv.pvdBuscar(con, ruc);
        if(proveedor.getProveedorRuc().equals(ruc)){
            
           razonS = proveedor.getProveedorRazonSocial();
            t2.setText(razonS);

            System.out.println("Email= "+proveedor.getProveedorEmail());
            email = proveedor.getProveedorEmail();
            t3.setText(email);

            direccion = proveedor.getProveedorDireccion();
            t4.setText(direccion); 
        }else{
            JOptionPane.showMessageDialog(null,"Error","El proveedor no"
                            + " existe",JOptionPane.ERROR_MESSAGE);
        }

    }
    
    public void editarProveedor(){
        ruc = t1.getText();
        razonS = t2.getText();
        email = t3.getText();
        direccion = t4.getText();
        
        Proveedor pv;
        pv = cpv.pvdBuscar(con, ruc);
        pv.setProveedorRazonSocial(razonS);
        pv.setProveedorEmail(email);
        pv.setProveedorDireccion(direccion);
        
        int res = JOptionPane.showConfirmDialog(null,"Desea Confirmar la acción?",
                "Confirmacion",JOptionPane.YES_NO_OPTION);
        
        if(res == JOptionPane.YES_OPTION){
            if(cpv.pvdEditar(con, pv) == true){
                JOptionPane.showMessageDialog(null, "Operación Exitosa");
            }else{
                JOptionPane.showMessageDialog(null,"No se pudo completar la Operacion","Error "
                ,JOptionPane.ERROR_MESSAGE); 
            }
        }else{
            
        }
    }
    
    public void eliminarProveedor(){
        ruc = t1.getText();
        
        int res = JOptionPane.showConfirmDialog(null,"Confirmacion",
                "Desea Confirmar la acción?",JOptionPane.YES_NO_OPTION);
        
        if(res == JOptionPane.YES_OPTION){
            if(cpv.cancelarProveedor(con, cpv.pvdBuscar(con, ruc)) == true){
                JOptionPane.showMessageDialog(null, "Operación Exitosa");
            }else{
                JOptionPane.showMessageDialog(null,"Error","No se pudo completar "
                        + "la operación",
                    JOptionPane.ERROR_MESSAGE); 
            }
        }else{
            
        }
        
    }  
}
