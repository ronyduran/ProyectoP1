package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import com.toedter.calendar.JCalendar;


import logica.Evento;
import logica.PlanificacionEvento;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.JList;

public class CrearEvento extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCodigo;
	private JTextField txtNombreEvento;
	private JTextField txtFecha;
	private JComboBox cbxTipoEvento;
	private JSpinner spnCantAsist;
	private JList jlistRecursos;
	
	
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
			panel.setBounds(12, 13, 407, 447);
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
			panel_1.setBounds(12, 236, 383, 198);
			panel.add(panel_1);
			panel_1.setLayout(null);
			
			 
			
			jlistRecursos = new JList();
			jlistRecursos.setBounds(91, 13, 197, 172);
			panel_1.add(jlistRecursos);
			jlistRecursos.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

			jlistRecursos.setModel(CargarJListRecursos());
			jlistRecursos.setSelectionBackground(Color.LIGHT_GRAY);
			
			 for (MouseListener mouseListener : jlistRecursos.getMouseListeners()) {
			        jlistRecursos.removeMouseListener(mouseListener);
			    }
			    jlistRecursos.addMouseListener(new MouseAdapter() {
			        @Override
			        public void mouseClicked(MouseEvent e) {
			            int index = jlistRecursos.locationToIndex(e.getPoint());
			            if (jlistRecursos.isSelectedIndex(index)) {
			                jlistRecursos.removeSelectionInterval(index, index);
			            } else {
			                jlistRecursos.addSelectionInterval(index, index);
			            }
			            jlistRecursos.requestFocusInWindow();
			        }
			    });
			
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
						
						int [] indices= jlistRecursos.getSelectedIndices();
						ArrayList<String> recursos=new ArrayList<String>();
						for (int i = 0; i < indices.length; i++) {
							recursos.add(PlanificacionEvento.getInstance().getRecursos().get(indices[i]));
						}
						
						//System.out.println(recursos);
						int cantAsistentes= new Integer(spnCantAsist.getValue().toString());
						String nombreEvento= txtNombreEvento.getText();
						String identificador= txtCodigo.getText();
						
						
						if(fechaEvento!=null && !nombreEvento.equalsIgnoreCase("")&& cantAsistentes>0 && cbxTipoEvento.getSelectedIndex()>0 && !jlistRecursos.isSelectionEmpty()) {
						Evento event=new Evento(tipoEvento, fechaEvento, recursos, cantAsistentes, nombreEvento, identificador);
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
		jlistRecursos.clearSelection();
		
	}
	
	
	public static DefaultListModel CargarJListRecursos() {
		DefaultListModel recursos = new DefaultListModel();
		
		for (String aux : PlanificacionEvento.getInstance().getRecursos()) {
			recursos.addElement(aux);	
		}
		
		return recursos;

	}
}
