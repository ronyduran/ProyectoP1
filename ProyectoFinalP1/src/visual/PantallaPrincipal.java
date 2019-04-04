package visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logica.Evento;
import logica.PlanificacionEvento;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class PantallaPrincipal extends JFrame implements Runnable  {

	private JPanel contentPane;
	private Dimension dim;
	Thread h1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
		setTitle("Sistema de Planificaci\u00F3n de Eventos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 611, 437);
		setResizable(false);
		dim = super.getToolkit().getScreenSize();
		super.setSize(dim.width, (dim.height-50));
		setLocationRelativeTo(null);
		
		JMenuBar menuBar = new JMenuBar();
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
		
		JMenu mnTrabajo = new JMenu("Trabajo");
		mnTrabajo.setFont(new Font("Segoe UI", Font.BOLD, 17));
		menuBar.add(mnTrabajo);
		
		JMenuItem mntmRegistrarTrabajo = new JMenuItem("Registrar trabajo");
		mntmRegistrarTrabajo.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		mntmRegistrarTrabajo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				RegistrarTrabajo rt = new RegistrarTrabajo();
				rt.setModal(true);
				rt.setVisible(true);
				
			}
		});
		mnTrabajo.add(mntmRegistrarTrabajo);
		
		JMenuItem mntmListadoDeTrabajos = new JMenuItem("Listado de trabajos");
		mntmListadoDeTrabajos.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		mnTrabajo.add(mntmListadoDeTrabajos);
		
		JMenu mnJurado = new JMenu("Jurado");
		mnJurado.setFont(new Font("Segoe UI", Font.BOLD, 17));
		menuBar.add(mnJurado);
		
		JMenuItem mntmRegistrarJurado = new JMenuItem("Registrar Jurado");
		mntmRegistrarJurado.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		mntmRegistrarJurado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistrarPersona regP = new RegistrarPersona(2);
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
		contentPane = new JPanel();
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
