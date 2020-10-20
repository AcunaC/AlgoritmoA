package src;

import static src.Algoritmo.*;

/**
 *
 * @author Billy
 */
public class Nodo implements Comparable {

    public Nodo previo;

    public final int fila;
    public final int col;

    public final int H;
    public int G = 0;
    public int F = 999990;

    public Nodo(int nFila, int nColumna) {
        fila = nFila;
        col = nColumna;

        H = (Math.abs(FILA_DESTINO - fila)
                + Math.abs(COLUMNA_DESTINO - col)) * 10;
    }

    public void calcularF() {
        F = H + G;
    }

    public void calcularG(Nodo n2) {
        this.G = n2.G + calcularCosto(n2);
    }

    public boolean esDestino() {
        return fila == FILA_DESTINO && col == COLUMNA_DESTINO;
    }

    public int calcularCosto(Nodo n2) {
        if (this.fila == n2.fila || this.col == n2.col) {
            return COSTO_HORIZONTAL;
        }
        return COSTO_DIAGONAL;
    }

    @Override
    public String toString() {
        return "[" + fila + "," + col + "] "; //: G=" + G + " H=" + H + " F=" + F;
    }

    @Override
    public int compareTo(Object o) {
        return (H + G) - ((Nodo) o).G - ((Nodo) o).H;

    }

}
