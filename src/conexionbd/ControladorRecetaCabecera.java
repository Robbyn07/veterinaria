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
import modelo.RecetaCabecera;
import modelo.RecetaDetalle;

/**
 *
 * @author Usuario
 */
public class ControladorRecetaCabecera {
    
    private ResultSet resultado = null;
    private PreparedStatement sentencia = null;
    
    
    public RecetaCabecera recCabBuscar(Conexion con, int diagnosticoId){
        RecetaCabecera recetaCabecera = new RecetaCabecera();
        List<RecetaDetalle> recetaDetalle = new ArrayList<>();
        ControladorRecetaDetalle controladorRecetaDetalle = new ControladorRecetaDetalle();
        
        try {
            sentencia = con.getConexion().prepareStatement("SELECT rec_id, rec_fecha "
            + "FROM VETERINARIA.vet_receta_Cabeceras "
            + "WHERE dia_id = ?");
            sentencia.setInt(1, diagnosticoId);
            resultado= sentencia.executeQuery();

            //Se presenta el resultado
            while(resultado.next()){
                int recetaCabeceraId = resultado.getInt("rec_id");
                recetaCabecera.setRecetaCabeceraId(recetaCabeceraId);
                recetaCabecera.setRecetaCabeceraFecha(resultado.getDate("rec_fecha"));
                
                recetaDetalle = controladorRecetaDetalle.recDetObtener(con, recetaCabeceraId);
                for(int i=0; i<recetaDetalle.size();i++){
                    recetaCabecera.addRecetasDetalle(recetaDetalle.get(i));
                }
                
            }
            
            return recetaCabecera;
            
        } catch (SQLException e) {
            e.printStackTrace();
            
            return null;
            
        }finally{
                
                if(sentencia !=null){
                    try {
                        sentencia.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(ControladorRecetaCabecera.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
            }
        
    }
    
    
    public RecetaCabecera recCabBuscarCabecera(Conexion con, int recetaCabeceraId){
        RecetaCabecera recetaCabecera = new RecetaCabecera();
        List<RecetaDetalle> recetaDetalle = new ArrayList<>();
        ControladorRecetaDetalle controladorRecetaDetalle = new ControladorRecetaDetalle();
        
        try {
            sentencia = con.getConexion().prepareStatement("SELECT rec_id, rec_fecha "
            + "FROM veterinaria.vet_receta_Cabeceras "
            + "WHERE rec_id = ?");
            sentencia.setInt(1, recetaCabeceraId);
            resultado= sentencia.executeQuery();

            //Se presenta el resultado
            while(resultado.next()){
                int cabeceraId = resultado.getInt("rec_id");
                recetaCabecera.setRecetaCabeceraId(recetaCabeceraId);
                recetaCabecera.setRecetaCabeceraFecha(resultado.getDate("rec_fecha"));
                
                recetaDetalle = controladorRecetaDetalle.recDetObtener(con, cabeceraId);
                for(int i=0; i<recetaDetalle.size();i++){
                    recetaCabecera.addRecetasDetalle(recetaDetalle.get(i));
                }
                
            }
            
            return recetaCabecera;
            
        } catch (SQLException e) {
            e.printStackTrace();
            
            return null;
            
        }finally{
                
                if(sentencia !=null){
                    try {
                        sentencia.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(ControladorRecetaCabecera.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
            }
        
    }
    
    
    
    
    public boolean recCabAgregar(Conexion con, RecetaCabecera recetaCabecera, int diagnosticoId){
        ControladorRecetaDetalle controladorRecetaDetalle = new ControladorRecetaDetalle();
        if(recCabBuscarCabecera(con, recetaCabecera.getRecetaCabeceraId())==null){
            try {

                sentencia = con.getConexion().prepareStatement("INSERT INTO veterinaria.vet_receta_Cabeceras VALUES (VETERINARIA.rec_id_seq.nextval,'26/07/2019',?)");

                //sentencia.setInt(1, recetaCabecera.getRecetaCabeceraId());
                //sentencia.setDate(1, (Date) recetaCabecera.getRecetaCabeceraFecha());
                sentencia.setInt(1, diagnosticoId);
                
                sentencia.executeUpdate();
                /*
                for(int i=0; i<recetaCabecera.getRecetasDetalle().size();i++){
                    controladorRecetaDetalle.recDetAgregar(con, recetaCabecera.getRecetasDetalle().get(i), recetaCabecera.getRecetaCabeceraId());
                }*/

                return true;
                
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }finally{
                
                if(sentencia !=null){
                    try {
                        sentencia.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(ControladorRecetaCabecera.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
            }
            
        } else
            return false;
        
    }
    
      public int obtenerId(Conexion con){
        int recId=0;
        
        try {          
            sentencia = con.getConexion().prepareStatement("SELECT max(rec_id) "
            + "FROM veterinaria.vet_receta_Cabeceras");
            resultado= sentencia.executeQuery();

            while(resultado.next()){
                recId = resultado.getInt("max(rec_id)");

            }
            return recId;

        } catch (SQLException e) {
            e.printStackTrace();

            return 0;
        } 
    }
}


