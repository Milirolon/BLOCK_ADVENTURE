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

public class Help2 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private JLabel lblFinn;
    private JLabel lblBloque1;
    private JLabel lblBloque2;
    private JLabel lblBloque3;
	
	private Timer timer;
	
	private int finnX = 310;
    private int finnY = 288;
    private int finnSpeed = 15;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Help2 frame = new Help2();
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
	public Help2() {
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1066, 855);
		setTitle("Block Adventure");
		contentPane = new JPanel() {
			@Override
            protected void paintComponent(Graphics g) { 
	            ImageIcon imgfondo = new ImageIcon(Help2.class.getResource("/imgsAyuda/ayuda2.png"));
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
				Help2.this.setVisible(false);
			}
		});
		btnBack.setIcon(new ImageIcon(Help2.class.getResource("/imgsAyuda/atras.png")));
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
				Help.main(new String[] {});
				Help2.this.setVisible(false);
			}
		});
		btnAnterior.setIcon(new ImageIcon(Help2.class.getResource("/imgsAyuda/izquierda.png")));
		btnAnterior.setBounds(38, 355, 80, 71);
		btnAnterior.setContentAreaFilled(false); 
		btnAnterior.setBorderPainted(false);
		contentPane.add(btnAnterior);
		
		JButton btnSiguiente = new JButton("");
		btnSiguiente.addMouseListener(new MouseAdapter() {
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
		btnSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Help3.main(new String[] {});
				Help2.this.setVisible(false);
			}
		});
		btnSiguiente.setIcon(new ImageIcon(Help2.class.getResource("/imgsAyuda/derecha.png")));
		btnSiguiente.setBounds(885, 355, 80, 71);
		btnSiguiente.setContentAreaFilled(false); 
		btnSiguiente.setBorderPainted(false);
		contentPane.add(btnSiguiente);

		lblFinn = new JLabel("");
		lblFinn.setIcon(new ImageIcon(Help2.class.getResource("/imgsAyuda/F_LATERAL DERECHA.png")));
		lblFinn.setBounds(310, 288, 65, 60);
		contentPane.add(lblFinn);
		
		lblBloque1 = new JLabel("");
		lblBloque1.setIcon(new ImageIcon(Help2.class.getResource("/Imagenes/bloqhiel.png")));
		lblBloque1.setBounds(479, 298, 50, 50);
		contentPane.add(lblBloque1);
		
		lblBloque2 = new JLabel("");
		lblBloque2.setIcon(new ImageIcon(Help2.class.getResource("/Imagenes/bloqhiel.png")));
		lblBloque2.setBounds(527, 298, 50, 50);
		contentPane.add(lblBloque2);
		
		lblBloque3 = new JLabel("");
		lblBloque3.setIcon(new ImageIcon(Help2.class.getResource("/Imagenes/bloqhiel.png")));
		lblBloque3.setBounds(576, 298, 50, 50);
		contentPane.add(lblBloque3);
		
		// MOVIMIENTO DE FINN ROMPER BOQUES
        timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Mover a Finn hacia la derecha
                finnX += finnSpeed;
                lblFinn.setBounds(finnX, finnY, 65, 60);
              //  lblFinn.setIcon(new ImageIcon(Help3.class.getResource("/interfaces/imgs/F_LATERAL DERECHA.png")));

                // Verificar colisiones con los bloques
                if (colision(lblFinn, lblBloque1) && lblBloque1.isVisible()) {
                    // Ocultar el bloque 1 al colisionar
                    lblBloque1.setVisible(false);
                }

                if (colision(lblFinn, lblBloque2) && lblBloque2.isVisible()) {
                    // Ocultar el bloque 2 al colisionar
                    lblBloque2.setVisible(false);
                }

                if (colision(lblFinn, lblBloque3) && lblBloque3.isVisible()) {
                    // Ocultar el bloque 3 al colisionar
                    lblBloque3.setVisible(false);
                }

                // Verificar si todos los bloques han sido destruidos
                if (!lblBloque1.isVisible() && !lblBloque2.isVisible() && !lblBloque3.isVisible()) {
                    // Detener el temporizador
                    lblFinn.setIcon(new ImageIcon(Help2.class.getResource("/imgsAyuda/finnFrent.gif")));
                    timer.stop();
                    reiniciar();
                }
            }
        });
        // Iniciar el temporizador REVISAR
        timer.start();
    }
	private void reiniciar() {
	    Timer reiniciarTimer = new Timer(2000, new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	finnX = 310;
	            finnY = 288;
	            lblFinn.setBounds(finnX, finnY, 65, 60);
	            lblFinn.setIcon(new ImageIcon(Help3.class.getResource("/imgsAyuda/F_LATERAL DERECHA.png")));

	            lblBloque1.setVisible(true);
	        	lblBloque2.setVisible(true);
	        	lblBloque3.setVisible(true);
	        	
	        	timer.start();;
	        }
	    });
	    reiniciarTimer.setRepeats(false); // Evitar que el temporizador se repita autom�ticamente
	    reiniciarTimer.start();
	} 

	// M�todo para verificar colisiones entre dos componentes
    private boolean colision(Component c1, Component c2) {
        Rectangle r1 = c1.getBounds();
        Rectangle r2 = c2.getBounds();
        return r1.intersects(r2);
    }   
}