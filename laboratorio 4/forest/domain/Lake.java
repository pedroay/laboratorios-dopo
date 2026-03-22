package domain;
import java.awt.Color;

public class Lake implements Thing {
    private Forest forest;
    private int row, column;
    private Color color;
    private int tictac;

    public Lake(Forest forest, int row, int column) {
        this.forest = forest;
        this.row = row;
        this.column = column;
        this.tictac = 0;
        this.color = new Color(0, 105, 148);
        for (int r = row; r < row + 2; r++) {
            for (int c = column; c < column + 2; c++) {
                if (forest.inForest(r, c)) {
                    forest.setThing(r, c, this);
                }
            }
        }
    }
    
    /**
     * Ejecuta un ciclo básico de actualización del objeto.
     * 
     * Incrementa el contador tictac y actualiza el color mediante
     * el método updateColor().
     * 
     * A diferencia de otras implementaciones, este método no realiza
     * movimiento, reproducción ni cambios en la edad.
     */
    @Override
    public void ticTac() {
        tictac++;
        updateColor();
    }
    
    /**
     * Actualiza el color del objeto de forma cíclica según el valor de tictac.
     * 
     * El color cambia cada vez que se invoca el método, siguiendo un ciclo de 4 estados
     * basado en el residuo de tictac % 4. Cada estado corresponde a una tonalidad distinta
     * de color.
     * 
     * Este método permite representar visualmente el paso del tiempo o el estado
     * del objeto mediante variaciones de color.
     */
    private void updateColor() {
        color = switch (tictac % 4) {
            case 0 -> new Color(0, 105, 148);
            case 1 -> new Color(0, 140, 180);
            case 2 -> new Color(64, 164, 223);
            default -> new Color(0, 120, 160);
        };
    }

    @Override
    public Color getColor() { return color; }

    @Override
    public int shape() { return Thing.SQUARE; }

    @Override
    public boolean isOnlyThing() { return true; }

    public int getRow() { return row; }
    public int getColumn() { return column; }
}