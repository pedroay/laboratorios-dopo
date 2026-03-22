package domain;

import java.awt.Color;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class Squirrel extends LivingThing implements Thing {
    private Forest forest;
    private int row, column;
    private Color color;
    private int tictac;
    private Random rand;
    
    public Squirrel(Forest forest, int row, int column) {
        this.forest = forest;
        this.row = row;
        this.column = column;
        this.color = new Color(139, 69, 19); 
        this.tictac = 0;
        this.forest.setThing(row, column, this);
        this.rand = new Random();
    } 
    

    
        public void ticTac() {
        tictac++;
        actualizarColor();
        int proximaR = nr();
        int proximaC = nc();
        if (forest.getThing(proximaR, proximaC) == null) {
            forest.setThing(this.row, this.column, null);
            this.row = proximaR;
            this.column = proximaC;
        } 
        if (tictac % 4 == 1) {
            years += 1;
        }
        reproduce();
        
        if (years < 10) {
            forest.setThing(this.row, this.column, this);
        } else {
            die();
        }
    }
    
    private void actualizarColor() {
    this.color = switch (years) {
        case 0, 1 -> new Color(139, 69, 19);
        case 2    -> new Color(152, 90, 17);
        case 3    -> new Color(165, 110, 15);
        case 4    -> new Color(178, 131, 13);
        case 5    -> new Color(191, 152, 11);
        case 6    -> new Color(203, 172, 8);
        case 7    -> new Color(216, 193, 6);
        case 8    -> new Color(229, 214, 4);
        case 9    -> new Color(242, 234, 2);
        default   -> Color.YELLOW;
    };
}
    
    private int nr(){
        int r = rand.nextInt(3)-1;
        int nr;
        int mr;
        if (this.row + r < 25 && this.row + r >= 0){
            nr = r + this.row;
            mr = nr;
        }
        else{
            mr = this.row;
        }
        return mr;
    }
    
    private int nc(){
        int c = rand.nextInt(3)-1;
        int nc;
        int mc;
        if (this.column + c < 25 && this.column + c >= 0){
            nc = c + this.column;
            mc= nc;
        }
        else{
            mc = this.column;
        }
        return mc;
    }
    
        private void reproduce() {
        // Definimos las 8 direcciones posibles: N, S, E, O y las 4 diagonales
        int[][] directions = {
            {-1, 0}, {1, 0}, {0, -1}, {0, 1},   // Arriba, Abajo, Izquierda, Derecha
            {-1, -1}, {-1, 1}, {1, -1}, {1, 1}  // Diagonales
        };
    
        for (int[] d : directions) {
            // Celda intermedia (a 1 de distancia)
            int midR = this.row + d[0];
            int midC = this.column + d[1];
            
            // Celda objetivo donde buscamos a la pareja (a 2 de distancia)
            int targetR = this.row + 2 * d[0];
            int targetC = this.column + 2 * d[1];
    
            // 1. Verificar que las coordenadas estén dentro del bosque
            if (forest.inForest(targetR, targetC)) {
                Thing partner = forest.getThing(targetR, targetC);
                
                // 2. Condición: Hay otra ardilla a distancia 2 Y el medio está vacío
                if (partner instanceof Squirrel && forest.getThing(midR, midC) == null) {
                    // 3. Nace la nueva ardilla en la celda intermedia
                    new Squirrel(this.forest, midR, midC);
                    
                    // Opcional: break para que solo nazca una ardilla por turno
                    break; 
                }
            }
        }
    }
        /**Returns the row
        @return 
         */
        public final int getRow(){
            return row;
        }

    /**Returns the column
    @return 
     */
    public final int getColumn(){
        return column;
    }

    
    /**Returns the color
    @return 
     */
    public final Color getColor(){
        return color;
    }

    /**Returns the shape
    @return 
     */
    public final int shape(){
        return Thing.ROUND;
    }
    
        /**Die
     */
    public void die(){
        forest.setThing(row, column,null);
    }
}
