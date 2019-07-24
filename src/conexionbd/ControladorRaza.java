/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexionbd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Raza;

/**
 *
 * @author Usuario
 */
public class ControladorRaza {
    
    private ResultSet resultado = null;
    private PreparedStatement sentencia = null;
    
    
    public Raza razBuscar(Conexion con, String razaNombre){
        Raza raza = new Raza();
        try {
            sentencia = con.getConexion().prepareStatement("SELECT raz_id, raz_nombre "
            + "FROM vet_razas "
            + "WHERE UPPER(raz_nombre) = UPPER(?)");
            sentencia.setString(1, razaNombre);
            resultado= sentencia.executeQuery();

            //Se presenta el resultado
            while(resultado.next()){
                raza.setRazaId(resultado.getInt("raz_id"));
                raza.setRazaNombre(resultado.getString("raz_nombre"));
            }
            
            return raza;
            
        } catch (SQLException e) {
            e.printStackTrace();
            
            return null;
            
        }
        
    }
    
    public Raza razBuscarId(Conexion con, int razaId){
        Raza raza = new Raza();
        try {
            sentencia = con.getConexion().prepareStatement("SELECT raz_id, raz_nombre "
            + "FROM VETERINARIA.vet_razas "
            + "WHERE raz_id = ?");
            sentencia.setInt(1, razaId);
            resultado= sentencia.executeQuery();

            //Se presenta el resultado
            while(resultado.next()){
                raza.setRazaId(resultado.getInt("raz_id"));
                raza.setRazaNombre(resultado.getString("raz_nombre"));
            }
            
            return raza;
            
        } catch (SQLException e) {
            e.printStackTrace();
            
            return null;
            
        }
        
    }
    
    
    
    public boolean razAgregar(Conexion con, Raza raza){
        
        if(razBuscar(con, raza.getRazaNombre())==null){
            try {

                sentencia = con.getConexion().prepareStatement("INSERT INTO vet_razas VALUES (raz_id_seq.nextval,?)");

                //sentencia.setInt(1, raza.getRazaId());
                sentencia.setString(1, raza.getRazaNombre());

                sentencia.executeUpdate();

                return true;
                
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
            
        } else
            return false;
        
    }
    
    public boolean razEditar(Conexion con, Raza raza){
        
        if(razBuscar(con, raza.getRazaNombre())!=null){
            try {

                sentencia = con.getConexion().prepareStatement("UPDATE vet_razas SET "
                + "raz_nombre=? "
                + "WHERE raz_id=?");

                sentencia.setString(1, raza.getRazaNombre());
                sentencia.setInt(2, raza.getRazaId());

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


