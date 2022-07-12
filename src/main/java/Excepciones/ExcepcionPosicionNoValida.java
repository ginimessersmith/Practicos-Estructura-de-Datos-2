/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Excepciones;

/**
 *
 * @author Gino
 */
public class ExcepcionPosicionNoValida extends Exception{
     public ExcepcionPosicionNoValida() {
        super("Vertice No Valido");
    }

    public ExcepcionPosicionNoValida(String msj) {
        super(msj);
    }
}
