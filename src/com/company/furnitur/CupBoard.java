package com.company.furnitur;

public class CupBoard extends Item {
    public CupBoard(){
        this.price = 7000;
        this.name = "Шкаф";

        material.put ("ДСП плита",1);
        material.put("Дюбель",8);
        material.put ("Доска",4);
    }
}
