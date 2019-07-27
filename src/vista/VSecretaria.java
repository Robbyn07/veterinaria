
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
import conexionbd.ControladorRaza;
import conexionbd.ControladorRecetaCabecera;
import conexionbd.ControladorRecetaDetalle;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import modelo.Empleado;

/**
 *
 * @author Rakrad7101
 */
public class VSecretaria extends JFrame implements ActionListener{
    
    private JDesktopPane escritorioS;
    Conexion con;
    ControladorCaracter cca;
    ControladorEmpleado cem;
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
    Empleado emp;
    
    public VSecretaria(Conexion con,ControladorCaracter cca,ControladorEmpleado cem,
            ControladorProducto cpd,ControladorCliente cc,ControladorMascota cm,
            ControladorEspecie ces,ControladorRaza cr,ControladorCita cct,
            ControladorFacturaCabecera cfc,ControladorFacturaDetalle cfd,
            ControladorDiagnostico cd,ControladorRecetaCabecera crc,
            ControladorRecetaDetalle crd, Empleado emp){
        this.con = con;
        this.cca = cca;
        this.cem = cem;
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
        this.emp=emp;
        escritorioS = new JDesktopPane();
        initComponentes();
        ventanaSecretaria();
    }
    
    public void initComponentes(){
        setSize(1600,800);
        setTitle("Ventana Secretaría");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void ventanaSecretaria(){
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(escritorioS, BorderLayout.CENTER);
        
        //crear barra de menu
        JMenuBar barra = new JMenuBar();
                   
        //Agrega opciones a la barra de menu
        JMenu m1 = new JMenu("Clientes");
            //Ingresa items en la opcion
            JMenuItem i1 = new JMenuItem("Listar Clientes");
            i1.addActionListener(this);
            i1.setActionCommand("listarCl");
            m1.add(i1);
            
            JMenuItem i2 = new JMenuItem("Agregar Clientes");
            i2.addActionListener(this);
            i2.setActionCommand("agregarCl");
            m1.add(i2);
            
            JMenuItem i3 = new JMenuItem("Modificar Clientes");
            i3.addActionListener(this);
            i3.setActionCommand("modificarCl");
            m1.add(i3);
            
            JMenuItem i4 = new JMenuItem("Agregar Mascota");
            i4.addActionListener(this);
            i4.setActionCommand("agregarM");
            m1.add(i4);
            
            JMenuItem i5 = new JMenuItem("Modificar Mascota");
            i5.addActionListener(this);
            i5.setActionCommand("modificarM");
            m1.add(i5);
            
        barra.add(m1);
        
        //Agrega opciones a la barra de menu
        JMenu m2 = new JMenu("Citas");
            //Ingresa items en la opcion
            
            JMenuItem i6 = new JMenuItem("Realizar Cita");
            i6.addActionListener(this);
            i6.setActionCommand("agregarC");
            m2.add(i6);
            
            JMenuItem i7 = new JMenuItem("Modificar Citas");
            i7.addActionListener(this);
            i7.setActionCommand("modificarC");
            m2.add(i7);
            
        barra.add(m2);
        
        //Añade la opcion de prestamo a la barra de menu
        JMenu m3 = new JMenu("Facturas");
            //Ingresa items en la opcion
            JMenuItem i8 = new JMenuItem("Realizar Factura");
            i8.addActionListener(this);
            i8.setActionCommand("realizarF");
            m3.add(i8);
            
            JMenuItem i9 = new JMenuItem("Anular Facturas");
            i9.addActionListener(this);
            i9.setActionCommand("anularF");
            m3.add(i9);
   
        barra.add(m3);
       
        JMenu m4 = new JMenu("Cuenta");
            //Ingresar items en la opción
            JMenuItem i10 = new JMenuItem("Cambiar Contraseña");
            i10.addActionListener(this);
            i10.setActionCommand("cambiarContra");
            m4.add(i10);
            
            JMenuItem i11 = new JMenuItem("Cerrar Sesión");
            i11.addActionListener(this);
            i11.setActionCommand("cerrarS");
            m4.add(i11);
           
        barra.add(m4);
        
       setJMenuBar(barra);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        System.out.println("Comando :" + comando);
        
        switch(comando){
            case "listarCl":
                llamarVentanaListarCl();
                break;
            
            case "agregarCl":
                llamarVentanaAgregarCl();
                break;
            
            case "modificarCl":
                llamarVentanaModificarCl();
                break;
            
            case "agregarM":
                llamarVentanaAgregarM();
                break;
            
            case "modificarM":
                llamarVentanaModificarM();
                break;    
                    
            case "agregarC":
                llamarVentanaRealizarC();
                break;
            
            case "modificarC":
                llamarVentanaModificarC();
                break;
                    
            case "realizarF":
                llamarVentanaRealizarF();
                break;
            
            case "anularF":
                llamarVentanaAnularF();
                break;
            
            case "cambiarContra":
                llamarVentanaEditarC();
                break;    
                
            case "cerrarS":
                llamarVentanaIniciarS();
                con.cerrarConexion();
                break;

        }
    }
    
    public void llamarVentanaListarCl(){
        VListarCliente vlcl = new VListarCliente(con,cc);
        vlcl.setVisible(true);
        
        escritorioS.add(vlcl);
    }
    
    public void llamarVentanaAgregarCl(){
        VAgregarCliente vacl = new VAgregarCliente(con,cca,cc);
        vacl.setVisible(true);
        
        escritorioS.add(vacl);
    }
    
    public void llamarVentanaModificarCl(){
        VModificarCliente vmcl = new VModificarCliente(con,cca,cc);
        vmcl.setVisible(true);
        
        escritorioS.add(vmcl);
    }
    
    public void llamarVentanaAgregarM(){
        VAgregarMascota vam = new VAgregarMascota(con,cca,cc,cm,ces,cr);
        vam.setVisible(true);
        
        escritorioS.add(vam);
    }
    
    public void llamarVentanaModificarM(){
        VModificarMascota vmm = new VModificarMascota(con,cca,cc,cm,ces,cr);
        vmm.setVisible(true);
    
        escritorioS.add(vmm);
    }
    
    public void llamarVentanaRealizarC(){
        VRealizarCita vrc = new VRealizarCita(con,cca,cc,cm,cct);
        vrc.setVisible(true);
        
        escritorioS.add(vrc);
    }
    
    public void llamarVentanaModificarC(){
        VModificarCita vmdc = new VModificarCita(con,cca,cc,cm,cct);
        vmdc.setVisible(true);
        
        escritorioS.add(vmdc);
    }
    
    public void llamarVentanaRealizarF(){
        VRealizarFactura vrf = new VRealizarFactura(con,cca,cpd,cc,cfc,cfd);
        vrf.setVisible(true);
        
        escritorioS.add(vrf);
    }
    
    public void llamarVentanaAnularF(){
        VAnularFactura vaf = new VAnularFactura(con,cpd,cc,cfc,cfd);
        vaf.setVisible(true);
    
        escritorioS.add(vaf);
    }
    
    public void llamarVentanaEditarC(){
        VCambiarContrasena vcc = new VCambiarContrasena(con,cem,emp);
        vcc.setVisible(true);
        
        escritorioS.add(vcc);
    }
    
    public void llamarVentanaIniciarS() {
        VIniciarSesion vI = new VIniciarSesion(con,cca,null,null,cpd,cc,cm,ces,cr,cct,
                cfc,cfd,cd,crc,crd);
        vI.setVisible(true);
        
        this.setVisible(false);
    }

}
