package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import logica.PlanificacionEvento;
import logica.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.UIManager;
import java.awt.Toolkit;
import java.awt.Font;

public class regUser extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField usuario;
	private JTextField txtContraseña;
	private JTextField txtConfirmar;
	private JComboBox cbxTipoUsuario;

	
	public regUser() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(regUser.class.getResource("/Imagenes/Formulario.png")));
		setTitle("Registrar Usuario");
		setBounds(100, 100, 372, 237);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(176, 196, 222));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNombreUsuario = new JLabel("Nombre Usuario:");
		lblNombreUsuario.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNombreUsuario.setBounds(20, 26, 127, 14);
		contentPanel.add(lblNombreUsuario);
		
		usuario = new JTextField();
		usuario.setFont(new Font("Tahoma", Font.PLAIN, 15));
		usuario.setBounds(20, 49, 127, 20);
		contentPanel.add(usuario);
		usuario.setColumns(10);
		
		cbxTipoUsuario = new JComboBox();
		cbxTipoUsuario.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cbxTipoUsuario.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "Administrador", "Representante"}));
		cbxTipoUsuario.setBounds(20, 113, 127, 20);
		contentPanel.add(cbxTipoUsuario);
		
		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTipo.setBounds(20, 88, 97, 14);
		contentPanel.add(lblTipo);
		
		txtContraseña = new JTextField();
		txtContraseña.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtContraseña.setBounds(190, 49, 147, 20);
		contentPanel.add(txtContraseña);
		txtContraseña.setColumns(10);
		
		JLabel lblPassword = new JLabel("Contrase\u00F1a");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPassword.setBounds(189, 26, 97, 14);
		contentPanel.add(lblPassword);
		
		JLabel lblConfirmarPassword = new JLabel("Confirmar contrase\u00F1a:");
		lblConfirmarPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblConfirmarPassword.setBounds(189, 88, 167, 14);
		contentPanel.add(lblConfirmarPassword);
		
		txtConfirmar = new JTextField();
		txtConfirmar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtConfirmar.setColumns(10);
		txtConfirmar.setBounds(190, 113, 147, 20);
		contentPanel.add(txtConfirmar);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Registrar");
				okButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(cbxTipoUsuario.getSelectedIndex()>0 &&  !usuario.getText().equalsIgnoreCase("") && !txtContraseña.getText().equalsIgnoreCase("") && !txtConfirmar.getText().equalsIgnoreCase("") ) {
							if(!PlanificacionEvento.getInstance().BuscarUsuarioPorNombre(usuario.getText())) {
							
								if(txtConfirmar.getText().equalsIgnoreCase(txtContraseña.getText())) {	
									User user = new User(cbxTipoUsuario.getSelectedItem().toString(),usuario.getText(),txtContraseña.getText());
									PlanificacionEvento.getInstance().regUser(user);
									JOptionPane.showMessageDialog(null, "Registro Realizado", "Información", JOptionPane.INFORMATION_MESSAGE);
									dispose();

								}else {
									JOptionPane.showMessageDialog(null, "Las Contraseñas no coinciden", "Validación", JOptionPane.WARNING_MESSAGE); 
								}		
							}else {
								JOptionPane.showMessageDialog(null, "Este Usuario ya existe", "Validación", JOptionPane.WARNING_MESSAGE); 

							}
						
						}else {
							JOptionPane.showMessageDialog(null, "Revise los datos", "Validación", JOptionPane.WARNING_MESSAGE); 
					}
						
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
