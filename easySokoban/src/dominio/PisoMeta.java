package dominio;

public class PisoMeta extends Piso {
	 
    public PisoMeta(int fila, int columna) {
        super(fila, columna);
        this.simbolo = '.';
    }
 
    @Override
    public char render() {
        return '.'; // Punto que indica casilla destino vacía
    }
}
 