import java.util.ArrayList;

/**
 * Representa un avión de guerra dentro del juego.
 * Un avión puede estar en el aire o no y es operado por marinos.
 */
public class Avion {

    /**
     * Placa que identifica de manera única al avión.
     */
    private final String placa;

    /**
     * Indica si el avión se encuentra actualmente en el aire.
     */
    private boolean enAire;

    /**
     * indica el numero minimo de tripulantes
     */
    private final int numTripulante = 2;

    private static int puntaje;

    /**
     * Ubicación actual del avión.
     */
    private Posicion ubicacion;

    /**
     * Marino que actúa como copiloto del avión.
     */
    private Marino copiloto;

    /**
     * Marino que actúa como piloto del avión.
     */
    private Marino piloto;
    private ArrayList<Marino> marinos;
    /**
     * defines de placa of a avion
     */
    public Avion(String placa){
        this.placa = placa;
    }
    
    /**
     * return the size of marinos arraylist
     */
    private int getMarinoSize(){
        return marinos.size();
    }
    /**
     * return a true if the avion have all their marinos
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
    