/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdm.vendingmachine.dao;

import com.jdm.vendingmachine.dto.Change;
import com.jdm.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Joe
 */
public interface VendingMachineDao {

    /**
     *
     * @param money
     */
    public void setInsertedMoney(BigDecimal money);
    
    public BigDecimal returnInsertedMoney();
    
    public Item getItem(String menuKey);
    
    public void vendItem(String choice);
    
    public Change calculateChange(String choice);

    public void loadInventory() throws ItemPersistenceException;
    
    public void writeInventory() throws ItemPersistenceException;

    public List<Item> getAllItems();

   
    
    
}
