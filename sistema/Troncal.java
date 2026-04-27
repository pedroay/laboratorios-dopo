import java.util.ArrayList;
import java.util.HashMap;

/**
 * Representa una troncal del sistema que agrupa estaciones y tramos.
 */
public class Troncal {
    private String nombre;
    private double velocidadPromedio;
    private ArrayList<Tramo> tramos;
    private HashMap<String, Estacion> estaciones;

    /**
     * Constructor para la clase Troncal.
     */
    public Troncal(String nombre, int velocidadPromedio) {
        this.nombre = nombre;
        this.velocidadPromedio = velocidadPromedio;
        this.tramos = new ArrayList<>();
        this.estaciones = new HashMap<>();
    }

    public String getNombre() {
        return nombre;
    }

    public double getVelocidadPromedio() {
        return velocidadPromedio;
    }

    public ArrayList<Tramo> getTramos() {
        return tramos;
    }

    public HashMap<String, Estacion> getEstaciones() {
        return estaciones;
    }

    public void addEstacion(Estacion estacion) {
        estaciones.put(estacion.getNombre(), estacion);
    }

    public void addTramo(Tramo tramo) {
        tramos.add(tramo);
    }
}
