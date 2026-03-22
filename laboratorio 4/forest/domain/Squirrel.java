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
    

    
    public void ticTac(){
        tictac++;
        color=( years == 0  ? color = new Color(139,69,19):
                years == 1? color = new Color(139,69,19):
               years == 2? color = new Color(152, 90, 17):
               years == 3? color = new Color(165, 110, 15):
               years == 4? color = new Color(178, 131, 13):
               years == 5? color = new Color(191, 152, 11):
               years == 6? color = new Color(203, 172, 8):
               years == 7? color = new Color(216, 193, 6):
               years == 8? color = new Color(229, 214, 4):
               years == 9? color = new Color(242, 234, 2):
               Color.YELLOW);
        int nr = nr();
        int nc = nc();
        if (tictac % 4 == 1){
            years+=1;
        }
        forest.setThing(row, column, null); // Borrar de posición vieja
        this.row = nr;      // ¡IMPORTANTE! Actualizar coordenada interna
        this.column = nc;
        reproduce();
            if(years < 10){
            forest.setThing(row, column, this); // Poner en posición nueva
        } else {
            die();
        }
    }
    
    private int nr(){
        int r = rand.nextInt(3)-1;
        int nr;
        int mr;
        if (this.row + r < 25 && this.row + r > 0){
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
        if (this.column + c < 25 && this.column + c > 0){
            nc = c + this.column;
            mc= nc;
        }
        else{
            mc = this.column;
        }
        return mc;
    }
    
    private void reproduce(){
        for(int i = -1; i <= 1; i++){
            for(int j = -1; j <= 1; j++){
                if(i < 25 || i > 0){
                    if(j < 25 || j > 0){
                        
                    }
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
