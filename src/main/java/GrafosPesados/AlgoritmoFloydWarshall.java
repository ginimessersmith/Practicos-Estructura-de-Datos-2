/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GrafosPesados;

import GrafosNoPesados.DiGrafos;
import java.util.*;

/**
 *
 * @author Gino
 */
public class AlgoritmoFloydWarshall {

    private DiGrafosPesados digrafoPesado;
    private int Maximum = 99999;

    public AlgoritmoFloydWarshall(DiGrafosPesados unDiGrafo) {
        this.digrafoPesado = unDiGrafo;
    }

    public int[][] FloydWarshall() {
        int size = digrafoPesado.cantidadDeVertices();
        int distancias[][] = minimaDistancias();
        int i, j, k;
        for (k = 0; k < size; k++) {
            for (i = 0; i < size; i++) {
                for (j = 0; j < size; j++) {
                    if (distancias[i][k] + distancias[k][j] < distancias[i][j]) {
                        distancias[i][j] = distancias[i][k] + distancias[k][j];
                    }
                }
            }
        }
        return distancias;
    }

    public void mostrarMatriz(int[][] matriz) {
        int size = this.digrafoPesado.cantidadDeVertices();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public int[][] minimaDistancias() {
        int size = this.digrafoPesado.cantidadDeVertices();
        int[][] matrizDeDistancias = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == j) {
                    matrizDeDistancias[i][j] = 0;
                } else {
                    matrizDeDistancias[i][j] = this.Maximum;
                }
            }
        }
        for (int i = 0; i < size; i++) {
            List<AdyacenciaConPeso> adyacentesVertice = digrafoPesado.listaDeAdyacencia.get(i);
            for (AdyacenciaConPeso posicionAdyacente : adyacentesVertice) {
                matrizDeDistancias[i][posicionAdyacente.getIndexVertice()] = (int) posicionAdyacente.getPeso();
            }
        }
        return matrizDeDistancias;
    }

    public String mostrarCostosYCaminos(int unVertice) {
        LinkedList<List> listaDeUnVerticeATodos = new LinkedList<>();
        for (int i = 0; i < this.digrafoPesado.cantidadDeVertices(); i++) {
            List<Integer> CaminoVerticeAOtro = new LinkedList<>();
            CaminoVerticeAOtro.add(unVertice);
            listaDeUnVerticeATodos.add(CaminoVerticeAOtro);
        }
        int size = this.digrafoPesado.cantidadDeVertices();
        int distancia[][] = this.minimaDistancias();
        int i, j, k;
        for (k = 0; k < size; k++) {
            for (i = 0; i < size; i++) {
                for (j = 0; j < size; j++) {
                    if (distancia[i][k] + distancia[k][j] < distancia[i][j]) {
                        distancia[i][j] = distancia[i][k] + distancia[k][j];
                        if (i == unVertice) {
                            listaDeUnVerticeATodos.get(j).add(k);
                        }
                    }
                }
            }
        }
        for (int m = 0; m < this.digrafoPesado.cantidadDeVertices(); m++) {
            listaDeUnVerticeATodos.get(m).add(m);
        }
        String costosCaminos = "";
        for (int l = 0; l < listaDeUnVerticeATodos.size(); l++) {
            if (distancia[unVertice][l] == 99999) {
                costosCaminos = costosCaminos + unVertice + " a " + l + " no se puede llegar " + "\n";
            } else if (distancia[unVertice][l] == 0) {
                costosCaminos = " "+costosCaminos + unVertice + " a " + l + " es el mismo vertice" + "\n";
            } else {
                costosCaminos = costosCaminos + unVertice + " a " + l + " cuesta " + distancia[unVertice][l] + " y el camino es "
                        + listaDeUnVerticeATodos.get(l) + "\n";
            }

        }
        return costosCaminos;
    }
}
