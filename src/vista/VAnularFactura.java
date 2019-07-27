
package vista;

import conexionbd.Conexion;
import conexionbd.ControladorCliente;
import conexionbd.ControladorFacturaCabecera;
import conexionbd.ControladorFacturaDetalle;
import conexionbd.ControladorProducto;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelo.FacturaCabecera;
import modelo.FacturaDetalle;

/**
 *
 * @author Rakrad7101
 */
public class VAnularFactura extends JInternalFrame implements ActionListener{
    
    Conexion con;
    ControladorProducto cpd;
    ControladorCliente cc;
    ControladorFacturaCabecera cfc;
    ControladorFacturaDetalle cfd;
    
    public VAnularFactura(Conexion con,ControladorProducto cpd,ControladorCliente cc,
            ControladorFacturaCabecera cfc,ControladorFacturaDetalle cfd){
        this.con = con;
        this.cpd = cpd;
        this.cc = cc;
        this.cfc = cfc;
        this.cfd = cfd;
        initComponentes();
        ventanaAnularFac();
    }
    
    public void initComponentes(){
        setSize(800,300);
        setTitle("Anular Factura");
        setClosable(true);
        setMaximizable(true);
    }
    
    DefaultTableModel dt;
    private JTable tb1;
    private JScrollPane scr;
    private boolean[] editable = {false, false, false, false};
    
    private JButton b1;
    private JButton b2;
    private JButton b3;
    private JLabel l2;
    private JLabel l3;
    private JLabel l4;
    private JLabel l5;
    private JLabel l6;
    private JTextField t1;
    
    String fecha;
    String nombreC;
    String telefonoC;
    String direccionC;
    String totalF;
    
    public void ventanaAnularFac(){
        Container cp = getContentPane();       
        cp.setLayout(new BorderLayout()); 
        
        JPanel p1 = new JPanel();
        p1.setLayout(new FlowLayout());
        
        JLabel l1 = new JLabel("Nº Factura:");
        p1.add(l1);
        
        t1 = new JTextField(12);
        p1.add(t1);
        
        b1 = new JButton("Buscar");
        b1.addActionListener(this);
        b1.setActionCommand("buscar");
        p1.add(b1);
        
        //Panel que contiene a otros 2 paneles
        JPanel p2 = new JPanel(); 
        p2.setLayout(new BorderLayout());
        
        JPanel p3 = new JPanel(); 
        p3.setLayout(new FlowLayout());
        
        l2 = new JLabel(fecha);
        p3.add(l2) ;
        
        l3 = new JLabel(nombreC);
        p3.add(l3) ;
        
        l4 = new JLabel(telefonoC);
        p3.add(l4) ;
        
        l5 = new JLabel(direccionC);
        p3.add(l5) ;
        
        l6 = new JLabel(totalF);
        p3.add(l6) ;
        
        JPanel p4 = new JPanel(); 
        p4.setLayout(new BorderLayout());
        
        dt = new DefaultTableModel(new String[]{"Nombre", "Cantidad", "Precio U."
                                                , "Subtotal"}, 0) {
 
            Class[] types = new Class[]{
                java.lang.String.class, java.lang.Integer.class, 
                java.lang.Double.class, java.lang.Double.class
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
        p4.add(scr, BorderLayout.NORTH);

        p2.add(p3, BorderLayout.NORTH);
        p2.add(p4, BorderLayout.CENTER);
        
        JPanel p5 = new JPanel(); 
        p5.setLayout(new FlowLayout());
        
        b2 = new JButton("Volver");
        b2.addActionListener(this);
        b2.setActionCommand("volver");
        p5.add(b2);
        
        b3 = new JButton("Anular");
        b3.addActionListener(this);
        b3.setActionCommand("anular");
        p5.add(b3);
        
        cp.add(p1, BorderLayout.NORTH);
        cp.add(p2, BorderLayout.CENTER);
        cp.add(p5, BorderLayout.SOUTH);
        
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        System.out.println("Comando: " + comando);
        
        switch(comando){ 
            case "buscar":
                obtenerInfo();
                break;
            
            case "volver":
                setVisible(false);
                break;
                
            case "anular":
                if(anularFactura() == true){
                    JOptionPane.showMessageDialog(null, "Operación Exitosa");
                }
                break;
 
        }
        
    }
    
    int numeroF;
    Boolean v;
    FacturaCabecera fc;
    ArrayList<FacturaDetalle> lista;
    
        public void obtenerInfo(){
            System.out.println("Entra a metodo");
            try {
                numeroF = Integer.parseInt(t1.getText());

                lista = (ArrayList<FacturaDetalle>) cfd.detObtener(con, numeroF);
                fc = cfc.cabBuscar(con, numeroF);
                System.out.println(""+fc.getFacturaCabeceraFecha());
                int n = lista.size();
                
                for(int i = 0; i < n; i++){
                    Object fila[] = new Object[4];
                    fila[0] = lista.get(i).getProducto().getProductoNombre();
                    fila[1] = lista.get(i).getFacturaDetalleCantidad();
                    fila[2] = lista.get(i).getFacturaDetallePrecioUnitario();
                    fila[3] = lista.get(i).getFacturaDetalleSubtotal();
                }
                
                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                fecha = formato.format(fc.getFacturaCabeceraFecha());
                
                nombreC = fc.getCliente().getPersonaNombre() + " " +
                        fc.getCliente().getPersonaApellido();
                telefonoC = fc.getCliente().getPersonaTelefono();
                direccionC = fc.getCliente().getPersonaDireccion();
                totalF = Double.toString(fc.getFacturaCabeceraTotal());
                
            } catch (Exception e) {
                e.printStackTrace();
            }    
        }
    
        public boolean anularFactura(){
            v = false;

            fc.setFacturaCabeceraEstado("I");
            if(cfc.cabCancelar(con, fc) == true){
                v = true;
            }
            
            return v;
        }
}
