/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
