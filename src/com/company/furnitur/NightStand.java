package com.company.furnitur;

public class NightStand extends Item {
    public NightStand(){
        this.price = 3000;
        this.name = "Тумба";

        material.put ("ДСП плита",2);
        material.put("Дюбель",12);
        material.put ("Доска",2);
    }
}
