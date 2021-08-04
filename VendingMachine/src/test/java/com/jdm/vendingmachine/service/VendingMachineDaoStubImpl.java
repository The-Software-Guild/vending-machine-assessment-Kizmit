
package com.jdm.vendingmachine.service;

import com.jdm.vendingmachine.dao.ItemPersistenceException;
import com.jdm.vendingmachine.dao.VendingMachineDao;
import com.jdm.vendingmachine.dto.Change;
import com.jdm.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Joe McAdams
 * @email joedmcadams@gmail.com
 * 
 */
public class VendingMachineDaoStubImpl implements VendingMachineDao {
    public Item item, item2;
    private BigDecimal insertedMoney;
    private final HashMap<String, Item> inventory;
    
    public VendingMachineDaoStubImpl(){
        
        item = new Item("TestItem1", "3.50", "5", "A1");
        item2 = new Item("TestItem2", "2.00", "0", "A2");
        inventory = new HashMap<>();
        inventory.put(item.getMenuKey(), item);
        inventory.put(item2.getMenuKey(), item2);
    }

    
    @Override
    public void setInsertedMoney(BigDecimal money) {
        this.insertedMoney = money;
    }

    @Override
    public BigDecimal returnInsertedMoney() {
        BigDecimal tmp = this.insertedMoney;
        this.insertedMoney.equals(0);       //Set insertedMoney to 0 since it's being returned to user
        return tmp;
    }

    @Override
    public Item getItem(String menuKey) {
        return inventory.get(menuKey);
    }

    @Override
    public void vendItem(String choice) {
        this.getItem(choice).vend();
    }

    @Override
    public Change calculateChange(String choice) {
        BigDecimal tmp = this.insertedMoney;
        this.insertedMoney.equals(0);       //Set inserted money to zero as change is being returned
        return new Change(tmp.subtract(getItem(choice).getPrice()));    
    }

    @Override
    public void loadInventory() throws ItemPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void writeInventory() throws ItemPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Item> getAllItems() {
        return new ArrayList<>(inventory.values());
    }

    @Override
    public BigDecimal getInsertedMoney() {
        return insertedMoney;
    }

}
