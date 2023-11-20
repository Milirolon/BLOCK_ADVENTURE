package Game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

public class Help extends JFrame {

	private JPanel contentPane;
	 private Timer timer;
	    private JLabel lblFinn;
	    private JLabel lblArrow;
	    private int finnX = 476;
	    private int finnY = 287;
	    private int finnSpeed = 5;
	    private int currentDirection = 0; // 0: Frente, 1: Izquierda, 2: Derecha, 3: Atr�s


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Help frame = new Help();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Help() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1066, 855);
		setTitle("Block Adventure");
		contentPane = new JPanel() {
			@Override
            protected void paintComponent(Graphics g) { 
	            ImageIcon imgfondo = new ImageIcon(Inicio.class.getResource("/imgsAyuda/ayuda1.png"));
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
				Help.this.setVisible(false);
			}
		});
		btnBack.setIcon(new ImageIcon(Help.class.getResource("/imgsAyuda/atras.png")));
		btnBack.setBounds(450, 700, 148, 65);
		btnBack.setContentAreaFilled(false); 
		btnBack.setBorderPainted(false);
		contentPane.add(btnBack);
		
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
				Help2.main(new String[] {});
				Help.this.setVisible(false);
			}
		});
		btnSiguiente.setIcon(new ImageIcon(Help.class.getResource("/imgsAyuda/derecha.png")));
		btnSiguiente.setBounds(885, 355, 80, 71);
		btnSiguiente.setContentAreaFilled(false); 
		btnSiguiente.setBorderPainted(false);
		contentPane.add(btnSiguiente);

		lblFinn = new JLabel("");
		lblFinn.setIcon(new ImageIcon(Help.class.getResource("/imgsAyuda/finnFrent.gif")));
		lblFinn.setBounds(finnX, finnY, 65, 60);
		contentPane.add(lblFinn);
		
		lblArrow = new JLabel("");
        lblArrow.setBounds(0, 0, 70, 65); // Ajusta el tama�o seg�n tus necesidades
        contentPane.add(lblArrow);

        // Configurar el temporizador para cambiar autom�ticamente la direcci�n de Finn y su imagen
        timer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cambiar la direcci�n de Finn autom�ticamente
                currentDirection = (currentDirection + 1) % 4;

                // Cambiar la imagen de Finn autom�ticamente seg�n la direcci�n
                cambiarImagenFinn();

                // Mostrar la flecha solo cuando cambia de direcci�n
                mostrarFlecha();
            }
        });

        // Iniciar el temporizador
        timer.start();
    }

    // M�todo para mover a Finn autom�ticamente en la direcci�n actual
    private void moverFinn() {
        switch (currentDirection) {
            case 0: // Frente
                finnY -= finnSpeed;
                break;
            case 1: // Izquierda
                finnX -= finnSpeed;
                break;
            case 2: // Derecha
                finnX += finnSpeed;
                break;
            case 3: // Atr�s
                finnY += finnSpeed;
                break;
        }

        // Actualizar la posici�n de Finn
        lblFinn.setBounds(finnX, finnY, 65, 60);
    }

    // M�todo para cambiar la imagen de Finn autom�ticamente seg�n la direcci�n
    private void cambiarImagenFinn() {
        switch (currentDirection) {
            case 0: // Frente
                lblFinn.setIcon(new ImageIcon(Help.class.getResource("/imgsAyuda/F_DETRAS.png")));
                break;
            case 1: // Izquierda
                lblFinn.setIcon(new ImageIcon(Help.class.getResource("/imgsAyuda/F_IZQUIERDA.png")));
                break;
            case 2: // Derecha
                lblFinn.setIcon(new ImageIcon(Help.class.getResource("/imgsAyuda/F_LATERAL DERECHA.png")));
                break;
            case 3: // Atr�s
                lblFinn.setIcon(new ImageIcon(Help.class.getResource("/imgsAyuda/finnFrent.gif")));
                break;
        }
    }

    // M�todo para mostrar la flecha solo cuando cambia de direcci�n
    private void mostrarFlecha() {
        switch (currentDirection) {
            case 0: // Frente
                lblArrow.setIcon(new ImageIcon(Help.class.getResource("/imgsAyuda/flecha2.png")));
                lblArrow.setBounds(476,114, 70, 65);
                break;
            case 1: // Izquierda
                lblArrow.setIcon(new ImageIcon(Help.class.getResource("/imgsAyuda/flecha1.png")));
                lblArrow.setBounds(250, 291, 75, 65);
                break;
            case 2: // Derecha
                lblArrow.setIcon(new ImageIcon(Help.class.getResource("/imgsAyuda/flecha3.png")));
                lblArrow.setBounds(662, 287, 75, 65);
                break;
            case 3: // Atr�s
                lblArrow.setIcon(new ImageIcon(Help.class.getResource("/imgsAyuda/flecha4.png")));
                lblArrow.setBounds(466, 463, 70, 65);
                break;
        }

        // Muestra la flecha durante un breve per�odo de tiempo (ajustable)
        Timer arrowTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lblArrow.setIcon(null); // Oculta la flecha despu�s de un segundo
            }
        });
        arrowTimer.setRepeats(false); // Evita que el temporizador se repita autom�ticamente
        arrowTimer.start();
	}
}