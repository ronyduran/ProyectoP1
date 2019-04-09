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
import javax.swing.border.EmptyBorder;

import logica.Control;
import logica.User;

import javax.swing.JLabel;
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
	private JTextField textField;
	private JTextField textField_1;

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
					planiIn = new FileInputStream ("BDusuarios.dat");
					planiRead = new ObjectInputStream(planiIn);
					Control temp = (Control)planiRead.readObject();
					Control.setControl(temp);
				} catch (FileNotFoundException e) {
					try {
						planiOut = new  FileOutputStream("BDusuarios.dat");
						planiWrite = new ObjectOutputStream(planiOut);
						User aux = new User("Administrador", "Admin", "123456");
						Control.getInstance().regUser(aux);
						planiWrite.writeObject(Control.getInstance());
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
		setIconImage(Toolkit.getDefaultToolkit().getImage(login.class.getResource("/Imagenes/login.png")));
		setTitle("Login Sistema Planificaci\u00F3n ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 332, 321);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(176, 196, 222));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUsuario.setBounds(56, 98, 72, 19);
		panel.add(lblUsuario);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblContrasea.setBounds(56, 162, 105, 19);
		panel.add(lblContrasea);
		
		textField = new JTextField();
		textField.setBounds(56, 128, 191, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(56, 192, 191, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Control.getInstance().confirmLogin(textField.getText(),textField_1.getText())){
					PantallaPrincipal frame = new PantallaPrincipal();
					dispose();
					frame.setVisible(true);
				};
				
			}
		});
		btnLogin.setBounds(56, 237, 89, 23);
		panel.add(btnLogin);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(login.class.getResource("/Imagenes/PUCMM.png")));
		lblNewLabel.setBounds(66, 25, 174, 61);
		panel.add(lblNewLabel);
		
		JButton btnCacelar = new JButton("Cacelar");
		btnCacelar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnCacelar.setBounds(158, 238, 89, 23);
		panel.add(btnCacelar);
	}
}
