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
import modelo.Cita;
import modelo.Diagnostico;

/**
 *
 * @author Usuario
 */
public class ControladorCita {
    
    private ResultSet resultado = null;
    private PreparedStatement sentencia = null;
    
    
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
                
                diagnosticos = controladorDiagnostico.diaObtener(con, citaId);
                
                cita.setDiagnostico(diagnosticos);
                
                citas.add(cita);
                
            }
            
            return citas;
            
        } catch (SQLException e) {
            e.printStackTrace();
            
            return null;
            
        }
        
    }
    
    public List<Cita> citObtenerGeneral(Conexion con){
        Cita cita = new Cita();
        List<Cita> citas = new ArrayList<>();
        Diagnostico diagnostico;
        ControladorDiagnostico controladorDiagnostico = new ControladorDiagnostico();
        
        try {
            sentencia = con.getConexion().prepareStatement("SELECT cit_id, cit_fecha "
            + "FROM vet_citas "
            + "ORDER BY cit_fecha");
            resultado= sentencia.executeQuery();

            //Se presenta el resultado
            while(resultado.next()){
                int citaId = resultado.getInt("cit_id");
                cita.setCitaId(citaId);
                cita.setCitaFecha(resultado.getDate("cit_fecha"));
                
                diagnostico = controladorDiagnostico.diaObtener(con, citaId);
                
                    cita.setDiagnostico(diagnostico);
                
                citas.add(cita);
                
            }
            
            return citas;
            
        } catch (SQLException e) {
            e.printStackTrace();
            
            return null;
            
        }
        
    }
    
    public Cita citBuscar(Conexion con, int citaId){
        Cita cita = new Cita();
        Diagnostico diagnostico;
        ControladorDiagnostico controladorDiagnostico = new ControladorDiagnostico();
        
        try {
            sentencia = con.getConexion().prepareStatement("SELECT cit_id, cit_fecha "
            + "FROM vet_citas "
            + "WHERE cit_id = ?");
            sentencia.setInt(1, citaId);
            resultado= sentencia.executeQuery();

            //Se presenta el resultado
            while(resultado.next()){
                cita.setCitaId(resultado.getInt("cit_id"));
                cita.setCitaFecha(resultado.getDate("cit_fecha"));
                
                diagnostico = controladorDiagnostico.diaObtener(con, citaId);
                
                    cita.setDiagnostico(diagnostico);
                
                
            }
            
            return cita;
            
        } catch (SQLException e) {
            e.printStackTrace();
            
            return null;
            
        }
        
    }
    
    
    public boolean citAgregar(Conexion con, Cita cita, int mascotaId){
        
        if(citBuscar(con, cita.getCitaId())==null){
            try {

                sentencia = con.getConexion().prepareStatement("INSERT INTO vet_citas VALUES (cit_id_seq.nextval,?,?)");

                //sentencia.setInt(1, cita.getCitaId());
                sentencia.setDate(1, (Date) cita.getCitaFecha());
                sentencia.setInt(2, mascotaId);

                sentencia.executeUpdate();

                return true;
                
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
            
        } else
            return false;
        
    }
    
    public boolean citEditar(Conexion con, Cita cita, int mascotaId){
        
        if(citBuscar(con, cita.getCitaId())!=null){
            try {
                
                sentencia = con.getConexion().prepareStatement("UPDATE vet_citas SET "
                + "cit_id=?, cit_fecha=?, mas_id=? "
                + "WHERE cit_id=?");

                sentencia.setInt(1, cita.getCitaId());
                sentencia.setDate(2, (Date) cita.getCitaFecha());
                sentencia.setInt(4, mascotaId);
                sentencia.setInt(5, cita.getCitaId());

                sentencia.executeUpdate();

                return true;
                
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
            
        } else
            return false;
        
    }
    //metodo agregado
    public boolean citEliminar(Conexion con, int id){
        
        if(citBuscar(con, id)!=null){
            try {
                
                sentencia = con.getConexion().prepareStatement("DELETE FROM vet_citas  "
                + "WHERE cit_id=?");

                sentencia.setInt(1, id);
                sentencia.executeUpdate();

                return true;
                
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
            
        } else
            return false;
        
    }

    
    
    public int buscarMascotaId(Conexion con, int citaId){
        int masId=0;
        try {
            sentencia = con.getConexion().prepareStatement("SELECT mas_id "
            + "FROM vet_citas "
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
            
        }
        
    }
    
    
}


