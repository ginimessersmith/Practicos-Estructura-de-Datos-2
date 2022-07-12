/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GrafosPesados;

import Excepciones.ExcepcionAristaYaExiste;
import java.util.*;

/**
 *
 * @author Gino
 */
public class DiGrafosPesados extends GrafosPesados {

    public DiGrafosPesados() {
        super();
    }

    public DiGrafosPesados(int nroVertices) {
        super(nroVertices);
    }

    public int cantidadDeAristas() {
        int cantidad = 0;
        for (int i = 0; i < this.listaDeAdyacencia.size(); i++) {
            List<AdyacenciaConPeso> adyacencias = listaDeAdyacencia.get(i);
            cantidad = cantidad + adyacencias.size();
        }
        return cantidad;
    }

    public void eliminarArista(int posicionVerticeOrigen, int posicionVerticeDestino) {
        if (!this.existeAdyacencia(posicionVerticeOrigen, posicionVerticeDestino)) {
            throw new IllegalArgumentException("Error, la arista ya existe");
        }
        List<AdyacenciaConPeso> adyacenteOrigen = this.listaDeAdyacencia.get(posicionVerticeOrigen);
        AdyacenciaConPeso adyacenciaAlOrigen = new AdyacenciaConPeso(posicionVerticeDestino, 0);
        int posicionDelDestino = adyacenteOrigen.indexOf(adyacenciaAlOrigen);
        adyacenteOrigen.remove(posicionDelDestino);
    }

    public void insertarArista(int posicionVerticeOrigen, int posicionVerticeDestino, double unPeso) {
        if (this.existeAdyacencia(posicionVerticeOrigen, posicionVerticeDestino)) {
            throw new RuntimeException("La lista ya existe");
        }
        List<AdyacenciaConPeso> adyacenciaOrigen = this.listaDeAdyacencia.get(posicionVerticeOrigen);
        AdyacenciaConPeso adyacenciaAlOrigen = new AdyacenciaConPeso(posicionVerticeDestino, unPeso);
        adyacenciaOrigen.add(adyacenciaAlOrigen);
    }

    public int gradoDeEntrdaDeVertice(int posicionDeVertice) {
        super.validarVertice(posicionDeVertice);
        int entradaDeVertice = 0;
        for (List<AdyacenciaConPeso> adyacentesDeVertice : super.listaDeAdyacencia) {
            for (AdyacenciaConPeso posDeAdyacente : adyacentesDeVertice) {
                if (posDeAdyacente.getIndexVertice() == posicionDeVertice) {
                    entradaDeVertice++;
                }
            }
        }
        return entradaDeVertice;
    }

    public int gradoDeSalidaDeVertice(int posicionDeVertice) {
        return super.gradoDelVertice(posicionDeVertice);
    }

}
