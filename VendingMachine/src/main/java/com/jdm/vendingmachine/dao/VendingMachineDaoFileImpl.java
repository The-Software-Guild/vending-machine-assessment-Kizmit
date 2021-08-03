/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jdm.vendingmachine.dao;

import com.jdm.vendingmachine.dto.Item;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
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
    private HashMap<String, Item> inventory;
    
    public VendingMachineDaoFileImpl(){
        ITEM_FILE = "inventory.txt";
    }

    public VendingMachineDaoFileImpl(String ItemTextFile){ //For testing
        ITEM_FILE = ItemTextFile;
    }
    
    @Override
    public void setInsertedMoney() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void getItem() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void vendItem() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BigDecimal calculateChange() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private List<Item> getAllItems() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }  
    
    public Item unmarshallItem(String itemAsText) {
        String[] itemTokens = itemAsText.split(DELIMITER);
        Item itemFromFile = new Item(itemTokens[0], itemTokens[1], itemTokens[2]);
        return itemFromFile;
    }
   
    public String marshallItem(Item item)
    {
        String itemText = item.getName() + DELIMITER + item.getPrice() + DELIMITER + item.getStock();
        
        return itemText;
    }
    
    private void loadInventory() throws ItemPersistenceException {
        Scanner scanner;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(new BufferedReader(new FileReader(ITEM_FILE)));
        } 
        catch (FileNotFoundException e){
            throw new ItemPersistenceException("Couldn't load items into inventory.", e);
        }
        
        String currentLine;
        Item currentItem;
        // Go through ITEM_FILE line by line, decoding each line into a 
        // Student object by calling the unmarshallStudent method.
        // Process while we have more lines in the file
       
        while (scanner.hasNextLine()){
            // get the next line in the file
            currentLine = scanner.nextLine();
            // unmarshall the line into a Student
            currentItem = unmarshallItem(currentLine);

            // We are going to use the student id as the map key for our student object.
            // Put currentStudent into the map using student id as the key
            inventory.put(currentItem.getName(), currentItem);
        }
        // close scanner
        scanner.close();
    }
    
    private void writeInventory() throws ItemPersistenceException{
        
        PrintWriter out;

        try{
            out = new PrintWriter(new FileWriter(ITEM_FILE));
        } 
        catch (IOException e){
            throw new ItemPersistenceException("Could not save inventory data.", e);
        }
        
        String itemAsText;
        List<Item> itemList = this.getAllItems();
        for (Item currentItem : itemList) {
            // turn a Student into a String
            itemAsText = marshallItem(currentItem);
            // write the Student object to the file
            out.println(itemAsText);
            // force PrintWriter to write line to the file
            out.flush();
        }
        // Clean up
        out.close();
    }


}
