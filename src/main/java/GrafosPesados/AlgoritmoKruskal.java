/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GrafosPesados;

import Excepciones.ExcepcionAristaNoExiste;
import Excepciones.ExcepcionAristaYaExiste;
import Excepciones.ExcepcionNroVerticeNoValido;
import java.util.*;

/**
 *
 * @author Gino
 */
public class AlgoritmoKruskal {

    private GrafosPesados grafoPesado;

    public AlgoritmoKruskal(GrafosPesados unGrafoPesado) {
        this.grafoPesado = unGrafoPesado;
    }

    public GrafosPesados ejecutarKruskal() throws ExcepcionNroVerticeNoValido, ExcepcionAristaYaExiste, ExcepcionAristaNoExiste {
        AristasEnOrden ejecutar = new AristasEnOrden(this.grafoPesado);
        GrafosPesados grafoAux = new GrafosPesados(this.grafoPesado.cantidadDeVertices());
        LinkedList<Arista> arista = ejecutar.aristasDelGrafo();
        for (int i = 0; i < arista.size(); i++) {
            grafoAux.insertarArista(arista.get(i).verticeOrigen, arista.get(i).verticeDestino, arista.get(i).peso);
            if (grafoAux.hayCiclo()) {
                System.out.println("Existe un ciclo al insertar " + arista.get(i).verticeOrigen +" - " +arista.get(i).verticeDestino);
                grafoAux.eliminarArista(arista.get(i).verticeOrigen, arista.get(i).verticeDestino);
            }
            grafoAux.metodosRecorridos.desmarcarTodo();
        }
        return grafoAux;
    }

}
