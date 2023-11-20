package Game;

class Bloque {

	int x, y; // Posici�n del bloque
    int ancho, alto; // Dimensiones del bloque
    boolean visible; // Indica si el bloque debe ser dibujado

    public Bloque(int x, int y, int ancho, int alto) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
    }
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getAncho() {
        return ancho;
    }

    public int getAlto() {
        return alto;
    }
	
}
