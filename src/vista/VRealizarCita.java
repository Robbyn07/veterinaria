
package vista;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import conexionbd.Conexion;
import conexionbd.ControladorCaracter;
import conexionbd.ControladorCita;
import conexionbd.ControladorCliente;
import conexionbd.ControladorMascota;
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
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelo.Cita;
import modelo.Cliente;
import modelo.Mascota;

/**
 *
 * @author Rakrad7101
 */
public class VRealizarCita extends JInternalFrame implements ActionListener{
    
    Conexion con;
    ControladorCaracter cca;
    ControladorCliente cc;
    ControladorMascota cm;
    ControladorCita cct;
    
    public VRealizarCita(Conexion con,ControladorCaracter cca,ControladorCliente cc,
            ControladorMascota cm,ControladorCita cct){
        this.con = con;
        this.cca = cca;
        this.cc = cc;
        this.cm = cm;
        this.cct = cct;
        initComponentes();
        ventanaRealizarC();
    }
    
    public void initComponentes(){
        setSize(800,500);
        setClosable(true);
        setTitle("Realizar Cita");
    }
    
    DefaultTableModel dt;
    private JTable tb1;
    private JScrollPane scr;
    private boolean[] editable = {false, true};
    
    private JButton b1;
    private JButton b2;
    private JButton b3;
    private JLabel l4;
    private JLabel l5;
    private JLabel l6;
    private JTextField t1;
    private JTextField t2;
    private JTextField t3;
    
    String cedula;
    String nombreC;
    String telefonoC;
    String direccionC;
    
    public void ventanaRealizarC(){
        
        Container cp = getContentPane();      
        cp.setLayout(new BorderLayout());
 
        JPanel p0 = new JPanel();
        p0.setLayout(new BorderLayout());
        
        JPanel p1 = new JPanel();
        GridBagConstraints g1 = new GridBagConstraints(); 
        p1.setLayout(new GridBagLayout());
        
        JLabel l1 = new JLabel("Fecha: ");
        g1.gridx =0;
        g1.gridy =0;
        p1.add(l1, g1);
       
        t1 = new JTextField(8);
        g1.gridx =1;
        g1.gridy =0;
        p1.add(t1, g1);
        
        JLabel l2 = new JLabel("Hora: ");
        g1.gridx =0;
        g1.gridy =1;
        p1.add(l2, g1);
        
        t2 = new JTextField(8);
        g1.gridx =1;
        g1.gridy =1;
        p1.add(t2, g1);
        
        JLabel l3 = new JLabel("Cédula:");
        g1.gridx =0;
        g1.gridy =2;
        p1.add(l3, g1);
        
        t3 = new JTextField(10);
        g1.gridx =1;
        g1.gridy =2;
        p1.add(t3, g1);
        
        b1 = new JButton("Buscar");
        b1.addActionListener(this);
        b1.setActionCommand("buscar");
        g1.gridx =2;
        g1.gridy =2;
        p1.add(b1, g1);
        
        JPanel p2 = new JPanel();
        p2.setLayout(new FlowLayout());
        
        l4 = new JLabel(nombreC);
        p2.add(l4);
        
        JLabel l7 = new JLabel(" ");
        p2.add(l7);
        
        l5 = new JLabel(telefonoC);
        p2.add(l5);
        
        JLabel l8 = new JLabel(" ");
        p2.add(l8);
        
        l6 = new JLabel(direccionC);
        p2.add(l6);
        
        p0.add(p1, BorderLayout.NORTH);
        p0.add(p2, BorderLayout.CENTER);
        
        JPanel p3 = new JPanel();
        p3.setLayout(new BorderLayout());
        
        dt = new DefaultTableModel(new String[]{"Mascota", "Check"}, 0) {
 
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Boolean.class
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
        p3.add(scr, BorderLayout.NORTH);
        
        JPanel p4 = new JPanel(); 
        p4.setLayout(new FlowLayout());
        
        b2 = new JButton("Volver");
        b2.addActionListener(this);
        b2.setActionCommand("volver");
        p4.add(b2);
        
        b3 = new JButton("Realizar");
        b3.addActionListener(this);
        b3.setActionCommand("terminar");
        p4.add(b3);
        
        cp.add(p0, BorderLayout.NORTH);
        cp.add(p3, BorderLayout.CENTER);
        cp.add(p4, BorderLayout.SOUTH);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        System.out.println("Comando: " + comando);
        
        switch(comando){
            case "buscar":
                buscarCliente();
                break;
            
            case "volver":
                setVisible(false);
                break;
                
            case "terminar":
                if(agregarCita() == true){   
                    imprimirCita();
                }
                break;
        }
    }
    
    Cliente cli;
    
    public void buscarCliente(){
        cedula = t3.getText();
        cli = cc.cliBuscar(con, cedula);
        
        try {
            if(cca.verificarCedula(cedula) == true){
                
                nombreC = cli.getPersonaNombre() +" "+ cli.getPersonaApellido();
                telefonoC = cli.getPersonaTelefono();
                direccionC = cli.getPersonaDireccion();

                mostrar();
                
            }
        } catch (HeadlessException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"Verifique la cédula","Error",
                    JOptionPane.ERROR_MESSAGE);        
        }
    }
     
    ArrayList<Mascota> lista;
    
    public void mostrar(){
        lista = (ArrayList<Mascota>) cli.getMascotas();//las mascotas ya estarian dentro del cliente
        int n = lista.size();
        
        for(int i = 0; i < n; i++){
            Object fila[] = new Object[2];
            fila[0] = lista.get(i).getMascotaNombre();
            fila[1] = false;

            dt.addRow(fila);
        }
        
    }
    
    String fecha;
    String hora;
    String time;
    String mascota;
    Timestamp fechaC;
    Boolean v;
    
    Cita cit;
    Mascota mas;
    
    public boolean agregarCita(){
        v = false;
        fecha = t1.getText();
        hora = t2.getText();
        time = fecha +" "+ hora;
        
        try {
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH.mm.ss");
            Date dateN = formato.parse(time);
            fechaC = new Timestamp(dateN.getTime());
            
            cit = new Cita();
            
            for(int i = 0; i < tb1.getRowCount(); i++){
                
                if((boolean)tb1.getValueAt(i, 1) == true){
                    mascota = (String) tb1.getValueAt(i, 0);
                    //se elimino el segundo for
                    mas = cm.masBuscar(con, lista.get(i).getMascotaId());//se podria usar la mascota del cliente ya guardado

                    if(mas.getMascotaNombre().equals(mascota)){
                        cit.setCitaFecha(dateN);

                        if(cct.citAgregar(con,cit,mas.getMascotaId()) == true){
                            mas.addCitas(cit);
                            v = true;
                            JOptionPane.showMessageDialog(null, "Operación Exitosa");
                            break;
                        }else{
                            JOptionPane.showMessageDialog(null,"No se completó "
                                    + "la operación","Error",JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }    
            
        } catch (ParseException e) {
            e.printStackTrace();
        }  
        return v;
    }
    
    
    String ruta;
    
    public void imprimirCita(){
        
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
            tituloP.add("CITA REALIZADA");
            
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
            
            Paragraph infoHora = new Paragraph();
            infoHora.setAlignment(Paragraph.ALIGN_LEFT);
            infoHora.add("Hora: " + hora);

            Paragraph tituloC = new Paragraph();
            tituloC.add("Información Cliente");
            
            Paragraph tituloM = new Paragraph();
            tituloM.add("Información Mascota");
            
            PdfPTable table = new PdfPTable(2);
            
            List listC = new List(List.UNORDERED);  
            // Add elements to the list       
            listC.add(new ListItem(nombreC));       
            listC.add(new ListItem(cedula));      
            listC.add(new ListItem(telefonoC));       
            listC.add(new ListItem(direccionC));
            
            List listM = new List(List.UNORDERED); 
            listM.add(new ListItem(mascota));       
            listM.add(new ListItem(mas.getEspecie().getEspecieNombre()));      
            listM.add(new ListItem(mas.getRaza().getRazaNombre()));       
            listM.add(new ListItem(mas.getMascotaGenero())); 
            

            PdfPCell celda1 = new PdfPCell(tituloC);
            PdfPCell celda2 = new PdfPCell(tituloM);
            PdfPCell celda3 = new PdfPCell();
            celda3.addElement(listC);
            PdfPCell celda4 = new PdfPCell();
            celda4.addElement(listM);
            
            celda3.setBorder(Rectangle.NO_BORDER);
            celda4.setBorder(Rectangle.NO_BORDER);
            
            float medidaCeldas[] = {30f, 30f};
            table.setWidths(medidaCeldas);
            
            table.addCell(celda1);
            table.addCell(celda2);
            table.addCell(celda3);
            table.addCell(celda4);
            
            //Agregamos los parrafos y titulos
            try{
                pdf.add(tituloP);
                pdf.add(saltoLinea1);
                pdf.add(imagen);
                pdf.add(saltoLinea1);
                pdf.add(tituloV);
                pdf.add(saltoLinea1);
                pdf.add(infoFecha);
                pdf.add(infoHora);
                pdf.add(saltoLinea1);
                pdf.add(table);
            
            }catch(DocumentException ex){
                ex.getMessage();
            }
            
            pdf.close();
            
            //Mensaje de creacio
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
