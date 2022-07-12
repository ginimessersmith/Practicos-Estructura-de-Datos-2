/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GrafosPesados;

import Excepciones.ExcepcionAristaYaExiste;
import Excepciones.ExcepcionNroVerticeNoValido;
import java.util.*;

/**
 *
 * @author Gino
 */
public class AlgoritmoPrim {

    private GrafosPesados grafoPesado;
    private boolean[] verticesMarcados;

    public AlgoritmoPrim(GrafosPesados unGrafoPesado) {
        this.grafoPesado = unGrafoPesado;
        this.verticesMarcados = new boolean[unGrafoPesado.cantidadDeVertices()];
        for (int i = 0; i < this.verticesMarcados.length; i++) {
           this.verticesMarcados[i]=false;
        }
    }
    
    private boolean estanTodosMarcados(){
        for(int i=0;i<this.verticesMarcados.length;i++){
            if(!verticesMarcados[i]){
                return false;
            }
        }
        return true;
    }
    
    public GrafosPesados grafoPrim() throws ExcepcionNroVerticeNoValido, ExcepcionAristaYaExiste{
        int cantidad=this.grafoPesado.cantidadDeVertices();
        GrafosPesados grafoP=new GrafosPesados(cantidad);
        verticesMarcados[0]=true;
        while(!this.estanTodosMarcados()){
            double maximum=Double.MAX_VALUE;
            int verticeOrigen=0;
            int destino=0;
            List<Integer>marcados=listaDeMarcados();
                for(Integer elemento:marcados){
                    Iterable<AdyacenciaConPeso>adyacentes=this.grafoPesado.adyacentesDeVertice(elemento);
                            for(AdyacenciaConPeso adyacencias:adyacentes){
                                    if(!verticesMarcados[adyacencias.getIndexVertice()] && adyacencias.getPeso()<maximum){
                                       verticeOrigen=elemento;
                                       destino=adyacencias.getIndexVertice();
                                       maximum=adyacencias.getPeso();       
                                         }
                            } 
                }
               grafoP.insertarArista(verticeOrigen, destino, maximum);
               this.verticesMarcados[destino]=true;
        }
        return grafoP;
    }
    
    private List<Integer> listaDeMarcados(){
        List<Integer>lista=new ArrayList<>();
            for(int i=0;i<this.verticesMarcados.length;i++){
                if(verticesMarcados[i]){
                    lista.add(i);
                }
            }
        return lista;
    }

}
