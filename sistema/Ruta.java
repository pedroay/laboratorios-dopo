import java.util.ArrayList;

/**
 * Representa una ruta de transporte que pasa por varias estaciones.
 */
public class Ruta implements Comparable<Ruta> {
    private String nombre;
    private ArrayList<Estacion> paradas;

    /**
     * Constructor para la clase Ruta.
     */
    public Ruta(String nombre) {
        this.nombre = nombre;
        this.paradas = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public ArrayList<Estacion> getParadas() {
        return paradas;
    }

    public void addParada(Estacion estacion) {
        paradas.add(estacion);
        estacion.addRuta(this);
    }

    @Override
    public int compareTo(Ruta otra) {
        return this.nombre.compareTo(otra.nombre);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ruta ruta = (Ruta) o;
        return nombre.equals(ruta.nombre);
    }

    @Override
    public int hashCode() {
        return nombre.hashCode();
    }

    /**
     * Calcula el número de paradas entre dos estaciones dadas sus nombres.
     * 
     * @param estacion1 Nombre de la primera estación.
     * @param estacion2 Nombre de la segunda estación.
     * @return El número de paradas entre las dos estaciones (distancia absoluta entre sus índices).
     * @throws TransmilenioException Si alguna de las estaciones no se encuentra en esta ruta.
     */
    public int numParadas(String estacion1,String estacion2) throws TransmilenioException  {
        int indexInicio = -1;
        int indexFin = -1;

        for (int i = 0; i < paradas.size(); i++) {
            if (paradas.get(i).getNombre().equals(estacion1)) indexInicio = i;
            if (paradas.get(i).getNombre().equals(estacion2)) indexFin = i;
        }

        if (indexInicio == -1 || indexFin == -1) {
            throw new TransmilenioException(TransmilenioException.STATION_NOT_FOUND);
        }
        return Math.abs(indexFin - indexInicio);
    }
}