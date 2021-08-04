/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jdm.vendingmachine.dao;

import java.io.FileNotFoundException;

/**
 *
 * @author Joe McAdams
 * @email joedmcadams@gmail.com
 * 
 */
public class ItemPersistenceException extends Exception {

    public ItemPersistenceException(String message) {
        super(message);
    }

}
