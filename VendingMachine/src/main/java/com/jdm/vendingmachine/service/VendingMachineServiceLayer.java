/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdm.vendingmachine.service;

import com.jdm.vendingmachine.dao.InsufficientFundsException;
import com.jdm.vendingmachine.dao.ItemPersistenceException;
import com.jdm.vendingmachine.dao.NoItemInventoryException;
import com.jdm.vendingmachine.dto.Change;
import com.jdm.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Joe
 */
public interface VendingMachineServiceLayer {
    
    public boolean setInsertedMoney(BigDecimal money) throws OverPayException, InvalidInputException, ItemPersistenceException;
    
    public Change vendItem(String choice) throws InsufficientFundsException, NoItemInventoryException, ItemPersistenceException;
    
    public Change returnMoney() throws ItemPersistenceException;
    
    public List<Item> getAllItems();
    
    public void loadInventory() throws ItemPersistenceException;
    
    public void writeInventory() throws ItemPersistenceException;
   
}
