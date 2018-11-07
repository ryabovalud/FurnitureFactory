package com.company.furnitur;

public class TabouretGood extends Item {
    public TabouretGood(){
        this.price = 1500;
        this.name = "Табурет Хороший";

        material.put ("Ножка",4);
        material.put("Дюбель",4);
        material.put ("Доска",1);
    }
}
