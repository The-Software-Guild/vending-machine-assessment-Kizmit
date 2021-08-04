package com.jdm.vendingmachine.ui;

import com.jdm.vendingmachine.dto.Change;
import com.jdm.vendingmachine.dto.Item;
import com.jdm.vendingmachine.service.InvalidInputException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 *
 * @author Joe McAdams
 * @email joedmcadams@gmail.com
 * 
 */
public class VendingMachineView {
    private UserIO io;
    private static final int MONEY_SCALE = 2;

    public VendingMachineView(UserIO io) {
        this.io = io;
    }

    public int getMainMenuChoice() {
        io.print("=====ACTIONS=====");
        io.print("1. Make a purchase");
        io.print("2. Quit");
        return io.readInt("Select an action: ", 1,2);
    }

    public BigDecimal inputMoney() throws InvalidInputException{
        try{
            return new BigDecimal(io.readString("Insert money: ")).setScale(MONEY_SCALE, RoundingMode.HALF_UP);
        }
        catch(NumberFormatException e){
            throw new InvalidInputException("That is not a valid numerical value");
        }
    }

    public void displayErrorMessage(String message) {
        io.print(message);
    }


    public void displayItemMenuChoices(List<Item> allItems) {
        String fItemString, fItemHeader;
        fItemHeader = String.format("%-10s%-30s%-10s%-5s\n", "Button", "Item Name", "Price", "Stock");
        io.print("================VENDING OPTIONS================");
        io.print(fItemHeader);
        for(Item item : allItems){
            fItemString = String.format("%-10s%-30s$%-10s%-5s\n", item.getMenuKey(), item.getName(), item.getPrice(), item.getStock());
            io.print(fItemString);
        }
    }
    
    public String getItemMenuChoice(){
        return io.readString("Select an item from the vending options:");
    }

    public void displayChange(Change change) {
        io.print(change.toString());
    }

    public void displayVended() {
        io.print("ITEM VENDED. RETRIEVE ITEM FROM THE OPENING.");
    }

    public void displayRefund(Change change) {
        io.print("$" + change.getAmount() + " has been refunded to you.");
    }
    
}
