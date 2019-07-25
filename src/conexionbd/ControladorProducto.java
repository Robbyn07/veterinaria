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
import modelo.Producto;

/**
 *
 * @author plojam
 */
public class ControladorProducto {
    
    private ResultSet resultado = null;
    private PreparedStatement sentencia = null;
    
    
    public Producto proBuscar(Conexion con, String nombre){
        ControladorProveedor proveedor= new ControladorProveedor();
        Producto producto = new Producto();
        //producto=null;
        
        try {
            sentencia = con.getConexion().prepareStatement("SELECT pro_id, pro_nombre,pro_precioc,pro_preciov, " 
                    +"pro_stock, pro_categoria, pro_origen, pro_alianza, prv_id "
            + "FROM VETERINARIA.vet_productos "
            + "WHERE UPPER(pro_nombre) = ?");
            sentencia.setString(1, nombre.toUpperCase());
            resultado= sentencia.executeQuery();

            //Se presenta el resultado
            if(resultado.next()==false){
                producto=null;
            }else{
                do{
                    producto.setProductoId(resultado.getInt("pro_id"));
                    producto.setProductoNombre(resultado.getString("pro_nombre"));
                    producto.setProductoPrecioCompra(resultado.getDouble("pro_precioc"));
                    producto.setProductoPrecioVenta(resultado.getDouble("pro_preciov"));
                    producto.setProductoStock(resultado.getInt("pro_stock"));
                    producto.setProductoCategoria(resultado.getString("pro_categoria"));
                    producto.setProductoOrigen(resultado.getString("pro_origen"));
                    producto.setProductoAlianza(resultado.getString("pro_alianza"));
                    int proveedorId = resultado.getInt("prv_id");
                    producto.setProveedor(proveedor.pvdBuscarId(con, proveedorId));
                }while(resultado.next());
            }
            
            return producto;
            
        } catch (SQLException e) {
            e.printStackTrace();
            
            return null;
            
        }
        
    }
    
    
    public Producto proBuscarId(Conexion con, int id){
        ControladorProveedor proveedor= new ControladorProveedor();
        Producto producto = new Producto();
        try {
            sentencia = con.getConexion().prepareStatement("SELECT pro_id, pro_nombre,pro_precioc,pro_preciov, " 
                    +"pro_stock, pro_categoria, pro_origen, pro_alianza, pRV_id " 
                    + "FROM VETERINARIA.vet_productos "
                    + "WHERE pro_id = ?");
            sentencia.setInt(1, id);
            resultado= sentencia.executeQuery();

            //Se presenta el resultado
            while(resultado.next()){
                producto.setProductoId(resultado.getInt("pro_id"));
                producto.setProductoNombre(resultado.getString("pro_nombre"));
                producto.setProductoPrecioCompra(resultado.getDouble("pro_precioc"));
                producto.setProductoPrecioVenta(resultado.getDouble("pro_preciov"));
                producto.setProductoStock(resultado.getInt("pro_stock"));
                producto.setProductoCategoria(resultado.getString("pro_categoria"));
                producto.setProductoOrigen(resultado.getString("pro_origen"));
                producto.setProductoAlianza(resultado.getString("pro_alianza"));
                int proveedorId = resultado.getInt("pRV_id");
                producto.setProveedor(proveedor.pvdBuscarId(con, proveedorId));
            }
            
            return producto;
            
        } catch (SQLException e) {
            e.printStackTrace();
            
            return null;
            
        }
        
    }
    
    
    public List<Producto> proObtener(Conexion con){
        ControladorProveedor proveedor= new ControladorProveedor();//NO DEBERIA CREARSE CONTROLADOR AQUI
        ArrayList<Producto> productos = new ArrayList<>();
        
        try {
            //System.out.println("Entra a listar");
            sentencia = con.getConexion().prepareStatement("SELECT PRO_ID, PRO_NOMBRE, PRO_PRECIOC, PRO_PRECIOV, "
                    + "PRO_STOCK, PRO_CATEGORIA, PRO_ORIGEN, PRO_ALIANZA, PRV_ID "
                    + "FROM VETERINARIA.VET_PRODUCTOS ORDER BY 1");
            resultado= sentencia.executeQuery();

            //Se presenta el resultado
            while(resultado.next()){
                Producto producto = new Producto();
                producto.setProductoId(resultado.getInt("PRO_ID"));
                //System.out.println(resultado.getInt("PRO_ID"));
                producto.setProductoNombre(resultado.getString("PRO_NOMBRE"));
                producto.setProductoPrecioCompra(resultado.getDouble("PRO_PRECIOC"));
                producto.setProductoPrecioVenta(resultado.getDouble("PRO_PRECIOV"));
                producto.setProductoStock(resultado.getInt("PRO_STOCK"));
                producto.setProductoCategoria(resultado.getString("PRO_CATEGORIA"));
                producto.setProductoOrigen(resultado.getString("PRO_ORIGEN"));
                producto.setProductoAlianza(resultado.getString("PRO_ALIANZA"));
                int proveedorId = resultado.getInt("PRV_ID");
                producto.setProveedor(proveedor.pvdBuscarId(con, proveedorId));
                
                productos.add(producto);
            }
            
            return productos;
            
        } catch (SQLException e) {
            e.printStackTrace();
            
            return null;
            
        }
        
    }
    
    
    
    /**
     * Metodo para insetar paises
     */
    public boolean proAgregar(Conexion con, Producto producto){
        //System.out.println("Entra Metodo Agregar Producto");
        if(proBuscar(con, producto.getProductoNombre())==null){
            //System.out.println("Entra consulta");
            try {

                sentencia = con.getConexion().prepareStatement("INSERT INTO VETERINARIA.vet_productos VALUES "
                        + "(VETERINARIA.pro_id_seq.nextval,?,?,?,?,?,?,?,?,?)");

                //sentencia.setInt(1, producto.getProductoId());
                sentencia.setString(1, producto.getProductoNombre().toUpperCase());
                sentencia.setInt(2, producto.getProductoStock());
                sentencia.setString(3, producto.getProductoCategoria().toUpperCase());
                sentencia.setDouble(4, producto.getProductoPrecioCompra());
                sentencia.setDouble(5, producto.getProductoPrecioVenta());
                sentencia.setString(6, producto.getProductoOrigen());
                sentencia.setString(7, producto.getProductoAlianza());
                sentencia.setString(8, producto.getProductoEstado());
                sentencia.setInt(9, 1);

                sentencia.executeUpdate();

                return true;
                
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
            
        } else
            return false;
        
    }
    
    public boolean proEditar(Conexion con, Producto producto){
        
        
            try {
                
                sentencia = con.getConexion().prepareStatement("UPDATE VETERINARIA.vet_productos SET "
                + "pro_precioc=?, pro_preciov=?, pro_stock=? "
                + "WHERE pro_nombre=?");

                sentencia.setDouble(1, producto.getProductoPrecioCompra());
                sentencia.setDouble(2, producto.getProductoPrecioVenta());
                sentencia.setInt(3, producto.getProductoStock());
                sentencia.setString(4, producto.getProductoNombre().toUpperCase());

                sentencia.executeUpdate();

                return true;
                
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        
    }
    
    public boolean agregarStock(Conexion con, Producto producto, int cantidad){
        
        int stock = producto.getProductoStock();
        stock = stock+cantidad;
        
        producto.setProductoStock(stock);
        
        proEditar(con, producto);
        
        return true;
        
    }
    
    public boolean quitarStock(Conexion con, Producto producto, int cantidad){
        
        int stock = producto.getProductoStock();
        stock = stock-cantidad;
        
        producto.setProductoStock(stock);
        
        proEditar(con, producto);
        
        return true;
        
    }
    
    
    /*public boolean eliminarProducto (Conexion con, String nombre){
        
        if(proBuscar(con, nombre)==null){
            try {

                sentencia = con.getConexion().prepareStatement("DELETE FROM vet_productos WHERE pro_nombre=?");

                sentencia.setString(1, nombre);

                sentencia.executeUpdate();

                return true;
                
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
            
        } else
            return false;
        
    }*/
    
    public boolean cancelarProducto (Conexion con, Producto producto){
       
            try {
                
                sentencia = con.getConexion().prepareStatement("UPDATE veterinaria.vet_productos SET "
                + "pro_estado=? "
                + "WHERE pro_id=?");

                sentencia.setString(1, "I");
                sentencia.setInt(2, producto.getProductoId());
                
                sentencia.executeUpdate();
                
                return true;
                
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
            
    }
    
    
}



