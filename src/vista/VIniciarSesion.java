
package vista;

import conexionbd.Conexion;
import conexionbd.ControladorCaracter;
import conexionbd.ControladorCita;
import conexionbd.ControladorCliente;
import conexionbd.ControladorDiagnostico;
import conexionbd.ControladorEmpleado;
import conexionbd.ControladorEspecie;
import conexionbd.ControladorFacturaCabecera;
import conexionbd.ControladorFacturaDetalle;
import conexionbd.ControladorMascota;
import conexionbd.ControladorProducto;
import conexionbd.ControladorProveedor;
import conexionbd.ControladorRaza;
import conexionbd.ControladorRecetaCabecera;
import conexionbd.ControladorRecetaDetalle;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Rakrad7101
 */
public class VIniciarSesion extends JFrame implements ActionListener{

    Conexion con;
    ControladorCaracter cca;
    ControladorEmpleado cem;
    ControladorProveedor cpv;
    ControladorProducto cpd;
    ControladorCliente cc;
    ControladorMascota cm;
    ControladorEspecie ces;
    ControladorRaza cr;
    ControladorCita cct;
    ControladorFacturaCabecera cfc;
    ControladorFacturaDetalle cfd;
    ControladorDiagnostico cd;
    ControladorRecetaCabecera crc;
    ControladorRecetaDetalle crd;
    
    String user;
    String contra;
    int cont;
    
    public VIniciarSesion(Conexion con,ControladorCaracter cca,ControladorEmpleado cem,
            ControladorProveedor cpv,ControladorProducto cpd,ControladorCliente cc,
            ControladorMascota cm,ControladorEspecie ces,ControladorRaza cr,ControladorCita cct,
            ControladorFacturaCabecera cfc,ControladorFacturaDetalle cfd,
            ControladorDiagnostico cd,ControladorRecetaCabecera crc,
            ControladorRecetaDetalle crd){
        this.con = con;
        this.cca = cca;
        this.cem = cem;
        this.cpv = cpv;
        this.cpd = cpd;
        this.cc = cc;
        this.cm = cm;
        this.ces = ces;
        this.cr = cr;
        this.cct = cct;
        this.cfc = cfc;
        this.cfd = cfd;
        this.cd = cd;
        this.crc = crc;
        this.crd = crd;
        ventanaIniciar();
        initComponentes();
    }
    
    public void initComponentes(){
        setSize(300,300);
        setTitle("Inicar Sesión");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    private JButton b1;
    JTextField t1;
    JPasswordField t2;
    
    public void ventanaIniciar(){
        
        Container cp = getContentPane();
        
        GridBagConstraints g1 = new GridBagConstraints();
                
        cp.setLayout(new GridBagLayout());
        
        JLabel l1 = new JLabel("Username:");
        g1.gridx =0;
        g1.gridy =0;
        cp.add(l1, g1);
        
        t1 = new JTextField(10);
        g1.gridx =1;
        g1.gridy =0;
        cp.add(t1, g1);
        
        JLabel l2 = new JLabel("Contraseña:");
        g1.gridx =0;
        g1.gridy =1;
        cp.add(l2, g1);
        
        t2 = new JPasswordField(10);
        g1.gridx =1;
        g1.gridy =1;
        cp.add(t2, g1);
        
        b1 = new JButton("Iniciar Sesión");
        g1.gridx =0;
        g1.gridy =2;
        g1.gridwidth = 2;
        b1.addActionListener(this);
        b1.setActionCommand("iniciar");
        cp.add(b1, g1);
        
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        System.out.println("Comando :" + comando);
        
        switch(comando){
            case "iniciar":
<<<<<<< HEAD
                user = t1.getText();
=======

                /*user = t1.getText();
>>>>>>> 5f94735e6539767c880320cf0d97edb859ccf0a8
                contra = t2.getText();
                
                if ("Administrador".equals(cem.comprobarTipo(con, user, contra))){
                    llamarVentanaAdministrador();
                    setVisible(false);
                }else if("Secretaria".equals(cem.comprobarTipo(con, user, contra))){
                    llamarVentanaSecretaria();
                    setVisible(false);
                }else if("Médico".equals(cem.comprobarTipo(con, user, contra))){
                    llamarVentanaMedico();
                    setVisible(false);
                }else{
                    JOptionPane.showMessageDialog(null, "Vuelva a ingresar los datos");
                }
                /*
                user = t1.getText();
                if(user.equals("1")){
                    llamarVentanaAdministrador();
                    setVisible(false);
                }
                if(user.equals("2")){
                    llamarVentanaSecretaria();
                    setVisible(false);
                }
                if(user.equals("3")){
                    llamarVentanaMedico();
                    setVisible(false);
                }
*/
                break;
        }
     
    }
    
    public void llamarVentanaAdministrador(){
        VAdministrador vA = new VAdministrador(con,cca,cem,cpv,cpd,cc,cfc,cfd);
        vA.setVisible(true); 
    }
    
    public void llamarVentanaSecretaria(){
        VSecretaria vS = new VSecretaria(con,cca,cpd,cc,cm,ces,cr,cct,cfc,cfd,cd,crc,crd);
        vS.setVisible(true); 
    }
    
    public void llamarVentanaMedico(){
        VMedico vM = new VMedico(con,cca,cpd,cc,cm,ces,cr,cct,cfc,cfd,cd,crc,crd);
        vM.setVisible(true); 
    }
}
