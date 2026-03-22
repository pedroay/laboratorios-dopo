package domain;
import java.awt.Color;

public class Shadow {
    private Forest forest;
    private int row; // Solo necesita la fila, se extiende por todo el ancho
    private Color color;

    public Shadow(Forest forest, int row) {
        this.forest = forest;
        this.row = row;
        // Negro con transparencia (50/255) para que se vea lo de abajo
        this.color = new Color(0, 0, 0, 50); 
    }

    public void ticTac() {
        // (ii) Desplazamiento Sur a Norte (r - 1) circularmente.
        // Sumamos SIZE para evitar números negativos antes del módulo.
        this.row = (this.row - 1 + forest.getSize()) % forest.getSize();
        
        // NO hay validación de colisión. La sombra avanza SIEMPRE.
    }

    public Color getColor() {
        return color;
    }

    public int getRow() {
        return row;
    }
}