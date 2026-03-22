package domain;
import java.awt.Color;

public class Shadow {
    private Forest forest;
    private int row;
    private Color color;

    public Shadow(Forest forest, int row) {
        this.forest = forest;
        this.row = row;
        this.color = new Color(0, 0, 0, 50); 
        forest.addShadow(this);
    }

    public void ticTac() {
        this.row = (this.row - 1 + forest.getSize()) % forest.getSize();
    }

    public Color getColor() {
        return Color.BLACK;
    }

    public int getRow() {
        return row;
    }
}