package visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class PantallaPrincipal extends JFrame {

	private JPanel contentPane;
	private Dimension dim;

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
		mnEvento.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		menuBar.add(mnEvento);
		
		JMenuItem mntmInscripcinDeTrabajo = new JMenuItem("Creacion de Evento");
		mntmInscripcinDeTrabajo.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mnEvento.add(mntmInscripcinDeTrabajo);
		
		JMenuItem mntmListadosDeTrabajos = new JMenuItem("Listados de Eventos");
		mntmListadosDeTrabajos.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mnEvento.add(mntmListadosDeTrabajos);
		
		JMenuItem mntmCancelarmodificar = new JMenuItem("Cancelar/Modificar");
		mntmCancelarmodificar.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mnEvento.add(mntmCancelarmodificar);
		
		JMenu mnTrabajo = new JMenu("Trabajo");
		mnTrabajo.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		menuBar.add(mnTrabajo);
		
		JMenuItem mntmRegistrarTrabajo = new JMenuItem("Registrar trabajo");
		mntmRegistrarTrabajo.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mntmRegistrarTrabajo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				RegistrarTrabajo rt = new RegistrarTrabajo();
				rt.setModal(true);
				rt.setVisible(true);
				
			}
		});
		mnTrabajo.add(mntmRegistrarTrabajo);
		
		JMenuItem mntmListadoDeTrabajos = new JMenuItem("Listado de trabajos");
		mntmListadoDeTrabajos.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mnTrabajo.add(mntmListadoDeTrabajos);
		
		JMenu mnJurado = new JMenu("Jurado");
		mnJurado.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		menuBar.add(mnJurado);
		
		JMenuItem mntmRegistrarJurado = new JMenuItem("Registrar Jurado");
		mntmRegistrarJurado.setFont(new Font("Segoe UI", Font.PLAIN, 16));
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
		mntmListadoDeJurados.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mnJurado.add(mntmListadoDeJurados);
		
		JMenu mnComisin = new JMenu("Comisi\u00F3n");
		mnComisin.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		menuBar.add(mnComisin);
		
		JMenuItem mntmRegistrarComisin = new JMenuItem("Registrar Comisi\u00F3n");
		mntmRegistrarComisin.setFont(new Font("Segoe UI", Font.PLAIN, 16));
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
		mntmListadosDeComisiones.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mnComisin.add(mntmListadosDeComisiones);
		
		JMenu mnParticipantes = new JMenu("Participantes");
		mnParticipantes.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		menuBar.add(mnParticipantes);
		
		JMenuItem mntmListadosDeParticipantes = new JMenuItem("Listados de Participantes");
		mntmListadosDeParticipantes.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mnParticipantes.add(mntmListadosDeParticipantes);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

}
