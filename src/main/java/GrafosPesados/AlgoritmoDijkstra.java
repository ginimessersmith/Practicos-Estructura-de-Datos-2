/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GrafosPesados;

import GrafosNoPesados.MetodosParaRecorridos;
import java.util.*;

/**
 *
 * @author Gino
 */
public class AlgoritmoDijkstra {

    double INFINITO = Double.MAX_VALUE;
    DiGrafosPesados digrafo;
    List<Double> listaCostos;
    MetodosParaRecorridos MetodosRecorridos;
    List<Integer> predecesores;
    Stack<Integer> pilaCaminos;
    double costoMinimo;

    public AlgoritmoDijkstra(DiGrafosPesados unDigrafo) {
        this.digrafo = unDigrafo;
        listaCostos = new LinkedList<>();
        MetodosRecorridos = new MetodosParaRecorridos(unDigrafo.cantidadDeVertices());
        predecesores = new LinkedList<>();
        pilaCaminos = new Stack<>();
        costoMinimo = INFINITO;
        for (int i = 0; i < digrafo.cantidadDeVertices(); i++) {
            listaCostos.add(INFINITO);
            predecesores.add(-1);
        }
    }

    public double menorNoMarcado(List<Double> listaCostos, MetodosParaRecorridos metodosRecorridos) {
        double menor = Double.MAX_VALUE;
        int i = 0;
        while (i < listaCostos.size()) {
            if (listaCostos.get(i) < menor && !metodosRecorridos.esVerticeMarcado(i)) {
                menor = listaCostos.get(i);
            }
            i++;
        }
        return menor;
    }

    public void caminoMinimo(int verticeOrigen, int verticeDestino) {
        this.listaCostos.set(verticeOrigen, 0.0);
        int verticeActual = verticeOrigen;
        while (verticeActual != verticeDestino && !this.MetodosRecorridos.estanMarcadoTodos()
                && !MetodosRecorridos.esVerticeMarcado(verticeDestino)) {
            double posicionMenor = menorNoMarcado(listaCostos, MetodosRecorridos);
            verticeActual = listaCostos.indexOf(posicionMenor);
            List<Integer> listaDeAdyacentes = (List<Integer>) digrafo.adyancentesDeVertice(verticeActual);
            for (int i = 0; i < listaDeAdyacentes.size(); i++) {
                int posicionActual = listaDeAdyacentes.get(i);
                if (!MetodosRecorridos.esVerticeMarcado(posicionActual)) {
                    predecesores.set(posicionActual, verticeActual);
                }
                if (listaCostos.get(posicionActual) > (listaCostos.get(verticeActual) + digrafo.peso(verticeActual, posicionActual))) {
                    double nuevoCosto = listaCostos.get(verticeActual) + digrafo.peso(verticeActual, posicionActual);
                    listaCostos.set(posicionActual, nuevoCosto);
                }
            }
            MetodosRecorridos.marcarVertice(verticeActual);
        }
        costoMinimo = listaCostos.get(verticeDestino);
        MetodosRecorridos.desmarcarTodo();
        pilaCaminos.push(verticeDestino);
        while (predecesores.get(verticeDestino) != -1 && verticeDestino != verticeOrigen) {
            pilaCaminos.push(predecesores.get(verticeDestino));
            verticeDestino = predecesores.get(verticeDestino);
        }

    }

    public List<Integer> getPredecesores() {
        return predecesores;
    }

    public Stack<Integer> getPilaDeCaminos() {
        return pilaCaminos;
    }

    public double getCostoMinimo(int verticeI, int verticeF) {
        return costoMinimo;

    }

    public String camino() {
        String s = "";
        Stack<Integer> pila = getPilaDeCaminos();
        while (!pila.isEmpty()) {
            s = s + pila.pop()+" " ;
        }
        return s;
    }

    public void caminosYCostosDesdeUnVertice(int verticeInicial) {

        for (int unDestino = 0; unDestino < digrafo.cantidadDeVertices(); unDestino++) {
            String s = "De " + verticeInicial + " a ";
            if (unDestino != verticeInicial) {
                caminoMinimo(verticeInicial, unDestino);
                if (listaCostos.get(unDestino) != INFINITO) {
                    s = s + unDestino + " cuesta " + getCostoMinimo(verticeInicial, unDestino) + " y el camino es [";
                    s = s + camino();
                    System.out.println("Mostrando: ");
                }
                s = s + "]";
                System.out.println(s);
            }

        }
    }
}
