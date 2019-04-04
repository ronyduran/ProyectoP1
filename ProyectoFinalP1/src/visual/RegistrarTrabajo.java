package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;


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

public class RegistrarTrabajo extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private JTextField txtNombrePart;
	private JTextField txtCedula;
	private JTextField txtCodigo;
	private JComboBox cbxArea;
	private JComboBox cbxEvento;
	private JComboBox cbxComision;
	private JTextArea txtDescripcion;
	
	public RegistrarTrabajo() {
		setResizable(false);
		setTitle("Registro de Trabajo");
		setBounds(100, 100, 435, 549);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
		
		{
			JPanel panel = new JPanel();
			panel.setBounds(5, 5, 412, 120);
			panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Datos Personales", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			contentPanel.add(panel);
			panel.setLayout(null);
			
			JLabel lblIdentificacionDelParticipante = new JLabel("Cedula");
			lblIdentificacionDelParticipante.setBounds(12, 38, 134, 14);
			panel.add(lblIdentificacionDelParticipante);
			
			txtCedula = new JTextField();
			txtCedula.setBounds(124, 35, 182, 20);
			panel.add(txtCedula);
			txtCedula.setColumns(10);
			
			JButton btnBuscar = new JButton("Buscar");
				
			
			btnBuscar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String cedula=txtCedula.getText();
					Boolean encontrado= false;
					if(!cedula.equalsIgnoreCase("")) {
					Persona p1=PlanificacionEvento.getInstance().buscarPersonaPorCedula(cedula);
					if(p1!=null) {
						encontrado=true;
					}
	
					if(encontrado==true) {
						
						txtNombrePart.setText(p1.getNombre());
						txtCedula.setText(p1.getCedula());
						
					}else {
					if(JOptionPane.showConfirmDialog(null, "El Usuario no se ha encontrado\n¿Desea crear uno?", "Validación",JOptionPane.YES_NO_OPTION , JOptionPane.WARNING_MESSAGE)==JOptionPane.YES_OPTION) {
					RegistrarPersona regP = new RegistrarPersona(1);
					regP.setModal(true);
					regP.setLocationRelativeTo(null);
					regP.setVisible(true);}}
					Persona p2=PlanificacionEvento.getInstance().buscarPersonaPorCedula(cedula);
					if(p2!=null) {
					
					txtCedula.setText(p2.getCedula());
					txtNombrePart.setText(p2.getNombre());
					
					}
					}else {
						
						JOptionPane.showMessageDialog(null, "Ingrese la informacion", "Validación", JOptionPane.WARNING_MESSAGE);
					}
				}
			});
			btnBuscar.setBounds(318, 34, 77, 23);
			panel.add(btnBuscar);
			
			JLabel lblNombreDelParticipante = new JLabel("Nombre del Participante");
			lblNombreDelParticipante.setBounds(12, 81, 151, 14);
			panel.add(lblNombreDelParticipante);
			
			txtNombrePart = new JTextField();
			txtNombrePart.setEditable(false);
			txtNombrePart.setBounds(156, 78, 182, 20);
			panel.add(txtNombrePart);
			txtNombrePart.setColumns(10);
		}
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Datos del trabajo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(5, 138, 412, 328);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblCodigo = new JLabel("Codigo");
		lblCodigo.setBounds(12, 33, 44, 14);
		panel.add(lblCodigo);
		
		txtCodigo = new JTextField();
		txtCodigo.setEditable(false);
		txtCodigo.setBounds(148, 30, 108, 20);
		panel.add(txtCodigo);
		txtCodigo.setColumns(10);
		txtCodigo.setText("t-"+PlanificacionEvento.getInstance().getCodTrabjo());
		
		JLabel lblNombreDelTrabajo = new JLabel("Nombre del Trabajo");
		lblNombreDelTrabajo.setBounds(12, 73, 124, 14);
		panel.add(lblNombreDelTrabajo);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(148, 70, 222, 20);
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblAreaDelTrabajo = new JLabel("Area del trabajo");
		lblAreaDelTrabajo.setBounds(12, 113, 107, 14);
		panel.add(lblAreaDelTrabajo);
		
		 cbxArea = new JComboBox();
		 cbxArea.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		if(cbxArea.getSelectedIndex()>0) {
		 		String area= cbxArea.getSelectedItem().toString();
		 		loadEvento(area);
		 		
				
				
		 		}
		 	}
		 });
		cbxArea.setModel(new DefaultComboBoxModel(new String[] {"Seleccione", "Fisica", "Biologia", "Quimica", "Informatica", "Matematica", "Geologia"}));
		cbxArea.setBounds(148, 110, 222, 20);
		panel.add(cbxArea);
		
		JLabel lblEventoAParticipar = new JLabel("Evento a participar");
		lblEventoAParticipar.setBounds(12, 152, 107, 14);
		panel.add(lblEventoAParticipar);
		
		cbxEvento = new JComboBox();
			
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
		cbxEvento.setBounds(148, 149, 222, 20);
		panel.add(cbxEvento);
		
		JLabel lblComisionSupervisora = new JLabel("Comision supervisora");
		lblComisionSupervisora.setBounds(12, 191, 124, 14);
		panel.add(lblComisionSupervisora);
		
		cbxComision = new JComboBox();
		cbxComision.setModel(new DefaultComboBoxModel(new String[] {"Seleccione"}));
		cbxComision.setBounds(148, 188, 222, 20);
		panel.add(cbxComision);
		
		JLabel lblBreveDescripcin = new JLabel("Breve Descripci\u00F3n");
		lblBreveDescripcin.setBounds(12, 218, 107, 16);
		panel.add(lblBreveDescripcin);
		
		txtDescripcion = new JTextArea();
		txtDescripcion.setBounds(12, 247, 358, 68);
		panel.add(txtDescripcion);
		setLocationRelativeTo(null);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Registrar");
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
	    	boolean encontrado=false;
	    	cbxEvento.addItem(new String("Seleccione"));
		 	for (Evento aux : PlanificacionEvento.getInstance().getLosEventos()) {
	    		for (int i = 0; i < PlanificacionEvento.getInstance().getLasComisiones().size(); i++) {
	    		if(aux.getLasComisiones().get(i).getArea().equalsIgnoreCase(area)) {}
	    		encontrado=true;
	    	}
	    		if(encontrado==true) {
	    			cbxEvento.addItem((String) aux.getIdentificador()+"~~"+aux.getNombreEvento());
	    		}
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
