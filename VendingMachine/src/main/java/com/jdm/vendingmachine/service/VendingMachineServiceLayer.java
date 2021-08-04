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

    /**
     * Takes user amount of money input and calls the Dao to update insertedMoney if
     * the amount is valid according to business rules.
     * @param money
     * @return
     * @throws OverPayException
     * @throws InvalidInputException
     * @throws ItemPersistenceException
     */
    public boolean setInsertedMoney(BigDecimal money) throws OverPayException, InvalidInputException, ItemPersistenceException;
    
    /**
     * Takes the user item choice and calls vendItem() in dao if
     * the item has stock and the user has enough funds
     * @param choice
     * @return
     * @throws InsufficientFundsException
     * @throws NoItemInventoryException
     * @throws ItemPersistenceException
     */
    public Change vendItem(String choice) throws InsufficientFundsException, NoItemInventoryException, ItemPersistenceException;
    
    /**
     * dao returnMoney() pass through method
     * @return
     * @throws ItemPersistenceException
     */
    public Change returnMoney() throws ItemPersistenceException;
    
    /**
     * dao getAllItems() pass through method
     * @return
     */
    public List<Item> getAllItems();
    
    /**
     * dao loadInvenotry() pass through method
     * @throws ItemPersistenceException
     */
    public void loadInventory() throws ItemPersistenceException;
    
    /**
     * dao writeInventory() pass through method
     * @throws ItemPersistenceException
     */
    public void writeInventory() throws ItemPersistenceException;
   
}
