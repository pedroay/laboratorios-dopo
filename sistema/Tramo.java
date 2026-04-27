/**
 * Representa un tramo entre dos estaciones con una distancia específica.
 */
public class Tramo {
    private double distancia;
    private Estacion puntoInicio;
    private Estacion puntoFin;

    /**
     * Constructor para la clase Tramo.
     */
    public Tramo(int distancia, Estacion puntoInicio, Estacion puntoFin) {
        this.distancia = distancia;
        this.puntoInicio = puntoInicio;
        this.puntoFin = puntoFin;
    }

    public double getDistancia() {
        return distancia;
    }

    public Estacion getPuntoInicio() {
        return puntoInicio;
    }

    public Estacion getPuntoFin() {
        return puntoFin;
    }
}
