/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jdm.vendingmachine.ui;

import com.jdm.vendingmachine.dto.Item;
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
        io.print("=====MAIN MENU OPTIONS=====");
        io.print("1. Purchase");
        io.print("2. Quit");
        return io.readInt("Select from the above menu options:", 1,2);
    }

    public BigDecimal inputMoney() {
        return new BigDecimal(io.readString("Insert money: ")).setScale(MONEY_SCALE, RoundingMode.HALF_UP);
    }

    public void displayErrorMessage(String message) {
        io.print(message);
    }



    public String getItemMenuChoice(List<Item> allItems) {
        String fItemString;
        io.print("=====VENDING OPTIONS=====");
        for(Item item : allItems){
            fItemString = String.format("%-10s%-30s%-5s%-3s\n", item.getMenuKey(), item.getName(), item.getPrice(), item.getStock());
            io.print(fItemString);
        }
        return io.readString("Select from the above menu options:");
    }
    
}
