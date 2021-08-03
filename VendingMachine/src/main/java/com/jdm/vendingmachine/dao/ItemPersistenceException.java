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
class ItemPersistenceException extends Exception {

    ItemPersistenceException(String msg, Exception e) {
        super(msg);
    }

}
