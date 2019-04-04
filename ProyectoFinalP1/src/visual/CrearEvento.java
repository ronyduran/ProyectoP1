package visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import com.toedter.calendar.JCalendar;

import logica.Evento;
import logica.PlanificacionEvento;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SpinnerNumberModel;

public class CrearEvento extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCodigo;
	private JTextField txtNombreEvento;
	private JTextField txtFecha;
	private JComboBox cbxTipoEvento;
	private JSpinner spnCantAsist;
	
	
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
				txtCodigo.setText("Evento-"+PlanificacionEvento.getInstance().getCodEvento());
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
			btnFecha.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					Date fechaDelEVento=null;
					JCalendar calendario = new JCalendar();
					calendario.setMinSelectableDate(new Date());
					calendario.setPreferredSize(new Dimension(300,400));
					String message ="Fecha del evento:\n";
					Object[] params = {message,calendario};
					if(JOptionPane.showConfirmDialog(null,params,"Confirmacion de prestamo", JOptionPane.YES_NO_OPTION)== JOptionPane.YES_OPTION) {
						fechaDelEVento=calendario.getDate();
						SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
						String fechaEvento= formatter.format(fechaDelEVento);
						txtFecha.setText(fechaEvento);
					}
					
				}
			});
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
			spnCantAsist.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
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
						Date fechaEvento = null;
						if(!txtFecha.getText().equalsIgnoreCase("")) {
						SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
						try {
							fechaEvento = formatter.parse(txtFecha.getText());
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}catch (Exception e2) {
							// TODO: handle exception
						}
						
						}
						int cantAsistentes= new Integer(spnCantAsist.getValue().toString());
						String nombreEvento= txtNombreEvento.getText();
						String identificador= txtCodigo.getText();
						
						if(fechaEvento!=null && !nombreEvento.equalsIgnoreCase("")&& cantAsistentes>0 && cbxTipoEvento.getSelectedIndex()>0) {
						Evento event=new Evento(tipoEvento, fechaEvento, null, cantAsistentes, nombreEvento, identificador);
						PlanificacionEvento.getInstance().insertarEvento(event);
						JOptionPane.showMessageDialog(null, "Operación exitosa", "Información", JOptionPane.INFORMATION_MESSAGE);
						PlanificacionEvento.getInstance().setCodEvento(PlanificacionEvento.getInstance().getCodEvento()+1);
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
	
	private void clean() {
		txtCodigo.setText("Evento-"+PlanificacionEvento.getInstance().getCodEvento());
		txtFecha.setText("");
		txtNombreEvento.setText("");
		cbxTipoEvento.setSelectedIndex(0);
		spnCantAsist.setValue(Integer.parseInt("1"));
		
	}
	
}
