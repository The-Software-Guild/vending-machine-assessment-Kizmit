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
    private final VendingMachineAuditDao auditDao;
    
    public VendingMachineServiceLayerImpl(VendingMachineDao dao, VendingMachineAuditDao auditDao){
        this.dao = dao;
        this.auditDao = auditDao;
    }
    
    @Override
    public boolean setInsertedMoney(BigDecimal money) throws OverPayException, InvalidInputException, ItemPersistenceException{
        //Check business rules here: negative values, too large of values
        if(money.compareTo(new BigDecimal("5")) > 0){
            auditDao.writeAuditEntry("USER INSERTED TOO MUCH MONEY, OVER-PAY EXCEPTION THROWN, FUNDS REJECTED");
            throw new OverPayException("Machine doesn't accept more than $5");
        }
        else if(money.compareTo(new BigDecimal("0")) < 0){
            auditDao.writeAuditEntry("USER INSERTED NEGATIVE MONEY, INVALID INPUT EXCEPTION THROWN, THIEF DENIED");
            throw new InvalidInputException("Invalid monetary amount, value is negative");
        }
        //call dao setinsertedmoney
        dao.setInsertedMoney(money);
        auditDao.writeAuditEntry("USER INSERTED" + dao.getInsertedMoney() +", AMOUNT ADDED TO WALLET");
        return true;
    }

    @Override
    public Change vendItem(String choice) throws InsufficientFundsException, NoItemInventoryException, ItemPersistenceException{
        //Using choice, check price of choice <= dao.insertedMoney
        if(dao.returnInsertedMoney().compareTo(dao.getItem(choice).getPrice()) < 0){
            auditDao.writeAuditEntry("USER DIDN'T INSERT ENOUGH FUNDS, INSUFFICIENT FUNDS EXCEPTION THROWN");
            throw new InsufficientFundsException("Not enough money inserted to purchase item.");
        }
        else if(dao.getItem(choice).getStock() == 0){
            auditDao.writeAuditEntry("NO STOCK FOR REQUESTED ITEM, NO ITEM INVENTORY EXCEPTION THROWN");
            throw new NoItemInventoryException("No stock for requested item");
        }
        //If the user can afford and the item exists, remove item from stock, calculate/display the change
        else{
            dao.vendItem(choice);
            auditDao.writeAuditEntry("ITEM "+ dao.getItem(choice).getName() + " VENDED, CALCULATING AND RETURNING CHANGE");
            return dao.calculateChange(choice);
        }
    }
    
    @Override
    public Change returnMoney() throws ItemPersistenceException{
        auditDao.writeAuditEntry("RETURNING MONEY TO USER");
        return new Change(dao.returnInsertedMoney());
    }

    @Override
    public void loadInventory() throws ItemPersistenceException{
        try{
            dao.loadInventory();
            auditDao.writeAuditEntry("INVENTORY LOADED");
        }
        catch(ItemPersistenceException e){
            throw e;
        }
    }
    
    @Override
    public void writeInventory() throws ItemPersistenceException{
        try{
            dao.writeInventory();
            auditDao.writeAuditEntry("INVENTORY WRITTEN TO FILE");
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
