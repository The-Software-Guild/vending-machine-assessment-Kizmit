package com.jdm.vendingmachine.dto;

import java.math.BigDecimal;

/**
 *
 * @author Joe McAdams
 * @email joedmcadams@gmail.com
 * 
 */

public class Item {
    private String name, menuKey;
    private int stock;
    private BigDecimal price;
    
    public Item(String name, String price, String stock, String menuKey){
        this.name = name;
        this.stock = Integer.parseInt(stock);
        this.price = new BigDecimal(price);
        this.menuKey = menuKey;
    }

    public void setMenuKey(String menuKey) {
        this.menuKey = menuKey;
    }

    public String getMenuKey() {
        return menuKey;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStock(String stock) {
        this.stock = Integer.parseInt(stock);
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getStock() {
        return stock;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void vend() {
        stock--;
    }
 
}
