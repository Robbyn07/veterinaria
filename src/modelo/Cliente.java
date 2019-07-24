package modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class Cliente extends Persona{
    
    private int clienteId;
    private List<Mascota> mascotas = new ArrayList<>();

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }
    
    public List<Mascota> getMascotas() {
        return mascotas;
    }

    public void addMascotas(Mascota mascotas) {
        this.mascotas.add(mascotas);
    }

}
