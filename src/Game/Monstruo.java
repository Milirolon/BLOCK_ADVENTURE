package Game;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.List;

public class Monstruo {
	private Image imagen;
    private int x;
    private int y;
    private int velocidad =2;
    private int velocidadX;
    private int velocidadY;
    private int ancho;
    private int alto;

    public Monstruo(int x, int y) {
        this.x = x;
        this.y = y;
        this.velocidadX = 2;
        this.velocidadY = 2;
        this.ancho = 50; // Ancho de monstruo
        this.alto = 40; // Alto de monstruo
    }

    public void moverMonstruoConColision(List<Bloque> bloques, int pantallaAncho, int pantallaAlto) {
        int nuevaX = x + velocidadX;
        int nuevaY = y + velocidadY;

        // Verifica los limites de la pantalla
        if (nuevaX < 50 || nuevaX > 1000 - ancho || nuevaY < 115 || nuevaY > 766 - alto) {
            cambiarDireccion();
            return;
        }

        Rectangle nuevaAreaMonstruo = new Rectangle(nuevaX, nuevaY, ancho, alto);

        for (Bloque bloque : bloques) {
            Rectangle areaBloque = new Rectangle(bloque.getX(), bloque.getY(), bloque.getAncho(), bloque.getAlto());

            if (nuevaAreaMonstruo.intersects(areaBloque)) {
                cambiarDireccion();
                return;
            }
        }

        x = nuevaX;
        y = nuevaY;
    }

    private void cambiarDireccion() {
        // Cambia la direccion del monstruo al azar
        int direccion = (int) (Math.random() * 4);

        switch (direccion) {
            case 0:
                velocidadX = velocidad;
                velocidadY = 0;
                break;
            case 1:
                velocidadX = -velocidad;
                velocidadY = 0;
                break;
            case 2:
                velocidadX = 0;
                velocidadY = velocidad;
                break;
            case 3:
                velocidadX = 0;
                velocidadY = -velocidad;
                break;
        }
    }

    public Image getImagen() {
        return imagen;
    }

    public void setImagen(Image imagen) {
        this.imagen = imagen;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
    public int getAncho() {
        return ancho;
    }
    public int getAlto() {
        return alto;
    }

    public Rectangle obtenerAreaMonstruo() {
        return new Rectangle(x, y, ancho, alto);
    }
}

