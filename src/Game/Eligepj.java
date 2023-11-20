package Game;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

public class Eligepj extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static int PJvalor=0;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Eligepj frame = new Eligepj();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Eligepj() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1066, 855);
		setTitle("Block Adventure");
		contentPane = new JPanel() {
			@Override
            protected void paintComponent(Graphics g) { 
	            ImageIcon imgfondo = new ImageIcon(Inicio.class.getResource("/imgsMenu/fondostart.png"));
                g.drawImage(imgfondo.getImage(), 0, 0, getWidth(), getHeight(), this);
                g.setColor(new Color(255, 255, 255, 0));
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);  
		
		JButton btnFinn = new JButton("");
		btnFinn.addMouseListener(new MouseAdapter() {
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
		btnFinn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PJvalor=1;
				Niveles.main(new String[] {});
				Eligepj.this.setVisible(false);
			}
		});
		btnFinn.setBounds(304, 182, 207, 188);
		btnFinn.setContentAreaFilled(false); // Tambi�n se puede usar esto para desactivar el relleno del contenido
		btnFinn.setBorderPainted(false); // Desactiva el pintado del borde del bot�n
		btnFinn.setBackground(new Color(0, 0, 0, 0)); // 0, 0, 0 son los componentes RGB del color (negro en este caso), y el �ltimo 0 es la opacidad
		contentPane.add(btnFinn);
		
		JButton btnJake = new JButton("");
		btnJake.addMouseListener(new MouseAdapter() {
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
		btnJake.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PJvalor=2;
				Niveles.main(new String[] {});
				Eligepj.this.setVisible(false);
			}
		});
		btnJake.setBounds(547, 332, 219, 188);
		btnJake.setContentAreaFilled(false); // Tambi�n se puede usar esto para desactivar el relleno del contenido
		btnJake.setBorderPainted(false); // Desactiva el pintado del borde del bot�n
		btnJake.setBackground(new Color(0, 0, 0, 0));
		contentPane.add(btnJake);
		
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
				Eligepj.this.setVisible(false);
			}
		});
		btnBack.setIcon(new ImageIcon(Eligepj.class.getResource("/imgsAyuda/atras.png")));
		btnBack.setBounds(440, 639, 147, 64);
		btnBack.setContentAreaFilled(false); 
		btnBack.setBorderPainted(false);
		contentPane.add(btnBack);
	}
}