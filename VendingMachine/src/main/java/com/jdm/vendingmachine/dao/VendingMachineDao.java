/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdm.vendingmachine.dao;

import java.math.BigDecimal;

/**
 *
 * @author Joe
 */
public interface VendingMachineDao {
    public void setInsertedMoney();

    public void getItem();
    
    public void vendItem();
    
    public BigDecimal calculateChange();
    
    
}
