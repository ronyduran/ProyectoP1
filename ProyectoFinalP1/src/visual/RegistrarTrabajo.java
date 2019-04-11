package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;

import logica.Comision;
import logica.Evento;
import logica.Participante;
import logica.Persona;
import logica.PlanificacionEvento;
import logica.Trabajo;

import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Toolkit;

public class RegistrarTrabajo extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private JTextField txtNombrePart;
	private JFormattedTextField txtCedula;
	private JTextField txtCodigo;
	private JComboBox cbxArea;
	private JComboBox cbxEvento;
	private JComboBox cbxComision;
	private JTextArea txtDescripcion;
	private JButton okButton;
	
	public RegistrarTrabajo(String codigo, boolean modificar) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegistrarTrabajo.class.getResource("/Imagenes/Trabajo.png")));
		setResizable(false);
		setTitle("Registro de Trabajo");
		setBounds(100, 100, 403, 549);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
		
		{
			JPanel panel = new JPanel();
			panel.setBackground(new Color(176, 196, 222));
			panel.setBounds(5, 5, 385, 120);
			panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Datos Personales", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			contentPanel.add(panel);
			panel.setLayout(null);
			
			JLabel lblIdentificacionDelParticipante = new JLabel("C\u00E9dula");
			lblIdentificacionDelParticipante.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblIdentificacionDelParticipante.setBounds(12, 38, 134, 14);
			panel.add(lblIdentificacionDelParticipante);
			
			
			try
			{
			   MaskFormatter mascara = new MaskFormatter("###########");
			   txtCedula = new JFormattedTextField(mascara);
			   txtCedula.setFont(new Font("Tahoma", Font.PLAIN, 15));
			   txtCedula.setBounds(99, 35, 178, 20);
				panel.add(txtCedula);
			   
			}
			catch (Exception e)
			{
			  
			}
			txtCedula.setColumns(10);
			
			
			
			JButton btnBuscar = new JButton("Buscar");
			btnBuscar.setFont(new Font("Tahoma", Font.PLAIN, 15));
				
			
			btnBuscar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String cedula=txtCedula.getText();
					Boolean encontrado= false;
					if(txtCedula.getValue()!=null) {
					Persona p1=PlanificacionEvento.getInstance().buscarPersonaPorCedula(cedula);
					if(p1!=null) {
						encontrado=true;
					}
	
					
					if(encontrado==true) {
						
						txtNombrePart.setText(p1.getNombre());
						txtCedula.setText(p1.getCedula());
						txtCedula.setEditable(false);
						btnBuscar.setEnabled(false);
						txtNombre.setEditable(true);
						cbxArea.setEnabled(true);
						cbxComision.setEnabled(true);
						cbxEvento.setEnabled(true);
						txtDescripcion.setEditable(true);
						okButton.setEnabled(true);
						
					}else {
					if(JOptionPane.showConfirmDialog(null, "El Usuario no se ha encontrado\n¿Desea crear uno?", "Validación",JOptionPane.YES_NO_OPTION , JOptionPane.WARNING_MESSAGE)==JOptionPane.YES_OPTION) {
					RegistrarPersona regP = new RegistrarPersona(1,cedula,false);
					regP.setModal(true);
					regP.setLocationRelativeTo(null);
					regP.setVisible(true);}}
					Persona p2=PlanificacionEvento.getInstance().buscarPersonaPorCedula(cedula);
					if(p2!=null) {
					
					txtCedula.setText(p2.getCedula());
					txtNombrePart.setText(p2.getNombre());
					txtCedula.setEditable(false);
					btnBuscar.setEnabled(false);
					txtNombre.setEditable(true);
					cbxArea.setEnabled(true);
					cbxComision.setEnabled(true);
					cbxEvento.setEnabled(true);
					txtDescripcion.setEditable(true);
					okButton.setEnabled(true);
					}
					}else {
						
						JOptionPane.showMessageDialog(null, "Ingrese la informacion", "Validación", JOptionPane.WARNING_MESSAGE);
					}
				}
			});
			btnBuscar.setBounds(289, 34, 84, 23);
			panel.add(btnBuscar);
			
			JLabel lblNombreDelParticipante = new JLabel("Participante");
			lblNombreDelParticipante.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblNombreDelParticipante.setBounds(12, 81, 151, 17);
			panel.add(lblNombreDelParticipante);
			
			txtNombrePart = new JTextField();
			txtNombrePart.setFont(new Font("Tahoma", Font.PLAIN, 15));
			txtNombrePart.setEditable(false);
			txtNombrePart.setBounds(99, 78, 274, 20);
			panel.add(txtNombrePart);
			txtNombrePart.setColumns(10);
		}
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(176, 196, 222));
		panel.setBorder(new TitledBorder(null, "Datos del trabajo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(5, 138, 385, 328);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblCodigo = new JLabel("C\u00F3digo");
		lblCodigo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCodigo.setBounds(12, 33, 44, 17);
		panel.add(lblCodigo);
		
		txtCodigo = new JTextField();
		txtCodigo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtCodigo.setEditable(false);
		txtCodigo.setBounds(164, 30, 102, 20);
		panel.add(txtCodigo);
		txtCodigo.setColumns(10);
		txtCodigo.setText("T-"+PlanificacionEvento.getInstance().getCodTrabjo());
		
		JLabel lblNombreDelTrabajo = new JLabel("Nombre del Trabajo");
		lblNombreDelTrabajo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNombreDelTrabajo.setBounds(12, 73, 134, 20);
		panel.add(lblNombreDelTrabajo);
		
		txtNombre = new JTextField();
		txtNombre.setEditable(false);
		txtNombre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtNombre.setBounds(164, 70, 206, 20);
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblAreaDelTrabajo = new JLabel("Area del trabajo");
		lblAreaDelTrabajo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAreaDelTrabajo.setBounds(12, 113, 107, 17);
		panel.add(lblAreaDelTrabajo);
		
		 cbxArea = new JComboBox();
		 cbxArea.setEnabled(false);
		 cbxArea.setFont(new Font("Tahoma", Font.PLAIN, 15));
		 cbxArea.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		if(cbxArea.getSelectedIndex()>0) {
		 		String area= cbxArea.getSelectedItem().toString();
		 		loadEvento(area);
		 		
				
				
		 		}
		 	}
		 });
		cbxArea.setModel(new DefaultComboBoxModel(new String[] {"Seleccione", "Fisica", "Biologia", "Quimica", "Informatica", "Matematica", "Geologia"}));
		cbxArea.setBounds(164, 110, 206, 20);
		panel.add(cbxArea);
		
		JLabel lblEventoAParticipar = new JLabel("Evento a participar");
		lblEventoAParticipar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEventoAParticipar.setBounds(12, 152, 124, 20);
		panel.add(lblEventoAParticipar);
		
		cbxEvento = new JComboBox();
		cbxEvento.setEnabled(false);
		cbxEvento.setFont(new Font("Tahoma", Font.PLAIN, 15));
			
		cbxEvento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(cbxArea.getSelectedIndex()>0 & cbxEvento.getSelectedIndex()>0) {
			 		String area= cbxArea.getSelectedItem().toString();
			 		String evento= cbxEvento.getSelectedItem().toString();
			 		String[] partes = evento.split("~~");
					String id = partes[0]; 
					Evento even=PlanificacionEvento.getInstance().BuscarEventoCodigo(id);
			 		
					loadComisiones(area, even);
					
			 		}
				
			}
		});
		cbxEvento.setModel(new DefaultComboBoxModel(new String[] {"Seleccione"}));
		cbxEvento.setBounds(164, 149, 206, 20);
		panel.add(cbxEvento);
		
		JLabel lblComisionSupervisora = new JLabel("Comisi\u00F3n supervisora");
		lblComisionSupervisora.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblComisionSupervisora.setBounds(12, 191, 140, 17);
		panel.add(lblComisionSupervisora);
		
		cbxComision = new JComboBox();
		cbxComision.setEnabled(false);
		cbxComision.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cbxComision.setModel(new DefaultComboBoxModel(new String[] {"Seleccione"}));
		cbxComision.setBounds(164, 188, 206, 20);
		panel.add(cbxComision);
		
		JLabel lblBreveDescripcin = new JLabel("Breve Descripci\u00F3n");
		lblBreveDescripcin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblBreveDescripcin.setBounds(12, 221, 124, 13);
		panel.add(lblBreveDescripcin);
		
		txtDescripcion = new JTextArea();
		txtDescripcion.setEditable(false);
		txtDescripcion.setFont(new Font("Monospaced", Font.PLAIN, 15));
		txtDescripcion.setBounds(12, 247, 358, 68);
		panel.add(txtDescripcion);
		setLocationRelativeTo(null);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				 okButton = new JButton("Registrar");
				okButton.setEnabled(false);
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					String cedula=txtCedula.getText();
					Persona p1= PlanificacionEvento.getInstance().buscarPersonaPorCedula(cedula);
					String codigo=txtCodigo.getText();
					String nombreTrab=txtNombre.getText();
					String area=cbxArea.getSelectedItem().toString();
					String descripcion=txtDescripcion.getText();
					String comision= cbxComision.getSelectedItem().toString();
					String[]partes = comision.split("~~");
					String codigocomi=partes[0];
					String evento= cbxEvento.getSelectedItem().toString();
					String []partes2=evento.split("~~");
					String codigoEven=partes2[0];
					Comision c1=PlanificacionEvento.getInstance().BuscarComisionPorCodigo(codigocomi);	
					Evento e1= PlanificacionEvento.getInstance().BuscarEventoCodigo(codigoEven);
					
					if(p1!=null && !nombreTrab.equalsIgnoreCase("") && cbxArea.getSelectedIndex()>0 && c1!=null && e1!=null && !descripcion.equalsIgnoreCase("")) {
					
					Trabajo tr1=new Trabajo(nombreTrab, area, descripcion, e1, c1, codigo, p1);
					PlanificacionEvento.getInstance().insertarTrabajo(tr1);
					
					PlanificacionEvento.getInstance().setCodTrabjo(PlanificacionEvento.getInstance().getCodTrabjo()+1);
					JOptionPane.showMessageDialog(null, "Registro Exitoso", "Información", JOptionPane.INFORMATION_MESSAGE);
					clean();
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
	 private void loadEvento(String area) {
	    	
		 
		 	cbxEvento.removeAllItems();
	    	
	    	cbxEvento.addItem(new String("Seleccione"));
		 	for (Evento aux : PlanificacionEvento.getInstance().getLosEventos()) {
		 		boolean encontrado=false;
	    		for (int i = 0; i < aux.getLasComisiones().size(); i++) {
	    			
	    		if(aux.getLasComisiones().get(i).getArea().equalsIgnoreCase(area)) {
	    			encontrado=true;
	    		}
	    		
	    	}
	    		if(encontrado==true) {
	    			if(aux.isEstado()==true) {
	    			cbxEvento.addItem((String) aux.getIdentificador()+"~~"+aux.getNombreEvento());
	    		}}
		 	}
	    			
	    
	    	cbxEvento.setSelectedItem(0);
	    }
	 
	 private void loadComisiones(String area,Evento e) {
	    	cbxComision.removeAllItems();
	    	cbxComision.addItem(new String("Seleccione"));
	    	for (Comision aux : e.getLasComisiones()) {
	    		if(aux.getArea().equalsIgnoreCase(area)) {
	    		cbxComision.addItem((String) aux.getCodigo()+"~~"+aux.getArea());}
			}

	    	cbxComision.setSelectedItem(0);
	    }
	
	private void clean() {
		txtCedula.setText("");
		txtNombrePart.setText("");
		txtDescripcion.setText("");
		txtNombre.setText("");
		txtCodigo.setText("t-"+PlanificacionEvento.getInstance().getCodTrabjo());
		cbxArea.setSelectedIndex(0);
		cbxComision.setSelectedIndex(0);
		cbxEvento.setSelectedIndex(0);
		
	}
}
