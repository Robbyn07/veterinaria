/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexionbd;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Cliente;
import modelo.FacturaCabecera;
import modelo.FacturaDetalle;

/**
 *
 * @author plojam
 */
public class ControladorFacturaCabecera {
    
    private ResultSet resultado = null;
    private PreparedStatement sentencia = null;
    
    
    public List<FacturaCabecera> cabObtener(Conexion con){
      
        List<FacturaCabecera> cabeceras = new ArrayList<>();
        List<FacturaDetalle> detalles = new ArrayList<>();
       // Cliente cliente = new Cliente();
        ControladorCliente controladorCliente = new ControladorCliente();
        //ControladorFacturaDetalle controladorFacturaDetalle = new ControladorFacturaDetalle();
        try {
            sentencia = con.getConexion().prepareStatement("SELECT FAC_NUMEROF, FAC_FECHA, FAC_SUBTOTAL, FAC_IVA, "
                    + "FAC_DESCUENTO, FAC_TOTAL, FAC_METODOP, FAC_ESTADO, CLI_ID "
            + "FROM VETERINARIA.VET_FACTURA_CABECERAS "
            + "ORDER BY FAC_TOTAL DESC");
            resultado= sentencia.executeQuery();
            
            //Se presenta el resultado
            while(resultado.next()){
                FacturaCabecera facturaCabecera = new FacturaCabecera();
                int cabeceraNumero = resultado.getInt("fac_numerof");
                facturaCabecera.setFacturaCabeceraNumero(cabeceraNumero);
                facturaCabecera.setFacturaCabeceraFecha(resultado.getDate("fac_fecha"));
                facturaCabecera.setFacturaCabeceraSubtotal(resultado.getDouble("fac_subtotal"));
                facturaCabecera.setFacturaCabeceraIva(resultado.getDouble("fac_iva"));
                facturaCabecera.setFacturaCabeceraDescuento(resultado.getDouble("fac_descuento"));
                facturaCabecera.setFacturaCabeceraTotal(resultado.getDouble("fac_total"));
                facturaCabecera.setFacturaCabeceraTipoPago(resultado.getString("fac_metodop"));
                facturaCabecera.setFacturaCabeceraEstado(resultado.getString("fac_estado"));
                int clienteId = resultado.getInt("cli_id");
                
                Cliente cliente = controladorCliente.cliBuscarId(con, clienteId);
                facturaCabecera.setCliente(cliente);
                
                
                cabeceras.add(facturaCabecera);
                
            }
            
            return cabeceras;
            
        } catch (SQLException e) {
            e.printStackTrace();
            
            return null;
            
        }finally{
                
                if(sentencia !=null){
                    try {
                        sentencia.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(ControladorFacturaCabecera.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
            }
        
    }
    
   public FacturaCabecera cabBuscar(Conexion con, int facturaCabeceraNumero){
        FacturaCabecera facturaCabecera = new FacturaCabecera();
        List<FacturaDetalle> detalles = new ArrayList<>();
        Cliente cliente = new Cliente();
        ControladorCliente controladorCliente = new ControladorCliente();
        ControladorFacturaDetalle controladorFacturaDetalle = new ControladorFacturaDetalle();
        try {
            sentencia = con.getConexion().prepareStatement("SELECT fac_numerof, fac_fecha, fac_subtotal, fac_iva, "
                    + "fac_descuento, fac_total, fac_metodop, fac_estado, cli_id "
            + "FROM veterinaria.vet_factura_Cabeceras "
            + "WHERE fac_numerof = ?");
            sentencia.setInt(1, facturaCabeceraNumero);
            resultado= sentencia.executeQuery();
            
            //Se presenta el resultado
            if(resultado.next()==false){
                return null;
            }else{
                do{
                    int cabeceraNumero = resultado.getInt("fac_numerof");
                    facturaCabecera.setFacturaCabeceraNumero(cabeceraNumero);
                    facturaCabecera.setFacturaCabeceraFecha(resultado.getDate("fac_fecha"));
                    facturaCabecera.setFacturaCabeceraSubtotal(resultado.getDouble("fac_subtotal"));
                    facturaCabecera.setFacturaCabeceraIva(resultado.getDouble("fac_iva"));
                    facturaCabecera.setFacturaCabeceraDescuento(resultado.getDouble("fac_descuento"));
                    facturaCabecera.setFacturaCabeceraTotal(resultado.getDouble("fac_total"));
                    facturaCabecera.setFacturaCabeceraTipoPago(resultado.getString("fac_metodop"));
                    facturaCabecera.setFacturaCabeceraEstado(resultado.getString("fac_estado"));

                    int clienteId = resultado.getInt("cli_id");
                    cliente = controladorCliente.cliBuscarId(con, clienteId);
                    facturaCabecera.setCliente(cliente);



                    detalles = controladorFacturaDetalle.detObtener(con, cabeceraNumero);
                    for(int i=0; i<detalles.size();i++){
                        facturaCabecera.addFacturasDetalle(detalles.get(i));
                    }
                }while(resultado.next());
            }
            
            return facturaCabecera;
            
        } catch (SQLException e) {
            e.printStackTrace();
            
            return null;
            
        }finally{
                
                if(sentencia !=null){
                    try {
                        sentencia.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(ControladorFacturaCabecera.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
            }
        
    }
    
    
    public int obtenerId(Conexion con){
        int cabeceraNumero=0;
        try {
            
            sentencia = con.getConexion().prepareStatement("SELECT max(fac_numerof) "
            + "FROM veterinaria.vet_factura_cabeceras");
            resultado= sentencia.executeQuery();
            
            while(resultado.next()){
                cabeceraNumero = resultado.getInt("max(fac_numerof)");

            }
            
            return cabeceraNumero;

        } catch (SQLException e) {
            e.printStackTrace();

            return 0;

        }finally{
                
                if(sentencia !=null){
                    try {
                        sentencia.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(ControladorFacturaCabecera.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
            }

        
    }
    
    
    public boolean cabAgregar(Conexion con, FacturaCabecera facturaCabecera){
        ControladorFacturaDetalle controladorFacturaDetalle = new ControladorFacturaDetalle();
        ControladorFacturaCabecera controladorFacturaCabecera = new ControladorFacturaCabecera();
        
        if(cabBuscar(con, facturaCabecera.getFacturaCabeceraNumero())==null){
            System.out.println("Entra a agregar");
            
            System.out.println("Fecha que agrega"+facturaCabecera.getFacturaCabeceraFecha());
            try {

                sentencia = con.getConexion().prepareStatement("INSERT INTO veterinaria.vet_factura_Cabeceras VALUES "
                        + "(VETERINARIA.fac_numeroF_seq.nextval,'26/07/2019',?,?,?,?,'EFECTIVO','H',?)");

                //sentencia.setInt(1, facturaCabecera.getFacturaCabeceraNumero());
                //sentencia.setDate(1, (Date)"26/07/2019");
                sentencia.setDouble(1, facturaCabecera.getFacturaCabeceraSubtotal());
                sentencia.setDouble(2, facturaCabecera.getFacturaCabeceraIva());
                sentencia.setDouble(3, 0);
                sentencia.setDouble(4, facturaCabecera.getFacturaCabeceraTotal());
                //sentencia.setString(5, facturaCabecera.getFacturaCabeceraTipoPago());
                //sentencia.setString(5, facturaCabecera.getFacturaCabeceraEstado());
                sentencia.setInt(5, facturaCabecera.getCliente().getClienteId());
                
                sentencia.executeUpdate();
                
                int cabeceraNumero=controladorFacturaCabecera.obtenerId(con);
                
                //for(int i=0; i<facturaCabecera.getFacturasDetalle().size();i++){
                 //   controladorFacturaDetalle.detAgregar(con, facturaCabecera.getFacturasDetalle().get(i), cabeceraNumero);
                //}

                return true;
                
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }finally{
                
                if(sentencia !=null){
                    try {
                        sentencia.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(ControladorFacturaCabecera.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
            }
            
        } else
            return false;
        
    }
    
    
    public boolean cabCancelar (Conexion con, FacturaCabecera facturaCabecera){
        ControladorProducto controlarodProducto = new ControladorProducto();
        if(cabBuscar(con, facturaCabecera.getFacturaCabeceraNumero())!=null){
            try {
                
                sentencia = con.getConexion().prepareStatement("UPDATE veterinaria.vet_factura_Cabeceras SET "
                + "fac_estado=? "
                + "WHERE fac_numerof=?");

                sentencia.setString(1, facturaCabecera.getFacturaCabeceraEstado());
                sentencia.setInt(2, facturaCabecera.getFacturaCabeceraNumero());
                
                for(int i=0; i<facturaCabecera.getFacturasDetalle().size(); i++){
                    
                    controlarodProducto.agregarStock(con, facturaCabecera.getFacturasDetalle().get(i).getProducto(), 
                            facturaCabecera.getFacturasDetalle().get(i).getFacturaDetalleCantidad());
                    
                }
                
                sentencia.executeUpdate();
                
                return true;
                
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }finally{
                
                if(sentencia !=null){
                    try {
                        sentencia.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(ControladorFacturaCabecera.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
            }
            
        } else
            return false;
          
    }
    
    public boolean cabEditarMetodoPago (Conexion con, FacturaCabecera facturaCabecera){
        try {

            sentencia = con.getConexion().prepareStatement("UPDATE veterinaria.vet_factura_Cabeceras SET "
            + "fac_metodop=? "
            + "WHERE fac_numerof=?");

            sentencia.setString(1, facturaCabecera.getFacturaCabeceraTipoPago());
            sentencia.setInt(2, facturaCabecera.getFacturaCabeceraNumero());


            sentencia.executeUpdate();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }finally{
                
                if(sentencia !=null){
                    try {
                        sentencia.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(ControladorFacturaCabecera.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
            }
            
        
        
    }
    
    
    
}

