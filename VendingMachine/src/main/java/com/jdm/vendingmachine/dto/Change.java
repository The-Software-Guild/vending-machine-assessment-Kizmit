/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jdm.vendingmachine.dto;

import java.math.BigDecimal;

/**
 *
 * @author Joe McAdams
 * @email joedmcadams@gmail.com
 * 
 */
public class Change {
    private enum Coins{ QUARTER, DIME, NICKEL};
    int numOfQuarters, numOfDimes, numOfNickels;
    BigDecimal amount;
    public Change(BigDecimal change) {
        this.amount = change;
    }

    public int getNumOfQuarters() {
        return numOfQuarters;
    }

    public int getNumOfDimes() {
        return numOfDimes;
    }

    public int getNumOfNickels() {
        return numOfNickels;
    }

    public void setNumOfQuarters(int numOfQuarters) {
        this.numOfQuarters = numOfQuarters;
    }

    public void setNumOfDimes(int numOfDimes) {
        this.numOfDimes = numOfDimes;
    }

    public void setNumOfNickels(int numOfNickels) {
        this.numOfNickels = numOfNickels;
    }

    @Override
    public String toString() {
        return "Change{" + "numOfQuarters=" + numOfQuarters + ", numOfDimes=" + numOfDimes + ", numOfNickels=" + numOfNickels + '}';
    }
    
    
}
