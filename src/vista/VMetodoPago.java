
package vista;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Rakrad7101
 */
public class VMetodoPago extends JInternalFrame implements ActionListener{
    
    public VMetodoPago(){
        initComponentes();
        ventanaMetodoP();
    }
    
    public void initComponentes(){
        setSize(200,200);
        setTitle("Elegir Método de Pago");
    }
    
    private JButton b1;
    private JButton b2;
    private JButton b3;
    
    public void ventanaMetodoP(){
        
        Container cp = getContentPane();
        cp.setLayout(new FlowLayout());
    
        b1 = new JButton("Cancelar");
        b1.addActionListener(this);
        b1.setActionCommand("cancelar");
        cp.add(b1);
        
        b2 = new JButton("Efectivo");
        b2.addActionListener(this);
        b2.setActionCommand("efectivo");
        cp.add(b2);
        
        b3 = new JButton("Tarjeta");
        b3.addActionListener(this);
        b3.setActionCommand("tarjeta");
        cp.add(b3);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        System.out.println("Comando: " + comando);
        
        switch(comando){
            case "cancelar":
                llamarVentanaRealizarF(getDesktopPane());
                setVisible(false);
                break;
            
            case "efectivo":
                JOptionPane.showMessageDialog(null, "Operación Exitosa");
                setVisible(false);
                break;
                
            case "tarjeta":
                llamarVentanaPT(getDesktopPane());
                setVisible(false);
                break;     
        } 
    }
    
    public void llamarVentanaRealizarF(JDesktopPane escritorio){
        VRealizarFactura vrf = new VRealizarFactura();
        vrf.setVisible(true);
        
        escritorio.add(vrf);
    }
    
    public void llamarVentanaPT(JDesktopPane escritorio){
        VPagoTarjeta vpt = new VPagoTarjeta();
        vpt.setVisible(true);
        
        escritorio.add(vpt);
    }

}
