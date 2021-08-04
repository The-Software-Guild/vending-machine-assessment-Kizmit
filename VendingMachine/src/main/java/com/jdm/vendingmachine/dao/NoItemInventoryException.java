package com.jdm.vendingmachine.dao;

/**
 *
 * @author Joe McAdams
 * @email joedmcadams@gmail.com
 * 
 */
public class NoItemInventoryException extends Exception{

    public NoItemInventoryException(String message) {
        super(message);
    }

}
