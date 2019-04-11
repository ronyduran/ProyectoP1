package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import logica.Jurado;
import logica.PlanificacionEvento;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ListarJurados extends JDialog {

	
	private final JPanel contentPanel = new JPanel();
	private JFormattedTextField txtBuscar;
	private JTable tableJurado;
	public static Object[] fila;
	private static DefaultTableModel model;
	private String identificador = "";
	private JComboBox cbxSexo;
	private JComboBox cbxGrado;
	private JComboBox cbxArea;
	private JButton btnModificar;
	
	public ListarJurados() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ListarJurados.class.getResource("/Imagenes/Trabajo.png")));
		setTitle("Listar Jurados");
		setBounds(100, 100, 1091, 534);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		
		
		
		
		{
			JPanel panel = new JPanel();
			panel.setBounds(12, 13, 1061, 432);
			contentPanel.add(panel);
			panel.setLayout(null);
			
			JPanel panel_1 = new JPanel();
			panel_1.setBackground(new Color(176, 196, 222));
			panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_1.setBounds(12, 13, 1037, 63);
			panel.add(panel_1);
			panel_1.setLayout(null);
			
		
			try {
				MaskFormatter mascara = new MaskFormatter("###########");
				txtBuscar = new JFormattedTextField(mascara);
				txtBuscar.setFont(new Font("Tahoma", Font.PLAIN, 15));
				txtBuscar.setBounds(136, 16, 116, 25);
				panel_1.add(txtBuscar);
				txtBuscar.setColumns(10);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			JButton btnBuscar = new JButton("Buscar");
			btnBuscar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String cedula = txtBuscar.getText();
					cbxGrado.setSelectedIndex(0);
					cbxSexo.setSelectedIndex(0);
					if (!cedula.equalsIgnoreCase("")) {
						Jurado p1 = (Jurado)PlanificacionEvento.getInstance().buscarPersonaPorCedula(cedula);
						if(p1!=null) {
						loadTableJuradoCedula(p1);}else {
							JOptionPane.showMessageDialog(null, "Este Jurado no existe", "Validación", JOptionPane.WARNING_MESSAGE);}
					}else {
						JOptionPane.showMessageDialog(null, "Revise los datos", "Validación", JOptionPane.WARNING_MESSAGE);
					}
					
					
				}
			});
			btnBuscar.setFont(new Font("Tahoma", Font.PLAIN, 15));
			btnBuscar.setBounds(264, 15, 97, 25);
			panel_1.add(btnBuscar);
			
			JLabel lblBuscarJurado = new JLabel("C\u00E9dula del Jurado:");
			lblBuscarJurado.setHorizontalAlignment(SwingConstants.TRAILING);
			lblBuscarJurado.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblBuscarJurado.setBounds(-18, 18, 144, 16);
			panel_1.add(lblBuscarJurado);
			
			JLabel lblFiltro = new JLabel("Filtros:");
			lblFiltro.setForeground(Color.RED);
			lblFiltro.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblFiltro.setBounds(385, 19, 57, 16);
			panel_1.add(lblFiltro);
			
			cbxSexo = new JComboBox();
			cbxSexo.setFont(new Font("Tahoma", Font.PLAIN, 15));
			cbxSexo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (cbxSexo.getSelectedIndex()>0&&cbxArea.getSelectedIndex()==0 && cbxGrado.getSelectedIndex()==0) {
						String sexo= cbxSexo.getSelectedItem().toString();
						loadTableJuradoFiltro(sexo, "", "");
					}
					if (cbxSexo.getSelectedIndex()>0 &&(cbxArea.getSelectedIndex()>0 || cbxGrado.getSelectedIndex()>0)) {
						if(cbxArea.getSelectedIndex()>0) {
						String area= cbxArea.getSelectedItem().toString();
						String sexo= cbxSexo.getSelectedItem().toString();
						loadTableJuradoFiltro(sexo, "", area);
					}
					if(cbxGrado.getSelectedIndex()>0) {
						String grado= cbxGrado.getSelectedItem().toString();
						String sexo=cbxSexo.getSelectedItem().toString();
						loadTableJuradoFiltro(sexo,grado, "");}
					
					if(cbxArea.getSelectedIndex()>0 && cbxGrado.getSelectedIndex()>0){
						String area= cbxArea.getSelectedItem().toString();
						String sexo=cbxSexo.getSelectedItem().toString();
						String grado= cbxGrado.getSelectedItem().toString();
						loadTableJuradoFiltro(sexo, grado, area);}
					}if(cbxSexo.getSelectedIndex()==0 && cbxGrado.getSelectedIndex()==0 && cbxArea.getSelectedIndex()==0) {
						
						loadTableJurado();
					
				}
			}});
			cbxSexo.setModel(new DefaultComboBoxModel(new String[] {"Seleccione", "Hombre", "Mujer"}));
			cbxSexo.setBounds(496, 16, 101, 25);
			panel_1.add(cbxSexo);
			
			JLabel lblSexo = new JLabel("Sexo");
			lblSexo.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblSexo.setBounds(454, 19, 43, 16);
			panel_1.add(lblSexo);
			
			JLabel lblGradoAcadmico = new JLabel("Grado Acad\u00E9mico");
			lblGradoAcadmico.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblGradoAcadmico.setBounds(609, 19, 116, 16);
			panel_1.add(lblGradoAcadmico);
			
			cbxGrado = new JComboBox();
			cbxGrado.setFont(new Font("Tahoma", Font.PLAIN, 15));
			cbxGrado.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (cbxGrado.getSelectedIndex()>0&&cbxArea.getSelectedIndex()==0 && cbxSexo.getSelectedIndex()==0) {
						String grado= cbxGrado.getSelectedItem().toString();
						loadTableJuradoFiltro("", grado, "");
					}
					if (cbxGrado.getSelectedIndex()>0 &&(cbxArea.getSelectedIndex()>0 || cbxSexo.getSelectedIndex()>0)) {
						if(cbxArea.getSelectedIndex()>0) {
						String area= cbxArea.getSelectedItem().toString();
						String grado= cbxGrado.getSelectedItem().toString();
						loadTableJuradoFiltro("", grado, area);
					}
					if(cbxSexo.getSelectedIndex()>0) {
						String grado= cbxGrado.getSelectedItem().toString();
						String sexo=cbxSexo.getSelectedItem().toString();
						loadTableJuradoFiltro(sexo,grado, "");}
					
					if(cbxArea.getSelectedIndex()>0 && cbxSexo.getSelectedIndex()>0){
						String area= cbxArea.getSelectedItem().toString();
						String sexo=cbxSexo.getSelectedItem().toString();
						String grado= cbxGrado.getSelectedItem().toString();
						loadTableJuradoFiltro(sexo, grado, area);}
					}if(cbxSexo.getSelectedIndex()==0 && cbxGrado.getSelectedIndex()==0 && cbxArea.getSelectedIndex()==0) {
						
						loadTableJurado();
	
			}
				}
			});
			cbxGrado.setModel(new DefaultComboBoxModel(new String[] {"Seleccione", "Licenciado", "Mag\u00EDster", "Doctorado"}));
			cbxGrado.setBounds(737, 16, 97, 25);
			panel_1.add(cbxGrado);
			
			JLabel lblArea = new JLabel("\u00C1rea");
			lblArea.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblArea.setBounds(863, 19, 43, 16);
			panel_1.add(lblArea);
			
			 cbxArea = new JComboBox();
			 cbxArea.setFont(new Font("Tahoma", Font.PLAIN, 15));
			cbxArea.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (cbxArea.getSelectedIndex()>0 && cbxGrado.getSelectedIndex()==0 && cbxSexo.getSelectedIndex()==0) {
						String area= cbxArea.getSelectedItem().toString();
						loadTableJuradoFiltro("", "", area);
					}
					if (cbxArea.getSelectedIndex()>0 &&(cbxGrado.getSelectedIndex()>0 || cbxSexo.getSelectedIndex()>0)) {
						if(cbxGrado.getSelectedIndex()>0) {
						String area= cbxArea.getSelectedItem().toString();
						String grado= cbxGrado.getSelectedItem().toString();
						loadTableJuradoFiltro("", grado, area);
					}
					if(cbxSexo.getSelectedIndex()>0) {
						String area= cbxArea.getSelectedItem().toString();
						String sexo=cbxSexo.getSelectedItem().toString();
						loadTableJuradoFiltro(sexo,"", area);}
					
					if(cbxGrado.getSelectedIndex()>0 && cbxSexo.getSelectedIndex()>0){
						String area= cbxArea.getSelectedItem().toString();
						String sexo=cbxSexo.getSelectedItem().toString();
						String grado= cbxGrado.getSelectedItem().toString();
						loadTableJuradoFiltro(sexo, grado, area);}
					}if(cbxSexo.getSelectedIndex()==0 && cbxGrado.getSelectedIndex()==0 && cbxArea.getSelectedIndex()==0) {
						
						loadTableJurado();
					
					
					
				
			}}});
			cbxArea.setModel(new DefaultComboBoxModel(new String[] {"Seleccione", "Fisica", "Biologia", "Quimica", "Informatica", "Matematica", "Geologia"}));
			cbxArea.setBounds(909, 16, 97, 25);
			panel_1.add(cbxArea);
			
			
			
			JPanel panel_2 = new JPanel();
			panel_2.setBounds(12, 89, 1037, 330);
			panel.add(panel_2);
			panel_2.setLayout(null);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(0, 0, 1037, 330);
			panel_2.add(scrollPane,BorderLayout.CENTER);
			
			String[] header = {"Cédula","Nombre","Sexo","Grado Académico","Area","Teléfono","Dirección"};
			
			Font font = new Font("Tahoma", Font.PLAIN, 17);
			
			model = new DefaultTableModel();
			model.setColumnIdentifiers(header);
			
			tableJurado = new JTable();
			tableJurado.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					btnModificar.setEnabled(true);
					
				}
			});
			tableJurado.setFont(new Font("Tahoma", Font.PLAIN, 14));
			
			scrollPane.setViewportView(tableJurado);
			tableJurado.setModel(model);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnLimpiar = new JButton("Limpiar");
				btnLimpiar.setFont(new Font("Tahoma", Font.PLAIN, 15));
				btnLimpiar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						txtBuscar.setText("");
						cbxGrado.setSelectedIndex(0);
						cbxSexo.setSelectedIndex(0);
						cbxArea.setSelectedIndex(0);
						loadTableJurado();
					}
				});
				
				btnModificar = new JButton("Modificar");
				btnModificar.setEnabled(false);
				btnModificar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						int fila=	tableJurado.getSelectedRow();
						String id= (String) tableJurado.getValueAt(fila, 0);
						RegistrarPersona modPer= new RegistrarPersona(2, id,true);
						modPer.setModal(true);
						modPer.setVisible(true);
						loadTableJurado();
					}
				});
				btnModificar.setFont(new Font("Tahoma", Font.PLAIN, 15));
				btnModificar.setActionCommand("OK");
				buttonPane.add(btnModificar);
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
		loadTableJurado();
	}
	
	public void loadTableJurado() {
		model.setRowCount(0);
		fila = new Object[model.getColumnCount()];
		for (int i = 0; i < PlanificacionEvento.getInstance().getLasPersonas().size(); i++) {
			if (PlanificacionEvento.getInstance().getLasPersonas().get(i) instanceof Jurado){
				fila[0] = PlanificacionEvento.getInstance().getLasPersonas().get(i).getCedula();
				fila[1] = PlanificacionEvento.getInstance().getLasPersonas().get(i).getNombre();
				fila[2] = PlanificacionEvento.getInstance().getLasPersonas().get(i).getSexo();
			    fila[3] = PlanificacionEvento.getInstance().getLasPersonas().get(i).getGradoAcademico();
			    fila[4] = ((Jurado)PlanificacionEvento.getInstance().getLasPersonas().get(i)).getArea();
			    fila[5] = PlanificacionEvento.getInstance().getLasPersonas().get(i).getTelefono();
			    fila[6] = PlanificacionEvento.getInstance().getLasPersonas().get(i).getDireccion();
			    model.addRow(fila);
				
			}
		}
	}
	public void loadTableJuradoCedula(Jurado p1) {
		model.setRowCount(0);
		fila = new Object[model.getColumnCount()];
		
		fila[0] = p1.getCedula();
		fila[1] = p1.getNombre();
		fila[2] = p1.getSexo();
	    fila[3] = p1.getGradoAcademico();
	    fila[4] = ((Jurado)p1).getArea();
	    fila[5] = p1.getTelefono();
	    fila[6] = p1.getDireccion();
	    model.addRow(fila);
			
	}
	public void loadTableJuradoFiltro(String sexo, String grado, String area) {
		model.setRowCount(0);
		fila = new Object[model.getColumnCount()];

		for (int i = 0; i < PlanificacionEvento.getInstance().getLasPersonas().size(); i++) {
			if (PlanificacionEvento.getInstance().getLasPersonas().get(i) instanceof Jurado){
				if(!sexo.equalsIgnoreCase("")&& grado.equalsIgnoreCase("") && area.equalsIgnoreCase("")) {
				if(PlanificacionEvento.getInstance().getLasPersonas().get(i).getSexo().equalsIgnoreCase(sexo)){
				fila[0] = PlanificacionEvento.getInstance().getLasPersonas().get(i).getCedula();
				fila[1] = PlanificacionEvento.getInstance().getLasPersonas().get(i).getNombre();
				fila[2] = PlanificacionEvento.getInstance().getLasPersonas().get(i).getSexo();
			    fila[3] = PlanificacionEvento.getInstance().getLasPersonas().get(i).getGradoAcademico();
			    fila[4] = ((Jurado)PlanificacionEvento.getInstance().getLasPersonas().get(i)).getArea();
			    fila[5] = PlanificacionEvento.getInstance().getLasPersonas().get(i).getTelefono();
			    fila[6] = PlanificacionEvento.getInstance().getLasPersonas().get(i).getDireccion();
			    model.addRow(fila);
				}}
				
				if(sexo.equalsIgnoreCase("")&& !grado.equalsIgnoreCase("")&& area.equalsIgnoreCase("")) {
					if(PlanificacionEvento.getInstance().getLasPersonas().get(i).getGradoAcademico().equalsIgnoreCase(grado)) {
				fila[0] = PlanificacionEvento.getInstance().getLasPersonas().get(i).getCedula();
				fila[1] = PlanificacionEvento.getInstance().getLasPersonas().get(i).getNombre();
				fila[2] = PlanificacionEvento.getInstance().getLasPersonas().get(i).getSexo();
			    fila[3] = PlanificacionEvento.getInstance().getLasPersonas().get(i).getGradoAcademico();
			    fila[4] = ((Jurado)PlanificacionEvento.getInstance().getLasPersonas().get(i)).getArea();
			    fila[5] = PlanificacionEvento.getInstance().getLasPersonas().get(i).getTelefono();
			    fila[6] = PlanificacionEvento.getInstance().getLasPersonas().get(i).getDireccion();
			    model.addRow(fila);
				}}
				if(sexo.equalsIgnoreCase("")&& grado.equalsIgnoreCase("")&& !area.equalsIgnoreCase("")) {
				if(((Jurado)PlanificacionEvento.getInstance().getLasPersonas().get(i)).getArea().equalsIgnoreCase(area)) {
				fila[0] = PlanificacionEvento.getInstance().getLasPersonas().get(i).getCedula();
				fila[1] = PlanificacionEvento.getInstance().getLasPersonas().get(i).getNombre();
				fila[2] = PlanificacionEvento.getInstance().getLasPersonas().get(i).getSexo();
			    fila[3] = PlanificacionEvento.getInstance().getLasPersonas().get(i).getGradoAcademico();
			    fila[4] = ((Jurado)PlanificacionEvento.getInstance().getLasPersonas().get(i)).getArea();
			    fila[5] = PlanificacionEvento.getInstance().getLasPersonas().get(i).getTelefono();
			    fila[6] = PlanificacionEvento.getInstance().getLasPersonas().get(i).getDireccion();
			    model.addRow(fila);
				}}
				if(!sexo.equalsIgnoreCase("")&& !grado.equalsIgnoreCase("")&& area.equalsIgnoreCase("")) {
					if(PlanificacionEvento.getInstance().getLasPersonas().get(i).getGradoAcademico().equalsIgnoreCase(grado)&&PlanificacionEvento.getInstance().getLasPersonas().get(i).getSexo().equalsIgnoreCase(sexo)) {
					fila[0] = PlanificacionEvento.getInstance().getLasPersonas().get(i).getCedula();
					fila[1] = PlanificacionEvento.getInstance().getLasPersonas().get(i).getNombre();
					fila[2] = PlanificacionEvento.getInstance().getLasPersonas().get(i).getSexo();
				    fila[3] = PlanificacionEvento.getInstance().getLasPersonas().get(i).getGradoAcademico();
				    fila[4] = ((Jurado)PlanificacionEvento.getInstance().getLasPersonas().get(i)).getArea();
				    fila[5] = PlanificacionEvento.getInstance().getLasPersonas().get(i).getTelefono();
				    fila[6] = PlanificacionEvento.getInstance().getLasPersonas().get(i).getDireccion();
				    model.addRow(fila);
					}}
				
				if(!sexo.equalsIgnoreCase("")&& grado.equalsIgnoreCase("")&& !area.equalsIgnoreCase("")) {
					if( PlanificacionEvento.getInstance().getLasPersonas().get(i).getSexo().equalsIgnoreCase(sexo) &&((Jurado)PlanificacionEvento.getInstance().getLasPersonas().get(i)).getArea().equalsIgnoreCase(area)) {
					fila[0] = PlanificacionEvento.getInstance().getLasPersonas().get(i).getCedula();
					fila[1] = PlanificacionEvento.getInstance().getLasPersonas().get(i).getNombre();
					fila[2] = PlanificacionEvento.getInstance().getLasPersonas().get(i).getSexo();
				    fila[3] = PlanificacionEvento.getInstance().getLasPersonas().get(i).getGradoAcademico();
				    fila[4] = ((Jurado)PlanificacionEvento.getInstance().getLasPersonas().get(i)).getArea();
				    fila[5] = PlanificacionEvento.getInstance().getLasPersonas().get(i).getTelefono();
				    fila[6] = PlanificacionEvento.getInstance().getLasPersonas().get(i).getDireccion();
				    model.addRow(fila);
					}}
				
				if(sexo.equalsIgnoreCase("")&& !grado.equalsIgnoreCase("")&& !area.equalsIgnoreCase("")) {
					if(PlanificacionEvento.getInstance().getLasPersonas().get(i).getGradoAcademico().equalsIgnoreCase(grado) &&((Jurado)PlanificacionEvento.getInstance().getLasPersonas().get(i)).getArea().equalsIgnoreCase(area)) {
					fila[0] = PlanificacionEvento.getInstance().getLasPersonas().get(i).getCedula();
					fila[1] = PlanificacionEvento.getInstance().getLasPersonas().get(i).getNombre();
					fila[2] = PlanificacionEvento.getInstance().getLasPersonas().get(i).getSexo();
				    fila[3] = PlanificacionEvento.getInstance().getLasPersonas().get(i).getGradoAcademico();
				    fila[4] = ((Jurado)PlanificacionEvento.getInstance().getLasPersonas().get(i)).getArea();
				    fila[5] = PlanificacionEvento.getInstance().getLasPersonas().get(i).getTelefono();
				    fila[6] = PlanificacionEvento.getInstance().getLasPersonas().get(i).getDireccion();
				    model.addRow(fila);
					}}
				
				if(!sexo.equalsIgnoreCase("") && !grado.equalsIgnoreCase("") && !area.equalsIgnoreCase("")) {
					if(PlanificacionEvento.getInstance().getLasPersonas().get(i).getSexo().equalsIgnoreCase(sexo) &&PlanificacionEvento.getInstance().getLasPersonas().get(i).getGradoAcademico().equalsIgnoreCase(grado) &&((Jurado)PlanificacionEvento.getInstance().getLasPersonas().get(i)).getArea().equalsIgnoreCase(area)) {
						fila[0] = PlanificacionEvento.getInstance().getLasPersonas().get(i).getCedula();
				fila[1] = PlanificacionEvento.getInstance().getLasPersonas().get(i).getNombre();
				fila[2] = PlanificacionEvento.getInstance().getLasPersonas().get(i).getSexo();
			    fila[3] = PlanificacionEvento.getInstance().getLasPersonas().get(i).getGradoAcademico();
			    fila[4] = ((Jurado)PlanificacionEvento.getInstance().getLasPersonas().get(i)).getArea();
			    fila[5] = PlanificacionEvento.getInstance().getLasPersonas().get(i).getTelefono();
			    fila[6] = PlanificacionEvento.getInstance().getLasPersonas().get(i).getDireccion();
			    model.addRow(fila);
				}}
			}
		}
	}
}
