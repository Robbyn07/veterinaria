package modelo;

/**
 *
 * @author Usuario
 */
public class FacturaDetalle {
    
    private int facturaDetalleId;
    private int facturaDetalleCantidad;
    private double facturaDetallePrecioUnitario;
    private double facturaDetalleSubtotal;
    private Producto producto;

    public int getFacturaDetalleId() {
        return facturaDetalleId;
    }

    public void setFacturaDetalleId(int facturaDetalleId) {
        this.facturaDetalleId = facturaDetalleId;
    }

    public int getFacturaDetalleCantidad() {
        return facturaDetalleCantidad;
    }

    public void setFacturaDetalleCantidad(int facturaDetalleCantidad) {
        this.facturaDetalleCantidad = facturaDetalleCantidad;
    }

    public double getFacturaDetallePrecioUnitario() {
        return facturaDetallePrecioUnitario;
    }

    public void setFacturaDetallePrecioUnitario(double facturaDetallePrecioUnitario) {
        this.facturaDetallePrecioUnitario = facturaDetallePrecioUnitario;
    }

    public double getFacturaDetalleSubtotal() {
        return facturaDetalleSubtotal;
    }

    public void setFacturaDetalleSubtotal(double facturaDetalleSubtotal) {
        this.facturaDetalleSubtotal = facturaDetalleSubtotal;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    
    
    
    
}
