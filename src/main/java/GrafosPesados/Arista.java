/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GrafosPesados;

/**
 *
 * @author Gino
 */
public class Arista {

    int verticeOrigen;
    int verticeDestino;
    double peso;

    public Arista(int unVerticeOrigen, int unVerticeDestino, double unPeso) {
        this.verticeOrigen = unVerticeOrigen;
        this.verticeDestino = unVerticeDestino;
        this.peso = unPeso;
    }
}
