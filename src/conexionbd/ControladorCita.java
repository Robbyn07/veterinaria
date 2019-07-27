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
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Cita;
import modelo.Diagnostico;
import modelo.Mascota;

/**
 *
 * @author Usuario
 */
public class ControladorCita {
    
    private ResultSet resultado = null;
    private PreparedStatement sentencia = null;
    
    /**
     * OBTIENE LISTA DE CITAS DE LA MASCOTA
     * @param con
     * @param mascotaId
     * @return 
     */
    public List<Cita> citObtenerMascota(Conexion con, int mascotaId){
        
        List<Cita> citas = new ArrayList<>();
        Diagnostico diagnosticos;
        ControladorDiagnostico controladorDiagnostico = new ControladorDiagnostico();
        
        try {
            sentencia = con.getConexion().prepareStatement("SELECT cit_id, cit_fecha "
            + "FROM VETERINARIA.vet_citas "
            + "WHERE mas_id = ?");
            sentencia.setInt(1, mascotaId);
            resultado= sentencia.executeQuery();

            //Se presenta el resultado
            while(resultado.next()){
                Cita cita = new Cita();
                int citaId = resultado.getInt("cit_id");
                cita.setCitaId(citaId);
                cita.setCitaFecha(resultado.getDate("cit_fecha"));
                
                citas.add(cita);
                
            }
            return citas;
            
        } catch (SQLException e) {
            e.printStackTrace();
            
            return null;
            
        }finally{
                
                if(sentencia !=null){
                    try {
                        sentencia.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(ControladorCita.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
            }
        
    }
    
    public List<Cita> citObtenerGeneral(Conexion con){
        
        List<Cita> citas = new ArrayList<>();
        ControladorMascota cm = new ControladorMascota();
        try {
            sentencia = con.getConexion().prepareStatement("SELECT cit_id, cit_fecha, MAS_ID "
            + "FROM veterinaria.vet_citas "
            + "ORDER BY cit_fecha");
            resultado= sentencia.executeQuery();

            if(resultado.next()==false){
                return null;
            }else{
                do{
                    Cita cita = new Cita();
                    int citaId = resultado.getInt("cit_id");
                    cita.setCitaId(citaId);
                    SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                    java.util.Date ts = resultado.getTimestamp("cit_fecha");
                    //String dddd= new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm").format(ts);
                    //Date date = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(dddd);
                    cita.setCitaFecha(ts);
                    //System.out.println(cita.getCitaFecha());
                    cita.setMascota(cm.masBuscar(con, resultado.getInt("MAS_ID")));

                    citas.add(cita);
                }while(resultado.next());
               
            }
            //Se presenta el resultado
            
            return citas;
            
        } catch (SQLException e) {
            e.printStackTrace();
            
            return null;
            
        }finally{
                
                if(sentencia !=null){
                    try {
                        sentencia.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(ControladorCita.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
            }
        
    }
    
    public Cita citBuscar(Conexion con, int citaId){
        Cita cita = new Cita();
       
        try {
            sentencia = con.getConexion().prepareStatement("SELECT cit_id, cit_fecha "
            + "FROM VETERINARIA.vet_citas "
            + "WHERE cit_id = ?");
            sentencia.setInt(1, citaId);
            resultado= sentencia.executeQuery();

            if(resultado.next()==false){
                return null;
            }else{
                do{
                    cita.setCitaId(resultado.getInt("cit_id"));
                    cita.setCitaFecha(resultado.getDate("cit_fecha"));
                }while(resultado.next());
               
            }
            
            return cita;
            
        } catch (SQLException e) {
            e.printStackTrace();
            
            return null;
            
        }finally{
                
                if(sentencia !=null){
                    try {
                        sentencia.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(ControladorCita.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
            }
        
    }
    
    
    public boolean citAgregar(Conexion con, Cita cita){
        
        if(citBuscar(con, cita.getCitaId())==null){
            try {

                sentencia = con.getConexion().prepareStatement(
                "INSERT INTO veterinaria.vet_citas VALUES (veterinaria.cit_id_seq.nextval,?,?)");

                //sentencia.setInt(1, cita.getCitaId());
                sentencia.setTimestamp(1, new Timestamp(cita.getCitaFecha().getTime()));
                sentencia.setInt(2, cita.getMascota().getMascotaId());

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
                        Logger.getLogger(ControladorCita.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
            }
            
        } else
            return false;
        
    }
    
    public boolean citEditar(Conexion con, Cita cita){
        cita =citBuscar2(con, cita);
        if(cita!=null){
            try {
                
                sentencia = con.getConexion().prepareStatement("UPDATE veterinaria.vet_citas SET "
                + "cit_id=?, cit_fecha=?, mas_id=? "
                + "WHERE cit_id=?");

                sentencia.setInt(1, cita.getCitaId());
                sentencia.setTimestamp(2, new Timestamp(cita.getCitaFecha().getTime()));
                sentencia.setInt(4, cita.getMascota().getMascotaId());
                sentencia.setInt(5, cita.getCitaId());

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
                        Logger.getLogger(ControladorCita.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
            }
            
        } else
            return false;
        
    }

    
    
    public int buscarMascotaId(Conexion con, int citaId){
        int masId=0;
        try {
            sentencia = con.getConexion().prepareStatement("SELECT mas_id "
            + "FROM VETERINARIA.vet_citas "
            + "WHERE cit_id = ?");
            sentencia.setInt(1, citaId);
            resultado= sentencia.executeQuery();

            //Se presenta el resultado
            while(resultado.next()){
                
                masId = resultado.getInt("mas_id");
                
            }
            
            return masId;
            
        } catch (SQLException e) {
            e.printStackTrace();
            
            return 0;
            
        }finally{
                
                if(sentencia !=null){
                    try {
                        sentencia.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(ControladorCita.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
            }
        
    }
    
    public Cita citBuscar2(Conexion con, Cita cita){
        
       
        try {
            sentencia = con.getConexion().prepareStatement("SELECT cit_id "
            + "FROM VETERINARIA.vet_citas "
            + "WHERE cit_fecha = ? "
            + "AND MAS_ID = ? ");
            sentencia.setTimestamp(1, new Timestamp(cita.getCitaFecha().getTime()));
            sentencia.setInt(2, cita.getMascota().getMascotaId());
            resultado= sentencia.executeQuery();

            if(resultado.next()==false){
                return null;
            }else{
                do{
                    cita.setCitaId(resultado.getInt("cit_id"));
                }while(resultado.next());
               
            }
            
            return cita;
            
        } catch (SQLException e) {
            e.printStackTrace();
            
            return null;
            
        }finally{
                
                if(sentencia !=null){
                    try {
                        sentencia.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(ControladorCita.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
            }
        
    }
    public boolean citEliminar(Conexion con, Cita cita){
        cita=this.citBuscar2(con, cita);
        if(citBuscar(con, cita.getCitaId())!=null){
            try {
                
                sentencia = con.getConexion().prepareStatement(
                 "DELETE FROM veterinaria.vet_citas  "
                + "WHERE cit_id=?");

                sentencia.setInt(1, cita.getCitaId());
                sentencia.executeUpdate();

                return true;
                
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }finally{
                if(sentencia!=null){
                    try {
                        sentencia.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(ControladorCita.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            
        } else
            return false;
        
    }
    
}


