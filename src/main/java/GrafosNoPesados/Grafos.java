/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GrafosNoPesados;

import Excepciones.ExcepcionAristaNoExiste;
import Excepciones.ExcepcionAristaYaExiste;
import Excepciones.ExcepcionNroVerticeNoValido;
import Excepciones.ExcepcionPosicionNoValida;
import Excepciones.ExcepcionVerticeNoValido;
import java.util.*;

/**
 *
 * @author Gino
 */
public class Grafos {

    public List<List<Integer>> listaDeAdyacencia;

    public Grafos() {
        this.listaDeAdyacencia = new ArrayList<>();
    }

    public Grafos(int nroVertices)  {
        if (nroVertices < 0) {
            throw new IllegalArgumentException("Nro de Vertice no valido");
        }
        this.listaDeAdyacencia = new ArrayList<>();
        for (int i = 0; i < nroVertices; i++) {
            this.listaDeAdyacencia.add(new ArrayList<>());
        }
    }

    public void insertarVertice() {
        this.listaDeAdyacencia.add(new ArrayList<>());
    }

    public int cantidadVertices() {
        return this.listaDeAdyacencia.size();
    }

    public int cantidadAristas() {
        int cantidadAristas = 0;
        int cantidadLazos = 0;

        for (int i = 0; i < this.listaDeAdyacencia.size(); i++) {
            List<Integer> adyacenteVertice = this.listaDeAdyacencia.get(i);
            for (Integer posicionAdyacente : adyacenteVertice) {
                if (i == posicionAdyacente) {
                    cantidadLazos++;
                } else {
                    cantidadAristas++;
                }
            }
        }
        return cantidadLazos + (cantidadAristas / 2);
    }

    public void validarVertice(int posicionVertice) {
        if (posicionVertice < 0 || posicionVertice >= this.cantidadVertices()) {
            throw new IllegalArgumentException("Posicion de Vertice no valida");
        }
    }

    public int gradoVertice(int posicionVertice) {
        this.validarVertice(posicionVertice);
        List<Integer> adyacenteVertice = this.listaDeAdyacencia.get(posicionVertice);
        return adyacenteVertice.size();
    }

    public boolean existeAdyacencia(int posicionVerticeOrigen, int posicionVerticeDestino) {
        this.validarVertice(posicionVerticeOrigen);
        this.validarVertice(posicionVerticeDestino);
        List<Integer> adyacenteOrigen = this.listaDeAdyacencia.get(posicionVerticeOrigen);
        return adyacenteOrigen.contains(posicionVerticeDestino);
    }

    public void insertarArista(int posicionVerticeOrigen, int posicionVerticeDestino) throws ExcepcionAristaYaExiste {
        if (this.existeAdyacencia(posicionVerticeOrigen, posicionVerticeDestino)) {
            throw new ExcepcionAristaYaExiste();
        }
        List<Integer> adyacenteOrigen = this.listaDeAdyacencia.get(posicionVerticeOrigen);
        adyacenteOrigen.add(posicionVerticeDestino);
        Collections.sort(adyacenteOrigen);
        if (posicionVerticeOrigen != posicionVerticeDestino) {
            List<Integer> adyacenciaDestino = this.listaDeAdyacencia.get(posicionVerticeDestino);
            adyacenciaDestino.add(posicionVerticeOrigen);
            Collections.sort(adyacenciaDestino);
        }
    }

    public Iterable<Integer> adyacenciaVertice(int posicionVertice) {
        this.validarVertice(posicionVertice);
        List<Integer> adyacenteVertice = this.listaDeAdyacencia.get(posicionVertice);
        Iterable<Integer> iterableDeAdyacentes = adyacenteVertice;
        return iterableDeAdyacentes;
    }

    public void eliminarArista(int posicionVerticeOrigen, int posicionVerticeDestino) throws ExcepcionAristaNoExiste {
        if (!this.existeAdyacencia(posicionVerticeOrigen, posicionVerticeDestino)) {
            throw new ExcepcionAristaNoExiste();
        }
        List<Integer> adyacenteOrigen = this.listaDeAdyacencia.get(posicionVerticeOrigen);
        int posicionDelDestino = adyacenteOrigen.indexOf(posicionVerticeDestino);
        adyacenteOrigen.remove(posicionDelDestino);
        if (posicionVerticeOrigen != posicionVerticeDestino) {
            List<Integer> adyacentesDestino = this.listaDeAdyacencia.get(posicionDelDestino);
            int posicionDelOrigen = adyacentesDestino.indexOf(posicionVerticeOrigen);
            adyacentesDestino.remove(posicionDelOrigen);
        }

    }

    public void eliminarVertice(int verticeEliminar) {
        this.validarVertice(verticeEliminar);
        this.listaDeAdyacencia.remove(verticeEliminar);
        for (List<Integer> adyacenteVertice : this.listaDeAdyacencia) {
            int posicionVerticeAdyacente = adyacenteVertice.indexOf(verticeEliminar);
            if (posicionVerticeAdyacente >= 0) {
                adyacenteVertice.remove(posicionVerticeAdyacente);
            }

            for (int i = 0; i < adyacenteVertice.size(); i++) {
                int posicionAdyacente = adyacenteVertice.get(i);
                if (posicionAdyacente > posicionVerticeAdyacente) {
                    adyacenteVertice.set(i, posicionAdyacente - 1);
                }
            }
        }
    }

}
