package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.text.AttributedString;

import javax.swing.JComboBox;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import logica.Participante;
import logica.PlanificacionEvento;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JRadioButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Toolkit;

public class ListarParticipante extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JFormattedTextField txtBuscar;
	private JTable tableParticipante;
	public static Object[] fila;
	private static DefaultTableModel model;
	private String identificador = "";
	private JComboBox cbxSexo;
	private JComboBox cbxGrado;

	
	public ListarParticipante() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ListarParticipante.class.getResource("/Imagenes/Trabajo.png")));
		setTitle("Listar Participante");
		setBounds(100, 100, 1000, 540);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		
		
		
		
		{
			JPanel panel = new JPanel();
			panel.setBounds(12, 13, 970, 442);
			contentPanel.add(panel);
			panel.setLayout(null);
			
			JPanel panel_1 = new JPanel();
			panel_1.setBackground(new Color(176, 196, 222));
			panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_1.setBounds(12, 13, 946, 63);
			panel.add(panel_1);
			panel_1.setLayout(null);
			
		
			
			try
			{
			   MaskFormatter mascara = new MaskFormatter("######");
			   txtBuscar = new JFormattedTextField(mascara);
			   txtBuscar.setFont(new Font("Tahoma", Font.PLAIN, 15));
			   txtBuscar.setBounds(180, 15, 124, 25);
			   panel_1.add(txtBuscar);
			   
			}
			catch (Exception e)
			{
			  
			}txtBuscar.setColumns(10);
			
			
			JButton btnBuscar = new JButton("Buscar");
			btnBuscar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String cedula = txtBuscar.getText();
					cbxGrado.setSelectedIndex(0);
					cbxSexo.setSelectedIndex(0);
					if (!cedula.equalsIgnoreCase("")) {
						Participante p1 = (Participante)PlanificacionEvento.getInstance().buscarPersonaPorCedula(cedula);
						if(p1!=null) {
						loadTableParticipanteCedula(p1);}else {
							JOptionPane.showMessageDialog(null, "Este participante no existe", "Validación", JOptionPane.WARNING_MESSAGE);}
					}else {
						JOptionPane.showMessageDialog(null, "Revise los datos", "Validación", JOptionPane.WARNING_MESSAGE);
					}
					
					
				}
			});
			btnBuscar.setFont(new Font("Tahoma", Font.PLAIN, 15));
			btnBuscar.setBounds(316, 15, 94, 25);
			panel_1.add(btnBuscar);
			
			JLabel lblBuscarParticipante = new JLabel("C\u00E9dula del Participante:");
			lblBuscarParticipante.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblBuscarParticipante.setBounds(12, 17, 176, 23);
			panel_1.add(lblBuscarParticipante);
			
			JLabel lblFiltro = new JLabel("Filtros:");
			lblFiltro.setForeground(Color.RED);
			lblFiltro.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblFiltro.setBounds(426, 17, 54, 23);
			panel_1.add(lblFiltro);
			
			cbxSexo = new JComboBox();
			cbxSexo.setFont(new Font("Tahoma", Font.PLAIN, 15));
			cbxSexo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (cbxSexo.getSelectedIndex()>0 && cbxGrado.getSelectedIndex()==0) {
						String sexo=cbxSexo.getSelectedItem().toString();
						loadTableParticipanteFiltro(sexo, "");
					}
					if(cbxSexo.getSelectedIndex()>0 && cbxGrado.getSelectedIndex()>0) {
						
						String sexo=cbxSexo.getSelectedItem().toString();
						String grado= cbxGrado.getSelectedItem().toString();
						loadTableParticipanteFiltro(sexo, grado);
					}
					if(cbxSexo.getSelectedIndex()==0 && cbxGrado.getSelectedIndex()==0) {
						
						loadTableParticipante();
					}
				}
			});
			cbxSexo.setModel(new DefaultComboBoxModel(new String[] {"Seleccione", "Hombre", "Mujer"}));
			cbxSexo.setBounds(547, 15, 116, 25);
			panel_1.add(cbxSexo);
			
			JLabel lblSexo = new JLabel("Sexo");
			lblSexo.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblSexo.setBounds(492, 17, 43, 23);
			panel_1.add(lblSexo);
			
			JLabel lblGradoAcadmico = new JLabel("Grado Acad\u00E9mico");
			lblGradoAcadmico.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblGradoAcadmico.setBounds(690, 16, 116, 23);
			panel_1.add(lblGradoAcadmico);
			
			cbxGrado = new JComboBox();
			cbxGrado.setFont(new Font("Tahoma", Font.PLAIN, 15));
			cbxGrado.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (cbxGrado.getSelectedIndex()>0 && cbxSexo.getSelectedIndex()==0) {
						String grado= cbxGrado.getSelectedItem().toString();
						loadTableParticipanteFiltro("", grado);
					}
					if(cbxGrado.getSelectedIndex()>0 && cbxSexo.getSelectedIndex()>0) {
						
						String sexo=cbxSexo.getSelectedItem().toString();
						String grado= cbxGrado.getSelectedItem().toString();
						loadTableParticipanteFiltro(sexo, grado);
					}if(cbxSexo.getSelectedIndex()==0 && cbxGrado.getSelectedIndex()==0) {
						
						loadTableParticipante();
					}
				}
			});
			cbxGrado.setModel(new DefaultComboBoxModel(new String[] {"Seleccione", "Bachiller", "Licenciado", "Mag\u00EDster", "Doctorado"}));
			cbxGrado.setBounds(818, 15, 116, 25);
			panel_1.add(cbxGrado);
			
			
			
			JPanel panel_2 = new JPanel();
			panel_2.setBounds(12, 89, 946, 340);
			panel.add(panel_2);
			panel_2.setLayout(null);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(0, 0, 946, 340);
			panel_2.add(scrollPane,BorderLayout.CENTER);
			
			String[] header = {"Cédula","Nombre","Sexo","Grado Académico","Teléfono","Dirección"};
			
			Font font = new Font("Tahoma", Font.PLAIN, 17);
			
			model = new DefaultTableModel();
			model.setColumnIdentifiers(header);
			
			tableParticipante = new JTable();
			tableParticipante.setFont(new Font("Tahoma", Font.PLAIN, 14));
			
			scrollPane.setViewportView(tableParticipante);
			tableParticipante.setModel(model);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnLimpiar = new JButton("Limpiar");
				btnLimpiar.setFont(new Font("Tahoma", Font.PLAIN, 15));
				btnLimpiar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						txtBuscar.setText("");
						cbxGrado.setSelectedIndex(0);
						cbxSexo.setSelectedIndex(0);
						loadTableParticipante();
					}
				});
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
		loadTableParticipante();
	}
	
	public void loadTableParticipante() {
		model.setRowCount(0);
		fila = new Object[model.getColumnCount()];
		for (int i = 0; i < PlanificacionEvento.getInstance().getLasPersonas().size(); i++) {
			if (PlanificacionEvento.getInstance().getLasPersonas().get(i) instanceof Participante){
				fila[0] = PlanificacionEvento.getInstance().getLasPersonas().get(i).getCedula();
				fila[1] = PlanificacionEvento.getInstance().getLasPersonas().get(i).getNombre();
				fila[2] = PlanificacionEvento.getInstance().getLasPersonas().get(i).getSexo();
			    fila[3] = PlanificacionEvento.getInstance().getLasPersonas().get(i).getGradoAcademico();
			    fila[4] = PlanificacionEvento.getInstance().getLasPersonas().get(i).getTelefono();
			    fila[5] = PlanificacionEvento.getInstance().getLasPersonas().get(i).getDireccion();
			    model.addRow(fila);
				
			}
		}
	}
	public void loadTableParticipanteCedula(Participante p1) {
		model.setRowCount(0);
		fila = new Object[model.getColumnCount()];
		
		fila[0] = p1.getCedula();
		fila[1] = p1.getNombre();
		fila[2] = p1.getSexo();
	    fila[3] = p1.getGradoAcademico();
	    fila[4] = p1.getTelefono();
	    fila[5] = p1.getDireccion();
	    model.addRow(fila);
			
	}
	public void loadTableParticipanteFiltro(String sexo, String grado) {
		model.setRowCount(0);
		fila = new Object[model.getColumnCount()];

		for (int i = 0; i < PlanificacionEvento.getInstance().getLasPersonas().size(); i++) {
			if (PlanificacionEvento.getInstance().getLasPersonas().get(i) instanceof Participante){
				if(!sexo.equalsIgnoreCase("")&& grado.equalsIgnoreCase("")) {
				if(PlanificacionEvento.getInstance().getLasPersonas().get(i).getSexo().equalsIgnoreCase(sexo)) {
				fila[0] = PlanificacionEvento.getInstance().getLasPersonas().get(i).getCedula();
				fila[1] = PlanificacionEvento.getInstance().getLasPersonas().get(i).getNombre();
				fila[2] = PlanificacionEvento.getInstance().getLasPersonas().get(i).getSexo();
			    fila[3] = PlanificacionEvento.getInstance().getLasPersonas().get(i).getGradoAcademico();
			    fila[4] = PlanificacionEvento.getInstance().getLasPersonas().get(i).getTelefono();
			    fila[5] = PlanificacionEvento.getInstance().getLasPersonas().get(i).getDireccion();
			    model.addRow(fila);
				}}
				
				if(sexo.equalsIgnoreCase("")&& !grado.equalsIgnoreCase("")) {
					if(PlanificacionEvento.getInstance().getLasPersonas().get(i).getGradoAcademico().equalsIgnoreCase(grado)) {
				fila[0] = PlanificacionEvento.getInstance().getLasPersonas().get(i).getCedula();
				fila[1] = PlanificacionEvento.getInstance().getLasPersonas().get(i).getNombre();
				fila[2] = PlanificacionEvento.getInstance().getLasPersonas().get(i).getSexo();
			    fila[3] = PlanificacionEvento.getInstance().getLasPersonas().get(i).getGradoAcademico();
			    fila[4] = PlanificacionEvento.getInstance().getLasPersonas().get(i).getTelefono();
			    fila[5] = PlanificacionEvento.getInstance().getLasPersonas().get(i).getDireccion();
			    model.addRow(fila);
				}}
				
				if(!sexo.equalsIgnoreCase("")&& !grado.equalsIgnoreCase("")) {
					if(PlanificacionEvento.getInstance().getLasPersonas().get(i).getSexo().equalsIgnoreCase(sexo)&&PlanificacionEvento.getInstance().getLasPersonas().get(i).getGradoAcademico().equalsIgnoreCase(grado)) {
				fila[0] = PlanificacionEvento.getInstance().getLasPersonas().get(i).getCedula();
				fila[1] = PlanificacionEvento.getInstance().getLasPersonas().get(i).getNombre();
				fila[2] = PlanificacionEvento.getInstance().getLasPersonas().get(i).getSexo();
			    fila[3] = PlanificacionEvento.getInstance().getLasPersonas().get(i).getGradoAcademico();
			    fila[4] = PlanificacionEvento.getInstance().getLasPersonas().get(i).getTelefono();
			    fila[5] = PlanificacionEvento.getInstance().getLasPersonas().get(i).getDireccion();
			    model.addRow(fila);
				}}
			}
		}
	}
}
