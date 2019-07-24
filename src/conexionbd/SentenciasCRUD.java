package conexionbd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Usuario
 */
public class SentenciasCRUD {
    
    private ResultSet resultado = null;
    private PreparedStatement sentencia = null;
    
    
    /**
     * Metodo para consultar los datos de un empleado (en el HR)
     */
    public void consultarEmployees(Conexion con, int codigo){
        
        try {
            sentencia = con.getConexion().prepareStatement("SELECT first_name, last_name, email"
            + "FROM hr.employees"
            + "WHERE employee_id = ?");
        sentencia.setInt(1, codigo);
        resultado= sentencia.executeQuery();
        
        //Se presenta el resultado
        while(resultado.next()){
            System.out.println("First Name: " + resultado.getString("first_name"));
            System.out.println("Last Name: " + resultado.getString("last_name"));
            System.out.println("Email: " + resultado.getString("email"));
        }
        
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    
    
    /**
     * Metodo para insetar paises
     */
    public void insertarPais(Conexion con, String countryID, String countryName, int regionID){
        
        try {
            
            sentencia = con.getConexion().prepareStatement("INSERT INTO Countries VALUES (?,?,?)");
            
            sentencia.setString(1, countryID);
            sentencia.setString(2, countryName);
            sentencia.setInt(3, regionID);
            
            sentencia.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    
    
    
    /**
     * Metodo para eliminar paises
     */
    public void eliminarPais(Conexion con, String countryID){
        
        try {
            
            sentencia = con.getConexion().prepareStatement("DELETE "
                    + "FROM countries "
                    + "WHERE country_id=?");
            
            sentencia.setString(1, countryID);
            
            sentencia.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    
}
