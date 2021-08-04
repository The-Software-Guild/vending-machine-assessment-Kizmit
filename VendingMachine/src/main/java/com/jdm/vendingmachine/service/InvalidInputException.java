package com.jdm.vendingmachine.service;

/**
 *
 * @author Joe McAdams
 * @email joedmcadams@gmail.com
 * 
 */
public class InvalidInputException extends Exception {

    public InvalidInputException(String message) {
        super(message);
    }

}
