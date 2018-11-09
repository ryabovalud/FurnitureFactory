package com.company;

import com.company.furnitur.Item;
import com.company.material.Material;

import java.util.*;

public class AssemblyRoom {

    private List<Item> items = new ArrayList<>();

    public AssemblyRoom() {
    }

    public AssemblyRoom(List<Item> items) {
        this.items = items;
    }


    public void acceptOrder(Customer c, HashMap<String, Integer> request, BlankRoom blankRoom) {
        if (blankRoom.getWood() == 0)
            System.out.println("Вам не повезло - в цехе нет древесины!!");
        else {
            Iterator it = request.entrySet().iterator();
            int total = 0;
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                int n = (Integer) pair.getValue();
                Item elem = items.stream().
                        filter(p -> p.getName().equals(pair.getKey())).
                        findFirst().orElse(null);
                if (elem == null) {
                    System.out.println(pair.getKey() + " - Нет в продаже");
                    return;
                }
                total += elem.getPrice() * n;
            }

            System.out.println("Сумма Вашего заказа: " + total);

            if (c.getMoney() < total) {
                System.out.println("У Вас не хватает денег");
                return;
            }
            System.out.println("У Вас хватает денег");

            //Составим заказ на материалы
            HashMap<String, Integer> requestMaterial = new HashMap<>();

            it = request.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                int n = (Integer) pair.getValue();
                HashMap<String, Integer> material = new HashMap<>();
                Item elem = items.stream().
                        filter(p -> p.getName().equals(pair.getKey())).
                        findFirst().orElse(null);
                if (elem == null) {
                    System.out.println(pair.getKey() + " - Нет в продаже");
                    return;
                }

                material = elem.getMaterial();
                for (Map.Entry<String, Integer> entry : material.entrySet()) {
                    if (requestMaterial.containsKey(entry.getKey())) {
                        int value = requestMaterial.get(entry.getKey());
                        requestMaterial.put(entry.getKey(), value + entry.getValue() * n);
                    } else
                        requestMaterial.put(entry.getKey(), entry.getValue());
                }
            }
            System.out.println("Для заказа нужно вот столько деталей:" + requestMaterial);

            //Закажем материалы у заготовочного цеха
            HashMap<String, Integer> newMaterial = new HashMap<>();
            newMaterial = blankRoom.acceptOrder(requestMaterial);
            if (newMaterial == requestMaterial) {
                System.out.println("Спасибо за покупку!! С Вашего счёта списанно - " + total + " рублей");
                c.setMoney(c.getMoney() - total);
            } else {
                System.out.println("На весь заказ древесины не хватило");
                System.out.println("Хватило только на: " + newMaterial);
                //максимально укомплектуем заказ
                HashMap<String, Integer> newRequest = new HashMap<>();
                total = 0;
                for (Item elem : items) {//идём по полному списку всей мебели
                    String key = elem.getName();
                    //Если эту мебель нужно сделать
                    if (request.containsKey(key)) {
                        for (int i = 1; i <= request.get(key); i++) {
                            HashMap<String, Integer> materialForElem = elem.getMaterial();
                            it = materialForElem.entrySet().iterator();
                            boolean okey = true;
                            while (it.hasNext()) {
                                Map.Entry pair = (Map.Entry) it.next();
                                int n = (Integer) pair.getValue();
                                String s = (String) pair.getKey();
                                if (newMaterial.containsKey(s) && newMaterial.get(s) >= n) {//если у нас хватает материалов на мебель
                                    continue;//смотрим дальше
                                } else //а если не хватает
                                    okey = false; // то мы её не сделаем
                            }

                            if (okey) {//нам хватило материала на какую-то мебель
                                newRequest.put(key, i);
                                total += elem.getPrice();
                            }
                        }
                    }
                }
                if (total > 0) {
                    System.out.println("Древесиы хватает на: " + newRequest);
                    System.out.println("Спасибо за покупку!! С Вашего счёта списанно - " + total + " рублей");
                    c.setMoney(c.getMoney() - total);
                } else
                    System.out.println("У нас не хватает материалова ни на какую мебель, извините!!!!");
            }

        }
    }
}
