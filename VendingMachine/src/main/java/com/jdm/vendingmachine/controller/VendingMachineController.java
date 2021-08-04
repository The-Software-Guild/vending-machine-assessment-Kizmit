/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jdm.vendingmachine.controller;

import com.jdm.vendingmachine.dao.InsufficientFundsException;
import com.jdm.vendingmachine.dao.ItemPersistenceException;
import com.jdm.vendingmachine.dao.NoItemInventoryException;
import com.jdm.vendingmachine.service.InvalidInputException;
import com.jdm.vendingmachine.service.OverPayException;
import com.jdm.vendingmachine.service.VendingMachineServiceLayer;
import com.jdm.vendingmachine.ui.VendingMachineView;

/**
 *
 * @author Joe McAdams
 * @email joedmcadams@gmail.com
 * 
 */
public class VendingMachineController {
    private VendingMachineServiceLayer service;
    private VendingMachineView view;
    
    public VendingMachineController(VendingMachineServiceLayer service, VendingMachineView view){
        this.service = service;
        this.view = view;
    }
    
    public void run(){
        boolean exit = false;
        
        
        try{
            service.loadInventory();
            while(!exit){

                //Display vending machine menu
                switch(view.getMainMenuChoice()){
                    case 1:
                        makePurchase();
                        break;
                    case 2:
                        exit = true;
                        //Quit
                }
                //Prompt user to make vending choice
                //Calculate outcome (not enough money, vend item)
                //calculate change
                //display vending machine menu

            }
            service.writeInventory();
        }
        catch(ItemPersistenceException e){
            view.displayErrorMessage(e.getMessage());
        }
    }

    private void makePurchase() {
        
        try{ 
            service.setInsertedMoney(view.inputMoney()); 
            service.vendItem(view.getItemMenuChoice(service.getAllItems()));
        }
        catch(InsufficientFundsException | NoItemInventoryException |InvalidInputException | OverPayException e){ 
            view.displayErrorMessage(e.getMessage()); 
        }
    }
}
