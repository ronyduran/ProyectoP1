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
	private String Codigo;

	
	
		public Grafica(){
	        setTitle("Estadisticas de los eventos");
	        setSize(800,600);
	        setLocationRelativeTo(null);
	        setDefaultCloseOperation(EXIT_ON_CLOSE);
	        setVisible(true);
	       Codigo="Evento-1";
	        init();
	        
	    }
	
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(5, 485, 914, 33);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
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
		
	
	
	
	
	private void init() {
		panel = new JPanel();
        getContentPane().add( panel);
        // Fuente de Datos     
        DefaultPieDataset data = new DefaultPieDataset();
        int hombre=PlanificacionEvento.getInstance().buscarGeneroPorEvento(Codigo)[0];
        int mujer=PlanificacionEvento.getInstance().buscarGeneroPorEvento(Codigo)[1];
        System.out.println(hombre);
        System.out.println(mujer);
        data.setValue("Mujeres", mujer);
        data.setValue("Hombres", hombre);
       
 
        // Creando el Grafico
        JFreeChart chart = ChartFactory.createPieChart(
         "Participacion por Genero", 
         data, 
         true, 
         true, 
         false);
        panel.setLayout(null);
        
     // Crear el Panel del Grafico con ChartPanel
	       ChartPanel chartPanel = new ChartPanel(chart);
	     
	       panel.add(chartPanel);
	}

}
