package modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class RecetaCabecera {
    
    private int recetaCabeceraId;
    private Date recetaCabeceraFecha;
    private List<RecetaDetalle> recetasDetalle = new ArrayList<>();

    public int getRecetaCabeceraId() {
        return recetaCabeceraId;
    }

    public void setRecetaCabeceraId(int recetaCabeceraId) {
        this.recetaCabeceraId = recetaCabeceraId;
    }

    public Date getRecetaCabeceraFecha() {
        return recetaCabeceraFecha;
    }

    public void setRecetaCabeceraFecha(Date recetaCabeceraFecha) {
        this.recetaCabeceraFecha = recetaCabeceraFecha;
    }

    public List<RecetaDetalle> getRecetasDetalle() {
        return recetasDetalle;
    }

    public void addRecetasDetalle(RecetaDetalle recetasDetalle) {
        this.recetasDetalle.add(recetasDetalle);
    }
    
    
    
}
