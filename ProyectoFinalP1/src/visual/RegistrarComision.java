package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import logica.Comision;
import logica.Evento;
import logica.Jurado;
import logica.Persona;
import logica.PlanificacionEvento;


import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.UIManager;
import java.awt.Color;

public class RegistrarComision extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCodigo;
	private JTable tableLista;
	private JTable tableElegido;
	private String identificador = "";
	public static Object[] fila;
	public static Object[] fila_1;
	private static DefaultTableModel model;
	private static DefaultTableModel model_1;
	private static JComboBox cbxArea;
	private static ArrayList<Jurado> listaJurado = new ArrayList();
	private JButton btnAgregar;
	private JButton btnEliminar;
	private JComboBox cbxPresidente;
	private JComboBox cbxEvento;

	
	public RegistrarComision() {
		setTitle("Registro de Comision");
		setResizable(false);
		setBounds(100, 100, 462, 400);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Informacion General", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel.setBounds(12, 13, 432, 304);
			contentPanel.add(panel);
			panel.setLayout(null);
			{
				JPanel panel_1 = new JPanel();
				panel_1.setBounds(12, 13, 408, 151);
				panel.add(panel_1);
				panel_1.setLayout(null);
				{
					JLabel lblAreaDeEspecialidad = new JLabel("Area de Especialidad:");
					lblAreaDeEspecialidad.setBounds(226, 13, 128, 16);
					panel_1.add(lblAreaDeEspecialidad);
				}
				{
					cbxArea = new JComboBox();
					cbxArea.setModel(new DefaultComboBoxModel(new String[] {"Seleccione", "Fisica", "Biologia", "Quimica", "Informatica", "Matematica", "Geologia"}));
					cbxArea.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							loadPresidente();
							loadTableLista("");
						}
					});
					cbxArea.setBounds(226, 42, 170, 22);
					panel_1.add(cbxArea);
				}
				{
					JLabel lblPresidente = new JLabel("Presidente:");
					lblPresidente.setBounds(12, 76, 128, 16);
					panel_1.add(lblPresidente);
				}
				{
					cbxPresidente = new JComboBox();
					cbxPresidente.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if(cbxPresidente.getSelectedIndex()>0) {
							String presidente= cbxPresidente.getSelectedItem().toString();
							String[] partes = presidente.split("-");
							String cedula = partes[0]; 
							
							loadTableLista(cedula);
							loadTableElegido(cedula);
							}
						}
					});
					
					cbxPresidente.setModel(new DefaultComboBoxModel(new String[] {"Seleccione"}));
					
					
					
					cbxPresidente.setBounds(12, 93, 158, 22);
					panel_1.add(cbxPresidente);
				}
				{
					JLabel lblCdigo = new JLabel("C\u00F3digo:");
					lblCdigo.setBounds(12, 13, 128, 16);
					panel_1.add(lblCdigo);
				}
				{
					txtCodigo = new JTextField();
					txtCodigo.setEditable(false);
					txtCodigo.setBounds(12, 41, 158, 22);
					panel_1.add(txtCodigo);
					txtCodigo.setColumns(10);
					txtCodigo.setText("Comi-"+PlanificacionEvento.getInstance().getCodComision());
				}
				{
					JLabel lblEvento = new JLabel("Evento:");
					lblEvento.setBounds(226, 76, 128, 16);
					panel_1.add(lblEvento);
				}
				{
					cbxEvento = new JComboBox();
					cbxEvento.setBounds(226, 93, 170, 22);
					cbxEvento.setModel(new DefaultComboBoxModel(new String[] {"Seleccione"}));
					panel_1.add(cbxEvento);
				}
				{
					JLabel lblElegirJurado = new JLabel("Elegir Jurado");
					lblElegirJurado.setBounds(0, 128, 128, 16);
					panel_1.add(lblElegirJurado);
				}
			}
			{
				JPanel panel_Lista = new JPanel();
				panel_Lista.setBounds(12, 166, 160, 125);
				panel.add(panel_Lista);
				panel_Lista.setLayout(null);
				
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(0, 0, 160, 125);
				panel_Lista.add(scrollPane,BorderLayout.CENTER);
				
				String[] header = {"Cédula","Nombre"};
				model = new DefaultTableModel();
				model.setColumnIdentifiers(header);
				
				String[] header1 = {"Cédula","Nombre"};
				model_1 = new DefaultTableModel();
				model_1.setColumnIdentifiers(header1);
				
				tableLista = new JTable();
				tableLista.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						btnAgregar.setEnabled(true);
					}
				});
				tableLista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				tableLista.setModel(model);
				scrollPane.setViewportView(tableLista);
				
			}
			
			JPanel panel_Elegido = new JPanel();
			panel_Elegido.setBounds(260, 166, 160, 125);
			panel.add(panel_Elegido);
			panel_Elegido.setLayout(null);
			{
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(0, 0, 160, 125);
				panel_Elegido.add(scrollPane,BorderLayout.CENTER);
				
				tableElegido = new JTable();
				tableElegido.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						btnEliminar.setEnabled(true);
					}
				});
				tableElegido.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				tableElegido.setModel(model_1);
				scrollPane.setViewportView(tableElegido);
			}
			
			btnAgregar = new JButton("\u2192");
			btnAgregar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int fila = tableLista.getSelectedRow();
					String cedula = (String)tableLista.getValueAt(fila, 0);
					Jurado j1 = (Jurado)PlanificacionEvento.getInstance().buscarPersonaPorCedula(cedula);
					listaJurado.add(j1);
					loadTableElegido("");
				}
			});
			btnAgregar.setBounds(184, 166, 64, 25);
			panel.add(btnAgregar);
			
			btnEliminar = new JButton("\u2190");
			btnEliminar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int fila = tableElegido.getSelectedRow();
					String cedula = (String)tableElegido.getValueAt(fila, 0);
					Jurado j1 = (Jurado)PlanificacionEvento.getInstance().buscarPersonaPorCedula(cedula);
					listaJurado.remove(j1);
					loadTableElegido("");
				}
			});
			btnEliminar.setBounds(184, 208, 64, 25);
			panel.add(btnEliminar);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnRegistrar = new JButton("Registrar");
				btnRegistrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Comision aux = null;
						String area = (String)cbxArea.getSelectedItem();
						String codigo = txtCodigo.getText();
						ArrayList<Jurado> losJurados = new ArrayList();
						losJurados.addAll(listaJurado);
						listaJurado.removeAll(listaJurado);
						Jurado elPresidente = (Jurado)buscarPersonaByNombre((String)cbxPresidente.getSelectedItem());
						Evento elEvento = buscarEventoByNombre((String)cbxEvento.getSelectedItem());
						if(losJurados!=null && cbxArea.getSelectedIndex()!=0 && elPresidente!=null && elEvento!=null) {
						aux = new Comision(losJurados, area, codigo, elPresidente, elEvento);
						PlanificacionEvento.getInstance().insertarComision(aux);
						JOptionPane.showMessageDialog(null, "Operación exitosa", "Información", JOptionPane.INFORMATION_MESSAGE);
						PlanificacionEvento.getInstance().setCodComision(PlanificacionEvento.getInstance().getCodComision()+1);
						dispose();}else {
							
							JOptionPane.showMessageDialog(null, "Revise los datos", "Validación", JOptionPane.WARNING_MESSAGE);	
						}
					}
				});
				btnRegistrar.setActionCommand("OK");
				buttonPane.add(btnRegistrar);
				getRootPane().setDefaultButton(btnRegistrar);
			}
			{
				JButton btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						listaJurado.removeAll(listaJurado);
						dispose();
					}
				});
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
		
		loadEvento();
	}
	
	public static void loadTableLista(String cedula) {
		model.setRowCount(0);
		
		fila = new Object[model.getColumnCount()];
		for (int i = 0; i < PlanificacionEvento.getInstance().getLasPersonas().size(); i++) {
			if (PlanificacionEvento.getInstance().getLasPersonas().get(i) instanceof Jurado) {
				if(!PlanificacionEvento.getInstance().getLasPersonas().get(i).getCedula().equalsIgnoreCase(cedula)) {
				if (((Jurado)(PlanificacionEvento.getInstance().getLasPersonas().get(i))).getArea().equalsIgnoreCase((String) cbxArea.getSelectedItem())) {
					fila[0] = PlanificacionEvento.getInstance().getLasPersonas().get(i).getCedula();
					fila[1] = PlanificacionEvento.getInstance().getLasPersonas().get(i).getNombre();
					model.addRow(fila);
				}}
				
			}				
		}	
		
	}
    public static void loadTableElegido(String cedula) {
		
		
		
		model_1.setRowCount(0);
		fila_1 = new Object[model_1.getColumnCount()];
		for (int i = 0; i < listaJurado.size(); i++) {
			if(!listaJurado.get(i).getCedula().equalsIgnoreCase(cedula)) {
			fila_1[0] = listaJurado.get(i).getCedula();
			fila_1[1] = listaJurado.get(i).getNombre();
			
			model_1.addRow(fila_1);
		}}
	}
    
    private void loadPresidente() {
		cbxPresidente.removeAllItems();
		for (int i = 0; i < PlanificacionEvento.getInstance().getLasPersonas().size(); i++) {
			if (PlanificacionEvento.getInstance().getLasPersonas().get(i) instanceof Jurado) {
				if (((Jurado)(PlanificacionEvento.getInstance().getLasPersonas().get(i))).getArea().equalsIgnoreCase((String) cbxArea.getSelectedItem())) {
				cbxPresidente.addItem(new String(PlanificacionEvento.getInstance().getLasPersonas().get(i).getCedula()+"-"+PlanificacionEvento.getInstance().getLasPersonas().get(i).getNombre()));
				
				}
				
			}				
		}
		cbxPresidente.insertItemAt((String)"<Seleccione>", 0);
		cbxPresidente.setSelectedIndex(0);
	}
    
    private void loadEvento() {
    	cbxEvento.removeAllItems();
    	for (int i = 0; i < PlanificacionEvento.getInstance().getLosEventos().size(); i++) {
    		cbxEvento.addItem((String) PlanificacionEvento.getInstance().getLosEventos().get(i).getNombreEvento());
			
		}
    	cbxEvento.insertItemAt((String)"<Seleccione>", 0);
    	cbxEvento.setSelectedItem(0);
    }
    private Persona buscarPersonaByNombre(String nombre) {
    	Persona p1 = null;
    	for (int i = 0; i < PlanificacionEvento.getInstance().getLasPersonas().size(); i++) {
			if (PlanificacionEvento.getInstance().getLasPersonas().get(i) instanceof Jurado) {
				if (PlanificacionEvento.getInstance().getLasPersonas().get(i).getNombre().equalsIgnoreCase(nombre)) {
					p1= PlanificacionEvento.getInstance().getLasPersonas().get(i);
				}
			}
		}
    	return p1;
    }
    private Evento buscarEventoByNombre(String nombre) {
    	Evento e1 = null;
    	for (int i = 0; i < PlanificacionEvento.getInstance().getLosEventos().size(); i++) {
			if (PlanificacionEvento.getInstance().getLosEventos().get(i).getNombreEvento().equalsIgnoreCase(nombre)) {
				e1= PlanificacionEvento.getInstance().getLosEventos().get(i);
			}
		}
    	return e1;
    }
}
