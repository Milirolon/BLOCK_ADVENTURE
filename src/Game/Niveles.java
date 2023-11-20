package Game;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;

public class Niveles extends JFrame {

	   private static final long serialVersionUID = 1L;
	    private JPanel contentPane;
	    public static int nivel = 0;

	    public static void main(String[] args) {
	        EventQueue.invokeLater(new Runnable() {
	            public void run() {
	                try {
	                    Niveles frame = new Niveles();
	                    frame.setVisible(true);
	                    frame.setLocationRelativeTo(null);
	                    frame.setResizable(false);
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }
	        });
	    }

	public Niveles() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1016, 850);
        contentPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                // Draw the background image
                ImageIcon imgfondo = new ImageIcon(Inicio.class.getResource("/imgsMenu/fondomenu.png"));
                g.drawImage(imgfondo.getImage(), 0, 0, getWidth(), getHeight(), this);
                g.setColor(new Color(255, 255, 255, 0));
                g.fillRect(0, 0, getWidth(), getHeight());

                // Draw the level images without transparency if unlocked
                drawImage(g, "/imgsMenu/nivel2.png", 372, 579, 186, 89, Gameplay.permitirNivel2);
                drawImage(g, "/imgsMenu/nivel3.png", 599, 579, 196, 89, Gameplay.permitirNivel3);
            }

            // Method to draw an image with or without transparency based on the unlock status
            private void drawImage(Graphics g, String imagePath, int x, int y, int width, int height, boolean unlocked) {
                if (unlocked) {
                    ImageIcon icon = new ImageIcon(Niveles.class.getResource(imagePath));
                    g.drawImage(icon.getImage(), x, y, width, height, this);
                } else {
                    Image img = createTransparentImage(imagePath);
                    g.drawImage(img, x, y, width, height, this);
                }
            }
        };

        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
		

		//LISTO
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
				Eligepj.main(new String[] {});
				Niveles.this.setVisible(false);
			}
		});
		btnBack.setIcon(new ImageIcon(Niveles.class.getResource("/imgsAyuda/atras.png")));
		btnBack.setBounds(10, 11, 155, 67);
		btnBack.setContentAreaFilled(false); // Tambi�n se puede usar esto para desactivar el relleno del contenido
		btnBack.setBorderPainted(false);
		contentPane.add(btnBack);
		
		JButton btnNivel1 = new JButton("");
		btnNivel1.addMouseListener(new MouseAdapter() {
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
		btnNivel1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nivel = 1;
				Main.main(new String[] {});
				Niveles.this.setVisible(false);
			}
		});
		btnNivel1.setIcon(new ImageIcon(Niveles.class.getResource("/imgsMenu/nivel1.png")));
		btnNivel1.setBounds(130, 579, 203, 89);
		btnNivel1.setContentAreaFilled(false); // Tambi�n se puede usar esto para desactivar el relleno del contenido
		btnNivel1.setBorderPainted(false);
		contentPane.add(btnNivel1);
		
		 JButton btnNivel2 = new JButton("");
	        btnNivel2.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseEntered(MouseEvent e) {
	                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	            }

	            @Override
	            public void mouseExited(MouseEvent e) {
	                setCursor(Cursor.getDefaultCursor());
	            }
	        });
	        btnNivel2.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                if (Gameplay.permitirNivel2) {
	                    nivel = 2;
	                    Main.main(new String[] {});
	                    Niveles.this.setVisible(false);
	                } else {
	                    System.out.println("Completa el nivel 1 primero o activa el nivel 2 desde otra clase.");
	                }
	            }
	        });

	        btnNivel2.setIcon(new ImageIcon(Niveles.class.getResource("/imgsMenu/nivel2.png")));
	        btnNivel2.setBounds(372, 579, 186, 89);
	        btnNivel2.setContentAreaFilled(false);
	        btnNivel2.setBorderPainted(false);
	        contentPane.add(btnNivel2);

	        JButton btnNivel3 = new JButton("");
	        btnNivel3.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseEntered(MouseEvent e) {
	                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	            }

	            @Override
	            public void mouseExited(MouseEvent e) {
	                setCursor(Cursor.getDefaultCursor());
	            }
	        });
	        btnNivel3.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                if (Gameplay.permitirNivel3) {
	                    nivel = 3;
	                    Main.main(new String[] {});
	                    Niveles.this.setVisible(false);
	                } else {
	                    System.out.println("Completa el nivel 2 primero o activa el nivel 3 desde otra clase.");
	                }
	            }
	        });

	        if (Gameplay.permitirNivel2) {
	            btnNivel2.setIcon(new ImageIcon(Niveles.class.getResource("/imgsMenu/nivel2.png")));
	        } else {
	            // Si no, mostrar la imagen con transparencia
	            btnNivel2.setIcon(new ImageIcon(createTransparentImage("/imgsMenu/nivel2.png")));
	        }

	        btnNivel2.setBounds(372, 579, 186, 89);
	        btnNivel2.setContentAreaFilled(false);
	        btnNivel2.setBorderPainted(false);
	        contentPane.add(btnNivel2);
	        // Si se permite el nivel 3, mostrar la imagen sin transparencia
	        if (Gameplay.permitirNivel3) {
	            btnNivel3.setIcon(new ImageIcon(Niveles.class.getResource("/imgsMenu/nivel3.png")));
	        } else {
	            // Si no, mostrar la imagen con transparencia
	            btnNivel3.setIcon(new ImageIcon(createTransparentImage("/imgsMenu/nivel3.png")));
	        }

	        btnNivel3.setBounds(599, 579, 196, 89);
	        btnNivel3.setContentAreaFilled(false);
	        btnNivel3.setBorderPainted(false);
	        contentPane.add(btnNivel3);
	}
	private Image createTransparentImage(String imagePath) {
        ImageIcon icon = new ImageIcon(Niveles.class.getResource(imagePath));
        Image img = icon.getImage();
        BufferedImage bufferedImage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.setComposite(AlphaComposite.SrcOver.derive(0.5f));
        g2d.drawImage(img, 0, 0, null);
        g2d.dispose();
        return bufferedImage;
    }

}