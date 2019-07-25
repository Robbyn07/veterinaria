
package vista;

import conexionbd.Conexion;
import conexionbd.ControladorProducto;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import modelo.Producto;

/**
 *
 * @author Rakrad7101
 */
public class VAgregarProducto extends JInternalFrame implements ActionListener{
    
    Conexion con;
    ControladorProducto cpd;
    
    public VAgregarProducto(Conexion con,ControladorProducto cpd){
        this.con = con;
        this.cpd = cpd;
        initComponentes();
        ventanaAgregarProd();
    }
    
    public void initComponentes(){
        setSize(400,400);
        setTitle("Agregar Productos");
        setClosable(true);
        setMaximizable(true);
    }
    
    private JButton b1;
    private JButton b2;
    private JTextField t1;
    private JComboBox<String> cb1;
    private JComboBox<String> cb2;
    private JComboBox<String> cb3;
    
    public void ventanaAgregarProd(){
        Container cp = getContentPane();
        GridBagConstraints g1 = new GridBagConstraints();       
        cp.setLayout(new GridBagLayout()); 
        
        JLabel l1 = new JLabel("Categoría:");
        g1.gridx =0;
        g1.gridy =0;
        cp.add(l1, g1);
        
        cb1 = new JComboBox<>();
            cb1.addItem("Servicio");
            cb1.addItem("Accesorios");
            cb1.addItem("Comida");
            cb1.addItem("Medicina");
        cb1.addActionListener(this);
        cb1.setActionCommand("categoria");
        g1.gridx =1;
        g1.gridy =0;
        cp.add(cb1, g1);
        
        JLabel l2 = new JLabel("Nombre:");
        g1.gridx =0;
        g1.gridy =1;
        cp.add(l2, g1);
        
        t1 = new JTextField(12);
        g1.gridx =1;
        g1.gridy =1;
        cp.add(t1, g1);
        
        JLabel l3 = new JLabel("Origen:");
        g1.gridx =0;
        g1.gridy =2;
        cp.add(l3, g1);
        
        cb2 = new JComboBox<>();
            cb2.addItem("Nacional");
            cb2.addItem("Extrangero");
        cb2.setEnabled(false);
        g1.gridx =1;
        g1.gridy =2;
        cp.add(cb2, g1);
        
        JLabel l4 = new JLabel("Alianza:");
        g1.gridx =0;
        g1.gridy =3;
        cp.add(l4, g1);
        
        cb3 = new JComboBox<>();
            cb3.addItem("Propio");
            cb3.addItem("Asociado");
        cb3.setEnabled(false);
        g1.gridx =1;
        g1.gridy =3;
        cp.add(cb3, g1);
        
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
                
            case "categoria":    
                habilitar();
                break;
                
            case "agregar":
                if(agregarProducto() == true){
                    JOptionPane.showMessageDialog(null, "Operación Exitosa");
                }
                break;
        }
    }
    
    String categoria;
    String nombre;
    String origen;
    String alianza;
    Boolean v;
    
    public void habilitar(){
        categoria = cb1.getSelectedItem().toString();
        
        if(categoria.equals("Servicio")){
            cb2.setEnabled(false);
            cb3.setEnabled(true);
        }else{
            cb2.setEnabled(true);
            cb3.setEnabled(false);
        }   
        
        
    }
    
    public boolean agregarProducto(){
        v = false;
        categoria = cb1.getSelectedItem().toString();
        nombre = t1.getText();
        
        Producto pro = new Producto(); 
        if(categoria.equals("Servicio")){
            pro.setProductoCategoria(categoria);
            pro.setProductoNombre(nombre);
            pro.setProductoOrigen(null);
            pro.setProductoAlianza(alianza);
        }else{
            pro.setProductoCategoria(categoria);
            pro.setProductoNombre(nombre);
            pro.setProductoOrigen(origen);
            pro.setProductoAlianza(null);
        }
        
        if(cpd.proAgregar(con, pro) == true){
            v = true;
        }
        
        return v;
    }
    
}
