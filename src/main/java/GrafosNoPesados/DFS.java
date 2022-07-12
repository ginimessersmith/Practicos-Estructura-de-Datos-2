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
public class DFS {
    private MetodosParaRecorridos metodosRecorrido;
    private Grafos grafo;
    private List<Integer> recorrido;
    
    public DFS(Grafos unGrafo, int posicionVertice ){
        this.grafo =unGrafo;
        this.grafo.validarVertice(posicionVertice);
        this.recorrido = new ArrayList<>();
        this.metodosRecorrido = new MetodosParaRecorridos(this.grafo.cantidadVertices()); //ya esta todo desmarcado
        ejecutarDFS(posicionVertice);
    }
    public void ejecutarDFS(int posicionVertice) {
        this.metodosRecorrido.marcarVertice(posicionVertice);
        recorrido.add(posicionVertice);
        Iterable<Integer> adyacentesVerticeEnTurno = grafo.adyacenciaVertice(posicionVertice);
        for(Integer posVerticeAdyacente : adyacentesVerticeEnTurno){
            if(!this.metodosRecorrido.esVerticeMarcado(posVerticeAdyacente)){
                ejecutarDFS(posVerticeAdyacente);
            }
        }
        
    }
    public boolean hayCaminoAVertice(int posVertice){
        this.grafo.validarVertice(posVertice);
        return this.metodosRecorrido.esVerticeMarcado(posVertice);
    }
    public Iterable<Integer> obtenerRecorrido(){
        return this.recorrido;
    }
    public boolean hayCaminoATodos(){
        return this.metodosRecorrido.estanMarcadoTodos();
    }
    
    public MetodosParaRecorridos metodosRecorrido() {
        return this.metodosRecorrido;
    }
    
    public MetodosParaRecorridos getControlMarcados() {
        return metodosRecorrido;
    }
}
