package modelo;

/**
 *
 * @author Usuario
 */
public class Empleado extends Persona{
    
    private int empleadoId;
    private String empleadoPermiso;
    private String empleadoUsername;
    private String empleadoContrasena;
    private String empleadoEstado;

    public int getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(int empleadoId) {
        this.empleadoId = empleadoId;
    }
    
    public String getEmpleadoPermiso() {
        return empleadoPermiso;
    }

    public void setEmpleadoPermiso(String empleadoPermiso) {
        this.empleadoPermiso = empleadoPermiso;
    }

    public String getEmpleadoUsername() {
        return empleadoUsername;
    }

    public void setEmpleadoUsername(String empleadoUsername) {
        this.empleadoUsername = empleadoUsername;
    }

    public String getEmpleadoContrasena() {
        return empleadoContrasena;
    }

    public void setEmpleadoContrasena(String empleadoContrasena) {
        this.empleadoContrasena = empleadoContrasena;
    } 

    public String getEmpleadoEstado() {
        return empleadoEstado;
    }

    public void setEmpleadoEstado(String empleadoEstado) {
        this.empleadoEstado = empleadoEstado;
    }

    
}
