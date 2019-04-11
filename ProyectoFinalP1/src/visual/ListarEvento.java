package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import logica.Comision;
import logica.Evento;
import logica.Participante;
import logica.PlanificacionEvento;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.Component;

public class ListarEvento extends JDialog {

	private final JPanel contentPanel = new JPanel();
	public static Object[] fila;
	private static DefaultTableModel model;
	private String identificador = "";
	private JTable tableEventos;
	private JTextField txtBuscar;
	private JComboBox cbxTipo;
	private JComboBox cbxEstado;
	private JButton btnImprimir;
	

	
	public ListarEvento() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ListarEvento.class.getResource("/Imagenes/Trabajo.png")));
		setTitle("Listar Eventos");
		setResizable(false);
		setBounds(100, 100, 1000, 500);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
		{
			JPanel panel = new JPanel();
			panel.setBounds(12, 13, 970, 404);
			contentPanel.add(panel);
			panel.setLayout(null);
			{
				JPanel panel_1 = new JPanel();
				panel_1.setBackground(new Color(176, 196, 222));
				panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				panel_1.setBounds(12, 13, 946, 58);
				panel.add(panel_1);
				panel_1.setLayout(null);
				{
					JLabel lblCdigo = new JLabel("C\u00F3digo del Evento:");
					lblCdigo.setFont(new Font("Tahoma", Font.PLAIN, 15));
					lblCdigo.setBounds(12, 21, 126, 21);
					panel_1.add(lblCdigo);
				}
				{
					txtBuscar = new JTextField();
					txtBuscar.setFont(new Font("Tahoma", Font.PLAIN, 15));
					txtBuscar.setBounds(150, 17, 116, 25);
					panel_1.add(txtBuscar);
					txtBuscar.setColumns(10);
				}
				{
					JButton btnBuscar = new JButton("Buscar");
					btnBuscar.setFont(new Font("Tahoma", Font.PLAIN, 15));
					btnBuscar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							String codigo = txtBuscar.getText();
							
							if (!codigo.equalsIgnoreCase("")) {
								Evento e1 = PlanificacionEvento.getInstance().BuscarEventoCodigo(codigo);
								if(e1!=null) {
								loadTableEventoByCodigo(e1);}else {
									JOptionPane.showMessageDialog(null, "Este evento no existe", "Validación", JOptionPane.WARNING_MESSAGE);}
							}else {
								JOptionPane.showMessageDialog(null, "Revise los datos", "Validación", JOptionPane.WARNING_MESSAGE);
							}
							
							
						}
					});
					btnBuscar.setBounds(278, 17, 97, 25);
					panel_1.add(btnBuscar);
				}
				{
					JLabel lblFilto = new JLabel("Filtros:");
					lblFilto.setForeground(Color.RED);
					lblFilto.setFont(new Font("Tahoma", Font.BOLD, 15));
					lblFilto.setBounds(435, 21, 63, 16);
					panel_1.add(lblFilto);
				}
				{
					JLabel lblTipo = new JLabel("Tipo");
					lblTipo.setFont(new Font("Tahoma", Font.PLAIN, 15));
					lblTipo.setBounds(510, 21, 43, 16);
					panel_1.add(lblTipo);
				}
				{
					cbxTipo = new JComboBox();
					cbxTipo.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if (cbxTipo.getSelectedIndex() > 0 && cbxEstado.getSelectedIndex()==0) {
								String tipo = cbxTipo.getSelectedItem().toString();
								loadTableEventoFiltro(tipo,"");
							}
							if (cbxTipo.getSelectedIndex() > 0 && cbxEstado.getSelectedIndex()>0) {
								String tipo = cbxTipo.getSelectedItem().toString();
								String estado = cbxEstado.getSelectedItem().toString();
								loadTableEventoFiltro(tipo,estado);
							}
							if (cbxTipo.getSelectedIndex()==0 && cbxEstado.getSelectedIndex()==0) {
								loadTableEvento();
							}
						}
					});
					cbxTipo.setModel(new DefaultComboBoxModel(new String[] {"Seleccione", "Congreso", "Jornada", "Mesa Redonda"}));
					cbxTipo.setFont(new Font("Tahoma", Font.PLAIN, 15));
					cbxTipo.setBounds(565, 16, 126, 25);
					panel_1.add(cbxTipo);
				}
				{
					JLabel lblEstado = new JLabel("Estado");
					lblEstado.setFont(new Font("Tahoma", Font.PLAIN, 15));
					lblEstado.setBounds(732, 21, 53, 16);
					panel_1.add(lblEstado);
				}
				{
					cbxEstado = new JComboBox();
					cbxEstado.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if (cbxTipo.getSelectedIndex() == 0 && cbxEstado.getSelectedIndex() > 0) {
								String estado = cbxEstado.getSelectedItem().toString();
								loadTableEventoFiltro("",estado);
							}
							if (cbxTipo.getSelectedIndex() > 0 && cbxEstado.getSelectedIndex()>0) {
								String tipo = cbxTipo.getSelectedItem().toString();
								String estado = cbxEstado.getSelectedItem().toString();
								loadTableEventoFiltro(tipo,estado);
							}
							if (cbxTipo.getSelectedIndex()==0 && cbxEstado.getSelectedIndex()==0) {
								loadTableEvento();
							}
						}
					});
					cbxEstado.setModel(new DefaultComboBoxModel(new String[] {"Seleccione", "Activo", "No activo "}));
					cbxEstado.setFont(new Font("Tahoma", Font.PLAIN, 15));
					cbxEstado.setBounds(797, 17, 137, 25);
					panel_1.add(cbxEstado);
				}
			}
			{
				JPanel panel_2 = new JPanel();
				panel_2.setBounds(12, 84, 946, 307);
				panel.add(panel_2);
				panel_2.setLayout(null);
				{
					JScrollPane scrollPane = new JScrollPane();
					scrollPane.setBounds(0, 0, 946, 307);
					panel_2.add(scrollPane, BorderLayout.CENTER);
					{
						String[] header = {"Código","Nombre","Tipo","Fecha","Estado"};		
						model = new DefaultTableModel();
						model.setColumnIdentifiers(header);
						
						tableEventos = new JTable();
						tableEventos.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent e) {
								if(tableEventos.getSelectedRow()>=0){
									
									btnImprimir.setEnabled(true);
									
					
								}
							}
						});
						tableEventos.setFont(new Font("Tahoma", Font.PLAIN, 14));
						
						scrollPane.setViewportView(tableEventos);
						tableEventos.setModel(model);
					}
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnLimpiar = new JButton("Limpiar");
				btnLimpiar.setFont(new Font("Tahoma", Font.PLAIN, 15));
				btnLimpiar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						txtBuscar.setText("");
						cbxEstado.setSelectedIndex(0);
						cbxTipo.setSelectedIndex(0);
						loadTableEvento();
					}
				});
				
				btnImprimir = new JButton("Imprimir");
				btnImprimir.setAlignmentX(Component.CENTER_ALIGNMENT);
				btnImprimir.setFont(new Font("Tahoma", Font.PLAIN, 15));
				btnImprimir.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int fila = tableEventos.getSelectedRow();
						String codigo = (String)tableEventos.getValueAt(fila, 0);
						Evento aux = PlanificacionEvento.getInstance().BuscarEventoCodigo(codigo);
						SimpleDateFormat formatter = new SimpleDateFormat("hh:mm:ss dd-MMM-yyyy");
						SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy");
						String linea1 = "\r\n\r\nFecha de solicitud: " +formatter.format(new Date());
						String linea2 ="\r\nCódigo del Evento: "+aux.getIdentificador();
						String linea3 ="\r\nNombre del Evento: "+aux.getNombreEvento();
						String linea4 = "\r\nFecha del Evento: "+formatter1.format(aux.getFechaEvento());
						
						
						String linea6 = "\r\nCantidad de Trabajos Inscritos: " + aux.getLosTrabajos().size();
						String linea7 = "\r\nCantidad de Comisiones Inscritas: " + aux.getLasComisiones().size();
						String linea8 = "\r\nNota Extra:\r\n "+ aux.getJustificacion();
						File reporte = new File ("Reporte.txt");
				        FileWriter escritor;
				        
				        try {
				        	escritor = new FileWriter(reporte);
				        	escritor.write("Pontificia Universidad Católica Madre y Maestra");
							escritor.write("\r\nSistema de Planificación de Eventos");
							escritor.write("\r\n\r\nReporte del Evento");
							escritor.write(linea1);
							escritor.write(linea2);
							escritor.write(linea3);
							escritor.write(linea4);
							if (aux.isEstado()==true) {
								escritor.write("\r\nEstado del Evento: Activo");
							}else {
								escritor.write("\r\nEstado del Evento: No Activo");
								
							}
							if (aux.getJustificacion()!=null) {
								escritor.write(linea8);
							}
							
							escritor.write(linea6);
							escritor.write(linea7);
							
							if (aux.getRecursos().size()>0) {
								escritor.write("\r\n\r\nRecursos Utilizados:\r\n");
								for (int i = 0; i < aux.getRecursos().size(); i++) {
								
									escritor.write("\r\n-"+aux.getRecursos().get(i));
									
								}
							}
							
							if (aux.getLosTrabajos().size()>0) {
								escritor.write("\r\n\r\nDetalles de los Trabajos");
								for (int i = 0; i < aux.getLosTrabajos().size(); i++) {
									escritor.write("\r\n\r\nInformación del Trabajo:");
									escritor.write("\r\nCódigo del Trabajo: "+aux.getLosTrabajos().get(i).getIdentificador());
									escritor.write("\r\nNombre del Trabajo: "+aux.getLosTrabajos().get(i).getNombreTrabajo());
									escritor.write("\r\nÁrea del Trabajo: "+aux.getLosTrabajos().get(i).getAreaTrabajo());
									escritor.write("\r\nCédula del Participante: "+aux.getLosTrabajos().get(i).getElParticipante().getCedula());
									escritor.write("\r\nNombre del Participante: "+aux.getLosTrabajos().get(i).getElParticipante().getNombre());
									escritor.write("\r\nComisión Encargada: "+aux.getLosTrabajos().get(i).getLaComision().getCodigo());
									
								}
							}else {
								escritor.write("\r\n\r\nNo hay Trabajos Inscritos.");
							}
							
							escritor.close();
							JOptionPane.showMessageDialog(null,"El Reporte se ha Imprimido");
						} catch (IOException e2) {
							// TODO: handle exception
							e2.printStackTrace();
						}
				        
					
						
					}
				});
				buttonPane.add(btnImprimir);
				btnLimpiar.setActionCommand("OK");
				buttonPane.add(btnLimpiar);
				getRootPane().setDefaultButton(btnLimpiar);
			}
			{
				JButton btnCerrar = new JButton("Cerrar");
				btnCerrar.setFont(new Font("Tahoma", Font.PLAIN, 15));
				btnCerrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCerrar.setActionCommand("Cancel");
				buttonPane.add(btnCerrar);
			}
		}
		loadTableEvento();
	}
	
	public void loadTableEvento() {
		model.setRowCount(0);
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
		fila = new Object[model.getColumnCount()];
		for (int i = 0; i < PlanificacionEvento.getInstance().getLosEventos().size(); i++) {
			fila[0] = PlanificacionEvento.getInstance().getLosEventos().get(i).getIdentificador();
			fila[1] = PlanificacionEvento.getInstance().getLosEventos().get(i).getNombreEvento();
			fila[2] = PlanificacionEvento.getInstance().getLosEventos().get(i).getTipoEvento();
		    
		    fila[3] = formatter.format(PlanificacionEvento.getInstance().getLosEventos().get(i).getFechaEvento());
		    
			if (PlanificacionEvento.getInstance().getLosEventos().get(i).isEstado()==true) {
				
			    fila[4] = "Activo";			    
			    
			}else {
			    fila[4] = "No Activo";
			    
			    
			}
			model.addRow(fila);	
				
			
		}
	}
	public void loadTableEventoByCodigo(Evento e1) {
		model.setRowCount(0);
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
		fila = new Object[model.getColumnCount()];
		
		fila[0] = e1.getIdentificador();
		fila[1] = e1.getNombreEvento();
		fila[2] = e1.getTipoEvento();
	    
	    fila[3] = formatter.format(e1.getFechaEvento());
	    
	    if (e1.isEstado()==true) {
			
		    fila[4] = "Activo";			    
		    
		}else {
		    fila[4] = "No Activo";
		    
		    
		}
	    model.addRow(fila);
			
	}
	public void loadTableEventoFiltro(String tipo,String estado) {
		model.setRowCount(0);
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
		fila = new Object[model.getColumnCount()];
		for (int i = 0; i < PlanificacionEvento.getInstance().getLosEventos().size(); i++) {
			
			if (!tipo.equalsIgnoreCase("") && estado.equalsIgnoreCase("")) {
				if (PlanificacionEvento.getInstance().getLosEventos().get(i).getTipoEvento().equalsIgnoreCase(tipo)) {
					fila[0] = PlanificacionEvento.getInstance().getLosEventos().get(i).getIdentificador();
					fila[1] = PlanificacionEvento.getInstance().getLosEventos().get(i).getNombreEvento();
					fila[2] = PlanificacionEvento.getInstance().getLosEventos().get(i).getTipoEvento();
				    
				    fila[3] = formatter.format(PlanificacionEvento.getInstance().getLosEventos().get(i).getFechaEvento());
				    
					if (PlanificacionEvento.getInstance().getLosEventos().get(i).isEstado()==true) {
						
					    fila[4] = "Activo";			    
					    
					}else {
					    fila[4] = "No Activo";
					    
					    
					}
					model.addRow(fila);
				}
				
				
			}
			
			if (tipo.equalsIgnoreCase("") && !estado.equalsIgnoreCase("")) {
				if (cbxEstado.getSelectedItem().toString().equalsIgnoreCase("Activo")) {
					if (PlanificacionEvento.getInstance().getLosEventos().get(i).isEstado()==true) {
						fila[0] = PlanificacionEvento.getInstance().getLosEventos().get(i).getIdentificador();
						fila[1] = PlanificacionEvento.getInstance().getLosEventos().get(i).getNombreEvento();
						fila[2] = PlanificacionEvento.getInstance().getLosEventos().get(i).getTipoEvento();
					    
					    fila[3] = formatter.format(PlanificacionEvento.getInstance().getLosEventos().get(i).getFechaEvento());
					    fila[4] = "Activo";
					    model.addRow(fila);
					}
				}else {
					if (PlanificacionEvento.getInstance().getLosEventos().get(i).isEstado()==false) {
						fila[0] = PlanificacionEvento.getInstance().getLosEventos().get(i).getIdentificador();
						fila[1] = PlanificacionEvento.getInstance().getLosEventos().get(i).getNombreEvento();
						fila[2] = PlanificacionEvento.getInstance().getLosEventos().get(i).getTipoEvento();
					    
					    fila[3] = formatter.format(PlanificacionEvento.getInstance().getLosEventos().get(i).getFechaEvento());
					    fila[4] = "No Activo";
					    model.addRow(fila);
					}
				}
				
				
			}
			
			if (!tipo.equalsIgnoreCase("")&& !estado.equalsIgnoreCase("")) {
				if (PlanificacionEvento.getInstance().getLosEventos().get(i).getTipoEvento().equalsIgnoreCase(tipo) && cbxEstado.getSelectedItem().toString().equalsIgnoreCase("Activo")) {
					if (PlanificacionEvento.getInstance().getLosEventos().get(i).isEstado()==true) {
						fila[0] = PlanificacionEvento.getInstance().getLosEventos().get(i).getIdentificador();
						fila[1] = PlanificacionEvento.getInstance().getLosEventos().get(i).getNombreEvento();
						fila[2] = PlanificacionEvento.getInstance().getLosEventos().get(i).getTipoEvento();
					    
					    fila[3] = formatter.format(PlanificacionEvento.getInstance().getLosEventos().get(i).getFechaEvento());
					    fila[4] = "Activo";
					    model.addRow(fila);
					}
				}else  {
					if (PlanificacionEvento.getInstance().getLosEventos().get(i).isEstado()==false) {
						fila[0] = PlanificacionEvento.getInstance().getLosEventos().get(i).getIdentificador();
						fila[1] = PlanificacionEvento.getInstance().getLosEventos().get(i).getNombreEvento();
						fila[2] = PlanificacionEvento.getInstance().getLosEventos().get(i).getTipoEvento();
					    
					    fila[3] = formatter.format(PlanificacionEvento.getInstance().getLosEventos().get(i).getFechaEvento());
					    fila[4] = "No Activo";
					    model.addRow(fila);
					}
				}
			}
			
				
			
			
			
		}
	}
}
