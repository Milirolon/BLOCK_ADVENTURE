package Game;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class test extends JFrame {

    public test() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                // Dibuja el texto
                g.setColor(Color.BLACK);
                g.drawString("Texto a señalar", 50, 100);
            }
        };

        // Agregar un MouseListener al panel
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // Obtén las coordenadas del mouse
                int mouseX = e.getX();
                int mouseY = e.getY();

                // Coordenadas del área del texto
                int textX = 50;
                int textY = 100 - panel.getFontMetrics(panel.getFont()).getAscent(); // Ajusta la posición vertical según la fuente

                // Dimensiones del área del texto
                int textWidth = panel.getFontMetrics(panel.getFont()).stringWidth("Texto a señalar");
                int textHeight = panel.getFontMetrics(panel.getFont()).getHeight();

                // Verifica si el mouse está sobre el área del texto
                if (mouseX >= textX && mouseX <= textX + textWidth && mouseY >= textY && mouseY <= textY + textHeight) {
                    System.out.println("AAAAAAAAAAAAAAAAAA");
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Acciones a realizar cuando el mouse sale del área del panel
                System.out.println("Mouse salió del área del texto");
            }
        });

        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(test::new);
    }
}
