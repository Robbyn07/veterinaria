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
import modelo.Cliente;
import modelo.Mascota;

/**
 *
 * @author Usuario
 */
public class ControladorCliente {
    
    private ResultSet resultado = null;
    private PreparedStatement sentencia = null;
    
    public List<Cliente> cliObtener(Conexion con){
        
        List<Cliente> clientes = new ArrayList<>();
        List<Mascota> mascotas = new ArrayList<>();
        ControladorMascota controladorMascota = new ControladorMascota();
        
        try {
            sentencia = con.getConexion().prepareStatement("SELECT cli_id, cli_nombre, cli_apellido, cli_cedula, cli_telefono, cli_direccion, "
                    + "cli_email "
            + "FROM VETERINARIA.vet_clientes "
            + "ORDER BY cli_nombre, cli_apellido");
            resultado= sentencia.executeQuery();

            //Se presenta el resultado
            while(resultado.next()){
                Cliente cliente = new Cliente();
                int clienteId = resultado.getInt("cli_id");
                cliente.setClienteId(clienteId);
                cliente.setPersonaNombre(resultado.getString("cli_nombre"));
                cliente.setPersonaApellido(resultado.getString("cli_apellido"));
                cliente.setPersonaCedula(resultado.getString("cli_cedula"));
                cliente.setPersonaTelefono(resultado.getString("cli_cedula"));
                cliente.setPersonaDireccion(resultado.getString("cli_direccion"));
                cliente.setPersonaEmail(resultado.getString("cli_email"));
                
                mascotas = controladorMascota.masObtener(con, clienteId);
                for(int i=0; i<mascotas.size(); i++){
                    cliente.addMascotas(mascotas.get(i));
                }
                
                
                clientes.add(cliente);
                
            }
            
            return clientes;
            
        } catch (SQLException e) {
            e.printStackTrace();
            
            return null;
            
        }
        
    }
    
    
    
    public Cliente cliBuscar(Conexion con, String clienteCedula){
        Cliente cliente = new Cliente();
        List<Mascota> mascotas = new ArrayList<>();
        ControladorMascota controladorMascota = new ControladorMascota();
        
        try {
            sentencia = con.getConexion().prepareStatement("SELECT cli_id, cli_nombre, cli_apellido, cli_cedula, cli_telefono, cli_ddireccion, "
                    + "cli_email "
            + "FROM vet_clientes "
            + "WHERE cli_cedula = ?");
            sentencia.setString(1, clienteCedula);
            resultado= sentencia.executeQuery();

            //Se presenta el resultado
            while(resultado.next()){
                int clienteId = resultado.getInt("cli_id");
                cliente.setClienteId(clienteId);
                cliente.setPersonaNombre(resultado.getString("cli_nombre"));
                cliente.setPersonaApellido(resultado.getString("cli_apellido"));
                cliente.setPersonaCedula(resultado.getString("cli_cedulla"));
                cliente.setPersonaTelefono(resultado.getString("cli_telefono"));
                cliente.setPersonaDireccion(resultado.getString("cli_direccion"));
                cliente.setPersonaEmail(resultado.getString("cli_email"));
                
                mascotas = controladorMascota.masObtener(con, clienteId);
                for(int i=0; i<mascotas.size(); i++){
                    cliente.addMascotas(mascotas.get(i));
                }
                
                
                
            }
            
            return cliente;
            
        } catch (SQLException e) {
            e.printStackTrace();
            
            return null;
            
        }
        
    }
    
    public Cliente cliBuscarId(Conexion con, int clienteId){
        Cliente cliente=null;
        List<Mascota> mascotas = new ArrayList<>();
        ControladorMascota controladorMascota = new ControladorMascota();
        
        try {
            sentencia = con.getConexion().prepareStatement("SELECT cli_id, cli_nombre, cli_apellido, cli_cedula, cli_telefono, cli_direccion, "
                    + "cli_email "
            + "FROM VETERINARIA.vet_clientes "
            + "WHERE cli_id = ?");
            sentencia.setInt(1, clienteId);
            resultado= sentencia.executeQuery();

            //Se presenta el resultado
            while(resultado.next()){
                cliente = new Cliente();
                cliente.setClienteId(resultado.getInt("cli_id"));
                cliente.setPersonaNombre(resultado.getString("cli_nombre"));
                cliente.setPersonaApellido(resultado.getString("cli_apellido"));
                cliente.setPersonaCedula(resultado.getString("cli_cedula"));
                cliente.setPersonaTelefono(resultado.getString("cli_telefono"));
                cliente.setPersonaDireccion(resultado.getString("cli_direccion"));
                cliente.setPersonaEmail(resultado.getString("cli_email"));
                
                
                
                
            }
            
            return cliente;
            
        } catch (SQLException e) {
            e.printStackTrace();
            
            return null;
            
        }
        
    }
    
    public boolean cliAgregar(Conexion con, Cliente cliente){
        
        if(cliBuscar(con, cliente.getPersonaCedula())==null){
            try {

                sentencia = con.getConexion().prepareStatement("INSERT INTO vet_clientes VALUES (cli_id_seq.nextval,?,?,?,?,?,?)");

                //sentencia.setInt(1, cliente.getClienteId());
                sentencia.setString(1, cliente.getPersonaCedula());
                sentencia.setString(2, cliente.getPersonaNombre());
                sentencia.setString(3, cliente.getPersonaApellido());
                sentencia.setString(4, cliente.getPersonaTelefono());
                sentencia.setString(5, cliente.getPersonaDireccion());
                sentencia.setString(6, cliente.getPersonaEmail());

                sentencia.executeUpdate();

                return true;
                
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
            
        } else
            return false;
        
    }
    
    public boolean cliEditar(Conexion con, Cliente cliente){
        
        if(cliBuscar(con, cliente.getPersonaCedula())!=null){
            try {
                sentencia = con.getConexion().prepareStatement("UPDATE vet_clientes SET "
                + "cli_nombre=?, cli_pellido=?, cli_telefono=?, cli_direccion=?, cli_email=? "
                + "WHERE cli_cedula=?");

                sentencia.setString(1, cliente.getPersonaNombre());
                sentencia.setString(2, cliente.getPersonaApellido());
                sentencia.setString(3, cliente.getPersonaTelefono());
                sentencia.setString(4, cliente.getPersonaDireccion());
                sentencia.setString(5, cliente.getPersonaEmail());
                sentencia.setString(6, cliente.getPersonaCedula());

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


