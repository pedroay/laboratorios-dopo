import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

/**
 * Clase principal que gestiona el sistema de transporte.
 */
public class Sistema {
    private HashMap<String, Troncal> troncales;
    private HashMap<String, Estacion> estaciones;
    private TreeSet<Ruta> rutas;

    /**
     * Constructor para la clase Sistema.
     */
    public Sistema() {
        this.troncales = new HashMap<>();
        this.estaciones = new HashMap<>();
        this.rutas = new TreeSet<>();
    }

    /**
     * Retorna el tiempo de espera de una ruta específica.
     * @param nombreRuta El nombre de la ruta.
     * @return tiempo de espera total de la ruta.
     * @throws TransmilenioException-STATION_NOT_FOUND si la ruta no existe o no tiene tiempo de espera.
     *                              -NO_WAIT_TIME si no tiene tiempo de espera.
     */
    public int tiempoEspera(String nombreEstacion) throws TransmilenioException {
        Estacion e = estaciones.get(nombreEstacion);
        if (e == null) {
            throw new TransmilenioException(TransmilenioException.STATION_NOT_FOUND);
        }
        int tiempo = e.getTiempoEspera();
        return tiempo;
    }

    /**
     * Retorna las rutas del sistema ordenadas por nombre.
     * @return Lista de rutas ordenadas.
     */
    public ArrayList<Ruta> getRutasOrdenadas() {
        return new ArrayList<>(rutas);
    }

    /**
     * Retorna los nombres de las rutas del sistema ordenados alfabéticamente.
     * @return Lista de nombres de rutas.
     */
    public ArrayList<String> getNombresRutasOrdenados() {
        ArrayList<String> nombres = new ArrayList<>();
        for (Ruta r : rutas) {
            nombres.add(r.getNombre());
        }
        return nombres;
    }

    /**
     * Calcula el número de paradas entre dos estaciones en una ruta específica.
     * @param nombreRuta Nombre de la ruta.
     * @param inicio Nombre de la estación de inicio.
     * @param fin Nombre de la estación de fin.
     * @return Número de paradas (diferencia de índices), o -1 si no se encuentra la ruta o estaciones.
     */
    public int paradasEnRuta (String nombreRuta, String inicio, String fin) throws TransmilenioException {
        Ruta rutaBuscada = null;
        for (Ruta r : rutas) {
            if (r.getNombre().equals(nombreRuta)) {
                rutaBuscada = r;
                break;
            }
        }
        
        int numParadas = rutaBuscada.numParadas(inicio, fin);
        return numParadas;
    }

    /**
     * Retorna los nombres de las rutas que permiten ir de una estación a otra sin transbordos,
     * ordenadas de menor a mayor por número de paradas y alfabéticamente por nombre.
     * 
     * @param inicio Nombre de la estación de origen.
     * @param fin Nombre de la estación de destino.
     * @return Lista de nombres de rutas que cumplen el criterio.
     */
    public ArrayList<String> rutasDirectas(String inicio, String fin) {
        ArrayList<Object[]> coincidentes = new ArrayList<>();
        for (Ruta r : rutas) {
            try {
                int nParadas = r.numParadas(inicio, fin);
                // Guardamos la "tupla" [nombre (String), paradas (int)]
                coincidentes.add(new Object[] { r.getNombre(), nParadas });
            } catch (TransmilenioException e) {
                // Ignorar rutas que no contienen ambas estaciones
            }
        }
        return ordenarRutas(coincidentes);
    }

    private ArrayList<String> ordenarRutas(ArrayList<Object[]> coincidentes) {
        ArrayList<String> nombres = new ArrayList<>();
        while (!coincidentes.isEmpty()) {
            int indexMejor = 0;
            for (int i = 0; i < coincidentes.size(); i++) {
                Object[] actual = coincidentes.get(i);
                Object[] mejor = coincidentes.get(indexMejor);
                int pActual = (int) actual[1];
                int pMejor = (int) mejor[1];
                String nActual = (String) actual[0];
                String nMejor = (String) mejor[0];
                if (pActual < pMejor) {
                    indexMejor = i;
                } 
                else if (pActual == pMejor) {
                    if (nActual.compareTo(nMejor) < 0) {
                        indexMejor = i;
                    }
                }
            }
            nombres.add((String) coincidentes.get(indexMejor)[0]);
            coincidentes.remove(indexMejor);
        }
        return nombres;
    }

    // Métodos para agregar elementos
    public void addTroncal(Troncal t) {
        troncales.put(t.getNombre(), t);
    }

    public void addEstacion(Estacion e) {
        estaciones.put(e.getNombre(), e);
    }

    public void addRuta(Ruta r) {
        rutas.add(r);
    }
}
