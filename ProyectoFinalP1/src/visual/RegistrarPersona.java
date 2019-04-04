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
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;

import logica.Jurado;
import logica.Participante;
import logica.Persona;
import logica.PlanificacionEvento;

import javax.swing.JTextField;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class RegistrarPersona extends JDialog {

	private final JPanel contentPanel = new JPanel();
	//private JTextField txtCedula;
	private JFormattedTextField txtCedula;
	private JTextField txtNombre;
	private JTextField txtTelefono;
	private JTextField txtDireccion;
	private JRadioButton rdbtnMasc;
	private JRadioButton rdbtnFem;
	private String sexo;
	private JPanel panel;
	private JComboBox cbxArea;
	private JComboBox cbxGradoAca;
	/**
	 * Launch the application.
	 */
	
	public RegistrarPersona(int elec) {
		setResizable(false);
		if (elec==1) {
			setTitle("Registrar Participante");
			
			
		}else if (elec==2) {
			setTitle("Registrar Jurado");
			
		}
		
		setBounds(100, 100, 300, 322);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Informaci\u00F3n General", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(12, 13, 260, 227);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblCedula = new JLabel("C\u00E9dula:");
		lblCedula.setBounds(12, 19, 56, 16);
		panel.add(lblCedula);
		
		
		try
		{
		   MaskFormatter mascara = new MaskFormatter("###-###");
		   txtCedula = new JFormattedTextField(mascara);
			txtCedula.setBounds(129, 16, 116, 22);
			panel.add(txtCedula);
		   
		}
		catch (Exception e)
		{
		  
		}
		txtCedula.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(12, 48, 56, 16);
		panel.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(129, 45, 116, 22);
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblSexo = new JLabel("Sexo:");
		lblSexo.setBounds(12, 77, 56, 16);
		panel.add(lblSexo);
		
		JLabel lblTelefono = new JLabel("Tel\u00E9fono:");
		lblTelefono.setBounds(12, 106, 56, 16);
		panel.add(lblTelefono);
		
		txtTelefono = new JTextField();
		txtTelefono.setBounds(129, 103, 116, 22);
		panel.add(txtTelefono);
		txtTelefono.setColumns(10);
		
		JLabel lblDireccin = new JLabel("Direcci\u00F3n:");
		lblDireccin.setBounds(12, 135, 62, 16);
		panel.add(lblDireccin);
		
		txtDireccion = new JTextField();
		txtDireccion.setBounds(129, 132, 116, 22);
		panel.add(txtDireccion);
		txtDireccion.setColumns(10);
		
		JLabel lblGradoAcademico = new JLabel("Grado Acad\u00E9mico:");
		lblGradoAcademico.setBounds(12, 164, 105, 16);
		panel.add(lblGradoAcademico);
		
		rdbtnMasc = new JRadioButton("\u2642");
		rdbtnMasc.setSelected(true);
		sexo = "Hombre";
		rdbtnMasc.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				rdbtnFem.setSelected(false);
				rdbtnMasc.setSelected(true);
				sexo = "Hombre";
			}
		});
		rdbtnMasc.setBounds(129, 73, 39, 25);
		panel.add(rdbtnMasc);
		
		rdbtnFem = new JRadioButton("\u2640");
		rdbtnFem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnFem.setSelected(true);
				rdbtnMasc.setSelected(false);
				sexo = "Mujer";
			}
		});
		rdbtnFem.setBounds(206, 73, 39, 25);
		panel.add(rdbtnFem);
		
		JLabel lblArea = new JLabel("Area:");
		
		lblArea.setBounds(12, 193, 56, 16);
		panel.add(lblArea);
		
		cbxArea = new JComboBox();
		cbxArea.setModel(new DefaultComboBoxModel(new String[] {"Seleccione", "Fisica", "Biologia", "Quimica", "Informatica", "Matematica", "Geologia"}));
		cbxArea.setBounds(129, 190, 116, 22);
		panel.add(cbxArea);
		
		cbxGradoAca = new JComboBox();
		if(elec==1) {
		cbxGradoAca.setModel(new DefaultComboBoxModel(new String[] {"Seleccione", "Bachiller", "Licenciado", "Mag\u00EDster", "Doctorado"}));}
		if(elec==2) {
			cbxGradoAca.setModel(new DefaultComboBoxModel(new String[] {"Seleccione",  "Licenciado", "Mag\u00EDster", "Doctorado"}));
			
		}
		cbxGradoAca.setBounds(129, 161, 116, 22);
		panel.add(cbxGradoAca);
		
		if (elec==1) {
			lblArea.setVisible(false);
			cbxArea.setVisible(false);
		}else if (elec==2) {
			lblArea.setVisible(true);
			cbxArea.setVisible(true);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnRegistar = new JButton("Registrar");
				btnRegistar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Persona aux = null;
						String cedula = txtCedula.getText();
						String nombre = txtNombre.getText();
						String telefono = txtTelefono.getText();
						String direccion = txtDireccion.getText();
						String gradoAcademico = cbxGradoAca.getSelectedItem().toString();
						
						if (elec==1) {
							
							if(!cedula.contentEquals("")&& !nombre.equalsIgnoreCase("")&& !telefono.equalsIgnoreCase("") && cbxGradoAca.getSelectedIndex() !=0) {
							aux = new Participante(cedula, nombre, telefono, direccion, sexo, gradoAcademico);
							if(PlanificacionEvento.getInstance().buscarPersonaPorCedula(cedula)==null) {
							PlanificacionEvento.getInstance().insertarPersona(aux);
							JOptionPane.showMessageDialog(null, "Operación exitosa", "Información", JOptionPane.INFORMATION_MESSAGE);
							dispose();}else {
								
								JOptionPane.showMessageDialog(null, "Este participante ya exixte", "Validación", JOptionPane.WARNING_MESSAGE);
							}
							}else {
								JOptionPane.showMessageDialog(null, "Revise los datos", "Validación", JOptionPane.WARNING_MESSAGE);	
								
							}
						}
						if (elec==2) {
							String area = cbxArea.getSelectedItem().toString();
							if(!cedula.contentEquals("")&& !nombre.equalsIgnoreCase("")&& !telefono.equalsIgnoreCase("") && cbxGradoAca.getSelectedIndex()!=0 &&cbxArea.getSelectedIndex()!=0) {
							aux = new Jurado(cedula, nombre, telefono, direccion, sexo, gradoAcademico, area);
							if(PlanificacionEvento.getInstance().buscarPersonaPorCedula(cedula)==null) {
							PlanificacionEvento.getInstance().insertarPersona(aux);
							JOptionPane.showMessageDialog(null, "Operación exitosa", "Información", JOptionPane.INFORMATION_MESSAGE);
							clean();}else { 
								JOptionPane.showMessageDialog(null, "Este participante ya exixte", "Validación", JOptionPane.WARNING_MESSAGE);
							}
							}else {
								JOptionPane.showMessageDialog(null, "Revise los datos", "Validación", JOptionPane.WARNING_MESSAGE);	
								
							}
						}
						
						
					}
				});
				btnRegistar.setActionCommand("OK");
				buttonPane.add(btnRegistar);
				getRootPane().setDefaultButton(btnRegistar);
			}
			{
				JButton btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
	}
	private void clean() {

		txtCedula.setText("");
		txtDireccion.setText("");
		cbxGradoAca.setSelectedIndex(0);
		txtNombre.setText("");
		txtTelefono.setText("");
		cbxArea.setSelectedIndex(0);
	
		
	}
}
