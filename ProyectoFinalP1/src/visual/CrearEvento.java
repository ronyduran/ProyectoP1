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
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.JList;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import java.awt.Font;

public class CrearEvento extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCodigo;
	private JTextField txtNombreEvento;
	private JComboBox cbxTipoEvento;
	private JList jlistRecursos;
	private JDateChooser dateChooser;
	
	
	public CrearEvento() {
		setTitle("Crear Evento");
		setBounds(100, 100, 404, 543);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Informaciones Generales", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel.setBounds(12, 13, 374, 447);
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
				txtCodigo.setBounds(69, 25, 145, 22);
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
				txtNombreEvento.setBounds(69, 60, 293, 22);
				panel.add(txtNombreEvento);
				txtNombreEvento.setColumns(10);
			}
			{
				JLabel lblFecha = new JLabel("Fecha");
				lblFecha.setBounds(12, 108, 56, 16);
				panel.add(lblFecha);
			}
			
			JLabel lblTipoDeEvento = new JLabel("Tipo");
			lblTipoDeEvento.setBounds(12, 160, 97, 16);
			panel.add(lblTipoDeEvento);
			
			 cbxTipoEvento = new JComboBox();
			 cbxTipoEvento.setModel(new DefaultComboBoxModel(new String[] {"Seleccione", "Congreso", "Jornada", "Mesa Redonda"}));
			cbxTipoEvento.setBounds(69, 157, 293, 22);
			panel.add(cbxTipoEvento);
			
			JPanel panel_1 = new JPanel();
			panel_1.setBorder(new TitledBorder(null, "Recursos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_1.setBounds(12, 199, 350, 235);
			panel.add(panel_1);
			panel_1.setLayout(null);
			
			 
			
			jlistRecursos = new JList();
			jlistRecursos.setFont(new Font("Tahoma", Font.PLAIN, 14));
			jlistRecursos.setBounds(12, 23, 326, 199);
			panel_1.add(jlistRecursos);
			jlistRecursos.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			//DefaultListCellRenderer cellRenderer = (DefaultListCellRenderer)jlistRecursos.getCellRenderer();
			//cellRenderer.setHorizontalAlignment(SwingConstants .CENTER);
			jlistRecursos.setModel(CargarJListRecursos());
			jlistRecursos.setSelectionBackground(Color.LIGHT_GRAY);
			
			dateChooser = new JDateChooser();
			dateChooser.setDateFormatString("dd-MMM-yyyy");
			dateChooser.setBounds(69, 108, 293, 22);
			panel.add(dateChooser);
			dateChooser.setMinSelectableDate(new Date());
			JTextFieldDateEditor editor = (JTextFieldDateEditor) dateChooser.getDateEditor();
			editor.setEditable(false);
			
			
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
						Date fechaEvento = dateChooser.getDate();
						int [] indices= jlistRecursos.getSelectedIndices();
						ArrayList<String> recursos=new ArrayList<String>();
						for (int i = 0; i < indices.length; i++) {
							recursos.add(PlanificacionEvento.getInstance().getRecursos().get(indices[i]));
						}
						
						
						
						String nombreEvento= txtNombreEvento.getText();
						String identificador= txtCodigo.getText();
						
						
						if(fechaEvento!=null && !nombreEvento.equalsIgnoreCase("")&& cbxTipoEvento.getSelectedIndex()>0 && !jlistRecursos.isSelectionEmpty()) {
						Evento event=new Evento(tipoEvento, fechaEvento, recursos, nombreEvento, identificador);
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
		dateChooser.cleanup();
		txtNombreEvento.setText("");
		cbxTipoEvento.setSelectedIndex(0);
		jlistRecursos.clearSelection();
		
	}
	
	
	public static DefaultListModel CargarJListRecursos() {
		DefaultListModel recursos = new DefaultListModel();
		
		for (String aux : PlanificacionEvento.getInstance().getRecursos()) {
			recursos.addElement("-"+aux);	
		}
		
		return recursos;

	}
}
