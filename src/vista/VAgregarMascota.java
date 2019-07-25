
package vista;

import com.toedter.calendar.JYearChooser;
import conexionbd.Conexion;
import conexionbd.ControladorCaracter;
import conexionbd.ControladorCliente;
import conexionbd.ControladorEspecie;
import conexionbd.ControladorMascota;
import conexionbd.ControladorRaza;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import modelo.Cliente;
import modelo.Especie;
import modelo.Mascota;
import modelo.Raza;

/**
 *
 * @author Rakrad7101
 */
public class VAgregarMascota extends JInternalFrame implements ActionListener{
    
    Conexion con;
    ControladorCaracter cca;
    ControladorCliente cc;
    ControladorMascota cm;
    ControladorEspecie ces;
    ControladorRaza cr;
    
    public VAgregarMascota(Conexion con,ControladorCaracter cca,ControladorCliente cc,
            ControladorMascota cm,ControladorEspecie ces,ControladorRaza cr){
        this.con = con;
        this.cca = cca;
        this.cc = cc;
        this.cm = cm;
        this.ces = ces;
        this.cr = cr;
        initComponentes();
        ventanaAgregarMas();
    }
    
    public void initComponentes(){
        setSize(600,300);
        setTitle("Agregar Mascotas");
        setClosable(true);
    }
    
    private JButton b1;
    private JButton b2;
    private JComboBox<String> cb1;
    private JLabel l2;
    private JLabel l3;
    private JLabel l4;
    private JYearChooser yc;
    private JTextField t1;
    private JTextField t2;
    private JTextField t3;
    private JTextField t4;
    private JTextField t5;
    
    String cedula;
    String nombreC = "Nombre";
    String telefonoC = "Teléfono";
    String direccionC = "Dirección";
    
    public void ventanaAgregarMas(){
        
        Container cp = getContentPane();   
        cp.setLayout(new BorderLayout()); 
        
        JPanel p0 = new JPanel();
        p0.setLayout(new BorderLayout());
        
        JPanel p1 = new JPanel();
        p1.setLayout(new FlowLayout());
        
        JLabel l1 = new JLabel("Cédula:");
        p1.add(l1);
        
        t1 = new JTextField(10);
        p1.add(t1);
        
        b1 = new JButton("Buscar");
        b1.addActionListener(this);
        b1.setActionCommand("buscar");
        p1.add(b1);
        
        JPanel p2 = new JPanel();
        p2.setLayout(new FlowLayout());
        
        l2 = new JLabel(nombreC);
        p2.add(l2);
        
        JLabel l11 = new JLabel(" ");
        p2.add(l11);
        
        l3 = new JLabel(telefonoC);
        p2.add(l3);
        
        JLabel l12 = new JLabel(" ");
        p2.add(l12);
        
        l4 = new JLabel(direccionC);
        p2.add(l4);
        
        p0.add(p1, BorderLayout.NORTH);
        p0.add(p2, BorderLayout.CENTER);
        
        Panel p3 = new Panel();
        p3.setLayout(new BorderLayout());
        
        JPanel p4 = new JPanel();
        p4.setLayout(new FlowLayout());
        
        JLabel l5 = new JLabel("Nombre:");
        p4.add(l5);
        
        t2 = new JTextField(10);
        p4.add(t2);
        
        JLabel l6 = new JLabel("Especie:");
        p4.add(l6);
        
        t3 = new JTextField(7);
        p4.add(t3);
        
        JLabel l7 = new JLabel("Raza:");
        p4.add(l7);
        
        t4 = new JTextField(7);
        p4.add(t4);
        
        JPanel p5 = new JPanel();
        p5.setLayout(new FlowLayout());
        
        JLabel l8 = new JLabel("Género:");
        p5.add(l8);
        
        cb1 = new JComboBox<>();
            cb1.addItem("Macho");
            cb1.addItem("Hembra");
        p5.add(cb1);
        
        JLabel l9 = new JLabel("Color:");
        p5.add(l9);
        
        t5 = new JTextField(6);
        p5.add(t5);
        
        JLabel l10 = new JLabel("Año de Nacimiento:");
        p5.add(l10);
        
        yc = new JYearChooser();
        p5.add(yc);

        p3.add(p4, BorderLayout.NORTH);
        p3.add(p5, BorderLayout.CENTER);
        
        Panel p6 = new Panel();
        p3.setLayout(new FlowLayout());

        b1 = new JButton("Volver");
        b1.addActionListener(this);
        b1.setActionCommand("volver");
        p6.add(b1);
        
        b2 = new JButton("Registrar");
        b2.addActionListener(this);
        b2.setActionCommand("registrar");
        p6.add(b2); 
        
        cp.add(p0, BorderLayout.NORTH);
        cp.add(p3, BorderLayout.CENTER);
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
            
            case "volver":
                setVisible(false);
                break;
                
            case "registrar":
                agregarMascota();
                break;
 
        }
    }
    
    Cliente cli;
    
    public void buscarCliente(){
        cedula = t1.getText();
        
        cli = cc.cliBuscar(con, cedula);
        
        try {
            if(cca.verificarCedula(cedula) == true){
                
                nombreC = cli.getPersonaNombre() + " " + cli.getPersonaApellido();

                telefonoC = cli.getPersonaTelefono();

                direccionC = cli.getPersonaDireccion();
            }
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null,"Verifique la cédula","Error",
                    JOptionPane.ERROR_MESSAGE);        
        }
    }
    
    String nombreM;
    String especie;
    String raza;
    String genero;
    String color;
    int anio;
    String anioS;
    
    public void agregarMascota(){
        nombreM = t2.getText();
        especie = t3.getText();
        raza = t4.getText();
        genero = cb1.getSelectedItem().toString();
        color = t5.getText();
        anio = yc.getYear();
        
        try {
            anioS = Integer.toString(anio);
            SimpleDateFormat fec = new SimpleDateFormat("yyyy");
            Date dateN = fec.parse(anioS);
            java.sql.Date fechaN = new java.sql.Date(dateN.getTime()); 
            
            Mascota mas = new Mascota();
            Especie esp = ces.espBuscar(con, especie);
            Raza raz = cr.razBuscar(con, raza);
            
            mas.setMascotaNombre(nombreM);
            
            if(esp == null){
                esp = new Especie();
                esp.setEspecieNombre(especie);
                ces.espAgregar(con, esp);
            }
            
            if(raz == null){
                raz = new Raza();
                raz.setRazaNombre(raza);
                cr.razAgregar(con, raz);
            }
            
            mas.setEspecie(esp);
            mas.setRaza(raz);
            mas.setMascotaGenero(genero);
            mas.setMascotaColor(color);
            mas.setAnio(fechaN);
            
            if(cm.masAgregar(con, mas, cli.getClienteId())==true){
                cli.addMascotas(mas);
                JOptionPane.showMessageDialog(null,"Operación Exitosa");
            }else{
                JOptionPane.showMessageDialog(null,"No se completó la operación"
                        ,"Error",JOptionPane.ERROR_MESSAGE); 
            }
            
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
    }
}
