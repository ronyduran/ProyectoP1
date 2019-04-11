package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ColorUIResource;

import logica.Evento;
import logica.PlanificacionEvento;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Image;
import java.awt.Label;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class PantallaPrincipal extends JFrame implements Runnable  {

	private JPanel contentPane;
	private Dimension dim;
	Thread h1;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallaPrincipal frame = new PantallaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PantallaPrincipal() {
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				FileOutputStream planiIn;
				ObjectOutputStream planiWrite;
				try {
					planiIn = new  FileOutputStream("BDEvento.dat");
					planiWrite = new ObjectOutputStream(planiIn);
					planiWrite.writeObject(PlanificacionEvento.getInstance());
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(PantallaPrincipal.class.getResource("/Imagenes/Planificar.png")));
		setTitle("Sistema de Planificaci\u00F3n de Eventos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 611, 437);
		setResizable(false);
		dim = super.getToolkit().getScreenSize();
		super.setSize(dim.width, (dim.height-50));
		setLocationRelativeTo(null);
		
		
		
		
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(220, 220, 220));
		setJMenuBar(menuBar);
		
		JMenu mnEvento = new JMenu("Evento");
		mnEvento.setFont(new Font("Segoe UI", Font.BOLD, 17));
		menuBar.add(mnEvento);
		
		h1 = new Thread(this);
	     h1.start();
	     
	   
		
		JMenuItem mntmInscripcinDeTrabajo = new JMenuItem("Creacion de Evento");
		mntmInscripcinDeTrabajo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CrearEvento ev= new CrearEvento();
				ev.setModal(true);
				ev.setVisible(true);
				
			}
		});
		mntmInscripcinDeTrabajo.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		mnEvento.add(mntmInscripcinDeTrabajo);
		
		JMenuItem mntmListadosDeTrabajos = new JMenuItem("Listados de Eventos");
		mntmListadosDeTrabajos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarEvento ListE = new ListarEvento();
				ListE.setModal(true);
				ListE.setVisible(true);
				ListE.setLocationRelativeTo(null);
			}
		});
		mntmListadosDeTrabajos.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		mnEvento.add(mntmListadosDeTrabajos);
		
		JMenuItem mntmCancelarmodificar = new JMenuItem("Cancelar/Modificar");
		mntmCancelarmodificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CancelarPosponer cp=new CancelarPosponer();
				cp.setModal(true);
				cp.setVisible(true);
			}
		});
		mntmCancelarmodificar.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		mnEvento.add(mntmCancelarmodificar);
		
		JMenuItem mntmImprimirReporte = new JMenuItem("Imprimir Reporte");
		mntmImprimirReporte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UIManager UI=new UIManager(); 
				UI.put("OptionPane.background",new ColorUIResource(176, 196, 222)); 
				UI.put("Panel.background",new ColorUIResource(176, 196, 222)); 
				UIManager.put("OptionPane.buttonFont", new Font("Tahoma", Font.PLAIN, 15));
				UIManager.put("OptionPane.font",new Font("Tahoma", Font.PLAIN, 15));
				Label mensaje= new Label("Escriba el C�digo del Evento");
				
				
				mensaje.setFont(new java.awt.Font("Tahoma", Font.PLAIN, 17));
				String codigo=null;
				if((codigo=((String)(JOptionPane.showInputDialog(null,mensaje,"Imprimir Reporte",JOptionPane.DEFAULT_OPTION, null, null, null))))!= null) {
					
						if(!codigo.equalsIgnoreCase("")) {
							Evento aux = PlanificacionEvento.getInstance().BuscarEventoCodigo(codigo);
							if (aux!=null) {
								SimpleDateFormat formatter = new SimpleDateFormat("hh:mm:ss dd-MMM-yyyy");
								SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy");
								String linea1 = "\r\n\r\nFecha de solicitud: " +formatter.format(new Date());
								String linea2 ="\r\nC�digo del Evento: "+aux.getIdentificador();
								String linea3 ="\r\nNombre del Evento: "+aux.getNombreEvento();
								String linea4 = "\r\nFecha del Evento: "+formatter1.format(aux.getFechaEvento());
								
								
								String linea6 = "\r\nCantidad de Trabajos Inscritos: " + aux.getLosTrabajos().size();
								String linea7 = "\r\nCantidad de Comisiones Inscritas: " + aux.getLasComisiones().size();
								String linea8 = "\r\nNota Extra:\r\n "+ aux.getJustificacion();
								File reporte = new File ("Reporte.txt");
						        FileWriter escritor;
						        
						        try {
						        	escritor = new FileWriter(reporte);
						        	escritor.write("Pontificia Universidad Cat�lica Madre y Maestra");
									escritor.write("\r\nSistema de Planificaci�n de Eventos");
									escritor.write("\r\n\r\nReporte del Evento");
									escritor.write(linea1);
									escritor.write(linea2);
									escritor.write(linea3);
									escritor.write(linea4);
									if (aux.isEstado()==true) {
										escritor.write("\r\nEstado del Evento: Activo");
									}else {
										escritor.write("\r\nEstado del Evento: No Activo");
										
									}
									if (aux.getJustificacion()!=null) {
										escritor.write(linea8);
									}
									
									escritor.write(linea6);
									escritor.write(linea7);
									
									if (aux.getRecursos().size()>0) {
										escritor.write("\r\n\r\nRecursos Utilizados:\r\n");
										for (int i = 0; i < aux.getRecursos().size(); i++) {
										
											escritor.write("\r\n-"+aux.getRecursos().get(i));
											
										}
									}
									
									if (aux.getLosTrabajos().size()>0) {
										escritor.write("\r\n\r\nDetalles de los Trabajos");
										for (int i = 0; i < aux.getLosTrabajos().size(); i++) {
											escritor.write("\r\n\r\nInformaci�n del Trabajo:");
											escritor.write("\r\nC�digo del Trabajo: "+aux.getLosTrabajos().get(i).getIdentificador());
											escritor.write("\r\nNombre del Trabajo: "+aux.getLosTrabajos().get(i).getNombreTrabajo());
											escritor.write("\r\n�rea del Trabajo: "+aux.getLosTrabajos().get(i).getAreaTrabajo());
											escritor.write("\r\nC�dula del Participante: "+aux.getLosTrabajos().get(i).getElParticipante().getCedula());
											escritor.write("\r\nNombre del Participante: "+aux.getLosTrabajos().get(i).getElParticipante().getNombre());
											escritor.write("\r\nComisi�n Encargada: "+aux.getLosTrabajos().get(i).getLaComision().getCodigo());
											
										}
									}else {
										escritor.write("\r\n\r\nNo hay Trabajos Inscritos.");
									}
									
									escritor.close();
									JOptionPane.showMessageDialog(null,"El Reporte se ha Imprimido");
								} catch (IOException e2) {
									// TODO: handle exception
									e2.printStackTrace();
								}
								
							}else {
								JOptionPane.showMessageDialog(null, "No se ha encontrado el Evento", "Validaci�n", JOptionPane.WARNING_MESSAGE);
							}
							
					        
						}else {
						
						
						}
				}
				
			}
		});
		mntmImprimirReporte.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		mnEvento.add(mntmImprimirReporte);
		
		JMenu mnTrabajo = new JMenu("Trabajo");
		mnTrabajo.setFont(new Font("Segoe UI", Font.BOLD, 17));
		menuBar.add(mnTrabajo);
		
		JMenuItem mntmRegistrarTrabajo = new JMenuItem("Registrar trabajo");
		mntmRegistrarTrabajo.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		mntmRegistrarTrabajo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				RegistrarTrabajo rt = new RegistrarTrabajo("",false);
				rt.setModal(true);
				rt.setVisible(true);
				
			}
		});
		mnTrabajo.add(mntmRegistrarTrabajo);
		
		JMenuItem mntmListadoDeTrabajos = new JMenuItem("Listado de trabajos");
		mntmListadoDeTrabajos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarTrabajo lt = new ListarTrabajo();
				lt.setModal(true);
				lt.setVisible(true);
				
			}
		});
		mntmListadoDeTrabajos.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		mnTrabajo.add(mntmListadoDeTrabajos);
		
		JMenu mnJurado = new JMenu("Jurado");
		mnJurado.setFont(new Font("Segoe UI", Font.BOLD, 17));
		menuBar.add(mnJurado);
		
		JMenuItem mntmRegistrarJurado = new JMenuItem("Registrar Jurado");
		mntmRegistrarJurado.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		mntmRegistrarJurado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistrarPersona regP = new RegistrarPersona(2,"",false);
				regP.setModal(true);
				regP.setLocationRelativeTo(null);
				regP.setVisible(true);
				
			}
		});
		mnJurado.add(mntmRegistrarJurado);
		
		JMenuItem mntmListadoDeJurados = new JMenuItem("Listado de Jurados");
		mntmListadoDeJurados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ListarJurados lu=new ListarJurados();
				lu.setModal(true);
				lu.setVisible(true);
			}
		});
		mntmListadoDeJurados.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		mnJurado.add(mntmListadoDeJurados);
		
		JMenu mnComisin = new JMenu("Comisi\u00F3n");
		mnComisin.setFont(new Font("Segoe UI", Font.BOLD, 17));
		menuBar.add(mnComisin);
		
		JMenuItem mntmRegistrarComisin = new JMenuItem("Registrar Comisi\u00F3n");
		mntmRegistrarComisin.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		mntmRegistrarComisin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistrarComision RegC = new RegistrarComision();
				RegC.setModal(true);
				RegC.setLocationRelativeTo(null);
				RegC.setVisible(true);
			}
		});
		mnComisin.add(mntmRegistrarComisin);
		
		JMenuItem mntmListadosDeComisiones = new JMenuItem("Listados de Comisiones");
		mntmListadosDeComisiones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarComision ListC = new ListarComision();
				ListC.setModal(true);
				ListC.setLocationRelativeTo(null);
				ListC.setVisible(true);
			}
		});
		mntmListadosDeComisiones.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		mnComisin.add(mntmListadosDeComisiones);
		
		JMenu mnParticipantes = new JMenu("Participantes");
		mnParticipantes.setFont(new Font("Segoe UI", Font.BOLD, 17));
		menuBar.add(mnParticipantes);
		
		JMenuItem mntmListadosDeParticipantes = new JMenuItem("Listados de Participantes");
		mntmListadosDeParticipantes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarParticipante lp=new ListarParticipante();
				
				lp.setModal(true);
				lp.setVisible(true);
			}
		});
		mntmListadosDeParticipantes.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		mnParticipantes.add(mntmListadosDeParticipantes);
		
		JMenu mnAdministrador = new JMenu("Administrador");
		if(!PlanificacionEvento.getLoginUser().getTipo().equalsIgnoreCase("Administrador")){
			mnAdministrador.setEnabled(false);
		}
		mnAdministrador.setFont(new Font("Segoe UI", Font.BOLD, 17));
		menuBar.add(mnAdministrador);
		
		
		JMenuItem mntmRegistrarUsuario = new JMenuItem("Registrar Usuario");
		mntmRegistrarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				regUser ru = new regUser();
				ru.setModal(true);
				ru.setVisible(true);
				
			}
		});
		mntmRegistrarUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		mnAdministrador.add(mntmRegistrarUsuario);
		
		JMenuItem mntmIngresarRecurso = new JMenuItem("Ingresar Recurso");
		mntmIngresarRecurso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				UIManager UI=new UIManager(); 
				UI.put("OptionPane.background",new ColorUIResource(176, 196, 222)); 
				UI.put("Panel.background",new ColorUIResource(176, 196, 222)); 
				UIManager.put("OptionPane.buttonFont", new Font("Tahoma", Font.PLAIN, 15));
				UIManager.put("OptionPane.font",new Font("Tahoma", Font.PLAIN, 15));
				Label mensaje= new Label("Ingrese el nuevo recurso");
				
				
				mensaje.setFont(new java.awt.Font("Tahoma", Font.PLAIN, 17));
				String recurso=null;
				if((recurso=((String)(JOptionPane.showInputDialog(null,mensaje,"Registro de Recurso",JOptionPane.DEFAULT_OPTION, null, null, null))))!= null) {
					
						if(!recurso.equalsIgnoreCase("")) {
							PlanificacionEvento.getInstance().insertarRecuso(recurso);
						}else {
						
						
						}
				}
				
			}
		});
		mntmIngresarRecurso.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		mnAdministrador.add(mntmIngresarRecurso);
		/*contentPane = new JPanel();*/
		//contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		//setContentPane(contentPane);
		/*ImageIcon imageIcon = new ImageIcon(PantallaPrincipal.class.getResource("/Imagenes/fotoRed.png"));
		Image ima= imageIcon.getImage();
		dim = super.getToolkit().getScreenSize();
		ima.getScaledInstance(dim.width-3, dim.height-115, java.awt.Image.SCALE_SMOOTH);
		ImageIcon imageIcon2 = new ImageIcon(ima);*/
		//contentPane.setLayout(new BorderLayout(0, 0));
		
		PanelImagen contentPane = new PanelImagen();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
	}

	@Override
	public void run() {//// esto es para que siempre se mantenga comprobando si el evento esta activo 
		Thread ct = Thread.currentThread();
        while (ct == h1) {
        	for (Evento aux :  PlanificacionEvento.getInstance().getLosEventos()) {
				aux.comprobarfecha();	
			}

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }
		
	}
}
