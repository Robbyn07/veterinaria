/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexionbd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Especie;

/**
 *
 * @author Usuario
 */
public class ControladorEspecie {
    
    private ResultSet resultado = null;
    private PreparedStatement sentencia = null;
    
    
    public Especie espBuscar(Conexion con, String especieNombre){
        Especie especie = new Especie();
        try {
            sentencia = con.getConexion().prepareStatement("SELECT esp_id, esp_nombre "
            + "FROM vet_especies "
            + "WHERE UPPER(esp_nombre) = UPPER(?)");
            sentencia.setString(1, especieNombre);
            resultado= sentencia.executeQuery();

            //Se presenta el resultado
            while(resultado.next()){
                especie.setEspecieId(resultado.getInt("esp_id"));
                especie.setEspecieNombre(resultado.getString("esp_nombre"));
            }
            
            return especie;
            
        } catch (SQLException e) {
            e.printStackTrace();
            
            return null;
            
        }
        
    }
    
    public Especie espBuscarId(Conexion con, int especieId){
       Especie especie=null;
        try {
            sentencia = con.getConexion().prepareStatement("SELECT esp_id, esp_nombre "
            + "FROM VETERINARIA.vet_especies "
            + "WHERE esp_id = ?");
            sentencia.setInt(1, especieId);
            resultado= sentencia.executeQuery();

            //Se presenta el resultado
            while(resultado.next()){
                especie = new Especie();
                especie.setEspecieId(resultado.getInt("esp_id"));
                especie.setEspecieNombre(resultado.getString("esp_nombre"));
            }
            
            return especie;
            
        } catch (SQLException e) {
            e.printStackTrace();
            
            return null;
            
        }
        
    }
    
    
    
    public boolean espAgregar(Conexion con, Especie especie){
        
        if(espBuscar(con, especie.getEspecieNombre())==null){
            try {

                sentencia = con.getConexion().prepareStatement("INSERT INTO vet_especies VALUES (esp_id_seq.nextval,?)");

                //sentencia.setInt(1, especie.getEspecieId());
                sentencia.setString(1, especie.getEspecieNombre());

                sentencia.executeUpdate();

                return true;
                
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
            
        } else
            return false;
        
    }
    
    public boolean razEditar(Conexion con, Especie especie){
        
        if(espBuscar(con, especie.getEspecieNombre())!=null){
            try {

                sentencia = con.getConexion().prepareStatement("UPDATE vet_especies SET "
                + "esp_nombre=? "
                + "WHERE esp_id=?");

                sentencia.setString(1, especie.getEspecieNombre());
                sentencia.setInt(2, especie.getEspecieId());

                sentencia.executeUpdate();

                return true;
                
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
            
        } else
            return false;
        
            
        
    }
    
}




