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

public class BFS {

    private MetodosParaRecorridos metodosMarcados;
    private Grafos grafo;
    private List<Integer> recorrido;

    public BFS(Grafos unGrafo, int posicionVertice) {
        this.grafo = unGrafo;
        this.grafo.validarVertice(posicionVertice);
        this.recorrido = new ArrayList<>();
        this.metodosMarcados = new MetodosParaRecorridos(this.grafo.cantidadVertices());
        ejecutarBFS(posicionVertice);
    }

    public void ejecutarBFS(int posicionVertice) {
        Queue<Integer> colaVertices = new LinkedList<>();
        colaVertices.offer(posicionVertice);
        this.metodosMarcados.marcarVertice(posicionVertice);
        do {
            int posicionVerticeEnTurno = colaVertices.poll();
            this.recorrido.add(posicionVerticeEnTurno);
            Iterable<Integer> adyacenteVerticeEnTurno = this.grafo.adyacenciaVertice(posicionVerticeEnTurno);
            for (Integer posicionVerticeAdyacente : adyacenteVerticeEnTurno) {
                if (!this.metodosMarcados.esVerticeMarcado(posicionVerticeAdyacente)) {
                    colaVertices.offer(posicionVerticeAdyacente);
                    this.metodosMarcados.marcarVertice(posicionVerticeAdyacente);
                }
            }
        } while (!colaVertices.isEmpty());
    }

    public boolean hayCaminoVertice(int vertice) {
        grafo.validarVertice(vertice);
        return this.metodosMarcados.esVerticeMarcado(vertice);
    }

    public Iterable<Integer> obtenerRecorrido() {
        return this.recorrido;
    }
}
