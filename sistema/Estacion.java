import java.util.ArrayList;

/**
 * Representa una estación del sistema de transporte.
 */
public class Estacion {
    private String nombre;
    private String nivelDeOcupacion;
    private int tiempoDeEspera;
    private ArrayList<Ruta> rutas;

    /**
     * Constructor para la clase Estacion.
     */
    public Estacion(String nombre, String nivelDeOcupacion, int tiempoDeEspera) {
        this.nombre = nombre;
        this.nivelDeOcupacion = nivelDeOcupacion;
        this.tiempoDeEspera = tiempoDeEspera;
        this.rutas = new ArrayList<>();
    }

    /**
     * Retorna el tiempo de espera en la estación.
     * @return tiempo de espera en minutos.
     * @throws TransmilenioException-NO_WAIT_TIME si no tiene tiempo de espera.
     */
    public int getTiempoEspera() throws TransmilenioException{
        if (tiempoDeEspera <= 0) {
            throw new TransmilenioException(TransmilenioException.NO_WAIT_TIME);
        }
        return tiempoDeEspera;
    }

    public String getNombre() {
        return nombre;
    }

    public String getNivelDeOcupacion() {
        return nivelDeOcupacion;
    }

    public void setNivelDeOcupacion(String nivelDeOcupacion) {
        this.nivelDeOcupacion = nivelDeOcupacion;
    }

    public void setTiempoDeEspera(int tiempoDeEspera) {
        this.tiempoDeEspera = tiempoDeEspera;
    }

    public ArrayList<Ruta> getRutas() {
        return rutas;
    }

    public void addRuta(Ruta ruta) {
        if (!rutas.contains(ruta)) {
            rutas.add(ruta);
        }
    }
}
