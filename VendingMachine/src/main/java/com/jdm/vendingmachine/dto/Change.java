package com.jdm.vendingmachine.dto;

import java.math.BigDecimal;

/**
 *
 * @author Joe McAdams
 * @email joedmcadams@gmail.com
 * 
 */
public class Change {

    private enum Coins{
        QUARTER (new BigDecimal(".25")), 
        DIME(new BigDecimal(".10")), 
        NICKEL(new BigDecimal(".05")),
        PENNY(new BigDecimal(".01"));
        
        public final BigDecimal value;
        
        private Coins(BigDecimal value){
            this.value = value;
        }
    }
    
    private int numOfQuarters, numOfDimes, numOfNickels, numOfPennies;
    private final BigDecimal amount;
    
    
    public Change(BigDecimal change) {
        this.amount = change;
        calculateCoins();
    }
    private void calculateCoins() {
        final int numOfCoinsIndex = 0;
        final int remainderIndex = 1;
        
        //divideAndRemainders returns BigDecimal array with integer value of division in index 0 and remainder value in index 1
        BigDecimal[] quarters = amount.divideAndRemainder(Coins.QUARTER.value);
        BigDecimal[] dimes = quarters[remainderIndex].divideAndRemainder(Coins.DIME.value);
        BigDecimal[] nickels = dimes[remainderIndex].divideAndRemainder(Coins.NICKEL.value);
        BigDecimal[] pennies = nickels[remainderIndex].divideAndRemainder(Coins.PENNY.value);
        
        this.numOfQuarters = quarters[numOfCoinsIndex].intValue();
        this.numOfDimes = dimes[numOfCoinsIndex].intValue();
        this.numOfNickels = nickels[numOfCoinsIndex].intValue();
        this.numOfPennies = pennies[numOfCoinsIndex].intValue();
    }

    public int getNumOfPennies() {
        return numOfPennies;
    }

    public void setNumOfPennies(int numOfPennies) {
        this.numOfPennies = numOfPennies;
    }

    public BigDecimal getAmount() {
        return amount;
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
        return "Change -> " + "Quarters: " + numOfQuarters + ", Dimes: " + numOfDimes + ", Nickels: " + numOfNickels + ", Pennies: " + numOfPennies + ", Total Change: $" + amount;
    }

    
}
