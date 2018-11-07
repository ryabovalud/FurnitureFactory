package com.company;

import java.util.HashMap;

public class Customer {

    private int money;

    public Customer(int money){this.money = money;}

    public int getMoney(){return this.money;}

    public void setMoney(int money){this.money = money;}

    public void requestOrder(HashMap<String,Integer> request, AssemblyRoom assembly, BlankRoom blankRoom){
        assembly.acceptOrder(this,request, blankRoom);
    }

}
