/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GrafosNoPesados;

import java.util.*;

/**
 *
 * @author Gino
 */
public class MetodosParaRecorridos {

    private List<Boolean> marcados;

    public MetodosParaRecorridos(int cantidadVertices) {
        this.marcados = new ArrayList<>();
        for (int i = 0; i < cantidadVertices; i++) {
            this.marcados.add(Boolean.FALSE);
        }
    }

    public void marcarVertice(int posicionVertice) {
        this.marcados.set(posicionVertice, Boolean.TRUE);
    }

    public void desmarcarVertice(int posicionVertice) {
        this.marcados.set(posicionVertice, Boolean.FALSE);
    }

    public boolean esVerticeMarcado(int vertice) {
        return this.marcados.get(vertice);
    }

    public void desmarcarTodo() {
        for (int i = 0; i < this.marcados.size(); i++) {
            this.marcados.set(i, Boolean.FALSE);
        }
    }

    public boolean estanMarcadoTodos() {
        for (int i = 0; i < this.marcados.size(); i++) {
            if (this.marcados.get(i) == false) {
                return false;
            }
        }
        return true;
    }
}
