package domain;
import java.awt.Color;

public class LonelyTree extends Tree {
    private Forest forest;
    private int tictac;

    public LonelyTree(Forest forest, int row, int column) {
        super(forest, row, column);
        this.forest = forest;
        this.tictac = 0;
        color = new Color(0, 128, 0);
    }

        /**
     * Ejecuta un ciclo de comportamiento del objeto basado en el tiempo (tictac).
     * 
     * Incrementa el contador tictac en cada invocación. Cuando tictac % 4 == 1,
     * se incrementa la edad (years).
     * 
     * Cuando tictac % 4 == 3, primero verifica si existe compañía en la misma
     * fila o columna mediante hasCompanyInRowOrColumn():
     * si es así, el objeto muere. Si no, intenta moverse usando step();
     * si el movimiento falla, también muere.
     * 
     * Finalmente, actualiza el color del objeto llamando a updateColor().
     */
    @Override
    public void ticTac() {
        tictac++;
        if (tictac % 4 == 1) {
            years += 1;
        }
        if (tictac % 4 == 3) {
            if (hasCompanyInRowOrColumn()) {
                die();
                return;
            }
            boolean ok = step();
            if (!ok) {
                die();
                return;
            }
        }
        updateColor();
    }

    /**
     * Verifica si existe otro objeto del tipo Tree en la misma fila o columna.
     * 
     * Recorre todas las posiciones de la fila actual (excepto la propia) y luego
     * todas las posiciones de la columna actual (también excluyendo la propia).
     * 
     * Si encuentra al menos un objeto de tipo Tree en alguna de estas posiciones,
     * retorna true. En caso contrario, retorna false.
     * 
     * Este método se utiliza para detectar la presencia de compañía en línea recta
     * dentro del entorno.
     */
    private boolean hasCompanyInRowOrColumn() {
        for (int c = 0; c < forest.getSize(); c++) {
            if (c != column) {
                Thing t = forest.getThing(row, c);
                if (t instanceof Tree) return true;
            }
        }
        for (int r = 0; r < forest.getSize(); r++) {
            if (r != row) {
                Thing t = forest.getThing(r, column);
                if (t instanceof Tree) return true;
            }
        }
        return false;
    }

    /**
     * Actualiza el color del objeto según su nivel de energía.
     * 
     * Obtiene el valor de energía mediante getEnergy() y asigna un color
     * en función de rangos definidos. A mayor energía, el color es más claro;
     * a menor energía, el color es más oscuro.
     * 
     * Este método permite representar visualmente el estado de energía
     * del objeto a través de diferentes tonalidades.
     */
    private void updateColor() {
        int energy = getEnergy();
        if (energy >= 80) color = new Color(173, 216, 230);  
        else if (energy >= 60) color = new Color(100, 149, 237);
        else if (energy >= 40) color = new Color(123, 104, 238); 
        else if (energy >= 20) color = new Color(148, 0, 211);  
        else color = new Color(75, 0, 130);                       
    }

}