
package vista;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Rakrad7101
 */
public class VPagoTarjeta extends JInternalFrame implements ActionListener{
    
    public VPagoTarjeta(){
        initComponentes();
        ventanaPagoTar();
    }
    
    public void initComponentes(){
        setSize(600,300);
        setTitle("Pagar con Tarjeta");
    }
    
    private JButton b1;
    private JButton b2;
    private JButton b3;
    
    public void ventanaPagoTar(){
        Container cp = getContentPane();
        GridBagConstraints g1 = new GridBagConstraints();       
        cp.setLayout(new GridBagLayout()); 
        
        JLabel l1 = new JLabel("Número de Tarjeta:");
        g1.gridx =0;
        g1.gridy =0;
        cp.add(l1, g1);
        
        JTextField t1 = new JTextField(12);
        g1.gridx =1;
        g1.gridy =0;
        cp.add(t1, g1);
        
        JLabel l2 = new JLabel("Mes Caducidad:");
        g1.gridx =0;
        g1.gridy =1;
        cp.add(l2, g1);
        
        JTextField t2 = new JTextField(5);
        g1.gridx =1;
        g1.gridy =1;
        cp.add(t2, g1);
        
        JLabel l3 = new JLabel("Año Caducidad:");
        g1.gridx =2;
        g1.gridy =1;
        cp.add(l3, g1);
        
        JTextField t3 = new JTextField(5);
        g1.gridx =3;
        g1.gridy =1;
        cp.add(t3, g1);
        
        JLabel l4 = new JLabel("CVV:");
        g1.gridx =0;
        g1.gridy =2;
        cp.add(l4, g1);
        
        JTextField t4 = new JTextField(5);
        g1.gridx =1;
        g1.gridy =2;
        cp.add(t4, g1);
        
        b1 = new JButton("Volver");
        b1.addActionListener(this);
        b1.setActionCommand("volver");
        g1.gridx =0;
        g1.gridy =3;
        g1.gridwidth =2;
        cp.add(b1, g1);
        
        b2 = new JButton("Pagar");
        b2.addActionListener(this);
        b2.setActionCommand("pagar");
        g1.gridx =2;
        g1.gridy =3;
        g1.gridwidth =2;
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
            
            case "pagar":
                JOptionPane.showMessageDialog(null, "Pago Exitoso");
                setVisible(false);
                break;    

        } 
    }    

}
