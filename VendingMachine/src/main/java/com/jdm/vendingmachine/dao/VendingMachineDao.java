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
     * Sets the amount of money inserted into the machine as a class variable
     * @param money
     */
    public void setInsertedMoney(BigDecimal money);
    
    /**
     * Returns the insertedMoney as a BigDecimal object and sets insertedMoney class var to 0
     * @return insertedMoney
     */
    public BigDecimal returnInsertedMoney();
    
    /**
     * Returns the object Item that matches the key parameter menuKey
     * @param menuKey
     * @return Item
     */
    public Item getItem(String menuKey);
    
    /**
     * Decrements the stock of the item matching the key parameter choice
     * @param choice
     */
    public void vendItem(String choice);
    
    /**
     * Creates and returns a change object based on insertedMoney var and price of the item
     * @param choice
     * @return
     */
    public Change calculateChange(String choice);

    /**
     * Loads the inventory into hashmap from file
     * @throws ItemPersistenceException
     */
    public void loadInventory() throws ItemPersistenceException;
    
    /**
     * Writes the inventory to text file from hashmap
     * @throws ItemPersistenceException
     */
    public void writeInventory() throws ItemPersistenceException;

    /**
     * Gets a list of all the item objects in the hashmap
     * @return
     */
    public List<Item> getAllItems();

    /**
     * Returns the value of the insertedMoney class var
     * @return
     */
    public BigDecimal getInsertedMoney();

   
    
    
}
