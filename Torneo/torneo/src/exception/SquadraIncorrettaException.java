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
public class SquadraIncorrettaException extends RuntimeException{
    public SquadraIncorrettaException(String testo){
        super(testo);
    }
}
