import java.util.ArrayList;

public class Tablero {

    private int latitud;
    private int longitud;
    private ArrayList<Flota> flotas;
    private final static int maximo = 100;
    private final static int minimo = -100;
    
    public Tablero (){
        flotas=new ArrayList<Flota>();
    }
    
    public ArrayList<Flota> getFlotas(){
        return flotas;
    }

    public int getMin(){
        return minimo;
    }

    public int getMax(){
        return maximo;
    }
    
    
}
