package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
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
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;

public class CancelarPosponer extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JDateChooser CalendarioNuevo;
	private JTextArea txtJustificacion;
	private JComboBox cbxEvento;
	private JTextField txtfechaActual;
	
	public CancelarPosponer() {
		setTitle("Carcelar o Posponer Evento");
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
			panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_1.setBounds(12, 13, 401, 44);
			panel.add(panel_1);
			panel_1.setLayout(null);
			
			JLabel lblOpciones = new JLabel("Opciones:");
			lblOpciones.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblOpciones.setForeground(Color.RED);
			lblOpciones.setBounds(12, 13, 87, 16);
			panel_1.add(lblOpciones);
			
			JRadioButton rdbtnPosponer = new JRadioButton("Posponer Evento");
			
			rdbtnPosponer.setSelected(true);
			rdbtnPosponer.setFont(new Font("Tahoma", Font.PLAIN, 15));
			rdbtnPosponer.setBounds(93, 9, 150, 25);
			panel_1.add(rdbtnPosponer);
			
			JRadioButton rdbtnCancelarEvento = new JRadioButton("Cancelar Evento");
			rdbtnCancelarEvento.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					loadEvento();
				}
			});
			rdbtnCancelarEvento.setFont(new Font("Tahoma", Font.PLAIN, 15));
			rdbtnCancelarEvento.setBounds(252, 9, 141, 25);
			panel_1.add(rdbtnCancelarEvento);
			
			JPanel panel_2 = new JPanel();
			panel_2.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_2.setBounds(12, 70, 401, 277);
			panel.add(panel_2);
			panel_2.setLayout(null);
			
			JLabel lblEvento = new JLabel("Evento:");
			lblEvento.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblEvento.setBounds(27, 28, 73, 16);
			panel_2.add(lblEvento);
			
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
			cbxEvento.setBounds(154, 26, 221, 22);
			panel_2.add(cbxEvento);
			
			JLabel lblFechaActual = new JLabel("Fecha Actual");
			lblFechaActual.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblFechaActual.setBounds(27, 67, 105, 22);
			panel_2.add(lblFechaActual);
			
			JLabel lblJustificacionDePosponer = new JLabel("Justificacion de Posponer el Evento");
			lblJustificacionDePosponer.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblJustificacionDePosponer.setBounds(27, 149, 235, 16);
			panel_2.add(lblJustificacionDePosponer);
			
			txtJustificacion = new JTextArea();
			txtJustificacion.setFont(new Font("Tahoma", Font.PLAIN, 15));
			txtJustificacion.setBounds(27, 178, 348, 86);
			panel_2.add(txtJustificacion);
			
			JLabel lblNuevaFecha = new JLabel("Nueva Fecha");
			lblNuevaFecha.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblNuevaFecha.setBounds(27, 108, 105, 16);
			panel_2.add(lblNuevaFecha);
			
			
			 CalendarioNuevo = new JDateChooser();
			 CalendarioNuevo.setFont(new Font("Tahoma", Font.PLAIN, 15));
			CalendarioNuevo.setDateFormatString("dd-MMM-yyyy");
			JTextFieldDateEditor editor1 = (JTextFieldDateEditor) CalendarioNuevo.getDateEditor();
			editor1.setEditable(false);
			CalendarioNuevo.getCalendarButton().addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			CalendarioNuevo.setBounds(154, 108, 221, 22);
			panel_2.add(CalendarioNuevo);
			
			txtfechaActual = new JTextField();
			txtfechaActual.setFont(new Font("Tahoma", Font.PLAIN, 15));
			txtfechaActual.setEditable(false);
			txtfechaActual.setBounds(154, 68, 221, 22);
			panel_2.add(txtfechaActual);
			txtfechaActual.setColumns(10);
			
			
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Realizar");
				okButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
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
}
