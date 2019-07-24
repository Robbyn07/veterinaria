
package vista;

import conexionbd.Conexion;
import conexionbd.ControladorCaracter;
import conexionbd.ControladorCliente;
import conexionbd.ControladorFacturaCabecera;
import conexionbd.ControladorFacturaDetalle;
import conexionbd.ControladorProducto;
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

/**
 *
 * @author Rakrad7101
 */
public class VAnularFactura extends JInternalFrame implements ActionListener{
    
    Conexion con;
    ControladorCaracter cca;
    ControladorProducto cpd;
    ControladorCliente cc;
    ControladorFacturaCabecera cfc;
    ControladorFacturaDetalle cfd;
    
    public VAnularFactura(Conexion con,ControladorCaracter cca,ControladorProducto cpd,
            ControladorCliente cc,ControladorFacturaCabecera cfc,ControladorFacturaDetalle cfd){
        this.con = con;
        this.cca = cca;
        this.cpd = cpd;
        this.cc = cc;
        this.cfc = cfc;
        this.cfd = cfd;
        initComponentes();
        ventanaAnularFac();
    }
    
    public void initComponentes(){
        setSize(800,300);
        setTitle("Anular Factura");
        setClosable(true);
        setMaximizable(true);
    }
    
    private JButton b1;
    private JButton b2;
    
    public void ventanaAnularFac(){
        Container cp = getContentPane();
        GridBagConstraints g1 = new GridBagConstraints();       
        cp.setLayout(new GridBagLayout()); 
        
        JLabel l1 = new JLabel("Nº Factura:");
        g1.gridx =0;
        g1.gridy =0;
        cp.add(l1, g1);
        
        JTextField t1 = new JTextField(12);
        g1.gridx =1;
        g1.gridy =0;
        cp.add(t1, g1);
        
        b1 = new JButton("Volver");
        g1.gridx = 0;
        g1.gridy = 1;
        b1.addActionListener(this);
        b1.setActionCommand("volver");
        cp.add(b1, g1);
        
        b2 = new JButton("Anular");
        g1.gridx = 1;
        g1.gridy = 1;
        b2.addActionListener(this);
        b2.setActionCommand("anular");
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
                
            case "anular":
                JOptionPane.showMessageDialog(null, "Operación Exitosa");
                break;
 
        }
    }
}
