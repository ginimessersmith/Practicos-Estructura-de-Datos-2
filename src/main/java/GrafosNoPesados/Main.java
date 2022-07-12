/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GrafosNoPesados;

import Excepciones.ExcepcionAristaNoExiste;
import Excepciones.ExcepcionAristaYaExiste;
import Excepciones.ExcepcionNroVerticeNoValido;
import GrafosPesados.AlgoritmoDijkstra;
import GrafosPesados.AlgoritmoFloydWarshall;
import GrafosPesados.AlgoritmoKruskal;
import GrafosPesados.AlgoritmoPrim;
import GrafosPesados.DiGrafosPesados;
import GrafosPesados.GrafosPesados;

/**
 *
 * @author Gino
 */
public class Main {

    public static void main(String[] args) throws ExcepcionNroVerticeNoValido, ExcepcionAristaYaExiste, ExcepcionAristaNoExiste {
        System.out.println("Practico de Grafos-Estructura de Datos 2");
        Grafos grafo = new Grafos(10);
        DiGrafos digrafo = new DiGrafos(10);
        MatrizCaminos matriz = new MatrizCaminos(digrafo);
        EjerciciosConDiGF ejemplos = new EjerciciosConDiGF(digrafo);
        EjercicioConGrafos ejecutar = new EjercicioConGrafos(grafo);
        AlgoritmoDeWarshall ejecutarWarshall = new AlgoritmoDeWarshall(digrafo);
        DiGrafosPesados digrafo1 = new DiGrafosPesados(10);
        GrafosPesados grafoPesado1 = new GrafosPesados(10);
        AlgoritmoFloydWarshall floyWarshall = new AlgoritmoFloydWarshall(digrafo1);
        AlgoritmoDijkstra dijkstra = new AlgoritmoDijkstra(digrafo1);
        AlgoritmoKruskal kruskal1 = new AlgoritmoKruskal(grafoPesado1);
        AlgoritmoPrim prim1 = new AlgoritmoPrim(grafoPesado1);

        grafo.insertarArista(0, 1);
        grafo.insertarArista(1, 3);
        grafo.insertarArista(1, 2);
        grafo.insertarArista(2, 4);
        grafo.insertarArista(2, 3);
        grafo.insertarArista(5, 6);
        grafo.insertarArista(6, 7);
        grafo.insertarArista(7, 8);
        grafo.insertarArista(8, 0);
        grafo.insertarArista(8, 9);
        grafo.insertarArista(9, 5);

        grafoPesado1.insertarArista(0, 1, 10);
        grafoPesado1.insertarArista(1, 2, 11);
        grafoPesado1.insertarArista(2, 3, 50);
        grafoPesado1.insertarArista(3, 1, 40);
        grafoPesado1.insertarArista(4, 7, 80);
        grafoPesado1.insertarArista(5, 1, 30);
        grafoPesado1.insertarArista(6, 2, 33);
        grafoPesado1.insertarArista(7, 1, 20);
        grafoPesado1.insertarArista(8, 3, 70);
        grafoPesado1.insertarArista(9, 1, 70);

        digrafo.insertarArista(0, 1);
        digrafo.insertarArista(0, 2);
        digrafo.insertarArista(1, 7);
        digrafo.insertarArista(2, 3);
        digrafo.insertarArista(3, 6);
        digrafo.insertarArista(4, 7);
        digrafo.insertarArista(4, 3);
        digrafo.insertarArista(5, 6);
        digrafo.insertarArista(6, 7);
        digrafo.insertarArista(6, 4);
        digrafo.insertarArista(7, 8);
        digrafo.insertarArista(7, 0);
        digrafo.insertarArista(8, 9);
        digrafo.insertarArista(8, 2);
        digrafo.insertarArista(9, 7);
        digrafo.insertarArista(9, 4);

        digrafo1.insertarArista(0, 1, 5);
        digrafo1.insertarArista(0, 2, 20);
        digrafo1.insertarArista(0, 7, 33);
        digrafo1.insertarArista(1, 2, 22);
        digrafo1.insertarArista(1, 0, 15);
        digrafo1.insertarArista(1, 8, 9);
        digrafo1.insertarArista(2, 6, 4);
        digrafo1.insertarArista(2, 3, 5);
        digrafo1.insertarArista(3, 2, 88);
        digrafo1.insertarArista(3, 1, 20);
        digrafo1.insertarArista(4, 3, 7);
        digrafo1.insertarArista(4, 8, 10);
        digrafo1.insertarArista(5, 7, 56);
        digrafo1.insertarArista(5, 9, 23);
        digrafo1.insertarArista(6, 3, 44);
        digrafo1.insertarArista(7, 4, 40);
        digrafo1.insertarArista(8, 5, 67);
        digrafo1.insertarArista(9, 3, 12);

        System.out.println("Ejercicio 3 : hay ciclo en el DiGrafo?: " + ejemplos.hayCiclo());
        System.out.println("Ejercicio 4: hay ciclo en el DiGrafo?, usando la matriz de caminos: " + matriz.hayCiclo());
        System.out.println("Ejercicio 5: retornar los elementos de las islas de DiGrafo");
        ejemplos.elementosIslas();
        System.out.println("Ejercicio 6: Encontrar la matriz de caminos");
        matriz.mostrarMatriz(matriz.matrizDeCaminos());
        System.out.println("Ejercicio 7: Metodo DiGrafo para encontrar si es debilmente conexo: " + ejemplos.esDebilmenteConexo());
        System.out.println("Ejercicio 8: Metodo DiGrafo para encontrar si es fuertemente conexo: " + ejemplos.esFuertementeConexo());
        System.out.println("Ejercicio 9: Verificar si hay ciclos en un grafo: " + ejecutar.hayCiclo());
        System.out.println("Ejercicio 10: Obtener los elementos de las islas de un grafo: ");
        ejecutar.elementosIslas();
        System.out.println("Ejercicio 11: Numero de islas en un DiGrafo: " + ejemplos.cantidadIslas());
        System.out.println("Ejercicio 12: Implementar el algoritmo de Warshall");
        ejecutarWarshall.mostrarMatriz(ejecutarWarshall.algoritmoWharsall());
        System.out.println("Ejercicio 13: mostrar costos y caminos mediante el algoritmo FloydWarshall" + floyWarshall.mostrarCostosYCaminos(0));
        System.out.println("\n" + "Ejercicio 14: Cuantas componentes fuertes conexas existen: " + ejemplos.cantidadConexionesFuertesConexas());
        System.out.println("Ejercicio 15: Algortimo de Dijkstra, mostrar con que vertice hay caminos de costo minimo y cuales son sus caminos");
        dijkstra.caminosYCostosDesdeUnVertice(0);
        System.out.println("Ejercicio 16: Algortimo de Kruskal, Grafo encontrado: ");
        kruskal1.ejecutarKruskal();
        System.out.println("Ejercicio 17: Algortimo de Prim, Grafo encontrado: ");
        prim1.grafoPrim().mostrar();
        System.out.println("--------------------------------------------------------------------------------------------------");
    }
}
