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

    private void updateColor() {
        int energy = getEnergy();
        if (energy >= 80) color = new Color(173, 216, 230);  
        else if (energy >= 60) color = new Color(100, 149, 237);
        else if (energy >= 40) color = new Color(123, 104, 238); 
        else if (energy >= 20) color = new Color(148, 0, 211);  
        else color = new Color(75, 0, 130);                       
    }

}