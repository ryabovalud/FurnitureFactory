package com.company.furnitur;

import java.util.HashMap;

public class Item {
    protected int price;
    protected String name;
    protected HashMap<String,Integer> material = new HashMap<>();

    public String getName(){return this.name;}
    public int getPrice(){return this.price;}
    public HashMap<String,Integer> getMaterial(){return this.material;}
}
