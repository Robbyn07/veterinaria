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
import modelo.Especie;
import modelo.Mascota;
import modelo.Raza;

/**
 *
 * @author Usuario
 */
public class ControladorMascota {
    
    private ResultSet resultado = null;
    private PreparedStatement sentencia = null;
    
    public List<Mascota> masObtenerTodos(Conexion con){
        Mascota mascota = new Mascota();
        List<Mascota> mascotas = new ArrayList<>();
        List<Cita> citas = new ArrayList<>();
        List<Diagnostico> diagnosticos = new ArrayList<>();
        ControladorCita controladorCita = new ControladorCita();
        ControladorDiagnostico controladorDiagnostico = new ControladorDiagnostico();
        Raza raza = new Raza();
        ControladorRaza controladorRaza = new ControladorRaza();
        Especie especie = new Especie();
        ControladorEspecie controladorEspecie = new ControladorEspecie();
        
        try {
            sentencia = con.getConexion().prepareStatement("SELECT mas_id, mas_nombre, mas_genero, mas_anio, "
                    + "mas_color, raz_id, esp_id "
            + "FROM vet_mascotas ");
            resultado= sentencia.executeQuery();

            //Se presenta el resultado
            while(resultado.next()){
                int mascotaId = resultado.getInt("mas_id");
                mascota.setMasscotaId(mascotaId);
                mascota.setMascotaNombre(resultado.getString("mas_nombre"));
                mascota.setMascotaGenero(resultado.getString("mas_genero"));
                mascota.setAnio(resultado.getDate("mas_anio"));
                mascota.setMascotaColor(resultado.getString("mas_color"));
                int razaId = resultado.getInt("raz_id");
                int especieId = resultado.getInt("esp_id");
                
                citas = controladorCita.citObtenerMascota(con, mascotaId);
                for(int i=0; i<citas.size(); i++){
                    mascota.addCitas(citas.get(i));
                }
                
                raza = controladorRaza.razBuscarId(con, razaId);
                mascota.setRaza(raza);
                
                especie = controladorEspecie.espBuscarId(con, especieId);
                mascota.setEspecie(especie);
                
                mascotas.add(mascota);
                
            }
            
            return mascotas;
            
        } catch (SQLException e) {
            e.printStackTrace();
            
            return null;
            
        }
        
    }
    
    
    
    public List<Mascota> masObtener(Conexion con, int clienteId){
       
        List<Mascota> mascotas = new ArrayList<>();
        List<Cita> citas = new ArrayList<>();
        List<Diagnostico> diagnosticos = new ArrayList<>();
        ControladorCita controladorCita = new ControladorCita();
        ControladorDiagnostico controladorDiagnostico = new ControladorDiagnostico();
        Raza raza = new Raza();
        ControladorRaza controladorRaza = new ControladorRaza();
        Especie especie = new Especie();
        ControladorEspecie controladorEspecie = new ControladorEspecie();
        
        try {
            sentencia = con.getConexion().prepareStatement("SELECT mas_id, mas_nombre, mas_genero, mas_anio, "
                    + "mas_color, raz_id, esp_id "
            + "FROM VETERINARIA.vet_mascotas "
            + "WHERE cli_id = ?");
            sentencia.setInt(1, clienteId);
            resultado= sentencia.executeQuery();

            //Se presenta el resultado
            while(resultado.next()){
                 Mascota mascota = new Mascota();
                int mascotaId = resultado.getInt("mas_id");
                mascota.setMasscotaId(mascotaId);
                mascota.setMascotaNombre(resultado.getString("mas_nombre"));
                mascota.setMascotaGenero(resultado.getString("mas_genero"));
                mascota.setAnio(resultado.getDate("mas_anio"));
                mascota.setMascotaColor(resultado.getString("mas_color"));
                int razaId = resultado.getInt("raz_id");
                int especieId = resultado.getInt("esp_id");
                
                citas = controladorCita.citObtenerMascota(con, mascotaId);
                for(int i=0; i<citas.size(); i++){
                    mascota.addCitas(citas.get(i));
                }
                
                raza = controladorRaza.razBuscarId(con, razaId);
                mascota.setRaza(raza);
                
                especie = controladorEspecie.espBuscarId(con, especieId);
                mascota.setEspecie(especie);
                
                mascotas.add(mascota);
                
            }
            
            return mascotas;
            
        } catch (SQLException e) {
            e.printStackTrace();
            
            return null;
            
        }
        
    }
    
    
    public List<Mascota> masObtenerNombre(Conexion con, String nombre){
        Mascota mascota = new Mascota();
        List<Mascota> mascotas = new ArrayList<>();
        List<Cita> citas = new ArrayList<>();
        List<Diagnostico> diagnosticos = new ArrayList<>();
        ControladorCita controladorCita = new ControladorCita();
        ControladorDiagnostico controladorDiagnostico = new ControladorDiagnostico();
        Raza raza = new Raza();
        ControladorRaza controladorRaza = new ControladorRaza();
        Especie especie = new Especie();
        ControladorEspecie controladorEspecie = new ControladorEspecie();
        
        try {
            sentencia = con.getConexion().prepareStatement("SELECT mas_id, mas_nombre, mas_genero, mas_anio, "
                    + "mas_color, raz_id, esp_id "
            + "FROM vet_mascotas "
            + "WHERE UPPER(mas_nombre) = UPPER(?)");
            sentencia.setString(1, nombre);
            resultado= sentencia.executeQuery();

            //Se presenta el resultado
            while(resultado.next()){
                int mascotaId = resultado.getInt("mas_id");
                mascota.setMasscotaId(mascotaId);
                mascota.setMascotaNombre(resultado.getString("mas_nombre"));
                mascota.setMascotaGenero(resultado.getString("mas_genero"));
                mascota.setAnio(resultado.getDate("mas_anio"));
                mascota.setMascotaColor(resultado.getString("mas_color"));
                int razaId = resultado.getInt("raz_id");
                int especieId = resultado.getInt("esp_id");
                
                citas = controladorCita.citObtenerMascota(con, mascotaId);
                for(int i=0; i<citas.size(); i++){
                    mascota.addCitas(citas.get(i));
                }
                
                raza = controladorRaza.razBuscarId(con, razaId);
                mascota.setRaza(raza);
                
                especie = controladorEspecie.espBuscarId(con, especieId);
                mascota.setEspecie(especie);
                
                mascotas.add(mascota);
                
            }
            
            return mascotas;
            
        } catch (SQLException e) {
            e.printStackTrace();
            
            return null;
            
        }
        
    }
    
    
    
    public Mascota masBuscar(Conexion con, int mascotaId){
        Mascota mascota = new Mascota();
        List<Cita> citas = new ArrayList<>();
        ControladorCita controladorcita = new ControladorCita();
        Raza raza = new Raza();
        ControladorRaza controladorRaza = new ControladorRaza();
        Especie especie = new Especie();
        ControladorEspecie controladorEspecie = new ControladorEspecie();
        
        try {
            sentencia = con.getConexion().prepareStatement("SELECT mas_id, mas_nombre, "
                    + "mas_genero, mas_anio, mas_color, raz_id, esp_id "
                    + "FROM vet_mascotas "
                    + "WHERE mas_id = ?");
            sentencia.setInt(1, mascotaId);
            resultado= sentencia.executeQuery();

            //Se presenta el resultado
            while(resultado.next()){
                mascota.setMasscotaId(resultado.getInt("mas_id"));
                mascota.setMascotaNombre(resultado.getString("mas_nombre"));
                mascota.setMascotaGenero(resultado.getString("mas_genero"));
                mascota.setAnio(resultado.getDate("mas_anio"));
                mascota.setMascotaColor(resultado.getString("mas_color"));
                int razaId = resultado.getInt("raz_id");
                int especieId = resultado.getInt("esp_id");
                
                
                citas = controladorcita.citObtenerMascota(con, resultado.getInt("mas_id"));
                for(int i=0; i<citas.size(); i++){
                    mascota.addCitas(citas.get(i));
                }
                
                raza = controladorRaza.razBuscarId(con, razaId);
                mascota.setRaza(raza);
                
                especie = controladorEspecie.espBuscarId(con, especieId);
                mascota.setEspecie(especie);
                
            }
            
            return mascota;
            
        } catch (SQLException e) {
            e.printStackTrace();
            
            return null;
            
        }
        
    }
    
    
    
    /*public Mascota masBuscarNombre(Conexion con, String nombre){
        Mascota mascota = new Mascota();
        List<Cita> citas = new ArrayList<>();
        ControladorCita controladorcita = new ControladorCita();
        Raza raza = new Raza();
        ControladorRaza controladorRaza = new ControladorRaza();
        Especie especie = new Especie();
        ControladorEspecie controladorEspecie = new ControladorEspecie();
        
        try {
            sentencia = con.getConexion().prepareStatement("SELECT mas_id, mas_nombre, "
                    + "mas_genero, mas_anio, mas_color, raz_id, esp_id "
                    + "FROM vet_mascotas "
                    + "WHERE UPPER(mas_nombre) = UPPER(?)");
            sentencia.setString(1, nombre);
            resultado= sentencia.executeQuery();

            //Se presenta el resultado
            while(resultado.next()){
                mascota.setMasscotaId(resultado.getInt("mas_id"));
                mascota.setMascotaNombre(resultado.getString("mas_nombre"));
                mascota.setMascotaGenero(resultado.getString("mas_genero"));
                mascota.setAnio(resultado.getDate("mas_anio"));
                mascota.setMascotaColor(resultado.getString("mas_color"));
                int razaId = resultado.getInt("raz_id");
                int especieId = resultado.getInt("esp_id");
                
                citas = controladorcita.citObtenerMascota(con, resultado.getInt("mas_id"));
                for(int i=0; i<citas.size(); i++){
                    mascota.addCitas(citas.get(i));
                }
                
                raza = controladorRaza.razBuscarId(con, razaId);
                mascota.setRaza(raza);
                
                especie = controladorEspecie.espBuscarId(con, especieId);
                mascota.setEspecie(especie);
                
            }
            
            return mascota;
            
        } catch (SQLException e) {
            e.printStackTrace();
            
            return null;
            
        }
        
    }*/
    
    
    
    public boolean masAgregar(Conexion con, Mascota mascota, int clienteId){
        
        if(masBuscar(con, mascota.getMascotaId())==null){
            try {

                sentencia = con.getConexion().prepareStatement("INSERT INTO vet_mascotas VALUES "
                        + "(mas_id_seq.nextval,?,?,?,?,?,?,?)");

                //sentencia.setInt(1, mascota.getMascotaId());
                sentencia.setString(1, mascota.getMascotaNombre());
                sentencia.setString(2, mascota.getMascotaGenero());
                sentencia.setDate(3, (Date) mascota.getAnio());
                sentencia.setString(4, mascota.getMascotaColor());
                sentencia.setInt(5, mascota.getRaza().getRazaId());
                sentencia.setInt(6, mascota.getEspecie().getEspecieId());
                sentencia.setInt(7, clienteId);

                sentencia.executeUpdate();

                return true;
                
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
            
        } else
            return false;
        
    }
    
    public boolean masEditar(Conexion con, Mascota mascota){
        
        if(masBuscar(con, mascota.getMascotaId())!=null){
            try {
                
                sentencia = con.getConexion().prepareStatement("UPDATE vet_mascotas SET "
                + "mas_nombre=?, mas_genero=?, mas_color=?, mas_anio=?, raz_id=?, esp_id=?"
                + "WHERE mas_id=?");

                sentencia.setString(1, mascota.getMascotaNombre());
                sentencia.setString(2, mascota.getMascotaGenero());
                sentencia.setString(3, mascota.getMascotaColor());
                sentencia.setDate(4, (Date) mascota.getAnio());
                sentencia.setInt(5, mascota.getRaza().getRazaId());
                sentencia.setInt(6, mascota.getEspecie().getEspecieId());
                sentencia.setInt(7, mascota.getMascotaId());

                sentencia.executeUpdate();

                return true;
                
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
            
        } else
            return false;
        
    }
    
    
    
    public int buscarClienteId(Conexion con, int masId){
        int cliId=0;
        try {
            sentencia = con.getConexion().prepareStatement("SELECT cli_id "
            + "FROM vet_mascotas "
            + "WHERE mas_id = ?");
            sentencia.setInt(1, masId);
            resultado= sentencia.executeQuery();

            //Se presenta el resultado
            while(resultado.next()){
                
                cliId = resultado.getInt("cli_id");
                
            }
            
            return cliId;
            
        } catch (SQLException e) {
            e.printStackTrace();
            
            return 0;
            
        }
        
    }
    
    

}



