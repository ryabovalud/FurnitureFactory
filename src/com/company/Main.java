package com.company;

import com.company.furnitur.*;
import com.company.material.Board;
import com.company.material.ChipBoard;
import com.company.material.Dowel;
import com.company.material.Leg;

import java.util.Arrays;
import java.util.HashMap;


public class Main {

    public static void main(String[] args) {

        //Создаём сборочный цех
        AssemblyRoom assemblyRoom = new AssemblyRoom(Arrays.asList(
                new Kitchen(),
                new NightStand(),
                new CupBoard(),
                new TabouretGood(),
                new TabouretBad()));

        //Создаём заготовочный цех
        BlankRoom blankRoom = new BlankRoom(Arrays.asList(
                new Board(),
                new Leg(),
                new ChipBoard(),
                new Dowel()));

        //В заготовочном цехе вот столько древесины
        blankRoom.setWood((float) 15);

        //Создаём покупателя
        Customer c = new Customer(1000000);

        //Создаём заказ
        HashMap<String,Integer> request = new HashMap<>();
            request.put ("Шкаф",1);
            request.put ("Табурет Хороший",2);
            request.put ("Кухня",1);

        //делаем заказ ПОКУПАТЕЛЬ--->СБОРОЧНЫЙ ЦЕХ
        c.requestOrder(request,assemblyRoom, blankRoom);

    }
}