import java.util.List;

/**
 * Representa un portaaviones dentro del juego Batalla Naval.
 * Un portaaviones puede transportar una cantidad limitada de aviones.
 */
public class PortaAviones {

    /**
     * Número identificador del portaaviones.
     */
    private final int numero;

    /**
     * Capacidad máxima de aviones que puede transportar el portaaviones.
     */
    private int capacidad;

    /**
     * numTripulante indica el numero minimo de tripulantes
     */
    private final static int numTripulante=5;

    /**
     * puntaje indica el puntaje queda eliminar el porta aviones
     */
    private static int puntaje;

    /**
     * Aviones que se encuentran actualmente en el portaaviones.
     */
    private List<Avion> aviones;

    /**
     * Ubicacion dice donde se encuentra actualmente el porta aviones
     */
    private Posicion ubicacion;

    /**
     * los marinos que viajan en el barco
     */
    private List<Marino> marinos;
    
    public PortaAviones(int numero){
        this.numero = numero;
    }
    
    /**
     * return the size of marinos arraylist
     */
    private int getMarinoSize(){
        return marinos.size();
    }
    
    /**
     * return a true if the portaaviones have all their marinos
     */
    public boolean verifSufMarinos(){
        boolean marinos = true;
        int marinosSize = getMarinoSize();
        if (marinosSize < numTripulante){
            marinos = false;
      }
        return marinos;
    }
    
    
    public boolean verfiPosicion(int latitud, int longitud){
        Posicion ubicacion = this.ubicacion;
        return ubicacion.latitud
        
    }
}

