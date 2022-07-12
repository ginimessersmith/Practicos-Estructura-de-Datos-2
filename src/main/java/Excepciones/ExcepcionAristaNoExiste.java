/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Excepciones;

/**
 *
 * @author Gino
 */
public class ExcepcionAristaNoExiste extends Exception {
    public ExcepcionAristaNoExiste(){
    super("La Arista no Existe");
    }
    public ExcepcionAristaNoExiste(String msj){
    super(msj);
    }
}
