package modelo;

import java.util.Date;

/**
 *
 * @author Usuario
 */
public class Cita {
    
    private int citaId;
    private Date citaFecha;
    private String citaEstado;
    private Diagnostico diagnostico;
    private Mascota mascota;

    
    public int getCitaId() {
        return citaId;
    }

    public void setCitaId(int citaId) {
        this.citaId = citaId;
    }

    public Date getCitaFecha() {
        return citaFecha;
    }

    public void setCitaFecha(Date citaFecha) {
        this.citaFecha = citaFecha;
    }

    public String getCitaEstado() {
        return citaEstado;
    }

    public void setCitaEstado(String citaEstado) {
        this.citaEstado = citaEstado;
    }
    
    public Diagnostico getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(Diagnostico diagnostico) {
        this.diagnostico = diagnostico;
    }
    
     public Mascota getMascota() {
        return mascota;
    }

    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }


}
