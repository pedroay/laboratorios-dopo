package dominio;

/**
 * Clase abstracta base para todos los objetos del juego Sokoban.
 * Representa cualquier entidad que puede ocupar una posición en el tablero.
 */
public abstract class Objeto {
    protected int fila;
    protected int columna;
    protected char simbolo;
    protected boolean movible;

    public Objeto(int fila, int columna, char simbolo) {
        this.fila = fila;
        this.columna = columna;
        this.simbolo = simbolo;
        this.movible = false;
    }

    public int getFila() { return fila; }
    public int getColumna() { return columna; }
    public char getSimbolo() { return simbolo; }

    public void setFila(int fila) { this.fila = fila; }
    public void setColumna(int columna) { this.columna = columna; }

    /**
     * Representación visual del objeto en el tablero.
     */
    public abstract char render();

    @Override
    public String toString() {
        return getClass().getSimpleName() + "[" + fila + "," + columna + "]";
    }

    public boolean isMovible() {
        return movible;
    }
}