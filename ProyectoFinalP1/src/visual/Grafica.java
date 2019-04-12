package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import logica.Evento;
import logica.PlanificacionEvento;

public class Grafica extends JDialog {

	private  JPanel contentPane;
	
	private JPanel panel;
	private JTextField txtBucarE;
	

	
	
		public Grafica(){
			setIconImage(Toolkit.getDefaultToolkit().getImage(Grafica.class.getResource("/Imagenes/Estadistica.png")));
			
			setTitle("Estad\u00EDsticas de los eventos");
			
			setBounds(100, 100, 523, 359);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(new BorderLayout(0, 0));
			
			JScrollPane scrollPane = new JScrollPane();
			contentPane.add(scrollPane);
			setLocationRelativeTo(null);
			
			init();
			
			

	        
		}
		
		
		
		
		
		
		private void Grafica01(String Codigo) {
			 // Fuente de Datos
	        DefaultPieDataset data = new DefaultPieDataset();
	        
	        int hombre=PlanificacionEvento.getInstance().buscarGeneroPorEvento(Codigo)[0];
	        int mujer=PlanificacionEvento.getInstance().buscarGeneroPorEvento(Codigo)[1];

	        data.setValue("Mujeres", mujer);
	        data.setValue("Hombres", hombre);
	        
	        	 
	        // Creando el Grafico
	        JFreeChart chart = ChartFactory.createPieChart(
	         "Participantes por género", 
	         data, 
	         true, 
	         true, 
	         false);
	 
	        // Mostrar Grafico
	        ChartFrame JDialog = new ChartFrame("Gáfica por género", chart);
	        
	        try {
				ChartUtilities.saveChartAsJPEG(new File("grafico por genero.jpg"), chart, 500, 500);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	     
	        JDialog.pack();
	        JDialog.setVisible(true);
	    
	    }
		
		private void Grafica02(String Codigo) {
			 // Fuente de Datos
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	        
	        int bachiller= PlanificacionEvento.getInstance().buscarGradoPorEvento(Codigo)[0];
	        int licenciado= PlanificacionEvento.getInstance().buscarGradoPorEvento(Codigo)[1];
	        int magister= PlanificacionEvento.getInstance().buscarGradoPorEvento(Codigo)[2];
	        int doctorado= PlanificacionEvento.getInstance().buscarGradoPorEvento(Codigo)[3];
	        

	           dataset.setValue(bachiller, "Bachiller", "Bachiller");
	           dataset.setValue(licenciado, "Licenciado", "Licenciado");
	           dataset.setValue(magister, "Magíster", "Magíaster");
	           dataset.setValue(doctorado, "Doctorado", "Doctorado");
	        
	        	 
	        // Creando el Grafico
	           JFreeChart chart = ChartFactory.createBarChart3D
	           ("Grado académico por participante","Genero", "Cantidad", 
	           dataset, PlotOrientation.VERTICAL, true,true, false);
	           chart.setBackgroundPaint(Color.gray);
	           chart.getTitle().setPaint(Color.black); 
	           CategoryPlot p = chart.getCategoryPlot(); 
	           p.setRangeGridlinePaint(Color.red); 
	 
	        // Mostrar Grafico
	        ChartFrame JDialog = new ChartFrame("Grado académico por participante", chart);
	        
	        try {
				ChartUtilities.saveChartAsJPEG(new File("grafico por grado.jpg"), chart, 500, 500);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	     
	        JDialog.pack();
	        JDialog.setVisible(true);
	    
	    }
		
		private void init() {
			
			panel = new JPanel();
	        getContentPane().add(panel);
	        panel.setLayout(null);
	        
			
			JPanel panel_1 = new JPanel();
		       panel_1.setBackground(new Color(176, 196, 222));
		       panel_1.setBounds(10, 11, 477, 60);
		       panel.add(panel_1);
		       panel_1.setLayout(null);
		       
		       JLabel lblCodigoEvento = new JLabel("Ingresar c\u00F3digo del evento:");
		       lblCodigoEvento.setFont(new Font("Tahoma", Font.PLAIN, 15));
		       lblCodigoEvento.setBounds(10, 15, 186, 22);
		       panel_1.add(lblCodigoEvento);
		       
		       JTextField textField = new JTextField();
		       textField.setBounds(191, 17, 116, 22);
		       panel_1.add(textField);
		       textField.setColumns(10);
		       
		       JButton btnBuscarEvento = new JButton("Buscar Evento");
		       btnBuscarEvento.addActionListener(new ActionListener() {
		       	public void actionPerformed(ActionEvent e) {
		       		
		       		String Codigo=textField.getText();
		       		if (!Codigo.equalsIgnoreCase("")) {
						Evento e1 = PlanificacionEvento.getInstance().BuscarEventoCodigo(Codigo);
						if(e1!=null) {
							System.out.println(1);
						}else {
							JOptionPane.showMessageDialog(null, "Este evento no existe", "Validación", JOptionPane.WARNING_MESSAGE);}
					}
		       	}
		       		
		       	      		
		          });
		       btnBuscarEvento.setFont(new Font("Tahoma", Font.PLAIN, 15));
		       btnBuscarEvento.setBounds(331, 15, 139, 23);
		       panel_1.add(btnBuscarEvento);
		       
		       JButton btnGrficaDeGenero = new JButton("Gr\u00E1fica de G\u00E9nero");
		       btnGrficaDeGenero.addActionListener(new ActionListener() {
		       	public void actionPerformed(ActionEvent e) {
		       		String Codigo=textField.getText();
		       		Grafica01(Codigo);
		       	}
		       });
		       btnGrficaDeGenero.setBounds(149, 123, 204, 52);
		       panel.add(btnGrficaDeGenero);
		       
		       JButton btnGrficoDelGrado = new JButton("Gr\u00E1fico del Grado Acad.");
		       btnGrficoDelGrado.addActionListener(new ActionListener() {
		       	public void actionPerformed(ActionEvent e) {
		       		String Codigo=textField.getText();
		       		Grafica02(Codigo);
		       	}
		       });
		       btnGrficoDelGrado.setBounds(149, 203, 204, 52);
		       panel.add(btnGrficoDelGrado);
		       
		       JPanel buttonPane = new JPanel();
				buttonPane.setBounds(0, 513, 922, 33);
				buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
				panel.add(buttonPane);
				{
					JButton okButton = new JButton("OK");
					okButton.setActionCommand("OK");
					buttonPane.add(okButton);
					getRootPane().setDefaultButton(okButton);
				}
				{
					JButton cancelButton = new JButton("Cancel");
					cancelButton.setActionCommand("Cancel");
					buttonPane.add(cancelButton);
				}

		}
		
	}