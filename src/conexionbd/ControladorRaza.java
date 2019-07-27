/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexionbd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
            + "FROM veterinaria.vet_razas "
            + "WHERE UPPER(raz_nombre) = UPPER(?)");
            sentencia.setString(1, razaNombre);
            resultado= sentencia.executeQuery();

            //Se presenta el resultado
            if(resultado.next()==false){
                raza=null;
            }else{
                do{
                    raza.setRazaId(resultado.getInt("raz_id"));
                    raza.setRazaNombre(resultado.getString("raz_nombre"));
                }while(resultado.next());
            }
            return raza;
            
        } catch (SQLException e) {
            e.printStackTrace();
            
            return null;
            
        }finally{
                
                if(sentencia !=null){
                    try {
                        sentencia.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(ControladorRaza.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
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
               
            if(resultado.next()==false){
                raza=null;
            }else{
                do{
                    raza.setRazaId(resultado.getInt("raz_id"));
                    raza.setRazaNombre(resultado.getString("raz_nombre"));
                }while(resultado.next());
            }
            //Se presenta el resultado
            
            return raza;
            
        } catch (SQLException e) {
            e.printStackTrace();
            
            return null;
            
        }finally{
                
                if(sentencia !=null){
                    try {
                        sentencia.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(ControladorRaza.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
            }
        
    }
    
    
    
    public boolean razAgregar(Conexion con, Raza raza){
        
        if(razBuscar(con, raza.getRazaNombre())==null){
            try {

                sentencia = con.getConexion().prepareStatement("INSERT INTO veterinaria.vet_razas VALUES (VETERINARIA.raz_id_seq.nextval,?)");

                //sentencia.setInt(1, raza.getRazaId());
                sentencia.setString(1, raza.getRazaNombre());

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
                        Logger.getLogger(ControladorRaza.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
            }
            
        } else
            return false;
        
    }
    
    public boolean razEditar(Conexion con, Raza raza){
        
        if(razBuscar(con, raza.getRazaNombre())!=null){
            try {

                sentencia = con.getConexion().prepareStatement("UPDATE veterinaria.vet_razas SET "
                + "raz_nombre=? "
                + "WHERE raz_id=?");

                sentencia.setString(1, raza.getRazaNombre());
                sentencia.setInt(2, raza.getRazaId());

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
                        Logger.getLogger(ControladorRaza.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
            }
            
        } else
            return false;
        
            
        
    }
    
}


