package com.company.furnitur;

import com.company.furnitur.Item;

import java.util.HashMap;

public class Kitchen extends Item {
    public Kitchen(){
        this.price = 40000;
        this.name = "Кухня";

        material.put ("ДСП плита",10);
        material.put ("Ножка",12);
        material.put("Дюбель",12);
        material.put ("Доска",6);
    }

}
