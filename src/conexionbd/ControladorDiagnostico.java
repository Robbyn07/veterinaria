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
import modelo.Diagnostico;
import modelo.Empleado;
import modelo.RecetaCabecera;

/**
 *
 * @author plojam
 */
public class ControladorDiagnostico {
    
    private ResultSet resultado = null;
    private PreparedStatement sentencia = null;
    
    public Diagnostico diaObtener(Conexion con, int citaId){
        Diagnostico diagnostico = new Diagnostico();
        ControladorRecetaCabecera controladorRecetaCabecera = new ControladorRecetaCabecera();
        Empleado empleado = new Empleado();
        ControladorEmpleado controladorEmpleado = new ControladorEmpleado();
        
        try {
            sentencia = con.getConexion().prepareStatement("SELECT dia_id, dia_nomenfermedad, emp_id "
            + "FROM VETERINARIA.vet_diagnosticos "
            + "WHERE cit_id = ?");
            sentencia.setInt(1, citaId);
            resultado= sentencia.executeQuery();

            //Se presenta el resultado
            while(resultado.next()){
                int diagnosticoId = resultado.getInt("dia_id");
                diagnostico.setDiagnosticoId(diagnosticoId);
                diagnostico.setDiagnosticoNomEnfermedad(resultado.getString("dia_nomenfermedad"));
                empleado = controladorEmpleado.empBuscarId(con, resultado.getInt("emp_id"));
                diagnostico.setEmpleado(empleado);
                
                RecetaCabecera recetaCabecera = controladorRecetaCabecera.recCabBuscar(con, diagnosticoId);
                diagnostico.setReceta(recetaCabecera);
                
                
            }
            
            return diagnostico;
            
        } catch (SQLException e) {
            e.printStackTrace();
            
            return null;
            
        }
        
    }
    
    
    public Diagnostico diaBuscar(Conexion con, int diagnosticoId){
        Diagnostico diagnostico = new Diagnostico();
        ControladorRecetaCabecera controladorRecetaCabecera = new ControladorRecetaCabecera();
        Empleado empleado = new Empleado();
        ControladorEmpleado controladorEmpleado = new ControladorEmpleado();
        
        try {
            sentencia = con.getConexion().prepareStatement("SELECT dia_id, dia_nomenfermedad, emp_id "
            + "FROM vet_diagnosticos "
            + "WHERE dia_id = ?");
            sentencia.setInt(1, diagnosticoId);
            resultado= sentencia.executeQuery();

            //Se presenta el resultado
            while(resultado.next()){
                diagnostico.setDiagnosticoId(resultado.getInt("dia_id"));
                diagnostico.setDiagnosticoNomEnfermedad(resultado.getString("dia_nomenfermedad"));
                
                empleado = controladorEmpleado.empBuscarId(con, resultado.getInt("emp_id"));
                diagnostico.setEmpleado(empleado);
                
                RecetaCabecera recetaCabecera = controladorRecetaCabecera.recCabBuscar(con, diagnosticoId);
                diagnostico.setReceta(recetaCabecera);
                
            }
            
            return diagnostico;
            
        } catch (SQLException e) {
            e.printStackTrace();
            
            return null;
            
        }
        
    }
    
    
    
    
    public boolean diaAgregar(Conexion con, Diagnostico diagnostico, int citaId, Empleado empleado){
        ControladorRecetaCabecera controladorRecetaCabecera = new ControladorRecetaCabecera();
        if(diaBuscar(con, diagnostico.getDiagnosticoId())==null){
            try {

                sentencia = con.getConexion().prepareStatement("INSERT INTO vet_diagnosticos VALUES (dia_id_seq.nextval,?,?,?)");

                //sentencia.setInt(1, diagnostico.getDiagnosticoId());
                sentencia.setString(1, diagnostico.getDiagnosticoNomEnfermedad());
                sentencia.setInt(2, citaId);
                sentencia.setInt(3, empleado.getEmpleadoId());
                
                sentencia.executeUpdate();
                
                controladorRecetaCabecera.recCabAgregar(con, diagnostico.getReceta(), diagnostico.getDiagnosticoId());

                return true;
                
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
            
        } else
            return false;
        
    }
    
}


