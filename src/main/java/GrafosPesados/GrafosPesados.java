/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GrafosPesados;

import Excepciones.ExcepcionAristaNoExiste;
import Excepciones.ExcepcionAristaYaExiste;
import Excepciones.ExcepcionNroVerticeNoValido;
import GrafosNoPesados.MetodosParaRecorridos;
import java.util.*;

/**
 *
 * @author Gino
 */
public class GrafosPesados {

    protected List<List<AdyacenciaConPeso>> listaDeAdyacencia;
    protected MetodosParaRecorridos metodosRecorridos;

    public GrafosPesados() {
        this.listaDeAdyacencia = new ArrayList<>();
        metodosRecorridos = new MetodosParaRecorridos(listaDeAdyacencia.size());
    }

    public GrafosPesados(int nroDeVertices) {
        if (nroDeVertices <= 0) {
            throw new RuntimeException("El numero de Vertices no es valido");
        }
        this.listaDeAdyacencia = new ArrayList<>();
        for (int i = 0; i < nroDeVertices; i++) {
            this.insertarVertice();
        }
        metodosRecorridos = new MetodosParaRecorridos(listaDeAdyacencia.size());
    }

    public void insertarVertice() {
        List<AdyacenciaConPeso> adyacentesVertice = new ArrayList<>();
        this.listaDeAdyacencia.add(adyacentesVertice);
    }

    public int cantidadDeVertices() {
        return listaDeAdyacencia.size();
    }

    public int gradoDelVertice(int posDeVertice) {
        validarVertice(posDeVertice);
        List<AdyacenciaConPeso> adyacenteVertice = this.listaDeAdyacencia.get(posDeVertice);
        return adyacenteVertice.size();

    }

    public void validarVertice(int posicionDeVertice) {
        if (posicionDeVertice < 0 || posicionDeVertice >= this.cantidadDeVertices()) {
            throw new IllegalArgumentException("Error vertice invalido");
        }
    }

    public void insertarArista(int posicionVerticeOrigen, int posicionVerticeDestino, double unPeso) throws ExcepcionAristaYaExiste {
        if (this.existeAdyacencia(posicionVerticeOrigen, posicionVerticeDestino)) {
            throw new ExcepcionAristaYaExiste();
        }
        List<AdyacenciaConPeso> adyacenciaOrigen = this.listaDeAdyacencia.get(posicionVerticeOrigen);
        AdyacenciaConPeso adyacenciaAlOrigen = new AdyacenciaConPeso(posicionVerticeDestino, unPeso);
        adyacenciaOrigen.add(adyacenciaAlOrigen);

        if (posicionVerticeOrigen != posicionVerticeDestino) {
            List<AdyacenciaConPeso> adyacenciaDelDestino = this.listaDeAdyacencia.get(posicionVerticeDestino);
            AdyacenciaConPeso adyacenciaAlDestino = new AdyacenciaConPeso(posicionVerticeOrigen, unPeso);
            adyacenciaDelDestino.add(adyacenciaAlDestino);

        }
    }

    public boolean existeAdyacencia(int posicionVerticeOrigen, int posicionVerticeDestino) {
        validarVertice(posicionVerticeOrigen);
        validarVertice(posicionVerticeDestino);
        List<AdyacenciaConPeso> adyacenciaOrigen = this.listaDeAdyacencia.get(posicionVerticeOrigen);
        AdyacenciaConPeso adyacenciaAlOrigen = new AdyacenciaConPeso(posicionVerticeDestino);
        return adyacenciaOrigen.contains(adyacenciaAlOrigen);
    }

    public Iterable<Integer> adyancentesDeVertice(int posicionVertice) {
        validarVertice(posicionVertice);
        List<AdyacenciaConPeso> adyaventeVertice = this.listaDeAdyacencia.get(posicionVertice);
        List<Integer> vertices = new ArrayList<>();
        for (AdyacenciaConPeso adyacenteConPeso : adyaventeVertice) {
            vertices.add(adyacenteConPeso.getIndexVertice());
        }
        Iterable<Integer> iterableDeAdyacentes = vertices;
        return iterableDeAdyacentes;
    }

    public Iterable<AdyacenciaConPeso> adyacentesDeVertice(int posicionVertice) {
        validarVertice(posicionVertice);
        List<AdyacenciaConPeso> adyaventeVertice = this.listaDeAdyacencia.get(posicionVertice);
        Iterable<AdyacenciaConPeso> iterableDeAdyacentes = adyaventeVertice;
        return iterableDeAdyacentes;
    }

    public int cantidadDeAristas() {
        int cantidadAristas = 0;
        int cantidadLazos = 0;
        for (int i = 0; i < this.listaDeAdyacencia.size(); i++) {
            List<AdyacenciaConPeso> adyacentesVertice = this.listaDeAdyacencia.get(i);
            for (AdyacenciaConPeso posDeAdyacente : adyacentesVertice) {
                if (i == posDeAdyacente.getIndexVertice()) {
                    cantidadLazos++;
                } else {
                    cantidadAristas++;
                }
            }
        }
        return cantidadLazos + (cantidadAristas / 2);
    }

    public void eliminarVertice(int posicionDeVertice) {
        validarVertice(posicionDeVertice);
        listaDeAdyacencia.remove(posicionDeVertice);
        for (int i = 0; i < listaDeAdyacencia.size(); i++) {
            List<AdyacenciaConPeso> adyacencias = listaDeAdyacencia.get(i);
            for (int j = 0; j < adyacencias.size(); j++) {
                AdyacenciaConPeso posicion = adyacencias.get(posicionDeVertice);
                if (posicion.getIndexVertice() >= 0) {
                    adyacencias.remove(posicion);
                } else if (posicion.getIndexVertice() > posicionDeVertice) {
                    AdyacenciaConPeso posicionAux = adyacencias.get(j);
                    posicionAux.setIndexVertice(posicionAux.getIndexVertice() - 1);
                    adyacencias.set(j, posicionAux);
                }
            }
        }
    }

    public void eliminarArista(int posicionVerticeOrigen, int posicionVerticeDestino) throws ExcepcionAristaNoExiste {
        if (!this.existeAdyacencia(posicionVerticeOrigen, posicionVerticeDestino)) {
            throw new ExcepcionAristaNoExiste();
        }
        List<AdyacenciaConPeso> adyacentesVerticeOrigen = this.listaDeAdyacencia.get(posicionVerticeOrigen);
        AdyacenciaConPeso unAdyacenteConPeso = new AdyacenciaConPeso(posicionVerticeDestino);
        int posicionAristaAEliminar = adyacentesVerticeOrigen.indexOf(unAdyacenteConPeso);
        adyacentesVerticeOrigen.remove(posicionAristaAEliminar);
        if (posicionVerticeOrigen != posicionVerticeDestino) {
            List<AdyacenciaConPeso> adyacentesDelVerticeDestino = this.listaDeAdyacencia.get(posicionVerticeDestino);
            unAdyacenteConPeso = new AdyacenciaConPeso(posicionVerticeOrigen);
            posicionAristaAEliminar = adyacentesDelVerticeDestino.indexOf(unAdyacenteConPeso);
            adyacentesDelVerticeDestino.remove(posicionAristaAEliminar);
        }
    }

    public double peso(int posicionVerticeOrigen, int posicionVerticeDestino) {
        validarVertice(posicionVerticeOrigen);
        validarVertice(posicionVerticeDestino);
        if (!this.existeAdyacencia(posicionVerticeOrigen, posicionVerticeDestino)) {
            throw new IllegalArgumentException("No existe Adyacencia");
        }
        List<AdyacenciaConPeso> adyacenteDelOrigen = this.listaDeAdyacencia.get(posicionVerticeOrigen);
        AdyacenciaConPeso adyacenciaAlOrigen = new AdyacenciaConPeso(posicionVerticeDestino);
        int posicionDeLaAdyacencia = adyacenteDelOrigen.indexOf(adyacenciaAlOrigen);
        return adyacenteDelOrigen.get(posicionDeLaAdyacencia).getPeso();
    }

    public boolean hayCiclo() throws ExcepcionNroVerticeNoValido, ExcepcionAristaYaExiste {
        GrafosPesados grafoAux = new GrafosPesados(this.cantidadDeVertices());
        for (int i = 0; i < listaDeAdyacencia.size(); i++) {
            if (hayCiclo(grafoAux, i)) {
                return true;
            }
        }
        return false;
    }

    public boolean hayCiclo(GrafosPesados grafoAux, int posicionVertice) throws ExcepcionAristaYaExiste {

        this.metodosRecorridos.marcarVertice(posicionVertice);

        Iterable<AdyacenciaConPeso> adyacentesDeVertice = listaDeAdyacencia.get(posicionVertice);
        for (AdyacenciaConPeso adyacentes : adyacentesDeVertice) {
            if (!metodosRecorridos.esVerticeMarcado(adyacentes.getIndexVertice())) {
                if (!grafoAux.existeAdyacencia(posicionVertice, adyacentes.getIndexVertice()) && posicionVertice != adyacentes.getIndexVertice()) {
                    grafoAux.insertarArista(posicionVertice, adyacentes.getIndexVertice(), adyacentes.getPeso());
                    hayCiclo(grafoAux, adyacentes.getIndexVertice());
                }
            } else {
                if (!grafoAux.existeAdyacencia(posicionVertice, adyacentes.getIndexVertice()) && posicionVertice != adyacentes.getIndexVertice()) {
                    return true;
                }
            }
        }

        return false;
    }

    public String mostrar() {
        String grafo = "";
        for (int i = 0; i < listaDeAdyacencia.size(); i++) {
            List<AdyacenciaConPeso> adyacencia = listaDeAdyacencia.get(i);
            grafo = grafo + i + " : " + "[";
            for (int j = 0; j < adyacencia.size(); j++) {
                AdyacenciaConPeso componente = adyacencia.get(j);
                if (j == adyacencia.size() - 1) {
                    grafo = grafo + "[" + componente.getIndexVertice() + "|" + (int) componente.getPeso() + "]";
                } else {
                    grafo = grafo + "[" + componente.getIndexVertice() + "|" + (int) componente.getPeso() + "]" + "-->";
                }
            }
            grafo = grafo + "]" + "\n";
        }
        return grafo;
    }
}
