package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import logica.Evento;
import logica.PlanificacionEvento;
import logica.Trabajo;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.text.SimpleDateFormat;

import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Toolkit;

public class ListarTrabajo extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCodigo;
	private JTable tableTrabajos;
	public static Object[] fila;
	private static DefaultTableModel model;
	private String identificador = "";

	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	public ListarTrabajo() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ListarTrabajo.class.getResource("/Imagenes/Trabajo.png")));
		setTitle("Listar Trabajos");
		setResizable(false);
		setBounds(100, 100, 1000, 500);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 13, 970, 404);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(176, 196, 222));
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(12, 13, 946, 60);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblCdigo = new JLabel("C\u00F3digo:");
		lblCdigo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCdigo.setBounds(14, 11, 56, 25);
		panel_1.add(lblCdigo);
		
		txtCodigo = new JTextField();
		txtCodigo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtCodigo.setBounds(80, 11, 116, 25);
		panel_1.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String codigo = txtCodigo.getText();
				
				if (!codigo.equalsIgnoreCase("")) {
					Trabajo t1 = PlanificacionEvento.getInstance().BuscarTrabajoPorCodigo(codigo);
					if(t1!=null) {
					loadTableTrabajoByCodigo(t1);}else {
						JOptionPane.showMessageDialog(null, "Este evento no existe", "Validación", JOptionPane.WARNING_MESSAGE);}
				}else {
					JOptionPane.showMessageDialog(null, "Revise los datos", "Validación", JOptionPane.WARNING_MESSAGE);
				}
				
			}
		});
		btnBuscar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnBuscar.setBounds(208, 11, 97, 25);
		panel_1.add(btnBuscar);
		
		JLabel lblFiltro = new JLabel("Filtros:");
		lblFiltro.setForeground(Color.RED);
		lblFiltro.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblFiltro.setBounds(369, 15, 63, 16);
		panel_1.add(lblFiltro);
		
		JLabel lblArea = new JLabel("\u00C1rea");
		lblArea.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblArea.setBounds(444, 15, 43, 16);
		panel_1.add(lblArea);
		
		JComboBox cbxArea = new JComboBox();
		cbxArea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cbxArea.getSelectedIndex() > 0) {
					String area = cbxArea.getSelectedItem().toString();
					loadTableTrabajoFiltro(area);
				}
				if (cbxArea.getSelectedIndex()==0) {
					loadTableTrabajo();
				}
			}
		});
		cbxArea.setModel(new DefaultComboBoxModel(new String[] {"Seleccione", "Fisica", "Biologia", "Quimica", "Informatica", "Matematica", "Geologia"}));
		cbxArea.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cbxArea.setBounds(499, 11, 116, 25);
		panel_1.add(cbxArea);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(12, 86, 946, 305);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 946, 305);
		panel_2.add(scrollPane,BorderLayout.CENTER);
		
		String[] header = {"Código","Nombre","Área","Participante","Comisión","Evento"};
		model = new DefaultTableModel();
		model.setColumnIdentifiers(header);
		
		tableTrabajos = new JTable();
		tableTrabajos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane.setViewportView(tableTrabajos);
		tableTrabajos.setModel(model);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnLimpiar = new JButton("Limpiar");
				btnLimpiar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						cbxArea.setSelectedIndex(0);
						txtCodigo.setText("");
						loadTableTrabajo();
					}
				});
				btnLimpiar.setActionCommand("OK");
				buttonPane.add(btnLimpiar);
				getRootPane().setDefaultButton(btnLimpiar);
			}
			{
				JButton btnCerrar = new JButton("Cerrar");
				btnCerrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCerrar.setActionCommand("Cancel");
				buttonPane.add(btnCerrar);
			}
		}
		loadTableTrabajo();
	}
	public void loadTableTrabajo() {
		model.setRowCount(0);
		
		fila = new Object[model.getColumnCount()];
		for (int i = 0; i < PlanificacionEvento.getInstance().getLosTrabajos().size(); i++) {
			fila[0] = PlanificacionEvento.getInstance().getLosTrabajos().get(i).getIdentificador();
			fila[1] = PlanificacionEvento.getInstance().getLosTrabajos().get(i).getNombreTrabajo();
			fila[2] = PlanificacionEvento.getInstance().getLosTrabajos().get(i).getAreaTrabajo();
			fila[3] = PlanificacionEvento.getInstance().getLosTrabajos().get(i).getElParticipante().getCedula();
			fila[4] = PlanificacionEvento.getInstance().getLosTrabajos().get(i).getLaComision().getCodigo();
			fila[5] = PlanificacionEvento.getInstance().getLosTrabajos().get(i).getElEvento().getIdentificador();
			
		    
		    
		    
			
			model.addRow(fila);	
				
			
		}
	}
	public void loadTableTrabajoByCodigo(Trabajo t1) {
	model.setRowCount(0);
		
		fila = new Object[model.getColumnCount()];
		
		fila[0] = t1.getIdentificador();
		fila[1] = t1.getNombreTrabajo();
		fila[2] = t1.getAreaTrabajo();
		fila[3] = t1.getElParticipante().getCedula();
		fila[4] = t1.getLaComision().getCodigo();
		fila[5] = t1.getElEvento().getIdentificador();
		    
		    
		    
			
			model.addRow(fila);	
				
			
		
	}
	public void loadTableTrabajoFiltro(String area) {
		model.setRowCount(0);
		
		fila = new Object[model.getColumnCount()];
		for (int i = 0; i < PlanificacionEvento.getInstance().getLosTrabajos().size(); i++) {
			
				if (PlanificacionEvento.getInstance().getLosTrabajos().get(i).getAreaTrabajo().equalsIgnoreCase(area)) {
					fila[0] = PlanificacionEvento.getInstance().getLosTrabajos().get(i).getIdentificador();
					fila[1] = PlanificacionEvento.getInstance().getLosTrabajos().get(i).getNombreTrabajo();
					fila[2] = PlanificacionEvento.getInstance().getLosTrabajos().get(i).getAreaTrabajo();
					fila[3] = PlanificacionEvento.getInstance().getLosTrabajos().get(i).getElParticipante().getCedula();
					fila[4] = PlanificacionEvento.getInstance().getLosTrabajos().get(i).getLaComision().getCodigo();
					fila[5] = PlanificacionEvento.getInstance().getLosTrabajos().get(i).getElEvento().getIdentificador();
					model.addRow(fila);	
				}
			
			
			
		}
	}
}
