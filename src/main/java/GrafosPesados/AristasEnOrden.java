/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GrafosPesados;

/**
 *
 * @author Gino
 */
import java.util.*;

public class AristasEnOrden {

    private GrafosPesados grafo;

    public AristasEnOrden(GrafosPesados unGrafo) {
        this.grafo = unGrafo;
    }

    public LinkedList<Arista> aristasDelGrafo() {
        LinkedList<Arista> aristas = new LinkedList();
        for (int i = 0; i < grafo.cantidadDeVertices(); i++) {
            List<AdyacenciaConPeso> listaDeAdyacentesDeVertice = grafo.listaDeAdyacencia.get(i);
            for (int j = 0; j < listaDeAdyacentesDeVertice.size(); j++) {
                Arista arista = new Arista(i, listaDeAdyacentesDeVertice.get(j).getIndexVertice(), listaDeAdyacentesDeVertice.get(j).getPeso());
                aristas.add(arista);
            }
        }
        EliminarRepetidas(aristas);
        aristas.sort(Comparator.comparing(arista -> arista.peso));
        return aristas;
    }

    private int posicionDelRepetido(Arista arista, LinkedList<Arista> aristaGrafo) {
        int posicion = -1;
        int verticeOrigen = arista.verticeOrigen;
        int verticeDestino = arista.verticeDestino;
        for (int i = 0; i < aristaGrafo.size(); i++) {
            Arista aristaActual = aristaGrafo.get(i);
            if ((aristaActual.verticeDestino == arista.verticeOrigen)
                    && (aristaActual.verticeOrigen == arista.verticeDestino)) {
                posicion = i;
            }
        }
        return posicion;
    }

    public void EliminarRepetidas(LinkedList<Arista> aristasGrafo) {
        for (int i = 0; i < aristasGrafo.size(); i++) {
            int pos = posicionDelRepetido(aristasGrafo.get(i), aristasGrafo);
            aristasGrafo.remove(pos);
        }
    }

}
