
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
import conexionbd.SentenciasCRUD;

/**
 *
 * @author Rakrad7101
 */
public class Main {
            
    public static void main(String [] args){
        
        Conexion con = new Conexion();
        /*SentenciasCRUD s = new SentenciasCRUD();
        
        con.setUrl("jdbc:oracle:thin:@localhost:1521:robbynbd");
        con.setUserName("Veterinaria");
        con.setPassword("lprt_54");
        
        con.conectar();
        
        if(con.getConexion()!= null){
            System.out.println("Base de datos conectada");
            
            ControladorCaracter cca = new ControladorCaracter();
            ControladorEmpleado cem = new ControladorEmpleado();
            ControladorProveedor cpv = new ControladorProveedor();
            ControladorProducto cpd = new ControladorProducto();
            ControladorCliente cc = new ControladorCliente();
            ControladorMascota cm = new ControladorMascota();
            ControladorEspecie ces = new ControladorEspecie();
            ControladorRaza cr = new ControladorRaza();
            ControladorCita cct = new ControladorCita();
            ControladorFacturaCabecera cfc = new ControladorFacturaCabecera();
            ControladorFacturaDetalle cfd = new ControladorFacturaDetalle();
            ControladorDiagnostico cd = new ControladorDiagnostico();
            ControladorRecetaCabecera crc = new ControladorRecetaCabecera();
            ControladorRecetaDetalle crd = new ControladorRecetaDetalle();

            VIniciarSesion vi = new VIniciarSesion(con,cca,cem,cpv,cpd,cc,cm,ces,cr,cct,
                                                    cfc,cfd,cd,crc,crd);
            vi.setVisible(true);
            
            con.cerrarConexion();
        }*/
        
        ControladorCaracter cca = new ControladorCaracter();
            ControladorEmpleado cem = new ControladorEmpleado();
            ControladorProveedor cpv = new ControladorProveedor();
            ControladorProducto cpd = new ControladorProducto();
            ControladorCliente cc = new ControladorCliente();
            ControladorMascota cm = new ControladorMascota();
            ControladorEspecie ces = new ControladorEspecie();
            ControladorRaza cr = new ControladorRaza();
            ControladorCita cct = new ControladorCita();
            ControladorFacturaCabecera cfc = new ControladorFacturaCabecera();
            ControladorFacturaDetalle cfd = new ControladorFacturaDetalle();
            ControladorDiagnostico cd = new ControladorDiagnostico();
            ControladorRecetaCabecera crc = new ControladorRecetaCabecera();
            ControladorRecetaDetalle crd = new ControladorRecetaDetalle();

            VIniciarSesion vi = new VIniciarSesion(con,cca,cem,cpv,cpd,cc,cm,ces,cr,cct,
                                                    cfc,cfd,cd,crc,crd);
            vi.setVisible(true);
        
    }
}




