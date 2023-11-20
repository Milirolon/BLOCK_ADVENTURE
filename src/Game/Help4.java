package Game;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

public class Help4 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private Timer timer;

	private JLabel lblFinn;
	private JLabel lblPinguino;
		
	private int pingX = 310;
	private int pingY = 283;
	private int pingSpeed = 15;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Help4 frame = new Help4();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Help4() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1066, 855);
		setTitle("Block Adventure");
		contentPane = new JPanel() {
			@Override
            protected void paintComponent(Graphics g) { 
	            ImageIcon imgfondo = new ImageIcon(Help4.class.getResource("/imgsAyuda/ayuda4.png"));
                g.drawImage(imgfondo.getImage(), 0, 0, getWidth(), getHeight(), this);
                g.setColor(new Color(255, 255, 255, 0));
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnBack = new JButton("");
		btnBack.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // Cambiar el cursor cuando el mouse entra en el bot�n
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Restaurar el cursor cuando el mouse sale del bot�n
                setCursor(Cursor.getDefaultCursor());
            }
        });
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Inicio.main(new String[] {});
				Help4.this.setVisible(false);
			}
		});
		btnBack.setIcon(new ImageIcon(Help3.class.getResource("/imgsAyuda/atras.png")));
		btnBack.setBounds(450, 700, 148, 65);
		btnBack.setContentAreaFilled(false); 
		btnBack.setBorderPainted(false);
		contentPane.add(btnBack);
		
		JButton btnAnterior = new JButton("");
		btnAnterior.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // Cambiar el cursor cuando el mouse entra en el bot�n
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Restaurar el cursor cuando el mouse sale del bot�n
                setCursor(Cursor.getDefaultCursor());
            }
        });
		btnAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Help3.main(new String[] {});
				Help4.this.setVisible(false);
			}
		});
		btnAnterior.setIcon(new ImageIcon(Help3.class.getResource("/imgsAyuda/izquierda.png")));
		btnAnterior.setBounds(38, 355, 80, 71);
		btnAnterior.setContentAreaFilled(false); 
		btnAnterior.setBorderPainted(false);
		contentPane.add(btnAnterior);

		lblFinn = new JLabel("");
		lblFinn.setIcon(new ImageIcon(Help4.class.getResource("/imgsAyuda/finnFrent.gif")));
		lblFinn.setBounds(527, 288, 65, 60);
		contentPane.add(lblFinn);
		
		lblPinguino = new JLabel("");
		lblPinguino.setIcon(new ImageIcon(Help4.class.getResource("/imgsAyuda/P_FRENTE.gif")));
		lblPinguino.setBounds(310, 283, 65, 65);
		contentPane.add(lblPinguino);
		
		// Configurar el temporizador para mover al pinguino autom�ticamente
        timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	pingX += pingSpeed;
                lblPinguino.setBounds(pingX, pingY, 65, 60);
                lblPinguino.setIcon(new ImageIcon(Help3.class.getResource("/imgsAyuda/P_LATERAL DERECHA.png")));
                
                if (colision(lblPinguino, lblFinn) && lblFinn.isVisible()) {
                    // Cambiar la imagen del ping�ino a frente
                    lblPinguino.setIcon(new ImageIcon(Help4.class.getResource("/imgsAyuda/P_FRENTE.gif")));
                    lblFinn.setVisible(false);
                    timer.stop();
                    // Reiniciar el juego despu�s de un breve per�odo de tiempo
                    reiniciarDespuesDeColision();
                }
            }
        });
        // Iniciar el temporizador
        timer.start();
    }

	// M�todo para reiniciar el juego despu�s de la colisi�n con el ping�ino
	private void reiniciarDespuesDeColision() {
	    Timer reiniciarTimer = new Timer(2000, new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            // Restaurar la posici�n del ping�ino y cambiar la imagen
	            pingX = 310;
	            pingY = 283;
	            lblPinguino.setBounds(pingX, pingY, 65, 60);
	            lblPinguino.setIcon(new ImageIcon(Help3.class.getResource("/imgsAyuda/P_LATERAL DERECHA.png")));

	            // Restaurar la posici�n de Finn
	            lblFinn.setVisible(true);
	            lblFinn.setLocation(527, 288);

	            // Reiniciar el temporizador
	            timer.start();
	        }
	    });
	    reiniciarTimer.setRepeats(false); // Evitar que el temporizador se repita autom�ticamente
	    reiniciarTimer.start();
	}
    
    private boolean colision(Component c1, Component c2) {
        Rectangle r1 = c1.getBounds();
        Rectangle r2 = c2.getBounds();
        return r1.intersects(r2);
    }
}