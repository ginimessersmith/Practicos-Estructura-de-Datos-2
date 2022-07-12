/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Excepciones;

/**
 *
 * @author Gino
 */
public class ExcepcionAristaYaExiste extends Exception {

    public ExcepcionAristaYaExiste() {
        super("La Arista ya existe");
    }

    public ExcepcionAristaYaExiste(String msj) {
        super(msj);
    }
}
