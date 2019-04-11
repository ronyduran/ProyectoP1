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

public class Graficas extends JFrame {

	private JPanel contentPane;
	JPanel panel;
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
		
		setTitle("Estadisticas de los eventos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
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
        
     // Crear el Panel del Grafico con ChartPanel
	       ChartPanel chartPanel = new ChartPanel(chart);
	       panel.add(chartPanel);
    }
	
	
}
