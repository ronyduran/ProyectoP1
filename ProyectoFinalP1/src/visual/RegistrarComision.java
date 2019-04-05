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
import java.awt.Font;

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
	private static ArrayList<Persona> elegirJurado = new ArrayList();
	private static ArrayList<Jurado> listaJurado = new ArrayList();
	private JButton btnAgregar;
	private JButton btnEliminar;
	private JComboBox cbxPresidente;
	private JComboBox cbxEvento;

	
	public RegistrarComision() {
		setFont(new Font("Dialog", Font.PLAIN, 14));
		setTitle("Registro de Comision");
		setResizable(false);
		setBounds(100, 100, 462, 400);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		elegirJurado.addAll(PlanificacionEvento.getInstance().getLasPersonas());
		{
			JPanel panel = new JPanel();
			panel.setFont(new Font("Tahoma", Font.PLAIN, 15));
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
					lblAreaDeEspecialidad.setFont(new Font("Tahoma", Font.PLAIN, 15));
					lblAreaDeEspecialidad.setBounds(226, 13, 158, 16);
					panel_1.add(lblAreaDeEspecialidad);
				}
				{
					cbxArea = new JComboBox();
					cbxArea.setFont(new Font("Tahoma", Font.PLAIN, 15));
					cbxArea.setModel(new DefaultComboBoxModel(new String[] {"Seleccione", "Fisica", "Biologia", "Quimica", "Informatica", "Matematica", "Geologia"}));
					cbxArea.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							loadPresidente();
							loadTableLista("");
							
						}
					});
					cbxArea.setBounds(226, 42, 182, 22);
					panel_1.add(cbxArea);
				}
				{
					JLabel lblPresidente = new JLabel("Presidente:");
					lblPresidente.setFont(new Font("Tahoma", Font.PLAIN, 15));
					lblPresidente.setBounds(0, 77, 128, 16);
					panel_1.add(lblPresidente);
				}
				{
					cbxPresidente = new JComboBox();
					cbxPresidente.setFont(new Font("Tahoma", Font.PLAIN, 15));
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
					
					
					
					cbxPresidente.setBounds(0, 93, 158, 22);
					panel_1.add(cbxPresidente);
				}
				{
					JLabel lblCdigo = new JLabel("C\u00F3digo:");
					lblCdigo.setFont(new Font("Tahoma", Font.PLAIN, 15));
					lblCdigo.setBounds(0, 13, 128, 22);
					panel_1.add(lblCdigo);
				}
				{
					txtCodigo = new JTextField();
					txtCodigo.setFont(new Font("Tahoma", Font.PLAIN, 15));
					txtCodigo.setEditable(false);
					txtCodigo.setBounds(0, 42, 158, 22);
					panel_1.add(txtCodigo);
					txtCodigo.setColumns(10);
					txtCodigo.setText("Comi-"+PlanificacionEvento.getInstance().getCodComision());
				}
				{
					JLabel lblEvento = new JLabel("Evento:");
					lblEvento.setFont(new Font("Tahoma", Font.PLAIN, 15));
					lblEvento.setBounds(226, 76, 128, 16);
					panel_1.add(lblEvento);
				}
				{
					cbxEvento = new JComboBox();
					cbxEvento.setFont(new Font("Tahoma", Font.PLAIN, 14));
					cbxEvento.setBounds(226, 93, 182, 22);
					cbxEvento.setModel(new DefaultComboBoxModel(new String[] {"Seleccione"}));
					panel_1.add(cbxEvento);
				}
				{
					JLabel lblElegirJurado = new JLabel("Elegir Jurado:");
					lblElegirJurado.setFont(new Font("Tahoma", Font.PLAIN, 15));
					lblElegirJurado.setBounds(0, 128, 128, 23);
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
			btnAgregar.setFont(new Font("Tahoma", Font.PLAIN, 15));
			btnAgregar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int fila = tableLista.getSelectedRow();
					String cedula = (String)tableLista.getValueAt(fila, 0);
					Jurado j1 = (Jurado)PlanificacionEvento.getInstance().buscarPersonaPorCedula(cedula);
					listaJurado.add(j1);
					elegirJurado.remove(j1);
					String presidente= cbxPresidente.getSelectedItem().toString();
					String[] partes = presidente.split("-");
					String cedulaPresi = partes[0];
					loadTableElegido(cedulaPresi);
					loadTableLista(cedulaPresi);
					
				}
			});
			btnAgregar.setBounds(184, 166, 64, 25);
			panel.add(btnAgregar);
			
			btnEliminar = new JButton("\u2190");
			btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 15));
			btnEliminar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int fila = tableElegido.getSelectedRow();
					String cedula = (String)tableElegido.getValueAt(fila, 0);
					Jurado j1 = (Jurado)PlanificacionEvento.getInstance().buscarPersonaPorCedula(cedula);
					elegirJurado.add(j1);
					listaJurado.remove((Persona)j1);
					String presidente= cbxPresidente.getSelectedItem().toString();
					String[] partes = presidente.split("-");
					String cedulaPresi = partes[0];
					loadTableElegido(cedulaPresi);
					loadTableLista(cedulaPresi);
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
				btnRegistrar.setFont(new Font("Tahoma", Font.PLAIN, 15));
				btnRegistrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Comision aux = null;
						String area = (String)cbxArea.getSelectedItem();
						String codigo = txtCodigo.getText();
						ArrayList<Jurado> losJurados = new ArrayList();
						elegirJurado.removeAll(elegirJurado);
						losJurados.addAll(listaJurado);
						listaJurado.removeAll(listaJurado);
						String Presidente= cbxPresidente.getSelectedItem().toString();
						String [] partes= Presidente.split("~~");
						String cedula= partes[0];
						String evento=cbxEvento.getSelectedItem().toString();
						String []partes2=evento.split("~~");
						String codigoEven=partes2[0];
						
						Jurado elPresidente = (Jurado) PlanificacionEvento.getInstance().buscarPersonaPorCedula(cedula);
						Evento elEvento = PlanificacionEvento.getInstance().BuscarEventoCodigo(codigoEven);
						
						if(losJurados!=null && cbxArea.getSelectedIndex()!=0 && elPresidente!=null && elEvento!=null) {
						aux = new Comision(losJurados, area, codigo, elPresidente, elEvento);
						PlanificacionEvento.getInstance().insertarComision(aux);
						JOptionPane.showMessageDialog(null, "Operación exitosa", "Información", JOptionPane.INFORMATION_MESSAGE);
						PlanificacionEvento.getInstance().setCodComision(PlanificacionEvento.getInstance().getCodComision()+1);
						clean();}else {
							
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
				btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 15));
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						listaJurado.removeAll(listaJurado);
						elegirJurado.removeAll(elegirJurado);
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
		for (int i = 0; i < elegirJurado.size(); i++) {
			if (elegirJurado.get(i) instanceof Jurado) {
				if(!elegirJurado.get(i).getCedula().equalsIgnoreCase(cedula)) {
				if (((Jurado)(elegirJurado.get(i))).getArea().equalsIgnoreCase((String) cbxArea.getSelectedItem())) {
					fila[0] = elegirJurado.get(i).getCedula();
					fila[1] = elegirJurado.get(i).getNombre();
					model.addRow(fila);
				}}
				
			}				
		}	
		
	}
	/*public static void loadTableLista(String cedula) {
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
		
	}*/
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
		cbxPresidente.addItem(new String("Seleccione"));
		for (int i = 0; i < elegirJurado.size(); i++) {
			if (elegirJurado.get(i) instanceof Jurado) {
				if (((Jurado)(elegirJurado.get(i))).getArea().equalsIgnoreCase((String) cbxArea.getSelectedItem())) {
				cbxPresidente.addItem(new String(elegirJurado.get(i).getCedula()+"~~"+elegirJurado.get(i).getNombre()));
				
				}
			}				
		}
		
		cbxPresidente.setSelectedIndex(0);
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
    
    private void clean() {
		txtCodigo.setText("Comi-"+PlanificacionEvento.getInstance().getCodComision());
		cbxArea.setSelectedIndex(0);
		cbxEvento.setSelectedIndex(0);
		cbxPresidente.setSelectedIndex(0);
		loadTableElegido("");
		loadTableLista("");
		elegirJurado.addAll(PlanificacionEvento.getInstance().getLasPersonas());
	}
    
   
}
