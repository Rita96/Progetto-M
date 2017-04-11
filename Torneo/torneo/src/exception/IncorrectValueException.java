/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exception;

/**
 *
 * @author Giulia
 */
public class IncorrectValueException extends RuntimeException {
    public IncorrectValueException(String testo){
        super(testo);
    }
}
