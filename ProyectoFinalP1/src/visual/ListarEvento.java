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
import logica.Evento;
import logica.Participante;
import logica.PlanificacionEvento;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class ListarEvento extends JDialog {

	private final JPanel contentPanel = new JPanel();
	public static Object[] fila;
	private static DefaultTableModel model;
	private String identificador = "";
	private JTable tableEventos;
	private JTextField txtBuscar;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		try {
			ListarEvento dialog = new ListarEvento();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
	 */
	public ListarEvento() {
		setTitle("Listar Eventos");
		setResizable(false);
		setBounds(100, 100, 1000, 500);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBounds(12, 13, 958, 404);
			contentPanel.add(panel);
			panel.setLayout(null);
			{
				JPanel panel_1 = new JPanel();
				panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				panel_1.setBounds(12, 13, 934, 58);
				panel.add(panel_1);
				panel_1.setLayout(null);
				{
					JLabel lblCdigo = new JLabel("C\u00F3digo del Evento:");
					lblCdigo.setFont(new Font("Tahoma", Font.PLAIN, 15));
					lblCdigo.setBounds(12, 13, 126, 16);
					panel_1.add(lblCdigo);
				}
				{
					txtBuscar = new JTextField();
					txtBuscar.setFont(new Font("Tahoma", Font.PLAIN, 15));
					txtBuscar.setBounds(150, 9, 116, 25);
					panel_1.add(txtBuscar);
					txtBuscar.setColumns(10);
				}
				{
					JButton btnBuscar = new JButton("Buscar");
					btnBuscar.setFont(new Font("Tahoma", Font.PLAIN, 15));
					btnBuscar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							String codigo = txtBuscar.getText();
							
							if (!codigo.equalsIgnoreCase("")) {
								Evento e1 = PlanificacionEvento.getInstance().BuscarEventoCodigo(codigo);
								if(e1!=null) {
								loadTableEventoByCodigo(e1);}else {
									JOptionPane.showMessageDialog(null, "Este evento no existe", "Validación", JOptionPane.WARNING_MESSAGE);}
							}else {
								JOptionPane.showMessageDialog(null, "Revise los datos", "Validación", JOptionPane.WARNING_MESSAGE);
							}
							
							
						}
					});
					btnBuscar.setBounds(278, 9, 97, 25);
					panel_1.add(btnBuscar);
				}
				{
					JLabel lblFilto = new JLabel("Filtros:");
					lblFilto.setFont(new Font("Tahoma", Font.PLAIN, 15));
					lblFilto.setBounds(387, 13, 43, 16);
					panel_1.add(lblFilto);
				}
				{
					JLabel lblTipo = new JLabel("Tipo");
					lblTipo.setFont(new Font("Tahoma", Font.PLAIN, 15));
					lblTipo.setBounds(442, 13, 43, 16);
					panel_1.add(lblTipo);
				}
				{
					JComboBox cbxTipo = new JComboBox();
					cbxTipo.setModel(new DefaultComboBoxModel(new String[] {"Seleccione", "Congreso", "Jornada", "Mesa Redonda"}));
					cbxTipo.setFont(new Font("Tahoma", Font.PLAIN, 15));
					cbxTipo.setBounds(497, 9, 116, 25);
					panel_1.add(cbxTipo);
				}
				{
					JLabel lblEstado = new JLabel("Estado");
					lblEstado.setFont(new Font("Tahoma", Font.PLAIN, 15));
					lblEstado.setBounds(625, 13, 53, 16);
					panel_1.add(lblEstado);
				}
				{
					JComboBox cbxEstado = new JComboBox();
					cbxEstado.setModel(new DefaultComboBoxModel(new String[] {"Seleccione", "Activo", "No activo "}));
					cbxEstado.setFont(new Font("Tahoma", Font.PLAIN, 15));
					cbxEstado.setBounds(690, 9, 116, 25);
					panel_1.add(cbxEstado);
				}
			}
			{
				JPanel panel_2 = new JPanel();
				panel_2.setBounds(12, 84, 934, 307);
				panel.add(panel_2);
				panel_2.setLayout(null);
				{
					JScrollPane scrollPane = new JScrollPane();
					scrollPane.setBounds(0, 0, 934, 307);
					panel_2.add(scrollPane, BorderLayout.CENTER);
					{
						String[] header = {"Código","Nombre","Tipo","Asistencia","Fecha","Estado"};		
						model = new DefaultTableModel();
						model.setColumnIdentifiers(header);
						
						tableEventos = new JTable();
						tableEventos.setFont(new Font("Tahoma", Font.PLAIN, 14));
						
						scrollPane.setViewportView(tableEventos);
						tableEventos.setModel(model);
					}
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnLimpiar = new JButton("Limpiar");
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
		loadTableEvento();
	}
	
	public void loadTableEvento() {
		model.setRowCount(0);
		fila = new Object[model.getColumnCount()];
		for (int i = 0; i < PlanificacionEvento.getInstance().getLosEventos().size(); i++) {
			
				fila[0] = PlanificacionEvento.getInstance().getLosEventos().get(i).getIdentificador();
				fila[1] = PlanificacionEvento.getInstance().getLosEventos().get(i).getNombreEvento();
				fila[2] = PlanificacionEvento.getInstance().getLosEventos().get(i).getTipoEvento();
			    fila[3] = PlanificacionEvento.getInstance().getLosEventos().get(i).getCantAsistentes();
			    fila[4] = PlanificacionEvento.getInstance().getLosEventos().get(i).getFechaEvento();
			    fila[5] = PlanificacionEvento.getInstance().getLosEventos().get(i).isEstado();
			    
			    model.addRow(fila);
				
			
		}
	}
	public void loadTableEventoByCodigo(Evento e1) {
		model.setRowCount(0);
		fila = new Object[model.getColumnCount()];
		
		fila[0] = e1.getIdentificador();
		fila[1] = e1.getNombreEvento();
		fila[2] = e1.getTipoEvento();
	    fila[3] = e1.getCantAsistentes();
	    fila[4] = e1.getFechaEvento();
	    fila[5] = e1.isEstado();
	    model.addRow(fila);
			
	}

}
