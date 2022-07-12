/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swlc.bolton.notifier.exception;

/**
 *
 * @author athukorala
 */
// custom exception
public class NotifierException extends RuntimeException  {
    
    public NotifierException() {
        super ("Something went wrong");
    }
    public NotifierException(String message) {
        super (message);
    }
}
