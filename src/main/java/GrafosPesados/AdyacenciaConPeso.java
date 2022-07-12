/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GrafosPesados;

/**
 *
 * @author Gino
 */
public class AdyacenciaConPeso {

    private int indexVertice;
    private double peso;

    public AdyacenciaConPeso(int unVertice) {
        this.indexVertice = unVertice;
    }

    public AdyacenciaConPeso(int unVertice, double unPeso) {
        this.indexVertice = unVertice;
        this.peso = unPeso;
    }

    public int getIndexVertice() {
        return this.indexVertice;
    }

    public void setIndexVertice(int unVertice) {
        this.indexVertice = unVertice;
    }

    public double getPeso() {
        return this.peso;
    }

    public void setPeso(double unPeso) {
        this.peso = unPeso;
    }

//    public int hashCode() {
//        int hash = 3;
//        hash = 67 + hash + this.indexVertice;
//        return hash;
//    }

    public boolean equals(Object otro) {
        if (otro == null) {
            return false;
        }
        if (getClass() != otro.getClass()) {
            return false;
        }
        AdyacenciaConPeso other = (AdyacenciaConPeso) otro;
        return this.indexVertice == other.indexVertice;
    }

    public int compareTo(AdyacenciaConPeso vertice) {
        Integer esteVertice = this.indexVertice;
        Integer elOtroVertice = vertice.indexVertice;
        return esteVertice.compareTo(elOtroVertice);
    }
}
