package modelo;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class FacturaCabecera {
    
    private int facturaCabeceraNumero;
    private Date facturaCabeceraFecha;
    private double facturaCabeceraSubtotal;
    private double facturaCabeceraIva;
    private double facturaCabeceraDescuento;
    private double facturaCabeceraTotal;
    private String facturaCabeceraTipoPago;
    private String facturaCabeceraEstado;
    private Cliente cliente;
    private List<FacturaDetalle> facturasDetalle = new ArrayList<>();

    public int getFacturaCabeceraNumero() {
        return facturaCabeceraNumero;
    }

    public void setFacturaCabeceraNumero(int facturaCabeceraNumero) {
        this.facturaCabeceraNumero = facturaCabeceraNumero;
    }

    public Date getFacturaCabeceraFecha() {
        return facturaCabeceraFecha;
    }

    public void setFacturaCabeceraFecha(Date facturaCabeceraFecha) {
        this.facturaCabeceraFecha = facturaCabeceraFecha;
    }

    public double getFacturaCabeceraSubtotal() {
        return facturaCabeceraSubtotal;
    }

    public void setFacturaCabeceraSubtotal(double facturaCabeceraSubtotal) {
        this.facturaCabeceraSubtotal = facturaCabeceraSubtotal;
    }

    public double getFacturaCabeceraIva() {
        return facturaCabeceraIva;
    }

    public void setFacturaCabeceraIva(double facturaCabeceraIva) {
        this.facturaCabeceraIva = facturaCabeceraIva;
    }

    public double getFacturaCabeceraDescuento() {
        return facturaCabeceraDescuento;
    }

    public void setFacturaCabeceraDescuento(double facturaCabeceraDescuento) {
        this.facturaCabeceraDescuento = facturaCabeceraDescuento;
    }

    public double getFacturaCabeceraTotal() {
        return facturaCabeceraTotal;
    }

    public void setFacturaCabeceraTotal(double facturaCabeceraTotal) {
        this.facturaCabeceraTotal = facturaCabeceraTotal;
    }

    public String getFacturaCabeceraTipoPago() {
        return facturaCabeceraTipoPago;
    }

    public void setFacturaCabeceraTipoPago(String facturaCabeceraTipoPago) {
        this.facturaCabeceraTipoPago = facturaCabeceraTipoPago;
    }

    public String getFacturaCabeceraEstado() {
        return facturaCabeceraEstado;
    }

    public void setFacturaCabeceraEstado(String facturaCabeceraEstado) {
        this.facturaCabeceraEstado = facturaCabeceraEstado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<FacturaDetalle> getFacturasDetalle() {
        return facturasDetalle;
    }

    public void addFacturasDetalle(FacturaDetalle facturasDetalle) {
        this.facturasDetalle.add(facturasDetalle);
    }

}
