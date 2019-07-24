/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import com.mxrck.autocompleter.TextAutoCompleter;
import conexionbd.Conexion;
import conexionbd.ControladorCaracter;
import conexionbd.ControladorCliente;
import conexionbd.ControladorFacturaCabecera;
import conexionbd.ControladorFacturaDetalle;
import conexionbd.ControladorProducto;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Rakrad7101
 */
public class VRealizarFactura extends JInternalFrame implements ActionListener{

    Calendar fecha;
    Conexion con;
    ControladorCaracter cca;
    ControladorProducto cpd;
    ControladorCliente cc;
    ControladorFacturaCabecera cfc;
    ControladorFacturaDetalle cfd;
    
    public VRealizarFactura(Conexion con,ControladorCaracter cca,ControladorProducto cpd,
            ControladorCliente cc,ControladorFacturaCabecera cfc,ControladorFacturaDetalle cfd){
        this.con = con;
        this.cca = cca;
        this.cpd = cpd;
        this.cc = cc;
        this.cfc = cfc;
        this.cfd = cfd;
        fecha = new GregorianCalendar();
        initComponentes();
        ventanaRealizarFac();
    } 
    
    
    public void initComponentes(){
        setSize(800,500);
        setTitle("Realizar Factura");
    }
    
    DefaultTableModel dt;
    private JTable tb1;
    private JScrollPane scr;
    private boolean[] editable = {false, true, false, false};
    
    private int dia;
    private int mes;
    private int anio;

    private static int id = 1;
    private String cedula; 
    
    private JButton b1;
    private JButton b2;
    private JButton b3;
    private JButton b4;
    private JLabel l2;
    private JLabel l4;
    private JLabel l7;
    private JLabel l9;
    private JLabel l11;
    private JTextField t1;
    private JTextField t2;
    private JTextField t3;
    private JTextField t4;
    private JTextField t5;
    private JTextField t6;
    private TextAutoCompleter ac;
    
    
    public void ventanaRealizarFac(){
        
        anio = fecha.get(Calendar.YEAR);
        mes = fecha.get(Calendar.MONTH);
        dia = fecha.get(Calendar.DAY_OF_MONTH);
        
        Container cp = getContentPane();       
        cp.setLayout(new BorderLayout()); 
        
        JPanel p1 = new JPanel();
        GridBagConstraints g1 = new GridBagConstraints(); 
        p1.setLayout(new GridBagLayout());
        
        JLabel l1 = new JLabel("Nº Factura: ");
        g1.gridx =0;
        g1.gridy =0;
        p1.add(l1, g1);
        
        l2 = new JLabel(generarNF());
        g1.gridx =1;
        g1.gridy =0;
        p1.add(l2, g1);
        
        JLabel l3 = new JLabel("Fecha: ");
        g1.gridx =2;
        g1.gridy =0;
        p1.add(l3, g1);
        
        l4 = new JLabel(dia + "/" + mes + "/" + anio);
        g1.gridx =3;
        g1.gridy =0;
        p1.add(l4, g1);
        
        JLabel l5 = new JLabel("Cédula: ");
        g1.gridx =0;
        g1.gridy =1;
        p1.add(l5, g1);
        
        t1 = new JTextField(12);
        g1.gridx =1;
        g1.gridy =1;
        p1.add(t1, g1);
        
        b1 = new JButton("Buscar");
        g1.gridx = 2;
        g1.gridy = 1;
        b1.addActionListener(this);
        b1.setActionCommand("buscar");
        p1.add(b1, g1);
        
        JLabel l6 = new JLabel("Nombre: ");
        g1.gridx =0;
        g1.gridy =2;
        p1.add(l6, g1);
        
        l7 = new JLabel(nombre);
        g1.gridx =1;
        g1.gridy =2;
        p1.add(l7, g1);
        
        JLabel l8 = new JLabel("Teléfono: ");
        g1.gridx =2;
        g1.gridy =2;
        p1.add(l8, g1);
        
        l9 = new JLabel(telefono);
        g1.gridx =3;
        g1.gridy =2;
        p1.add(l9, g1);
        
        JLabel l10 = new JLabel("Teléfono: ");
        g1.gridx =0;
        g1.gridy =3;
        p1.add(l10, g1);
        
        l11 = new JLabel(direccion);
        g1.gridx =1;
        g1.gridy =3;
        p1.add(l11, g1);
        
        JPanel p2 = new JPanel(); 
        p2.setLayout(new BorderLayout());
        Border loweredbevel = BorderFactory.createLoweredBevelBorder();
        TitledBorder title1;
        title1 = BorderFactory.createTitledBorder("Detalle Factura");
        title1.setTitlePosition(TitledBorder.ABOVE_TOP);
        p2.setBorder(title1);
        
        JPanel p3 = new JPanel(); 
        p3.setLayout(new FlowLayout());
        
        auto();
        
        JLabel l12 = new JLabel("Producto:");
        p3.add(l12) ;
        
        t2 = new JTextField(12);
        ac = new TextAutoCompleter(t2);
        p3.add(t2);
        
        b2 = new JButton("Agregar");
        b2.addActionListener(this);
        b2.setActionCommand("agregar");
        p3.add(b2);
        
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
        
        JPanel p5 = new JPanel(); 
        GridBagConstraints g2 = new GridBagConstraints(); 
        p5.setLayout(new GridBagLayout());
        
        JLabel l13 = new JLabel("Subtotal:");
        g2.gridx =0;
        g2.gridy =0;
        p5.add(l13, g2);
        
        t3 = new JTextField(12);
        g2.gridx =1;
        g2.gridy =0;
        p5.add(t3, g2);
        
        JLabel l14 = new JLabel("IVA(12%):");
        g2.gridx =0;
        g2.gridy =1;
        p5.add(l14, g2);
        
        t4 = new JTextField(12);
        g2.gridx =1;
        g2.gridy =1;
        p5.add(t4, g2);
        
        JLabel l15 = new JLabel("Descuento:");
        g2.gridx =0;
        g2.gridy =2;
        p5.add(l15, g2);
        
        t5 = new JTextField(12);
        g2.gridx =1;
        g2.gridy =2;
        p5.add(t5, g2);
        
        JLabel l16 = new JLabel("Total:");
        g2.gridx =0;
        g2.gridy =3;
        p5.add(l16, g2);
        
        t6 = new JTextField(12);
        g2.gridx =1;
        g2.gridy =3;
        p5.add(t6, g2);

        p2.add(p3, BorderLayout.NORTH);
        p2.add(p4, BorderLayout.CENTER);
        p2.add(p5, BorderLayout.SOUTH);
        
        JPanel p6 = new JPanel(); 
        p6.setLayout(new FlowLayout());
        
        b3 = new JButton("Volver");
        b3.addActionListener(this);
        b3.setActionCommand("volver");
        p6.add(b3);
        
        b4 = new JButton("Terminar");
        b4.addActionListener(this);
        b4.setActionCommand("terminar");
        p6.add(b4);
        
        cp.add(p1, BorderLayout.NORTH);
        cp.add(p2, BorderLayout.CENTER);
        cp.add(p6, BorderLayout.SOUTH);
 
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        System.out.println("Comando: " + comando);
        
        switch(comando){
            case "buscar":
                buscarCliente();
                break;
            
            case "agregar":
                agregarProducto();
                break;    
                
            case "volver":
                setVisible(false);
                break;
                
            case "terminar":
                llamarVentanaElegirMP(getDesktopPane());
                break;
        }
    }
    
    public String generarNF(){
        String ids = Integer.toString(id);
        id++;
        return ids;
    }
    
    private String nombre;
    private String apellido;
    private String telefono;
    private String direccion;
    
    public void buscarCliente(){
        cedula = t1.getText();
        
        try {
            if(cca.verificarCedula(cedula) == true){
                if(cc.cliBuscar(con, cedula).getPersonaCedula().equals(cedula)){
                    nombre = cc.cliBuscar(con, cedula).getPersonaNombre();
                    apellido = cc.cliBuscar(con, cedula).getPersonaApellido();
                    l7.setText(nombre + " " + apellido);

                    telefono = cc.cliBuscar(con, cedula).getPersonaTelefono();
                    l9.setText(telefono);

                    direccion = cc.cliBuscar(con, cedula).getPersonaDireccion();
                    l11.setText(direccion);  
                }else{
                    JOptionPane.showMessageDialog(null,"Error","El cliente no"
                            + " existe",JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null,"Error","Verifique la cédula",
                    JOptionPane.ERROR_MESSAGE);        
        }
    }
    
    public void auto(){
        int n = cpd.proObtener(con).size();
        
        for(int i = 0; i < n; i++){
            ac.addItem(cpd.proObtener(con).get(i).getProductoNombre());
        } 
    }
    
    private String producto;
    private int cantidad;
    private double precioU;
    private double subtotalD;
    
    public void agregarProducto(){
        producto = t2.getText();
        
        int n = cpd.proObtener(con).size();
        
        if(cpd.proBuscar(con, producto) != null){
            precioU = cpd.proBuscar(con, nombre).getProductoPrecioVenta();
            
            Object fila[] = new Object[4];
            fila[0] = cpd.proBuscar(con, producto).getProductoNombre();
            fila[1] = cantidad;
            fila[2] = precioU;
            
            for(int i = 0; i < tb1.getRowCount(); i++){
                cantidad = (int) tb1.getValueAt(i, 1);
                subtotalD = cantidad * precioU;
                fila[3] = subtotalD; 
                
                this.updateUI();
            }
            
            dt.addRow(fila);
            completarCabecera();
        }else{
            
        }
 
    }
    
    private double subtotalC = 0;
    private double test;
    private double iva;
    private double descuento;
    private double totalC;
    
    public void completarCabecera(){
        for(int i = 0; i < tb1.getRowCount(); i++){
            test = (double) tb1.getValueAt(i, 3);
            test = subtotalC + test;
            subtotalC = test;
        }
        
        try {
            t3.setText(Double.toString(subtotalC));
            iva = subtotalC * 0.12;
            t4.setText(Double.toString(iva));
            if(t5.getText() != null){
                descuento = Double.parseDouble(t5.getText());
                this.updateUI();
            }else{
                descuento = 0; 
            }
            totalC = subtotalC + iva + descuento;
            t5.setText(Double.toString(totalC));
                    
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    
    
    public void llamarVentanaElegirMP(JDesktopPane escritorio){
        VMetodoPago vmp = new VMetodoPago();
        vmp.setVisible(true);
        setVisible(false);
        
        escritorio.add(vmp);
    }
}
