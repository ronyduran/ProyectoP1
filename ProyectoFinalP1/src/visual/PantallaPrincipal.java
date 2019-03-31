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
		menuBar.add(mnEvento);
		
		JMenu mnTrabajo = new JMenu("Trabajo");
		menuBar.add(mnTrabajo);
		
		JMenu mnJurado = new JMenu("Jurado");
		menuBar.add(mnJurado);
		
		JMenuItem mntmRegistrarJurado = new JMenuItem("Registrar Jurado");
		mntmRegistrarJurado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistrarPersona regP = new RegistrarPersona(2);
				regP.setModal(true);
				regP.setLocationRelativeTo(null);
				regP.setVisible(true);
				
			}
		});
		mnJurado.add(mntmRegistrarJurado);
		
		JMenu mnParticipante = new JMenu("Participante");
		menuBar.add(mnParticipante);
		
		JMenuItem mntmRegistrarParticipante = new JMenuItem("Registrar Participante");
		mntmRegistrarParticipante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistrarPersona regP = new RegistrarPersona(1);
				regP.setModal(true);
				regP.setLocationRelativeTo(null);
				regP.setVisible(true);
			}
		});
		mnParticipante.add(mntmRegistrarParticipante);
		
		JMenu mnComisin = new JMenu("Comisi\u00F3n");
		menuBar.add(mnComisin);
		
		JMenuItem mntmRegistrarComisin = new JMenuItem("Registrar Comisi\u00F3n");
		mntmRegistrarComisin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistrarComision RegC = new RegistrarComision();
				RegC.setModal(true);
				RegC.setLocationRelativeTo(null);
				RegC.setVisible(true);
			}
		});
		mnComisin.add(mntmRegistrarComisin);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

}
