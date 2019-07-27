
package vista;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.mxrck.autocompleter.TextAutoCompleter;
import conexionbd.Conexion;
import conexionbd.ControladorCaracter;
import conexionbd.ControladorCliente;
import conexionbd.ControladorFacturaCabecera;
import conexionbd.ControladorFacturaDetalle;
import conexionbd.ControladorProducto;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
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
import modelo.Cliente;
import modelo.FacturaCabecera;
import modelo.FacturaDetalle;
import modelo.Producto;

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
        setSize(800,550);
        setTitle("Realizar Factura");
    }
    
    DefaultTableModel dt;
    private JTable tb1;
    private JScrollPane scr;
    private boolean[] editable = {false, false, false, false};
    
    private int dia;
    private int mes;
    private int anio;

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
    private JTextField t7;
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
        
        t1 = new JTextField(10);
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
        
        l7 = new JLabel(nombre + " " + apellido);
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
        
        JLabel l10 = new JLabel("Dirección: ");
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
        p3.setLayout(new GridBagLayout());
        GridBagConstraints g2 = new GridBagConstraints();
        
        JLabel l12 = new JLabel("Producto:");
        g2.gridx = 0;
        g2.gridy = 0;
        p3.add(l12, g2) ;
        
        t2 = new JTextField(10);
        ac = new TextAutoCompleter(t2);
        g2.gridx = 1;
        g2.gridy = 0;
        p3.add(t2, g2);
        
        JLabel l13 = new JLabel("Cantidad:");
        g2.gridx = 0;
        g2.gridy = 1;
        p3.add(l13, g2) ;
        
        t3 = new JTextField(5);
        g2.gridx = 1;
        g2.gridy = 1;
        p3.add(t3, g2);
        
        b2 = new JButton("Agregar");
        b2.addActionListener(this);
        b2.setActionCommand("agregar");
        g2.gridx = 2;
        g2.gridy = 1;
        p3.add(b2, g2);
        
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
        p5.setLayout(new FlowLayout());
        
        JLabel l14 = new JLabel("Subtotal:");
        p5.add(l14);
        
        t4 = new JTextField(6);
        t4.setEditable(false);
        p5.add(t4);
        
        JLabel l15 = new JLabel("IVA(12%):");
        p5.add(l15);
        
        t5 = new JTextField(6);
        t5.setEditable(false);
        p5.add(t5);        
        
        JLabel l17 = new JLabel("Total:");
        p5.add(l17);
        
        t7 = new JTextField(6);
        t7.setEditable(false);
        p5.add(t7);

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
                terminarFactura();
                break;
        }
    }
    
    String ids;
    int id;

    public String generarNF(){
        /*
        ArrayList<FacturaCabecera> lista = (ArrayList<FacturaCabecera>) cfc.cabObtener(con);
        int n = lista.size();
        
        for(int i = 0; i < n; i++){
            id = lista.get(i).getFacturaCabeceraNumero();
        }*/
        
        id = cfc.obtenerId(con) + 1;
        ids = Integer.toString(id);
        return ids;
    }
    
    private String nombre;
    private String apellido;
    private String telefono;
    private String direccion;
    
    Cliente cli;
    
    public void buscarCliente(){
        cedula = t1.getText();
        cli = cc.cliBuscar(con, cedula);
        
        try {
            if(cca.verificarCedula(cedula) == true){
                if(cli.getPersonaCedula().equals(cedula)){
                            
                    nombre = cli.getPersonaNombre() +" "+ cli.getPersonaApellido();
                    l7.setText(nombre);
                    System.out.println(nombre);
                    
                    telefono = cli.getPersonaTelefono();
                    l9.setText(telefono);
                     System.out.println(telefono);
                     
                    direccion = cli.getPersonaDireccion();
                     System.out.println(direccion);
                     l11.setText(direccion);
                    
                    auto();
                    this.updateUI();
                    
                }else{
                    JOptionPane.showMessageDialog(null,"El cliente no existe ",
                            "Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null,"Verifique la cédula","Error",
                    JOptionPane.ERROR_MESSAGE);    
            
            e.printStackTrace();
        }
    }
    
    public void auto(){
        ArrayList<Producto> lista = (ArrayList<Producto>) cpd.proObtener(con);
        int n = lista.size();
        
        for(int i = 0; i < n; i++){
            ac.addItem(lista.get(i).getProductoNombre());
        } 
    }
    
    private String producto;
    private int cantidad;
    private double precioU;
    private double subtotalD;
    
    Producto pro;
    FacturaDetalle fd;
    
    DecimalFormat df;
    public void agregarProducto(){
        fd = new FacturaDetalle();
        producto = t2.getText();
        cantidad = Integer.parseInt(t3.getText());
        
        pro = cpd.proBuscar(con, producto);
        
        if(pro != null){
            if(cantidad == 0){
                JOptionPane.showMessageDialog(null,"Ingrese una cantidad válida",
                        "Error",JOptionPane.ERROR_MESSAGE);
            }else{
                if(cantidad > pro.getProductoStock()){
                    JOptionPane.showMessageDialog(null,"Revise el stock del producto",
                        "Error",JOptionPane.ERROR_MESSAGE);
                }else{
                    Object fila[] = new Object[4];
                    fila[0] = pro.getProductoNombre();
                    fila[1] = cantidad;
                    precioU = pro.getProductoPrecioVenta();
                    fila[2] = precioU;
                    
                    subtotalD = cantidad * precioU;
                    
                    
                    fila[3] = subtotalD; 

                    dt.addRow(fila);
                    completarCabecera();
                }

            }

        }else{
            
        }
    }
    
    private double subtotalC = 0;
    private String test2;
    private double test;
    private double iva;
    private double descuento;
    private double totalC;
    
    
    public void completarCabecera(){
        for(int i = 0; i < tb1.getRowCount(); i++){
            
            test = (double) tb1.getValueAt(i, 3);
            test = subtotalC + test;
            subtotalC = test;
            
            try {
                
                t4.setText(Double.toString(subtotalC));
                double d=(subtotalC * 0.12)*100;
                int s = (int)d;
                iva = (s/100);
                t5.setText(Double.toString(iva));
                
                //descuento = Double.parseDouble(t6.getText());
                double d2=(subtotalC + iva)*100;
                int s2=(int)d2;
                totalC = s2/100;
                t7.setText(Double.toString(totalC));

                
                
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
    }
    
    private String fechaFC ;
    
    public void terminarFactura(){
      
        try {
            String diaS = Integer.toString(dia);
            String mesS = Integer.toString(mes);
            String anioS = Integer.toString(anio);
            fechaFC = diaS +"/"+ mesS +"/"+ anioS;
            
            
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.YEAR, anio);
            cal.set(Calendar.MONTH, mes);
            cal.set(Calendar.DAY_OF_MONTH, dia);
            //Date dateN = formato.parse(fechaFC);
            java.sql.Date fechaFinal = new java.sql.Date(cal.getTimeInMillis()); 
                    
            FacturaCabecera fc = new FacturaCabecera();
            fc.setFacturaCabeceraFecha(fechaFinal);
            fc.setCliente(cli);
            //System.out.println(""+cli.getClienteId());
            fc.setFacturaCabeceraSubtotal(subtotalC);
            fc.setFacturaCabeceraIva(iva);
            fc.setFacturaCabeceraDescuento(descuento);
            fc.setFacturaCabeceraTotal(totalC);
            cfc.cabAgregar(con, fc);  
            
            int idFC = Integer.parseInt(ids);
            
            for(int i = 0; i < tb1.getRowCount(); i++){
                pro = cpd.proBuscar(con, (String) tb1.getValueAt(i,0));
                fd.setProducto(pro);
                cantidad = (int) tb1.getValueAt(i, 1);
                fd.setFacturaDetalleCantidad(cantidad);
                precioU = pro.getProductoPrecioVenta();
                fd.setFacturaDetallePrecioUnitario(precioU);
                subtotalD = (double) tb1.getValueAt(i, 3);
                fd.setFacturaDetalleSubtotal(subtotalD);
                
                cfd.detAgregar(con, fd, idFC);
                fc.addFacturasDetalle(fd);
            }
            
            
            Object[] options = {"Efectivo",
                                "Tarjeta"};
            int n = JOptionPane.showOptionDialog(null,"Eliga el método de pago",
                    "Método de Pago",JOptionPane.YES_NO_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,null,
                    options,
                    options[0]);

            if(n == 0){
                fc.setFacturaCabeceraTipoPago("EFECTIVO");
                cfc.cabEditarMetodoPago(con, fc);
                //imprimirFactura();
            }else{
                fc.setFacturaCabeceraTipoPago("TARJETA");
                cfc.cabEditarMetodoPago(con, fc);
                llamarVentanaPagoTarjeta(getDesktopPane());
            } 
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    public void llamarVentanaPagoTarjeta(JDesktopPane escritorio){
        VPagoTarjeta vpt = new VPagoTarjeta();
        vpt.setVisible(true);
        
        escritorio.add(vpt);
    }
    
    String ruta;
    
    public void imprimirFactura(){
        
        JFileChooser elegir = new JFileChooser();
        int opcion = elegir.showSaveDialog(this);
        if (opcion == JFileChooser.APPROVE_OPTION){
            File f = elegir.getSelectedFile();
            ruta = f.toString();
        }
        
        
        try {
            //Crea el documento con la ruta seleccionada y su extension
            FileOutputStream documento = new FileOutputStream(ruta + ".pdf");
            
            //Instancia el documento para editarlo
            Document pdf = new Document();
            
            PdfWriter writer = null;
            
            try {
                //Instancia para comenzar a editar el pdf
                writer = PdfWriter.getInstance(pdf, documento);
            } catch (Exception e) {
                e.getMessage();
            }
        
            //De aqui comienza la edicion del documento .pdf
            pdf.open();
            
            //Datos iniciales de la plantilla
            Paragraph tituloP = new Paragraph();
            tituloP.setAlignment(Paragraph.ALIGN_CENTER);
            tituloP.add("FACTURA Nº" + ids);
            
            Image imagen;
            imagen = Image.getInstance("src/imagenes/UPSlogo.png");
            imagen.setAlignment(Image.ALIGN_CENTER);
            imagen.scaleAbsoluteWidth(140f);
            imagen.scaleAbsoluteHeight(40f);
            
            //Separacion entre parrafos 1
            Paragraph saltoLinea1 = new Paragraph();
            saltoLinea1.add("\n");
            
            Paragraph tituloV = new Paragraph();
            tituloV.setAlignment(Paragraph.ALIGN_LEFT);
            tituloV.add("LPRT Veterinaria");
            
            Paragraph infoFecha = new Paragraph();
            infoFecha.setAlignment(Paragraph.ALIGN_LEFT);
            infoFecha.add("Fecha: " + fecha);
            
            Paragraph clienteDireccion = new Paragraph();
            clienteDireccion.setAlignment(Paragraph.ALIGN_LEFT);
            clienteDireccion.add("Dirección: " + direccion);
            
            Chunk ch1 = new Chunk("Subtotal: " + subtotalC);
            Chunk ch2 = new Chunk("IVA%: " + iva);
            Chunk ch3 = new Chunk("Total: " + totalC);
            Chunk ch4 = new Chunk("\t\t\t\t\t");
            
            
            Paragraph facturaCabe = new Paragraph();
            facturaCabe.setAlignment(Paragraph.ALIGN_CENTER);
            facturaCabe.add(ch1);
            facturaCabe.add(ch4);
            facturaCabe.add(ch2);
            facturaCabe.add(ch4);
            facturaCabe.add(ch3);

            
            PdfPTable table = new PdfPTable(4);

            //Celdas de la tabla con la informacion 
            Paragraph columna1 = new Paragraph("Producto");
            table.addCell(columna1);
            
            Paragraph columna2 = new Paragraph("Cantidad");
            table.addCell(columna2);
            
            Paragraph columna3 = new Paragraph("Precio U.");
            table.addCell(columna3);
            
            Paragraph columna4 = new Paragraph("Subtotal");
            table.addCell(columna4);
            
            FacturaCabecera fac = cfc.cabBuscar(con, cfc.obtenerId(con));
            ArrayList<FacturaDetalle> lista = (ArrayList<FacturaDetalle>) fac.getFacturasDetalle();
            
            int n = lista.size();
            
            for(int i = 0; i < n; i++){
                table.addCell(lista.get(i).getProducto().getProductoNombre());
                String pdfCantidad = Integer.toString(lista.get(i).getFacturaDetalleCantidad());
                table.addCell(pdfCantidad);
                String pdfPrecio = Double.toString(lista.get(i).getFacturaDetallePrecioUnitario());
                table.addCell(pdfPrecio);
                String pdfSubD =Double.toString(lista.get(i).getFacturaDetalleSubtotal());
                table.addCell(pdfSubD);
            }

            //Agregamos los parrafos y titulos
            try{
                pdf.add(tituloP);
                pdf.add(saltoLinea1);
                pdf.add(imagen);
                pdf.add(saltoLinea1);
                pdf.add(tituloV);
                pdf.add(saltoLinea1);
                pdf.add(infoFecha);
                pdf.add(new Chunk("Cliente: " + nombre +" "+ apellido));
                pdf.add(new Chunk("\n"));
                pdf.add(new Chunk("Cédula: " + cedula));
                pdf.add(new Chunk("\t\t\t\t\t"));
                pdf.add(new Chunk("Teléfono: " + telefono));
                pdf.add(clienteDireccion);
                pdf.add(saltoLinea1);
                pdf.add(table);
                pdf.add(saltoLinea1);
                pdf.add(facturaCabe);
                
            
            }catch(DocumentException ex){
                ex.getMessage();
            }
            
            pdf.close();
            
            //Mensaje de creacion
            JOptionPane.showMessageDialog(null, "Se creo el PDF");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        //Codigo para que el pdf se abra al crearse
        try {
                File rutaA = new File(ruta + ".pdf");
                Desktop.getDesktop().open(rutaA);
            } catch (Exception e) {
                e.printStackTrace();
            }
   
    }
}
