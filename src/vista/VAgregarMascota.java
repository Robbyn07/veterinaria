
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
        setSize(1000,500);
        setTitle("Agregar Mascotas");
        setClosable(true);
        setMaximizable(true);
    }
    
    private JButton b1;
    private JButton b2;
    private JButton b3;
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
        
        JPanel p1 = new JPanel();
        GridBagConstraints g1 = new GridBagConstraints(); 
        p1.setLayout(new GridBagLayout());
        
        JLabel l1 = new JLabel("Cédula:");
        g1.gridx =0;
        g1.gridy =0;
        p1.add(l1, g1);
        
        t1 = new JTextField(12);
        g1.gridx =1;
        g1.gridy =0;
        p1.add(t1, g1);
        
        b1 = new JButton("Buscar");
        g1.gridx = 2;
        g1.gridy = 0;
        b1.addActionListener(this);
        b1.setActionCommand("buscar");
        p1.add(b1, g1);
        
        l2 = new JLabel(nombreC);
        g1.gridx =0;
        g1.gridy =1;
        p1.add(l2, g1);
        
        l3 = new JLabel(telefonoC);
        g1.gridx =1;
        g1.gridy =1;
        p1.add(l3, g1);
        
        l4 = new JLabel(direccionC);
        g1.gridx =2;
        g1.gridy =1;
        p1.add(l4, g1);
        
        Panel p2 = new Panel();
        p2.setLayout(new GridBagLayout());
        
        JLabel l5 = new JLabel("Nombre:");
        g1.gridx =0;
        g1.gridy =2;
        g1.fill = GridBagConstraints.HORIZONTAL;
        p2.add(l5, g1);
        
        t2 = new JTextField(10);
        g1.gridx =1;
        g1.gridy =2;
        g1.fill = GridBagConstraints.HORIZONTAL;
        p2.add(t2, g1);
        
        JLabel l6 = new JLabel("Especie:");
        g1.gridx =2;
        g1.gridy =2;
        g1.fill = GridBagConstraints.HORIZONTAL;
        p2.add(l6, g1);
        
        t3 = new JTextField(10);
        g1.gridx =3;
        g1.gridy =2;
        g1.fill = GridBagConstraints.HORIZONTAL;
        p2.add(t3, g1);
        
        JLabel l7 = new JLabel("Raza:");
        g1.gridx =4;
        g1.gridy =2;
        g1.fill = GridBagConstraints.HORIZONTAL;
        p2.add(l7, g1);
        
        t4 = new JTextField(10);
        g1.gridx =5;
        g1.gridy =2;
        g1.fill = GridBagConstraints.HORIZONTAL;
        p2.add(t4, g1);
        
        JLabel l8 = new JLabel("Género:");
        g1.gridx =0;
        g1.gridy =4;
        g1.fill = GridBagConstraints.HORIZONTAL;
        p2.add(l8, g1);
        
        cb1 = new JComboBox<>();
            cb1.addItem("Macho");
            cb1.addItem("Hembra");
        g1.gridx =1;
        g1.gridy =4;
        g1.fill = GridBagConstraints.HORIZONTAL;
        p2.add(cb1, g1);
        
        JLabel l9 = new JLabel("Color:");
        g1.gridx =2;
        g1.gridy =4;
        g1.fill = GridBagConstraints.HORIZONTAL;
        p2.add(l9, g1);
        
        t5 = new JTextField(10);
        g1.gridx =3;
        g1.gridy =4;
        g1.fill = GridBagConstraints.HORIZONTAL;
        p2.add(t5, g1);
        
        JLabel l10 = new JLabel("Año de Nacimiento:");
        g1.gridx =4;
        g1.gridy =4;
        g1.fill = GridBagConstraints.HORIZONTAL;
        p2.add(l10, g1);
        
        yc = new JYearChooser();
        g1.gridx =5;
        g1.gridy =4;
        g1.fill = GridBagConstraints.HORIZONTAL;
        p2.add(yc, g1);

        Panel p3 = new Panel();
        p3.setLayout(new FlowLayout());

        b1 = new JButton("Volver");
        b1.addActionListener(this);
        b1.setActionCommand("volver");
        p3.add(b1, g1);
        
        b2 = new JButton("Registrar");
        b2.addActionListener(this);
        b2.setActionCommand("registrar");
        p3.add(b2, g1); 
        
        cp.add(p1, BorderLayout.NORTH);
        cp.add(p2, BorderLayout.CENTER);
        cp.add(p3, BorderLayout.SOUTH);
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
                if(agregarMascota() == true){
                    JOptionPane.showMessageDialog(null, "Operación Exitosa");
                }
                
                break;
 
        }
    }
    
    public void buscarCliente(){
        cedula = t1.getText();
        
        try {
            if(cca.verificarCedula(cedula) == true){
                if(cc.cliBuscar(con, cedula).getPersonaCedula().equals(cedula)){
                    nombreC = cc.cliBuscar(con, cedula).getPersonaNombre();
                    l2.setText(nombreC);
                    
                    telefonoC = cc.cliBuscar(con, cedula).getPersonaTelefono();
                    l3.setText(title);
                    
                    direccionC = cc.cliBuscar(con, cedula).getPersonaDireccion();
                    l4.setText(title); 
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
    
    String nombre;
    String especie;
    String raza;
    String genero;
    String color;
    int anio;
    String anio2;
    Boolean v;
    
    public boolean agregarMascota(){
        v = false;
        cedula = t1.getText();
        nombre = t2.getText();
        especie = t3.getText();
        raza = t4.getText();
        genero = cb1.getSelectedItem().toString();
        color = t5.getText();
        anio = yc.getYear();
        
        try {
            anio2 = Integer.toString(anio);
            SimpleDateFormat fec = new SimpleDateFormat("yyyy");
            java.util.Date dateN = fec.parse(anio2);
            java.sql.Date fechaN = new java.sql.Date(dateN.getTime()); 
            
            Cliente cli;
            cli = cc.cliBuscar(con, cedula);
            Mascota mas = new Mascota();
            Especie esp = ces.espBuscar(con, especie);
            Raza raz = cr.razBuscar(con, nombre);
            
            mas.setMascotaNombre(nombre);
            
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
                v = true;
            }
            
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        return v;
    }
}
