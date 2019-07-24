package modelo;

/**
 *
 * @author Usuario
 */
public class Producto {
    
    private int productoId;
    private String productoNombre;
    private double productoPrecioVenta;
    private double productoPrecioCompra;
    private int productoStock;
    private String productoCategoria;
    private String productoOrigen;
    private String productoAlianza;
    private String productoEstado;
    private Proveedor proveedor;

    public int getProductoId() {
        return productoId;
    }

    public void setProductoId(int productoId) {
        this.productoId = productoId;
    }

    public String getProductoNombre() {
        return productoNombre;
    }

    public void setProductoNombre(String productoNombre) {
        this.productoNombre = productoNombre;
    }
    
    public double getProductoPrecioCompra() {
        return productoPrecioCompra;
    }

    public void setProductoPrecioCompra(double productoPrecioCompra) {
        this.productoPrecioCompra = productoPrecioCompra;
    }
        
    public double getProductoPrecioVenta() {
        return productoPrecioVenta;
    }

    public void setProductoPrecioVenta(double productoPrecioVenta) {
        this.productoPrecioVenta = productoPrecioVenta;
    }

    public int getProductoStock() {
        return productoStock;
    }

    public void setProductoStock(int productoStock) {
        this.productoStock = productoStock;
    }

    public String getProductoCategoria() {
        return productoCategoria;
    }

    public void setProductoCategoria(String productoCategoria) {
        this.productoCategoria = productoCategoria;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public String getProductoOrigen() {
        return productoOrigen;
    }

    public void setProductoOrigen(String productoOrigen) {
        this.productoOrigen = productoOrigen;
    }

    public String getProductoAlianza() {
        return productoAlianza;
    }

    public void setProductoAlianza(String productoAlianza) {
        this.productoAlianza = productoAlianza;
    }

    public String getProductoEstado() {
        return productoEstado;
    }

    public void setProductoEstado(String productoEstado) {
        this.productoEstado = productoEstado;
    }

    
    
}
