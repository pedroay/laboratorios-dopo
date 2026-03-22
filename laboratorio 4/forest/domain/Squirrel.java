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
    

    /**
     * Ejecuta un ciclo de comportamiento del objeto dentro del entorno.
     * 
     * Incrementa el contador tictac y actualiza el color mediante el método
     * actualizarColor(). Luego calcula una posible nueva posición (nr, nc) y,
     * si está libre, se mueve a esa posición.
     * 
     * Cada vez que tictac % 4 == 1, incrementa la edad (years).
     * 
     * Intenta reproducirse llamando al método reproduce().
     * 
     * Finalmente, si la edad es menor a 10, el objeto se mantiene en el bosque
     * en su nueva posición; de lo contrario, muere ejecutando die().
     */
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
    
    /**
     * Actualiza el color del objeto según su edad (years).
     * 
     * Asigna diferentes tonalidades de color dependiendo del valor de years,
     * generando una transición progresiva desde tonos oscuros hasta amarillo.
     * 
     * Para edades entre 0 y 9 se asignan colores específicos definidos manualmente.
     * Para cualquier edad mayor o no contemplada, se asigna el color amarillo.
     */
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
    
    /**
     * Calcula una nueva fila aleatoria cercana a la posición actual.
     * 
     * Genera un desplazamiento aleatorio entre -1, 0 y 1, y lo suma a la fila actual.
     * Si la nueva posición está dentro de los límites válidos (0 a 24),
     * se retorna esa nueva fila; en caso contrario, se mantiene la fila actual.
     * 
     * Este método se utiliza para simular un movimiento aleatorio controlado
     * dentro del entorno.
     */
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
    /**
     * Calcula una nueva columna aleatoria cercana a la posición actual.
     * 
     * Genera un desplazamiento aleatorio entre -1, 0 y 1, y lo suma a la columna actual.
     * Si la nueva posición está dentro de los límites válidos (0 a 24),
     * se retorna esa nueva columna; en caso contrario, se mantiene la columna actual.
     * 
     * Este método permite simular un movimiento aleatorio dentro del entorno,
     * asegurando que no se salga de los límites definidos.
     */
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
    
    /**
     * Intenta reproducir el objeto con otro de su misma especie.
     * 
     * Recorre las 8 direcciones posibles alrededor de la posición actual.
     * Para cada dirección, verifica si existe otro objeto del tipo Squirrel
     * a una distancia de dos posiciones (posible pareja).
     * 
     * Si encuentra una pareja y la posición intermedia está libre,
     * crea una nueva instancia de Squirrel en esa posición intermedia.
     * 
     * El proceso se detiene al lograrse una reproducción.
     */
        private void reproduce() {
        int[][] directions = {
            {-1, 0}, {1, 0}, {0, -1}, {0, 1}, 
            {-1, -1}, {-1, 1}, {1, -1}, {1, 1} 
        };   
        for (int[] d : directions) {
            int midR = this.row + d[0];
            int midC = this.column + d[1];
            int targetR = this.row + 2 * d[0];
            int targetC = this.column + 2 * d[1];
            if (forest.inForest(targetR, targetC)) {
                Thing partner = forest.getThing(targetR, targetC);
                if (partner instanceof Squirrel && forest.getThing(midR, midC) == null) {
                    new Squirrel(this.forest, midR, midC);
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
