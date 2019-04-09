package visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;


import logica.PlanificacionEvento;
import logica.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;

public class login extends JFrame {

	private JPanel contentPane;
	private JTextField usuario;
	private JPasswordField contraseña;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				FileInputStream planiIn;
				FileOutputStream planiOut;
				ObjectInputStream planiRead;
				ObjectOutputStream planiWrite;
				try {
					planiIn = new FileInputStream ("BDEvento.dat");
					planiRead = new ObjectInputStream(planiIn);
					PlanificacionEvento temp = (PlanificacionEvento)planiRead.readObject();
					PlanificacionEvento.setLaPlanificacion(temp);
				} catch (FileNotFoundException e) {
					try {
						planiOut = new  FileOutputStream("BDEvento.dat");
						planiWrite = new ObjectOutputStream(planiOut);
						User aux = new User("Administrador", "Admin", "123456");
						PlanificacionEvento.getInstance().regUser(aux);
						planiWrite.writeObject(PlanificacionEvento.getInstance());
					} catch (FileNotFoundException e1) {
					} catch (IOException e1) {
						// TODO Auto-generated catch block
					}
				} catch (IOException e) {
					
					
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				try {
					login frame = new login();
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
	public login() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(login.class.getResource("/Imagenes/login.png")));
		setTitle("Login Usuario");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 332, 321);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setLocationRelativeTo(null);

		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(176, 196, 222));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblUsuario.setBounds(48, 97, 72, 19);
		panel.add(lblUsuario);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblContrasea.setBounds(48, 160, 105, 19);
		panel.add(lblContrasea);
		
		usuario = new JTextField();
		usuario.setFont(new Font("Tahoma", Font.PLAIN, 15));
		usuario.setBounds(48, 119, 199, 28);
		panel.add(usuario);
		usuario.setColumns(10);
		
		contraseña = new JPasswordField();
		contraseña.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contraseña.setBounds(48, 184, 199, 28);
		panel.add(contraseña);
		contraseña.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(PlanificacionEvento.getInstance().confirmLogin(usuario.getText(),contraseña.getText())){
					PantallaPrincipal frame = new PantallaPrincipal();
					dispose();
					frame.setVisible(true);
				}else {
					
					JOptionPane.showMessageDialog(null, "El Usurio/Contraseña no coinciden\nVuelva a inventarlo", "Validación", JOptionPane.WARNING_MESSAGE);
				}
				
			}
		});
		btnLogin.setBounds(48, 225, 89, 23);
		panel.add(btnLogin);
		
		JButton btnCacelar = new JButton("Cacelar");
		btnCacelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCacelar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCacelar.setBounds(158, 226, 89, 23);
		panel.add(btnCacelar);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBackground(Color.WHITE);
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setIcon(new ImageIcon(login.class.getResource("/Imagenes/Marca PUCMM (Color) (1).png")));
		lblNewLabel_1.setBounds(48, 13, 206, 61);
		panel.add(lblNewLabel_1);
	}
}
