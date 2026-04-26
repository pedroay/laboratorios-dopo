package Domain;

/**
 * Caja: objeto movible que el personaje puede empujar.
 * El objetivo del juego es llevar todas las cajas a las casillas destino (PisoMeta).
 */
public class Caja extends Objeto {
    private boolean enMeta; // true si la caja está sobre una casilla destino

    public Caja(int fila, int columna) {
        super(fila, columna, '$');
        this.enMeta = false;
        this.movible = true;
    }

    public boolean isEnMeta() { return enMeta; }
    public void setEnMeta(boolean enMeta) { this.enMeta = enMeta; }

    /**
     * Mueve la caja a una nueva posición.
     */
    public void mover(int nuevaFila, int nuevaColumna) {
        this.fila = nuevaFila;
        this.columna = nuevaColumna;
    }

    @Override
    public char render() {
        return enMeta ? '*' : '$'; // '*' si está en meta, '$' si no
    }
}