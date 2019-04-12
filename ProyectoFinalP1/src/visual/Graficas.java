package visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import logica.Participante;
import logica.PlanificacionEvento;

import javax.swing.JScrollPane;
import javax.swing.Scrollable;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Toolkit;

public class Graficas extends JFrame {

	private JPanel contentPane;
	JPanel panel;
	private JTextField textField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Graficas frame = new Graficas();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Graficas() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Graficas.class.getResource("/Imagenes/Estadistica.png")));
		
		setTitle("Estad\u00EDsticas de los eventos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 940, 562);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane);
		
		              
		       
		
		init();
	}
	
	private void init() {
        panel = new JPanel();
        getContentPane().add(panel);
        // Fuente de Datos
        
   
        
        DefaultPieDataset data = new DefaultPieDataset();
        
        data.setValue("Mujeres", 55 );
        data.setValue("Hombres", 45);
       
 
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
	       
	       textField = new JTextField();
	       textField.setBounds(385, 17, 116, 22);
	       panel_1.add(textField);
	       textField.setColumns(10);
	       
	       JButton btnBuscarEvento = new JButton("Buscar Evento");
	       btnBuscarEvento.setFont(new Font("Tahoma", Font.PLAIN, 15));
	       btnBuscarEvento.setBounds(522, 17, 139, 23);
	       panel_1.add(btnBuscarEvento);
	       
	       JButton btnGrficaDeGenero = new JButton("Gr\u00E1fica de G\u00E9nero");
	       btnGrficaDeGenero.setBounds(10, 220, 204, 52);
	       panel.add(btnGrficaDeGenero);
	       
	       JButton btnGrficoDelGrado = new JButton("Gr\u00E1fico del Grado Acad.");
	       btnGrficoDelGrado.setBounds(10, 300, 204, 52);
	       panel.add(btnGrficoDelGrado);
    }
}
