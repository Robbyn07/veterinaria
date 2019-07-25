
package vista;

import conexionbd.Conexion;
import conexionbd.ControladorProducto;
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
import modelo.Producto;

/**
 *
 * @author Rakrad7101
 */
public class VComprarProducto extends JInternalFrame implements ActionListener{
    
    Conexion con;
    ControladorProveedor cpv;
    ControladorProducto cpd;

    public VComprarProducto(Conexion con,ControladorProveedor cpv,ControladorProducto cpd){
        this.con = con;
        this.cpv = cpv;
        this.cpd = cpd;
        initComponentes();
        ventanaComprarPro();
    }
    
    public void initComponentes(){
        setSize(400, 400);
        setTitle("Comprar Productos");
        setClosable(true);
        setMaximizable(true);
    }
    
    private JButton b1;
    private JButton b2;
    private JTextField t1;
    private JTextField t2;
    private JTextField t3;
    private JTextField t4;
    
    public void ventanaComprarPro(){
        Container cp = getContentPane();
        GridBagConstraints g1 = new GridBagConstraints();       
        cp.setLayout(new GridBagLayout()); 
        
        JLabel l1 = new JLabel("Proveedor:");
        g1.gridx =0;
        g1.gridy =0;
        cp.add(l1, g1);
        
        t1 = new JTextField(12);
        g1.gridx =1;
        g1.gridy =0;
        cp.add(t1, g1);
        
        JLabel l2 = new JLabel("Nombre:");
        g1.gridx =0;
        g1.gridy =1;
        cp.add(l2, g1);
        
        t2 = new JTextField(12);
        g1.gridx =1;
        g1.gridy =1;
        cp.add(t2, g1);
        
        JLabel l3 = new JLabel("Precio Compra:");
        g1.gridx =0;
        g1.gridy =2;
        cp.add(l3, g1);
        
        t3 = new JTextField(12);
        g1.gridx =1;
        g1.gridy =2;
        cp.add(t3, g1);
        
        JLabel l4 = new JLabel("Cantidad:");
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
        
        b2 = new JButton("Comprar");
        g1.gridx = 1;
        g1.gridy = 4;
        b2.addActionListener(this);
        b2.setActionCommand("comprar");
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
                
            case "comprar":
                comprar();
                break;
        }
    }    
    
    private String proveedor;
    private String producto;
    private double precioC;
    private int cantidad;
    Boolean v;
    
    public boolean buscarProveedor(){
        v = false;
        proveedor = t1.getText();
        
        if(cpv.pvdBuscarNombre(con, proveedor).getProveedorRazonSocial().equals(proveedor)){
            v = true;
        }else{
            JOptionPane.showMessageDialog(null,"Error","No existe el proveedor",
                        JOptionPane.ERROR_MESSAGE); 
        }
        return v;
    }
    
    
    public void comprar(){
        
        try {
            proveedor = t1.getText();
            producto = t2.getText();
            precioC = Double.parseDouble(t3.getText());
            cantidad = Integer.parseInt(t4.getText());  
            
            Producto pro;
            pro = cpd.proBuscar(con, producto);
            pro.setProductoPrecioCompra(precioC);
            pro.setProductoEstado("A");
            
            if(pro.getProductoNombre().toUpperCase().equals(producto.toUpperCase())){
                
                int res = JOptionPane.showConfirmDialog(null,"Confirmacion",
                "Desea Confirmar la acción?",JOptionPane.YES_NO_OPTION);
        
                if(res == JOptionPane.YES_OPTION){
                    if(cpd.agregarStock(con,pro, cantidad) == true){
                   
                        JOptionPane.showMessageDialog(null, "Se compró "+ cantidad +" de "
                        + producto + ", al precio de " + (precioC * cantidad));
                    }   
                }else{
                JOptionPane.showMessageDialog(null,"Error","Se canceló "
                            + "la operación",JOptionPane.ERROR_MESSAGE); 
                }
                
            }else{
                JOptionPane.showMessageDialog(null,"Error","No existe el producto",
                        JOptionPane.ERROR_MESSAGE); 
            }
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Verifique los campos numéricos");
        }
    }
}
