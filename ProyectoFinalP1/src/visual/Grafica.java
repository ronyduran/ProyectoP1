package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
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
			
			setBounds(100, 100, 948, 595);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(new BorderLayout(0, 0));
			
			JScrollPane scrollPane = new JScrollPane();
			contentPane.add(scrollPane);
			setLocationRelativeTo(null);
			
			init("");
			
		


		}
		
		private void init(String Codigo) {
	        panel = new JPanel();
	        getContentPane().add(panel);
	        // Fuente de Datos
	        
	        DefaultPieDataset data = new DefaultPieDataset();
	        int hombre=0;
	        int mujer=0;
	        if(!Codigo.equalsIgnoreCase("")) {
	        hombre=PlanificacionEvento.getInstance().buscarGeneroPorEvento(Codigo)[0];
	         mujer=PlanificacionEvento.getInstance().buscarGeneroPorEvento(Codigo)[1];}
	        else {
	        	
	        	 hombre=PlanificacionEvento.getInstance().buscarGeneroPorEventoGeneral()[0];
		         mujer=PlanificacionEvento.getInstance().buscarGeneroPorEventoGeneral()[1];
	        	
	        }
	        System.out.println(hombre);
	        System.out.println(mujer);
	        data.setValue("Mujeres", mujer);
	        data.setValue("Hombres", hombre);
	       
	 
	        // Creando el Grafico
	        JFreeChart chart = ChartFactory.createPieChart(
	         "Cantidad de participantes por sexo", 
	         data, 
	         true, 
	         true, 
	         false);
		       panel.setLayout(null);
	        
	     // Crear el Panel del Grafico con ChartPanel
		       ChartPanel chartPanel = new ChartPanel(chart);
		       chartPanel.setBounds(224, 82, 680, 420);
		       panel.add(chartPanel);
		       
		       JPanel panel_1 = new JPanel();
		       panel_1.setBackground(new Color(176, 196, 222));
		       panel_1.setBounds(10, 11, 894, 60);
		       panel.add(panel_1);
		       panel_1.setLayout(null);
		       
		       JLabel lblCodigoEvento = new JLabel("Ingresar c\u00F3digo del evento:");
		       lblCodigoEvento.setFont(new Font("Tahoma", Font.PLAIN, 15));
		       lblCodigoEvento.setBounds(195, 17, 186, 22);
		       panel_1.add(lblCodigoEvento);
		       
		       JTextField textField = new JTextField();
		       textField.setBounds(385, 17, 116, 22);
		       panel_1.add(textField);
		       textField.setColumns(10);
		       
		       JButton btnBuscarEvento = new JButton("Buscar Evento");
		       btnBuscarEvento.addActionListener(new ActionListener() {
		       	public void actionPerformed(ActionEvent e) {
		       		
		       		String Codigo=textField.getText();
		       		
		       		init(Codigo);
		       		
		       	
		       	}
		       });
		       btnBuscarEvento.setFont(new Font("Tahoma", Font.PLAIN, 15));
		       btnBuscarEvento.setBounds(522, 17, 139, 23);
		       panel_1.add(btnBuscarEvento);
		       
		       JButton btnGrficaDeGenero = new JButton("Gr\u00E1fica de G\u00E9nero");
		       btnGrficaDeGenero.setBounds(10, 220, 204, 52);
		       panel.add(btnGrficaDeGenero);
		       
		       JButton btnGrficoDelGrado = new JButton("Gr\u00E1fico del Grado Acad.");
		       btnGrficoDelGrado.setBounds(10, 300, 204, 52);
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