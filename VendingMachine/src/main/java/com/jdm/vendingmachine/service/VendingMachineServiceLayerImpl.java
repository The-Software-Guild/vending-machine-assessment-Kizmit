/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jdm.vendingmachine.service;

import com.jdm.vendingmachine.dao.InsufficientFundsException;
import com.jdm.vendingmachine.dao.ItemPersistenceException;
import com.jdm.vendingmachine.dao.NoItemInventoryException;
import com.jdm.vendingmachine.dao.VendingMachineAuditDao;
import com.jdm.vendingmachine.dao.VendingMachineDao;
import com.jdm.vendingmachine.dto.Change;
import com.jdm.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.List;


/**
 *
 * @author Joe McAdams
 * @email joedmcadams@gmail.com
 * 
 */
public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer {
    private final VendingMachineDao dao;
    private VendingMachineAuditDao auditDao;
    
    public VendingMachineServiceLayerImpl(VendingMachineDao dao, VendingMachineAuditDao auditDao){
        this.dao = dao;
        this.auditDao = auditDao;
    }
    
    @Override
    public boolean setInsertedMoney(BigDecimal money) throws OverPayException, InvalidInputException{
        //Check business rules here: negative values, too large of values
        if(money.compareTo(new BigDecimal("50")) > 0){
            throw new OverPayException("Machine doesn't accept more than $50");
        }
        else if(money.compareTo(new BigDecimal("0")) < 0){
            throw new InvalidInputException("Invalid monetary amount, value is negative");
        }
        //call dao setinsertedmoney
        dao.setInsertedMoney(money);
        return true;
    }

    @Override
    public Change vendItem(String choice) throws InsufficientFundsException, NoItemInventoryException{
        //Using choice, check price of choice <= dao.insertedMoney
        if(dao.returnInsertedMoney().compareTo(dao.getItem(choice).getPrice()) < 0){
            throw new InsufficientFundsException("Not enough money inserted to purchase item.");
        }
        else if(dao.getItem(choice).getStock() == 0){
            throw new NoItemInventoryException("No stock for requested item");
        }
        //If the user can afford and the item exists, remove item from stock, calculate/display the change
        else{
            dao.vendItem(choice);
            return dao.calculateChange(choice);
        }
    }
    
    @Override
    public Change returnMoney(){
        return new Change(dao.returnInsertedMoney());
    }

    @Override
    public void loadInventory() throws ItemPersistenceException{
        try{
            dao.loadInventory();
        }
        catch(ItemPersistenceException e){
            throw e;
        }
    }
    
    @Override
    public void writeInventory() throws ItemPersistenceException{
        try{
            dao.writeInventory();
        }
        catch(ItemPersistenceException e){
            throw e;
        }
    }

    @Override
    public List<Item> getAllItems() {
        return dao.getAllItems();
    }

}
