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
import javax.swing.text.StyledEditorKit.BoldAction;

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
import java.awt.Color;
import java.awt.Toolkit;

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
	private Persona laPersona;
	/**
	 * Launch the application.
	 */
	
	public RegistrarPersona(int elec,String ide, Boolean modificacion) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegistrarPersona.class.getResource("/Imagenes/Formulario.png")));
		setAlwaysOnTop(true);
		setTitle("Registrar Persona");
		setResizable(false);
		
		if (elec==1&& modificacion==false) {
			setTitle("Registrar Participante");
			
			
		}if (elec==2&& modificacion==false) {
			setTitle("Registrar Jurado");
			
		}
		if(elec==1 && modificacion==true) {
			setTitle("Modificación Participante");
			
		}
		if(elec==2 && modificacion==true) {
			setTitle("Modificación Jurado");
			
		}
		
		setBounds(100, 100, 314, 353);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
		
		panel = new JPanel();
		panel.setBackground(new Color(176, 196, 222));
		panel.setBorder(new TitledBorder(null, "Informaci\u00F3n General", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(12, 13, 284, 255);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		laPersona=PlanificacionEvento.getInstance().buscarPersonaPorCedula(ide);
		
		JLabel lblCedula = new JLabel("C\u00E9dula:");
		lblCedula.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCedula.setBounds(12, 33, 56, 16);
		panel.add(lblCedula);
		
		
		try
		{
		   MaskFormatter mascara = new MaskFormatter("###########");
		   txtCedula = new JFormattedTextField(mascara);
		   txtCedula.setFont(new Font("Tahoma", Font.PLAIN, 15));
			txtCedula.setBounds(101, 33, 166, 22);
			if(elec==1 && modificacion==false) {
				txtCedula.setText(ide);
				txtCedula.setEditable(false);
			}
			if(modificacion==true){
				txtCedula.setText(ide);
				if(PlanificacionEvento.getLoginUser().getTipo().equalsIgnoreCase("Representante")) {
				txtCedula.setEditable(false);}
				if(PlanificacionEvento.getLoginUser().getTipo().equalsIgnoreCase("Administrador")) {
					txtCedula.setEditable(true);
				}
			}
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
		if(modificacion==true){
			
			txtNombre.setText(laPersona.getNombre());
			if(PlanificacionEvento.getLoginUser().getTipo().equalsIgnoreCase("Representante")) {
			txtNombre.setEditable(false);}
			if(PlanificacionEvento.getLoginUser().getTipo().equalsIgnoreCase("Administrador")) {
				txtNombre.setEditable(true);
			}
		}
		
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
		   if( modificacion==true){
				txtTelefono.setText(laPersona.getTelefono());
				if(PlanificacionEvento.getLoginUser().getTipo().equalsIgnoreCase("Representante")) {
				txtTelefono.setEditable(true);}
				if(PlanificacionEvento.getLoginUser().getTipo().equalsIgnoreCase("Administrador")) {
					txtTelefono.setEditable(true);
				}
			}

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
		if(modificacion==true){
			txtDireccion.setText(laPersona.getDireccion());
			if(PlanificacionEvento.getLoginUser().getTipo().equalsIgnoreCase("Representante")) {
			txtDireccion.setEditable(true);}
			if(PlanificacionEvento.getLoginUser().getTipo().equalsIgnoreCase("Administrador")) {
				txtDireccion.setEditable(true);
			}
		}
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
		
		
		if( modificacion==true){
			if(laPersona.getSexo().equalsIgnoreCase("Mujer")) {
				rdbtnFem.setSelected(true);
				rdbtnMasc.setSelected(false);
				rdbtnMasc.setEnabled(false);
				
			}if(laPersona.getSexo().equalsIgnoreCase("Hombre")) {
				rdbtnMasc.setSelected(true);
				rdbtnFem.setSelected(false);
				rdbtnFem.setEnabled(false);
				
			}
			}
		
		
		JLabel lblArea = new JLabel("Area:");
		lblArea.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		lblArea.setBounds(12, 207, 56, 16);
		panel.add(lblArea);
		
		cbxArea = new JComboBox();
		cbxArea.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cbxArea.setModel(new DefaultComboBoxModel(new String[] {"Seleccione", "Fisica", "Biologia", "Quimica", "Informatica", "Matematica", "Geologia"}));
		cbxArea.setBounds(101, 207, 166, 22);
		
		if(elec==2 && modificacion==true) {
			ArrayList<String> lasAreas= new ArrayList<>();
			lasAreas.removeAll(lasAreas);
			for (int j = 0; j < cbxArea.getItemCount(); j++) {
			
			lasAreas.add(cbxArea.getItemAt(j).toString());
			}
			for (int i = 0; i < lasAreas.size(); i++) {
				if(lasAreas.get(i).equalsIgnoreCase(((Jurado)laPersona).getArea())) {
					
					cbxArea.setSelectedIndex(i);
				}
			}
			
			if(PlanificacionEvento.getLoginUser().getTipo().equalsIgnoreCase("Representante")) {
				cbxArea.setVisible(false);
				JTextField txtArea=new JTextField();
				txtArea.setFont(new Font("Tahoma", Font.PLAIN, 15));
				txtArea.setBounds(101, 207, 166, 22);
				txtArea.setEditable(false);
				txtArea.setText(((Jurado)laPersona).getArea());
				panel.add(txtArea);
				}
			if(PlanificacionEvento.getLoginUser().getTipo().equalsIgnoreCase("Administrador")) {
				cbxArea.setEnabled(true);
			}
		}
		
			
		
		panel.add(cbxArea);
		
		cbxGradoAca = new JComboBox();
		cbxGradoAca.setFont(new Font("Tahoma", Font.PLAIN, 15));
		if(elec==1) {
		cbxGradoAca.setModel(new DefaultComboBoxModel(new String[] {"Seleccione", "Bachiller", "Licenciado", "Mag\u00EDster", "Doctorado"}));
		if(modificacion==true){
			ArrayList<String> losGrados= new ArrayList<>();
			losGrados.removeAll(losGrados);
			for (int j = 0; j < cbxGradoAca.getItemCount(); j++) {
			
			losGrados.add(cbxGradoAca.getItemAt(j).toString());
			}
			for (int i = 0; i < losGrados.size(); i++) {
				if(losGrados.get(i).equalsIgnoreCase(laPersona.getGradoAcademico())) {
					
					cbxGradoAca.setSelectedIndex(i);
				}
			}
			
			if(PlanificacionEvento.getLoginUser().getTipo().equalsIgnoreCase("Representante")) {
			cbxGradoAca.setVisible(false);
			JTextField txtGrado=new JTextField();
			txtGrado.setFont(new Font("Tahoma", Font.PLAIN, 15));
			txtGrado.setBounds(101, 178, 166, 22);
			txtGrado.setEditable(false);
			txtGrado.setText(laPersona.getGradoAcademico());
			panel.add(txtGrado);
			}
			if(PlanificacionEvento.getLoginUser().getTipo().equalsIgnoreCase("Administrador")) {
				cbxGradoAca.setVisible(true);
			}
		}
		
		
		}
		if(elec==2) {
			cbxGradoAca.setModel(new DefaultComboBoxModel(new String[] {"Seleccione",  "Licenciado", "Mag\u00EDster", "Doctorado"}));
			
			if(modificacion==true){
				ArrayList<String> losGrados= new ArrayList<>();
				losGrados.removeAll(losGrados);
				for (int j = 0; j < cbxGradoAca.getItemCount(); j++) {
				
				losGrados.add(cbxGradoAca.getItemAt(j).toString());
				}
				for (int i = 0; i < losGrados.size(); i++) {
					if(losGrados.get(i).equalsIgnoreCase(laPersona.getGradoAcademico())) {
						
						cbxGradoAca.setSelectedIndex(i);
					}
				}
				
				if(PlanificacionEvento.getLoginUser().getTipo().equalsIgnoreCase("Representante")) {
					cbxGradoAca.setVisible(false);
					JTextField txtGrado=new JTextField();
					txtGrado.setFont(new Font("Tahoma", Font.PLAIN, 15));
					txtGrado.setBounds(101, 178, 166, 22);
					txtGrado.setEditable(false);
					txtGrado.setText(laPersona.getGradoAcademico());
					panel.add(txtGrado);}
				if(PlanificacionEvento.getLoginUser().getTipo().equalsIgnoreCase("Administrador")) {
					lblArea.setVisible(true);
					cbxArea.setVisible(true);
				}
			}
			
			
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
						
						if (elec==1 && modificacion==false) {
							
							if(!cedula.contentEquals("")&& !nombre.equalsIgnoreCase("")&& !telefono.equalsIgnoreCase("") && cbxGradoAca.getSelectedIndex() !=0 && !direccion.equalsIgnoreCase("")) {
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
						if (elec==2&& modificacion==false) {
							String area = cbxArea.getSelectedItem().toString();
							if(!cedula.contentEquals("")&& !nombre.equalsIgnoreCase("")&& !telefono.equalsIgnoreCase("") && cbxGradoAca.getSelectedIndex()!=0 &&cbxArea.getSelectedIndex()!=0&& !direccion.equalsIgnoreCase("")) {
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
						
						if (elec==1 && modificacion==true) {
							
							if(PlanificacionEvento.getLoginUser().getTipo().equalsIgnoreCase("Representante")) {
								if(!telefono.equalsIgnoreCase("") && !direccion.equalsIgnoreCase("") ) {
									laPersona.setDireccion(direccion);
									laPersona.setTelefono(telefono);
									JOptionPane.showMessageDialog(null, "Operación exitosa", "Información", JOptionPane.INFORMATION_MESSAGE);
									dispose();
								}else {
									
									JOptionPane.showMessageDialog(null, "Revise los datos", "Validación", JOptionPane.WARNING_MESSAGE);	

								}
							}
							if(PlanificacionEvento.getLoginUser().getTipo().equalsIgnoreCase("Administrador")) {
								if(!nombre.equalsIgnoreCase("")&& !telefono.equalsIgnoreCase("") && cbxGradoAca.getSelectedIndex() !=0 && !direccion.equalsIgnoreCase("")) {
									laPersona.setNombre(nombre);
									laPersona.setGradoAcademico(gradoAcademico);
									laPersona.setDireccion(direccion);
									laPersona.setTelefono(telefono);
									laPersona.setCedula(cedula);
									
									
									JOptionPane.showMessageDialog(null, "Operación exitosa", "Información", JOptionPane.INFORMATION_MESSAGE);
									dispose();}else {
										JOptionPane.showMessageDialog(null, "Revise los datos", "Validación", JOptionPane.WARNING_MESSAGE);	

													}
								}
							}
						if (elec==2 && modificacion==true) {
							
							if(PlanificacionEvento.getLoginUser().getTipo().equalsIgnoreCase("Representante")) {
								if(!telefono.equalsIgnoreCase("") && !direccion.equalsIgnoreCase("") ) {
									laPersona.setDireccion(direccion);
									laPersona.setTelefono(telefono);
									JOptionPane.showMessageDialog(null, "Operación exitosa", "Información", JOptionPane.INFORMATION_MESSAGE);
									dispose();
								}else {
									
									JOptionPane.showMessageDialog(null, "Revise los datos", "Validación", JOptionPane.WARNING_MESSAGE);	

								}
							}
							if(PlanificacionEvento.getLoginUser().getTipo().equalsIgnoreCase("Administrador")) {
								String area = cbxArea.getSelectedItem().toString();
								if(!area.equalsIgnoreCase("")&&!nombre.equalsIgnoreCase("")&& !telefono.equalsIgnoreCase("") && cbxGradoAca.getSelectedIndex() !=0 && !direccion.equalsIgnoreCase("")) {
									laPersona.setNombre(nombre);
									laPersona.setGradoAcademico(gradoAcademico);
									laPersona.setDireccion(direccion);
									laPersona.setTelefono(telefono);
									laPersona.setCedula(cedula);
									((Jurado)laPersona).setArea(area);
									
									
									JOptionPane.showMessageDialog(null, "Operación exitosa", "Información", JOptionPane.INFORMATION_MESSAGE);
									dispose();}else {
										JOptionPane.showMessageDialog(null, "Revise los datos", "Validación", JOptionPane.WARNING_MESSAGE);	

													}
								}
							}
						
						
						
						
						
						
						}
							
			
				});
				btnRegistar.setActionCommand("OK");
				if(modificacion==true) {
					btnRegistar.setText("Modificar");
				}
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
