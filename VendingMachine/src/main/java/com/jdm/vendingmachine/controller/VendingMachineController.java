package com.jdm.vendingmachine.controller;

import com.jdm.vendingmachine.dao.InsufficientFundsException;
import com.jdm.vendingmachine.dao.ItemPersistenceException;
import com.jdm.vendingmachine.dao.NoItemInventoryException;
import com.jdm.vendingmachine.dto.Change;
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
    private final VendingMachineServiceLayer service;
    private final VendingMachineView view;
    
    public VendingMachineController(VendingMachineServiceLayer service, VendingMachineView view){
        this.service = service;
        this.view = view;
    }
    
    public void run(){
        boolean exit = false;
        
        
        try{
            service.loadInventory();
            while(!exit){

                view.displayItemMenuChoices(service.getAllItems());
                switch(view.getMainMenuChoice()){
                    case 1:
                        makePurchase();
                        break;
                    case 2:
                        exit = true;
                        break;
                }
            }
            service.writeInventory();
        }
        catch(ItemPersistenceException e){
            view.displayErrorMessage(e.getMessage());
        }
    }

    private void makePurchase() throws ItemPersistenceException {
        Change change = null;
        boolean validInsert = false;
        try{ 
            validInsert = service.setInsertedMoney(view.inputMoney()); 
            change = service.vendItem(view.getItemMenuChoice());
            view.displayVended();
        }
        catch(InsufficientFundsException | NoItemInventoryException | InvalidInputException | OverPayException e){ 
            view.displayErrorMessage(e.getMessage()); 
        }
        finally{
            if(validInsert){
                handleChange(change);
            }
        }
    }

    private void handleChange(Change change) throws ItemPersistenceException{
        if(change == null){ //VM errored out because of lack of funds/stock; return user money
            change = service.returnMoney();
            view.displayRefund(change);
        }
        else{
            view.displayChange(change);
        }
    }
}
