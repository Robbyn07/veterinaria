/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import java.beans.PropertyVetoException;
import javax.swing.*;

/**
 *
 * @author Usuario
 */
public class VMedico extends JFrame implements ActionListener{
    
    private JDesktopPane escritorioM;
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
    
    public VMedico(Conexion con,ControladorCaracter cca,ControladorEmpleado cem,
            ControladorProducto cpd,ControladorCliente cc,ControladorMascota cm,
            ControladorEspecie ces,ControladorRaza cr,ControladorCita cct,
            ControladorFacturaCabecera cfc,ControladorFacturaDetalle cfd,
            ControladorDiagnostico cd,ControladorRecetaCabecera crc,
            ControladorRecetaDetalle crd){
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
        escritorioM = new JDesktopPane();
        ventanaMedico ();
        initComponentes();
             
    }
    private void initComponentes() {
        setSize(1100,800);
        setTitle("Ventana Medico");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
     
    private void ventanaMedico(){
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(escritorioM, BorderLayout.CENTER);
        
        //crear barra de menu
        JMenuBar barra = new JMenuBar();
                   
        //Agrega opciones a la barra de menu
        JMenu m1 = new JMenu("Paciente");
            //Ingresa items en la opcion
            JMenuItem i1 = new JMenuItem("Diagnostico");
            i1.addActionListener(this);
            i1.setActionCommand("DiagnosticoM");
            m1.add(i1);
            
            JMenuItem i2 = new JMenuItem("Historial");
            i2.addActionListener(this);
            i2.setActionCommand("HistorialM");
            m1.add(i2);
            
        barra.add(m1);
        
        JMenu m2 = new JMenu("Cerrar Sesion");
            //Ingresa items en la opcion
            JMenuItem i4 = new JMenuItem("Cerrar");
            i4.addActionListener(this);
            i4.setActionCommand("CerrarS");
            m2.add(i4);
        barra.add(m2);   
        setJMenuBar(barra);
       
    }
  
    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        System.out.println("Comando: "+ comando);
        
        switch(comando){
            case "DiagnosticoM":
                llamarVentanaDiagnosticoM();
                break;
            
            case "HistorialM":
                llamarVentanaHistorialM();
                break;
            
            case "CerrarS":
                llamarVentanaCerrarS();
                con.cerrarConexion();
                break;
      
        }
    }

    private void llamarVentanaDiagnosticoM() {
        VDiagnostico vD = new VDiagnostico();
        vD.setVisible(true);
        
        escritorioM.add(vD);
        try {
	vD.setSelected(true);
	} catch (PropertyVetoException e) {
		e.printStackTrace();
	}
    }

    private void llamarVentanaHistorialM() {
        VHistorial vH = new VHistorial();
        vH.setVisible(true);
        
        escritorioM.add(vH);
        try {
	vH.setSelected(true);
	} catch (PropertyVetoException e) {
		e.printStackTrace();
	}
    }

    private void llamarVentanaCerrarS() {
        VIniciarSesion vIS = new VIniciarSesion(con,cca,null,null,cpd,cc,cm,ces,cr,cct,
                cfc,cfd,cd,crc,crd);
        vIS.setVisible(true);
        
        setVisible(false);
    }
                   
}
