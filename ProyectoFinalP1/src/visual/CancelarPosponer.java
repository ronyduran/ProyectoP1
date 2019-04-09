package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import logica.Evento;
import logica.PlanificacionEvento;

import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import java.awt.Toolkit;

public class CancelarPosponer extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JPanel panelCancelar;
	private JPanel panelModificar;
	private JDateChooser CalendarioNuevo;
	private JTextArea txtJustificacion;
	private JTextArea txtJustificacionModificar;
	private JComboBox cbxEvento;
	private JTextField txtfechaActual;
	private JRadioButton rdbtnModificar;
	private JRadioButton rdbtnCancelarEvento;
	private JLabel lblEvento;
	private JLabel lblFechaActual;
	private JLabel lblJustificacionDeModificar ;
	private JLabel lblFechaActual_1;
	private JTextField txtFecha;
	private JComboBox cbxEventoModificar;

	
	public CancelarPosponer() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(CancelarPosponer.class.getResource("/Imagenes/Evento.png")));
		setTitle("Carcelar o Modificar Evento");
		setBounds(100, 100, 458, 452);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
		{
			
			JPanel panel = new JPanel();
			panel.setBounds(5, 5, 429, 360);
			contentPanel.add(panel);
			panel.setLayout(null);
			
			JPanel panel_1 = new JPanel();
			panel_1.setBackground(new Color(176, 196, 222));
			panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_1.setBounds(12, 13, 401, 44);
			panel.add(panel_1);
			panel_1.setLayout(null);
			
			JLabel lblOpciones = new JLabel("Opciones:");
			lblOpciones.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblOpciones.setForeground(Color.RED);
			lblOpciones.setBounds(12, 13, 87, 16);
			panel_1.add(lblOpciones);
			
			rdbtnModificar = new JRadioButton("Modificar Evento");
			rdbtnModificar.addActionListener(new ActionListener() {
			
				public void actionPerformed(ActionEvent e) {
			try {
				rdbtnCancelarEvento.setSelected(false);
				rdbtnModificar.setSelected(true);
				panelCancelar.setVisible(false);
				panelModificar.setVisible(true);
			
				//CalendarioNuevo.getCalendarButton().setVisible(true);
			} catch (Exception e2) {
				// TODO: handle exception
			}
					
				}
			});
			rdbtnModificar.setSelected(true);
			rdbtnModificar.setFont(new Font("Tahoma", Font.PLAIN, 15));
			rdbtnModificar.setBounds(93, 9, 150, 25);
			panel_1.add(rdbtnModificar);
			
			rdbtnCancelarEvento = new JRadioButton("Cancelar Evento");
			rdbtnCancelarEvento.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						rdbtnCancelarEvento.setSelected(true);
						rdbtnModificar.setSelected(false);
						panelCancelar.setVisible(true);
						panelModificar.setVisible(false);
						
						//CalendarioNuevo.getCalendarButton().setVisible(false);
					} catch (Exception e2) {
						// TODO: handle exception
					}
						
					
					
				}
			});
			rdbtnCancelarEvento.setFont(new Font("Tahoma", Font.PLAIN, 15));
			rdbtnCancelarEvento.setBounds(252, 9, 141, 25);
			panel_1.add(rdbtnCancelarEvento);
			

		
			panelModificar = new JPanel();
			panelModificar.setBackground(new Color(176, 196, 222));
			panelModificar.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelModificar.setBounds(12, 70, 401, 277);
			panel.add(panelModificar);
			panelModificar.setLayout(null);
			
			JLabel lblEvento_1 = new JLabel("Evento:");
			lblEvento_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblEvento_1.setBounds(27, 28, 73, 16);
			panelModificar.add(lblEvento_1);
			
			
			
			lblFechaActual_1 = new JLabel("Fecha Actual");
			lblFechaActual_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblFechaActual_1.setBounds(27, 67, 105, 22);
			panelModificar.add(lblFechaActual_1);
			
			lblJustificacionDeModificar = new JLabel("Justificacion de la Modificar  la fecha");
			lblJustificacionDeModificar.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblJustificacionDeModificar.setBounds(27, 149, 235, 16);
			panelModificar.add(lblJustificacionDeModificar);
			
			txtJustificacionModificar = new JTextArea();
			txtJustificacionModificar.setFont(new Font("Tahoma", Font.PLAIN, 15));
			txtJustificacionModificar.setBounds(27, 178, 348, 86);
			panelModificar.add(txtJustificacionModificar);
			
			JLabel lblNuevaFecha = new JLabel("Nueva Fecha");
			lblNuevaFecha.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblNuevaFecha.setBounds(27, 108, 105, 16);
			panelModificar.add(lblNuevaFecha);
			
			
			 /*int dia= (new Date().getDay())+3;
			 Date minifecha=new Date(new Date().getYear(), new Date().getMonth(), dia);*/
			 
			 Calendar c = Calendar.getInstance();
			 c.add(Calendar.DATE, +3);

			 
			CalendarioNuevo = new JDateChooser();
		 	CalendarioNuevo.setFont(new Font("Tahoma", Font.PLAIN, 15));
			 CalendarioNuevo.setDateFormatString("dd-MMM-yyyy");
			 JTextFieldDateEditor editor1 = (JTextFieldDateEditor) CalendarioNuevo.getDateEditor();
			 CalendarioNuevo.setBounds(154, 108, 221, 22);
			
			 CalendarioNuevo.setMinSelectableDate(c.getTime());
			
			 panelModificar.add(CalendarioNuevo);
			 
			 txtFecha = new JTextField();
			 txtFecha.setEditable(false);
			 txtFecha.setFont(new Font("Tahoma", Font.PLAIN, 15));
			 txtFecha.setBounds(154, 68, 221, 22);
			 panelModificar.add(txtFecha);
			 txtFecha.setColumns(10);
			
			 
			  cbxEventoModificar = new JComboBox();
			 cbxEventoModificar.addActionListener(new ActionListener() {
			 	public void actionPerformed(ActionEvent e) {
			 		if(cbxEventoModificar.getSelectedIndex()>0) {
			 			SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
			 			String evento= cbxEventoModificar.getSelectedItem().toString();
			 			String[] partes= evento.split("~~");
			 			String codigo=partes[0];
			 			Evento eve=PlanificacionEvento.getInstance().BuscarEventoCodigo(codigo);
			 			txtFecha.setText(formatter.format(eve.getFechaEvento()));
			 			
			 		}
			 		
			 	}
			 });
			 cbxEventoModificar.setModel(new DefaultComboBoxModel(new String[] {"Seleccione"}));
			 loadEvento1();
			 cbxEventoModificar.setFont(new Font("Tahoma", Font.PLAIN, 15));
			 cbxEventoModificar.setBounds(154, 26, 221, 22);
			 panelModificar.add(cbxEventoModificar);
			 
			 if(cbxEventoModificar.getSelectedIndex()>0) {
			 if(CalendarioNuevo.getDate().getTime()==CalendarioNuevo.getMinSelectableDate().getTime()) {
					JOptionPane.showMessageDialog(null, "Seleccione otra fecha", "Validación", JOptionPane.WARNING_MESSAGE);
					CalendarioNuevo.setDate(null);
				 
			 }}
			 
			 
			panelCancelar = new JPanel();
			panelCancelar.setVisible(false);
			panelCancelar.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelCancelar.setBounds(12, 70, 401, 277);
			panel.add(panelCancelar);
			panelCancelar.setLayout(null);

			
			lblEvento = new JLabel("Evento:");
			lblEvento.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblEvento.setBounds(27, 29, 101, 25);
			panelCancelar.add(lblEvento);
			
			cbxEvento = new JComboBox();
			
			cbxEvento.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					if(cbxEvento.getSelectedIndex()>0) {
						SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
						String evento= cbxEvento.getSelectedItem().toString();
				 		String[] partes = evento.split("~~");
						String id = partes[0]; 
						Evento even=PlanificacionEvento.getInstance().BuscarEventoCodigo(id);
						txtfechaActual.setText(formatter.format(even.getFechaEvento()));
					}
				}
			});
			cbxEvento.setFont(new Font("Tahoma", Font.PLAIN, 15));
			cbxEvento.setModel(new DefaultComboBoxModel(new String[] {"Seleccione "}));
			loadEvento();
			cbxEvento.setBounds(140, 29, 235, 25);
			panelCancelar.add(cbxEvento);
			
			JLabel lblFechaActual = new JLabel("Fecha Actual");
			lblFechaActual.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblFechaActual.setBounds(27, 86, 105, 22);
			panelCancelar.add(lblFechaActual);
			
			txtfechaActual = new JTextField();
			txtfechaActual.setFont(new Font("Tahoma", Font.PLAIN, 15));
			txtfechaActual.setEditable(false);
			txtfechaActual.setBounds(144, 86, 231, 22);
			panelCancelar.add(txtfechaActual);
			txtfechaActual.setColumns(10);
			
		
			
			txtJustificacion = new JTextArea();
			txtJustificacion.setFont(new Font("Tahoma", Font.PLAIN, 15));
			txtJustificacion.setBounds(27, 170, 348, 94);
			panelCancelar.add(txtJustificacion);
			
			JLabel lblJustificacionDeCancelacion = new JLabel("Justificacion de Cancelaci\u00F3n del Evento:");
			lblJustificacionDeCancelacion.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblJustificacionDeCancelacion.setBounds(27, 137, 285, 16);
			panelCancelar.add(lblJustificacionDeCancelacion);
			loadEvento();
			editor1.setEditable(false);
			
			
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Realizar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						Date fechaNueva= CalendarioNuevo.getDate();
						String justificacion=txtJustificacion.getText();
						String justificacionModificar=txtJustificacionModificar.getText();
						String evento= cbxEvento.getSelectedItem().toString();
						
				 		String[] partes = evento.split("~~");
						String id = partes[0]; 
						Evento even=PlanificacionEvento.getInstance().BuscarEventoCodigo(id);
						
						String evento1=cbxEventoModificar.getSelectedItem().toString();
						String[] partes1 = evento1.split("~~");
						String id1 = partes1[0]; 
						Evento even1=PlanificacionEvento.getInstance().BuscarEventoCodigo(id1);
						if(even!=null || even1!=null ){
							
							if(rdbtnModificar.isSelected() ) {
								System.out.println("entra1");
								if(!justificacionModificar.equalsIgnoreCase("") && fechaNueva!=null) {
										
										even1.setFechaEvento(fechaNueva);
										even1.setJustificacion(justificacion);
										JOptionPane.showMessageDialog(null, "Se ha pospuesto el evento", "Información", JOptionPane.INFORMATION_MESSAGE);
										dispose();
								}else {JOptionPane.showMessageDialog(null, "Revise los datos", "Validación", JOptionPane.WARNING_MESSAGE); }
							}
							if(rdbtnCancelarEvento.isSelected()) {
								if(!justificacion.equalsIgnoreCase("")) {
										
										even.setJustificacion(justificacion);
										even.setEstado(false);
										JOptionPane.showMessageDialog(null, "El evento se ha calcelado", "Información", JOptionPane.INFORMATION_MESSAGE);
							
							dispose();
								}else {JOptionPane.showMessageDialog(null, "Revise los datos", "Validación", JOptionPane.WARNING_MESSAGE); }}
						}else {
							
							JOptionPane.showMessageDialog(null, "Revise los datos", "Validación", JOptionPane.WARNING_MESSAGE);
						}
					}
				});
				okButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
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
				cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	private void loadEvento() {
    	cbxEvento.removeAllItems();
    	boolean encontrado=false;
    	cbxEvento.addItem(new String("Seleccione"));
	 	for (Evento aux : PlanificacionEvento.getInstance().getLosEventos()) {
	 		if(aux.isEstado()==true) {
    			cbxEvento.addItem((String) aux.getIdentificador()+"~~"+aux.getNombreEvento());
	 	}}

    	cbxEvento.setSelectedItem(0);
    }
	private void loadEvento1() {
    	cbxEventoModificar.removeAllItems();
    	boolean encontrado=false;
    	cbxEventoModificar.addItem(new String("Seleccione"));
	 	for (Evento aux : PlanificacionEvento.getInstance().getLosEventos()) {
	 		if(aux.isEstado()==true) {
    			cbxEventoModificar.addItem((String) aux.getIdentificador()+"~~"+aux.getNombreEvento());
	 	}}

    	cbxEventoModificar.setSelectedItem(0);
    }
}
