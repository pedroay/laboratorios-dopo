package Domain;

/**
 * Personaje: el jugador controlado por el usuario.
 * Puede moverse en las 4 direcciones y empujar cajas.
 */
public class Personaje extends Objeto {
    private int movimientos; // contador de movimientos realizados

    public Personaje(int fila, int columna) {
        super(fila, columna, '@');
        this.movimientos = 0;
        this.movible = true;
    }

    public int getMovimientos() { return movimientos; }

    /**
     * Mueve el personaje a una nueva posición e incrementa el contador.
     */
    public void mover(int nuevaFila, int nuevaColumna) {
        this.fila = nuevaFila;
        this.columna = nuevaColumna;
        movimientos++;
    }

    @Override
    public char render() {
        return '@';
    }
}