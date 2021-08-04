package com.jdm.vendingmachine.dao;

import com.jdm.vendingmachine.dto.Change;
import com.jdm.vendingmachine.dto.Item;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Joe McAdams
 * @email joedmcadams@gmail.com
 * 
 */
public class VendingMachineDaoFileImpl implements VendingMachineDao {
    private final String ITEM_FILE;
    private static final String DELIMITER = "::";
    private BigDecimal insertedMoney;
    private HashMap<String, Item> inventory;
    
    public VendingMachineDaoFileImpl(){
        ITEM_FILE = "inventory.txt";
        inventory = new HashMap<>();
    }

    public VendingMachineDaoFileImpl(String ItemTextFile){ //For testing
        ITEM_FILE = ItemTextFile;
        inventory = new HashMap<>();
    }
    
    @Override
    public void setInsertedMoney(BigDecimal money) {
        this.insertedMoney = money;
    }
    
    
    public BigDecimal getInsertedMoney() {
        return insertedMoney;
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
    public void vendItem(String choice){
        this.getItem(choice).vend();
    }

    @Override
    public Change calculateChange(String choice){
        BigDecimal tmp = this.insertedMoney;
        this.insertedMoney.equals(0);       //Set inserted money to zero as change is being returned
        return new Change(tmp.subtract(getItem(choice).getPrice()));
    }
    
    @Override
    public List<Item> getAllItems(){
        return new ArrayList<>(inventory.values());
    }  
    
    
    public Item unmarshallItem(String itemAsText) {
        String[] itemTokens = itemAsText.split(DELIMITER);
        Item itemFromFile = new Item(itemTokens[0], itemTokens[1], itemTokens[2], itemTokens[3]);
        return itemFromFile;
    }
   
    public String marshallItem(Item item){
        String itemText = item.getName() + DELIMITER + item.getPrice() + DELIMITER + item.getStock() + DELIMITER + item.getMenuKey();
        return itemText;
    }
    
    @Override
    public void loadInventory() throws ItemPersistenceException {
        Scanner scanner;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(new BufferedReader(new FileReader(ITEM_FILE)));
        } 
        catch (FileNotFoundException e){
            throw new ItemPersistenceException("Couldn't load items into inventory.");
        }
        
        String currentLine;
        Item currentItem;
   
       
        while (scanner.hasNextLine()){ 
            currentLine = scanner.nextLine();
            currentItem = unmarshallItem(currentLine);
               
            inventory.put(currentItem.getMenuKey(), currentItem);
        }
        // close scanner
        scanner.close();
    }
    
    @Override
    public void writeInventory() throws ItemPersistenceException{
        
        PrintWriter out;

        try{
            out = new PrintWriter(new FileWriter(ITEM_FILE));
        } 
        catch (IOException e){
            throw new ItemPersistenceException("Could not save inventory data.");
        }
        
        String itemAsText;
        List<Item> itemList = this.getAllItems();
        for (Item currentItem : itemList) {
            itemAsText = marshallItem(currentItem);
            out.println(itemAsText);
            out.flush();
        }
        // Clean up
        out.close();
    }


}
