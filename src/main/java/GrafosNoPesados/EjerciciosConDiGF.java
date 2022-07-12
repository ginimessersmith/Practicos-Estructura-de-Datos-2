/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GrafosNoPesados;

/**
 *
 * @author Gino
 */
import Excepciones.ExcepcionAristaYaExiste;
import Excepciones.ExcepcionNroVerticeNoValido;
import java.util.*;

public class EjerciciosConDiGF {

    private DiGrafos digrafo;
    private DFS dfs;
    private MetodosParaRecorridos metodosRecorridos;

    public EjerciciosConDiGF(DiGrafos unDiGrafo) {
        this.digrafo = unDiGrafo;
        this.metodosRecorridos = new MetodosParaRecorridos(this.digrafo.cantidadVertices());
    }

    public boolean hayCiclo() {
        for (int i = 0; i < this.digrafo.cantidadVertices(); i++) {
            if (this.hayCiclo(i)) {
                return true;
            }

        }
        return false;
    }

    public boolean hayCiclo(int posicionVertice) {
        this.metodosRecorridos.marcarVertice(posicionVertice);
        Iterable<Integer> adyacentesVerticeEnTurno = digrafo.adyacenciaVertice(posicionVertice);
        for (Integer verticeAdyacente : adyacentesVerticeEnTurno) {
            if (this.metodosRecorridos.esVerticeMarcado(verticeAdyacente)) {
                return true;
            } else {
                if (hayCiclo(verticeAdyacente) == true) {
                    return true;

                }
            }
        }
        this.metodosRecorridos.desmarcarVertice(posicionVertice);
        for (Integer posicionVerticeAdyacente : adyacentesVerticeEnTurno) {
            this.metodosRecorridos.desmarcarVertice(posicionVerticeAdyacente);
        }
        return false;
    }

    public boolean esFuertementeConexo() {
        for (int i = 0; i < digrafo.cantidadVertices(); i++) {
            DFS dfs = new DFS(digrafo, i);
            if (!dfs.hayCaminoATodos()) {
                return false;
            }
        }
        return true;
    }

    public boolean esDebilmenteConexo() {
        Grafos grafo = convertir(digrafo);
        EjercicioConGrafos ejecutar = new EjercicioConGrafos(grafo);
        if (ejecutar.esConexo()) {
            return true;
        }
        return false;
    }

    private Grafos convertir(DiGrafos unDiGrafo) {
        int cantidad = digrafo.cantidadVertices();
        Grafos grafo = new Grafos();
        for (int i = 0; i < digrafo.cantidadVertices(); i++) {
            grafo.insertarVertice();
        }
        for (int i = 0; i < digrafo.cantidadVertices(); i++) {
            List<Integer> L1 = digrafo.listaDeAdyacencia.get(i);
            for (int j = 0; j < L1.size(); j++) {
                grafo.listaDeAdyacencia.get(i).add(L1.get(j));
                grafo.listaDeAdyacencia.get(L1.get(j)).add(i);
            }
        }

        return grafo;
    }

    public int cantidadIslas() {
        Grafos grafo = convertir(digrafo);
        EjercicioConGrafos ejecutar = new EjercicioConGrafos(grafo);
        int cantIslas = ejecutar.islas();
        return cantIslas;
    }

    public void elementosIslas() {
        Grafos grafo = convertir(this.digrafo);
        this.dfs = new DFS(grafo, 0);
        System.out.println(this.dfs.obtenerRecorrido());
        for (int i = 0; i < grafo.cantidadVertices(); i++) {
            if (!this.dfs.hayCaminoAVertice(i)) {
                this.dfs.ejecutarDFS(i);
                DFS dfsAux = new DFS(grafo, i);
                String pos = "" + i;
                System.out.println(pos + dfsAux.obtenerRecorrido());
            }
        }
    }

    public int cantidadConexionesFuertesConexas() throws ExcepcionNroVerticeNoValido, ExcepcionAristaYaExiste {
        int cantidad = 0;
        DFS dfs = new DFS(digrafo, 0);allDFS(dfs);System.out.println(dfs.obtenerRecorrido());
        Iterable<Integer> elRecorrido = dfs.obtenerRecorrido();
        DiGrafos invertido = invertido(digrafo);
        DFS dfsAux = new DFS(invertido, 0);
        cantidad++;
        for (int vertice : elRecorrido) {
            if (!dfsAux.getControlMarcados().esVerticeMarcado(vertice)) {
                dfsAux.ejecutarDFS(vertice);
                cantidad++;
            }
        }
        return cantidad;
    }

    public DiGrafos invertido(DiGrafos unDiGrafo) throws ExcepcionNroVerticeNoValido, ExcepcionAristaYaExiste {
        DiGrafos invertido = new DiGrafos(digrafo.cantidadVertices());
        for (int origen = 0; origen < digrafo.cantidadVertices(); origen++) {
            Iterable<Integer> anteriorDestino = digrafo.adyacenciaVertice(origen);
            for (Integer DestinoOriginal : anteriorDestino) {
                invertido.insertarArista(DestinoOriginal, origen);
            }
        }

        return invertido;
    }

    public void allDFS(DFS dfs) {
        for (int i = 0; i < digrafo.cantidadVertices(); i++) {
            if (!dfs.hayCaminoAVertice(i)) {
                dfs.ejecutarDFS(i);
            }
        }
    }

}
