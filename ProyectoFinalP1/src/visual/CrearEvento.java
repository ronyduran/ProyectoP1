package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import logica.Evento;
import logica.PlanificacionEvento;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

public class CrearEvento extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCodigo;
	private JTextField txtNombreEvento;
	private JTextField txtFecha;
	private JComboBox cbxTipoEvento;
	private JSpinner spnCantAsist;
	private int i=0;
	
	public CrearEvento() {
		setTitle("Crear Evento");
		setBounds(100, 100, 449, 543);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Informaciones Generales", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel.setBounds(12, 13, 407, 435);
			contentPanel.add(panel);
			panel.setLayout(null);
			{
				JLabel lblCdigo = new JLabel("C\u00F3digo");
				lblCdigo.setBounds(12, 28, 56, 16);
				panel.add(lblCdigo);
			}
			{
				txtCodigo = new JTextField();
				txtCodigo.setEditable(false);
				txtCodigo.setBounds(69, 25, 138, 22);
				panel.add(txtCodigo);
				txtCodigo.setColumns(10);
				txtCodigo.setText("Evento-"+i+1);
			}
			{
				JLabel lblNombre = new JLabel("Nombre");
				lblNombre.setBounds(12, 61, 56, 16);
				panel.add(lblNombre);
			}
			{
				txtNombreEvento = new JTextField();
				txtNombreEvento.setBounds(69, 60, 301, 22);
				panel.add(txtNombreEvento);
				txtNombreEvento.setColumns(10);
			}
			{
				JLabel lblFecha = new JLabel("Fecha");
				lblFecha.setBounds(12, 108, 56, 16);
				panel.add(lblFecha);
			}
			{
				txtFecha = new JTextField();
				txtFecha.setEditable(false);
				txtFecha.setBounds(69, 105, 166, 22);
				panel.add(txtFecha);
				txtFecha.setColumns(10);
			}
			
			JButton btnFecha = new JButton("Elegir Fecha");
			btnFecha.setBounds(257, 104, 115, 25);
			panel.add(btnFecha);
			
			JLabel lblTipoDeEvento = new JLabel("Tipo de Evento");
			lblTipoDeEvento.setBounds(12, 160, 97, 16);
			panel.add(lblTipoDeEvento);
			
			 cbxTipoEvento = new JComboBox();
			 cbxTipoEvento.setModel(new DefaultComboBoxModel(new String[] {"Seleccione", "Congreso", "Jornada", "Mesa Redonda"}));
			cbxTipoEvento.setBounds(122, 157, 248, 22);
			panel.add(cbxTipoEvento);
			
			JLabel lblCantidadDeAsistentes = new JLabel("Cantidad de Asistentes");
			lblCantidadDeAsistentes.setBounds(12, 205, 138, 16);
			panel.add(lblCantidadDeAsistentes);
			
			spnCantAsist = new JSpinner();
			spnCantAsist.setBounds(175, 202, 89, 22);
			panel.add(spnCantAsist);
			
			JPanel panel_1 = new JPanel();
			panel_1.setBorder(new TitledBorder(null, "Recursos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_1.setBounds(12, 236, 383, 186);
			panel.add(panel_1);
			panel_1.setLayout(null);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Crear");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						String tipoEvento= cbxTipoEvento.getSelectedItem().toString();
						/*SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
						Date fechaEvento = new Date();
						try {
							fechaEvento = formatter.parse(txtFecha.getText());
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}*/
						int cantAsistentes= new Integer(spnCantAsist.getValue().toString());
						String nombreEvento= txtNombreEvento.getText();
						String identificador= txtCodigo.getText();
						
						Evento event=new Evento(tipoEvento, null, null, cantAsistentes, nombreEvento, identificador);
						PlanificacionEvento.getInstance().insertarEvento(event);
						i++;
						
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Salir");
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
