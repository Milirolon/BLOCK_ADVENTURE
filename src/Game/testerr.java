package Game;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class testerr extends JPanel {

	/**
	 * Create the panel.
	 */
	public testerr() {

	}

	
	@Override
	public void paint(Graphics g) {
	    super.paint(g);

	    // Tama�o de cada bloque y espaciado
	    int bloqueAncho = 50;
	    int bloqueAlto = 20;
	    int espaciado = 10;

	    int xInicial = 50; // Posici�n inicial de los bloques

	    for (int i = 0; i < 5; i++) {
	        // Dibuja un bloque
	        g.setColor(Color.BLUE);
	        g.fillRect(xInicial, 50, bloqueAncho, bloqueAlto);

	        // Dibuja una l�nea entre los bloques
	        g.setColor(Color.BLACK);
	        g.drawLine(xInicial + bloqueAncho, 50, xInicial + bloqueAncho, 50 + bloqueAlto);

	        // Ajusta la posici�n inicial para el siguiente bloque
	        xInicial += bloqueAncho + espaciado;
	    }
	}
}
