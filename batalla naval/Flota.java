import java.util.ArrayList;

/**
 * Representa una flota dentro del juego Batalla Naval.
 * Una flota está compuesta por máquinas de guerra y marinos,
 * y opera dentro de un tablero.
 */
public class Flota {
    /**
     * Tablero en el que opera la flota.
     */
    private final Tablero tablero;

    /**
     * Nombre de la flota.
     */
    private final String nombre;

    /**
     * CodigoFlota dice el codigo de identifiación de la flota
     */
    public final int codigoFlota;

    /**
     * Ubicación actual de la flota.
     */
    private Posicion ubicacion;

    /**
     * Aviones que pertenecen a la flota.
     */
    private ArrayList<Avion> aviones;

    /**
     * Portaaviones que pertenecen a la flota.
     */
    private ArrayList<PortaAviones> portaAviones;

    /**
     * Barcos que pertenecen a la flota.
     */
    private ArrayList<Barco> barcos;

    /**
     * Marinos que pertenecen a la flota.
     */
    private ArrayList<Marino> marinos;
    /**
     * return a object of class Flota
     */
    public Flota(Tablero tablero, String nombre, int codigo){
       this.tablero = tablero;
       this.nombre = nombre;
       this.codigoFlota = codigo;
    }
    /**
     * return a boolean if all the elements in the flota have all they marinos
     */
    public boolean suficientesMarinos(){
        for (Avion a : aviones){
            if ( !a.verifSufMarinos()){
                return false;
            }
        }
        for (Barco b :barcos){
            if( !b.verifSufMarinos()){
                return false;
            }
        }
        for (PortaAviones p: portaAviones){
            if( !p.verifSufMarinos()){
                return false;
            }
        }
        return true;
    }
    
    public ArrayList<PortaAviones> getPortaAviones(){
        return this.portaAviones;
    }
    
    public boolean esBuenAtaque(int longitud,int latitud){
        Tablero tablero = this.tablero;
        ArrayList<Flota> flotas = tablero.getFlotas();
        for (Flota f : flotas){
            if (difFLota(f)){
                ArrayList<PortaAviones> porEnemigos = getPortaAviones();
                for (PortaAviones p: porEnemigos){
                            
                }
            }
        }
    }
    
    private boolean difFLota(Flota f){
        return this != f;
    }
}
