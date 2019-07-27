/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class Mascota {
 
    private int mascotaId;
    private String mascotaNombre;
    private String mascotaGenero;
    private String mascotaColor;
    private Date anio;
    private Raza raza;
    private Especie especie;
    private List<Cita> citas = new ArrayList<>();

    public int getMascotaId() {
        return mascotaId;
    }

    public void setMasscotaId(int mascotaId) {
        this.mascotaId = mascotaId;
    }

    public String getMascotaNombre() {
        return mascotaNombre;
    }

    public void setMascotaNombre(String mascotaNombre) {
        this.mascotaNombre = mascotaNombre;
    }

    public Date getAnio() {
        return anio;
    }

    public void setAnio(Date anio) {
        this.anio = anio;
    }
    
    public String getMascotaGenero() {
        return mascotaGenero;
    }

    public void setMascotaGenero(String mascotaGenero) {
        this.mascotaGenero = mascotaGenero;
    }

    public String getMascotaColor() {
        return mascotaColor;
    }

    public void setMascotaColor(String mascotaColor) {
        this.mascotaColor = mascotaColor;
    }
    
    public List<Cita> getCitas() {
        return citas;
    }

    public void addCitas(Cita citas) {
        this.citas.add(citas);
    }
    
    public void eliminarCitas(Cita citas){
        this.citas.remove(citas);
    }

    public Raza getRaza() {
        return raza;
    }

    public void setRaza(Raza raza) {
        this.raza = raza;
    }

    public Especie getEspecie() {
        return especie;
    }

    public void setEspecie(Especie especie) {
        this.especie = especie;
    }

    
    
}
