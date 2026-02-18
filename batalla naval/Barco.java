import java.util.List;

/**
 * Representa un barco de guerra dentro del juego Batalla Naval.
 */
public class Barco {

    /**
     * numero dice el numero de identifcación del barco
     */
    
    
    private final int numero;

    /**
     * numTripulante indica el numero de tripulantes minimos
     */
    private final static int numTripulante=4
    ;

    /**
     * puntaje indica el valor que representa elimiinar cada uno
     * de los barcos
     */
    private static int puntaje;

    /**
     * ubicación del barco actualmente
     */
    private Posicion ubicación;
    
    /**
     * lista de marinos que viajan en el barco
     */
    private List<Marino> marinos;
    
    public Barco(int num){
        numero = num;
    }
    
    /**
     * return the size of marinos arraylist
     */
    private int getMarinoSize(){
        return marinos.size();
    }
    
    /**
     * return a true if the barcos have all their marinos
     */
    public boolean verifSufMarinos(){
        boolean marinos = true;
        int marinosSize = getMarinoSize();
        if (marinosSize < numTripulante){
            marinos = false;
      }
        return marinos;
    }
}