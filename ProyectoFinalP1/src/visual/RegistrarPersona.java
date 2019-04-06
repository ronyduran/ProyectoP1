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
import java.awt.Font;
import javax.swing.border.EtchedBorder;

public class RegistrarPersona extends JDialog {

	private final JPanel contentPanel = new JPanel();
	//private JTextField txtCedula;
	private JFormattedTextField txtCedula;
	private JTextField txtNombre;
	private JFormattedTextField txtTelefono;
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
		
		setBounds(100, 100, 314, 353);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Informaci\u00F3n General", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(12, 13, 284, 255);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblCedula = new JLabel("C\u00E9dula:");
		lblCedula.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCedula.setBounds(12, 33, 56, 16);
		panel.add(lblCedula);
		
		
		try
		{
		   MaskFormatter mascara = new MaskFormatter("######");
		   txtCedula = new JFormattedTextField(mascara);
		   txtCedula.setFont(new Font("Tahoma", Font.PLAIN, 15));
			txtCedula.setBounds(101, 33, 166, 22);
			panel.add(txtCedula);
		   
		}
		catch (Exception e)
		{
		  
		}
		txtCedula.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNombre.setBounds(12, 62, 77, 16);
		panel.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtNombre.setBounds(101, 62, 166, 22);
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblSexo = new JLabel("Sexo:");
		lblSexo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSexo.setBounds(12, 91, 56, 16);
		panel.add(lblSexo);
		
		JLabel lblTelefono = new JLabel("Tel\u00E9fono:");
		lblTelefono.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTelefono.setBounds(12, 120, 77, 16);
		panel.add(lblTelefono);
		
		
		try
		{
		   MaskFormatter mascara = new MaskFormatter("###-###-####");
		   txtTelefono = new JFormattedTextField(mascara);
		   txtTelefono.setFont(new Font("Tahoma", Font.PLAIN, 15));
		   txtTelefono.setBounds(101, 120, 166, 22);
			panel.add(txtTelefono);
		   
		}
		catch (Exception e)
		{
		  
		}
		txtTelefono.setColumns(10);
		
		JLabel lblDireccin = new JLabel("Direcci\u00F3n:");
		lblDireccin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDireccin.setBounds(12, 149, 62, 16);
		panel.add(lblDireccin);
		
		txtDireccion = new JTextField();
		txtDireccion.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtDireccion.setBounds(101, 149, 166, 22);
		panel.add(txtDireccion);
		txtDireccion.setColumns(10);
		
		JLabel lblGradoAcademico = new JLabel("Grado :");
		lblGradoAcademico.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblGradoAcademico.setBounds(12, 178, 128, 16);
		panel.add(lblGradoAcademico);
		
		rdbtnMasc = new JRadioButton("\u2642");
		rdbtnMasc.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdbtnMasc.setSelected(true);
		sexo = "Hombre";
		rdbtnMasc.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				rdbtnFem.setSelected(false);
				rdbtnMasc.setSelected(true);
				sexo = "Hombre";
			}
		});
		rdbtnMasc.setBounds(123, 90, 39, 25);
		panel.add(rdbtnMasc);
		
		rdbtnFem = new JRadioButton("\u2640");
		rdbtnFem.setFont(new Font("Tahoma", Font.BOLD, 15));
		rdbtnFem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnFem.setSelected(true);
				rdbtnMasc.setSelected(false);
				sexo = "Mujer";
			}
		});
		rdbtnFem.setBounds(199, 90, 39, 25);
		panel.add(rdbtnFem);
		
		JLabel lblArea = new JLabel("Area:");
		lblArea.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		lblArea.setBounds(12, 207, 56, 16);
		panel.add(lblArea);
		
		cbxArea = new JComboBox();
		cbxArea.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cbxArea.setModel(new DefaultComboBoxModel(new String[] {"Seleccione", "Fisica", "Biologia", "Quimica", "Informatica", "Matematica", "Geologia"}));
		cbxArea.setBounds(101, 207, 166, 22);
		panel.add(cbxArea);
		
		cbxGradoAca = new JComboBox();
		cbxGradoAca.setFont(new Font("Tahoma", Font.PLAIN, 15));
		if(elec==1) {
		cbxGradoAca.setModel(new DefaultComboBoxModel(new String[] {"Seleccione", "Bachiller", "Licenciado", "Mag\u00EDster", "Doctorado"}));}
		if(elec==2) {
			cbxGradoAca.setModel(new DefaultComboBoxModel(new String[] {"Seleccione",  "Licenciado", "Mag\u00EDster", "Doctorado"}));
			
		}
		cbxGradoAca.setBounds(101, 178, 166, 22);
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
			buttonPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnRegistar = new JButton("Registrar");
				btnRegistar.setFont(new Font("Tahoma", Font.PLAIN, 15));
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
				btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 15));
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
