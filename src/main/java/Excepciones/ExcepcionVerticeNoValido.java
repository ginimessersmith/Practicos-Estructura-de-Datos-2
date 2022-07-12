/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Excepciones;

/**
 *
 * @author Gino
 */
public class ExcepcionVerticeNoValido extends Exception {

    public ExcepcionVerticeNoValido() {
        super("Vertice No Valido");
    }

    public ExcepcionVerticeNoValido(String msj) {
        super(msj);
    }
}
