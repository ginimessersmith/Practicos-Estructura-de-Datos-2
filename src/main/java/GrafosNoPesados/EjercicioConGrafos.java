/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GrafosNoPesados;

import Excepciones.ExcepcionAristaYaExiste;

/**
 *
 * @author Gino
 */
public class EjercicioConGrafos {
private Grafos grafo;
    private MetodosParaRecorridos metodosRecorridos;

    public EjercicioConGrafos(Grafos unGrafo) {
        this.grafo = unGrafo;
        this.metodosRecorridos = new MetodosParaRecorridos(grafo.cantidadVertices());
    }

    public boolean esConexo() {
        DFS dfs = new DFS(this.grafo, 0);
        if (dfs.hayCaminoATodos()) {
            return true;
        } else {
            return false;
        }
    }

    public int islas() {
        int cantIslas = 1;
        DFS dfs = new DFS(this.grafo, 0);
        for (int i = 0; i < this.grafo.listaDeAdyacencia.size(); i++) {
            if (!dfs.getControlMarcados().esVerticeMarcado(i)) {
                cantIslas++;
                dfs.ejecutarDFS(i);
            }
        }

        return cantIslas;
    }
     public boolean hayCiclo() throws ExcepcionAristaYaExiste  {
        Grafos grafoAuxiliar = new Grafos(grafo.cantidadVertices());
        for(int i = 0; i < grafo.cantidadVertices(); i++) {
            
            if(hayCiclo(grafoAuxiliar, i)) {
                return true;
            }
        }
        return false;
    }

    public boolean hayCiclo(Grafos grafoAuxiliar, int posicionVertice) throws ExcepcionAristaYaExiste {
        
        this.metodosRecorridos.marcarVertice(posicionVertice);
        Iterable<Integer> adyacentesDeVertice = grafo.listaDeAdyacencia.get(posicionVertice);
        for (Integer adyacente : adyacentesDeVertice) {
            if(!metodosRecorridos.esVerticeMarcado(adyacente)) {
                if(!grafoAuxiliar.existeAdyacencia(posicionVertice, adyacente) && posicionVertice != adyacente) {
                    grafoAuxiliar.insertarArista(posicionVertice, adyacente);
                    hayCiclo(grafoAuxiliar, adyacente);
                }
            }
            else {
                if(!grafoAuxiliar.existeAdyacencia(posicionVertice, adyacente) && posicionVertice != adyacente) {
                    return true;
                }
            }
        }

        return false;
    }
    public void elementosIslas() {
        DFS dfs = new DFS(grafo, 0);
        System.out.println(dfs.obtenerRecorrido());
        for (int i = 0; i < grafo.cantidadVertices(); i++) {
            if (!dfs.hayCaminoAVertice(i)) {
                dfs.ejecutarDFS(i);
                DFS dfsAux = new DFS(grafo, i);
                String pos = "" + i;
                System.out.println(pos + dfsAux.obtenerRecorrido());
            }
        }
    }
    
}
