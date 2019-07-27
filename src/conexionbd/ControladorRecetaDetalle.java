/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexionbd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.RecetaDetalle;

/**
 *
 * @author Usuario
 */
public class ControladorRecetaDetalle {
    
    
    private ResultSet resultado = null;
    private PreparedStatement sentencia = null;
    
    
    public List<RecetaDetalle> recDetObtener(Conexion con, int recetaCabeceraId){
        RecetaDetalle recetaDetalle = new RecetaDetalle();
        List<RecetaDetalle> detalles = new ArrayList<>();
        
        try {
            sentencia = con.getConexion().prepareStatement("SELECT rde_id, rde_nombre, rde_cantidad, rde_dosificacion "
            + "FROM VETERINARIA.vet_receta_Detalles "
            + "WHERE rec_id = ?");
            sentencia.setInt(1, recetaCabeceraId);
            resultado= sentencia.executeQuery();

            //Se presenta el resultado
            while(resultado.next()){
                recetaDetalle.setRecetaDetalleId(resultado.getInt("rde_id"));
                recetaDetalle.setRecetaDetalleNombre(resultado.getString("rde_nombre"));
                recetaDetalle.setRecetaDetalleCantidad(resultado.getString("rde_cantidad"));
                recetaDetalle.setRecetaDetalleDosificacion(resultado.getString("rde_dosificacion"));
                detalles.add(recetaDetalle);
            }
            
            return detalles;
            
        } catch (SQLException e) {
            e.printStackTrace();
            
            return null;
            
        }finally{
                
                if(sentencia !=null){
                    try {
                        sentencia.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(ControladorRecetaDetalle.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
            }
        
    }
    
    
    public RecetaDetalle recDetBuscar(Conexion con, int recetaDetalleId){
        RecetaDetalle recetaDetalle = new RecetaDetalle();
        
        try {
            sentencia = con.getConexion().prepareStatement("SELECT rde_id, rde_nombre, rde_cantidad, rde_dosificacion "
            + "FROM VETERINARIA.vet_receta_Detalles "
            + "WHERE rde_id = ?");
            sentencia.setInt(1, recetaDetalleId);
            resultado= sentencia.executeQuery();

            //Se presenta el resultado
            while(resultado.next()){
                recetaDetalle.setRecetaDetalleId(resultado.getInt("rde_id"));
                recetaDetalle.setRecetaDetalleNombre(resultado.getString("rde_nombre"));
                recetaDetalle.setRecetaDetalleCantidad(resultado.getString("rde_cantidad"));
                recetaDetalle.setRecetaDetalleDosificacion(resultado.getString("rde_dosificacion"));
            }
            
            return recetaDetalle;
            
        } catch (SQLException e) {
            e.printStackTrace();
            
            return null;
            
        }finally{
                
                if(sentencia !=null){
                    try {
                        sentencia.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(ControladorRecetaDetalle.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
            }
        
    }

    public boolean recDetAgregar(Conexion con, RecetaDetalle recetaDetalle, int recetaCabeceraId){
        
        if(recDetBuscar(con, recetaDetalle.getRecetaDetalleId())==null){
            try {

                sentencia = con.getConexion().prepareStatement("INSERT INTO VETERINARIA.vet_receta_Detalles VALUES (VETERINARIA.rde_id_seq.nextval,?,?,?,?)");

                //sentencia.setInt(1, recetaDetalle.getRecetaDetalleId());
                sentencia.setString(1, recetaDetalle.getRecetaDetalleNombre());
                sentencia.setString(2, recetaDetalle.getRecetaDetalleCantidad());
                sentencia.setString(3, recetaDetalle.getRecetaDetalleDosificacion());
                sentencia.setInt(4, recetaCabeceraId);

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
                        Logger.getLogger(ControladorRecetaDetalle.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
            }
            
        } else
            return false;
        
    }
    
}



