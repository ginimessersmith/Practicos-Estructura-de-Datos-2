/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GrafosNoPesados;

/**
 *
 * @author Gino
 */
import Excepciones.ExcepcionAristaNoExiste;
import Excepciones.ExcepcionAristaYaExiste;
import Excepciones.ExcepcionVerticeNoValido;
import java.util.*;

public class DiGrafos extends Grafos {

    public DiGrafos() {
        super();
    }

    public DiGrafos(int nroVertices) {
        super(nroVertices);
    }

    public int cantidadAristas() {
        int cantidadArista = 0;
        for (int i = 0; i < super.listaDeAdyacencia.size(); i++) {
            int longitudListaAdyacencia = super.listaDeAdyacencia.get(i).size();
            cantidadArista = cantidadArista + longitudListaAdyacencia;
        }
        return cantidadArista;
    }

    public void insertarArista(int posicionVerticeOrigen, int posicionVerticeDestino) throws ExcepcionAristaYaExiste {
        if (super.existeAdyacencia(posicionVerticeOrigen, posicionVerticeDestino)) {
            throw new ExcepcionAristaYaExiste();
        }
        List<Integer> adyacenteOrigen = super.listaDeAdyacencia.get(posicionVerticeOrigen);
        adyacenteOrigen.add(posicionVerticeDestino);
        Collections.sort(adyacenteOrigen);
    }

    public void eliminarArista(int posicionVerticeOrigen, int posicionVerticeDestino) throws ExcepcionAristaNoExiste {
        if (!super.existeAdyacencia(posicionVerticeOrigen, posicionVerticeDestino)) {
            throw new ExcepcionAristaNoExiste();
        }
        List<Integer> adyacenteOrigen = super.listaDeAdyacencia.get(posicionVerticeOrigen);
        int posicionDestino = adyacenteOrigen.indexOf(posicionVerticeDestino);
        adyacenteOrigen.remove(posicionVerticeDestino);
    }

    public int gradoDeEntradaDeVertice(int posicionVertice) {
        super.validarVertice(posicionVertice);
        int entradasVertice = 0;
        for (List<Integer> adyacentesDeUnVertice : super.listaDeAdyacencia) {
            for (Integer posAdyacente : adyacentesDeUnVertice) {
                if (posAdyacente == posicionVertice) {
                    entradasVertice++;
                }
            }
        }
        return entradasVertice;
    }

    public int gradoDeSalidaDeVertice(int posicionVertice) {
        return super.gradoVertice(posicionVertice);
    }
}
