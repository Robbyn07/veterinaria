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
import modelo.Cita;
import modelo.Empleado;

/**
 *
 * @author plojam
 */
public class ControladorEmpleado {
    
    private ResultSet resultado = null;
    private PreparedStatement sentencia = null;
    private String permiso=null;
    
    
    public Empleado empBuscar(Conexion con, String cedula){
        Empleado empleado = new Empleado();
        try {
            sentencia = con.getConexion().prepareStatement("SELECT emp_id, emp_nombre, emp_apellido, emp_cedula, emp_telefono, "
                    + "emp_direccion, emp_email, emp_cargo, emp_username, emp_contrasena "
                    + "FROM vet_empleados "
                    + "WHERE emp_cedula = ?");
            sentencia.setString(1, cedula);
            resultado= sentencia.executeQuery();

            //Se presenta el resultado
            while(resultado.next()){
                empleado.setEmpleadoId(resultado.getInt("emp_id"));
                empleado.setPersonaNombre(resultado.getString("emp_nombre"));
                empleado.setPersonaApellido(resultado.getString("emp_apellido"));
                empleado.setPersonaCedula(resultado.getString("emp_cedula"));
                empleado.setPersonaTelefono(resultado.getString("emp_telefono"));
                empleado.setPersonaDireccion(resultado.getString("emp_direccion"));
                empleado.setPersonaEmail(resultado.getString("emp_email"));
                empleado.setEmpleadoPermiso(resultado.getString("emp_cargo"));
                empleado.setEmpleadoUsername(resultado.getString("emp_username"));
                empleado.setEmpleadoContrasena(resultado.getString("emp_contrasena"));
            }
            
            return empleado;
            
        } catch (SQLException e) {
            e.printStackTrace();
            
            return null;
            
        }
        
    }
    
    public List<Empleado> empObtener(Conexion con){
        
        List<Empleado> empleados = new ArrayList<>();
        try {
            sentencia = con.getConexion().prepareStatement("SELECT emp_id, emp_nombre, emp_apellido, emp_cedula, emp_telefono, "
                    + "emp_direccion, emp_email, emp_cargo, emp_username, emp_contrasena "
                    + "FROM VETERINARIA.vet_empleados ");
            resultado= sentencia.executeQuery();

            //Se presenta el resultado
            while(resultado.next()){
                Empleado empleado = new Empleado();
                empleado.setEmpleadoId(resultado.getInt("emp_id"));
                empleado.setPersonaNombre(resultado.getString("emp_nombre"));
                empleado.setPersonaApellido(resultado.getString("emp_apellido"));
                empleado.setPersonaCedula(resultado.getString("emp_cedula"));
                empleado.setPersonaTelefono(resultado.getString("emp_telefono"));
                empleado.setPersonaDireccion(resultado.getString("emp_direccion"));
                empleado.setPersonaEmail(resultado.getString("emp_email"));
                empleado.setEmpleadoPermiso(resultado.getString("emp_cargo"));
                empleado.setEmpleadoUsername(resultado.getString("emp_username"));
                empleado.setEmpleadoContrasena(resultado.getString("emp_contrasena"));
                
                empleados.add(empleado);
            }
            
            return empleados;
            
        } catch (SQLException e) {
            e.printStackTrace();
            
            return null;
            
        }
        
    }
    
    
    public Empleado empBuscarId(Conexion con, int id){
        Empleado empleado = new Empleado();
        try {
            sentencia = con.getConexion().prepareStatement("SELECT emp_id, emp_nombre, emp_apellido, emp_cedula, emp_telefono, "
                    + "emp_direccion, emp_email, emp_cargo, emp_userNaMe, emp_contrasena "
            + "FROM VETERINARIA.vet_empleados "
            + "WHERE emp_id = ?");
            sentencia.setInt(1, id);
            resultado= sentencia.executeQuery();

            //Se presenta el resultado
            while(resultado.next()){
                empleado.setEmpleadoId(resultado.getInt("emp_id"));
                empleado.setPersonaNombre(resultado.getString("emp_nombre"));
                empleado.setPersonaApellido(resultado.getString("emp_apellido"));
                empleado.setPersonaCedula(resultado.getString("emp_cedula"));
                empleado.setPersonaTelefono(resultado.getString("emp_telefono"));
                empleado.setPersonaDireccion(resultado.getString("emp_direccion"));
                empleado.setPersonaEmail(resultado.getString("emp_email"));
                empleado.setEmpleadoPermiso(resultado.getString("emp_cargo"));
                empleado.setEmpleadoUsername(resultado.getString("emp_username"));
                empleado.setEmpleadoContrasena(resultado.getString("emp_contrasena"));
            }
            
            return empleado;
            
        } catch (SQLException e) {
            e.printStackTrace();
            
            return null;            
        }        
    }
    
    
    
    public boolean empAgregar(Conexion con, Empleado empleado){
        
        if(empBuscar(con, empleado.getPersonaCedula())==null){
            try {

                sentencia = con.getConexion().prepareStatement("INSERT INTO vet_empleados VALUES (emp_id_seq.nextval,?,?,?,?,?,?,?,?,?)");

                //sentencia.setInt(1, empleado.getEmpleadoId());
                sentencia.setString(1, empleado.getPersonaCedula());
                sentencia.setString(2, empleado.getPersonaNombre());
                sentencia.setString(3, empleado.getPersonaApellido());
                sentencia.setString(4, empleado.getEmpleadoPermiso());
                sentencia.setString(5, empleado.getPersonaTelefono());
                sentencia.setString(6, empleado.getPersonaEmail());
                sentencia.setString(7, empleado.getPersonaDireccion());
                sentencia.setString(8, empleado.getEmpleadoUsername());
                sentencia.setString(9, empleado.getEmpleadoContrasena());

                sentencia.executeUpdate();

                return true;
                
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
            
        } else
            return false;
        
    }
    
    public boolean empEditar(Conexion con, Empleado empleado){
        
        /*if(empBuscar(con, empleado.getPersonaCedula())!=null){
            try {
                
                sentencia = con.getConexion().prepareStatement("UPDATE vet_empleados SET "
                + "emp_telefono=?, emp_direccion=?, emp_email=?, emp_permiso=? "
                + "WHERE emp_cedula=?");

                sentencia.setString(1, empleado.getPersonaTelefono());
                sentencia.setString(2, empleado.getPersonaDireccion());
                sentencia.setString(3, empleado.getPersonaEmail());
                sentencia.setString(4, empleado.getEmpleadoPermiso());
                sentencia.setString(5, empleado.getPersonaCedula());

                sentencia.executeUpdate();

                return true;
                
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
            
        } else
            return false;*/
        
        
        if(empBuscar(con, empleado.getPersonaCedula())!=null){
            try {
                
                sentencia = con.getConexion().prepareStatement("UPDATE vet_empleados SET "
                + "emp_telefono=?, emp_direccion=?, emp_email=?, emp_permiso=?, emp_username=?, emp_contrasena=? "
                + "WHERE emp_cedula=?");

                sentencia.setString(1, empleado.getPersonaTelefono());
                sentencia.setString(2, empleado.getPersonaDireccion());
                sentencia.setString(3, empleado.getPersonaEmail());
                sentencia.setString(4, empleado.getEmpleadoPermiso());
                sentencia.setString(5, empleado.getPersonaCedula());
                sentencia.setString(5, empleado.getEmpleadoUsername());
                sentencia.setString(5, empleado.getEmpleadoContrasena());

                sentencia.executeUpdate();

                return true;
                
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
            
        } else
            return false;
        
        
    }
    
    
    //COMPROBACION DE ROLES Y CARGOS AL INICIO DE SESION
    public String comprobarTipo(Conexion con, String username, String contrasena){
        
        //Empleado empleado = new Empleado();
        //System.out.println("USUARIO= "+username);
        //System.out.println("CONTRASEÑA= "+contrasena);
        try {
           
            sentencia = con.getConexion().prepareStatement("SELECT GRANTED_ROLE "
            + "FROM USER_ROLE_PRIVS "
            + "WHERE USERNAME=?");
            sentencia.setString(1, username);
            resultado= sentencia.executeQuery();
            //Se presenta el resultado
            // System.out.println("ENTRA");
            while(resultado.next()){
                permiso=resultado.getString(1);
            }
            if(permiso.equals("EMPLEADOS")){
                sentencia = con.getConexion().prepareStatement("SELECT EMP_CARGO "
                + "FROM VETERINARIA.VET_EMPLEADOS "
                + "WHERE EMP_USERNAME=?");
                sentencia.setString(1, username);
                resultado= sentencia.executeQuery();
                //Se presenta el resultado
               // System.out.println("ENTRA");
                
                while(resultado.next()){
                    permiso=resultado.getString(1);
                }
            }
            System.out.println("Permiso= "+permiso);
            return permiso;
            
        } catch (SQLException e) {
            e.printStackTrace();
            
            return null;
            
        }
        
    }
    
    /*public boolean eliminarEmpleado (Conexion con, String cedula){
        
        if(empBuscar(con, cedula)==null){
            try {

                sentencia = con.getConexion().prepareStatement("DELETE FROM vet_empleados WHERE emp_cedula=?");
                sentencia.setString(1, cedula);
                sentencia.executeUpdate();

                return true;
                
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
            
        } else
            return false;

    }*/
    
    public boolean cancelarEmpleado (Conexion con, Empleado empleado){
        if(empBuscar(con, empleado.getPersonaCedula())!=null){
            try {
                
                sentencia = con.getConexion().prepareStatement("UPDATE vet_empleados SET "
                + "emp_estado=? "
                + "WHERE emp_cedula=?");

                sentencia.setString(1, empleado.getEmpleadoEstado());
                sentencia.setString(2, empleado.getPersonaCedula());
                
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



