package dominio;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Sokoban {

    private Objeto[][] tablero;       
    private Personaje personaje;
    private List<Caja> cajas;
    private int filas;
    private int columnas;
    public static final int ARRIBA   = 0;
    public static final int ABAJO    = 1;
    public static final int IZQUIERDA = 2;
    public static final int DERECHA  = 3;
    private static final String[] NIVEL = {
        "##########",
        "#   .    #",
        "# $ # .  #",
        "#   $ #  #",
        "#  @     #",
        "#  .  $  #",   
        "#        #",
        "##########"
    };
    public Sokoban() {
        cajas = new ArrayList<>();
        cargarNivel(NIVEL);
        
    }

    public void reiniciar() {
        cajas.clear();
        cargarNivel(NIVEL);
    }

    public int getFilas() { return filas; }
    public int getColumnas() { return columnas; }

    public char getSimboloEn(int f, int c) {

        if (personaje.getFila() == f && personaje.getColumna() == c) {
            return personaje.render();
        }
        // 2. Cajas
        for (Caja caja : cajas) {
            if (caja.getFila() == f && caja.getColumna() == c) {
                return caja.render();
            }
        }
        // 3. Fondo (Paredes/Metas/Piso)
        return tablero[f][c].render();
    }
    

    private void cargarNivel(String[] nivel) {
        filas    = nivel.length;
        columnas = nivel[0].length();
        tablero  = new Objeto[filas][columnas];

        for (int f = 0; f < filas; f++) {
            for (int c = 0; c < columnas && c < nivel[f].length(); c++) {
                char ch = nivel[f].charAt(c);
                switch (ch) {
                    case '#':
                        tablero[f][c] = new Obstaculo(f, c);
                        break;
                    case '.':
                        tablero[f][c] = new PisoMeta(f, c);
                        break;
                    case '$':
                        tablero[f][c] = new Piso(f, c);
                        Caja caja = new Caja(f, c);
                        cajas.add(caja);
                        break;
                    case '@':
                        tablero[f][c] = new Piso(f, c);
                        personaje = new Personaje(f, c);
                        break;
                    default:
                        tablero[f][c] = new Piso(f, c);
                        break;
                }
            }
        }
        actualizarEstadoCajas();
    }

    // =========================================================
    //  Lógica de movimiento
    // =========================================================

    /**
     * Intenta mover el personaje en la dirección indicada.
     * @return true si el movimiento fue válido.
     */
    public boolean mover(int direccion) {
        int direcionFila = 0;
        int direcionColumna = 0;
        switch (direccion) {
            case ARRIBA:    direcionFila = -1; break;
            case ABAJO:     direcionFila =  1; break;
            case IZQUIERDA: direcionColumna = -1; break;
            case DERECHA:   direcionColumna =  1; break;
            default: return false;
        }

        int numeroFila = personaje.getFila()    + direcionFila;
        int numeroColumna = personaje.getColumna() + direcionColumna;

        if (!dentroDelTablero(numeroFila, numeroColumna)) return false;

        // Destino es pared → no se puede mover
        if (!tablero[numeroFila][numeroColumna].isMovible()) return false;

        // ¿Hay una caja en la posición destino?
        Caja cajaDestino = cajaEn(numeroFila, numeroColumna);
        if (cajaDestino != null) {
            int numeroFila2 = numeroFila + direcionFila;
            int numeroColumna2 = numeroColumna + direcionColumna;
            if (!dentroDelTablero(numeroFila2, numeroColumna2)) return false;
            if (!tablero[numeroFila2][numeroColumna2].isMovible()) return false;
            if (cajaEn(numeroFila2, numeroColumna2) != null) return false;

            cajaDestino.mover(numeroFila2, numeroColumna2);
        }

        personaje.mover(numeroFila, numeroColumna);
        actualizarEstadoCajas();
        return true;
    }

    // =========================================================
    //  Helpers
    // =========================================================

    private boolean dentroDelTablero(int f, int c) {
        return f >= 0 && f < filas && c >= 0 && c < columnas;
    }
    
    public Personaje getPersonaje() {
        return personaje;
    }

    private Caja cajaEn(int f, int c) {
        for (Caja caja : cajas) {
            if (caja.getFila() == f && caja.getColumna() == c) return caja;
        }
        return null;
    }

    /** Marca cada caja según si está sobre una PisoMeta. */
    private void actualizarEstadoCajas() {
        for (Caja caja : cajas) {
            caja.setEnMeta(tablero[caja.getFila()][caja.getColumna()] instanceof PisoMeta);
        }
    }

    /** @return true si todas las cajas están sobre metas. */
    public boolean nivelCompletado() {
        for (Caja caja : cajas) {
            if (!caja.isEnMeta()) return false;
        }
        return true;
    }


    public void dibujar() {
        System.out.println("\n=== SOKOBAN ===  Movimientos: " + personaje.getMovimientos());
        System.out.println("Flechas / WASD  |  Q = salir\n");

        char[][] buffer = new char[filas][columnas];

        for (int f = 0; f < filas; f++) {
            for (int c = 0; c < columnas; c++) {
                buffer[f][c] = tablero[f][c].render();
            }
        }

        // 2. Cajas (encima del piso)
        for (Caja caja : cajas) {
            buffer[caja.getFila()][caja.getColumna()] = caja.render();
        }

        // 3. Personaje (encima de todo)
        buffer[personaje.getFila()][personaje.getColumna()] = personaje.render();

        // 4. Imprimir buffer
        for (int f = 0; f < filas; f++) {
            System.out.println(new String(buffer[f]));
        }
        System.out.println();
    }

    
}