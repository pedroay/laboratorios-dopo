package Domain;

public class Piso extends Objeto {
	 
    public Piso(int fila, int columna) {
        super(fila, columna, ' ');
        this.movible = true;
    }
 
    @Override
    public char render() {
        return ' '; // Espacio vacío caminable
    }
}