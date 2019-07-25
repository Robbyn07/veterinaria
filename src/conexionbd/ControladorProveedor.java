/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexionbd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Proveedor;

/**
 *
 * @author plojam
 */
public class ControladorProveedor {
    
    private ResultSet resultado = null;
    private PreparedStatement sentencia = null;
    
    
    public Proveedor pvdBuscar(Conexion con, String ruc){
         Proveedor proveedor = new Proveedor();
        //proveedor=null;
        //System.out.println("Entra a esta huevada de buscar");
        try {
            sentencia = con.getConexion().prepareStatement("SELECT prv_id, PRV_RAZONS, PRV_RUC, PRV_CORREO, PRV_DIRECCION "
                    + "FROM veterinaria.vet_proveedores "
                    + "WHERE prv_ruc = ?");
            sentencia.setString(1, ruc);
            resultado= sentencia.executeQuery();

            //Se presenta el resultado
            if(resultado.next()==false){
                proveedor = null;
            }else{
                do{
                    proveedor.setProveedorId(resultado.getInt("prv_id"));
                    proveedor.setProveedorRazonSocial(resultado.getString("prv_razons"));
                    proveedor.setProveedorRuc(resultado.getString("prv_ruc"));
                    proveedor.setProveedorEmail(resultado.getString("prv_correo"));
                    proveedor.setProveedorDireccion(resultado.getString("prv_direccion"));
                }while(resultado.next());
            }
            
            
            return proveedor;
            
        } catch (SQLException e) {
            e.printStackTrace();
            
            return null;
            
        }
       
    }
    
    
    public Proveedor pvdBuscarNombre(Conexion con, String nombre){
        Proveedor proveedor = new Proveedor();
        try {
            sentencia = con.getConexion().prepareStatement("SELECT prv_id, prv_razons, prv_ruc, prv_correo, prv_direccion "
            + "FROM veterinaria.vet_proveedores "
            + "WHERE prv_ruc = ?");
            sentencia.setString(1, nombre.toUpperCase());
            resultado= sentencia.executeQuery();

            //Se presenta el resultado
            while(resultado.next()){
                
                proveedor.setProveedorId(resultado.getInt("prv_id"));
                proveedor.setProveedorRazonSocial(resultado.getString("prv_razons"));
                proveedor.setProveedorRuc(resultado.getString("prv_ruc"));
                proveedor.setProveedorEmail(resultado.getString("prv_correo"));
                proveedor.setProveedorDireccion(resultado.getString("prv_direccion"));
            }
            
            return proveedor;
            
        } catch (SQLException e) {
            e.printStackTrace();
            
            return null;
            
        }
        
    }
            
            
    public Proveedor pvdBuscarId(Conexion con, int proveedorId){
        Proveedor proveedor = new Proveedor();
        try {
            sentencia = con.getConexion().prepareStatement("SELECT prv_id, prv_razons, prv_ruc, prv_correo, prv_direccion "
            + "FROM VETERINARIA.vet_proveedores "
            + "WHERE prv_id = ?");
            sentencia.setInt(1, proveedorId);
            resultado= sentencia.executeQuery();

            //Se presenta el resultado
            if(resultado.next()==false){
                proveedor=null;
            }else{
                do{
                    
                proveedor.setProveedorId(resultado.getInt("prv_id"));
                proveedor.setProveedorRazonSocial(resultado.getString("prv_razons"));
                proveedor.setProveedorRuc(resultado.getString("prv_ruc"));
                proveedor.setProveedorEmail(resultado.getString("prv_correo"));
                proveedor.setProveedorDireccion(resultado.getString("prv_direccion"));
            
                }while(resultado.next());
            }
            
            return proveedor;
            
        } catch (SQLException e) {
            e.printStackTrace();
            
            return null;
            
        }
       
    }
    
    
    public boolean pvdAgregar(Conexion con, Proveedor proveedor){
        System.out.println("Entra a metodo");
        if(pvdBuscar(con, proveedor.getProveedorRuc())==null){
            try {
                System.out.println("Entra a sentencia");
                sentencia = con.getConexion().prepareStatement("INSERT INTO VETERINARIA.vet_proveedores VALUES "
                        + "(VETERINARIA.prv_id_seq.nextval,UPPER(?),UPPER(?),UPPER(?),UPPER(?),?)");

                //sentencia.setInt(1, proveedor.getProveedorId());
                sentencia.setString(1, proveedor.getProveedorRuc());
                sentencia.setString(2, proveedor.getProveedorRazonSocial());
                sentencia.setString(3, proveedor.getProveedorEmail());
                sentencia.setString(4, proveedor.getProveedorDireccion());
                sentencia.setString(5, "A");

                sentencia.executeUpdate();

                return true;
                
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
            
        } else
            return false;
       
    }
    
    public boolean pvdEditar(Conexion con, Proveedor proveedor){
        
        if(pvdBuscar(con, proveedor.getProveedorRuc())!=null){
            try {

                sentencia = con.getConexion().prepareStatement("UPDATE veterinaria.vet_proveedores SET "
                + "prv_razons=?, prv_correo=?, prv_direccion=? "
                + "WHERE prv_ruc=?");
                
                sentencia.setString(1, proveedor.getProveedorRazonSocial().toUpperCase());
                sentencia.setString(2, proveedor.getProveedorEmail().toUpperCase());
                sentencia.setString(3, proveedor.getProveedorDireccion().toUpperCase());
                sentencia.setString(4, proveedor.getProveedorRuc());

                sentencia.executeUpdate();

                return true;
                
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
            
        } else
            return false; 
    }
    
    
    public boolean cancelarProveedor (Conexion con, Proveedor proveedor){
        
            try {
                
                sentencia = con.getConexion().prepareStatement("UPDATE veterinaria.vet_proveedores SET "
                + "prv_estado=? "
                + "WHERE prv_ruc=?");

                sentencia.setString(1, "I");
                sentencia.setString(2, proveedor.getProveedorRuc());
                
                sentencia.executeUpdate();
                
                return true;
                
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
            
    }
    
    
    
}


