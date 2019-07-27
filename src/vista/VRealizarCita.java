package vista;
import conexionbd.Conexion;
import conexionbd.ControladorCaracter;
import conexionbd.ControladorCita;
import conexionbd.ControladorCliente;
import conexionbd.ControladorMascota;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import modelo.Cita;
import modelo.Mascota;

public class VRealizarCita extends JInternalFrame implements ActionListener{

	private JTextField fecha;
	private JTextField hora;
	private JTextField cedula;
	private JTextField nombre;
	private JButton botonBuscar;
	private JTable tablaMascotas;
	private JTable tablaSemana;
	private JButton pasada;
	private JButton siguiente;
	private JButton agregar;
	
	//Creamos la tabla semanal
	private Timestamp timestamp=null;
	private Date parsedDate=null;
	private SimpleDateFormat dateFormat=null;
	private String fechaActual = null;
	private SimpleDateFormat hourFormat = null;
	private Date hour =null;
	private DefaultTableModel modelo2;
	
	//Setamos los tipos de calendarios para dias y horas
	Calendar calD = null;
	Calendar calH = null;
	int dLunes = 0;
	String lunes = null;
	int dMartes = 0;
	String martes = null;
	int dMiercoles = 0;
	String miercoles = null;
	int dJueves = 0;
	String jueves = null;
	int dViernes = 0;
	String viernes = null;
	JLabel labelAno=null;
	JLabel labelMes=null;
	
	Container container=null;
	
        Conexion con;
        ControladorCaracter cca;
        ControladorCliente cc;
        ControladorMascota cm;
        ControladorCita cct;
        
        
	public VRealizarCita(Conexion con, ControladorCaracter cca, ControladorCliente cc, 
                ControladorMascota cm, ControladorCita cct) {
                this.con=con;
                this.cca=cca;
                this.cc=cc;
                this.cm=cm;
                this.cct=cct;
		setSize(1180,700);
		setMaximizable(false);
		setResizable(false);
		setClosable(true);
		setTitle("Agregar Cita");
		
		container= getContentPane();
		container.setLayout(new java.awt.GridBagLayout());
		
		GridBagConstraints gb = new GridBagConstraints();
		
		//Seteamos como primer dia de la tabla el dia actual
		try {
		    dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		    parsedDate = new Date();
		    fechaActual = dateFormat.format(parsedDate);
		    timestamp = new java.sql.Timestamp(parsedDate.getTime());
		    hourFormat = new SimpleDateFormat("hh:mm");
		    hour = hourFormat.parse( "07:00" );    
		} catch(Exception e) { 
		    e.printStackTrace();
		} 
		calD = Calendar.getInstance();
		calH = Calendar.getInstance();
		
		
		//Panel de Muestra de Datos
		JPanel panelDatos = new JPanel();
		panelDatos.setLayout(new java.awt.GridBagLayout());
		panelDatos.setSize(450, 700);
		
			//Label fecha
			JLabel labelFecha = new JLabel("Fecha:");
			gb.gridx=0;
			
			panelDatos.add(labelFecha, gb);
			
			//JTextField fecha
			fecha = new JTextField(10);
			gb.gridx=1;
			gb.gridy=0;
			panelDatos.add(fecha, gb);
			
			//Label Hora
			JLabel labelhora = new JLabel("Hora:");
			gb.gridx=0;
			gb.gridy=1;
			panelDatos.add(labelhora, gb);
		
			//JTextField Hora
			hora = new JTextField(5);
			gb.gridx=1;
			gb.gridy=1;
			gb.fill=GridBagConstraints.BOTH;
			
			panelDatos.add(hora, gb);
			
			//Label Cedula
			JLabel labelCedula = new JLabel("Cedula:");
			gb.gridx=0;
			gb.gridy=2;
			panelDatos.add(labelCedula, gb);
			
			//JTextField Cedula
			cedula = new JTextField(10);
			gb.gridx=1;
			gb.gridy=2;
			panelDatos.add(cedula, gb);
			
			//Boton Buscar
			botonBuscar = new JButton("Buscar");
			botonBuscar.addActionListener(this);
			botonBuscar.setActionCommand("Buscar");
			gb.gridx=2;
			gb.gridy=2;
			panelDatos.add(botonBuscar, gb);
			
			//Label Nombre
			JLabel labelNombre = new JLabel("Nombre:");
			gb.gridx=0;
			gb.gridy=3;
			panelDatos.add(labelNombre, gb);
			
			//JTextField nombre
			nombre = new JTextField(10);
			gb.gridx=1;
			gb.gridy=3;
			panelDatos.add(nombre, gb);
			
			//Boton agregar
			agregar = new JButton("Agregar");
			agregar.addActionListener(this);
			agregar.setActionCommand("Agregar");
			gb.gridx=2;
			gb.gridy=3;
			panelDatos.add(agregar, gb);
			
			//Agregamos el JTable
			
			String[] columnNames = {"Nombre", "Color"};
			DefaultTableModel modelo = new DefaultTableModel(columnNames,6);
			modelo.insertRow(0, columnNames);
			tablaMascotas = new JTable(modelo);
			JScrollPane jp = new JScrollPane(tablaMascotas);
			
			Color color = new Color(0,0,0);
			tablaMascotas.setBorder(null);
			tablaMascotas.setGridColor(color);
			
			gb.gridx=0;
			gb.gridy=4;
			gb.gridwidth=3;
			gb.fill=GridBagConstraints.BOTH;
			panelDatos.add(tablaMascotas, gb);
			
			
			//Agregamos panelDatos a container	
			gb.gridx=0;	
			gb.gridy=0;
			gb.fill=GridBagConstraints.BOTH;
			gb.gridheight=1;
			gb.gridwidth=1;
			container.add(panelDatos, gb);
			
			tabla();
            this.cca = cca;
			
		
	}

	/**
	 * Metodo para crear tabla
	 * @return
	 */
	private void tabla() {
		//Panel de Calendario
				JPanel panelCalendario = new JPanel();
				panelCalendario.setSize(1000,700);
				panelCalendario.setLayout(new java.awt.GridBagLayout());
				GridBagConstraints gb2 = new GridBagConstraints();
				
				//Boton de Semana Pasada
				pasada = new JButton("←");
				pasada.addActionListener(this);
				pasada.setActionCommand("Pasada");
				GridBagConstraints gb4 = new GridBagConstraints();
				gb4.gridx=0;
				gb4.gridy=0;
				gb4.fill=GridBagConstraints.CENTER;
				panelCalendario.add(pasada, gb4);
				
				//Label de mes actual
				labelMes = new JLabel(""+calD.get(Calendar.YEAR));
				gb4.gridx=1;
				gb4.gridy=0;
				panelCalendario.add(labelMes, gb4);
				
				//Label de año actual
				labelAno = new JLabel(" / "+(calD.get(Calendar.MONTH)+1));
				gb4.gridx=2;
				gb4.gridy=0;
				panelCalendario.add(labelAno, gb4);
				
				//Boton de Semana siguiente
				siguiente = new JButton("→");
				siguiente.addActionListener(this);
				siguiente.setActionCommand("Siguiente");
				gb4.gridx=3;
				gb4.gridy=0;
				panelCalendario.add(siguiente, gb4);
				
				//calD.add(Calendar.MONTH, 1);
				
				//Seteamos los dias de la semana
				//Lunes
				
				semana(calD);
				
				
				//for para setear la fecha en los dias
				 String[]columnSemana = {"Horario", lunes , martes, miercoles, jueves, viernes};
                                 modelo2 = new DefaultTableModel(columnSemana,0);
	
				
				//Se ubican los dias en la tabla
				//String[]columnSemana = {"Horario", lunes , martes, miercoles, jueves, viernes};
				//modelo2.setV
				//modelo2.insertRow(0, columnSemana);
				tabla2();
				
                                
				
				
				tablaSemana = new JTable(modelo2);
				tablaSemana.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				
				tablaSemana.addMouseListener(new MouseAdapter() {
					
					public void mouseClicked(java.awt.event.MouseEvent event) {
						int row = tablaSemana.rowAtPoint(event.getPoint());
					    int col = tablaSemana.columnAtPoint(event.getPoint());
					    
					    //System.out.print("\nfila: "+ row + " columna: "+ col);
					    
					    hora.setText((String) (tablaSemana.getValueAt(row, 0)));
					    
					    //lunes
					    if(col==1) {
					    	calD.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
							String lunes2= calD.get(Calendar.DAY_OF_MONTH)+"/"+(calD.get(Calendar.MONTH)+1)+"/"+calD.get(Calendar.YEAR);
							fecha.setText(lunes2);
					    }
					    
					    if(col==2) {
					    	calD.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
							String martes2= calD.get(Calendar.DAY_OF_MONTH)+"/"+(calD.get(Calendar.MONTH)+1)+"/"+calD.get(Calendar.YEAR);
							fecha.setText(martes2);
					    }
					    
					    if(col==3) {
					    	calD.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
							String miercoles2= calD.get(Calendar.DAY_OF_MONTH)+"/"+(calD.get(Calendar.MONTH)+1)+"/"+calD.get(Calendar.YEAR);
							fecha.setText(miercoles2);
					    }
					    if(col==4) {
					    	calD.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
							String jueves2= calD.get(Calendar.DAY_OF_MONTH)+"/"+(calD.get(Calendar.MONTH)+1)+"/"+calD.get(Calendar.YEAR);
							fecha.setText(jueves2);
					    }
					    if(col==5) {
					    	calD.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
							String viernes2= ""+calD.get(Calendar.DAY_OF_MONTH)+"/"+(calD.get(Calendar.MONTH)+1)+"/"+calD.get(Calendar.YEAR);
							fecha.setText(viernes2);
					    }
					    
					}
				});
				
				//Damos color a las tabas
				Color color = new Color(0,0,0);
				tablaSemana.setBorder(null);
				tablaSemana.setGridColor(color);
				
				//Agregamos el Jpanel
				JScrollPane jp2 = new JScrollPane(tablaSemana);
				jp2.setSize(500, 400);
				gb2.gridx=0;
				gb2.gridy=1;
				gb2.gridwidth=4;
				panelCalendario.add(jp2, gb2);
				panelCalendario.setOpaque(true);
				
				gb2.gridx=1;
				gb2.gridy=0;
				
				container.add(panelCalendario, gb2);
				
	seteoCitas();			
	}

	/**
	 * Metodo que cambia la semana
	 * @param cal
	 */
	private void semana(Calendar cal) {
		// TODO Auto-generated method stub
		calD.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		dLunes = calD.get(Calendar.DAY_OF_MONTH);
		lunes="Lunes "+ dLunes;
		//Martes
		calD.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
		dMartes =calD.get(Calendar.DAY_OF_MONTH);
		martes="Martes "+ dMartes;
		//Miercoles
		calD.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
		dMiercoles = calD.get(Calendar.DAY_OF_MONTH);
		miercoles="Miercoles "+ dMiercoles;
		//Jueves
		calD.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
		dJueves = calD.get(Calendar.DAY_OF_MONTH);
		jueves="Jueves "+dJueves;
		//Viernes
		calD.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
		dViernes = calD.get(Calendar.DAY_OF_MONTH);
		viernes="Viernes "+ dViernes;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String comando = e.getActionCommand();
		switch (comando) {
		case "Siguiente":
			//Llamamos a metodo SumarSemana
			SumarSemana();
			
			break;
		case "Pasada":
			RestarSemana();
			break;

		case "Buscar":
			buscarMascota();
			break;
			
		case "Agregar":
			agregarCita();
			break;
		default:
			break;
		}
	}

	private void buscarMascota() {
		// TODO Auto-generated method stub
		String Scedula = cedula.getText();
		ArrayList<Mascota> lista=(ArrayList<Mascota>) cm.masListarCed(con, Scedula);
                listarMascotas(lista);
	}

	private void agregarCita() {
            try {
                // TODO Auto-generated method stub
                String Sfecha = fecha.getText();
                String Shora = hora.getText();
                String Scedula = cedula.getText();
                String Snombre = nombre.getText().toUpperCase();
                Cita cita = new Cita();
                String ff=Sfecha+" "+Shora;
                Date dd= new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(ff);
                cita.setCitaFecha(dd);
                
                cita.setMascota(cm.masNomCed(con, Snombre, Scedula));
                /**
                 *
                 * MANDA A AGREGAR CITA
                 */
                //Objeto Cita y mascota_id
                if(cct.citAgregar(con, cita)){
                    JOptionPane.showMessageDialog(null, "Operación Exitosa");
                    limpiaTabla();
                    //tabla();
                    tabla2();

                    seteoCitas();
                    updateTabla();
                    modelo2.fireTableDataChanged();
                    tablaSemana.repaint();
                    SwingUtilities.updateComponentTreeUI(this);
                }
                
            } catch (ParseException ex) {
                Logger.getLogger(VRealizarCita.class.getName()).log(Level.SEVERE, null, ex);
            }
	}

	private void RestarSemana() {
		// TODO Auto-generated method stub
		calD.add(Calendar.DAY_OF_YEAR, -7);
		semana(calD);
		labelAno.setText(" / "+(calD.get(Calendar.MONTH)+1));
		labelMes.setText(""+calD.get(Calendar.YEAR));
                limpiaTabla();
                //tabla();
                tabla2();
                
                seteoCitas();
		updateTabla();
		modelo2.fireTableDataChanged();
		tablaSemana.repaint();
		SwingUtilities.updateComponentTreeUI(this);
	}

	private void updateTabla() {
		// TODO Auto-generated method stub
		String[] columnSemana = {"Horario", lunes , martes, miercoles, jueves, viernes};
		modelo2.setColumnIdentifiers(columnSemana);
	}

	private void SumarSemana() {
		// TODO Auto-generated method stub      
		calD.add(Calendar.DAY_OF_YEAR, 7);
		semana(calD);
		labelAno.setText(" / "+(calD.get(Calendar.MONTH)+1));
		labelMes.setText(""+calD.get(Calendar.YEAR));
                limpiaTabla();
                //tabla();
                tabla2();
                seteoCitas();
		updateTabla();
		modelo2.fireTableDataChanged();
		tablaSemana.repaint();
		SwingUtilities.updateComponentTreeUI(this);
	}

   
    private void seteoCitas() {
        //agarramos la lista de citas
        ArrayList<Cita> list=(ArrayList<Cita>) cct.citObtenerGeneral(con);
        int x= list.size();
        DateFormat formato1 = new SimpleDateFormat("dd/mm/yyyy");
        DateFormat formato2 = new SimpleDateFormat("hh:mm");
        Date inicio=null;
            try {
                inicio = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(dLunes+"/"+(calD.get(Calendar.MONTH)+1)+"/"+(calD.get(Calendar.YEAR))+" 01:00:00");
            } catch (ParseException ex) {
                Logger.getLogger(VRealizarCita.class.getName()).log(Level.SEVERE, null, ex);
            }
        int fil=0;
        int col=0;
        //Pasar toda la lista
        for(int i=0; i<x; i++){
            Cita cita=list.get(i);
            String dddd= new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm").format(cita.getCitaFecha());
            //System.out.println(""+dddd);

            String f = dddd.substring(0, dddd.indexOf(" "));
            //System.out.print(f);
            String h = dddd.substring(dddd.indexOf(" ")+1);
            //System.out.print(h);
            if(cita.getCitaFecha().after(inicio)){
                //Compracion de Hora
                //System.out.println(""+f);
                for(int j=1; j<26;j++){
                    if(tablaSemana.getValueAt(j, 0).equals(h)){
                       
                       fil=j;
                       
                        System.out.println("fila="+fil);
                        System.out.println(h);
                        System.out.println(f);
                       break;
                    }
                }
                //Comparacion de Dia
                String dd="D";
                Calendar calX = calD;
                for(int j=1; j<6;j++){
                    //Lunes
                    if(j==1){
                        calX.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
                        String mm = ""+(calX.get(Calendar.MONTH)+1);
                        String auxd = ""+calX.get(Calendar.DAY_OF_MONTH);
                        if(mm.length()==1){
                            mm="0"+mm;
                        }
                        if(auxd.length()==1){
                            auxd="0"+auxd;
                        }
			dd= auxd+"/"+mm+"/"+calX.get(Calendar.YEAR);
                    }
                    //Martes
                    if(j==2){
                        calX.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
			String mm = ""+(calX.get(Calendar.MONTH)+1);
                        String auxd = ""+calX.get(Calendar.DAY_OF_MONTH);
                        if(mm.length()==1){
                            mm="0"+mm;
                        }
                        if(auxd.length()==1){
                            auxd="0"+auxd;
                        }
			dd= auxd+"/"+mm+"/"+calX.get(Calendar.YEAR);
                    }
                    //Miercoles
                    if(j==3){
                        calX.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
			String mm = ""+(calX.get(Calendar.MONTH)+1);
                        String auxd = ""+calX.get(Calendar.DAY_OF_MONTH);
                        if(mm.length()==1){
                            mm="0"+mm;
                        }
                        if(auxd.length()==1){
                            auxd="0"+auxd;
                        }
			dd= auxd+"/"+mm+"/"+calX.get(Calendar.YEAR);
                        System.out.println("dd="+dd);
                    }
                    //Jueves
                    if(j==4){
                        calX.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
			String mm = ""+(calX.get(Calendar.MONTH)+1);
                        String auxd = ""+calX.get(Calendar.DAY_OF_MONTH);
                        if(mm.length()==1){
                            mm="0"+mm;
                        }
                        if(auxd.length()==1){
                            auxd="0"+auxd;
                        }
			dd= auxd+"/"+mm+"/"+calX.get(Calendar.YEAR);
                        
                    }
                    //Viernes
                    if(j==5){
                        calX.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
			String mm = ""+(calX.get(Calendar.MONTH)+1);
                        String auxd = ""+calX.get(Calendar.DAY_OF_MONTH);
                        if(mm.length()==1){
                            mm="0"+mm;
                        }
                        if(auxd.length()==1){
                            auxd="0"+auxd;
                        }
			dd= auxd+"/"+mm+"/"+calX.get(Calendar.YEAR);
                    }
                    if(f.equals(dd)){
                        System.out.println("ENTRAAAA");
                        col=j;
                         System.out.println("col="+col);
                        break;
                    }
                }
            }
            
            //Si tiene valor fil y col se inserta
            if(fil>0 && col>0){
                tablaSemana.setValueAt(cita.getMascota().getMascotaNombre(), fil, col);
            }
            fil=0;
            col=0;
            
            
        }
    }

    private void listarMascotas(ArrayList<Mascota> lista) {
        for(int i=0;i<lista.size();i++){
            tablaMascotas.setValueAt(lista.get(i).getMascotaNombre(),i+1,0);
            tablaMascotas.setValueAt(lista.get(i).getMascotaColor(), i+1, 1);
        }
       
    }

    private void limpiaTabla() {
        DefaultTableModel dm = (DefaultTableModel)tablaSemana.getModel();
        dm.getDataVector().removeAllElements();
        dm.fireTableDataChanged();
    }
    public void tabla2(){
       	
	calH.setTime(hour);	//calendario para horario
							
        String newTimeH=hourFormat.format(calH.getTime());
				
				//For para setear los horarios
				for(int i=0; i<34 ; i++) {
					if(i>0) {
						if(i<16) {
							calH.add(Calendar.MINUTE, 15);
							newTimeH = hourFormat.format(calH.getTime());
						}else {
							if(i==25) {
								calH.add(Calendar.HOUR, 2);
								newTimeH = hourFormat.format(calH.getTime());
							}else {
								calH.add(Calendar.MINUTE, 15);
								newTimeH = hourFormat.format(calH.getTime());

							}
							
						}
						
						
					}
					
					String[] columnHorario = { newTimeH, null, null, null, null, null, null, null};
					modelo2.insertRow(i,columnHorario);
				}
				
    }
}