/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GrafosNoPesados;

/**
 *
 * @author Gino
 */
import java.util.*;

public class MatrizCaminos {

    private Grafos grafo;
    public int[][] matriz;

    public MatrizCaminos(Grafos unGrafo) {
        this.grafo = unGrafo;
        this.matriz = this.matrizDeCaminos();
    }

    public int[][] matrizDeCaminos() {
        int[][] matriz = matrizAdyacencia();
        LinkedList<int[][]> potencias = listaDePotencia(matriz);
        int[][] suma = matriz;
        for (int i = 0; i < potencias.size(); i++) {
            suma = sumar(suma, potencias.get(i));
        }
        return suma;
    }

    public int[][] matrizAdyacencia() {
        int size = this.grafo.cantidadVertices();
        int[][] unaMatriz = new int[size][size];
        Inicializar(unaMatriz, size);
        for (int i = 0; i < size; i++) {
            for (int j : grafo.listaDeAdyacencia.get(i)) {
                unaMatriz[i][j] = 1;
            }
        }
        return unaMatriz;
    }

    public int[][] matrizTop(int[][] unaMatriz, int top) {
        int[][] resultado = unaMatriz;
        for (int i = 1; i < top; i++) {
            resultado = multiplicar(unaMatriz, resultado);
        }
        return resultado;
    }

    private void Inicializar(int[][] unaMatriz, int size) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                unaMatriz[i][j] = 0;
            }
        }
    }

    public LinkedList<int[][]> listaDePotencia(int[][] unaMatriz) {
        int size = unaMatriz.length;
        LinkedList<int[][]> Lista = new LinkedList();
        for (int i = 2; i < size + 1; i++) {
            Lista.add(matrizTop(unaMatriz, i));
        }
        return Lista;
    }

    public int[][] sumar(int[][] matrizA, int[][] matrizB) {
        int[][] suma = new int[matrizA.length][matrizA.length];
        for (int i = 0; i < matrizA.length; i++) {
            for (int j = 0; j < matrizA.length; j++) {
                suma[i][j] = matrizA[i][j] + matrizB[i][j];
            }
        }
        return suma;
    }

    
    public int[][] multiplicar(int[][] matrizA, int[][] matrizB) {
        int casilla = 0;
        int[][] resultado = new int[matrizA.length][matrizB.length];
        for (int i = 0; i < matrizA.length; i++) { //que fila
            for (int j = 0; j < matrizA.length; j++) { //que columna
                for (int k = 0; k < matrizA.length; k++) { //va recorriedo la fila y la columna
                    int fico = matrizA[i][k] * matrizB[k][j];
                    casilla = casilla + fico;
                }
                resultado[i][j] = casilla;
                casilla = 0;
            }
        }
        return resultado;
    }

    public boolean hayCiclo() {
        for (int i = 0; i < this.matriz.length; i++) { //que fila
            if (this.matriz[i][i] == 1) {
                return true;
            }
        }
        return false;
    }
    public void mostrarMatriz(int[][] matriz) {
        int size = grafo.cantidadVertices();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if(matriz[i][j]!=0){
                    System.out.print(1 + " ");
                }
                else{
                    System.out.print(0 + " ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}
