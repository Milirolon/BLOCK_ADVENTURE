package Game;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Main {
	final static int ancho = 1066;
	final static int alto = 855;
	
	public Main() {
		JFrame ventana = new JFrame();
		ventana.setTitle("Block Adventure");
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setResizable(false);
		ventana.setSize(ancho, alto);
		ventana.setBounds(0, 0, ancho, alto);
		ventana.setVisible(true);
		ventana.setLocationRelativeTo(null);
		
		
		Gameplay panel = new Gameplay();
        ventana.getContentPane().add(panel);
	}
	
	public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Main();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
