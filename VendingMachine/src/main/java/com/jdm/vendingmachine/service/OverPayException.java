package com.jdm.vendingmachine.service;

/**
 *
 * @author Joe McAdams
 * @email joedmcadams@gmail.com
 * 
 */
public class OverPayException extends Exception {

    public OverPayException(String message) {
        super(message);
    }

}
