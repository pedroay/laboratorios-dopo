package domain;
import java.util.*;

/*No olviden adicionar la documentacion*/
public class Forest{
    static private int SIZE=25;
    private Thing[][] places;
    private List<Shadow> shadows;
    
    /**
     * Constructor de la clase Forest.
     * 
     * Inicializa la matriz bidimensional {@code places} con tamaño {@code SIZE x SIZE},
     * asignando {@code null} a cada una de sus posiciones, lo que indica que inicialmente
     * no hay objetos (Thing) en ninguna celda del bosque.
     * 
     * Además, inicializa la lista {@code shadows} como un {@code ArrayList} vacío,
     * que se utilizará para almacenar ciertos elementos especiales del bosque.
     * 
     * Finalmente, invoca el método {@code someThings()} para poblar el bosque
     * con algunos objetos iniciales.
     */
    public Forest() {
        places=new Thing[SIZE][SIZE];
        for (int r=0;r<SIZE;r++){
            for (int c=0;c<SIZE;c++){
                places[r][c]=null;
            }
        }
        shadows = new ArrayList<>(); 
        someThings();
    }

    public int  getSize(){
        return SIZE;
    }

    public Thing getThing(int r,int c){
        return places[r][c];
    }

    public void setThing(int r, int c, Thing e){
        places[r][c]=e;
    }

    public void someThings(){   
         Squirrel s1 = new Squirrel(this, 10, 10);
        Squirrel s2 = new Squirrel(this, 10, 12);
         Shadow so0 = new Shadow(this,24);
         Shadow so1 = new Shadow(this,10);
         LonelyTree lt1 = new LonelyTree(this, 5, 5);
        LonelyTree lt2 = new LonelyTree(this, 5, 10);
        Lake lake = new Lake(this, 12, 12);
    }
    
    /**
     * Cuenta el número de vecinos adyacentes a una posición dada que son del mismo tipo.
     * 
     * Recorre las 8 posiciones vecinas (incluyendo diagonales) alrededor de la celda
     * {@code (r, c)} y contabiliza cuántas contienen un objeto no nulo del mismo tipo
     * (misma clase) que el objeto ubicado en dicha posición.
     * 
     * @param r Fila de la posición a evaluar.
     * @param c Columna de la posición a evaluar.
     * @return Número de vecinos con objetos del mismo tipo. Retorna 0 si la posición
     *         está fuera del bosque o si no hay objeto en {@code (r, c)}.
 */
    public int neighborsEquals(int r, int c){
        int num=0;
        if (inForest(r,c) && places[r][c]!=null){
            for(int dr=-1; dr<2;dr++){
                for (int dc=-1; dc<2;dc++){
                    if ((dr!=0 || dc!=0) && inForest(r+dr,c+dc) && 
                    (places[r+dr][c+dc]!=null) &&  (places[r][c].getClass()==places[r+dr][c+dc].getClass())) num++;
                }
            }
        }
        return num;
    }
   

    public boolean isEmpty(int r, int c){
        return (inForest(r,c) && places[r][c]==null);
    }    
        
    public boolean inForest(int r, int c){
        return ((0<=r) && (r<SIZE) && (0<=c) && (c<SIZE));
    }
    
    /**
     * seacrh in all the things in the forest and thing call the method ticTac
     */
    public void ticTac(){
        for (int r = 0; r < SIZE; r++){
            for (int c = 0; c < SIZE; c++){
                if (places[r][c] != null){
                    places[r][c].ticTac();
                }
            }
        }
        for (Shadow s : shadows) {
            s.ticTac();
        }
    }
    
    public void addShadow(Shadow shadow) {
        shadows.add(shadow);
    }

    public List<Shadow> getShadows() {
        return shadows;
    }
}
