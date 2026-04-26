package Domain;

/**
 * Obstáculo: representa una pared fija en el tablero.
 * No puede moverse ni ser empujada. Bloquea el paso del personaje y las cajas.
 */
public class Obstaculo extends Objeto {

    public Obstaculo(int fila, int columna) {
        super(fila, columna, '#');
        this.movible = false;
    }

    @Override
    public char render() {
        return '#'; // Pared sólida
    }
}