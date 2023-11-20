package Game;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

public class Inicio extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inicio frame = new Inicio();
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
	public Inicio() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1066, 855);
		setTitle("Block Adventure");
		contentPane = new JPanel() {
			@Override
            protected void paintComponent(Graphics g) { // Funcion para establecer imagen de fondo de la ventana
	            ImageIcon imgfondo = new ImageIcon(Inicio.class.getResource("/imgsMenu/fondoinicio.png"));
                g.drawImage(imgfondo.getImage(), 0, 0, getWidth(), getHeight(), this);
                g.setColor(new Color(255, 255, 255, 0));
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnStart = new JButton("");
		btnStart.addMouseListener(new MouseAdapter() {
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
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Eligepj.main(new String[] {});
				Inicio.this.setVisible(false);
			}
		});
		btnStart.setIcon(new ImageIcon(Inicio.class.getResource("/imgsMenu/start.png")));
		btnStart.setBounds(656, 485, 189, 80);
		btnStart.setContentAreaFilled(false); // Tambi�n se puede usar esto para desactivar el relleno del contenido
		btnStart.setBorderPainted(false); // Desactiva el pintado del borde del bot�n
		contentPane.add(btnStart);
		
		//HACE QUE EL MOUSE CAMBIE POR LA MANITO
		JButton btnHelp = new JButton("");
		btnHelp.addMouseListener(new MouseAdapter() {
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
		btnHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Help.main(new String[] {});
				Inicio.this.setVisible(false);
			}
		});
		btnHelp.setIcon(new ImageIcon(Inicio.class.getResource("/imgsMenu/help.png")));
		btnHelp.setBounds(656, 576, 199, 89);
		btnHelp.setContentAreaFilled(false); // Tambi�n se puede usar esto para desactivar el relleno del contenido
		btnHelp.setBorderPainted(false); // Desactiva el pintado del borde del bot�n
		contentPane.add(btnHelp);
	}
}
