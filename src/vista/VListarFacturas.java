
package vista;

import conexionbd.Conexion;
import conexionbd.ControladorFacturaCabecera;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.FacturaCabecera;

/**
 *
 * @author Rakrad7101
 */
public class VListarFacturas extends JInternalFrame implements ActionListener{
    
    Conexion con;
    ControladorFacturaCabecera cfc;
    
    public VListarFacturas(Conexion con,ControladorFacturaCabecera cfc){
        this.con = con;
        this.cfc = cfc;
        initComponentes();
        ventanaListarFac();
    }
    
    public void initComponentes(){
        setSize(800,400);
        setTitle("Listar Facturas");
        setClosable(true);
    }
    
    private JButton b1;
    private JButton b2;
    DefaultTableModel dt;
    private JTable tb1;
    private JScrollPane scr;
    private boolean[] editable = {false,false,false,false,false};
    
    public void ventanaListarFac(){
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        
        JPanel pa = new JPanel();
        pa.setLayout(new BorderLayout()); 
        
        dt = new DefaultTableModel(new String[]{"Nº Factura","Fecha", "Cliente", 
            "Total", "Método de Pago"}, 0) {
 
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, 
                java.lang.Object.class, java.lang.Object.class
            };
 
            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
            
            @Override
            public boolean isCellEditable(int row, int column){
                return editable[column];
            }
        };
        
        tb1 = new JTable();
        tb1.setModel(dt);
        scr = new JScrollPane(tb1);
        pa.add(scr, BorderLayout.NORTH);
        
        JPanel pa2 = new JPanel();
        pa2.setLayout(new FlowLayout()); 
        b1 = new JButton("Volver");
        b1.addActionListener(this);
        b1.setActionCommand("volver");
        pa2.add(b1);
        
        b2 = new JButton("Listar");
        b2.addActionListener(this);
        b2.setActionCommand("mostrar");
        pa2.add(b2);
        
        cp.add(pa, BorderLayout.CENTER);
        cp.add(pa2, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        System.out.println("Comando: " + comando);
        
        switch(comando){
            case "volver":
                setVisible(false);
                break;
                
            case "mostrar":
                mostrar();
                break;    
        }
    }
    
    public void mostrar(){
        ArrayList<FacturaCabecera> lista =(ArrayList<FacturaCabecera>) cfc.cabObtener(con);
        int n = lista.size();
        
        for(int i = 0; i < n; i++){
            Object fila[] = new Object[5];
            fila[0] = lista.get(i).getFacturaCabeceraNumero();
            fila[1] = lista.get(i).getFacturaCabeceraFecha();
            fila[2] = lista.get(i).getCliente().getPersonaNombre() + " " + 
                    lista.get(i).getCliente().getPersonaApellido();
            fila[3] = lista.get(i).getFacturaCabeceraTotal();
            fila[4] = lista.get(i).getFacturaCabeceraTipoPago();

            dt.addRow(fila);
            
        }
        
    }
    
    
}
