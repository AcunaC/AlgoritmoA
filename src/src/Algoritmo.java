package src;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 *
 * @author Billy
 */
public class Algoritmo {

    public static final int COSTO_HORIZONTAL = 10;
    public static final int COSTO_DIAGONAL = 14;

    public static final int NRO_FILAS = 7;
    public static final int NRO_COLUMNAS = 9;

    public static final int FILA_INICIAL = 3;
    public static final int COLUMNA_INICIAL = 2;

    public static final int FILA_DESTINO = 6;
    public static final int COLUMNA_DESTINO = 3;

    public static int[][] bloques = new int[][]{
        {2, 4},
        {3, 4},
        {4, 4},
        {5, 4}
    };

    /**
     * Importante validar: 1. las coordenadas de inicio son distintas que las de
     * fin. 2. Las coordenadas de inicio y fin no deben corresponder a un
     * bloque.
     *
     *
     * @param args
     */
    public static void main(String[] args) {
        // Tablero de nodos
        Tablero tab = new Tablero(NRO_FILAS, NRO_COLUMNAS);
        tab.ubicarBloques(bloques);

        // Nodo inicial
        final Nodo inicial = tab.obtenerNodo(FILA_INICIAL, COLUMNA_INICIAL);
        inicial.calcularF();
        System.out.println(inicial);

        // Nodo destino
        final Nodo destino = tab.obtenerNodo(FILA_DESTINO, COLUMNA_DESTINO);
        System.out.println(destino);

        // Conjunto de nodos a evaluar
        PriorityQueue<Nodo> abiertos = new PriorityQueue();
        ArrayList<Nodo> cerrados = new ArrayList();
        abiertos.add(inicial);

        do {
            // Nodo con menor F 
            Nodo i = abiertos.poll();
            cerrados.add(i);

            if (i.esDestino()) {
                break;
            }

            ArrayList<Nodo> sucesores = tab.obtenerSucesores(i);
            for (int ii = 0; ii < sucesores.size(); ii++) {
                Nodo j = sucesores.get(ii);
                if (cerrados.contains(j)) {
                    continue;
                }
                if (!abiertos.contains(j)) {
                    j.previo = i;
                    j.calcularG(i);
                    abiertos.add(j);
                } else {
                    if (j.G > i.G + i.calcularCosto(j)) {
                        j.previo = i;
                        j.calcularG(i);
                    }
                }
            }

        } while (!abiertos.isEmpty());

        System.out.println(calcularRuta(inicial, destino));
    }

    static LinkedList<Nodo> calcularRuta(Nodo inicio, Nodo fin) {
        // TODO if invalid nodes are given (eg cannot find from
        // goal to start, this method will result in an infinite loop!)
        LinkedList<Nodo> ruta = new LinkedList<Nodo>();

        Nodo actual = fin;
        boolean exito = false;
        while (!exito) {
            ruta.addFirst(actual);
            actual = (Nodo) actual.previo;

            if (actual.equals(inicio)) {
                exito = true;
            }
        }

        // si quieres obtener solo la ruta sin el nodo inicial comentas esto.
        ruta.addFirst(inicio);
        return ruta;
    }

}
