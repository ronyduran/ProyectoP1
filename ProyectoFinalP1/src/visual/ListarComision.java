package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import logica.Comision;
import logica.Participante;
import logica.PlanificacionEvento;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.EtchedBorder;
import java.awt.Color;

public class ListarComision extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCodigo;
	private JTable tableComisiones;
	public static Object[] fila;
	private static DefaultTableModel model;
	private String identificador = "";
	private JComboBox cbxArea;

	
	public ListarComision() {
		setTitle("Listar Comisiones");
		setResizable(false);
		setBounds(100, 100, 1000, 500);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(12, 13, 970, 404);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(12, 13, 946, 52);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblCdigo = new JLabel("C\u00F3digo de la Comisi\u00F3n:");
		lblCdigo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCdigo.setBounds(12, 13, 154, 22);
		panel_1.add(lblCdigo);
		
		txtCodigo = new JTextField();
		txtCodigo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtCodigo.setBounds(178, 10, 116, 25);
		panel_1.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String codigo = txtCodigo.getText();
				
				if (!codigo.equalsIgnoreCase("")) {
					Comision c1 = PlanificacionEvento.getInstance().BuscarComisionPorCodigo(codigo);
					if(c1!=null) {
					loadTableComisionByCodigo(c1);;}else {
						JOptionPane.showMessageDialog(null, "Este comisión no existe", "Validación", JOptionPane.WARNING_MESSAGE);}
				}else {
					JOptionPane.showMessageDialog(null, "Revise los datos", "Validación", JOptionPane.WARNING_MESSAGE);
				}
				
				
			}
		});
		btnBuscar.setBounds(306, 9, 97, 25);
		panel_1.add(btnBuscar);
		
		JLabel label = new JLabel("Filtros:");
		label.setForeground(Color.RED);
		label.setFont(new Font("Tahoma", Font.BOLD, 15));
		label.setBounds(458, 14, 63, 16);
		panel_1.add(label);
		
		JLabel lblTipo = new JLabel("\u00C1rea");
		lblTipo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTipo.setBounds(533, 14, 43, 16);
		panel_1.add(lblTipo);
		
		cbxArea = new JComboBox();
		cbxArea.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cbxArea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cbxArea.getSelectedIndex() > 0) {
					String area = cbxArea.getSelectedItem().toString();
					loadTableComisionFiltro(area);
				}
				if (cbxArea.getSelectedIndex()==0) {
					loadTableComisiones();
				}
			}
		});
		cbxArea.setModel(new DefaultComboBoxModel(new String[] {"Seleccione", "Fisica", "Biologia", "Quimica", "Informatica", "Matematica", "Geologia"}));
		cbxArea.setBounds(588, 10, 116, 25);
		panel_1.add(cbxArea);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(12, 78, 946, 313);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 946, 313);
		panel_2.add(scrollPane,BorderLayout.CENTER);
		
		String[] header = {"Código","Área","Presidente","Evento","Fecha de Creación"};		
		model = new DefaultTableModel();
		model.setColumnIdentifiers(header);
		
		tableComisiones = new JTable();
		tableComisiones.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		scrollPane.setViewportView(tableComisiones);
		tableComisiones.setModel(model);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnLimpiar = new JButton("Limpiar");
				btnLimpiar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						cbxArea.setSelectedIndex(0);
						txtCodigo.setText("");
						loadTableComisiones();
					}
				});
				btnLimpiar.setFont(new Font("Tahoma", Font.PLAIN, 15));
				btnLimpiar.setActionCommand("OK");
				buttonPane.add(btnLimpiar);
				getRootPane().setDefaultButton(btnLimpiar);
			}
			{
				JButton btnCerrar = new JButton("Cerrar");
				btnCerrar.setFont(new Font("Tahoma", Font.PLAIN, 15));
				btnCerrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCerrar.setActionCommand("Cancel");
				buttonPane.add(btnCerrar);
			}
		}
		loadTableComisiones();
	}
	
	public void loadTableComisiones() {
		model.setRowCount(0);
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
		fila = new Object[model.getColumnCount()];
		for (int i = 0; i < PlanificacionEvento.getInstance().getLasComisiones().size(); i++) {
			
				fila[0] = PlanificacionEvento.getInstance().getLasComisiones().get(i).getCodigo();
				fila[1] = PlanificacionEvento.getInstance().getLasComisiones().get(i).getArea();
				fila[2] = PlanificacionEvento.getInstance().getLasComisiones().get(i).getPresidente().getNombre();
			    fila[3] = PlanificacionEvento.getInstance().getLasComisiones().get(i).getElEvento().getNombreEvento();
			    fila[4] = formatter.format(PlanificacionEvento.getInstance().getLasComisiones().get(i).getFechaCreacion());
			    model.addRow(fila);
				
			
		}
	}
	
	public void loadTableComisionByCodigo(Comision c1) {
		model.setRowCount(0);
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
		fila = new Object[model.getColumnCount()];
		
		fila[0] = c1.getCodigo();
		fila[1] = c1.getArea();
		fila[2] = c1.getPresidente().getNombre();
	    fila[3] = c1.getElEvento().getNombreEvento();
	    fila[4] = formatter.format(c1.getFechaCreacion());
	    model.addRow(fila);
			
	}
	public void loadTableComisionFiltro(String area) {
		model.setRowCount(0);
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
		fila = new Object[model.getColumnCount()];
		for (int i = 0; i < PlanificacionEvento.getInstance().getLasComisiones().size(); i++) {
			
				if (PlanificacionEvento.getInstance().getLasComisiones().get(i).getArea().equalsIgnoreCase(area)) {
					fila[0] = PlanificacionEvento.getInstance().getLasComisiones().get(i).getCodigo();
					fila[1] = PlanificacionEvento.getInstance().getLasComisiones().get(i).getArea();
					fila[2] = PlanificacionEvento.getInstance().getLasComisiones().get(i).getPresidente().getNombre();
				    fila[3] = PlanificacionEvento.getInstance().getLasComisiones().get(i).getElEvento().getNombreEvento();
				    fila[4] = formatter.format(PlanificacionEvento.getInstance().getLasComisiones().get(i).getFechaCreacion());
				    model.addRow(fila);
				}
			
			
			
		}
	}
}
