package src;

import java.util.ArrayList;

/**
 *
 * @author Billy
 */
public class Tablero {

    private final int nroFilas;
    private final int nroColumnas;
    private final int[][] mapa;

    private ArrayList<Nodo> nodos = new ArrayList();

    public Tablero(int nroFilas, int nroColumnas) {
        this.nroFilas = nroFilas;
        this.nroColumnas = nroColumnas;
        this.mapa = new int[nroFilas][nroColumnas];
    }

    public void ubicarBloques(int[][] bloques) {
        int bloqueFila;
        int bloqueColumna;
        // para cada fila de bloques
        for (int[] fila : bloques) {
            bloqueFila = fila[0];
            bloqueColumna = fila[1];

            // si esta dentro de los limites
            if (posicionValida(bloqueFila, bloqueColumna)) {
                mapa[bloqueFila][bloqueColumna] = 1;
            }
        }
    }

    boolean posicionValida(int fila, int columna) {
        return (fila >= 0 && fila < nroFilas) && (columna >= 0 && columna < nroColumnas);
    }

    public ArrayList<Nodo> obtenerSucesores(Nodo n) {
        ArrayList<Nodo> retorno = new ArrayList();
        for (int i = n.fila - 1; i < n.fila + 2; i++) {
            for (int j = n.col - 1; j < n.col + 2; j++) {
                Nodo current = obtenerNodo(i, j);
                if (current != null) {
                    retorno.add(current);
                }
            }
        }
        retorno.remove(n);
        return retorno;
    }

    public Nodo obtenerNodo(int fila, int col) {
        if (posicionValida(fila, col)) {
            if (mapa[fila][col] == 0) {
                for (Nodo n : nodos) {
                    if (n.fila == fila && n.col == col) {
                        return n;
                    }
                }
                Nodo nuevo = new Nodo(fila, col);
                nodos.add(nuevo);
                return nuevo;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

}
