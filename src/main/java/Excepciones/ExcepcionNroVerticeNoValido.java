/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Excepciones;

/**
 *
 * @author Gino
 */
public class ExcepcionNroVerticeNoValido extends Exception {
   public ExcepcionNroVerticeNoValido(){
   super("El nro de Vertices no es valido");
   } 
   
   public ExcepcionNroVerticeNoValido(String msj){
       super(msj);   
   }
}
