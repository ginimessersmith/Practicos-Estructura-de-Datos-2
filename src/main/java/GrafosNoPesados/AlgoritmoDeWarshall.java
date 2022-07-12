/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GrafosNoPesados;

/**
 *
 * @author Gino
 */
public class AlgoritmoDeWarshall {

    private Grafos grafo;

    public AlgoritmoDeWarshall(Grafos unGrafo) {
        this.grafo = unGrafo;
    }

    public int[][] algoritmoWharsall() {
        int unaMatriz[][] = this.matrizAdyacencia();
        for (int k = 0; k < grafo.cantidadVertices(); k++) {
            for (int i = 0; i < grafo.cantidadVertices(); i++) {
                for (int j = 0; j < grafo.cantidadVertices(); j++) {
                    unaMatriz[i][j] = unaMatriz[i][j] | (unaMatriz[k][j] & unaMatriz[i][k]);
                }
            }
        }
        return unaMatriz;
    }

    public int[][] matrizAdyacencia() {
        int size = this.grafo.cantidadVertices();
        int[][] unaMatriz = new int[size][size];
        ejecutar(unaMatriz, size);
        for (int i = 0; i < size; i++) {
            for (int j : grafo.listaDeAdyacencia.get(i)) {
                unaMatriz[i][j] = 1;
            }
        }
        return unaMatriz;
    }

    private void ejecutar(int[][] unaMatriz, int size) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                unaMatriz[i][j] = 0;
            }
        }
    }

    public void mostrarMatriz(int[][] matriz) {
        int tamaño = grafo.cantidadVertices();
        for (int i = 0; i < tamaño; i++) {
            for (int j = 0; j < tamaño; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
