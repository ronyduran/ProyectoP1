package visual;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.ImageIcon;

public class PanelImagen extends JPanel {

	/**
	 * Create the panel.
	 */
	public PanelImagen() {
		this.setSize(400, 280);
	}
	 @Override
	 public void paintComponent(Graphics g) {
	  Dimension tamanio = getSize();
	  ImageIcon imagenFondo = new ImageIcon(getClass().getResource("/Imagenes/fotoRed.png"));
	  g.drawImage(imagenFondo.getImage(), 0, 0, tamanio.width, tamanio.height, null);
	  setOpaque(false);
	  super.paintComponent(g);
	 }
}
