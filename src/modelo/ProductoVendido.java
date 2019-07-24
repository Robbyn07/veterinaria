
package modelo;

/**
 *
 * @author Rakrad7101
 */
public class ProductoVendido {
    private String venNombre;
    private int venCantidad;
    private double venSubtotal;
    private double venIva;
    private double venTotal;
    private double preciov;
    

    public String getVenNombre() {
        return venNombre;
    }

    public void setVenNombre(String venNombre) {
        this.venNombre = venNombre;
    }

    public int getVenCantidad() {
        return venCantidad;
    }

    public void setVenCantidad(int venCantidad) {
        this.venCantidad = venCantidad;
    }

    public double getVenSubtotal() {
        return venSubtotal;
    }

    public void setVenSubtotal(double venSubtotal) {
        this.venSubtotal = venSubtotal;
    }

    public double getVenIva() {
        return venIva;
    }

    public void setVenIva(double venIva) {
        this.venIva = venIva;
    }

    public double getVenTotal() {
        return venTotal;
    }

    public void setVenTotal(double venTotal) {
        this.venTotal = venTotal;
    }

    public double getPreciov() {
        return preciov;
    }

    public void setPreciov(double preciov) {
        this.preciov = preciov;
    }
}
