package modelo;

/**
 *
 * @author Usuario
 */
public class Diagnostico {
    
    private int diagnosticoId;
    private String diagnosticoNomEnfermedad;
    private RecetaCabecera receta;
    private Empleado empleado;

    public int getDiagnosticoId() {
        return diagnosticoId;
    }

    public void setDiagnosticoId(int diagnosticoId) {
        this.diagnosticoId = diagnosticoId;
    }

    public String getDiagnosticoNomEnfermedad() {
        return diagnosticoNomEnfermedad;
    }

    public void setDiagnosticoNomEnfermedad(String diagnosticoNomEnfermedad) {
        this.diagnosticoNomEnfermedad = diagnosticoNomEnfermedad;
    }

    public RecetaCabecera getReceta() {
        return receta;
    }

    public void setReceta(RecetaCabecera receta) {
        this.receta = receta;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
    
    
    
    
}
